package nightkosh.gravestone.block;

import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;

import javax.annotation.Nonnull;
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

    @Override
    public void appendHoverText(ItemStack stack, @Nonnull Item.TooltipContext context,
                                List<Component> tooltips, @Nonnull TooltipFlag flag) {
        tooltips.add(Component.translatable("material.title")
                .append(" ")
                .append(Component.translatable("material." + this.material.name().toLowerCase())));

        var data = stack.get(DataComponents.CUSTOM_DATA);
        if (data != null) {
            var tag = data.getUnsafe();
            if (tag != null) {
                if (tag.contains("deathMessageJson")) {
                    tooltips.add(Component.Serializer.fromJson(
                            tag.getString("deathMessageJson"),
                            Minecraft.getInstance().level.registryAccess()));
                }
                if (tag.getInt("Age") > 0) {
                    tooltips.add(Component.translatable("item.grave.age")
                            .append(" " + tag.getInt("Age" + " "))
                            .append(Component.translatable("item.grave.days")));
                }

//            if (tag.contains("Sword")) {
//                var sword = ItemStack.parse(level.registryAccess(), tag.getCompoundTag("Sword")).get();
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
            }
            super.appendHoverText(stack, context, tooltips, flag);
        }
    }

}
