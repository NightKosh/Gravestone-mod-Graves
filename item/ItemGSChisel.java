package gravestone.item;

import gravestone.config.GraveStoneConfig;
import gravestone.ModGraveStone;
import gravestone.core.Resources;
import gravestone.tileentity.TileEntityGSGrave;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemGSChisel extends ItemTool {

    public ItemGSChisel(int id) {
        super(id, 1, EnumToolMaterial.IRON, new Block[0]);
        setMaxStackSize(1);
        setCreativeTab(ModGraveStone.creativeTab);
        setUnlocalizedName("chisel");
        setMaxDamage(50);
        MinecraftForge.setToolClass(this, "chisel", 1);
        this.setTextureName(Resources.CHISEL);
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
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (world.getBlockId(x, y, z) == GraveStoneConfig.graveStoneID) {
            return setGraveText(stack, player, world, x, y, z, false);
        } else if (world.getBlockId(x, y, z) == GraveStoneConfig.memorialID) {
            return setGraveText(stack, player, world, x, y, z, true);
        }

        return false;
    }

    private boolean setGraveText(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, boolean isMemorial) {
        TileEntityGSGrave tileEntity = (TileEntityGSGrave) world.getBlockTileEntity(x, y, z);

        if (tileEntity != null && tileEntity.isEditable() && tileEntity.getDeathTextComponent().getDeathText().length() == 0) {
            player.openGui(ModGraveStone.instance, 0, world, x, y, z);

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
