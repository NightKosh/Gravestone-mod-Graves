package nightkosh.gravestone.block;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGraveStone extends BlockItem {

    public final EnumGraveMaterial material;
    public final EnumGraveType graveType;

    public ItemBlockGraveStone(BlockGraveStone block) {
        super(block, new Item.Properties().stacksTo(64));
        this.graveType = block.graveType;
        this.material = block.material;
    }

    //TODO
//    @Override
//    public void onCreated(ItemStack stack, Level level, Player player) {
//        if (!stack.hasTagCompound()) {
//            stack.setTagCompound(new CompoundTag());
//        }
//    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltips, TooltipFlag flag) {

        var material = this.material;
        //TODO
//            if (material != EnumGraveMaterial.OTHER) {
        tooltips.add(Component.translatable("material.title")
                .append(" ")
                .append(Component.translatable("material." + material.name().toLowerCase())));
//            }

        if (!stack.hasTag()) {
            stack.setTag(new CompoundTag());
        } else {
            var nbt = stack.getTag();//TODO rename to tag

            if (nbt.contains("deathMessageJson")) {
                tooltips.add(Component.Serializer.fromJson(nbt.getString("deathMessageJson")));
            }

            if (nbt.getInt("Age") > 0) {
                tooltips.add(Component.translatable("item.grave.age")
                        .append(" " + nbt.getInt("Age" + " "))
                        .append(Component.translatable("item.grave.days")));
            }

//            if (nbt.contains("Sword")) {
//                var sword = ItemStack.of(nbt.getCompoundTag("Sword"));
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
            super.appendHoverText(stack, level, tooltips, flag);
        }
    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public boolean hasEffect(ItemStack stack) {
//        return stack.hasTagCompound() && stack.getTag().contains("Enchanted");
//    }

}
