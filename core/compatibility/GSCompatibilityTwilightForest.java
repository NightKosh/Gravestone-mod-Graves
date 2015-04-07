package gravestone.core.compatibility;

import gravestone.config.GraveStoneConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Iterator;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author DA3DSOUL
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityTwilightForest {

    protected static boolean isInstalled = false;

    private GSCompatibilityTwilightForest() {
    }

    /**
     * Twilight Forest checks the players inventory on death, so we can't wipe it all.
     * Only vanilla items will be supported otherwise some changes required.
     */
    public static boolean handleCharmsOfKeeping(List<ItemStack> items, EntityPlayer player) {
        if (isInstalled() && GraveStoneConfig.enableTwilightForestKeeping) {
            byte[] keepingData = checkForCharmOfKeeping(player);

            if (keepingData[0] > 0) {
                Iterator<ItemStack> it = items.iterator();
                while (it.hasNext()) {
                    ItemStack item = it.next();
                    if (item == null || !item.hasTagCompound()) {
                        continue;
                    }
                    byte slot = item.getTagCompound().getByte("slot");
                    if (keepingData[0] == 1) {
                        if (slot == player.inventory.currentItem) {
                            it.remove();
                            break;
                        }
                    } else if (keepingData[0] == 2) {
                        if (slot < 9) {
                            it.remove();
                        }
                    } else if (keepingData[0] == 3) {
                        if (slot < 40) {
                            it.remove();
                        }
                    }
                }
                for (ItemStack item : items) {
                    if (item == null || !item.hasTagCompound()) {
                        continue;
                    }
                    byte slot = item.getTagCompound().getByte("slot");
                    if (slot == keepingData[1]) {
                        //make sure there is a charm of keeping left for TwilightForest to detect
                        player.inventory.setInventorySlotContents(slot, item.splitStack(1));
                        if (item.stackSize <= 0) {
                            item = null;
                        }
                    } else {
                        player.inventory.setInventorySlotContents(slot, null);
                    }
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Ensure the slot tag is set. Mainly for TwilightForest, but could have other uses
     */
    public static void addSlotTags(List<ItemStack> items) {
        if (isInstalled() && GraveStoneConfig.enableTwilightForestKeeping) {
            for (byte i = 0; i < GraveStoneConfig.graveItemsCount; i++) {
                ItemStack item = items.get(i);
                if (item == null) continue;
                NBTTagCompound nbt = item.getTagCompound();
                if (nbt == null) {
                    nbt = new NBTTagCompound();
                    if (nbt.hasKey("slot")) {
                        continue;
                    }
                }
                nbt.setByte("slot", i);
                item.setTagCompound(nbt);
            }

        }
    }

    /**
     * remove the slot tag to avoid stacking issues, items.size() after the size was modified
     */
    public static void removeSlotTags(List<ItemStack> items) {
        if (isInstalled() && GraveStoneConfig.enableTwilightForestKeeping && items.size() > 0) {
            for (byte i = 0; i < items.size(); i++) {
                ItemStack item = items.get(i);
                if (item == null || !item.hasTagCompound()) {
                    continue;
                }
                NBTTagCompound nbt = item.getTagCompound();
                nbt.removeTag("slot");
                if (nbt.hasNoTags()) {
                    item.setTagCompound(null);
                }
            }
        }
    }

    private static byte[] checkForCharmOfKeeping(EntityPlayer player) {
        byte max = 0;
        byte slot = -1;
        for (byte i = 0; i < player.inventory.mainInventory.length; i++) {
            ItemStack stack = player.inventory.mainInventory[i];
            if (stack == null) continue;
            if (stack.getItem() == twilightforest.item.TFItems.charmOfKeeping1) {
                if (max < 1) {
                    max = 1;
                    slot = i;
                }
            } else if (stack.getItem() == twilightforest.item.TFItems.charmOfKeeping2) {
                if (max < 2) {
                    max = 2;
                    slot = i;
                }
            } else if (stack.getItem() == twilightforest.item.TFItems.charmOfKeeping3) {
                if (max < 3) {
                    max = 3;
                    slot = i;
                }
            }
        }
        return new byte[]{max, slot};
    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}