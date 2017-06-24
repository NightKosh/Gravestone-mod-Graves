package nightkosh.gravestone.helper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import nightkosh.gravestone.api.IGraveStoneHelper;
import nightkosh.gravestone.api.death_handler.ICustomEntityDeathHandler;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.api.grave_items.*;
import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.GSBlock;
import nightkosh.gravestone.core.MobHandler;
import nightkosh.gravestone.core.compatibility.CompatibilityBattlegear;
import nightkosh.gravestone.core.compatibility.CompatibilityTwilightForest;
import nightkosh.gravestone.core.logger.GSLogger;
import nightkosh.gravestone.helper.api.APIGraveGeneration;
import nightkosh.gravestone.inventory.GraveInventory;
import nightkosh.gravestone.tileentity.DeathMessageInfo;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;

import java.util.*;

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

    private static final EnumGraveType[] GENERATED_PLAYER_GRAVES_TYPES = {
            EnumGraveType.VERTICAL_PLATE,
            EnumGraveType.CROSS,
            EnumGraveType.OBELISK,
            EnumGraveType.CELTIC_CROSS,
            EnumGraveType.HORIZONTAL_PLATE
    };
    private static final EnumGraveType[] STARVED_PLAYER_GRAVES_TYPES = {
            EnumGraveType.STARVED_CORPSE,
    };
    private static final EnumGraveType[] WITHERED_PLAYER_GRAVES_TYPES = {
            EnumGraveType.WITHERED_CORPSE,
    };
    private static final EnumGraveType[] GENERATED_VILLAGERS_GRAVES_TYPES = {EnumGraveType.VILLAGER_STATUE};
    private static final EnumGraveType[] GENERATED_DOGS_GRAVES_TYPES = {EnumGraveType.DOG_STATUE};
    private static final EnumGraveType[] GENERATED_CAT_GRAVES_TYPES = {EnumGraveType.CAT_STATUE};
    private static final EnumGraveType[] GENERATED_HORSE_GRAVES_TYPES = {EnumGraveType.HORSE_STATUE};
    private static final EnumGraveType[] GENERATED_CREEPER_STATUES_GRAVES_TYPES = {EnumGraveType.CREEPER_STATUE};

    public static void createPlayerGrave(EntityPlayer player, LivingDeathEvent event, long spawnTime) {
        if (player.getEntityWorld() != null && !player.getEntityWorld().getGameRules().getBoolean("keepInventory") && Config.graveItemsCount > 0 &&
                !isInRestrictedArea(player.getPosition())) {
            List<ItemStack> items = new ArrayList<>(40);

//            GSCompatibilityAntiqueAtlas.placeDeathMarkerAtDeath(player); //TODO !!!!!!!!!!!!

            items.addAll(player.inventory.mainInventory);
            items.addAll(player.inventory.armorInventory);
            items.addAll(player.inventory.offHandInventory);

            CompatibilityTwilightForest.addSlotTags(items);
            CompatibilityBattlegear.addItems(items, player);

            if (!CompatibilityTwilightForest.handleCharmsOfKeeping(items, player)) {
                player.inventory.clear();
            }

            for (IPlayerItems additionalItems : APIGraveGeneration.PLAYER_ITEMS) {
                try {
                    List<ItemStack> modItems = additionalItems.addItems(player, event.getSource());
                    if (modItems != null && modItems.size() != 0) {
                        items.addAll(modItems);
                    }
                } catch (Exception e) {
                    GSLogger.logError("Compatibility error occurred in additionalItems.addItems");
                    e.printStackTrace();
                }
            }

            //TODO is it really required??
            CompatibilityTwilightForest.removeSlotTags(items);


            for (IPlayerItems additionalItems : APIGraveGeneration.PLAYER_ITEMS) {
                try {
                    additionalItems.getItems(player, event.getSource(), items);
                } catch (Exception e) {
                    GSLogger.logError("Compatibility error occurred in additionalItems.getItems");
                    e.printStackTrace();
                }
            }

            createGrave(player, event, items, EnumGraveTypeByEntity.PLAYER_GRAVES, false, spawnTime);
        } else {
            createGrave(player, event, null, EnumGraveTypeByEntity.PLAYER_GRAVES, false, spawnTime);
        }
    }

    public static void createVillagerGrave(EntityVillager villager, LivingDeathEvent event) {
        List<ItemStack> items = new ArrayList<>(5);
        for (IVillagerItems additionalItems : APIGraveGeneration.VILLAGER_ITEMS) {
            items.addAll(additionalItems.addItems(villager, event.getSource()));
        }

        IItemHandler itemHandler =  villager.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, villager.getHorizontalFacing());
        if (villager.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, villager.getHorizontalFacing())) {
            for (int slot = 0 ; slot < itemHandler.getSlots(); slot++) {
                ItemStack stack = itemHandler.extractItem(slot, 100500, false);
                if (stack != null && !stack.isEmpty()) {
                    items.add(stack);
                }
            }
        }

        long spawnTime = MobHandler.getAndRemoveSpawnTime(event.getEntity());
        createGrave(villager, event, items, GraveGenerationHelper.EnumGraveTypeByEntity.VILLAGERS_GRAVES, true, spawnTime);
    }

    public static void createDogGrave(EntityWolf dog, LivingDeathEvent event) {
        if (dog.isTamed()) {
            long spawnTime = MobHandler.getAndRemoveSpawnTime(event.getEntity());
            createGrave(dog, event, getDogsItems(dog, event), EnumGraveTypeByEntity.DOGS_GRAVES, false, spawnTime);
        }
    }

    public static void createCatGrave(EntityOcelot cat, LivingDeathEvent event) {
        if (cat.isTamed()) {
            long spawnTime = MobHandler.getAndRemoveSpawnTime(event.getEntity());
            createGrave(cat, event, getCatsItems(cat, event), EnumGraveTypeByEntity.CATS_GRAVES, false, spawnTime);
        }
    }

    private static List<ItemStack> getDogsItems(EntityWolf dog, LivingDeathEvent event) {
        List<ItemStack> items = new ArrayList<>(5);
        for (IDogItems additionalItems : APIGraveGeneration.DOG_ITEMS) {
            items.addAll(additionalItems.addItems(dog, event.getSource()));
        }

        IItemHandler itemHandler =  dog.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dog.getHorizontalFacing());
        if (dog.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dog.getHorizontalFacing())) {
            for (int slot = 0 ; slot < itemHandler.getSlots(); slot++) {
                ItemStack stack = itemHandler.extractItem(slot, 100500, false);
                if (stack != null && !stack.isEmpty()) {
                    items.add(stack);
                }
            }
        }
        return items;
    }

    private static List<ItemStack> getCatsItems(EntityOcelot cat, LivingDeathEvent event) {
        List<ItemStack> items = new ArrayList<>(5);
        for (ICatItems additionalItems : APIGraveGeneration.CAT_ITEMS) {
            items.addAll(additionalItems.addItems(cat, event.getSource()));
        }

        IItemHandler itemHandler =  cat.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, cat.getHorizontalFacing());
        if (cat.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, cat.getHorizontalFacing())) {
            for (int slot = 0 ; slot < itemHandler.getSlots(); slot++) {
                ItemStack stack = itemHandler.extractItem(slot, 100500, false);
                if (stack != null && !stack.isEmpty()) {
                    items.add(stack);
                }
            }
        }
        return items;
    }

    public static void createHorseGrave(AbstractHorse horse, LivingDeathEvent event) {
        if (horse.isTame()) {
            List<ItemStack> items = new ArrayList<>();
            items.addAll(getHorseItems(horse));

            for (IHorseItems additionalItems : APIGraveGeneration.HORSE_ITEMS) {
                items.addAll(additionalItems.addItems(horse, event.getSource()));
            }

            long spawnTime = MobHandler.getAndRemoveSpawnTime(event.getEntity());
            createGrave(horse, event, items, EnumGraveTypeByEntity.HORSE_GRAVES, false, spawnTime);
        }
    }

    private static List<ItemStack> getHorseItems(AbstractHorse horse) {
        List<ItemStack> items = new ArrayList<>();

        IItemHandler itemHandler =  horse.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, horse.getHorizontalFacing());
        if (horse.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, horse.getHorizontalFacing())) {
            for (int slot = 0 ; slot < itemHandler.getSlots(); slot++) {
                ItemStack stack = itemHandler.extractItem(slot, 100500, false);
                if (stack != null && !stack.isEmpty()) {
                    items.add(stack);
                }
            }
        }

        return items;
    }

    public static void createGrave(Entity entity, LivingDeathEvent event, List<ItemStack> items, EnumGraveTypeByEntity graveTypeByEntity, boolean isVillager, long spawnTime) {
        if (isInRestrictedArea(entity.getPosition())) {
            GSLogger.logInfo("Can't generate " + entity.getName() + "'s grave in restricted area. " + entity.getPosition().toString());
            if (items != null) {
                items.stream().filter(item -> item != null).forEach(item -> {
                    GraveInventory.dropItem(item, entity.getEntityWorld(), entity.getPosition());
                });
            }
        } else {
            int age = (int) (entity.getEntityWorld().getWorldTime() - spawnTime) / 24000;
            BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ - 1);
            GraveInfoOnDeath graveInfo = getGraveOnDeath(entity.getEntityWorld(), pos, entity, graveTypeByEntity, items, age, event.getSource());
            DeathMessageInfo messageInfo = getDeathMessage((EntityLivingBase) entity, event.getSource().damageType, isVillager);
            createOnDeath(entity, entity.getEntityWorld(), pos, messageInfo, items, age, graveInfo, event.getSource());
        }
    }

    public static void createCustomGrave(Entity entity, LivingDeathEvent event, ICustomEntityDeathHandler customEntityDeathHandler) {
        if (isInRestrictedArea(entity.getPosition())) {
            GSLogger.logInfo("Can't generate " + entity.getName() + "'s grave in restricted area. " + entity.getPosition().toString());
            if (customEntityDeathHandler.getItems() != null) {
                customEntityDeathHandler.getItems().stream().filter(item -> item != null).forEach(item -> {
                    GraveInventory.dropItem(item, entity.getEntityWorld(), entity.getPosition());
                });
            }
        } else {
            int age = customEntityDeathHandler.getAge();
            GraveInfoOnDeath graveInfo = new GraveInfoOnDeath();
            graveInfo.setGrave(EnumGraves.getByTypeAndMaterial(customEntityDeathHandler.getGraveType(entity, event.getSource()),
                    customEntityDeathHandler.getGraveMaterial(entity, event.getSource())));
            graveInfo.setSword(customEntityDeathHandler.getSword());
            graveInfo.setEnchanted(customEntityDeathHandler.isEnchanted(entity, event.getSource()));
            graveInfo.setMossy(customEntityDeathHandler.isMossy(entity, event.getSource()));

            BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ - 1);
            DeathMessageInfo messageInfo = getDeathMessage((EntityLivingBase) entity, event.getSource().damageType, false);
            createOnDeath(entity, entity.getEntityWorld(), pos, messageInfo, customEntityDeathHandler.getItems(), age, graveInfo, event.getSource());
        }
    }

    private static GraveInfoOnDeath getGraveOnDeath(World world, BlockPos pos, Entity entity, EnumGraveTypeByEntity graveTypeByEntity,
                                                    List<ItemStack> items, int age, DamageSource damageSource) {
        GraveInfoOnDeath graveInfo = new GraveInfoOnDeath();
        if (chooseGraveTypeByAgeOrLevel(entity, graveTypeByEntity, age)) {
            EnumGraveMaterial material = getGraveMaterialByAgeOrLevel(entity, age, graveTypeByEntity);
            EnumGraveType[] type;
            if (isExplosionDamage(damageSource)) {
                type = GENERATED_CREEPER_STATUES_GRAVES_TYPES;
            } else {
                type = getDefaultGraveTypes(graveTypeByEntity);
            }

            graveInfo.grave = getGraveType(type, material);
        } else {
            graveInfo.grave = getGraveByDeath(damageSource, graveTypeByEntity, entity, age);
            if (graveInfo.grave == null) {
                if (graveTypeByEntity == EnumGraveTypeByEntity.PLAYER_GRAVES && Config.generateSwordGraves &&
                        world.rand.nextInt(4) == 0 && graveTypeByEntity.equals(EnumGraveTypeByEntity.PLAYER_GRAVES)) {
                    ItemStack sword = getSwordFromInventory(items);
                    if (sword != null) {
                        graveInfo.sword = sword;
                        graveInfo.grave = EnumGraves.SWORD;
                    }
                }

                if (graveInfo.grave == null) {
                    graveInfo.grave = getGraveTypeByBiomes(world, pos, graveTypeByEntity, damageSource);
                }
            }
        }

        graveInfo.setMossy(isMossyGrave(world, pos, graveInfo.grave.getMaterial()));
        graveInfo.setEnchanted(INSTANCE.isMagicDamage(damageSource));

        return graveInfo;
    }

    private static class GraveInfoOnDeath {
        private EnumGraves grave;
        private ItemStack sword;
        private boolean enchanted;
        private boolean mossy;

        public EnumGraves getGrave() {
            return grave;
        }

        public ItemStack getSword() {
            return sword;
        }

        public boolean isEnchanted() {
            return enchanted;
        }

        public boolean isMossy() {
            return mossy;
        }

        public void setGrave(EnumGraves grave) {
            this.grave = grave;
        }

        public void setSword(ItemStack sword) {
            this.sword = sword;
        }

        public void setEnchanted(boolean enchanted) {
            this.enchanted = enchanted;
        }

        public void setMossy(boolean mossy) {
            this.mossy = mossy;
        }
    }

    private static void createOnDeath(Entity entity, World world, BlockPos pos, DeathMessageInfo deathInfo, List<ItemStack> items,
                                      int age, GraveInfoOnDeath graveInfo, DamageSource damageSource) {
        EnumFacing direction = EnumFacing.getHorizontal(MathHelper.floor((double) (entity.rotationYaw * 4 / 360F) + 0.5) & 3);

        BlockPos newPos = findPlaceForGrave(world, pos);
        if (newPos != null) {
            world.setBlockState(newPos, GSBlock.GRAVE_STONE.getDefaultState().withProperty(BlockGraveStone.FACING, direction), 2);
            TileEntityGraveStone tileEntity = (TileEntityGraveStone) world.getTileEntity(newPos);

            if (tileEntity != null) {
                if (graveInfo.getSword() != null) {
                    tileEntity.setSword(graveInfo.getSword());
                }

                tileEntity.getDeathTextComponent().setLocalized();
                tileEntity.getDeathTextComponent().setName(deathInfo.getName());
                tileEntity.getDeathTextComponent().setDeathText(deathInfo.getDeathMessage());
                tileEntity.getDeathTextComponent().setKillerName(deathInfo.getKillerName());
                tileEntity.getInventory().setItems(items);
                tileEntity.setGraveType(graveInfo.getGrave().ordinal());
                tileEntity.setAge(age);
                tileEntity.setEnchanted(graveInfo.isEnchanted());
                tileEntity.setMossy(graveInfo.isMossy());
                if (entity instanceof EntityPlayer) {
                    tileEntity.setOwner(entity.getUniqueID().toString());
                } else if (entity instanceof EntityTameable && ((EntityTameable) entity).isTamed() && ((EntityTameable) entity).getOwner() != null) {
                    tileEntity.setOwner(((EntityTameable) entity).getOwner().getUniqueID().toString());
                }
            }
            GSLogger.logInfoGrave("Create " + deathInfo.getName() + "'s grave at " + newPos.getX() + "x" + newPos.getY() + "x" + newPos.getZ());
        } else {
            ItemStack itemStack = new ItemStack(Item.getItemFromBlock(GSBlock.GRAVE_STONE), 1);
            itemStack.setItemDamage(graveInfo.getGrave().ordinal());
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setBoolean("isLocalized", true);
            nbt.setString("name", deathInfo.getName());
            nbt.setString("DeathText", deathInfo.getDeathMessage());
            nbt.setString("KillerName", deathInfo.getKillerNameForTE());
            nbt.setBoolean("Enchanted", graveInfo.isEnchanted());
            nbt.setBoolean("Mossy", graveInfo.isMossy());
            nbt.setInteger("Age", age);

            if (graveInfo.getGrave() == EnumGraves.SWORD) {
                GraveStoneHelper.addSwordInfo(nbt, graveInfo.getSword());
            }

            itemStack.setTagCompound(nbt);
            GraveInventory.dropItem(itemStack, world, pos);

            if (items != null) {
                for (ItemStack item : items) {
                    if (item != null) {
                        GraveInventory.dropItem(item, world, pos);
                    }
                }
            }
            GSLogger.logInfoGrave("Can not create " + deathInfo.getName() + "'s grave at " + pos.getX() + "x" + pos.getY() + "x" + pos.getZ());
        }
    }

    private static DeathMessageInfo getDeathMessage(EntityLivingBase entity, String damageType, boolean isVillager) {
        EntityLivingBase killer = entity.getAttackingEntity();
        String shortString = "death.attack." + damageType;
        String fullString = shortString + ".player";

        String entityName = entity.getName();
        if (entityName == null) {
            entityName = "entity." + EntityList.getEntityString(entity) + ".name";
        }

        if (killer != null) {
            String killerName;
            if (killer instanceof EntityPlayer) {
                killerName = killer.getDisplayName().getFormattedText();
                if (isVillager) {
                    GSLogger.logInfoGrave("Villager was killed by " + killerName);
                }
            } else {
                killerName = EntityList.getEntityString(killer);
                if (killerName == null) {
                    killerName = "entity.generic.name";
                } else {
                    killerName = "entity." + killerName + ".name";
                }
            }
            if (I18n.canTranslate(fullString)) {
                return new DeathMessageInfo(entityName, fullString, killerName);
            } else {
                return new DeathMessageInfo(entityName, shortString, killerName);
            }
        } else {
            return new DeathMessageInfo(entityName, shortString, null);
        }
    }

    private static boolean isInRestrictedArea(BlockPos pos) {
        return Config.restrictGraveGenerationInArea.stream().anyMatch((area) -> area.isInArea(pos));
    }

    @Override
    public boolean isMagicDamage(DamageSource damageSource) {
        return DamageSource.MAGIC.equals(damageSource) || damageSource.damageType.toLowerCase().contains("magic");
    }

    @Override
    public boolean isMossyGrave(World world, BlockPos pos, EnumGraveMaterial graveMaterial, EnumGraveType graveType) {
        return isMossyGrave(world, pos, EnumGraves.getByTypeAndMaterial(graveType, graveMaterial).getMaterial());
    }

    public static boolean isMossyGrave(World world, BlockPos pos, EnumGraveMaterial graveMaterial) {
        Set<BiomeDictionary.Type> biomeTypesList = BiomeDictionary.getTypes(world.getBiome(pos));
        return graveMaterial != EnumGraveMaterial.OTHER && (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP));
    }

    public static boolean chooseGraveTypeByAgeOrLevel(Entity entity, EnumGraveTypeByEntity graveTypeByEntity, int age) {
        if (graveTypeByEntity == EnumGraveTypeByEntity.PLAYER_GRAVES) {
            return ((EntityPlayer) entity).experienceLevel >= 15;
        } else {
            return age >= 30;
        }
    }

    public static EnumGraveMaterial getGraveMaterialByAgeOrLevel(Entity entity, int age, EnumGraveTypeByEntity graveTypeByEntity) {
        if (graveTypeByEntity == EnumGraveTypeByEntity.PLAYER_GRAVES) {
            return INSTANCE.getGraveMaterialByLevel(((EntityPlayer) entity).experienceLevel);
        } else {
            return INSTANCE.getGraveMaterialByAge(age);
        }
    }

    @Override
    public EnumGraveMaterial getGraveMaterialByLevel(int level) {
        if (level >= 65) {
            return EnumGraveMaterial.EMERALD;
        } else if (level >= 55) {
            return EnumGraveMaterial.DIAMOND;
        } else if (level >= 45) {
            return EnumGraveMaterial.REDSTONE;
        } else if (level >= 35) {
            return EnumGraveMaterial.GOLD;
        } else if (level >= 25) {
            return EnumGraveMaterial.LAPIS;
        } else {
            return EnumGraveMaterial.IRON;
        }
    }

    @Override
    public EnumGraveMaterial getGraveMaterialByAge(int age) {
        if (age > 180) {
            return EnumGraveMaterial.EMERALD;
        } else if (age > 150) {
            return EnumGraveMaterial.DIAMOND;
        } else if (age > 120) {
            return EnumGraveMaterial.REDSTONE;
        } else if (age > 90) {
            return EnumGraveMaterial.GOLD;
        } else if (age > 60) {
            return EnumGraveMaterial.LAPIS;
        } else {
            return EnumGraveMaterial.IRON;
        }
    }

    protected static EnumGraveType[] getDefaultGraveTypes(EnumGraveTypeByEntity graveTypeByEntity) {
        switch (graveTypeByEntity) {
            case VILLAGERS_GRAVES:
                return GENERATED_VILLAGERS_GRAVES_TYPES;
            case DOGS_GRAVES:
                return GENERATED_DOGS_GRAVES_TYPES;
            case CATS_GRAVES:
                return GENERATED_CAT_GRAVES_TYPES;
            case HORSE_GRAVES:
                return GENERATED_HORSE_GRAVES_TYPES;
            default:
            case PLAYER_GRAVES:
                return GENERATED_PLAYER_GRAVES_TYPES;
        }
    }

    public static EnumGraves getGraveByDeath(DamageSource damageSource, EnumGraveTypeByEntity graveTypeByEntity, Entity entity, int age) {
        EnumGraveType[] graveTypes = null;
        EnumGraveMaterial material;

        if (isFireDamage(damageSource, damageSource.damageType) || isLavaDamage(damageSource, damageSource.damageType)) {
            material = EnumGraveMaterial.OBSIDIAN;
        } else if (graveTypeByEntity == EnumGraveTypeByEntity.PLAYER_GRAVES) {
            //TODO drown
            if (DamageSource.STARVE.equals(damageSource)) {
                graveTypes = STARVED_PLAYER_GRAVES_TYPES;
                material = EnumGraveMaterial.OTHER;
            } else if (DamageSource.WITHER.equals(damageSource)) {
                graveTypes = WITHERED_PLAYER_GRAVES_TYPES;
                material = EnumGraveMaterial.OTHER;
            } else {
                return null;
            }
        } else {
            return null;
        }

        if (graveTypes == null) {
            graveTypes = getDefaultGraveTypes(graveTypeByEntity);
        }

        return getGraveType(graveTypes, material);
    }

    public static boolean isFireDamage(DamageSource damageSource, String damageType) {
        return DamageSource.IN_FIRE.equals(damageSource) || DamageSource.ON_FIRE.equals(damageSource) || isFireDamage(damageType);
    }

    public static boolean isFireDamage(String damageType) {
        return damageType.toLowerCase().contains("nfire");
    }

    public static boolean isLavaDamage(DamageSource damageSource, String damageType) {
        return DamageSource.LAVA.equals(damageSource) || isLavaDamage(damageType);
    }

    public static boolean isLavaDamage(String damageType) {
        return damageType.toLowerCase().contains("lava");
    }

    public static boolean isMagicDamage(String damageText) {
        return damageText.toLowerCase().contains("magic");
    }

    public static boolean isExplosionDamage(DamageSource damageSource) {
        return isBlastDamage(damageSource.damageType) || isFireballDamage(damageSource.damageType);
    }

    public static boolean isBlastDamage(String damageType) {
        return damageType.toLowerCase().contains("explosion");
    }

    public static boolean isFireballDamage(String damageType) {
        return damageType.toLowerCase().contains("fireball");
    }

    public static EnumGraveMaterial[] getGraveMaterialByBiomes(World world, BlockPos pos) {
        Set<BiomeDictionary.Type> biomeTypesList = BiomeDictionary.getTypes(world.getBiome(pos));

        ArrayList<EnumGraveMaterial> materials = new ArrayList<>();
        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            materials.add(EnumGraveMaterial.SANDSTONE);
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            materials.add(EnumGraveMaterial.STONE);
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN)) {
            materials.add(EnumGraveMaterial.GRANITE);
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.HILLS)) {
            materials.add(EnumGraveMaterial.ANDESITE);
            materials.add(EnumGraveMaterial.DIORITE);
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            materials.add(EnumGraveMaterial.STONE);
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            materials.add(EnumGraveMaterial.WOOD);
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            materials.add(EnumGraveMaterial.ICE);
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            materials.add(EnumGraveMaterial.QUARTZ);
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MESA)) {
            materials.add(EnumGraveMaterial.RED_SANDSTONE);
        }
        // TODO if (biomeTypesList.contains(BiomeDictionary.Type.END)) {} ????????
        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
            materials.add(EnumGraveMaterial.PRIZMARINE);
        }

        if (materials.isEmpty()) {
            materials.add(EnumGraveMaterial.STONE);
        }

        EnumGraveMaterial[] materialsArray = new EnumGraveMaterial[materials.size()];
        return materials.toArray(materialsArray);
    }

    public static EnumGraves getGraveTypeByBiomes(World world, BlockPos pos, EnumGraveTypeByEntity graveTypeByEntity, DamageSource damageSource) {
        EnumGraveMaterial[] materialsArray = getGraveMaterialByBiomes(world, pos);

        EnumGraveType[] type;
        if (damageSource != null && isExplosionDamage(damageSource)) {
            type = GENERATED_CREEPER_STATUES_GRAVES_TYPES;
        } else {
            type = getDefaultGraveTypes(graveTypeByEntity);
        }
        return getGraveType(type, materialsArray);
    }

    private static BlockPos findPlaceForGrave(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int newY = getGround(world, x, y, z);

        if (canGenerateGraveAtCoordinates(world, new BlockPos(x, newY, z))) {
            return new BlockPos(x, newY, z);
        } else {
            int dx = 1;
            int dz = 1;

            while (Math.abs(dx) < 9 && Math.abs(dz) < 9) {
                if (dx < 0) {
                    for (int newX = x - 1; newX >= x + dx; newX--) {
                        newY = getGround(world, newX, y, z);
                        if (canGenerateGraveAtCoordinates(world, new BlockPos(newX, newY, z))) {
                            return new BlockPos(newX, newY, z);
                        }
                    }
                } else {
                    for (int newX = x + 1; newX <= x + dx; newX++) {
                        newY = getGround(world, newX, y, z);
                        if (canGenerateGraveAtCoordinates(world, new BlockPos(newX, newY, z))) {
                            return new BlockPos(newX, newY, z);
                        }
                    }
                }
                x += dx;

                if (dz < 0) {
                    for (int newZ = z - 1; newZ >= z + dz; newZ--) {
                        newY = getGround(world, x, y, newZ);
                        if (canGenerateGraveAtCoordinates(world, new BlockPos(x, newY, newZ))) {
                            return new BlockPos(x, newY, newZ);
                        }
                    }
                } else {
                    for (int newZ = z + 1; newZ <= z + dz; newZ++) {
                        newY = getGround(world, x, y, newZ);
                        if (canGenerateGraveAtCoordinates(world, new BlockPos(x, newY, newZ))) {
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

    private static int getGround(World world, int x, int y, int z) {
        while ((world.isAirBlock(new BlockPos(x, y - 1, z)) || world.getBlockState(new BlockPos(x, y - 1, z)).getBlock().getMaterial(null).isLiquid() ||
                world.getBlockState(new BlockPos(x, y - 1, z)).getBlock().getMaterial(null).isReplaceable()) && y > 1) {
            y--;
        }
        return y;
    }

    private static boolean canGenerateGraveAtCoordinates(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock().getMaterial(null).isSolid() &&
                (world.isAirBlock(pos) || world.getBlockState(pos).getBlock().getMaterial(null).isLiquid() || world.getBlockState(pos).getBlock().getMaterial(null).isReplaceable());
    }

    protected static EnumGraves getGraveType(EnumGraveType[] graveTypes, EnumGraveMaterial... materials) {
        EnumGraveType graveType = graveTypes[rand.nextInt(graveTypes.length)];
        EnumGraveMaterial material = materials[rand.nextInt(materials.length)];

        return EnumGraves.getByTypeAndMaterial(graveType, material);
    }

    private static ItemStack getSwordFromInventory(List<ItemStack> items) {
        if (items != null) {
            for (ItemStack stack : items) {
                if (stack != null && swordsList.contains(stack.getItem())) {
                    ItemStack sword = stack.copy();
                    items.remove(stack);
                    return sword;
                }
            }
        }

        return null;
    }
}
