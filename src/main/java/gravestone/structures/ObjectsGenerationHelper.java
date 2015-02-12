package gravestone.structures;

import gravestone.block.BlockGSHauntedChest;
import gravestone.block.BlockGSSpawner;
import gravestone.block.enums.EnumHauntedChest;
import gravestone.core.GSBlock;
import gravestone.tileentity.TileEntityGSHauntedChest;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDispenser;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

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
public class ObjectsGenerationHelper {

    public static final int POISON_POTION_ID = 16420;
    public static final int HARM_POTION_ID = 16428;
    public static final int WEAKNESS_POTION_ID = 16424;
    private static final int[] POTIONS = {POISON_POTION_ID, HARM_POTION_ID, WEAKNESS_POTION_ID};
    private static final List<WeightedRandomChestContent> NETHER_CHEST_CONTENT = new ArrayList<WeightedRandomChestContent>(Arrays.asList(
            new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 5),
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 5),
            new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 15),
            new WeightedRandomChestContent(Items.golden_sword, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.golden_chestplate, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.flint_and_steel, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.nether_wart, 0, 3, 7, 5),
            new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10),
            new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 8),
            new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 3)));

    private ObjectsGenerationHelper() {
    }

    /**
     * Generate chest with random loot type or haunted chest
     */
    public static void generateChest(ComponentGraveStone component, World world, Random random, int xCoord, int yCoord, int zCoord, EnumFacing facing, boolean defaultChest, EnumChestTypes chestType) {
        if (chestType.equals(EnumChestTypes.ALL_CHESTS) && random.nextInt(7) == 0) {
            generateHauntedChest(component, world, random, xCoord, yCoord, zCoord, facing);
        } else {
            generateVanillaChest(component, world, random, xCoord, yCoord, zCoord, facing, defaultChest, chestType);
        }
    }

    /**
     * Generate chest with random loot type
     */
    public static void generateVanillaChest(ComponentGraveStone component, World world, Random random, int xCoord, int yCoord, int zCoord, EnumFacing facing, boolean defaultChest, EnumChestTypes chestType) {
        int y = component.getYWithOffset(yCoord);
        int x = component.getXWithOffset(xCoord, zCoord);
        int z = component.getZWithOffset(xCoord, zCoord);

        ChestGenHooks chest = getChest(random, chestType);
        List<WeightedRandomChestContent> items;
        int count;
        if (chest == null) { // временный костыль для "аццких" сундуков
            items = NETHER_CHEST_CONTENT;
            count = 2 + random.nextInt(4);
        } else {
            items = chest.getItems(random);
            count = chest.getCount(random);
        }

        if (defaultChest) {
            //func_180778_a -> generateStructureChestContents
            component.func_180778_a(world, component.getBoundingBox(), random, xCoord, yCoord, zCoord, items, count);
        } else {
            generateTrappedChestContents(component, world, random, xCoord, yCoord, zCoord, facing, items, count);
        }
    }

    public static void generateHauntedChest(ComponentGraveStone component, World world, Random random, int xCoord, int yCoord, int zCoord, EnumFacing facing) {
        int x = component.getXWithOffset(xCoord, zCoord);
        int y = component.getYWithOffset(yCoord);
        int z = component.getZWithOffset(xCoord, zCoord);

        BlockPos pos = new BlockPos(x, y, z);
        world.setBlockState(pos, GSBlock.hauntedChest.getDefaultState().withProperty(BlockGSHauntedChest.FACING, facing), 2);
        TileEntityGSHauntedChest te = (TileEntityGSHauntedChest) world.getTileEntity(pos);
        if (te != null) {
            te.setChestType(EnumHauntedChest.getById((byte) random.nextInt(EnumHauntedChest.values().length)));
        }
    }

    /**
     * Generate trapped chests with items.
     */
    public static void generateTrappedChestContents(ComponentGraveStone component, World world, Random random, int xCoord, int yCoord, int zCoord, EnumFacing facing, List<WeightedRandomChestContent> chestContent, int count) {
        int x = component.getXWithOffset(xCoord, zCoord);
        int y = component.getYWithOffset(yCoord);
        int z = component.getZWithOffset(xCoord, zCoord);

        BlockPos pos = new BlockPos(x, y, z);
        world.setBlockState(pos, Blocks.trapped_chest.getDefaultState().withProperty(BlockChest.FACING, facing), 2);
        TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(pos);

        if (tileentitychest != null) {
            WeightedRandomChestContent.generateChestContents(random, chestContent, tileentitychest, count);
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
     * @param world     World object
     * @param xCoord    X coord
     * @param yCoord    Y coord
     * @param zCoord    Z coord
     */
    public static void generateSpawner(ComponentGraveStone component, World world, Random random, int xCoord, int yCoord, int zCoord) {
        int y = component.getYWithOffset(yCoord);
        int x = component.getXWithOffset(xCoord, zCoord);
        int z = component.getZWithOffset(xCoord, zCoord);

        world.setBlockState(new BlockPos(x, y, z), GSBlock.spawner.getDefaultState().withProperty(BlockGSSpawner.VARIANT,
                BlockGSSpawner.MOB_SPAWNERS.get(random.nextInt(BlockGSSpawner.MOB_SPAWNERS.size()))), 2);
    }

    /**
     * Generate mob spawner
     *
     * @param component Component instatnce
     * @param world     World object
     * @param xCoord    X coord
     * @param yCoord    Y coord
     * @param zCoord    Z coord
     * @param mobNmae   Spawned mob name
     */
    public static void generateMinecraftSpawner(ComponentGraveStone component, World world, int xCoord, int yCoord, int zCoord, String mobNmae) {
        int y = component.getYWithOffset(yCoord);
        int x = component.getXWithOffset(xCoord, zCoord);
        int z = component.getZWithOffset(xCoord, zCoord);

        BlockPos pos = new BlockPos(x, y, z);
        world.setBlockState(pos, Blocks.mob_spawner.getDefaultState());
        TileEntityMobSpawner tileEntity = (TileEntityMobSpawner) world.getTileEntity(pos);

        if (tileEntity != null) {
            tileEntity.getSpawnerBaseLogic().setEntityName(mobNmae);
        }
    }

    /**
     * Used to generate dispenser contents for structures. ex: Jungle Temples.
     */
    public static void generateDispenser(World world, ComponentGraveStone component, Random random, int xCoord, int yCoord, int zCoord, EnumFacing direction) {
        int x = component.getXWithOffset(xCoord, zCoord);
        int y = component.getYWithOffset(yCoord);
        int z = component.getZWithOffset(xCoord, zCoord);

        BlockPos pos = new BlockPos(x, y, z);
        world.setBlockState(pos, Blocks.dispenser.getDefaultState().withProperty(BlockDispenser.FACING, direction));
        TileEntityDispenser dispenser = (TileEntityDispenser) world.getTileEntity(pos);
        if (dispenser != null) {
            generateDispenserContents(random, dispenser);
        }
    }

    /**
     * Generates dispenser contents.
     */
    public static void generateDispenserContents(Random random, TileEntityDispenser dispenserEntity) {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = new ItemStack(Items.potionitem, getRandomCount(random), POTIONS[random.nextInt(POTIONS.length)]);
            dispenserEntity.setInventorySlotContents(i, stack);
        }
    }

    /**
     * Return random potions
     */
    private static int getRandomCount(Random random) {
        return 5 + random.nextInt(5);
    }

    public enum EnumChestTypes {
        ALL_CHESTS,
        VALUABLE_CHESTS
    }
}
