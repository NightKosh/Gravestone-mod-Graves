package gravestone.item.itemblock;

import gravestone.ModGraveStone;
import gravestone.block.enums.EnumGraveMaterial;
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
        EnumGraves graveType = EnumGraves.getById(itemStack.getTagCompound().getInteger("Type"));
        return getUnlocalizedName() + "." + graveType.getLocalizedName();
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        } else {
            NBTTagCompound nbt = stack.getTagCompound();

            String deathText = "";
            if (nbt.hasKey("DeathText") && StringUtils.isNotBlank(nbt.getString("DeathText"))) {
                deathText = nbt.getString("DeathText");
            }

            if (nbt.hasKey("isLocalized") && nbt.getBoolean("isLocalized")) {
                if (nbt.hasKey("name")) {
                    String name = ModGraveStone.proxy.getLocalizedEntityName(nbt.getString("name"));
                    String killerName = ModGraveStone.proxy.getLocalizedEntityName(nbt.getString("KillerName"));
                    if (killerName.length() == 0) {
                        list.add(new ChatComponentTranslation(deathText, new Object[]{name}).getFormattedText());
                    } else {
                        list.add(new ChatComponentTranslation(deathText, new Object[]{name, killerName.toLowerCase()}).getFormattedText());
                    }
                }
            } else {
                list.add(deathText);
            }

            if (nbt.getInteger("Age") > 0) {
                list.add(ModGraveStone.proxy.getLocalizedString("item.grave.age") + " " + nbt.getInteger("Age") + " " + ModGraveStone.proxy.getLocalizedString("item.grave.days"));
            }

            EnumGraveMaterial material = EnumGraves.getById(nbt.getInteger("Type")).getMaterial();
            if (material != EnumGraveMaterial.OTHER) {
                StringBuilder materialStr = new StringBuilder();
                materialStr.append(ModGraveStone.proxy.getLocalizedString("material.title"))
                        .append(" ")
                        .append(material.getLocalizedMaterial());
                if (nbt.getBoolean("Mossy")) {
                    materialStr.append(", ")
                            .append(ModGraveStone.proxy.getLocalizedString("material.mossy"));
                }
                list.add(materialStr.toString());
            }

            if (nbt.hasKey("Sword")) {
                ItemStack sword = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("Sword"));

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

                            try {
                                if (Enchantment.enchantmentsBookList[enchantmentId] != null) {
                                    list.add(Enchantment.enchantmentsBookList[enchantmentId].getTranslatedName(enchantmentLvl));
                                }
                            } catch (Exception e) {

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
        return stack.hasTagCompound() && stack.getTagCompound().hasKey("Enchanted");
    }
}
