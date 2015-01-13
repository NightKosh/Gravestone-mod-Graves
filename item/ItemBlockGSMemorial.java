package gravestone.item;

import gravestone.ModGraveStone;
import gravestone.block.enums.EnumMemorials;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("GraveType")) {
            memorialType = EnumMemorials.getByID(itemStack.getTagCompound().getByte("GraveType"));
        } else {
            memorialType = EnumMemorials.getByID(0);
        }

        return getUnlocalizedName() + "." + memorialType.getName();
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }

        String deathText = "";
        if (stack.getTagCompound().hasKey("DeathText") && stack.getTagCompound().getString("DeathText").length() != 0) {
            deathText = stack.getTagCompound().getString("DeathText");
        }

        if (stack.getTagCompound().hasKey("isLocalized") && stack.getTagCompound().getBoolean("isLocalized")) {
            if (stack.getTagCompound().hasKey("name")) {
                String name = ModGraveStone.proxy.getLocalizedEntityName(stack.getTagCompound().getString("name"));
                String killerName = ModGraveStone.proxy.getLocalizedEntityName(stack.getTagCompound().getString("KillerName"));
                if (killerName.length() == 0) {
                    list.add(new ChatComponentTranslation(deathText, new Object[]{name}).getFormattedText());
                } else {
                    list.add(new ChatComponentTranslation(deathText, new Object[]{name, killerName.toLowerCase()}).getFormattedText());
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
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("GraveType")) {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            switch (side) {
                case DOWN:
                    return false;
                case UP:
                    y++;
                    break;
                case NORTH:
                    z--;
                    break;
                case SOUTH:
                    z++;
                    break;
                case WEST:
                    x--;
                    break;
                case EAST:
                    x++;
                    break;
            }

            EnumMemorials memorialType = EnumMemorials.getByID(stack.getTagCompound().getByte("GraveType"));
            byte maxY;
            byte maxX = 1;
            byte maxZ = 1;
            byte startX = 0;
            byte startZ = 0;

            switch (memorialType) {
                case STONE_CROSS:
                case QUARTZ_OBELISK:
                    maxY = 5;
                    maxX = 2;
                    maxZ = 2;
                    startX = -1;
                    startZ = -1;
                    break;
                case STONE_DOG_STATUE:
                case STONE_CAT_STATUE:
                    maxY = 2;
                    break;
                default:
                    maxY = 3;
                    break;
            }

            int airBlockId = Block.getIdFromBlock(Blocks.air);
            for (byte shiftY = 0; shiftY < maxY; shiftY++) {
                for (byte shiftZ = startZ; shiftZ < maxZ; shiftZ++) {
                    for (byte shiftX = startX; shiftX < maxX; shiftX++) {
                        if (Block.getIdFromBlock(world.getBlockState(new BlockPos(x + shiftX, y + shiftY, z + shiftZ)).getBlock()) != airBlockId) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
