package gravestone.item;

import gravestone.block.enums.EnumGraves;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGSGraveStone extends ItemBlock {

    public ItemBlockGSGraveStone(int id) {
        super(id);
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

            if (stack.stackTagCompound.hasKey("DeathText") && stack.stackTagCompound.getString("DeathText").length() != 0) {
                deathText = stack.stackTagCompound.getString("DeathText");
            }

            if (stack.stackTagCompound.hasKey("isLocalized") && stack.stackTagCompound.getBoolean("isLocalized")) {
                if (stack.stackTagCompound.hasKey("name")) {
                    String name = stack.stackTagCompound.getString("name");
                    String killerName = ModGraveStone.proxy.getLocalizedEntityName(stack.stackTagCompound.getString("KillerName"));
                    if (killerName.length() == 0) {
                        list.add(ChatMessageComponent.createFromTranslationWithSubstitutions(deathText, new Object[]{name}).toString());
                    } else {
                        list.add(ChatMessageComponent.createFromTranslationWithSubstitutions(deathText, new Object[]{name, killerName.toLowerCase()}).toString());
                    }
                }
            } else {
                list.add(deathText);
            }

            if (stack.stackTagCompound.hasKey("Age") && stack.stackTagCompound.getInteger("Age") != -1) {
                list.add(LanguageRegistry.instance().getStringLocalization("item.grave.age") + " " + stack.stackTagCompound.getInteger("Age") + " " + LanguageRegistry.instance().getStringLocalization("item.grave.days"));
            }

            if (stack.stackTagCompound.hasKey("SwordType") && stack.stackTagCompound.getByte("SwordType") != 0) {
                if (stack.stackTagCompound.hasKey("SwordName") && !stack.stackTagCompound.getString("SwordName").isEmpty()) {
                    list.add(LanguageRegistry.instance().getStringLocalization("item.grave.sword_name") + " - " + stack.stackTagCompound.getString("SwordName"));
                }

                if (stack.stackTagCompound.hasKey("SwordDamage") && stack.stackTagCompound.getInteger("SwordDamage") != 0) {
                    list.add(LanguageRegistry.instance().getStringLocalization("item.grave.sword_damage") + " - " + stack.stackTagCompound.getInteger("SwordDamage"));
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

    /**
     * Return sword type for sword grave
     *
     * @param swordId Sword item id
     */
    public static byte swordIdtoSwordGraveType(int swordId) {
        if (swordId == Item.swordDiamond.itemID) {
            return 5;
        } else if (swordId == Item.swordIron.itemID) {
            return 3;
        } else if (swordId == Item.swordGold.itemID) {
            return 4;
        } else if (swordId == Item.swordStone.itemID) {
            return 2;
        } else if (swordId == Item.swordWood.itemID) {
            return 1;
        }

        return 0;
    }
}
