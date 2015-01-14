package gravestone.item;

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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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

        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("GraveType")) {
            graveType = EnumGraves.getById(itemStack.getTagCompound().getByte("GraveType"));
        } else {
            graveType = EnumGraves.getById(0);
        }

        return getUnlocalizedName() + "." + graveType.getName();
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
        } else {
            String deathText = "";

            if (stack.getTagCompound().hasKey("DeathText") && StringUtils.isNotBlank(stack.getTagCompound().getString("DeathText"))) {
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

            if (stack.getTagCompound().hasKey("Age") && stack.getTagCompound().getInteger("Age") > 0) {
                list.add(ModGraveStone.proxy.getLocalizedString("item.grave.age") + " " + stack.getTagCompound().getInteger("Age") + " " + ModGraveStone.proxy.getLocalizedString("item.grave.days"));
            }

            if (stack.getTagCompound().hasKey("Sword")) {
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

                            if (Enchantment.enchantmentsBookList[enchantmentId] != null) {
                                list.add(Enchantment.enchantmentsBookList[enchantmentId].getTranslatedName(enchantmentLvl));
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
        return stack.getTagCompound() != null && stack.getTagCompound().hasKey("Enchanted");
    }
}
