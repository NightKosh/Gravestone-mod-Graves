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
                String name = ModGraveStone.proxy.getLocalizedEntityName(stack.stackTagCompound.getString("name"));
                String killerName = ModGraveStone.proxy.getLocalizedEntityName(stack.stackTagCompound.getString("KillerName"));
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
    public boolean func_150936_a(World world, int x, int y, int z, int side, EntityPlayer player, ItemStack stack) {
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
                case WOODEN_CROSS:
                case SANDSTONE_CROSS:
                case STONE_CROSS:
                case MOSSY_CROSS:
                case IRON_CROSS:
                case GOLDEN_CROSS:
                case DIAMOND_CROSS:
                case EMERALD_CROSS:
                case LAPIS_CROSS:
                case REDSTONE_CROSS:
                case OBSIDIAN_CROSS:
                case QUARTZ_CROSS:
                case ICE_CROSS:
                case WOODEN_OBELISK:
                case SANDSTONE_OBELISK:
                case STONE_OBELISK:
                case MOSSY_OBELISK:
                case IRON_OBELISK:
                case GOLDEN_OBELISK:
                case DIAMOND_OBELISK:
                case EMERALD_OBELISK:
                case LAPIS_OBELISK:
                case REDSTONE_OBELISK:
                case OBSIDIAN_OBELISK:
                case QUARTZ_OBELISK:
                case ICE_OBELISK:
                    maxY = 5;
                    maxX = 2;
                    maxZ = 2;
                    startX = -1;
                    startZ = -1;
                    break;
                case WOODEN_DOG_STATUE:
                case WOODEN_CAT_STATUE:
                case SANDSTONE_DOG_STATUE:
                case SANDSTONE_CAT_STATUE:
                case STONE_DOG_STATUE:
                case STONE_CAT_STATUE:
                case MOSSY_DOG_STATUE:
                case MOSSY_CAT_STATUE:
                case IRON_DOG_STATUE:
                case IRON_CAT_STATUE:
                case GOLDEN_DOG_STATUE:
                case GOLDEN_CAT_STATUE:
                case DIAMOND_DOG_STATUE:
                case DIAMOND_CAT_STATUE:
                case EMERALD_DOG_STATUE:
                case EMERALD_CAT_STATUE:
                case LAPIS_DOG_STATUE:
                case LAPIS_CAT_STATUE:
                case REDSTONE_DOG_STATUE:
                case REDSTONE_CAT_STATUE:
                case OBSIDIAN_DOG_STATUE:
                case OBSIDIAN_CAT_STATUE:
                case QUARTZ_DOG_STATUE:
                case QUARTZ_CAT_STATUE:
                case ICE_DOG_STATUE:
                case ICE_CAT_STATUE:
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
                        if (Block.getIdFromBlock(world.getBlock(x + shiftX, y + shiftY, z + shiftZ)) != airBlockId) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
