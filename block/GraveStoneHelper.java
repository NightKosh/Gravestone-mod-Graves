
package GraveStone.block;

import GraveStone.GraveStoneConfig;
import GraveStone.GraveStoneMobSpawn;
import static GraveStone.block.BlockGSGraveStone.CAT_GRAVES;
import static GraveStone.block.BlockGSGraveStone.DOG_GRAVES;
import static GraveStone.block.BlockGSGraveStone.EnumGraveType.ALL_GRAVES;
import static GraveStone.block.BlockGSGraveStone.EnumGraveType.CATS_GRAVES;
import static GraveStone.block.BlockGSGraveStone.EnumGraveType.DOGS_GRAVES;
import static GraveStone.block.BlockGSGraveStone.EnumGraveType.PLAYER_GRAVES;
import static GraveStone.block.BlockGSGraveStone.GENERATED_GRAVES;
import static GraveStone.block.BlockGSGraveStone.GENERATED_SWORD_GRAVES;
import static GraveStone.block.BlockGSGraveStone.PETS_GRAVES;
import static GraveStone.block.BlockGSGraveStone.SWORD_GRAVES;
import GraveStone.tileentity.TileEntityGSGraveStone;
import java.util.Arrays;
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
    
    /**
     * Check is there sword in your inventory
     */
    public static ItemStack checkSword(ItemStack[] items) {
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null && (items[i].itemID == Item.swordDiamond.itemID || items[i].itemID == Item.swordGold.itemID
                        || items[i].itemID == Item.swordIron.itemID || items[i].itemID == Item.swordStone.itemID
                        || items[i].itemID == Item.swordWood.itemID)) {
                    ItemStack sword = items[i];
                    items[i] = null;
                    return sword;
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
        return Arrays.binarySearch(SWORD_GRAVES, graveType) != -1;
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
                    return GENERATED_GRAVES[random.nextInt(GENERATED_GRAVES.length)];
                } else {
                    return GENERATED_SWORD_GRAVES[random.nextInt(GENERATED_SWORD_GRAVES.length)];
                }
            case PETS_GRAVES:
                return PETS_GRAVES[random.nextInt(PETS_GRAVES.length)];
            case DOGS_GRAVES:
                return DOG_GRAVES[random.nextInt(DOG_GRAVES.length)];
            case CATS_GRAVES:
                return CAT_GRAVES[random.nextInt(CAT_GRAVES.length)];
            case ALL_GRAVES:
            default:
                if (random.nextFloat() > 0.2) {
                    if (random.nextFloat() > 0.1) {
                        return GENERATED_GRAVES[random.nextInt(GENERATED_GRAVES.length)];
                    } else {
                        return GENERATED_SWORD_GRAVES[random.nextInt(GENERATED_SWORD_GRAVES.length)];
                    }
                } else {
                    return PETS_GRAVES[random.nextInt(PETS_GRAVES.length)];
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
        return Arrays.binarySearch(PETS_GRAVES, graveType) != -1;
    }

    public static byte graveTypeToSwordType(byte graveType) {
        return (byte) (graveType - 4);
    }

    public static byte swordGraveTypeToGraveType(byte swordGraveType) {
        return (byte) (swordGraveType + 4);
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
        if (GraveStoneMobSpawn.checkChance(world.rand)) {
            TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(x, y, z);

            if (tileEntity != null) {
                Entity mob = GraveStoneMobSpawn.getMobEntity(world, tileEntity.getGraveType(), x, y, z);

                if (mob != null) {
                    GraveStoneMobSpawn.spawnMob(world, mob, x, y, z, false);
                }
            }
        }
    }
    
    /**
     * Check can be grave placed on this type of surface
     */
    public static boolean canPlaceBlockAt(int blockId) {
        if (GraveStoneConfig.canPlaceGravesEveryWhere) {
            return true;
        } else if (blockId == Block.grass.blockID || blockId == Block.dirt.blockID
                || blockId == Block.sand.blockID || blockId == Block.gravel.blockID
                || blockId == Block.slowSand.blockID || blockId == Block.mycelium.blockID
                || blockId == Block.blockSnow.blockID) {
            return true;
        } else {
            return false;
        }
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
