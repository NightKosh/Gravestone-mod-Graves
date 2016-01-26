package nightkosh.gravestone.core.compatibility;

import nightkosh.gravestone.ModGraveStone;
import nightkosh.gravestone.config.GSConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.LinkedList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityTheCampingMod implements ICompatibility {

    public static final String MOD_ID = "camping";

    protected GSCompatibilityTheCampingMod() {
        if (isModLoaded(MOD_ID) && GSConfig.storeTheCampingModItems) {
            ModGraveStone.apiGraveGeneration.addPlayerItemsHandler((player, source) -> {
                List<ItemStack> items = new LinkedList<>();
                NBTTagCompound tag = player.getEntityData().getCompoundTag("campInv");
                NBTTagList inventory = tag.getTagList("Items", 10);
                for (int i = 0; i < inventory.tagCount(); ++i) {
                    NBTTagCompound Slots = inventory.getCompoundTagAt(i);
                    Slots.getByte("Slot");
                    items.add(ItemStack.loadItemStackFromNBT(Slots).copy());
                }
                player.getEntityData().setTag("campInv", new NBTTagCompound());
                return items;
            });
        }
    }
}
