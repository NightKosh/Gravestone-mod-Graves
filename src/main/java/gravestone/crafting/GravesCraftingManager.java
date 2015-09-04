package gravestone.crafting;

import gravestone.block.enums.EnumGraveMaterial;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumMemorials;
import gravestone.config.GSConfig;
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

        
        // Memorials
        // CROSS
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.WOOD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.RED_SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.RED_SANDSTONE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.STONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.DIORITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 6, DIORITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.DIORITE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 6, ANDESITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.ANDESITE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 6, GRANITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.GRANITE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.IRON,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.GOLD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.DIAMOND,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.EMERALD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.LAPIS,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.REDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.OBSIDIAN,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.QUARTZ,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.PRIZMARINE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.PRIZMARINE_CROSS.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CROSS, EnumGraveMaterial.ICE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_CROSS.ordinal())));
        // OBELISK
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.WOOD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.RED_SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.RED_SANDSTONE_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.STONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.DIORITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 6, DIORITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.DIORITE_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 6, ANDESITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.ANDESITE_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 6, GRANITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.GRANITE_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.IRON,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.GOLD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.DIAMOND,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.EMERALD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.LAPIS,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.REDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.OBSIDIAN,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.QUARTZ,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.PRIZMARINE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.PRIZMARINE_OBELISK.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.OBELISK, EnumGraveMaterial.ICE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 6))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_OBELISK.ordinal())));
        // STEVE_STATUE
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.WOOD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.RED_SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.RED_SANDSTONE_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.STONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.DIORITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, DIORITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.DIORITE_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, ANDESITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.ANDESITE_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, GRANITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.GRANITE_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.IRON,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.GOLD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.DIAMOND,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.EMERALD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.LAPIS,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.REDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.OBSIDIAN,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.QUARTZ,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.PRIZMARINE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.PRIZMARINE_STEVE_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.ICE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_STEVE_STATUE.ordinal())));
        // VILLAGER_STATUE
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.WOOD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.RED_SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.RED_SANDSTONE_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.STONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.DIORITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, DIORITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.DIORITE_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, ANDESITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.ANDESITE_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, GRANITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.GRANITE_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.IRON,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.GOLD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.DIAMOND,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.EMERALD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.LAPIS,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.REDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.OBSIDIAN,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.QUARTZ,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.PRIZMARINE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.PRIZMARINE_VILLAGER_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.ICE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_VILLAGER_STATUE.ordinal())));
        // ANGEL_STATUE
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.WOOD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.RED_SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.RED_SANDSTONE_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.STONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.DIORITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, DIORITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.DIORITE_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, ANDESITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.ANDESITE_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, GRANITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.GRANITE_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.IRON,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.GOLD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.DIAMOND,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.EMERALD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.LAPIS,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.REDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.OBSIDIAN,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.QUARTZ,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.PRIZMARINE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.PRIZMARINE_ANGEL_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.ICE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_ANGEL_STATUE.ordinal())));
        // DOG_STATUE
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.WOOD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.RED_SANDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.RED_SANDSTONE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.STONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.DIORITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, DIORITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.DIORITE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, ANDESITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.ANDESITE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, GRANITE_META))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.GRANITE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.IRON,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.GOLD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.DIAMOND,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.EMERALD,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.LAPIS,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.REDSTONE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.OBSIDIAN,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.QUARTZ,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.PRIZMARINE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.PRIZMARINE_DOG_STATUE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.DOG_STATUE, EnumGraveMaterial.ICE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 3))),
                getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_DOG_STATUE.ordinal())));
        // CREEPER_STATUE
        if (GSConfig.enableCreeperStatuesRecipes) {
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.WOOD,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.SANDSTONE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.RED_SANDSTONE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.RED_SANDSTONE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.STONE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.DIORITE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, DIORITE_META))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.DIORITE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.ANDESITE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, ANDESITE_META))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.ANDESITE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.GRANITE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, GRANITE_META))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.GRANITE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.IRON,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.GOLD,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.DIAMOND,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.EMERALD,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.LAPIS,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.REDSTONE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.OBSIDIAN,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.QUARTZ,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.PRIZMARINE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.PRIZMARINE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.ICE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_CREEPER_STATUE.ordinal())));// CREEPER_STATUE
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.WOOD,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.planks, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.SANDSTONE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.sandstone, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.RED_SANDSTONE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.red_sandstone, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.RED_SANDSTONE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.STONE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.DIORITE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, DIORITE_META))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.DIORITE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.ANDESITE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, ANDESITE_META))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.ANDESITE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.GRANITE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 3, GRANITE_META))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.GRANITE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.IRON,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.iron_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.GOLD,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.gold_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.DIAMOND,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.diamond_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.EMERALD,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.emerald_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.LAPIS,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.lapis_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.REDSTONE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.redstone_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.OBSIDIAN,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.obsidian, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.QUARTZ,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.quartz_block, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.PRIZMARINE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.prismarine, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.PRIZMARINE_CREEPER_STATUE.ordinal())));
            recipes.add(new GravestoneRecipe(true, EnumMemorials.EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.ICE,
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.ice, 3))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_CREEPER_STATUE.ordinal())));
        }
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
