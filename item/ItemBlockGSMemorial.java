package gravestone.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import gravestone.block.enums.EnumMemorials;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGSMemorial extends ItemBlock {

    public ItemBlockGSMemorial(Block block) {
        super(block);
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

        String deathText = "";
        if (stack.stackTagCompound.hasKey("DeathText") && stack.stackTagCompound.getString("DeathText").length() != 0) {
            deathText = stack.stackTagCompound.getString("DeathText");
        }

        if (stack.stackTagCompound.hasKey("isLocalized") && stack.stackTagCompound.getBoolean("isLocalized")) {
            if (stack.stackTagCompound.hasKey("name")) {
                String name = stack.stackTagCompound.getString("name");
                String killerName = ModGraveStone.proxy.getLocalizedEntityName(stack.stackTagCompound.getString("KillerName"));
                if (killerName.length() == 0) {
                    list.add(new ChatComponentTranslation(deathText, new Object[]{name}).toString());
                } else {
                    list.add(new ChatComponentTranslation(deathText, new Object[]{name, killerName.toLowerCase()}).toString());
                }
            }
        } else {
            list.add(deathText);
        }
    }

    /**
     * Returns true if the given ItemBlock can be placed on the given side of
     * the given block position.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
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

            EnumMemorials memorialType = EnumMemorials.getByID(stack.stackTagCompound.getByte("GraveType"));
            byte maxY;
            byte maxX = 1;
            byte maxZ = 1;
            byte startX = 0;
            byte startZ = 0;

            switch (memorialType) {
                case STONE_CROSS:
                case OBELISK:
                    maxY = 5;
                    maxX = 2;
                    maxZ = 2;
                    startX = -1;
                    startZ = -1;
                    break;
                case DOG_STATUE:
                case CAT_STATUE:
                    maxY = 2;
                    break;
                default:
                    maxY = 3;
                    break;
            }

            for (byte shiftY = 0; shiftY < maxY; shiftY++) {
                for (byte shiftZ = startZ; shiftZ < maxZ; shiftZ++) {
                    for (byte shiftX = startX; shiftX < maxX; shiftX++) {
                        if (world.getBlock(x + shiftX, y + shiftY, z + shiftZ).equals(Blocks.air)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
