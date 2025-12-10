package nightkosh.gravestone.item.itemblock;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import nightkosh.gravestone.ModGraveStone;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.core.GSBlock;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGraveStone extends BlockItem {

    public ItemBlockGraveStone(Block block) {
        super(GSBlock.getGraveStone(), new Item.Properties().stacksTo(64));
    }

    //TODO

//    @Override
//    public String getUnlocalizedName(ItemStack itemStack) {
//        return EnumGraves.getById(itemStack.getItemDamage()).getUnLocalizedName();
//    }
//
//    @Override
//    public void onCreated(ItemStack stack, Level level, Player player) {
//        if (!stack.hasTagCompound()) {
//            stack.setTagCompound(new NBTTagCompound());
//        }
//    }
//
//    @Override
//    public void addInformation(ItemStack stack, @Nullable Level level, List<String> tooltipList, ITooltipFlag flag) {
//        if (!stack.hasTagCompound()) {
//            stack.setTagCompound(new NBTTagCompound());
//        } else {
//            NBTTagCompound nbt = stack.getTagCompound();
//
//            String deathText = "";
//            if (nbt.hasKey("DeathText") && StringUtils.isNotBlank(nbt.getString("DeathText"))) {
//                deathText = nbt.getString("DeathText");
//            }
//
//            if (nbt.hasKey("isLocalized") && nbt.getBoolean("isLocalized")) {
//                if (nbt.hasKey("name")) {
//                    String name = ModGraveStone.proxy.getLocalizedEntityName(nbt.getString("name"));
//                    String killerName = ModGraveStone.proxy.getLocalizedEntityName(nbt.getString("KillerName"));
//                    if (killerName.length() == 0) {
//                        tooltipList.add(new TextComponentTranslation(deathText, new Object[]{name}).getFormattedText());
//                    } else {
//                        tooltipList.add(new TextComponentTranslation(deathText, new Object[]{name, killerName.toLowerCase()}).getFormattedText());
//                    }
//                }
//            } else {
//                tooltipList.add(deathText);
//            }
//
//            if (nbt.getInteger("Age") > 0) {
//                tooltipList.add(ModGraveStone.proxy.getLocalizedString("item.grave.age") + " " + nbt.getInteger("Age") + " " + ModGraveStone.proxy.getLocalizedString("item.grave.days"));
//            }
//
//            EnumGraveMaterial material = EnumGraves.getById(stack.getItemDamage()).getMaterial();
//            if (material != EnumGraveMaterial.OTHER) {
//                StringBuilder materialStr = new StringBuilder();
//                materialStr.append(ModGraveStone.proxy.getLocalizedString("material.title"))
//                        .append(" ")
//                        .append(ModGraveStone.proxy.getLocalizedMaterial(material));
//                if (nbt.getBoolean("Mossy")) {
//                    materialStr.append(", ")
//                            .append(ModGraveStone.proxy.getLocalizedString("material.mossy"));
//                }
//                tooltipList.add(materialStr.toString());
//            }
//
//            if (nbt.hasKey("Sword")) {
//                ItemStack sword = new ItemStack(nbt.getCompoundTag("Sword"));
//
//                if (StringUtils.isNotBlank(sword.getDisplayName())) {
//                    tooltipList.add(ModGraveStone.proxy.getLocalizedString("item.grave.sword_name") + " - " + sword.getDisplayName());
//                }
//
//                if (sword.getItemDamage() != 0) {
//                    tooltipList.add(ModGraveStone.proxy.getLocalizedString("item.grave.sword_damage") + " - " + sword.getItemDamage());
//                }
//
//                if (sword.getTagCompound() != null && sword.getTagCompound().hasKey("ench")) {
//                    NBTTagList enchantments = sword.getTagCompound().getTagList("ench", 10);
//
//                    if (enchantments.tagCount() != 0) {
//                        for (int i = 0; i < enchantments.tagCount(); i++) {
//                            short enchantmentId = enchantments.getCompoundTagAt(i).getShort("id");
//                            short enchantmentLvl = enchantments.getCompoundTagAt(i).getShort("lvl");
//
//                            try {
//                                if (Enchantment.getEnchantmentByID(enchantmentId) != null) {
//                                    tooltipList.add(Enchantment.getEnchantmentByID(enchantmentId).getTranslatedName(enchantmentLvl));
//                                }
//                            } catch (Exception e) {
//
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public boolean hasEffect(ItemStack stack) {
//        return stack.hasTagCompound() && stack.getTagCompound().hasKey("Enchanted");
//    }

}
