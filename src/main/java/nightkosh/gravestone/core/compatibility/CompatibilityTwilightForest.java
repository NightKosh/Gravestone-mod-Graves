package nightkosh.gravestone.core.compatibility;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import nightkosh.gravestone.config.Config;

import java.util.Iterator;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author DA3DSOUL
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityTwilightForest implements ICompatibility {

    public static final String MOD_ID = "twilightforest";

    private static final CompatibilityTwilightForest INSTANCE = new CompatibilityTwilightForest();

    /**
     * Twilight Forest checks the players inventory on death, so we can't wipe it all.
     * Only vanilla items will be supported otherwise some changes required.
     */
    public static boolean handleCharmsOfKeeping(List<ItemStack> items, Player player) {
        if (INSTANCE.isModLoaded(MOD_ID) && Config.enableTwilightForestKeeping) {
            int[] keepingData = checkForCharmOfKeeping(player);

            if (keepingData[0] > 0) {
                Iterator<ItemStack> it = items.iterator();
                while (it.hasNext()) {
                    ItemStack item = it.next();
                    if (item != null && !item.isEmpty() && item.hasTagCompound()) {
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
                }
                for (ItemStack item : items) {
                    if (item != null && !item.isEmpty() && item.hasTagCompound()) {
                        byte slot = item.getTagCompound().getByte("slot");
                        if (slot == keepingData[1]) {
                            //make sure there is a charm of keeping left for TwilightForest to detect
                            player.inventory.setInventorySlotContents(slot, item.splitStack(1));
                        } else {
                            player.inventory.setInventorySlotContents(slot, ItemStack.EMPTY);
                        }
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
        if (INSTANCE.isModLoaded(MOD_ID) && Config.enableTwilightForestKeeping) {
            for (int i = 0; i < items.size(); i++) {
                ItemStack item = items.get(i);
                if (item == null) {
                    continue;
                }
                NBTTagCompound nbt = item.getTagCompound();
                if (nbt == null) {
                    nbt = new NBTTagCompound();
                    if (nbt.hasKey("slot")) {
                        continue;
                    }
                }
                nbt.setByte("slot", (byte) i);
                item.setTagCompound(nbt);
            }

        }
    }

    /**
     * remove the slot tag to avoid stacking issues, items.size() after the size was modified
     */
    public static void removeSlotTags(List<ItemStack> items) {
        if (INSTANCE.isModLoaded(MOD_ID) && Config.enableTwilightForestKeeping && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
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

    private static int[] checkForCharmOfKeeping(Player player) {
        byte max = 0;
        int slot = -1;
        for (int i = 0; i < player.inventory.mainInventory.size(); i++) {
            ItemStack stack = player.inventory.mainInventory.get(i);
            if (stack == null) continue;
            //TODO
//            if (stack.getItem() == twilightforest.item.TFItems.charmOfKeeping1) {
//                if (max < 1) {
//                    max = 1;
//                    slot = i;
//                }
//            } else if (stack.getItem() == twilightforest.item.TFItems.charmOfKeeping2) {
//                if (max < 2) {
//                    max = 2;
//                    slot = i;
//                }
//            } else if (stack.getItem() == twilightforest.item.TFItems.charmOfKeeping3) {
//                if (max < 3) {
//                    max = 3;
//                    slot = i;
//                }
//            }
        }
        return new int[]{max, slot};
    }

}