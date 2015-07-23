package gravestone.core;

import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumMemorials;
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
public class GSTabs {
    private GSTabs() {
    }

    public static CreativeTabs gravesTab;
    public static CreativeTabs memorialsTab;
    public static CreativeTabs otherItemsTab;

    public static void registration() {
        gravesTab = new CreativeTabs("tabGSGraveStone") {
            @Override
            public ItemStack getIconItemStack() {
                ItemStack stack = new ItemStack(GSBlock.graveStone, 1, 0);
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setInteger("Type", EnumGraves.STONE_VERTICAL_PLATE.ordinal());
                stack.setTagCompound(nbt);
                return stack;
            }

            @Override
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return Item.getItemFromBlock(GSBlock.graveStone);
            }
        };

        memorialsTab = new CreativeTabs("tabGSMemorials") {
            @Override
            public ItemStack getIconItemStack() {
                ItemStack stack = new ItemStack(GSBlock.memorial, 1, 0);
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setInteger("Type", EnumMemorials.STONE_CREEPER_STATUE.ordinal());
                stack.setTagCompound(nbt);
                return stack;
            }

            @Override
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return Item.getItemFromBlock(GSBlock.memorial);
            }
        };

        otherItemsTab = new CreativeTabs("tabGSOther") {
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
