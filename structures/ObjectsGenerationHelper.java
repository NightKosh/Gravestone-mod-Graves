package gravestone.structures;

import gravestone.block.enums.EnumHauntedChest;
import gravestone.core.GSBlock;
import gravestone.tileentity.TileEntityGSHauntedChest;
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
    private static final WeightedRandomChestContent[] NETHER_CHEST_CONTENT = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Item.diamond.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.ingotIron.itemID, 0, 1, 5, 5), new WeightedRandomChestContent(Item.ingotGold.itemID, 0, 1, 3, 15), new WeightedRandomChestContent(Item.swordGold.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.plateGold.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.flintAndSteel.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.netherStalkSeeds.itemID, 0, 3, 7, 5), new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.horseArmorGold.itemID, 0, 1, 1, 8), new WeightedRandomChestContent(Item.horseArmorIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.horseArmorDiamond.itemID, 0, 1, 1, 3)};

    public enum EnumChestTypes {

        ALL_CHESTS,
        VALUABLE_CHESTS
    }

    /**
     * Generate chest with random loot type or haunted chest
     */
    public static void generateChest(ComponentGraveStone component, World world, Random random, int xCoord, int yCoord, int zCoord, boolean defaultChest, EnumChestTypes chestType) {
        if (chestType.equals(EnumChestTypes.ALL_CHESTS) && random.nextInt(7) == 0) {
            generateHauntedChest(component, world, random, xCoord, yCoord, zCoord);
        } else {
            generateVanillaChest(component, world, random, xCoord, yCoord, zCoord, defaultChest, chestType);
        }
    }

    /**
     * Generate chest with random loot type 
     */
    public static void generateVanillaChest(ComponentGraveStone component, World world, Random random, int xCoord, int yCoord, int zCoord, boolean defaultChest, EnumChestTypes chestType) {
        int y = component.getYWithOffset(yCoord);
        int x = component.getXWithOffset(xCoord, zCoord);
        int z = component.getZWithOffset(xCoord, zCoord);

        if (component.getBoundingBox().isVecInside(x, y, z)) {
            ChestGenHooks chest = getChest(random, chestType);
            WeightedRandomChestContent[] items;
            int count;
            if (chest == null) { // временный костыль для "аццких" сундуков 
                items = NETHER_CHEST_CONTENT;
                count = 2 + random.nextInt(4);
            } else {
                items = chest.getItems(random);
                count = chest.getCount(random);
            }

            if (defaultChest) {
                component.generateStructureChestContents(world, component.getBoundingBox(), random, xCoord, yCoord, zCoord, items, count);
            } else {
                generateTrappedChestContents(component, world, random, xCoord, yCoord, zCoord, items, count);
            }
        }
    }

    public static void generateHauntedChest(ComponentGraveStone component, World world, Random random, int xCoord, int yCoord, int zCoord) {
        int x = component.getXWithOffset(xCoord, zCoord);
        int y = component.getYWithOffset(yCoord);
        int z = component.getZWithOffset(xCoord, zCoord);

        if (component.getBoundingBox().isVecInside(x, y, z) && world.getBlockId(x, y, z) != GSBlock.hauntedChest.blockID) {
            world.setBlock(x, y, z, GSBlock.hauntedChest.blockID, 0, 2);
            TileEntityGSHauntedChest te = (TileEntityGSHauntedChest) world.getBlockTileEntity(x, y, z);

            if (te != null) {
                te.setChestType(EnumHauntedChest.getById((byte) random.nextInt(EnumHauntedChest.values().length)));
            }
        }
    }

    /**
     * Generate trapped chests with items.
     */
    public static void generateTrappedChestContents(ComponentGraveStone component, World world, Random random, int xCoord, int yCoord, int zCoord, WeightedRandomChestContent[] chestContent, int count) {
        int x = component.getXWithOffset(xCoord, zCoord);
        int y = component.getYWithOffset(yCoord);
        int z = component.getZWithOffset(xCoord, zCoord);

        if (component.getBoundingBox().isVecInside(x, y, z) && world.getBlockId(x, y, z) != Block.chestTrapped.blockID) {
            world.setBlock(x, y, z, Block.chestTrapped.blockID, 0, 2);
            TileEntityChest tileentitychest = (TileEntityChest) world.getBlockTileEntity(x, y, z);

            if (tileentitychest != null) {
                WeightedRandomChestContent.generateChestContents(random, chestContent, tileentitychest, count);
            }
        }
    }

    private static ChestGenHooks getChest(Random random, EnumChestTypes chestType) {
        switch (chestType) {
            case VALUABLE_CHESTS:
                switch (random.nextInt(7)) {
                    case 1:
                        return ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
                    case 2:
                        return ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
                    case 3:
                        return ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
                    case 4:
                        return ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING);
                    case 5:
                        return ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY);
                    case 6:
                        return null;
                    case 0:
                    default:
                        return ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR);
                }
            case ALL_CHESTS:
            default:
                switch (random.nextInt(9)) {
                    case 1:
                        return ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
                    case 2:
                        return ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
                    case 3:
                        return ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
                    case 4:
                        return ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
                    case 5:
                        return ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING);
                    case 6:
                        return ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY);
                    case 7:
                        return ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);
                    case 8:
                        return null;
                    case 0:
                    default:
                        return ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR);
                }
        }
    }

    /**
     * Generate mob spawner
     *
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
     *
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
