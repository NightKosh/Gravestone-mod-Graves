package gravestone.core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumMemorials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSTabs {
    private GSTabs() {
    }

    public static CreativeTabs gravesTab;
    public static CreativeTabs memorialsTab;
    public static CreativeTabs otherItemsTab;

    public static void registration() {
        gravesTab = new CreativeTabs("tabGraveStone") {
            @Override
            public ItemStack getIconItemStack() {
                ItemStack stack = new ItemStack(GSBlock.graveStone, 1, 0);
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte("GraveType", (byte) EnumGraves.STONE_VERTICAL_PLATE.ordinal());
                stack.setTagCompound(nbt);
                return stack;
            }

            @Override
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return Item.getItemFromBlock(GSBlock.graveStone);
            }
        };

        memorialsTab = new CreativeTabs("tabGraveStone") {
            @Override
            public ItemStack getIconItemStack() {
                ItemStack stack = new ItemStack(GSBlock.memorial, 1, 0);
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte("GraveType", (byte) EnumMemorials.STONE_CREEPER_STATUE.ordinal());
                stack.setTagCompound(nbt);
                return stack;
            }

            @Override
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return Item.getItemFromBlock(GSBlock.memorial);
            }
        };

        otherItemsTab = new CreativeTabs("tabGraveStone") {
            @Override
            public ItemStack getIconItemStack() {
                return new ItemStack(GSBlock.skullCandle, 1, 1);
            }

            @Override
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return Item.getItemFromBlock(GSBlock.skullCandle);
            }
        };
    }
}
