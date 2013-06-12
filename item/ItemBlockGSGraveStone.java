package GraveStone.item;

import GraveStone.block.BlockGSGraveStone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ItemBlockGSGraveStone extends ItemBlock {

    public ItemBlockGSGraveStone(int id) {
        super(id);
        setHasSubtypes(true);
        setUnlocalizedName("Gravestone");
    }

    @Override
    public int getMetadata(int damageValue) {
        return 0;//damageValue;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        byte graveType = 0;
        if (itemStack.stackTagCompound != null) {
            graveType = itemStack.stackTagCompound.getByte("GraveType");
        }
        return getUnlocalizedName() + "." + BlockGSGraveStone.NAMES[graveType];
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
        } else {
            if (stack.stackTagCompound.hasKey("DeathText") && !stack.stackTagCompound.getString("DeathText").equals("")) {
                list.add(stack.stackTagCompound.getString("DeathText"));
            }
            if (stack.stackTagCompound.hasKey("Age") && stack.stackTagCompound.getInteger("Age") != -1) {
                list.add("Had lived " + stack.stackTagCompound.getInteger("Age") + " days");
            }

            if (stack.stackTagCompound.hasKey("SwordType") && stack.stackTagCompound.getByte("SwordType") != 0) {
                if (stack.stackTagCompound.hasKey("SwordName") && !stack.stackTagCompound.getString("SwordName").isEmpty()) {
                    list.add("Sword name - " + stack.stackTagCompound.getString("SwordName"));
                }
                if (stack.stackTagCompound.hasKey("SwordDamage") && stack.stackTagCompound.getInteger("SwordDamage") != 0) {
                    list.add("Sword damage - " + stack.stackTagCompound.getInteger("SwordDamage"));
                }
                if (stack.stackTagCompound.hasKey("SwordNBT")) {
                    NBTTagList enchantments = stack.stackTagCompound.getCompoundTag("SwordNBT").getTagList("ench");
                    if (enchantments.tagCount() != 0) {
                        for (int i = 0; i < enchantments.tagCount(); i++) {
                            short enchantmentId = ((NBTTagCompound) enchantments.tagAt(i)).getShort("id");
                            short enchantmentLvl = ((NBTTagCompound) enchantments.tagAt(i)).getShort("lvl");
                            if (Enchantment.enchantmentsList[enchantmentId] != null) {
                                list.add(Enchantment.enchantmentsList[enchantmentId].getTranslatedName(enchantmentLvl));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("SwordNBT")) {
            NBTTagList enchantments = stack.stackTagCompound.getCompoundTag("SwordNBT").getTagList("ench");
            if (enchantments.tagCount() != 0) {
                return true;
            }
        }
        return false;
    }
}
