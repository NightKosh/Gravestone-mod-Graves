
package gravestone.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.block.enums.EnumSkullCandle;
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
public class ItemBlockGSSkullCandle  extends ItemBlock {

    public ItemBlockGSSkullCandle(int id) {
        super(id);
        setHasSubtypes(true);
        setUnlocalizedName("Skull Candle");
    }

    @Override
    public int getMetadata(int damageValue) {
        return 0;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        EnumSkullCandle skullCandleType;

        if (itemStack.stackTagCompound != null && itemStack.stackTagCompound.hasKey("SkullType")) {
            skullCandleType = EnumSkullCandle.getByID(itemStack.stackTagCompound.getByte("SkullType"));
        } else {
            skullCandleType = EnumSkullCandle.getByID(0);
        }

        return getUnlocalizedName() + "." + skullCandleType.getName();
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }

    /**
     * Returns true if the given ItemBlock can be placed on the given side of
     * the given block position.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean canPlaceItemBlockOnSide(World world, int x, int y, int z, int side, EntityPlayer player, ItemStack stack) {
        return y > 0 && world.getBlockMaterial(x, y - 1, z).isSolid();
    }

}
