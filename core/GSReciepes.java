package gravestone.core;

import cpw.mods.fml.common.registry.GameRegistry;
import gravestone.block.GraveStoneHelper;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumSkullCandle;
import gravestone.block.enums.EnumSpawner;
import gravestone.config.GraveStoneConfig;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSReciepes {

    private GSReciepes() {
    }

    public static void registration() {
        // chisel reciep
        GameRegistry.addRecipe(new ItemStack(GSItem.chisel), "   ", "s  ", " i ", 's', Items.stick, 'i', Items.iron_ingot);
        // bone blocks
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneBlock, 1, 0), "xxx", "xxx", "xxx", 'x', Items.bone);
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneBlock, 9, 1), "xxx", "xsx", "xxx", 'x', GSBlock.boneBlock, 's', new ItemStack(Items.skull, 1, 0));
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneBlock), "x", "x", 'x', GSBlock.boneSlab);
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneSlab, 6), "xxx", 'x', GSBlock.boneBlock);
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneStairs, 4), "x  ", "xx ", "xxx", 'x', GSBlock.boneBlock);
        // Bone block to bones
        GameRegistry.addRecipe(new ItemStack(Items.bone, 9), "x", 'x', GSBlock.boneBlock);

        // graves
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.STONE_VERTICAL_PLATE.ordinal(), "GraveType"), "sc", 's', Blocks.stone, 'c', GSItem.chisel);
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.CROSS.ordinal(), "GraveType"), " c", "s ", 's', Blocks.stone, 'c', GSItem.chisel);
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.HORISONTAL_PLATE.ordinal(), "GraveType"), "c", "s", 's', Blocks.stone, 'c', GSItem.chisel);
        // pet graves
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.DOG_STATUE.ordinal(), "GraveType"), " c", "  ", "s ", 's', Blocks.stone, 'c', GSItem.chisel);
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.graveStone, (byte) EnumGraves.CAT_STATUE.ordinal(), "GraveType"), " c", "  ", " s", 's', Blocks.stone, 'c', GSItem.chisel);
        // sword graves
        GameRegistry.addRecipe(getSwordGravestone(GSBlock.graveStone, (byte) EnumGraves.WOODEN_SWORD.ordinal()), "sc", 's', Items.wooden_sword, 'c', GSItem.chisel);
        GameRegistry.addRecipe(getSwordGravestone(GSBlock.graveStone, (byte) EnumGraves.STONE_SWORD.ordinal()), "sc", 's', Items.stone_sword, 'c', GSItem.chisel);
        GameRegistry.addRecipe(getSwordGravestone(GSBlock.graveStone, (byte) EnumGraves.IRON_SWORD.ordinal()), "sc", 's', Items.iron_sword, 'c', GSItem.chisel);
        GameRegistry.addRecipe(getSwordGravestone(GSBlock.graveStone, (byte) EnumGraves.GOLDEN_SWORD.ordinal()), "sc", 's', Items.golden_sword, 'c', GSItem.chisel);
        GameRegistry.addRecipe(getSwordGravestone(GSBlock.graveStone, (byte) EnumGraves.DIAMOND_SWORD.ordinal()), "sc", 's', Items.diamond_sword, 'c', GSItem.chisel);

        // spawners
        if (GraveStoneConfig.enableBossSpawnerCraftingRecipe) {
            GameRegistry.addRecipe(new ItemStack(GSBlock.spawner, 1, EnumSpawner.WITHER_SPAWNER.ordinal()), "bcb", "cec", "cbc", 'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()),
                    'b', new ItemStack(Items.dye, 1, 15), 'e', Items.ender_eye);
        }
        if (GraveStoneConfig.enableSpawnerCraftingRecipe) {
            GameRegistry.addRecipe(new ItemStack(GSBlock.spawner, 1, EnumSpawner.SKELETON_SPAWNER.ordinal()), "bcb", "cec", "cbc", 'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()),
                    'b', new ItemStack(Items.dye, 1, 15), 'e', Items.ender_eye);
            GameRegistry.addRecipe(new ItemStack(GSBlock.spawner, 1, EnumSpawner.ZOMBIE_SPAWNER.ordinal()), "bcb", "cec", "cbc", 'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()),
                    'b', new ItemStack(Items.dye, 1, 15), 'e', Items.ender_eye);
        }

        // candles
        GameRegistry.addRecipe(new ItemStack(GSBlock.candle, 1, 0), "t", "m", "s", 't', Items.string, 'm', new ItemStack(Items.dye, 1, 15), 's', Items.slime_ball);
        addSkullCandleReciepes(new ItemStack(GSBlock.candle, 1, 0));

        GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 0), "c", 'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()));
        GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 1), "c", 'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()));
        GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 2), "c", 'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()));

        // altar
        GameRegistry.addRecipe(new ItemStack(GSBlock.altar), " h ", "sns", "ooo",
                'h', Items.nether_star, 's', new ItemStack(Items.skull, 1, 0), 'n', new ItemStack(GSBlock.trap, 1, 0), 'o', Blocks.obsidian);
    }

    private static ItemStack getStackWithNTB(Block block, byte graveType, String ntbName) {
        ItemStack stack = new ItemStack(block, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte(ntbName, graveType);
        stack.setTagCompound(nbt);
        return stack;
    }

    private static ItemStack getSwordGravestone(Block block, byte graveType) {
        ItemStack stack = new ItemStack(block, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte("GraveType", graveType);
        nbt.setByte("SwordType", GraveStoneHelper.graveTypeToSwordType(graveType));
        stack.setTagCompound(nbt);
        return stack;
    }

    public static void addSkullCandleReciepes(ItemStack candle) {
        GameRegistry.addRecipe(new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.skull, 1, 0), 'c', candle);
        GameRegistry.addRecipe(new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.skull, 1, 1), 'c', candle);
        GameRegistry.addRecipe(new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.skull, 1, 2), 'c', candle);
    }
}
