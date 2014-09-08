package gravestone.block;

import gravestone.GraveStoneLogger;
import gravestone.block.enums.EnumGraves;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSBlock;
import gravestone.core.GSMobSpawn;
import gravestone.core.compatibility.*;
import gravestone.item.corpse.CorpseHelper;
import gravestone.item.enums.EnumCorpse;
import gravestone.tileentity.DeathMessageInfo;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneHelper {
    private static final List<Integer> GRAVESTONE_GROUND = Arrays.asList(
            Block.getIdFromBlock(Blocks.grass), Block.getIdFromBlock(Blocks.dirt),
            Block.getIdFromBlock(Blocks.sand), Block.getIdFromBlock(Blocks.gravel),
            Block.getIdFromBlock(Blocks.soul_sand), Block.getIdFromBlock(Blocks.mycelium),
            Block.getIdFromBlock(Blocks.snow));
    public static ArrayList<Item> swordsList = new ArrayList<Item>(
            Arrays.asList(
                    Items.wooden_sword,
                    Items.stone_sword,
                    Items.iron_sword,
                    Items.golden_sword,
                    Items.diamond_sword
            )
    );

    public static final Item[] GENERATED_SWORD_GRAVES = {
            Items.wooden_sword,
            Items.stone_sword
    };

    public static final EnumGraves[] GENERATED_WOODEN_GRAVES = {
            EnumGraves.WOODEN_VERTICAL_PLATE,
            EnumGraves.WOODEN_CROSS,
            EnumGraves.WOODEN_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_SANDSTONE_GRAVES = {
            EnumGraves.SANDSTONE_VERTICAL_PLATE,
            EnumGraves.SANDSTONE_CROSS,
            EnumGraves.SANDSTONE_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_STONE_GRAVES = {
            EnumGraves.STONE_VERTICAL_PLATE,
            EnumGraves.STONE_CROSS,
            EnumGraves.STONE_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_MOSSY_GRAVES = {
            EnumGraves.MOSSY_VERTICAL_PLATE,
            EnumGraves.MOSSY_CROSS,
            EnumGraves.MOSSY_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_IRON_GRAVES = {
            EnumGraves.IRON_VERTICAL_PLATE,
            EnumGraves.IRON_CROSS,
            EnumGraves.IRON_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_GOLDEN_GRAVES = {
            EnumGraves.GOLDEN_VERTICAL_PLATE,
            EnumGraves.GOLDEN_CROSS,
            EnumGraves.GOLDEN_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_DIAMOND_GRAVES = {
            EnumGraves.DIAMOND_VERTICAL_PLATE,
            EnumGraves.DIAMOND_CROSS,
            EnumGraves.DIAMOND_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_EMERALD_GRAVES = {
            EnumGraves.EMERALD_VERTICAL_PLATE,
            EnumGraves.EMERALD_CROSS,
            EnumGraves.EMERALD_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_LAPIS_GRAVES = {
            EnumGraves.LAPIS_VERTICAL_PLATE,
            EnumGraves.LAPIS_CROSS,
            EnumGraves.LAPIS_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_REDSTONE_GRAVES = {
            EnumGraves.REDSTONE_VERTICAL_PLATE,
            EnumGraves.REDSTONE_CROSS,
            EnumGraves.REDSTONE_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_OBSIDIAN_GRAVES = {
            EnumGraves.OBSIDIAN_VERTICAL_PLATE,
            EnumGraves.OBSIDIAN_CROSS,
            EnumGraves.OBSIDIAN_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_QUARTZ_GRAVES = {
            EnumGraves.QUARTZ_VERTICAL_PLATE,
            EnumGraves.QUARTZ_CROSS,
            EnumGraves.QUARTZ_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_ICE_GRAVES = {
            EnumGraves.ICE_VERTICAL_PLATE,
            EnumGraves.ICE_CROSS,
            EnumGraves.ICE_HORISONTAL_PLATE
    };
    public static final EnumGraves[] PETS_GRAVES = {
            EnumGraves.WOODEN_DOG_STATUE,
            EnumGraves.WOODEN_CAT_STATUE,
            EnumGraves.SANDSTONE_DOG_STATUE,
            EnumGraves.SANDSTONE_CAT_STATUE,
            EnumGraves.STONE_DOG_STATUE,
            EnumGraves.STONE_CAT_STATUE,
            EnumGraves.MOSSY_DOG_STATUE,
            EnumGraves.MOSSY_CAT_STATUE,
            EnumGraves.IRON_DOG_STATUE,
            EnumGraves.IRON_CAT_STATUE,
            EnumGraves.GOLDEN_DOG_STATUE,
            EnumGraves.GOLDEN_CAT_STATUE,
            EnumGraves.DIAMOND_DOG_STATUE,
            EnumGraves.DIAMOND_CAT_STATUE,
            EnumGraves.EMERALD_DOG_STATUE,
            EnumGraves.EMERALD_CAT_STATUE,
            EnumGraves.LAPIS_DOG_STATUE,
            EnumGraves.LAPIS_CAT_STATUE,
            EnumGraves.REDSTONE_DOG_STATUE,
            EnumGraves.REDSTONE_CAT_STATUE,
            EnumGraves.OBSIDIAN_DOG_STATUE,
            EnumGraves.OBSIDIAN_CAT_STATUE,
            EnumGraves.QUARTZ_DOG_STATUE,
            EnumGraves.QUARTZ_CAT_STATUE,
            EnumGraves.ICE_DOG_STATUE,
            EnumGraves.ICE_CAT_STATUE
    };
    public static final byte[] DOGS_GRAVES = {
            (byte) EnumGraves.WOODEN_DOG_STATUE.ordinal(),
            (byte) EnumGraves.SANDSTONE_DOG_STATUE.ordinal(),
            (byte) EnumGraves.STONE_DOG_STATUE.ordinal(),
            (byte) EnumGraves.MOSSY_DOG_STATUE.ordinal(),
            (byte) EnumGraves.IRON_DOG_STATUE.ordinal(),
            (byte) EnumGraves.GOLDEN_DOG_STATUE.ordinal(),
            (byte) EnumGraves.DIAMOND_DOG_STATUE.ordinal(),
            (byte) EnumGraves.EMERALD_DOG_STATUE.ordinal(),
            (byte) EnumGraves.LAPIS_DOG_STATUE.ordinal(),
            (byte) EnumGraves.REDSTONE_DOG_STATUE.ordinal(),
            (byte) EnumGraves.OBSIDIAN_DOG_STATUE.ordinal(),
            (byte) EnumGraves.QUARTZ_DOG_STATUE.ordinal(),
            (byte) EnumGraves.ICE_DOG_STATUE.ordinal()
    };
    public static final EnumGraves[] CATS_GRAVES = {
            EnumGraves.WOODEN_CAT_STATUE,
            EnumGraves.SANDSTONE_CAT_STATUE,
            EnumGraves.STONE_CAT_STATUE,
            EnumGraves.MOSSY_CAT_STATUE,
            EnumGraves.IRON_CAT_STATUE,
            EnumGraves.GOLDEN_CAT_STATUE,
            EnumGraves.DIAMOND_CAT_STATUE,
            EnumGraves.EMERALD_CAT_STATUE,
            EnumGraves.LAPIS_CAT_STATUE,
            EnumGraves.REDSTONE_CAT_STATUE,
            EnumGraves.OBSIDIAN_CAT_STATUE,
            EnumGraves.QUARTZ_CAT_STATUE,
            EnumGraves.ICE_CAT_STATUE
    };
    public static final EnumGraves[] DOG_WOODEN_GRAVES = {EnumGraves.WOODEN_DOG_STATUE};
    public static final EnumGraves[] DOG_SANDSTONE_GRAVES = {EnumGraves.SANDSTONE_DOG_STATUE};
    public static final EnumGraves[] DOG_STONE_GRAVES = {EnumGraves.STONE_DOG_STATUE};
    public static final EnumGraves[] DOG_MOSSY_GRAVES = {EnumGraves.MOSSY_DOG_STATUE};
    public static final EnumGraves[] DOG_GOLDEN_GRAVES = {EnumGraves.GOLDEN_DOG_STATUE};
    public static final EnumGraves[] DOG_DIAMOND_GRAVES = {EnumGraves.DIAMOND_DOG_STATUE};
    public static final EnumGraves[] DOG_OBSIDIAN_GRAVES = {EnumGraves.OBSIDIAN_DOG_STATUE};
    public static final EnumGraves[] DOG_QUARTZ_GRAVES = {EnumGraves.QUARTZ_DOG_STATUE};
    public static final EnumGraves[] DOG_ICE_GRAVES = {EnumGraves.ICE_DOG_STATUE};

    public static final EnumGraves[] CAT_WOODEN_GRAVES = {EnumGraves.WOODEN_CAT_STATUE};
    public static final EnumGraves[] CAT_SANDSTONE_GRAVES = {EnumGraves.SANDSTONE_CAT_STATUE};
    public static final EnumGraves[] CAT_STONE_GRAVES = {EnumGraves.STONE_CAT_STATUE};
    public static final EnumGraves[] CAT_MOSSY_GRAVES = {EnumGraves.MOSSY_CAT_STATUE};
    public static final EnumGraves[] CAT_GOLDEN_GRAVES = {EnumGraves.GOLDEN_CAT_STATUE};
    public static final EnumGraves[] CAT_DIAMOND_GRAVES = {EnumGraves.DIAMOND_CAT_STATUE};
    public static final EnumGraves[] CAT_OBSIDIAN_GRAVES = {EnumGraves.OBSIDIAN_CAT_STATUE};
    public static final EnumGraves[] CAT_QUARTZ_GRAVES = {EnumGraves.QUARTZ_CAT_STATUE};
    public static final EnumGraves[] CAT_ICE_GRAVES = {EnumGraves.ICE_CAT_STATUE};

    public static final EnumGraves[] HORSE_WOODEN_GRAVES = {EnumGraves.WOODEN_HORSE_STATUE};
    public static final EnumGraves[] HORSE_SANDSTONE_GRAVES = {EnumGraves.SANDSTONE_HORSE_STATUE};
    public static final EnumGraves[] HORSE_STONE_GRAVES = {EnumGraves.STONE_HORSE_STATUE};
    public static final EnumGraves[] HORSE_MOSSY_GRAVES = {EnumGraves.MOSSY_HORSE_STATUE};
    public static final EnumGraves[] HORSE_OBSIDIAN_GRAVES = {EnumGraves.OBSIDIAN_HORSE_STATUE};
    public static final EnumGraves[] HORSE_QUARTZ_GRAVES = {EnumGraves.QUARTZ_HORSE_STATUE};
    public static final EnumGraves[] HORSE_ICE_GRAVES = {EnumGraves.ICE_HORSE_STATUE};


    public static final List<Block> FLOWERS_GROUND = Arrays.asList(
            Blocks.grass, Blocks.dirt);
    public static final List FLOWERS = Arrays.asList(
            Blocks.yellow_flower, Blocks.red_flower);
    public static final List<EnumGraves> FLOWER_GRAVES = Arrays.asList(
            EnumGraves.WOODEN_VERTICAL_PLATE,
            EnumGraves.WOODEN_CROSS,
            EnumGraves.SANDSTONE_VERTICAL_PLATE,
            EnumGraves.SANDSTONE_CROSS,
            EnumGraves.STONE_VERTICAL_PLATE,
            EnumGraves.STONE_CROSS,
            EnumGraves.MOSSY_VERTICAL_PLATE,
            EnumGraves.MOSSY_CROSS,
            EnumGraves.IRON_VERTICAL_PLATE,
            EnumGraves.IRON_CROSS,
            EnumGraves.GOLDEN_VERTICAL_PLATE,
            EnumGraves.GOLDEN_CROSS,
            EnumGraves.DIAMOND_VERTICAL_PLATE,
            EnumGraves.DIAMOND_CROSS,
            EnumGraves.EMERALD_VERTICAL_PLATE,
            EnumGraves.EMERALD_CROSS,
            EnumGraves.LAPIS_VERTICAL_PLATE,
            EnumGraves.LAPIS_CROSS,
            EnumGraves.REDSTONE_VERTICAL_PLATE,
            EnumGraves.REDSTONE_CROSS,
            EnumGraves.OBSIDIAN_VERTICAL_PLATE,
            EnumGraves.OBSIDIAN_CROSS,
            EnumGraves.QUARTZ_VERTICAL_PLATE,
            EnumGraves.QUARTZ_CROSS,
            EnumGraves.ICE_VERTICAL_PLATE,
            EnumGraves.ICE_CROSS
    );

    private GraveStoneHelper() {
    }

    /**
     * Check is there sword in your inventory
     */
    // TODO
    public static ItemStack oldCheckSword(List<ItemStack> items) {
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

    /**
     * Check is grave - sword grave
     *
     * @param graveType Grave type
     */
    // TODO
    public static boolean isSwordGrave(byte graveType) {
        return graveType == EnumGraves.SWORD.ordinal();
    }

    /**
     * Return random grave type
     *
     * @param random
     * @param graveType
     */
    public static byte getGraveType(World world, int x, int z, Random random, BlockGSGraveStone.EnumGraveType graveType) {
        ArrayList<EnumGraves> petsGravesList;
        switch (graveType) {
            case PLAYER_GRAVES:
                if (random.nextFloat() > 0.1) {
                    return getRandomGrave(getPlayerGraveTypes(world, x, z), random);
                } else {
                    return (byte) EnumGraves.SWORD.ordinal();
                }
            case PETS_GRAVES:
                petsGravesList = new ArrayList<EnumGraves>();
                petsGravesList.addAll(getDogGraveTypes(world, x, z));
                petsGravesList.addAll(getCatGraveTypes(world, x, z));
                return getRandomGrave(petsGravesList, random);
            case DOGS_GRAVES:
                return getRandomGrave(getDogGraveTypes(world, x, z), random);
            case CATS_GRAVES:
                return getRandomGrave(getCatGraveTypes(world, x, z), random);
            case ALL_GRAVES:
            default:
                if (random.nextFloat() > 0.2) {
                    if (random.nextFloat() > 0.1) {
                        return getRandomGrave(getPlayerGraveTypes(world, x, z), random);
                    } else {
                        return (byte) EnumGraves.SWORD.ordinal();
                    }
                } else {
                    petsGravesList = new ArrayList<EnumGraves>();
                    petsGravesList.addAll(getDogGraveTypes(world, x, z));
                    petsGravesList.addAll(getCatGraveTypes(world, x, z));
                    return getRandomGrave(petsGravesList, random);
                }
        }
    }

    /**
     * Return grave metadata by direction
     */
    public static int getMetaDirection(int direction) {
        switch (direction) {
            case 0: // S
                return 1;
            case 1: // W
                return 2;
            case 2: // N
                return 0;
            case 3: // E
                return 3;
            default:
                return 0;
        }
    }

    /**
     * Check is grave - pet grave
     *
     * @param graveType Grave type
     */
    public static boolean isPetGrave(byte graveType) {
        // TODO rework
        return Arrays.binarySearch(PETS_GRAVES, EnumGraves.getByID(graveType)) >= 0;
    }

    // TODO
    public static byte oldGraveTypeToSwordType(byte graveType) {
        return (byte) (graveType - 4);
    }

    /**
     * Check ground type and replace it on dirt if it grass or mycelium
     */
    public static void replaceGround(World world, int x, int y, int z) {
        Block botBlock = world.getBlock(x, y, z);

        if (botBlock.equals(Blocks.grass) || botBlock.equals(Blocks.mycelium)) {
            world.setBlock(x, y, z, Blocks.dirt);
        }
    }

    /**
     * Spawn mob
     */
    public static void spawnMob(World world, int x, int y, int z) {
        if (world.rand.nextInt(10) == 0) {
            TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(x, y, z);

            if (tileEntity != null) {
                Entity mob = GSMobSpawn.getMobEntity(world, tileEntity.getGraveType(), x, y, z);

                if (mob != null) {
                    GSMobSpawn.spawnMob(world, mob, x, y, z, false);
                }
            }
        }
    }

    /**
     * Check can be grave placed on this type of surface
     */
    public static boolean canPlaceBlockAt(Block block) {
        return GraveStoneConfig.canPlaceGravesEveryWhere || GRAVESTONE_GROUND.contains(Block.getIdFromBlock(block));
    }

    public static int getMetadataBasedOnRotation(int rotation) {
        if (rotation >= 315 || rotation < 45) {
            return 1;
        } else if (rotation >= 45 && rotation < 135) {
            return 2;
        } else if (rotation >= 135 && rotation < 225) {
            return 0;
        } else {
            return 3;
        }
    }

    public static void createPlayerGrave(EntityPlayer player, LivingDeathEvent event) {
        if (player.worldObj != null && !player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory") && GraveStoneConfig.graveItemsCount > 0) {
            List<ItemStack> items = new LinkedList<ItemStack>();

            items.addAll(Arrays.asList(player.inventory.mainInventory));
            items.addAll(Arrays.asList(player.inventory.armorInventory));

            GSCompatibilityBattlegear.addItems(items, player);
            GSCompatibilityTheCampingMod.addItems(items, player);
            GSCompatibilityBaubles.addItems(items, player);
            GSCompatibilityMariculture.addItems(items, player);
            GSCompatibilityTinkerConstruct.addItems(items, player);
            GSCompatibilityRpgInventory.addItems(items, player);
            //GSCompatibilityBackpacksMod.addItems(items, player);
            player.inventory.clearInventory(null, -1);

            GSCompatibilityisArsMagica.getSoulboundItemsBack(items, player);

            createGrave(player, event, items, player.getAge(), BlockGSGraveStone.EnumGraveType.PLAYER_GRAVES, false);
        } else {
            createGrave(player, event, null, player.getAge(), BlockGSGraveStone.EnumGraveType.PLAYER_GRAVES, false);
        }
    }

    public static void createGrave(Entity entity, LivingDeathEvent event, List<ItemStack> items, int age, BlockGSGraveStone.EnumGraveType entityType, boolean isVillager) {
        GSBlock.graveStone.createOnDeath(entity, entity.worldObj, (int) entity.posX, (int) entity.posY, (int) entity.posZ - 1,
                getDeathMessage((EntityLivingBase) entity, event.source.damageType, isVillager),
                MathHelper.floor_float(entity.rotationYaw), items, age, entityType, event.source);
    }

    public static void createPetGrave(Entity entity, LivingDeathEvent event) {
        EntityTameable pet = (EntityTameable) entity;

        if (pet.isTamed()) {
            if (pet instanceof EntityWolf) {
                createGrave(entity, event, CorpseHelper.getCorpse(entity, EnumCorpse.DOG), pet.getAge(), BlockGSGraveStone.EnumGraveType.DOGS_GRAVES, false);
            } else if (pet instanceof EntityOcelot) {
                createGrave(entity, event, CorpseHelper.getCorpse(entity, EnumCorpse.CAT), pet.getAge(), BlockGSGraveStone.EnumGraveType.CATS_GRAVES, false);
            }
        }
    }

    public static void createHorseGrave(EntityHorse horse, LivingDeathEvent event) {
        if (horse.isTame()) {
            List<ItemStack> items = new ArrayList<ItemStack>();
            items.addAll(CorpseHelper.getCorpse(horse, EnumCorpse.HORSE));

            createGrave(horse, event, items, horse.getAge(), BlockGSGraveStone.EnumGraveType.HORSE_GRAVES, false);
        }
    }

    private static DeathMessageInfo getDeathMessage(EntityLivingBase entity, String damageType, boolean isVillager) {
        EntityLivingBase killer = entity.func_94060_bK();
        String shortString = "death.attack." + damageType;
        String fullString = shortString + ".player";

        if (killer != null) {
            String killerName;
            if (killer instanceof EntityPlayer) {
                killerName = ((EntityPlayer) killer).getDisplayName();
                if (isVillager) {
                    GraveStoneLogger.logInfo("Villager was killed by " + killerName);
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
                return new DeathMessageInfo(entity.getCommandSenderName(), fullString, killerName);
            } else {
                return new DeathMessageInfo(entity.getCommandSenderName(), shortString, killerName);
            }
        } else {
            return new DeathMessageInfo(entity.getCommandSenderName(), shortString, null);
        }
    }

    public static void addSwordInfo(NBTTagCompound nbt, ItemStack sword) {
        NBTTagCompound swordNBT = new NBTTagCompound();
        sword.writeToNBT(swordNBT);
        nbt.setTag("Sword", swordNBT);
    }

    public static ItemStack getSwordAsGrave(Item grave, ItemStack sword) {
        ItemStack graveStoneStack = new ItemStack(grave, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte("GraveType", (byte) EnumGraves.SWORD.ordinal());
        GraveStoneHelper.addSwordInfo(nbt, sword);

        graveStoneStack.setTagCompound(nbt);
        return graveStoneStack;
    }

    public static Item getRandomSwordForGeneration(byte graveType, Random random) {
        if (graveType == EnumGraves.SWORD.ordinal()) {
            return GENERATED_SWORD_GRAVES[random.nextInt(GENERATED_SWORD_GRAVES.length)];
        } else {
            return null;
        }
    }

    public static void addSwordToSwordsList(Item sword) {
        swordsList.add(sword);
    }

    public static byte getRandomGrave(List<EnumGraves> graveTypes, Random rand) {
        if (graveTypes.size() > 0) {
            return (byte) graveTypes.get(rand.nextInt(graveTypes.size())).ordinal();
        } else {
            return 0;
        }
    }

    public static boolean canFlowerBePlaced(TileEntityGSGraveStone te) {
        return !te.isSwordGrave() && FLOWER_GRAVES.contains(te.getGraveType());
    }

    public static ArrayList<EnumGraves> getPlayerGraveTypes(World world, int x, int z) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (biomeTypesList.contains(BiomeDictionary.Type.DESERT) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            graveTypes.addAll(Arrays.asList(GENERATED_SANDSTONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            graveTypes.addAll(Arrays.asList(GENERATED_MOSSY_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            graveTypes.addAll(Arrays.asList(GENERATED_STONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            graveTypes.addAll(Arrays.asList(GENERATED_WOODEN_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FROZEN)) {
            graveTypes.addAll(Arrays.asList(GENERATED_ICE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            graveTypes.addAll(Arrays.asList(GENERATED_QUARTZ_GRAVES));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (graveTypes.isEmpty()) {
            graveTypes.addAll(Arrays.asList(GENERATED_STONE_GRAVES));
        }

        return graveTypes;
    }

    public static ArrayList<EnumGraves> getDogGraveTypes(World world, int x, int z) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (biomeTypesList.contains(BiomeDictionary.Type.DESERT) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            graveTypes.addAll(Arrays.asList(DOG_SANDSTONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            graveTypes.addAll(Arrays.asList(DOG_MOSSY_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            graveTypes.addAll(Arrays.asList(DOG_STONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            graveTypes.addAll(Arrays.asList(DOG_WOODEN_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FROZEN)) {
            graveTypes.addAll(Arrays.asList(DOG_ICE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            graveTypes.addAll(Arrays.asList(DOG_QUARTZ_GRAVES));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (graveTypes.isEmpty()) {
            graveTypes.addAll(Arrays.asList(DOG_STONE_GRAVES));
        }

        return graveTypes;
    }

    public static ArrayList<EnumGraves> getCatGraveTypes(World world, int x, int z) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (biomeTypesList.contains(BiomeDictionary.Type.DESERT) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            graveTypes.addAll(Arrays.asList(CAT_SANDSTONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            graveTypes.addAll(Arrays.asList(CAT_MOSSY_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            graveTypes.addAll(Arrays.asList(CAT_STONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            graveTypes.addAll(Arrays.asList(CAT_WOODEN_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FROZEN)) {
            graveTypes.addAll(Arrays.asList(CAT_ICE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            graveTypes.addAll(Arrays.asList(CAT_QUARTZ_GRAVES));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (graveTypes.isEmpty()) {
            graveTypes.addAll(Arrays.asList(CAT_STONE_GRAVES));
        }
        return graveTypes;
    }

    public static ArrayList<EnumGraves> getHorseGraveTypes(World world, int x, int z) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (biomeTypesList.contains(BiomeDictionary.Type.DESERT) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            graveTypes.addAll(Arrays.asList(HORSE_SANDSTONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            graveTypes.addAll(Arrays.asList(HORSE_MOSSY_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            graveTypes.addAll(Arrays.asList(HORSE_STONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            graveTypes.addAll(Arrays.asList(HORSE_WOODEN_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FROZEN)) {
            graveTypes.addAll(Arrays.asList(HORSE_ICE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            graveTypes.addAll(Arrays.asList(HORSE_QUARTZ_GRAVES));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (graveTypes.isEmpty()) {
            graveTypes.addAll(Arrays.asList(HORSE_STONE_GRAVES));
        }

        return graveTypes;
    }


    public static ArrayList<EnumGraves> getPlayerGraveForDeath(DamageSource damageSource, String damageType) {
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (isFireDamage(damageSource, damageType) || isLavaDamage(damageSource, damageType) || isBlastDamage(damageType) || isFireballDamage(damageType)) {
            graveTypes.addAll(Arrays.asList(GENERATED_OBSIDIAN_GRAVES));
        }
        return graveTypes;
    }

    public static ArrayList<EnumGraves> getDogGraveForDeath(DamageSource damageSource, String damageType) {
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (isFireDamage(damageSource, damageType) || isLavaDamage(damageSource, damageType) || isBlastDamage(damageType) || isFireballDamage(damageType)) {
            graveTypes.addAll(Arrays.asList(DOG_OBSIDIAN_GRAVES));
        }
        return graveTypes;
    }

    public static ArrayList<EnumGraves> getCatGraveForDeath(DamageSource damageSource, String damageType) {
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (isFireDamage(damageSource, damageType) || isLavaDamage(damageSource, damageType) || isBlastDamage(damageType) || isFireballDamage(damageType)) {
            graveTypes.addAll(Arrays.asList(CAT_OBSIDIAN_GRAVES));
        }
        return graveTypes;
    }

    public static ArrayList<EnumGraves> getHorseGraveForDeath(DamageSource damageSource, String damageType) {
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (isFireDamage(damageSource, damageType) || isLavaDamage(damageSource, damageType) || isBlastDamage(damageType) || isFireballDamage(damageType)) {
            graveTypes.addAll(Arrays.asList(HORSE_OBSIDIAN_GRAVES));
        }
        return graveTypes;
    }

    public static ArrayList<EnumGraves> getPlayerGraveForLevel(Entity entity) {
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (entity instanceof EntityPlayer) {
            int level = ((EntityPlayer) entity).experienceLevel;
            if (level >= 65) {
                graveTypes.addAll(Arrays.asList(GENERATED_EMERALD_GRAVES));
            } else if (level >= 55) {
                graveTypes.addAll(Arrays.asList(GENERATED_DIAMOND_GRAVES));
            } else if (level >= 45) {
                graveTypes.addAll(Arrays.asList(GENERATED_REDSTONE_GRAVES));
            } else if (level >= 35) {
                graveTypes.addAll(Arrays.asList(GENERATED_GOLDEN_GRAVES));
            } else if (level >= 25) {
                graveTypes.addAll(Arrays.asList(GENERATED_LAPIS_GRAVES));
            } else if (level >= 15) {
                graveTypes.addAll(Arrays.asList(GENERATED_IRON_GRAVES));
            }
        }

        return graveTypes;
    }

    public static boolean isFireDamage(DamageSource damageSource, String damageType) {
        return DamageSource.inFire.equals(damageSource) || DamageSource.onFire.equals(damageSource) ||
                damageType.toLowerCase().contains("nFire");
    }

    public static boolean isLavaDamage(DamageSource damageSource, String damageType) {
        return DamageSource.lava.equals(damageSource) || damageType.toLowerCase().contains("lava");
    }

    public static boolean isBlastDamage(String damageType) {
        return damageType.toLowerCase().contains("explosion");
    }

    public static boolean isFireballDamage(String damageType) {
        return damageType.toLowerCase().contains("fireball");
    }

    public static boolean isMagicDamage(DamageSource damageSource, String damageType) {
        return DamageSource.magic.equals(damageSource) || damageType.toLowerCase().contains("magic");
    }
}
