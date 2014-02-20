package gravestone.core.compatibility;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityTheCampingMod {

    protected static boolean isTheCampingModInstalled = false;
    
    private GSCompatibilityTheCampingMod() {
    }

    public static void addItems(List<ItemStack> items, EntityPlayer player) {
        if (isInstalled()) {
            NBTTagCompound tag = player.getEntityData().getCompoundTag("campInv");
            NBTTagList inventory = tag.getTagList("Items");
            for (int i = 0; i < inventory.tagCount(); ++i) {
                NBTTagCompound Slots = (NBTTagCompound) inventory.tagAt(i);
                Slots.getByte("Slot");
                items.add(ItemStack.loadItemStackFromNBT(Slots));
            }
        }
    }
//
//    public static void addItems(List<ItemStack> items, EntityPlayer player) {
//        addItems(items, player, "backpack");
//        addItems(items, player, "tool");
//    }
//
//    private static void addItems(List<ItemStack> items, EntityPlayer player, String tagName) {
//        NBTTagCompound tag = player.getEntityData().getCompoundTag("campInv").getCompoundTag(tagName);
//        NBTTagList inventory = tag.getTagList("Items");
//        for (int i = 0; i < inventory.tagCount(); ++i) {
//            NBTTagCompound Slots = (NBTTagCompound) inventory.tagAt(i);
//            Slots.getByte("Slot");
//            items.add(ItemStack.loadItemStackFromNBT(Slots));
//        }
//    }
    
    
    public static boolean isInstalled() {
        return isTheCampingModInstalled;
    }
}
