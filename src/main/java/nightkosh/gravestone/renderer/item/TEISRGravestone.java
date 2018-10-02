package nightkosh.gravestone.renderer.item;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.renderer.tileentity.TileEntityGraveStoneRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TEISRGravestone extends TileEntityItemStackRenderer {

    @Override
    public void renderByItem(ItemStack stack) {
        EnumGraves graveType = EnumGraves.getById(stack.getItemDamage());
        boolean isEnchanted = false;
        boolean isMossy = false;
        boolean isSwordGrave = false;
        ItemStack sword = null;

        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            isEnchanted = nbt.hasKey("Enchanted") && nbt.getBoolean("Enchanted");
            isMossy = nbt.hasKey("Mossy") && nbt.getBoolean("Mossy");
            if (nbt.hasKey("Sword")) {
                isSwordGrave = true;
                sword = new ItemStack(nbt.getCompoundTag("Sword"));
            }
        }

        TileEntityGraveStoneRenderer.instance.renderGraveAsItem(graveType, isEnchanted, isMossy, isSwordGrave, sword);
    }
}
