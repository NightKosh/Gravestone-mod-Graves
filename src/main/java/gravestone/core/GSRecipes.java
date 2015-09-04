package gravestone.core;

import gravestone.block.GraveStoneHelper;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumMemorials;
import gravestone.block.enums.EnumSkullCandle;
import gravestone.block.enums.EnumSpawner;
import gravestone.config.GSConfig;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSRecipes {

    private GSRecipes() {
    }

    public static void registration() {
        // chisel recipe
        GameRegistry.addRecipe(new ItemStack(GSItem.chisel), "   ", "s  ", " i ", 's', Items.stick, 'i', Items.iron_ingot);
        // pile of bones
        GameRegistry.addRecipe(new ItemStack(GSBlock.pileOfBones, 1, 0), "   ", " b ", "bbb", 'b', Items.bone);
        GameRegistry.addRecipe(new ItemStack(GSBlock.pileOfBones, 9, 1), "bbb", "bsb", "bbb", 'b', new ItemStack(GSBlock.pileOfBones, 1, 0), 's', new ItemStack(Items.skull, 1, 0));
        // piles to bones
        GameRegistry.addRecipe(new ItemStack(Items.bone, 4), "p", 'p', new ItemStack(GSBlock.pileOfBones, 1, 0));
        GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 0), "ppp", "ppp", "ppp", 'p', new ItemStack(GSBlock.pileOfBones, 1, 1));
        // bone blocks
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneBlock, 1, 0), "ppp", "ppp", "ppp", 'p', new ItemStack(GSBlock.pileOfBones, 1, 0));
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneBlock, 9, 1), "bbb", "bsb", "bbb", 'b', new ItemStack(GSBlock.boneBlock, 1, 0), 's', new ItemStack(Items.skull, 1, 0));

        GameRegistry.addRecipe(new ItemStack(GSBlock.boneBlock), "x", "x", 'x', GSBlock.boneSlab);
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneSlab, 6), "xxx", 'x', GSBlock.boneBlock);
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneStairs, 4), "x  ", "xx ", "xxx", 'x', GSBlock.boneBlock);
        // Bone block to piles
        GameRegistry.addRecipe(new ItemStack(GSBlock.pileOfBones, 9, 0), "x", 'x', GSBlock.boneBlock);


        // graves
        // vertical plates
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.WOODEN_VERTICAL_PLATE.ordinal(), "Type"), Blocks.planks);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.SANDSTONE_VERTICAL_PLATE.ordinal(), "Type"), Blocks.sandstone);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.STONE_VERTICAL_PLATE.ordinal(), "Type"), Blocks.stone);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.IRON_VERTICAL_PLATE.ordinal(), "Type"), Blocks.iron_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.GOLDEN_VERTICAL_PLATE.ordinal(), "Type"), Blocks.gold_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.DIAMOND_VERTICAL_PLATE.ordinal(), "Type"), Blocks.diamond_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.EMERALD_VERTICAL_PLATE.ordinal(), "Type"), Blocks.emerald_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.LAPIS_VERTICAL_PLATE.ordinal(), "Type"), Blocks.lapis_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.REDSTONE_VERTICAL_PLATE.ordinal(), "Type"), Blocks.redstone_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.QUARTZ_VERTICAL_PLATE.ordinal(), "Type"), Blocks.quartz_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.OBSIDIAN_VERTICAL_PLATE.ordinal(), "Type"), Blocks.obsidian);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.ICE_VERTICAL_PLATE.ordinal(), "Type"), Blocks.ice);
        // crosses
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.WOODEN_CROSS.ordinal(), "Type"), Blocks.planks);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.SANDSTONE_CROSS.ordinal(), "Type"), Blocks.sandstone);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.STONE_CROSS.ordinal(), "Type"), Blocks.stone);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.IRON_CROSS.ordinal(), "Type"), Blocks.iron_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.GOLDEN_CROSS.ordinal(), "Type"), Blocks.gold_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.DIAMOND_CROSS.ordinal(), "Type"), Blocks.diamond_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.EMERALD_CROSS.ordinal(), "Type"), Blocks.emerald_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.LAPIS_CROSS.ordinal(), "Type"), Blocks.lapis_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.REDSTONE_CROSS.ordinal(), "Type"), Blocks.redstone_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.QUARTZ_CROSS.ordinal(), "Type"), Blocks.quartz_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.OBSIDIAN_CROSS.ordinal(), "Type"), Blocks.obsidian);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.ICE_CROSS.ordinal(), "Type"), Blocks.ice);
        // horisontal plates
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.WOODEN_HORIZONTAL_PLATE.ordinal(), "Type"), Blocks.planks);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.SANDSTONE_HORIZONTAL_PLATE.ordinal(), "Type"), Blocks.sandstone);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.STONE_HORIZONTAL_PLATE.ordinal(), "Type"), Blocks.stone);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.IRON_HORIZONTAL_PLATE.ordinal(), "Type"), Blocks.iron_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.GOLDEN_HORIZONTAL_PLATE.ordinal(), "Type"), Blocks.gold_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.DIAMOND_HORIZONTAL_PLATE.ordinal(), "Type"), Blocks.diamond_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.EMERALD_HORIZONTAL_PLATE.ordinal(), "Type"), Blocks.emerald_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.LAPIS_HORIZONTAL_PLATE.ordinal(), "Type"), Blocks.lapis_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.REDSTONE_HORIZONTAL_PLATE.ordinal(), "Type"), Blocks.redstone_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.QUARTZ_HORIZONTAL_PLATE.ordinal(), "Type"), Blocks.quartz_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.OBSIDIAN_HORIZONTAL_PLATE.ordinal(), "Type"), Blocks.obsidian);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.ICE_HORIZONTAL_PLATE.ordinal(), "Type"), Blocks.ice);
        // pet graves
        // dogs graves
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.WOODEN_DOG_STATUE.ordinal(), "Type"), Blocks.planks);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.SANDSTONE_DOG_STATUE.ordinal(), "Type"), Blocks.sandstone);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.STONE_DOG_STATUE.ordinal(), "Type"), Blocks.stone);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.IRON_DOG_STATUE.ordinal(), "Type"), Blocks.iron_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.GOLDEN_DOG_STATUE.ordinal(), "Type"), Blocks.gold_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.DIAMOND_DOG_STATUE.ordinal(), "Type"), Blocks.diamond_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.EMERALD_DOG_STATUE.ordinal(), "Type"), Blocks.emerald_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.LAPIS_DOG_STATUE.ordinal(), "Type"), Blocks.lapis_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.REDSTONE_DOG_STATUE.ordinal(), "Type"), Blocks.redstone_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.QUARTZ_DOG_STATUE.ordinal(), "Type"), Blocks.quartz_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.OBSIDIAN_DOG_STATUE.ordinal(), "Type"), Blocks.obsidian);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.ICE_DOG_STATUE.ordinal(), "Type"), Blocks.ice);
        // cats graves
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.WOODEN_CAT_STATUE.ordinal(), "Type"), Blocks.planks);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.SANDSTONE_CAT_STATUE.ordinal(), "Type"), Blocks.sandstone);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.STONE_CAT_STATUE.ordinal(), "Type"), Blocks.stone);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.IRON_CAT_STATUE.ordinal(), "Type"), Blocks.iron_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.GOLDEN_CAT_STATUE.ordinal(), "Type"), Blocks.gold_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.DIAMOND_CAT_STATUE.ordinal(), "Type"), Blocks.diamond_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.EMERALD_CAT_STATUE.ordinal(), "Type"), Blocks.emerald_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.LAPIS_CAT_STATUE.ordinal(), "Type"), Blocks.lapis_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.REDSTONE_CAT_STATUE.ordinal(), "Type"), Blocks.redstone_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.QUARTZ_CAT_STATUE.ordinal(), "Type"), Blocks.quartz_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.OBSIDIAN_CAT_STATUE.ordinal(), "Type"), Blocks.obsidian);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.ICE_CAT_STATUE.ordinal(), "Type"), Blocks.ice);
        // horses graves
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.WOODEN_HORSE_STATUE.ordinal(), "Type"), Blocks.planks);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.SANDSTONE_HORSE_STATUE.ordinal(), "Type"), Blocks.sandstone);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.STONE_HORSE_STATUE.ordinal(), "Type"), Blocks.stone);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.IRON_HORSE_STATUE.ordinal(), "Type"), Blocks.iron_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.GOLDEN_HORSE_STATUE.ordinal(), "Type"), Blocks.gold_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.DIAMOND_HORSE_STATUE.ordinal(), "Type"), Blocks.diamond_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.EMERALD_HORSE_STATUE.ordinal(), "Type"), Blocks.emerald_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.LAPIS_HORSE_STATUE.ordinal(), "Type"), Blocks.lapis_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.REDSTONE_HORSE_STATUE.ordinal(), "Type"), Blocks.redstone_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.QUARTZ_HORSE_STATUE.ordinal(), "Type"), Blocks.quartz_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.OBSIDIAN_HORSE_STATUE.ordinal(), "Type"), Blocks.obsidian);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, EnumGraves.ICE_HORSE_STATUE.ordinal(), "Type"), Blocks.ice);

        // memorials
        // crosses
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_CROSS.ordinal(), "Type"), Blocks.planks);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_CROSS.ordinal(), "Type"), Blocks.sandstone);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_CROSS.ordinal(), "Type"), Blocks.stone);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_CROSS.ordinal(), "Type"), Blocks.iron_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_CROSS.ordinal(), "Type"), Blocks.gold_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_CROSS.ordinal(), "Type"), Blocks.diamond_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_CROSS.ordinal(), "Type"), Blocks.emerald_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_CROSS.ordinal(), "Type"), Blocks.lapis_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_CROSS.ordinal(), "Type"), Blocks.redstone_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_CROSS.ordinal(), "Type"), Blocks.quartz_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_CROSS.ordinal(), "Type"), Blocks.obsidian);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_CROSS.ordinal(), "Type"), Blocks.ice);
        // obelisks TODO
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_OBELISK.ordinal(), "Type"), Blocks.planks);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_OBELISK.ordinal(), "Type"), Blocks.sandstone);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_OBELISK.ordinal(), "Type"), Blocks.stone);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_OBELISK.ordinal(), "Type"), Blocks.iron_block);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_OBELISK.ordinal(), "Type"), Blocks.gold_block);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_OBELISK.ordinal(), "Type"), Blocks.diamond_block);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_OBELISK.ordinal(), "Type"), Blocks.emerald_block);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_OBELISK.ordinal(), "Type"), Blocks.lapis_block);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_OBELISK.ordinal(), "Type"), Blocks.redstone_block);
        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_OBELISK.ordinal(), "Type"), Blocks.quartz_block);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_OBELISK.ordinal(), "Type"), Blocks.obsidian);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_OBELISK.ordinal(), "Type"), Blocks.ice);
        // steve statues
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_STEVE_STATUE.ordinal(), "Type"), Blocks.planks);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_STEVE_STATUE.ordinal(), "Type"), Blocks.sandstone);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_STEVE_STATUE.ordinal(), "Type"), Blocks.stone);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_STEVE_STATUE.ordinal(), "Type"), Blocks.iron_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_STEVE_STATUE.ordinal(), "Type"), Blocks.gold_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_STEVE_STATUE.ordinal(), "Type"), Blocks.diamond_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_STEVE_STATUE.ordinal(), "Type"), Blocks.emerald_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_STEVE_STATUE.ordinal(), "Type"), Blocks.lapis_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_STEVE_STATUE.ordinal(), "Type"), Blocks.redstone_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_STEVE_STATUE.ordinal(), "Type"), Blocks.quartz_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_STEVE_STATUE.ordinal(), "Type"), Blocks.obsidian);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_STEVE_STATUE.ordinal(), "Type"), Blocks.ice);
        // villager statue
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_VILLAGER_STATUE.ordinal(), "Type"), Blocks.planks);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_VILLAGER_STATUE.ordinal(), "Type"), Blocks.sandstone);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_VILLAGER_STATUE.ordinal(), "Type"), Blocks.stone);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_VILLAGER_STATUE.ordinal(), "Type"), Blocks.iron_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_VILLAGER_STATUE.ordinal(), "Type"), Blocks.gold_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_VILLAGER_STATUE.ordinal(), "Type"), Blocks.diamond_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_VILLAGER_STATUE.ordinal(), "Type"), Blocks.emerald_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_VILLAGER_STATUE.ordinal(), "Type"), Blocks.lapis_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_VILLAGER_STATUE.ordinal(), "Type"), Blocks.redstone_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_VILLAGER_STATUE.ordinal(), "Type"), Blocks.quartz_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_VILLAGER_STATUE.ordinal(), "Type"), Blocks.obsidian);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_VILLAGER_STATUE.ordinal(), "Type"), Blocks.ice);
        // angel statue
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_ANGEL_STATUE.ordinal(), "Type"), Blocks.planks);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_ANGEL_STATUE.ordinal(), "Type"), Blocks.sandstone);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_ANGEL_STATUE.ordinal(), "Type"), Blocks.stone);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_ANGEL_STATUE.ordinal(), "Type"), Blocks.iron_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_ANGEL_STATUE.ordinal(), "Type"), Blocks.gold_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_ANGEL_STATUE.ordinal(), "Type"), Blocks.diamond_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_ANGEL_STATUE.ordinal(), "Type"), Blocks.emerald_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_ANGEL_STATUE.ordinal(), "Type"), Blocks.lapis_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_ANGEL_STATUE.ordinal(), "Type"), Blocks.redstone_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_ANGEL_STATUE.ordinal(), "Type"), Blocks.quartz_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_ANGEL_STATUE.ordinal(), "Type"), Blocks.obsidian);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_ANGEL_STATUE.ordinal(), "Type"), Blocks.ice);
        // dog statue
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_DOG_STATUE.ordinal(), "Type"), Blocks.planks);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_DOG_STATUE.ordinal(), "Type"), Blocks.sandstone);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_DOG_STATUE.ordinal(), "Type"), Blocks.stone);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_DOG_STATUE.ordinal(), "Type"), Blocks.iron_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_DOG_STATUE.ordinal(), "Type"), Blocks.gold_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_DOG_STATUE.ordinal(), "Type"), Blocks.diamond_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_DOG_STATUE.ordinal(), "Type"), Blocks.emerald_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_DOG_STATUE.ordinal(), "Type"), Blocks.lapis_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_DOG_STATUE.ordinal(), "Type"), Blocks.redstone_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_DOG_STATUE.ordinal(), "Type"), Blocks.quartz_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_DOG_STATUE.ordinal(), "Type"), Blocks.obsidian);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_DOG_STATUE.ordinal(), "Type"), Blocks.ice);
        // cat statue
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_CAT_STATUE.ordinal(), "Type"), Blocks.planks);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_CAT_STATUE.ordinal(), "Type"), Blocks.sandstone);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_CAT_STATUE.ordinal(), "Type"), Blocks.stone);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_CAT_STATUE.ordinal(), "Type"), Blocks.iron_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_CAT_STATUE.ordinal(), "Type"), Blocks.gold_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_CAT_STATUE.ordinal(), "Type"), Blocks.diamond_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_CAT_STATUE.ordinal(), "Type"), Blocks.emerald_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_CAT_STATUE.ordinal(), "Type"), Blocks.lapis_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_CAT_STATUE.ordinal(), "Type"), Blocks.redstone_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_CAT_STATUE.ordinal(), "Type"), Blocks.quartz_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_CAT_STATUE.ordinal(), "Type"), Blocks.obsidian);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_CAT_STATUE.ordinal(), "Type"), Blocks.ice);
        // creeper statue
        if (GSConfig.enableCreeperStatuesRecipes) {
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.WOODEN_CREEPER_STATUE.ordinal(), "Type"), Blocks.planks);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.SANDSTONE_CREEPER_STATUE.ordinal(), "Type"), Blocks.sandstone);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.STONE_CREEPER_STATUE.ordinal(), "Type"), Blocks.stone);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.IRON_CREEPER_STATUE.ordinal(), "Type"), Blocks.iron_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.GOLDEN_CREEPER_STATUE.ordinal(), "Type"), Blocks.gold_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.DIAMOND_CREEPER_STATUE.ordinal(), "Type"), Blocks.diamond_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.EMERALD_CREEPER_STATUE.ordinal(), "Type"), Blocks.emerald_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.LAPIS_CREEPER_STATUE.ordinal(), "Type"), Blocks.lapis_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.REDSTONE_CREEPER_STATUE.ordinal(), "Type"), Blocks.redstone_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.QUARTZ_CREEPER_STATUE.ordinal(), "Type"), Blocks.quartz_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.OBSIDIAN_CREEPER_STATUE.ordinal(), "Type"), Blocks.obsidian);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.ICE_CREEPER_STATUE.ordinal(), "Type"), Blocks.ice);
        }
        // gibbets
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.GIBBET.ordinal(), "Type"), "ww ", "wr ", "wc ", 'w', Blocks.planks, 'r', Items.lead, 'c', GSItem.chisel);
        // stocks
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.STOCKS.ordinal(), "Type"), "wsw", "wcw", 'w', Blocks.planks, 's', Blocks.wooden_slab, 'c', GSItem.chisel);
        // burning stake
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.BURNING_STAKE.ordinal(), "Type"), " w ", "www", "hwh", 'w', Blocks.planks, 'h', Blocks.hay_block);


        // sword graves
        for (Item sword : GraveStoneHelper.swordsList) {
            GameRegistry.addRecipe(GraveStoneHelper.getSwordAsGrave(Item.getItemFromBlock(GSBlock.graveStone), new ItemStack(sword, 1)), "sc", 's', sword, 'c', GSItem.chisel);
        }

        // spawners
        if (GSConfig.enableBossSpawnerCraftingRecipe) {
            GameRegistry.addRecipe(new ItemStack(GSBlock.spawner, 1, EnumSpawner.WITHER_SPAWNER.ordinal()), "bcb", "cec", "cbc",
                    'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()),
                    'b', new ItemStack(Items.dye, 1, 15), 'e', Items.ender_eye);
        }
        if (GSConfig.enableSpawnerCraftingRecipe) {
            GameRegistry.addRecipe(new ItemStack(GSBlock.spawner, 1, EnumSpawner.SKELETON_SPAWNER.ordinal()), "bcb", "cec", "cbc",
                    'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()),
                    'b', new ItemStack(Items.dye, 1, 15), 'e', Items.ender_eye);
            GameRegistry.addRecipe(new ItemStack(GSBlock.spawner, 1, EnumSpawner.ZOMBIE_SPAWNER.ordinal()), "bcb", "cec", "cbc",
                    'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()),
                    'b', new ItemStack(Items.dye, 1, 15), 'e', Items.ender_eye);
        }

        // candles
        GameRegistry.addRecipe(new ItemStack(GSBlock.candle, 1, 0), "t", "m", "s", 't', Items.string, 'm', new ItemStack(Items.dye, 1, 15), 's', Items.slime_ball);
        addSkullCandleReciepes(new ItemStack(GSBlock.candle, 1, 0));

        GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 0), "c", 'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()));
        GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 1), "c", 'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()));
        GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 2), "c", 'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()));

        // altar
        GameRegistry.addRecipe(new ItemStack(GSBlock.altar), " h ", "sns", "bbb",
                'h', Items.nether_star,
                's', new ItemStack(Items.skull, 1, 0),
                'n', new ItemStack(GSBlock.trap, 1, 0),
                'b', new ItemStack(GSBlock.boneBlock, 1, 0));
    }

    private static ItemStack getStackWithNTB(Block block, int graveType, String ntbName) {
        ItemStack stack = new ItemStack(block, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger(ntbName, graveType);
        stack.setTagCompound(nbt);
        return stack;
    }

    // player graves
    private static void addVerticalPlateGravestoneRecipe(ItemStack gravestone, Block material) {
        GameRegistry.addRecipe(gravestone, "mc", 'm', material, 'c', GSItem.chisel);
    }

    private static void addCrossGravestoneRecipe(ItemStack gravestone, Block material) {
        GameRegistry.addRecipe(gravestone, " c", "m ", 'm', material, 'c', GSItem.chisel);
    }

    private static void addHorisontalPlateGravestoneRecipe(ItemStack gravestone, Block material) {
        GameRegistry.addRecipe(gravestone, "c", "m", 'm', material, 'c', GSItem.chisel);
    }

    // pets graves
    private static void addDogGravestoneRecipe(ItemStack gravestone, Block material) {
        GameRegistry.addRecipe(gravestone, " c", "  ", "m ", 'm', material, 'c', GSItem.chisel);
    }

    private static void addCatGravestoneRecipe(ItemStack gravestone, Block material) {
        GameRegistry.addRecipe(gravestone, " c", "  ", " m", 'm', material, 'c', GSItem.chisel);
    }

    private static void addHorseGravestoneRecipe(ItemStack gravestone, Block material) {
        GameRegistry.addRecipe(gravestone, "  c", "   ", "m  ", 'm', material, 'c', GSItem.chisel);
    }

    // memorials
    // cross
    private static void addCrossMemorialRecipe(ItemStack memorial, Block material) {
        GameRegistry.addRecipe(memorial, " mc", "mmm", " m ", 'm', material, 'c', GSItem.chisel);
    }

    // obelisk
    private static void addObeliskMemorialRecipe(ItemStack memorial, Block material) {
        GameRegistry.addRecipe(memorial, " mc", " m ", "mmm", 'm', material, 'c', GSItem.chisel);
    }

    private static void addSteveMemorialRecipe(ItemStack memorial, Block material) {
        GameRegistry.addRecipe(memorial, "mc", "m ", "m ", 'm', material, 'c', GSItem.chisel);
    }

    private static void addVillagerMemorialRecipe(ItemStack memorial, Block material) {
        GameRegistry.addRecipe(memorial, "m ", "mc", "m ", 'm', material, 'c', GSItem.chisel);
    }

    private static void addAngelMemorialRecipe(ItemStack memorial, Block material) {
        GameRegistry.addRecipe(memorial, "m ", "m ", "mc", 'm', material, 'c', GSItem.chisel);
    }

    private static void addDogMemorialRecipe(ItemStack memorial, Block material) {
        GameRegistry.addRecipe(memorial, "mc", "m ", 'm', material, 'c', GSItem.chisel);
    }

    private static void addCatMemorialRecipe(ItemStack memorial, Block material) {
        GameRegistry.addRecipe(memorial, "m ", "mc", 'm', material, 'c', GSItem.chisel);
    }

    private static void addCreeperMemorialRecipe(ItemStack memorial, Block material) {
        GameRegistry.addRecipe(memorial, "m  ", "m c", "m  ", 'm', material, 'c', GSItem.chisel);
    }

    public static void addSkullCandleReciepes(ItemStack candle) {
        GameRegistry.addRecipe(new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.skull, 1, 0), 'c', candle);
        GameRegistry.addRecipe(new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.skull, 1, 1), 'c', candle);
        GameRegistry.addRecipe(new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.skull, 1, 2), 'c', candle);
    }

    public static void addForestryBackpack(ItemStack backpack, Item item) {
        GameRegistry.addRecipe(backpack, "sws", "ici", "sws", 'w', Blocks.wool, 'i', item, 's', Items.string, 'c', Blocks.chest);
    }
}
