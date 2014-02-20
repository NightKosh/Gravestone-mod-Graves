package gravestone.block;

import gravestone.config.GraveStoneConfig;
import gravestone.core.GSMobSpawn;
import gravestone.tileentity.TileEntityGSGraveStone;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneHelper {

    private GraveStoneHelper() {
    }
    private static final List<Integer> swordsList = Arrays.asList(
            Item.swordDiamond.itemID, Item.swordGold.itemID, Item.swordIron.itemID,
            Item.swordStone.itemID, Item.swordWood.itemID);
    private static final List<Integer> gravestoneGround = Arrays.asList(
            Block.grass.blockID, Block.dirt.blockID, Block.sand.blockID, Block.gravel.blockID,
            Block.slowSand.blockID, Block.mycelium.blockID, Block.blockSnow.blockID);

    /**
     * Check is there sword in your inventory
     */
    public static ItemStack checkSword(List<ItemStack> items) {
        if (items != null) {
            Iterator<ItemStack> it = items.iterator();
            while (it.hasNext()) {
                ItemStack item = it.next().copy();
                if (item != null && swordsList.contains(item.itemID)) {
                    it.remove();
                    return item;
                }
            }
        }

        return null;
    }

    /**
     * Check is grave - sword grave
     *
     * @param tileEntity Grave tile entity
     */
    public static boolean isSwordGrave(TileEntityGSGraveStone tileEntity) {
        return tileEntity.getSword() != 0;
    }

    /**
     * Check is grave - sword grave
     *
     * @param graveType Grave type
     */
    public static boolean isSwordGrave(byte graveType) {
        return Arrays.binarySearch(BlockGSGraveStone.SWORD_GRAVES, graveType) != -1;
    }

    /**
     * Return random grave type
     *
     * @param random
     * @param graveType
     */
    public static byte getGraveType(Random random, BlockGSGraveStone.EnumGraveType graveType) {
        switch (graveType) {
            case PLAYER_GRAVES:
                if (random.nextFloat() > 0.1) {
                    return BlockGSGraveStone.GENERATED_GRAVES[random.nextInt(BlockGSGraveStone.GENERATED_GRAVES.length)];
                } else {
                    return BlockGSGraveStone.GENERATED_SWORD_GRAVES[random.nextInt(BlockGSGraveStone.GENERATED_SWORD_GRAVES.length)];
                }
            case PETS_GRAVES:
                return BlockGSGraveStone.PETS_GRAVES[random.nextInt(BlockGSGraveStone.PETS_GRAVES.length)];
            case DOGS_GRAVES:
                return BlockGSGraveStone.DOG_GRAVES[random.nextInt(BlockGSGraveStone.DOG_GRAVES.length)];
            case CATS_GRAVES:
                return BlockGSGraveStone.CAT_GRAVES[random.nextInt(BlockGSGraveStone.CAT_GRAVES.length)];
            case ALL_GRAVES:
            default:
                if (random.nextFloat() > 0.2) {
                    if (random.nextFloat() > 0.1) {
                        return BlockGSGraveStone.GENERATED_GRAVES[random.nextInt(BlockGSGraveStone.GENERATED_GRAVES.length)];
                    } else {
                        return BlockGSGraveStone.GENERATED_SWORD_GRAVES[random.nextInt(BlockGSGraveStone.GENERATED_SWORD_GRAVES.length)];
                    }
                } else {
                    return BlockGSGraveStone.PETS_GRAVES[random.nextInt(BlockGSGraveStone.PETS_GRAVES.length)];
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
        return Arrays.binarySearch(BlockGSGraveStone.PETS_GRAVES, graveType) >= 0;
    }

    public static byte graveTypeToSwordType(byte graveType) {
        return (byte) (graveType - 4);
    }

    public static byte swordGraveTypeToGraveType(byte swordGraveType) {
        return (byte) (swordGraveType + 4);
    }

    public static byte getSwordType(int itemId) {
        if (itemId == Item.swordDiamond.itemID) {
            return 5;
        } else if (itemId == Item.swordIron.itemID) {
            return 3;
        } else if (itemId == Item.swordStone.itemID) {
            return 2;
        } else if (itemId == Item.swordGold.itemID) {
            return 4;
        } else {
            return 1;
        }
    }

    /**
     * Check ground type and replace it on dirt if it grass or mycelium
     */
    public static void replaceGround(World world, int x, int y, int z) {
        int botBlockID = world.getBlockId(x, y, z);

        if (botBlockID == 2 || botBlockID == 110) {
            world.setBlock(x, y, z, Block.dirt.blockID);
        }
    }

    /**
     * Spawn mob
     */
    public static void spawnMob(World world, int x, int y, int z) {
        if (world.rand.nextInt(10) == 0) {
            TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(x, y, z);

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
    public static boolean canPlaceBlockAt(int blockId) {
        return GraveStoneConfig.canPlaceGravesEveryWhere || gravestoneGround.contains(blockId);
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
}
