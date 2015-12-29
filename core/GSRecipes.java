package gravestone.core;

import cpw.mods.fml.common.registry.GameRegistry;
import gravestone.block.GraveStoneHelper;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumMemorials;
import gravestone.block.enums.EnumSkullCandle;
import gravestone.block.enums.EnumSpawner;
import gravestone.config.GraveStoneConfig;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.WOODEN_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.planks);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.SANDSTONE_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.sandstone);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.STONE_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.stone);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.MOSSY_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.IRON_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.iron_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.GOLDEN_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.gold_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.DIAMOND_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.diamond_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.EMERALD_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.emerald_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.LAPIS_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.lapis_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.REDSTONE_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.redstone_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.QUARTZ_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.quartz_block);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.OBSIDIAN_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.obsidian);
        addVerticalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.ICE_VERTICAL_PLATE.ordinal(), "GraveType"), Blocks.ice);
        // crosses
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.WOODEN_CROSS.ordinal(), "GraveType"), Blocks.planks);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.SANDSTONE_CROSS.ordinal(), "GraveType"), Blocks.sandstone);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.STONE_CROSS.ordinal(), "GraveType"), Blocks.stone);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.MOSSY_CROSS.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.IRON_CROSS.ordinal(), "GraveType"), Blocks.iron_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.GOLDEN_CROSS.ordinal(), "GraveType"), Blocks.gold_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.DIAMOND_CROSS.ordinal(), "GraveType"), Blocks.diamond_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.EMERALD_CROSS.ordinal(), "GraveType"), Blocks.emerald_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.LAPIS_CROSS.ordinal(), "GraveType"), Blocks.lapis_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.REDSTONE_CROSS.ordinal(), "GraveType"), Blocks.redstone_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.QUARTZ_CROSS.ordinal(), "GraveType"), Blocks.quartz_block);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.OBSIDIAN_CROSS.ordinal(), "GraveType"), Blocks.obsidian);
        addCrossGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.ICE_CROSS.ordinal(), "GraveType"), Blocks.ice);
        // horisontal plates
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.WOODEN_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.planks);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.SANDSTONE_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.sandstone);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.STONE_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.stone);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.MOSSY_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.IRON_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.iron_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.GOLDEN_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.gold_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.DIAMOND_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.diamond_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.EMERALD_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.emerald_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.LAPIS_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.lapis_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.REDSTONE_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.redstone_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.QUARTZ_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.quartz_block);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.OBSIDIAN_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.obsidian);
        addHorisontalPlateGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.ICE_HORISONTAL_PLATE.ordinal(), "GraveType"), Blocks.ice);
        // pet graves
        // dogs graves
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.WOODEN_DOG_STATUE.ordinal(), "GraveType"), Blocks.planks);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.SANDSTONE_DOG_STATUE.ordinal(), "GraveType"), Blocks.sandstone);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.STONE_DOG_STATUE.ordinal(), "GraveType"), Blocks.stone);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.MOSSY_DOG_STATUE.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.IRON_DOG_STATUE.ordinal(), "GraveType"), Blocks.iron_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.GOLDEN_DOG_STATUE.ordinal(), "GraveType"), Blocks.gold_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.DIAMOND_DOG_STATUE.ordinal(), "GraveType"), Blocks.diamond_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.EMERALD_DOG_STATUE.ordinal(), "GraveType"), Blocks.emerald_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.LAPIS_DOG_STATUE.ordinal(), "GraveType"), Blocks.lapis_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.REDSTONE_DOG_STATUE.ordinal(), "GraveType"), Blocks.redstone_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.QUARTZ_DOG_STATUE.ordinal(), "GraveType"), Blocks.quartz_block);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.OBSIDIAN_DOG_STATUE.ordinal(), "GraveType"), Blocks.obsidian);
        addDogGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.ICE_DOG_STATUE.ordinal(), "GraveType"), Blocks.ice);
        // cats graves
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.WOODEN_CAT_STATUE.ordinal(), "GraveType"), Blocks.planks);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.SANDSTONE_CAT_STATUE.ordinal(), "GraveType"), Blocks.sandstone);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.STONE_CAT_STATUE.ordinal(), "GraveType"), Blocks.stone);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.MOSSY_CAT_STATUE.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.IRON_CAT_STATUE.ordinal(), "GraveType"), Blocks.iron_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.GOLDEN_CAT_STATUE.ordinal(), "GraveType"), Blocks.gold_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.DIAMOND_CAT_STATUE.ordinal(), "GraveType"), Blocks.diamond_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.EMERALD_CAT_STATUE.ordinal(), "GraveType"), Blocks.emerald_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.LAPIS_CAT_STATUE.ordinal(), "GraveType"), Blocks.lapis_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.REDSTONE_CAT_STATUE.ordinal(), "GraveType"), Blocks.redstone_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.QUARTZ_CAT_STATUE.ordinal(), "GraveType"), Blocks.quartz_block);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.OBSIDIAN_CAT_STATUE.ordinal(), "GraveType"), Blocks.obsidian);
        addCatGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.ICE_CAT_STATUE.ordinal(), "GraveType"), Blocks.ice);
        // horses graves
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.WOODEN_HORSE_STATUE.ordinal(), "GraveType"), Blocks.planks);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.SANDSTONE_HORSE_STATUE.ordinal(), "GraveType"), Blocks.sandstone);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.STONE_HORSE_STATUE.ordinal(), "GraveType"), Blocks.stone);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.MOSSY_HORSE_STATUE.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.IRON_HORSE_STATUE.ordinal(), "GraveType"), Blocks.iron_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.GOLDEN_HORSE_STATUE.ordinal(), "GraveType"), Blocks.gold_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.DIAMOND_HORSE_STATUE.ordinal(), "GraveType"), Blocks.diamond_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.EMERALD_HORSE_STATUE.ordinal(), "GraveType"), Blocks.emerald_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.LAPIS_HORSE_STATUE.ordinal(), "GraveType"), Blocks.lapis_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.REDSTONE_HORSE_STATUE.ordinal(), "GraveType"), Blocks.redstone_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.QUARTZ_HORSE_STATUE.ordinal(), "GraveType"), Blocks.quartz_block);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.OBSIDIAN_HORSE_STATUE.ordinal(), "GraveType"), Blocks.obsidian);
        addHorseGravestoneRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.ICE_HORSE_STATUE.ordinal(), "GraveType"), Blocks.ice);

        // memorials
        // crosses
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.WOODEN_CROSS.ordinal(), "GraveType"), Blocks.planks);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.SANDSTONE_CROSS.ordinal(), "GraveType"), Blocks.sandstone);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.STONE_CROSS.ordinal(), "GraveType"), Blocks.stone);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.MOSSY_CROSS.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.IRON_CROSS.ordinal(), "GraveType"), Blocks.iron_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.GOLDEN_CROSS.ordinal(), "GraveType"), Blocks.gold_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.DIAMOND_CROSS.ordinal(), "GraveType"), Blocks.diamond_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.EMERALD_CROSS.ordinal(), "GraveType"), Blocks.emerald_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.LAPIS_CROSS.ordinal(), "GraveType"), Blocks.lapis_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.REDSTONE_CROSS.ordinal(), "GraveType"), Blocks.redstone_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.QUARTZ_CROSS.ordinal(), "GraveType"), Blocks.quartz_block);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.OBSIDIAN_CROSS.ordinal(), "GraveType"), Blocks.obsidian);
        addCrossMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.ICE_CROSS.ordinal(), "GraveType"), Blocks.ice);
        // obelisks TODO
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.WOODEN_OBELISK.ordinal(), "GraveType"), Blocks.planks);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.SANDSTONE_OBELISK.ordinal(), "GraveType"), Blocks.sandstone);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.STONE_OBELISK.ordinal(), "GraveType"), Blocks.stone);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.MOSSY_OBELISK.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.IRON_OBELISK.ordinal(), "GraveType"), Blocks.iron_block);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.GOLDEN_OBELISK.ordinal(), "GraveType"), Blocks.gold_block);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.DIAMOND_OBELISK.ordinal(), "GraveType"), Blocks.diamond_block);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.EMERALD_OBELISK.ordinal(), "GraveType"), Blocks.emerald_block);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.LAPIS_OBELISK.ordinal(), "GraveType"), Blocks.lapis_block);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.REDSTONE_OBELISK.ordinal(), "GraveType"), Blocks.redstone_block);
        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.QUARTZ_OBELISK.ordinal(), "GraveType"), Blocks.quartz_block);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.OBSIDIAN_OBELISK.ordinal(), "GraveType"), Blocks.obsidian);
//        addObeliskMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.ICE_OBELISK.ordinal(), "GraveType"), Blocks.ice);
        // steve statues
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.WOODEN_STEVE_STATUE.ordinal(), "GraveType"), Blocks.planks);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.SANDSTONE_STEVE_STATUE.ordinal(), "GraveType"), Blocks.sandstone);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.STONE_STEVE_STATUE.ordinal(), "GraveType"), Blocks.stone);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.MOSSY_STEVE_STATUE.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.IRON_STEVE_STATUE.ordinal(), "GraveType"), Blocks.iron_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.GOLDEN_STEVE_STATUE.ordinal(), "GraveType"), Blocks.gold_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.DIAMOND_STEVE_STATUE.ordinal(), "GraveType"), Blocks.diamond_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.EMERALD_STEVE_STATUE.ordinal(), "GraveType"), Blocks.emerald_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.LAPIS_STEVE_STATUE.ordinal(), "GraveType"), Blocks.lapis_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.REDSTONE_STEVE_STATUE.ordinal(), "GraveType"), Blocks.redstone_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.QUARTZ_STEVE_STATUE.ordinal(), "GraveType"), Blocks.quartz_block);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.OBSIDIAN_STEVE_STATUE.ordinal(), "GraveType"), Blocks.obsidian);
        addSteveMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.ICE_STEVE_STATUE.ordinal(), "GraveType"), Blocks.ice);
        // villager statue
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.WOODEN_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.planks);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.SANDSTONE_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.sandstone);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.STONE_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.stone);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.MOSSY_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.IRON_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.iron_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.GOLDEN_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.gold_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.DIAMOND_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.diamond_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.EMERALD_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.emerald_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.LAPIS_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.lapis_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.REDSTONE_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.redstone_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.QUARTZ_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.quartz_block);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.OBSIDIAN_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.obsidian);
        addVillagerMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.ICE_VILLAGER_STATUE.ordinal(), "GraveType"), Blocks.ice);
        // angel statue
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.WOODEN_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.planks);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.SANDSTONE_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.sandstone);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.STONE_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.stone);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.MOSSY_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.IRON_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.iron_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.GOLDEN_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.gold_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.DIAMOND_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.diamond_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.EMERALD_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.emerald_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.LAPIS_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.lapis_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.REDSTONE_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.redstone_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.QUARTZ_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.quartz_block);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.OBSIDIAN_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.obsidian);
        addAngelMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.ICE_ANGEL_STATUE.ordinal(), "GraveType"), Blocks.ice);
        // dog statue
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.WOODEN_DOG_STATUE.ordinal(), "GraveType"), Blocks.planks);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.SANDSTONE_DOG_STATUE.ordinal(), "GraveType"), Blocks.sandstone);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.STONE_DOG_STATUE.ordinal(), "GraveType"), Blocks.stone);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.MOSSY_DOG_STATUE.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.IRON_DOG_STATUE.ordinal(), "GraveType"), Blocks.iron_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.GOLDEN_DOG_STATUE.ordinal(), "GraveType"), Blocks.gold_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.DIAMOND_DOG_STATUE.ordinal(), "GraveType"), Blocks.diamond_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.EMERALD_DOG_STATUE.ordinal(), "GraveType"), Blocks.emerald_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.LAPIS_DOG_STATUE.ordinal(), "GraveType"), Blocks.lapis_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.REDSTONE_DOG_STATUE.ordinal(), "GraveType"), Blocks.redstone_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.QUARTZ_DOG_STATUE.ordinal(), "GraveType"), Blocks.quartz_block);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.OBSIDIAN_DOG_STATUE.ordinal(), "GraveType"), Blocks.obsidian);
        addDogMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.ICE_DOG_STATUE.ordinal(), "GraveType"), Blocks.ice);
        // cat statue
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.WOODEN_CAT_STATUE.ordinal(), "GraveType"), Blocks.planks);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.SANDSTONE_CAT_STATUE.ordinal(), "GraveType"), Blocks.sandstone);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.STONE_CAT_STATUE.ordinal(), "GraveType"), Blocks.stone);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.MOSSY_CAT_STATUE.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.IRON_CAT_STATUE.ordinal(), "GraveType"), Blocks.iron_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.GOLDEN_CAT_STATUE.ordinal(), "GraveType"), Blocks.gold_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.DIAMOND_CAT_STATUE.ordinal(), "GraveType"), Blocks.diamond_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.EMERALD_CAT_STATUE.ordinal(), "GraveType"), Blocks.emerald_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.LAPIS_CAT_STATUE.ordinal(), "GraveType"), Blocks.lapis_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.REDSTONE_CAT_STATUE.ordinal(), "GraveType"), Blocks.redstone_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.QUARTZ_CAT_STATUE.ordinal(), "GraveType"), Blocks.quartz_block);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.OBSIDIAN_CAT_STATUE.ordinal(), "GraveType"), Blocks.obsidian);
        addCatMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.ICE_CAT_STATUE.ordinal(), "GraveType"), Blocks.ice);
        // creeper statue
        if (GraveStoneConfig.enableCreeperStatuesRecipes) {
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.WOODEN_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.planks);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.SANDSTONE_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.sandstone);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.STONE_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.stone);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.MOSSY_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.mossy_cobblestone);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.IRON_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.iron_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.GOLDEN_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.gold_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.DIAMOND_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.diamond_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.EMERALD_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.emerald_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.LAPIS_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.lapis_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.REDSTONE_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.redstone_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.QUARTZ_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.quartz_block);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.OBSIDIAN_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.obsidian);
            addCreeperMemorialRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.ICE_CREEPER_STATUE.ordinal(), "GraveType"), Blocks.ice);
        }
        // gibbets
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.GIBBET.ordinal(), "GraveType"), "ww ", "wr ", "wc ", 'w', Blocks.planks, 'r', Items.lead, 'c', GSItem.chisel);
        // stocks
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.STOCKS.ordinal(), "GraveType"), "wsw", "wcw", 'w', Blocks.planks, 's', Blocks.wooden_slab, 'c', GSItem.chisel);
        // burning stake
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.memorial, (byte) EnumMemorials.BURNING_STAKE.ordinal(), "GraveType"), " w ", "www", "hwh", 'w', Blocks.planks, 'h', Blocks.hay_block);


        // sword graves
        for (Item sword : GraveStoneHelper.swordsList) {
            GameRegistry.addRecipe(GraveStoneHelper.getSwordAsGrave(Item.getItemFromBlock(GSBlock.graveStone), new ItemStack(sword, 1)), "sc", 's', sword, 'c', GSItem.chisel);
        }

        // spawners
        if (GraveStoneConfig.enableBossSpawnerCraftingRecipe) {
            GameRegistry.addRecipe(new ItemStack(GSBlock.spawner, 1, EnumSpawner.WITHER_SPAWNER.ordinal()), "bcb", "cec", "cbc",
                    'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()),
                    'b', new ItemStack(Items.dye, 1, 15), 'e', Items.ender_eye);
        }
        if (GraveStoneConfig.enableSpawnerCraftingRecipe) {
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

        if (GraveStoneConfig.craftableNightStone) {
            GameRegistry.addRecipe(new ItemStack(GSBlock.trap, 1, 0), " p ", "rnr", " s ", 'n', Blocks.nether_brick, 'p', Blocks.stone_pressure_plate, 'r', Items.redstone, 's', Blocks.soul_sand);
        }
        if (GraveStoneConfig.craftableThunderStone) {
            GameRegistry.addRecipe(new ItemStack(GSBlock.trap, 1, 1), " p ", "rnr", " s ", 'n', Blocks.stonebrick, 'p', Blocks.stone_pressure_plate, 'r', Items.redstone, 's', Blocks.soul_sand);
        }

        // altar
        Item altarCrystal = (GraveStoneConfig.hardAltarRecipe) ? Items.nether_star : Items.diamond;
        GameRegistry.addRecipe(new ItemStack(GSBlock.altar), " h ", "sns", "bbb",
                'h', altarCrystal,
                's', new ItemStack(Items.skull, 1, 0),
                'n', new ItemStack(GSBlock.trap, 1, 0),
                'b', new ItemStack(GSBlock.boneBlock, 1, 0));
    }

    private static ItemStack getStackWithNTB(Block block, byte graveType, String ntbName) {
        ItemStack stack = new ItemStack(block, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte(ntbName, graveType);
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
