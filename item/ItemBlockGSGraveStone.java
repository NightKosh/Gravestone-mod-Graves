package gravestone.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import gravestone.block.enums.EnumGraves;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGSGraveStone extends ItemBlock {

    public ItemBlockGSGraveStone(Block block) {
        super(block);
        setHasSubtypes(true);
        setUnlocalizedName("Gravestone");
    }

    @Override
    public int getMetadata(int damageValue) {
        return 0;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        EnumGraves graveType;

        if (itemStack.stackTagCompound != null && itemStack.stackTagCompound.hasKey("GraveType")) {
            graveType = EnumGraves.getByID(itemStack.stackTagCompound.getByte("GraveType"));
        } else {
            graveType = EnumGraves.getByID(0);
        }

        return getUnlocalizedName() + "." + graveType.getName();
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
            String deathText = "";

            if (stack.stackTagCompound.hasKey("DeathText") && StringUtils.isNotBlank(stack.stackTagCompound.getString("DeathText"))) {
                deathText = stack.stackTagCompound.getString("DeathText");
            }

            if (stack.stackTagCompound.hasKey("isLocalized") && stack.stackTagCompound.getBoolean("isLocalized")) {
                if (stack.stackTagCompound.hasKey("name")) {
                    String name = stack.stackTagCompound.getString("name");
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

            // TODO Age
//            if (stack.stackTagCompound.hasKey("Age") && stack.stackTagCompound.getInteger("Age") != -1) {
//                list.add(ModGraveStone.proxy.getLocalizedString("item.grave.age") + " " + stack.stackTagCompound.getInteger("Age") + " " + ModGraveStone.proxy.getLocalizedString("item.grave.days"));
//            }

            if (stack.stackTagCompound.hasKey("Sword")) {
                ItemStack sword = ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Sword"));

                if (StringUtils.isNotBlank(sword.getDisplayName())) {
                    list.add(ModGraveStone.proxy.getLocalizedString("item.grave.sword_name") + " - " + sword.getDisplayName());
                }

                if (sword.getItemDamage() != 0) {
                    list.add(ModGraveStone.proxy.getLocalizedString("item.grave.sword_damage") + " - " + sword.getItemDamage());
                }

                if (sword.getTagCompound() != null && sword.getTagCompound().hasKey("ench")) {
                    NBTTagList enchantments = sword.getTagCompound().getTagList("ench", 10);

                    if (enchantments.tagCount() != 0) {
                        for (int i = 0; i < enchantments.tagCount(); i++) {
                            short enchantmentId = enchantments.getCompoundTagAt(i).getShort("id");
                            short enchantmentLvl = enchantments.getCompoundTagAt(i).getShort("lvl");

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
    // TODO
    public boolean hasEffect(ItemStack stack) {
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("SwordNBT")) {
            NBTTagList enchantments = stack.stackTagCompound.getCompoundTag("SwordNBT").getTagList("ench", 10);

            if (enchantments.tagCount() != 0) {
                return true;
            }
        }

        return false;
    }
}
