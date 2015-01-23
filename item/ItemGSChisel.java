package gravestone.item;

import gravestone.ModGraveStone;
import gravestone.core.GSBlock;
import gravestone.core.GSTabs;
import gravestone.tileentity.TileEntityGSGrave;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemGSChisel extends ItemTool {

    public ItemGSChisel() {
        super(1, ToolMaterial.IRON, null);
        setMaxStackSize(1);
        setCreativeTab(GSTabs.otherItemsTab);
        setUnlocalizedName("gravestone chisel");
        setMaxDamage(50);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }

    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return True if something happen and
     * false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            if (world.getBlockState(pos).getBlock().equals(GSBlock.graveStone)) {
                return setGraveText(stack, player, world, pos, false);
            } else if (world.getBlockState(pos).getBlock().equals(GSBlock.memorial)) {
                return setGraveText(stack, player, world, pos, true);
            }
        }

        return false;
    }

    private boolean setGraveText(ItemStack stack, EntityPlayer player, World world, BlockPos pos, boolean isMemorial) {
        TileEntityGSGrave tileEntity = (TileEntityGSGrave) world.getTileEntity(pos);

        if (tileEntity != null && tileEntity.isEditable() && tileEntity.getDeathTextComponent().getDeathText().length() == 0) {
            ModGraveStone.proxy.openGraveGui(tileEntity);
            if (isMemorial) {
                stack.damageItem(5, player);
            } else {
                stack.damageItem(2, player);
            }

            return true;
        }

        return false;
    }
}
