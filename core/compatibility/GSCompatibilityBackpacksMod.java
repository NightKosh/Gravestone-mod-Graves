package gravestone.core.compatibility;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityBackpacksMod {

    protected static boolean isInstalled = false;

    private GSCompatibilityBackpacksMod() {
    }

    public static void addItems(List<ItemStack> items, EntityPlayer player) {
        if (isLoaded()) {
            NBTTagCompound playerData = player.getEntityData();
            ItemStack backpack;
            if (playerData.hasKey("backpack")) {
                backpack = ItemStack.loadItemStackFromNBT(playerData.getCompoundTag("backpack")).copy();
                playerData.setTag("backpack", new NBTTagCompound());
                items.add(backpack);

                System.out.println("backpack !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                //System.out.println(backpack);
            }
        }
    }

    public static boolean isLoaded() {
        return isInstalled;
    }
}
