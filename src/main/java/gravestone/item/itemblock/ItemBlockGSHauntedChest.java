/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gravestone.item.itemblock;

import gravestone.block.enums.EnumHauntedChest;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGSHauntedChest extends ItemBlock {

    public ItemBlockGSHauntedChest(Block block) {
        super(block);
        setHasSubtypes(true);
        setUnlocalizedName("Haunted chest");
    }

    @Override
    public int getMetadata(int damageValue) {
        return 0;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("ChestType")) {
            return EnumHauntedChest.getById(itemStack.getTagCompound().getByte("ChestType")).getLocalizedName();
        } else {
            return EnumHauntedChest.getById((byte) 0).getLocalizedName();
        }
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }
}
