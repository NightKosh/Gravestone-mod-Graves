package nightkosh.gravestone.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import nightkosh.gravestone.api.IGraveStoneHelper;
import nightkosh.gravestone.api.death_handler.ICustomEntityDeathHandler;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.core.GSBlocks;
import nightkosh.gravestone.core.MobHandler;
import nightkosh.gravestone.helper.api.APIGraveGeneration;
import nightkosh.gravestone.inventory.GraveInventory;
import nightkosh.gravestone.block_entity.DeathMessageInfo;
import nightkosh.gravestone.block_entity.GraveStoneBlockEntity;

import javax.annotation.Nullable;
import java.util.*;

import static nightkosh.gravestone.ModGraveStone.GRAVE_LOGGER;
import static nightkosh.gravestone.ModGraveStone.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveGenerationHelper implements IGraveStoneHelper {

    public static final IGraveStoneHelper INSTANCE = new GraveGenerationHelper();

    protected static final Random rand = new Random();

    public static ArrayList<Item> swordsList = new ArrayList<>(
            Arrays.asList(
                    Items.WOODEN_SWORD,
                    Items.STONE_SWORD,
                    Items.IRON_SWORD,
                    Items.GOLDEN_SWORD,
                    Items.DIAMOND_SWORD
            )
    );

    public enum EnumGraveTypeByEntity {
        ALL_GRAVES,
        PLAYER_GRAVES,
        VILLAGERS_GRAVES,
        HUMAN_GRAVES,
        PETS_GRAVES,
        DOGS_GRAVES,
        CATS_GRAVES,
        HORSE_GRAVES
    }

    @Override
    public void addSwordToSwordsList(Item sword) {
        if (sword != null) {
            swordsList.add(sword);
        }
    }

    private static final List<EnumGraveType> GENERATED_PLAYER_GRAVES_TYPES = List.of(
            EnumGraveType.GRAVE_STONE,
            EnumGraveType.CROSS,
            EnumGraveType.OBELISK,
            EnumGraveType.CELTIC_CROSS,
            EnumGraveType.GRAVE_PLATE);
    private static final List<EnumGraveType> GENERATED_VILLAGERS_GRAVES_TYPES = List.of(EnumGraveType.VILLAGER_GRAVE_STONE);
    //TODO ????
    private static final List<EnumGraveType> GENERATED_DOGS_GRAVES_TYPES = List.of(EnumGraveType.PET_GRAVE_STONE);
    private static final List<EnumGraveType> GENERATED_CAT_GRAVES_TYPES = List.of(EnumGraveType.PET_GRAVE_STONE);
    private static final List<EnumGraveType> GENERATED_HORSE_GRAVES_TYPES = List.of(EnumGraveType.PET_GRAVE_STONE);


    private static void addNonEmptyItems(List<ItemStack> items, NonNullList<ItemStack> itemsToAdd) {
        for (var stack : itemsToAdd) {
            if (!stack.isEmpty()) {
                items.add(stack);
            }
        }
    }

    public static void createPlayerGrave(Player player, Collection<ItemEntity> drops, DamageSource damageSource, long spawnTime) {
        //TODO isInRestrictedArea && emty grave option ????
        if (!player.level.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) &&
                GSConfigs.GRAVE_ITEMS_COUNT.get() > 0 &&
                !isInRestrictedArea(player.level, player.blockPosition())) {
            List<ItemStack> items = new ArrayList<>(41);

            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("Player {} has {} items.", player.getScoreboardName(), drops.size());
            }
            for (var entityItem : drops) {
                if (GSConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("Next item will be placed to the grave {} x {}", entityItem.getItem().getHoverName().getString(), entityItem.getItem().getCount());
                }
                items.add(entityItem.getItem().copy());
            }

            for (var additionalItems : APIGraveGeneration.PLAYER_ITEMS) {
                try {
                    var modItems = additionalItems.addItems(player, damageSource);
                    if (modItems != null && !modItems.isEmpty()) {
                        //TODO dev logs
                        items.addAll(modItems);
                    }
                } catch (Exception e) {
                    LOGGER.error("Compatibility error occurred in additionalItems.addItems", e);
                }
            }

            // remove some items by other mods
            for (var additionalItems : APIGraveGeneration.PLAYER_ITEMS) {
                try {
                    //TODO dev logs
                    additionalItems.getItems(player, damageSource, items);
                } catch (Exception e) {
                    LOGGER.error("Compatibility error occurred in additionalItems.getItems", e);
                }
            }

            if (GSConfigs.GENERATE_EMPTY_PLAYER_GRAVES.get() || !items.isEmpty()) {
                if (createGrave(player, damageSource, items, EnumGraveTypeByEntity.PLAYER_GRAVES, false, spawnTime)) {
                    // graves successfully created, discard all original items to prevent duplication
                    drops.forEach(Entity::discard);
                }
            }
        } else if (GSConfigs.GENERATE_EMPTY_PLAYER_GRAVES.get()) {
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("Going to create empty grave.");
            }
            createGrave(player, damageSource, null, EnumGraveTypeByEntity.PLAYER_GRAVES, false, spawnTime);
        }
    }

    public static void createVillagerGrave(Villager villager, DamageSource damageSource) {
        var items = new ArrayList<ItemStack>(5);
        for (var additionalItems : APIGraveGeneration.VILLAGER_ITEMS) {
            items.addAll(additionalItems.addItems(villager, damageSource));
        }

        //TODO
//        IItemHandler itemHandler = villager.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, villager.getHorizontalFacing());
//        if (villager.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, villager.getHorizontalFacing())) {
//            for (int slot = 0; slot < itemHandler.getSlots(); slot++) {
//                var stack = itemHandler.extractItem(slot, 100500, false);
//                if (stack != null && !stack.isEmpty()) {
//                    items.add(stack);
//                }
//            }
//        }

        long spawnTime = MobHandler.getAndRemoveSpawnTime(villager);
        createGrave(villager, damageSource, items, GraveGenerationHelper.EnumGraveTypeByEntity.VILLAGERS_GRAVES, true, spawnTime);
    }

    public static void createDogGrave(Wolf dog, DamageSource damageSource) {
        if (dog.isTame()) {
            long spawnTime = MobHandler.getAndRemoveSpawnTime(dog);
            createGrave(dog, damageSource, getDogsItems(dog, damageSource), EnumGraveTypeByEntity.DOGS_GRAVES, false, spawnTime);
        }
    }

    public static void createCatGrave(Cat cat, DamageSource damageSource) {
        if (cat.isTame()) {
            long spawnTime = MobHandler.getAndRemoveSpawnTime(cat);
            createGrave(cat, damageSource, getCatsItems(cat, damageSource), EnumGraveTypeByEntity.CATS_GRAVES, false, spawnTime);
        }
    }

    private static List<ItemStack> getDogsItems(Wolf dog, DamageSource damageSource) {
        var items = new ArrayList<ItemStack>(5);
        for (var additionalItems : APIGraveGeneration.DOG_ITEMS) {
            items.addAll(additionalItems.addItems(dog, damageSource));
        }

        return items;
    }

    private static List<ItemStack> getCatsItems(Cat cat, DamageSource damageSource) {
        var items = new ArrayList<ItemStack>(5);
        for (var additionalItems : APIGraveGeneration.CAT_ITEMS) {
            items.addAll(additionalItems.addItems(cat, damageSource));
        }

        //TODO
//        IItemHandler itemHandler = cat.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, cat.getHorizontalFacing());
//        if (cat.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, cat.getHorizontalFacing())) {
//            for (int slot = 0; slot < itemHandler.getSlots(); slot++) {
//                ItemStack stack = itemHandler.extractItem(slot, 100500, false);
//                if (stack != null && !stack.isEmpty()) {
//                    items.add(stack);
//                }
//            }
//        }
        return items;
    }

    public static void createHorseGrave(AbstractHorse horse, DamageSource damageSource) {
        if (horse.isTamed()) {
            var items = new ArrayList<ItemStack>();
            items.addAll(getHorseItems(horse));

            for (var additionalItems : APIGraveGeneration.HORSE_ITEMS) {
                items.addAll(additionalItems.addItems(horse, damageSource));
            }

            long spawnTime = MobHandler.getAndRemoveSpawnTime(horse);
            createGrave(horse, damageSource, items, EnumGraveTypeByEntity.HORSE_GRAVES, false, spawnTime);
        }
    }

    private static List<ItemStack> getHorseItems(AbstractHorse horse) {
        var items = new ArrayList<ItemStack>();

        //TODO
//        IItemHandler itemHandler = horse.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, horse.getHorizontalFacing());
//        if (horse.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, horse.getHorizontalFacing())) {
//            for (int slot = 0; slot < itemHandler.getSlots(); slot++) {
//                ItemStack stack = itemHandler.extractItem(slot, 100500, false);
//                if (stack != null && !stack.isEmpty()) {
//                    items.add(stack);
//                }
//            }
//        }

        return items;
    }

    public static boolean createGrave(
            Entity entity, DamageSource damageSource, List<ItemStack> items,
            EnumGraveTypeByEntity graveTypeByEntity, boolean isVillager, long spawnTime) {
        if (isInRestrictedArea(entity.level, entity.blockPosition())) {
            LOGGER.info("Can't generate " + entity.getName() + "'s grave in restricted area. " + entity.blockPosition());
            return false;
        } else {
            int age = (int) (entity.level.getGameTime() - spawnTime) / 24000;
            var oldPos = entity.blockPosition();
            var pos = new BlockPos(oldPos.getX(), oldPos.getY(), oldPos.getZ() - 1);
            var graveInfo = getGraveOnDeath(entity.level, pos, entity, graveTypeByEntity, items, age, damageSource);
            var messageInfo = getDeathMessage((LivingEntity) entity, damageSource, isVillager);
            return createOnDeath(entity, entity.level, pos, messageInfo, items, age, graveInfo, damageSource);
        }
    }

    public static void createCustomGrave(Entity entity, LivingDeathEvent event, ICustomEntityDeathHandler customEntityDeathHandler) {
        //TODO
//        if (isInRestrictedArea(entity.getEntityWorld(), entity.getPosition())) {
//            LOGGER.info("Can't generate " + entity.getName() + "'s grave in restricted area. " + entity.getPosition());
//            if (customEntityDeathHandler.getItems() != null) {
//                customEntityDeathHandler.getItems().stream().filter(item -> item != null).forEach(item -> {
//                    GraveInventory.dropItem(item, entity.getEntityWorld(), entity.getPosition());
//                });
//            }
//        } else {
//            int age = customEntityDeathHandler.getAge();
//            var graveInfo = new GraveInfoOnDeath();
//            graveInfo.setGrave(EnumGraves.getByTypeAndMaterial(customEntityDeathHandler.getGraveType(entity, event.getSource()),
//                    customEntityDeathHandler.getGraveMaterial(entity, event.getSource())));
//            graveInfo.setSword(customEntityDeathHandler.getSword());
//
//            var pos = new BlockPos(entity.posX, Math.round(entity.posY), entity.posZ - 1);
//            var messageInfo = getDeathMessage((LivingEntity) entity, event.getSource().damageType, false);
//            return createOnDeath(entity, entity.getEntityWorld(), pos, messageInfo, customEntityDeathHandler.getItems(), age, graveInfo, event.getSource());
//        }
    }

    private static GraveInfoOnDeath getGraveOnDeath(
            Level level, BlockPos pos, Entity entity, EnumGraveTypeByEntity graveTypeByEntity,
            List<ItemStack> items, int age, DamageSource damageSource) {

        EnumGraveMaterial material = null;
        EnumGraveType graveType = null;
        ItemStack sword = null;

        //if entity experienced enough or lived a long time
        if (shouldChooseGraveTypeByAgeOrLevel(entity, graveTypeByEntity, age)) {
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("Entity experienced enough or lived a long time");
            }
            material = getGraveMaterialByAgeOrLevel(entity, age, graveTypeByEntity);
            graveType = getDefaultGraveTypes(level.random, graveTypeByEntity);
        } else {
            // if sword grave TODO
//            if (graveTypeByEntity == EnumGraveTypeByEntity.PLAYER_GRAVES &&
//                    GSConfigs.GENERATE_SWORD_GRAVES.get() &&
//                    level.random.nextInt(4) == 0) {
//                sword = getSwordFromInventory(items);
//                if (sword != null) {
//                    sword = sword;
//                    graveType = EnumGraveType.SWORD;
//                }
//            } else {
                // check death related material
                material = getGraveMaterialByDeath(damageSource);
                // otherwise get material by biome
                if (material == null) {
                    material = getGraveMaterialByBiomes(level, pos);
                }

                if (graveType == null) {
                    graveType = getDefaultGraveTypes(level.random, graveTypeByEntity);
                }
//            }
        }

        if (GSConfigs.DEBUG_MODE.get()) {
            LOGGER.info("Chosen graveType {}, material {}", graveType, material);
            if (sword != null) {
                LOGGER.info("Chosen sword {}", sword.getHoverName().getString());
            }
        }
        return new GraveInfoOnDeath(graveType, material, sword);
    }

    private static boolean createOnDeath(
            Entity entity, Level level, BlockPos pos, DeathMessageInfo deathInfo, List<ItemStack> items,
            int age, GraveInfoOnDeath graveInfo, DamageSource damageSource) {
        BlockPos newPos = null;
        Direction direction = null;
        Level newLevel = null;

        boolean hasCustomLocation = false;
        try {
            for (var position : APIGraveGeneration.GRAVE_POSITION_HANDLERS) {
                if (position.condition(level, entity, pos, damageSource)) {
                    newPos = position.gravePosition(level, entity, pos, damageSource);
                    if (newPos != null) {
                        hasCustomLocation = true;
                        //TODO
//                        direction = position.graveFacing(level, entity, pos, damageSource);
                        newLevel = position.getLevel(level, entity, pos, damageSource);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Can't get custom position of grave!");
        }

        if (hasCustomLocation) {
            LOGGER.info("Position of grave was changed by other mod");
        } else {
            direction = Direction.fromYRot(entity.getYRot());
            newPos = findPlaceForGrave(level, entity, pos, damageSource);
            newLevel = level;
        }

        BackupsHelper.addBackup(entity, newLevel, newPos, items);

        if (newPos != null) {
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("Trying to place grave at {}", newPos.toShortString());
            }
            level.setBlock(
                    newPos,
                    GSBlocks.getGraveBlock(graveInfo.graveType(), graveInfo.material())
                            .defaultBlockState()
                            .setValue(BlockGraveStone.FACING, direction),
                    2
            );

            if (newLevel.getBlockEntity(newPos) instanceof GraveStoneBlockEntity graveEntity) {
                if (graveInfo.graveType() == EnumGraveType.SWORD && graveInfo.sword() != null) {
                    graveEntity.setSword(graveInfo.sword());
                }
//
//                graveEntity.getDeathTextComponent().setLocalized();
                graveEntity.getDeathTextComponent().setName(deathInfo.getName());
                graveEntity.getDeathTextComponent().setDeathText(deathInfo.getDeathMessage());
                graveEntity.getDeathTextComponent().setKillerName(deathInfo.getKillerName());
                graveEntity.getInventory().setItems(items);
                graveEntity.setAge(age);
                if (entity instanceof Player player) {
                    //TODO
//                    graveEntity.setOwner(player.getUniqueID().toString());
                } else if (entity instanceof TamableAnimal tamable && tamable.isTame() && tamable.getOwner() != null) {
                    //TODO
//                    graveEntity.setOwner(tamable.getOwner().getUniqueID().toString());
                }
                return true;
            }
            //TODO
//            GRAVE_LOGGER.info("Create " + deathInfo.getName() + "'s grave at " + newPos.getX() + "x" + newPos.getY() + "x" + newPos.getZ());
            return false;
        } else {
            var itemStack = new ItemStack(GSBlocks.getGraveBlock(graveInfo.graveType(), graveInfo.material()).asItem());
            var tag = new CompoundTag();
            tag.putBoolean("isLocalized", true);
            tag.putString("name", deathInfo.getName());
            tag.putString("DeathText", deathInfo.getDeathMessage());
            tag.putString("KillerName", deathInfo.getKillerNameForTE());
            tag.putInt("Age", age);
            if (graveInfo.graveType() == EnumGraveType.SWORD && graveInfo.sword() != null) {
                GraveStoneHelper.addSwordInfo(tag, graveInfo.sword());
            }

            itemStack.setTag(tag);
            GraveInventory.dropItem(itemStack, level, pos);

            GRAVE_LOGGER.info("Can not create " + deathInfo.getName() + "'s grave at " + pos.getX() + "x" + pos.getY() + "x" + pos.getZ());

            return false;
        }
    }

    private static DeathMessageInfo getDeathMessage(LivingEntity entity, DamageSource damageSource, boolean isVillager) {
        var killer = entity.getLastAttacker();
        String shortString = "death.attack." + damageSource.toString();
        String fullString = shortString + ".player";

        String entityName = entity.getScoreboardName();//TODO ??? .getName();
        if (entityName == null) {
            entityName = "entity." + EntityType.getKey(entity.getType()).toString() + ".name";
        }

        if (killer != null) {
            String killerName;
            if (killer instanceof Player) {
                killerName = killer.getDisplayName().getString();
                if (isVillager) {
                    GRAVE_LOGGER.info("Villager was killed by " + killerName);
                }
            } else {
                killerName = EntityType.getKey(killer.getType()).toString() ;
                if (killerName == null) {
                    killerName = "entity.generic.name";
                } else {
                    killerName = "entity." + killerName + ".name";
                }
            }
            //TODO
//            if (I18n.canTranslate(fullString)) {
//                return new DeathMessageInfo(entityName, fullString, killerName);
//            } else {
                return new DeathMessageInfo(entityName, shortString, killerName);
//            }
        } else {
            return new DeathMessageInfo(entityName, shortString, null);
        }
    }

    private static boolean isInRestrictedArea(Level level, BlockPos pos) {
        return GSConfigs.restrictGraveGenerationInArea.stream()
                .anyMatch((area) -> area.isInArea(level, pos));
    }

    @Override
    public boolean isMagicDamage(DamageSource damageSource) {
        return damageSource.is(DamageTypes.MAGIC);//TODO || damageSource.damageType.toLowerCase().contains("magic");
    }

    @Override
    public boolean isMossyGrave(Level level, BlockPos pos, EnumGraveMaterial graveMaterial, EnumGraveType graveType) {
        return false;//TODO isMossyGrave(level, pos, EnumGraves.getByTypeAndMaterial(graveType, graveMaterial).getMaterial());
    }

    //TODO
//    public static boolean isMossyGrave(Level level, BlockPos pos, EnumGraveMaterial graveMaterial) {
//        Set<BiomeDictionary.Type> biomeTypesList = BiomeDictionary.getTypes(level.getBiome(pos));
//        return graveMaterial != EnumGraveMaterial.OTHER && (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP));
//    }

    public static boolean shouldChooseGraveTypeByAgeOrLevel(Entity entity, EnumGraveTypeByEntity graveTypeByEntity, int age) {
        if (graveTypeByEntity == EnumGraveTypeByEntity.PLAYER_GRAVES) {
            return ((Player) entity).experienceLevel >= 40;
        } else {
            return age > 100;
        }
    }

    public static EnumGraveMaterial getGraveMaterialByAgeOrLevel(Entity entity, int age, EnumGraveTypeByEntity graveTypeByEntity) {
        if (graveTypeByEntity == EnumGraveTypeByEntity.PLAYER_GRAVES) {
            return INSTANCE.getGraveMaterialByLevel(((Player) entity).experienceLevel);
        } else {
            return INSTANCE.getGraveMaterialByAge(age);
        }
    }

    @Nullable
    @Override
    public EnumGraveMaterial getGraveMaterialByLevel(int level) {
        if (level >= 60) {
            return EnumGraveMaterial.DIAMOND;
        } else if (level >= 40) {
            return EnumGraveMaterial.GOLD;
        }
        return null;
    }

    @Nullable
    @Override
    public EnumGraveMaterial getGraveMaterialByAge(int age) {
        if (age > 200) {
            return EnumGraveMaterial.DIAMOND;
        } else if (age > 100) {
            return EnumGraveMaterial.GOLD;
        }
        return null;
    }

    protected static EnumGraveType getDefaultGraveTypes(RandomSource random, EnumGraveTypeByEntity graveTypeByEntity) {
        var types = getDefaultGraveTypes(graveTypeByEntity);
        return types.get(random.nextInt(types.size()));
    }

    protected static List<EnumGraveType> getDefaultGraveTypes(EnumGraveTypeByEntity graveTypeByEntity) {
        return switch (graveTypeByEntity) {
            case VILLAGERS_GRAVES -> GENERATED_VILLAGERS_GRAVES_TYPES;
            case DOGS_GRAVES -> GENERATED_DOGS_GRAVES_TYPES;
            case CATS_GRAVES -> GENERATED_CAT_GRAVES_TYPES;
            case HORSE_GRAVES -> GENERATED_HORSE_GRAVES_TYPES;
            case PLAYER_GRAVES -> GENERATED_PLAYER_GRAVES_TYPES;
            default -> GENERATED_PLAYER_GRAVES_TYPES;
        };
    }

    public static EnumGraveMaterial getGraveMaterialByDeath(DamageSource damageSource) {
        if (isFireDamage(damageSource, null) || isLavaDamage(damageSource, null)) {
            return EnumGraveMaterial.OBSIDIAN;
        }
        return null;
    }
//
    public static boolean isFireDamage(DamageSource damageSource, String damageType) {
        return damageSource.is(DamageTypes.IN_FIRE) || damageSource.is(DamageTypes.ON_FIRE) || isFireDamage(damageType);
    }

    public static boolean isFireDamage(String damageType) {
        return false;//TODO damageType.toLowerCase().contains("nfire");
    }

    public static boolean isLavaDamage(DamageSource damageSource, String damageType) {
        return damageSource.is(DamageTypes.LAVA) || isLavaDamage(damageType);
    }

    public static boolean isLavaDamage(String damageType) {
        return false;//TODO damageType.toLowerCase().contains("lava");
    }

    public static boolean isMagicDamage(String damageText) {
        return damageText.toLowerCase().contains("magic");
    }

    public static boolean isExplosionDamage(DamageSource damageSource) {
        return false;//TODO isBlastDamage(damageSource.damageType) || isFireballDamage(damageSource.damageType);
    }

    public static boolean isBlastDamage(String damageType) {
        return damageType.toLowerCase().contains("explosion");
    }

    public static boolean isFireballDamage(String damageType) {
        return damageType.toLowerCase().contains("fireball");
    }

    public static EnumGraveMaterial getGraveMaterialByBiomes(Level level, BlockPos pos) {
        var biome = level.getBiome(pos);

        List<EnumGraveMaterial> materials = new ArrayList<>();
        if (biome.is(Tags.Biomes.IS_SANDY) || biome.is(BiomeTags.IS_BEACH) || biome.is(BiomeTags.IS_SAVANNA)) {
            materials.add(EnumGraveMaterial.SANDSTONE);
        }
        if (biome.is(BiomeTags.IS_JUNGLE) || biome.is(Tags.Biomes.IS_SWAMP)) {
            materials.add(EnumGraveMaterial.STONE);
        }
        if (biome.is(BiomeTags.IS_MOUNTAIN)) {
            materials.add(EnumGraveMaterial.GRANITE);
        }
        if (biome.is(BiomeTags.IS_HILL)) {
            materials.add(EnumGraveMaterial.DIORITE);
        }
        if (biome.is(Tags.Biomes.IS_PLAINS) || biome.is(Tags.Biomes.IS_MUSHROOM)) {
            materials.add(EnumGraveMaterial.STONE);
        }
        if (biome.is(Tags.Biomes.IS_SNOWY)) {
            materials.add(EnumGraveMaterial.ICE);
        }
        if (biome.is(BiomeTags.IS_NETHER)) {
            materials.add(EnumGraveMaterial.QUARTZ);
        }
        if (biome.is(Tags.Biomes.IS_WATER)) {
            materials.add(EnumGraveMaterial.PRIZMARINE);
        }

        if (materials.isEmpty()) {
            return EnumGraveMaterial.STONE;
        } else {
            return materials.get(level.random.nextInt(materials.size()));
        }
    }


    private static BlockPos findPlaceForGrave(Level level, Entity entity, BlockPos pos, DamageSource damageSource) {
        if (pos.getY() <= -64) {
            var groundPos = new BlockPos(pos.getX(), 0, pos.getZ());
            if (level.isEmptyBlock(groundPos) && level.isEmptyBlock(groundPos.above())) {
                level.setBlock(groundPos, Blocks.GRASS.defaultBlockState(), 3);
                return groundPos.above();
            } else {
                GRAVE_LOGGER.info("Can't find position for grave on death in the void!");
            }
        }

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int newY = getGround(level, x, y, z);

        if (canGenerateGraveAtCoordinates(level, new BlockPos(x, newY, z))) {
            return new BlockPos(x, newY, z);
        } else {
            int dx = 1;
            int dz = 1;

            while (Math.abs(dx) < 9 && Math.abs(dz) < 9) {
                if (dx < 0) {
                    for (int newX = x - 1; newX >= x + dx; newX--) {
                        newY = getGround(level, newX, y, z);
                        if (canGenerateGraveAtCoordinates(level, new BlockPos(newX, newY, z))) {
                            return new BlockPos(newX, newY, z);
                        }
                    }
                } else {
                    for (int newX = x + 1; newX <= x + dx; newX++) {
                        newY = getGround(level, newX, y, z);
                        if (canGenerateGraveAtCoordinates(level, new BlockPos(newX, newY, z))) {
                            return new BlockPos(newX, newY, z);
                        }
                    }
                }
                x += dx;

                if (dz < 0) {
                    for (int newZ = z - 1; newZ >= z + dz; newZ--) {
                        newY = getGround(level, x, y, newZ);
                        if (canGenerateGraveAtCoordinates(level, new BlockPos(x, newY, newZ))) {
                            return new BlockPos(x, newY, newZ);
                        }
                    }
                } else {
                    for (int newZ = z + 1; newZ <= z + dz; newZ++) {
                        newY = getGround(level, x, y, newZ);
                        if (canGenerateGraveAtCoordinates(level, new BlockPos(x, newY, newZ))) {
                            return new BlockPos(x, newY, newZ);
                        }
                    }
                }
                z += dz;

                if (dx < 0) {
                    dx = Math.abs(dx) + 1;
                } else {
                    dx = (dx + 1) * -1;
                }

                if (dz < 0) {
                    dz = Math.abs(dz) + 1;
                } else {
                    dz = (dz + 1) * -1;
                }

            }
        }

        return null;
    }

    public static int getGround(Level level, int x, int y, int z) {
        while (true) {
            var pos = new BlockPos(x, y - 1, z);
            var state = level.getBlockState(pos);
            if (y > -63 && (level.isEmptyBlock(pos) ||
                    state.canBeReplaced() ||
                    state.getFluidState().isSource())) {
                y--;
            } else {
                return y;
            }
        }
    }

    public static boolean canGenerateGraveAtCoordinates(Level level, BlockPos pos) {
        var state = level.getBlockState(pos);
        var posDown = pos.below();
        var stateDown = level.getBlockState(pos.below());
        return stateDown.isSolidRender(level, posDown) &&
                stateDown.isCollisionShapeFullBlock(level, posDown) &&
                (level.isEmptyBlock(pos) ||
                        state.getFluidState().isSource() ||
                        state.canBeReplaced());
    }

    private static ItemStack getSwordFromInventory(List<ItemStack> items) {
        if (items != null) {
            for (var stack : items) {
                if (stack != null && swordsList.contains(stack.getItem())) {
                    var sword = stack.copy();
                    items.remove(stack);
                    return sword;
                }
            }
        }

        return null;
    }

}
