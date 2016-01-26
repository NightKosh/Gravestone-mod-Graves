package gravestone.helper;

import gravestone.api.grave_items.*;
import gravestone.block.BlockGSGraveStone;
import gravestone.block.enums.EnumGraveMaterial;
import gravestone.block.enums.EnumGraves;
import gravestone.config.GSConfig;
import gravestone.core.GSBlock;
import gravestone.core.MobHandler;
import gravestone.core.compatibility.*;
import gravestone.core.logger.GSLogger;
import gravestone.helper.api.APIGraveGeneration;
import gravestone.inventory.GraveInventory;
import gravestone.tileentity.DeathMessageInfo;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveGenerationHelper {

    protected static final Random rand = new Random();

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

    protected static EnumGraveTypeByEntity getRandomGraveType(Random random) {
        if (random.nextInt(5) < 4) {
            return getRandomHumanGraveType(random);
        } else {
            return getRandomPetGraveType(random);
        }
    }

    protected static EnumGraveTypeByEntity getRandomHumanGraveType(Random random) {
        return random.nextBoolean() ? EnumGraveTypeByEntity.PLAYER_GRAVES : EnumGraveTypeByEntity.VILLAGERS_GRAVES;
    }

    protected static EnumGraveTypeByEntity getRandomPetGraveType(Random random) {
        switch (random.nextInt(3)) {
            case 0:
            default:
                return EnumGraveTypeByEntity.DOGS_GRAVES;
            case 1:
                return EnumGraveTypeByEntity.CATS_GRAVES;
            case 2:
                return EnumGraveTypeByEntity.HORSE_GRAVES;
        }
    }

    private static final EnumGraves.EnumGraveType[] GENERATED_PLAYER_GRAVES_TYPES = {
            EnumGraves.EnumGraveType.VERTICAL_PLATE,
            EnumGraves.EnumGraveType.CROSS,
            EnumGraves.EnumGraveType.OBELISK,
            EnumGraves.EnumGraveType.HORIZONTAL_PLATE
            //TODO celtic cross
    };
    private static final EnumGraves.EnumGraveType[] GENERATED_VILLAGERS_GRAVES_TYPES = {EnumGraves.EnumGraveType.OBELISK};//TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private static final EnumGraves.EnumGraveType[] GENERATED_DOGS_GRAVES_TYPES = {EnumGraves.EnumGraveType.DOG_STATUE};
    private static final EnumGraves.EnumGraveType[] GENERATED_CAT_GRAVES_TYPES = {EnumGraves.EnumGraveType.CAT_STATUE};
    private static final EnumGraves.EnumGraveType[] GENERATED_HORSE_GRAVES_TYPES = {EnumGraves.EnumGraveType.HORSE_STATUE};
    private static final EnumGraves.EnumGraveType[] GENERATED_CREEPER_STATUES_GRAVES_TYPES = {EnumGraves.EnumGraveType.OBELISK};//TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public static void createPlayerGrave(EntityPlayer player, LivingDeathEvent event, long spawnTime) {
        if (player.worldObj != null && !player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory") && GSConfig.graveItemsCount > 0 &&
                !isInRestrictedArea(player.getPosition())) {
            List<ItemStack> items = new ArrayList<>(40);

//            GSCompatibilityAntiqueAtlas.placeDeathMarkerAtDeath(player); //TODO !!!!!!!!!!!!

            items.addAll(Arrays.asList(player.inventory.mainInventory));
            items.addAll(Arrays.asList(player.inventory.armorInventory));

            GSCompatibilityTwilightForest.addSlotTags(items);
            GSCompatibilityBattlegear.addItems(items, player);

            if (!GSCompatibilityTwilightForest.handleCharmsOfKeeping(items, player)) {
                player.inventory.clear();
            }

            GSCompatibilityTheCampingMod.addItems(items, player);
            GSCompatibilityBaubles.addItems(items, player);
            GSCompatibilityMariculture.addItems(items, player);
            GSCompatibilityTinkerConstruct.addItems(items, player);
            GSCompatibilityRpgInventory.addItems(items, player);
            GSCompatibilityGalacticraft.addItems(items, player);
            GSCompatibilityBackpacksMod.addItems(items, player);

            for (IPlayerItems additionalItems : APIGraveGeneration.PLAYER_ITEMS) {
                items.addAll(additionalItems.addItems(player, event.source));
            }

            GSCompatibilityisArsMagica.getSoulboundItemsBack(items, player);
            GSCompatibilityEnderIO.getSoulboundItemsBack(items, player);

            //TODO is it really required??
            GSCompatibilityTwilightForest.removeSlotTags(items);

            createGrave(player, event, items, EnumGraveTypeByEntity.PLAYER_GRAVES, false, spawnTime);
        } else {
            createGrave(player, event, null, EnumGraveTypeByEntity.PLAYER_GRAVES, false, spawnTime);
        }
    }

    public static void createVillagerGrave(EntityVillager villager, LivingDeathEvent event) {
        List<ItemStack> items = new ArrayList<>(5);
        for (IVillagerItems additionalItems : APIGraveGeneration.VILLAGER_ITEMS) {
            items.addAll(additionalItems.addItems(villager, event.source));
        }
        //CorpseHelper.getCorpse(event.entity, EnumCorpse.VILLAGER) //TODO external module

        long spawnTime = MobHandler.getAndRemoveSpawnTime(event.entity);
        createGrave(villager, event, items, GraveGenerationHelper.EnumGraveTypeByEntity.VILLAGERS_GRAVES, true, spawnTime);
    }

    public static void createDogGrave(EntityWolf dog, LivingDeathEvent event) {
        if (dog.isTamed()) {
            long spawnTime = MobHandler.getAndRemoveSpawnTime(event.entity);
            createGrave(dog, event, getDogsItems(dog, event), EnumGraveTypeByEntity.DOGS_GRAVES, false, spawnTime);
        }
    }

    public static void createCatGrave(EntityOcelot cat, LivingDeathEvent event) {
        if (cat.isTamed()) {
            long spawnTime = MobHandler.getAndRemoveSpawnTime(event.entity);
            createGrave(cat, event, getCatsItems(cat, event), EnumGraveTypeByEntity.CATS_GRAVES, false, spawnTime);
        }
    }

    private static List<ItemStack> getDogsItems(EntityWolf dog, LivingDeathEvent event) {
        List<ItemStack> items = new ArrayList<>(5);
        for (IDogItems additionalItems : APIGraveGeneration.DOG_ITEMS) {
            items.addAll(additionalItems.addItems(dog, event.source));
        }
        return items;//CorpseHelper.getCorpse(entity, EnumCorpse.DOG); //TODO external module
    }

    private static List<ItemStack> getCatsItems(EntityOcelot cat, LivingDeathEvent event) {
        List<ItemStack> items = new ArrayList<>(5);
        for (ICatItems additionalItems : APIGraveGeneration.CAT_ITEMS) {
            items.addAll(additionalItems.addItems(cat, event.source));
        }
        return items;//CorpseHelper.getCorpse(entity, EnumCorpse.CAT); //TODO external module
    }

    public static void createHorseGrave(EntityHorse horse, LivingDeathEvent event) {
        if (horse.isTame()) {
            List<ItemStack> items = new ArrayList<>();
            items.addAll(getHorseItems(horse));

            for (IHorseItems additionalItems : APIGraveGeneration.HORSE_ITEMS) {
                items.addAll(additionalItems.addItems(horse, event.source));
            }
//            items.addAll(CorpseHelper.getCorpse(horse, EnumCorpse.HORSE));//TODO external module

            long spawnTime = MobHandler.getAndRemoveSpawnTime(event.entity);
            createGrave(horse, event, items, EnumGraveTypeByEntity.HORSE_GRAVES, false, spawnTime);
        }
    }

    private static List<ItemStack> getHorseItems(EntityHorse horse) {
        List<ItemStack> items = new ArrayList<>();

        NBTTagCompound nbt = new NBTTagCompound();
        horse.writeEntityToNBT(nbt);
        NBTTagList nbtItemsList = nbt.getTagList("Items", 10);

//        if (horse.isHorseSaddled()) {
//            items.add(new ItemStack(Items.saddle));
//            nbt.removeTag("SaddleItem");
//        }
//        if (nbt.hasKey("ArmorItem", 10)) {
//            items.add(ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("ArmorItem")));
//            nbt.setTag("ArmorItem", new ItemStack(Blocks.air).writeToNBT(new NBTTagCompound()));
//        }
        if (horse.isChested()) {
            for (int i = 0; i < nbtItemsList.tagCount(); i++) {
                items.add(ItemStack.loadItemStackFromNBT(nbtItemsList.getCompoundTagAt(i)));
            }

            items.add(new ItemStack(Blocks.chest));
        }

        // new chest inventory
        nbtItemsList = new NBTTagList();
        for (int slot = 2; slot < 17; slot++) {
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            nbtTagCompound.setByte("Slot", (byte) slot);
            new ItemStack(Blocks.air).writeToNBT(nbtTagCompound);
            nbtItemsList.appendTag(nbtTagCompound);
        }

        nbt.removeTag("Items");
        nbt.setTag("Items", nbtItemsList);
        horse.readEntityFromNBT(nbt);

        // must be invoked after "readEntityFromNBT" otherwise items in chest will not be cleared
        horse.setChested(false);

        return items;
    }

    public static void createGrave(Entity entity, LivingDeathEvent event, List<ItemStack> items, EnumGraveTypeByEntity graveTypeByEntity, boolean isVillager, long spawnTime) {
        if (isInRestrictedArea(entity.getPosition())) {
            GSLogger.logInfo("Can't generate " + entity.getName() + "'s grave in restricted area. " + entity.getPosition().toString());
            if (items != null) {
                for (ItemStack item : items) {
                    if (item != null) {
                        GraveInventory.dropItem(item, entity.worldObj, entity.getPosition());
                    }
                }
            }
        } else {
            int age = (int) (entity.worldObj.getWorldTime() - spawnTime) / 24000;
            createOnDeath(entity, entity.worldObj, new BlockPos(entity.posX, entity.posY, entity.posZ - 1),
                    getDeathMessage((EntityLivingBase) entity, event.source.damageType, isVillager),
                    items, age, graveTypeByEntity, event.source);
        }
    }

    private static void createOnDeath(Entity entity, World world, BlockPos pos, DeathMessageInfo deathInfo, List<ItemStack> items,
                                      int age, EnumGraveTypeByEntity graveTypeByEntity, DamageSource damageSource) {
        EnumFacing direction = EnumFacing.getHorizontal(MathHelper.floor_double((double) (entity.rotationYaw * 4 / 360F) + 0.5) & 3);

        EnumGraves grave;
        ItemStack sword = null;
        if (chooseGraveTypeByAgeOrLevel(entity, graveTypeByEntity)) {
            EnumGraveMaterial material = getGraveMaterialByAgeOrLevel(entity, age, graveTypeByEntity);
            EnumGraves.EnumGraveType[] type;
            if (isExplosionDamage(damageSource)) {
                type = GENERATED_CREEPER_STATUES_GRAVES_TYPES;
            } else {
                type = getDefaultGraveTypes(graveTypeByEntity);
            }

            grave = getGraveType(type, material);
        } else {
            grave = getGraveByDeath(damageSource, graveTypeByEntity);
            if (grave == null) {
                if (graveTypeByEntity == EnumGraveTypeByEntity.PLAYER_GRAVES && GSConfig.generateSwordGraves &&
                        world.rand.nextInt(4) == 0 && graveTypeByEntity.equals(EnumGraveTypeByEntity.PLAYER_GRAVES)) {
                    sword = getSwordFromInventory(items);
                    grave = EnumGraves.SWORD;
                } else {
                    grave = getGraveTypeByBiomes(world, pos, graveTypeByEntity, damageSource);
                }
            }
        }

        boolean enchanted = isMagicDamage(damageSource);
        boolean mossy = isMossyGrave(world, pos, grave);

        BlockPos newPos = findPlaceForGrave(world, pos);
        if (newPos != null) {
            world.setBlockState(newPos, GSBlock.graveStone.getDefaultState().withProperty(BlockGSGraveStone.FACING, direction), 2);
            TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(newPos);

            if (tileEntity != null) {
                if (sword != null) {
                    tileEntity.setSword(sword);
                }

                tileEntity.getDeathTextComponent().setLocalized();
                tileEntity.getDeathTextComponent().setName(deathInfo.getName());
                tileEntity.getDeathTextComponent().setDeathText(deathInfo.getDeathMessage());
                tileEntity.getDeathTextComponent().setKillerName(deathInfo.getKillerName());
                tileEntity.getInventory().setItems(items);
                tileEntity.setGraveType(grave.ordinal());
                tileEntity.setAge(age);
                tileEntity.setEnchanted(enchanted);
                tileEntity.setMossy(mossy);
                if (entity instanceof EntityPlayer) {
                    tileEntity.setOwner(entity.getUniqueID().toString());
                } else if (entity instanceof EntityTameable && ((EntityTameable) entity).isTamed()) {
                    tileEntity.setOwner(((EntityTameable) entity).getOwner().getUniqueID().toString());
                }
            }
            GSLogger.logInfoGrave("Create " + deathInfo.getName() + "'s grave at " + newPos.getX() + "x" + newPos.getY() + "x" + newPos.getZ());
        } else {
            ItemStack itemStack = GSBlock.graveStone.createStackedBlock(GSBlock.graveStone.getDefaultState());
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("Type", grave.ordinal());
            nbt.setBoolean("isLocalized", true);
            nbt.setString("name", deathInfo.getName());
            nbt.setString("DeathText", deathInfo.getDeathMessage());
            nbt.setString("KillerName", deathInfo.getKillerNameForTE());
            nbt.setBoolean("Enchanted", enchanted);
            nbt.setBoolean("Mossy", mossy);
            nbt.setInteger("Age", age);

            if (grave == EnumGraves.SWORD) {
                GraveStoneHelper.addSwordInfo(nbt, sword);
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
        EntityLivingBase killer = entity.func_94060_bK();
        String shortString = "death.attack." + damageType;
        String fullString = shortString + ".player";

        String entityName = entity.getName();
        if (entityName == null) {
            entityName = "entity." + EntityList.getEntityString(entity) + ".name";
        }

        if (killer != null) {
            String killerName;
            if (killer instanceof EntityPlayer) {
                killerName = "KILLER_NAME";//TODO ((EntityPlayer) killer).getDisplayName();
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
            if (StatCollector.canTranslate(fullString)) {
                return new DeathMessageInfo(entityName, fullString, killerName);
            } else {
                return new DeathMessageInfo(entityName, shortString, killerName);
            }
        } else {
            return new DeathMessageInfo(entityName, shortString, null);
        }
    }

    private static boolean isInRestrictedArea(BlockPos pos) {
        return GSConfig.restrictGraveGenerationInArea.stream().anyMatch((area) -> area.isInArea(pos));
    }

    private static boolean isMagicDamage(DamageSource damageSource) {
        return DamageSource.magic.equals(damageSource) || damageSource.damageType.toLowerCase().contains("magic");
    }

    protected static boolean isMossyGrave(World world, BlockPos pos, EnumGraves grave) {
        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<>(Arrays.asList(BiomeDictionary.getTypesForBiome(world.getBiomeGenForCoords(pos))));
        return grave.getMaterial() != EnumGraveMaterial.OTHER && (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP));
    }

    public static boolean chooseGraveTypeByAgeOrLevel(Entity entity, EnumGraveTypeByEntity graveTypeByEntity) {
        if (graveTypeByEntity == EnumGraveTypeByEntity.PLAYER_GRAVES) {
            return ((EntityPlayer) entity).experienceLevel >= 15;
        } else {
            return false;//TODO check pet age !!!!!!!!!!
        }
    }

    public static EnumGraveMaterial getGraveMaterialByAgeOrLevel(Entity entity, int age, EnumGraveTypeByEntity graveTypeByEntity) {
        if (graveTypeByEntity == EnumGraveTypeByEntity.PLAYER_GRAVES) {
            return getPlayerGraveMaterialByLevel((EntityPlayer) entity);
        } else {
            return getGraveMaterialByAge(entity, age, graveTypeByEntity);
        }
    }

    private static EnumGraveMaterial getPlayerGraveMaterialByLevel(EntityPlayer player) {
        int level = player.experienceLevel;
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

    private static EnumGraveMaterial getGraveMaterialByAge(Entity entity, int age, EnumGraveTypeByEntity graveTypeByEntity) {
        return null;//TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    protected static EnumGraves.EnumGraveType[] getDefaultGraveTypes(EnumGraveTypeByEntity graveTypeByEntity) {
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

    public static EnumGraves getGraveByDeath(DamageSource damageSource, EnumGraveTypeByEntity graveTypeByEntity) {
        EnumGraves.EnumGraveType[] graveTypes = null;
        EnumGraveMaterial material = null;

        if (isFireDamage(damageSource, damageSource.damageType) || isLavaDamage(damageSource, damageSource.damageType)) {
            material = EnumGraveMaterial.OBSIDIAN;
            graveTypes = getDefaultGraveTypes(graveTypeByEntity);
        } else if (graveTypeByEntity == EnumGraveTypeByEntity.PLAYER_GRAVES) {
            if (DamageSource.drown.equals(damageSource)) {
                //TODO drown
                material = EnumGraveMaterial.OTHER;
            } else if (DamageSource.starve.equals(damageSource)) {
                //TODO starve
                material = EnumGraveMaterial.OTHER;
            } else if (DamageSource.wither.equals(damageSource)) {
                //TODO wither
                material = EnumGraveMaterial.OTHER;
            }
        } else {
            return null;
        }

        return getGraveType(graveTypes, material);
    }

    private static boolean isFireDamage(DamageSource damageSource, String damageType) {
        return DamageSource.inFire.equals(damageSource) || DamageSource.onFire.equals(damageSource) ||
                damageType.toLowerCase().contains("nFire");
    }

    private static boolean isLavaDamage(DamageSource damageSource, String damageType) {
        return DamageSource.lava.equals(damageSource) || damageType.toLowerCase().contains("lava");
    }

    private static boolean isExplosionDamage(DamageSource damageSource) {
        return isBlastDamage(damageSource.damageType) || isFireballDamage(damageSource.damageType);
    }

    private static boolean isBlastDamage(String damageType) {
        return damageType.toLowerCase().contains("explosion");
    }

    private static boolean isFireballDamage(String damageType) {
        return damageType.toLowerCase().contains("fireball");
    }

    public static EnumGraves getGraveTypeByBiomes(World world, BlockPos pos, EnumGraveTypeByEntity graveTypeByEntity, DamageSource damageSource) {
        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<>(Arrays.asList(BiomeDictionary.getTypesForBiome(world.getBiomeGenForCoords(pos))));

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

        EnumGraves.EnumGraveType[] type;
        if (damageSource != null && isExplosionDamage(damageSource)) {
            type = GENERATED_CREEPER_STATUES_GRAVES_TYPES;
        } else {
            type = getDefaultGraveTypes(graveTypeByEntity);
        }
        EnumGraveMaterial[] materialsArray = new EnumGraveMaterial[materials.size()];
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
        while ((world.isAirBlock(new BlockPos(x, y - 1, z)) || world.getBlockState(new BlockPos(x, y - 1, z)).getBlock().getMaterial().isLiquid() ||
                world.getBlockState(new BlockPos(x, y - 1, z)).getBlock().getMaterial().isReplaceable()) && y > 1) {
            y--;
        }
        return y;
    }

    private static boolean canGenerateGraveAtCoordinates(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock().getMaterial().isSolid() &&
                (world.isAirBlock(pos) || world.getBlockState(pos).getBlock().getMaterial().isLiquid() || world.getBlockState(pos).getBlock().getMaterial().isReplaceable());
    }

    protected static EnumGraves getGraveType(EnumGraves.EnumGraveType[] graveTypes, EnumGraveMaterial... materials) {
        EnumGraves.EnumGraveType graveType = graveTypes[rand.nextInt(graveTypes.length)];
        EnumGraveMaterial material = materials[rand.nextInt(materials.length)];

        return EnumGraves.getByTypeAndMaterial(graveType, material);
    }

    private static ItemStack getSwordFromInventory(List<ItemStack> items) {
        if (items != null) {
            for (ItemStack stack : items) {
                if (stack != null && GraveStoneHelper.swordsList.contains(stack.getItem())) {
                    ItemStack sword = stack.copy();
                    items.remove(stack);
                    return sword;
                }
            }
        }

        return null;
    }
}
