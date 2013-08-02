
package GraveStone.structures;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ObjectsGenerationHelper {
    
    private ObjectsGenerationHelper() {
        
    }
    
    private static final int[] POTIONS = {32764, 32692};
    
    /**
     * Generate chest with random loot type
     * @param component Component instance
     * @param world World object
     * @param random
     * @param xCoord X coord
     * @param yCoord Y coord
     * @param zCoord Z coord
     * @param defaultChest Is chest - default chest or trapped chest
     */
    public static void generateChest(ComponentGraveStone component, World world, Random random, int xCoord, int yCoord, int zCoord, boolean defaultChest) {
        int y = component.getYWithOffset(yCoord);
        int x = component.getXWithOffset(xCoord, zCoord);
        int z = component.getZWithOffset(xCoord, zCoord);

        if (component.getBoundingBox().isVecInside(x, y, z)) {
            ChestGenHooks chest;
            switch (random.nextInt(8)) {
                case 1:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
                    break;
                case 2:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
                    break;
                case 3:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
                    break;
                case 4:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
                    break;
                case 5:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING);
                    break;
                case 6:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY);
                    break;
                case 7:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);
                    break;
                default:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR);
            }
            if (defaultChest) {
                component.generateStructureChestContents(world, component.getBoundingBox(), random, xCoord, yCoord, zCoord, chest.getItems(random), chest.getCount(random));
            } else {
                generateTrappedChestContents(component, world, random, xCoord, yCoord, zCoord, chest.getItems(random), chest.getCount(random));
            }
        }
    }

    /**
     * Generate trapped chests with items.
     * @param component Component instatnce
     * @param world World object
     * @param random
     * @param xCoord X coord
     * @param yCoord Y coord
     * @param zCoord Z coord
     * @param chestContent Chest content
     * @param par8
     */
    public static boolean generateTrappedChestContents(ComponentGraveStone component, World world, Random random, int xCoord, int yCoord, int zCoord, WeightedRandomChestContent[] chestContent, int par8) {
        int x = component.getXWithOffset(xCoord, zCoord);
        int y = component.getYWithOffset(yCoord);
        int z = component.getZWithOffset(xCoord, zCoord);

        if (component.getBoundingBox().isVecInside(x, y, z) && world.getBlockId(x, y, z) != Block.chestTrapped.blockID) {
            world.setBlock(x, y, z, Block.chestTrapped.blockID, 0, 2);
            TileEntityChest tileentitychest = (TileEntityChest) world.getBlockTileEntity(x, y, z);

            if (tileentitychest != null) {
                WeightedRandomChestContent.generateChestContents(random, chestContent, tileentitychest, par8);
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Generate mob spawner
     * @param component Component instatnce
     * @param world World object
     * @param xCoord X coord
     * @param yCoord Y coord
     * @param zCoord Z coord
     * @param spawnerType Spawned mob name
     */
    public static void generateSpawner(ComponentGraveStone component, World world, int xCoord, int yCoord, int zCoord, String spawnerType) {
        int y = component.getYWithOffset(yCoord);
        int x = component.getXWithOffset(xCoord, zCoord);
        int z = component.getZWithOffset(xCoord, zCoord);

        if (component.getBoundingBox().isVecInside(x, y, z)) {
            world.setBlock(x, y, z, Block.mobSpawner.blockID);
            TileEntityMobSpawner tileEntity = (TileEntityMobSpawner) world.getBlockTileEntity(x, y, z);

            if (tileEntity != null) {
                tileEntity.getSpawnerLogic().setMobID(spawnerType);
            }
        }
    }
    
    /**
     * Generates dispenser contents.
     */
    public static void generateDispenserContents(Random random, TileEntityDispenser dispenserEntity) {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = new ItemStack(Item.potion.itemID, getRandomCount(random), POTIONS[random.nextInt(POTIONS.length)]);
            dispenserEntity.setInventorySlotContents(i, stack);
        }
    }

    /**
     * Return random potions 
     */
    private static int getRandomCount(Random random) {
        return 10 + random.nextInt(64);
    }

    /**
     * Set dispenser metadata
     * @param world World object
     * @param x X coorf
     * @param y Y coord
     * @param z Z coord
     * @param direction Direction 
     */
    public static void setDispenserMeta(World world, int x, int y, int z, int direction) {
        switch (direction) {
            case 0:
                world.setBlockMetadataWithNotify(x, y, z, 2, 2);
                break;
            case 1:
                world.setBlockMetadataWithNotify(x, y, z, 5, 2);
                break;
            case 2:
                world.setBlockMetadataWithNotify(x, y, z, 3, 2);
                break;
            case 3:
                world.setBlockMetadataWithNotify(x, y, z, 4, 2);
                break;
        }
    }
}
