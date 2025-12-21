package nightkosh.gravestone.item.itemblock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone.core.GSBlocks;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGraveStone extends BlockItem {

    public ItemBlockGraveStone(BlockGraveStone block) {
        super(block, new Item.Properties().stacksTo(64));
    }

    //TODO
//    @Override
//    public void onCreated(ItemStack stack, Level level, Player player) {
//        if (!stack.hasTagCompound()) {
//            stack.setTagCompound(new CompoundTag());
//        }
//    }
//
//    @Override
//    public void addInformation(ItemStack stack, @Nullable Level level, List<String> tooltipList, ITooltipFlag flag) {
//        if (!stack.hasTagCompound()) {
//            stack.setTagCompound(new CompoundTag());
//        } else {
//            CompoundTag nbt = stack.getTag();
//
//            String deathText = "";
//            if (nbt.contains("DeathText") && StringUtils.isNotBlank(nbt.getString("DeathText"))) {
//                deathText = nbt.getString("DeathText");
//            }
//
//            if (nbt.contains("isLocalized") && nbt.getBoolean("isLocalized")) {
//                if (nbt.contains("name")) {
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
//            if (nbt.getInt("Age") > 0) {
//                tooltipList.add(ModGraveStone.proxy.getLocalizedString("item.grave.age") + " " + nbt.getInt("Age") + " " + ModGraveStone.proxy.getLocalizedString("item.grave.days"));
//            }
//
//            var material = EnumGraves.getById(stack.getItemDamage()).getMaterial();
//            if (material != EnumGraveMaterial.OTHER) {
//                StringBuilder materialStr = new StringBuilder();
//                materialStr.append(ModGraveStone.proxy.getLocalizedString("material.title"))
//                        .append(" ")
//                        .append(ModGraveStone.proxy.getLocalizedMaterial(material));
//                tooltipList.add(materialStr.toString());
//            }
//
//            if (nbt.contains("Sword")) {
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
//                if (sword.getTag() != null && sword.getTag().contains("ench")) {
//                    NBTTagList enchantments = sword.getTag().getTagList("ench", 10);
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
//        return stack.hasTagCompound() && stack.getTag().contains("Enchanted");
//    }

}
