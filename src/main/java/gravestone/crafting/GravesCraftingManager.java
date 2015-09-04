package gravestone.crafting;

import gravestone.block.enums.EnumGraveMaterial;
import gravestone.block.enums.EnumGraves;
import gravestone.core.GSBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GravesCraftingManager {

    private static final IBlockState DEFAULT_STONE_STATE = Blocks.stone.getDefaultState();
    private static final int DIORITE_META = Blocks.stone.getMetaFromState(DEFAULT_STONE_STATE.withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE_SMOOTH));
    private static final int ANDESITE_META = Blocks.stone.getMetaFromState(DEFAULT_STONE_STATE.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
    private static final int GRANITE_META = Blocks.stone.getMetaFromState(DEFAULT_STONE_STATE.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH));

    private static final GravesCraftingManager instance = new GravesCraftingManager();
    private List<GravestoneRecipe> recipes;

    private GravesCraftingManager() {
        recipes = new ArrayList<GravestoneRecipe>();//TODO set recipes count
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.WOOD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.WOODEN_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.SANDSTONE_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.RED_SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.RED_SANDSTONE_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.STONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.STONE_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.DIORITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, DIORITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIORITE_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, ANDESITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ANDESITE_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, GRANITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.GRANITE_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.IRON,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.IRON_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.GOLD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.GOLDEN_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.DIAMOND,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIAMOND_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.EMERALD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.EMERALD_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.LAPIS,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.LAPIS_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.REDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.REDSTONE_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.OBSIDIAN,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.OBSIDIAN_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.QUARTZ,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.QUARTZ_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.PRIZMARINE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.PRIZMARINE_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.ICE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ICE_VERTICAL_PLATE.ordinal())));
        // CROSSES
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.WOOD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.WOODEN_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.SANDSTONE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.RED_SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.RED_SANDSTONE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.STONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.STONE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.DIORITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, DIORITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIORITE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, ANDESITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ANDESITE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, GRANITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.GRANITE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.IRON,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.IRON_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.GOLD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.GOLDEN_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.DIAMOND,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIAMOND_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.EMERALD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.EMERALD_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.LAPIS,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.LAPIS_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.REDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.REDSTONE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.OBSIDIAN,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.OBSIDIAN_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.QUARTZ,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.QUARTZ_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.PRIZMARINE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.PRIZMARINE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.ICE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ICE_CROSS.ordinal())));
        // HORISONTAL PLATES
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.WOOD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.WOODEN_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.SANDSTONE_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.RED_SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.RED_SANDSTONE_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.STONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.STONE_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.DIORITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, DIORITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIORITE_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, ANDESITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ANDESITE_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, GRANITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.GRANITE_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.IRON,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.IRON_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.GOLD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.GOLDEN_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.DIAMOND,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIAMOND_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.EMERALD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.EMERALD_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.LAPIS,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.LAPIS_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.REDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.REDSTONE_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.OBSIDIAN,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.OBSIDIAN_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.QUARTZ,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.QUARTZ_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.PRIZMARINE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.PRIZMARINE_HORIZONTAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.ICE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ICE_HORIZONTAL_PLATE.ordinal())));
        // DOGS GRAVES
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.WOOD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.WOODEN_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.SANDSTONE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.RED_SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.RED_SANDSTONE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.STONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.STONE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.DIORITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, DIORITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIORITE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, ANDESITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ANDESITE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, GRANITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.GRANITE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.IRON,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.IRON_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.GOLD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.GOLDEN_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.DIAMOND,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIAMOND_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.EMERALD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.EMERALD_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.LAPIS,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.LAPIS_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.REDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.REDSTONE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.OBSIDIAN,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.OBSIDIAN_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.QUARTZ,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.QUARTZ_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.PRIZMARINE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.PRIZMARINE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.ICE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ICE_DOG_STATUE.ordinal())));
        // CATS GRAVES
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.WOOD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.WOODEN_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.SANDSTONE_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.RED_SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.RED_SANDSTONE_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.STONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.STONE_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.DIORITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, DIORITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIORITE_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, ANDESITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ANDESITE_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, GRANITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.GRANITE_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.IRON,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.IRON_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.GOLD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.GOLDEN_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.DIAMOND,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIAMOND_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.EMERALD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.EMERALD_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.LAPIS,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.LAPIS_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.REDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.REDSTONE_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.OBSIDIAN,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.OBSIDIAN_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.QUARTZ,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.QUARTZ_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.PRIZMARINE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.PRIZMARINE_CAT_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.ICE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ICE_CAT_STATUE.ordinal())));
        // HORSES GRAVES
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.WOOD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.WOODEN_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.SANDSTONE_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.RED_SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.RED_SANDSTONE_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.STONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.STONE_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.DIORITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, DIORITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIORITE_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, ANDESITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ANDESITE_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1, GRANITE_META))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.GRANITE_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.IRON,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.IRON_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.GOLD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.GOLDEN_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.DIAMOND,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIAMOND_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.EMERALD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.EMERALD_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.LAPIS,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.LAPIS_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.REDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.REDSTONE_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.OBSIDIAN,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.OBSIDIAN_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.QUARTZ,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.QUARTZ_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.PRIZMARINE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.PRIZMARINE_HORSE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.ICE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 1))),
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ICE_HORSE_STATUE.ordinal())));
        // SWORD TODO
//                SWORD("block.gravestone.sword", null, EnumGraves.EnumGraveType.SWORD, EnumGraveMaterial.OTHER);

    }

    public List<ItemStack> findMatchingRecipe(boolean isGravestone, EnumGraves.EnumGraveType graveType, EnumGraveMaterial material, boolean isEnchanted, boolean isMossy) {
        for (GravestoneRecipe recipe : recipes) {
            if (recipe.match(isGravestone, graveType, material, isEnchanted, isMossy)) {
                return recipe.getRequiredItems(isEnchanted, isMossy);
            }
        }
        return null;
    }

    public ItemStack findMatchingRecipe(List<ItemStack> requiredItems) {//} isGravestone, EnumGraves.EnumGraveType graveType, EnumGraveMaterial material, boolean isEnchanted, boolean isMossy, List<ItemStack> requiredItems) {
//        for (GravestoneRecipe recipe : recipes) {
//            if (recipe.match(isGravestone, graveType, material, requiredItems)) {
//                return recipe.getResultItem();
//            }
//        }
        for (GravestoneRecipe recipe : recipes) {
            if (recipe.containItems(requiredItems)) {
                return recipe.getResultItem(requiredItems);
            }
        }
        return null;
    }

    public List<GravestoneRecipe> getRecipes() {
        return recipes;
    }

    private static ItemStack getStackWithNTB(Block block, int graveType) {
        ItemStack stack = new ItemStack(block, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Type", graveType);
        stack.setTagCompound(nbt);
        return stack;
    }

    public static GravesCraftingManager getInstance() {
        return instance;
    }
}
