package gravestone.core.compatibility;

import java.util.LinkedList;
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
            items.addAll(getItems(player));
        }
    }

    private static List<ItemStack> getItems(EntityPlayer player) {
        List<ItemStack> items = new LinkedList<ItemStack>();

        items.addAll(addItems(player, "backpack"));
        items.addAll(addItems(player, "tool"));
        
        return items;
    }

    private static List<ItemStack> addItems(EntityPlayer player, String tagName) {
        List<ItemStack> items = new LinkedList<ItemStack>();
        NBTTagCompound tag = player.getEntityData().getCompoundTag("campInv").getCompoundTag(tagName);
        NBTTagList inventory = tag.getTagList("Items");
        for (int i = 0; i < inventory.tagCount(); ++i) {
            NBTTagCompound Slots = (NBTTagCompound) inventory.tagAt(i);
            Slots.getByte("Slot");
            items.add(ItemStack.loadItemStackFromNBT(Slots).copy());
        }
        player.getEntityData().getCompoundTag("campInv").setCompoundTag(tagName, new NBTTagCompound());
        return items;
    }

    public static boolean isInstalled() {
        return isTheCampingModInstalled;
    }
}
