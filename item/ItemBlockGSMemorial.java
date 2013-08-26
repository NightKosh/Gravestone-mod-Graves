package GraveStone.item;

import GraveStone.block.EnumMemorials;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
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
public class ItemBlockGSMemorial extends ItemBlock {

    public ItemBlockGSMemorial(int id) {
        super(id);
        setHasSubtypes(true);
        setUnlocalizedName("Memorial");
    }

    @Override
    public int getMetadata(int damageValue) {
        return 0;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        EnumMemorials memorialType;

        if (itemStack.stackTagCompound != null && itemStack.stackTagCompound.hasKey("GraveType")) {
            memorialType = EnumMemorials.getByID(itemStack.stackTagCompound.getByte("GraveType"));
        } else {
            memorialType = EnumMemorials.getByID(0);
        }

        return getUnlocalizedName() + "." + memorialType.getName();
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }

        if (stack.stackTagCompound.hasKey("DeathText") && stack.stackTagCompound.getString("DeathText").length() != 0) {
            list.add(stack.stackTagCompound.getString("DeathText"));
        }
    }

    /**
     * Returns true if the given ItemBlock can be placed on the given side of
     * the given block position.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean canPlaceItemBlockOnSide(World world, int x, int y, int z, int side, EntityPlayer player, ItemStack stack) {
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("GraveType")) {
            switch (side) {
                case 0:
                    return false;

                case 1:
                    y++;
                    break;

                case 2:
                    z--;
                    break;

                case 3:
                    z++;
                    break;

                case 4:
                    x--;
                    break;

                case 5:
                    x++;
                    break;
            }

            byte memorialType = stack.stackTagCompound.getByte("GraveType");
            byte maxY;
            byte maxX = 1;
            byte maxZ = 1;
            byte startX = 0;
            byte startZ = 0;

            switch (memorialType) {
                case 0:
                case 1:
                    maxY = 5;
                    maxX = 2;
                    maxZ = 2;
                    startX = -1;
                    startZ = -1;
                    break;

                case 5:
                case 6:
                    maxY = 2;
                    break;

                default:
                    maxY = 3;
                    break;
            }

            for (byte shiftY = 0; shiftY < maxY; shiftY++) {
                for (byte shiftZ = startZ; shiftZ < maxZ; shiftZ++) {
                    for (byte shiftX = startX; shiftX < maxX; shiftX++) {
                        if (world.getBlockId(x + shiftX, y + shiftY, z + shiftZ) != 0) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
