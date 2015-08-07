package gravestone.crafting;

import gravestone.block.enums.EnumGraveMaterial;
import gravestone.block.enums.EnumGraves;
import gravestone.core.GSBlock;
import net.minecraft.block.Block;
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
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1))),//TODO DIORITE
                getStackWithNTB(GSBlock.graveStone, EnumGraves.DIORITE_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.ANDESITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1))),//TODO ANDESITE
                getStackWithNTB(GSBlock.graveStone, EnumGraves.ANDESITE_VERTICAL_PLATE.ordinal())));
        recipes.add(new GravestoneRecipe(true, EnumGraves.EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.GRANITE,
                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.stone, 1))),//TODO Granite
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
//        recipes.add(new GravestoneRecipe(true, ,
//                new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks., 1))),
//                getStackWithNTB(GSBlock.graveStone, EnumGraves..ordinal())));
//        WOODEN_CROSS("block.gravestone.cross", Resources.GRAVE_WOODEN_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.WOOD),
//                SANDSTONE_CROSS("block.gravestone.cross", Resources.GRAVE_SANDSTONE_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.SANDSTONE),
//                RED_SANDSTONE_CROSS("block.gravestone.cross", Resources.GRAVE_RED_SANDSTONE_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.RED_SANDSTONE),
//                STONE_CROSS("block.gravestone.cross", Resources.GRAVE_STONE_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.STONE),
//                DIORITE_CROSS("block.gravestone.cross", Resources.GRAVE_DIORITE_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.DIORITE),
//                ANDESITE_CROSS("block.gravestone.cross", Resources.GRAVE_ANDESITE_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.ANDESITE),
//                GRANITE_CROSS("block.gravestone.cross", Resources.GRAVE_GRANITE_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.GRANITE),
//                IRON_CROSS("block.gravestone.cross", Resources.GRAVE_IRON_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.IRON),
//                GOLDEN_CROSS("block.gravestone.cross", Resources.GRAVE_GOLDEN_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.GOLD),
//                DIAMOND_CROSS("block.gravestone.cross", Resources.GRAVE_DIAMOND_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.DIAMOND),
//                EMERALD_CROSS("block.gravestone.cross", Resources.GRAVE_EMERALD_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.EMERALD),
//                LAPIS_CROSS("block.gravestone.cross", Resources.GRAVE_LAPIS_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.LAPIS),
//                REDSTONE_CROSS("block.gravestone.cross", Resources.GRAVE_REDSTONE_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.REDSTONE),
//                OBSIDIAN_CROSS("block.gravestone.cross", Resources.GRAVE_OBSIDIAN_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.OBSIDIAN),
//                QUARTZ_CROSS("block.gravestone.cross", Resources.GRAVE_QUARTZ_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.QUARTZ),
//                PRIZMARINE_CROSS("block.gravestone.cross", Resources.GRAVE_PRIZMARINE_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.PRIZMARINE),
//                ICE_CROSS("block.gravestone.cross", Resources.GRAVE_ICE_CROSS, EnumGraves.EnumGraveType.CROSS, EnumGraveMaterial.ICE),
//                // HORISONTAL PLATES
//                WOODEN_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_WOODEN_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.WOOD),
//                SANDSTONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_SANDSTONE_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.SANDSTONE),
//                RED_SANDSTONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_RED_SANDSTONE_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.RED_SANDSTONE),
//                STONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_STONE_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.STONE),
//                DIORITE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_DIORITE_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.DIORITE),
//                ANDESITE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_ANDESITE_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.ANDESITE),
//                GRANITE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_GRANITE_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.GRANITE),
//                IRON_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_IRON_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.IRON),
//                GOLDEN_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_GOLDEN_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.GOLD),
//                DIAMOND_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_DIAMOND_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.DIAMOND),
//                EMERALD_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_EMERALD_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.EMERALD),
//                LAPIS_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_LAPIS_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.LAPIS),
//                REDSTONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_REDSTONE_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.REDSTONE),
//                OBSIDIAN_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_OBSIDIAN_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.OBSIDIAN),
//                QUARTZ_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_QUARTZ_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.QUARTZ),
//                PRIZMARINE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_PRIZMARINE_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.PRIZMARINE),
//                ICE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_ICE_HORISONTAL_PLATE, EnumGraves.EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.ICE),
//                // DOGS GRAVES
//                WOODEN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_WOODEN_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.WOOD),
//                SANDSTONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_SANDSTONE_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.SANDSTONE),
//                RED_SANDSTONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_RED_SANDSTONE_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.RED_SANDSTONE),
//                STONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_STONE_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.STONE),
//                DIORITE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_DIORITE_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.DIORITE),
//                ANDESITE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_ANDESITE_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.ANDESITE),
//                GRANITE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_GRANITE_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.GRANITE),
//                IRON_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_IRON_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.IRON),
//                GOLDEN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_GOLDEN_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.GOLD),
//                DIAMOND_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_DIAMOND_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.DIAMOND),
//                EMERALD_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_EMERALD_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.EMERALD),
//                LAPIS_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_LAPIS_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.LAPIS),
//                REDSTONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_REDSTONE_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.REDSTONE),
//                OBSIDIAN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_OBSIDIAN_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.OBSIDIAN),
//                QUARTZ_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_QUARTZ_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.QUARTZ),
//                PRIZMARINE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_PRIZMARINE_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.PRIZMARINE),
//                ICE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_ICE_DOG_STATUE, EnumGraves.EnumGraveType.DOG_STATUE, EnumGraveMaterial.ICE),
//                // CATS GRAVES
//                WOODEN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_WOODEN_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.WOOD),
//                SANDSTONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_SANDSTONE_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.SANDSTONE),
//                RED_SANDSTONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_RED_SANDSTONE_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.RED_SANDSTONE),
//                STONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_STONE_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.STONE),
//                DIORITE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_DIORITE_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.DIORITE),
//                ANDESITE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_ANDESITE_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.ANDESITE),
//                GRANITE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_GRANITE_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.GRANITE),
//                IRON_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_IRON_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.IRON),
//                GOLDEN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_GOLDEN_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.GOLD),
//                DIAMOND_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_DIAMOND_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.DIAMOND),
//                EMERALD_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_EMERALD_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.EMERALD),
//                LAPIS_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_LAPIS_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.LAPIS),
//                REDSTONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_REDSTONE_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.REDSTONE),
//                OBSIDIAN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_OBSIDIAN_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.OBSIDIAN),
//                QUARTZ_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_QUARTZ_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.QUARTZ),
//                PRIZMARINE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_PRIZMARINE_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.PRIZMARINE),
//                ICE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_ICE_CAT_STATUE, EnumGraves.EnumGraveType.CAT_STATUE, EnumGraveMaterial.ICE),
//                // HORSES GRAVES
//                WOODEN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_WOODEN_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.WOOD),
//                SANDSTONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_SANDSTONE_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.SANDSTONE),
//                RED_SANDSTONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_RED_SANDSTONE_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.RED_SANDSTONE),
//                STONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_STONE_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.STONE),
//                DIORITE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_DIORITE_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.DIORITE),
//                ANDESITE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_ANDESITE_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.ANDESITE),
//                GRANITE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_GRANITE_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.GRANITE),
//                IRON_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_IRON_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.IRON),
//                GOLDEN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_GOLDEN_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.GOLD),
//                DIAMOND_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_DIAMOND_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.DIAMOND),
//                EMERALD_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_EMERALD_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.EMERALD),
//                LAPIS_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_LAPIS_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.LAPIS),
//                REDSTONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_REDSTONE_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.REDSTONE),
//                OBSIDIAN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_OBSIDIAN_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.OBSIDIAN),
//                QUARTZ_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_QUARTZ_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.QUARTZ),
//                PRIZMARINE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_PRIZMARINE_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.PRIZMARINE),
//                ICE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_ICE_HORSE_STATUE, EnumGraves.EnumGraveType.HORSE_STATUE, EnumGraveMaterial.ICE),
//                // SWORD
//                SWORD("block.gravestone.sword", null, EnumGraves.EnumGraveType.SWORD, EnumGraveMaterial.OTHER);
//

    }

    public ItemStack findMatchingRecipe(boolean isGravestone, EnumGraves.EnumGraveType graveType, EnumGraveMaterial material, List<ItemStack> requiredItems) {
        for (GravestoneRecipe recipe : recipes) {
            if (recipe.match(isGravestone, graveType, material, requiredItems)) {
                return recipe.getResultItem();
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
