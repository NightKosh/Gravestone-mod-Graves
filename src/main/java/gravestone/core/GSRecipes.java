package gravestone.core;

import gravestone.block.GraveStoneHelper;
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


        // memorials
        // gibbets
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.GIBBET.ordinal(), "Type"), "ww ", "wr ", "wc ", 'w', Blocks.planks, 'r', Items.lead, 'c', GSItem.chisel);
        // stocks
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.STOCKS.ordinal(), "Type"), "wsw", "wcw", 'w', Blocks.planks, 's', Blocks.wooden_slab, 'c', GSItem.chisel);
        // burning stake
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.memorial, EnumMemorials.BURNING_STAKE.ordinal(), "Type"), " w ", "www", "hwh", 'w', Blocks.planks, 'h', Blocks.hay_block);


        // sword graves TODO remove
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

    public static void addSkullCandleReciepes(ItemStack candle) {
        GameRegistry.addRecipe(new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.skull, 1, 0), 'c', candle);
        GameRegistry.addRecipe(new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.skull, 1, 1), 'c', candle);
        GameRegistry.addRecipe(new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.skull, 1, 2), 'c', candle);
    }

    public static void addForestryBackpack(ItemStack backpack, Item item) {
        GameRegistry.addRecipe(backpack, "sws", "ici", "sws", 'w', Blocks.wool, 'i', item, 's', Items.string, 'c', Blocks.chest);
    }
}
