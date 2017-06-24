package nightkosh.gravestone.core.compatibility;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.config.Config;

import java.util.LinkedList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityTheCampingMod implements ICompatibility {

    public static final String MOD_ID = "camping";

    protected CompatibilityTheCampingMod() {
        if (isModLoaded(MOD_ID) && Config.storeTheCampingModItems) {
            GraveStoneAPI.graveGenerationAtDeath.addPlayerItemsHandler((player, source) -> {
                List<ItemStack> items = new LinkedList<>();
                NBTTagCompound tag = player.getEntityData().getCompoundTag("campInv");
                NBTTagList inventory = tag.getTagList("Items", 10);
                for (int i = 0; i < inventory.tagCount(); ++i) {
                    NBTTagCompound slots = inventory.getCompoundTagAt(i);
                    slots.getByte("Slot");
                    items.add(new ItemStack(slots).copy());
                }
                player.getEntityData().setTag("campInv", new NBTTagCompound());
                return items;
            });
        }
    }
}
