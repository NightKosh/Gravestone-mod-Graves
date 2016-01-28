package nightkosh.gravestone.core;

import nightkosh.gravestone.block.enums.EnumGraves;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Tabs {
    public static CreativeTabs gravesTab;

    public static void registration() {
        gravesTab = new CreativeTabs("tabGSGraveStone") {
            @Override
            public ItemStack getIconItemStack() {
                ItemStack stack = new ItemStack(GSBlock.graveStone, 1, EnumGraves.STONE_VERTICAL_PLATE.ordinal());
                NBTTagCompound nbt = new NBTTagCompound();
                stack.setTagCompound(nbt);
                return stack;
            }

            @Override
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return Item.getItemFromBlock(GSBlock.graveStone);
            }
        };
    }
}
