package nightkosh.gravestone.core.compatibility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.api.grave_items.IPlayerItems;
import nightkosh.gravestone.config.Config;

import java.util.Iterator;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityTinkerConstruct implements ICompatibility {

    public static final String MOD_ID = "tconstruct";

    protected CompatibilityTinkerConstruct() {
        if (isModLoaded(MOD_ID) && Config.enableTconstructSoulbound) {
            GraveStoneAPI.graveGenerationAtDeath.addPlayerItemsHandler(new IPlayerItems() {

                @Override
                public List<ItemStack> addItems(EntityPlayer player, DamageSource source) {
                    return null;
                }

                @Override
                public void getItems(EntityPlayer player, DamageSource source, List<ItemStack> items) {
                    Iterator<ItemStack> it = items.iterator();
                    while (it.hasNext()) {
                        ItemStack stack = it.next();
                        if (stack != null && hasSoulbound(stack)) {
                            player.inventory.addItemStackToInventory(stack.copy());
                            it.remove();
                        }
                    }
                }
            });
        }
    }

    private static boolean hasSoulbound(ItemStack stack) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            if (nbt.hasKey("Modifiers")) {
                NBTTagList nbtList = nbt.getTagList("Modifiers", 10);
                for (int i = 0; i < nbtList.tagCount(); i++) {
                    NBTTagCompound tag = nbtList.getCompoundTagAt(i);
                    if (tag.hasKey("identifier") && tag.getString("identifier").equals("soulbound")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
