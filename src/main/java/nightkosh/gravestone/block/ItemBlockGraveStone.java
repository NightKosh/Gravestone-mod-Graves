package nightkosh.gravestone.block;

import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.core.GSBlocks;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGraveStone extends BlockItem {

    public final EnumGraveMaterial material;
    public final EnumGraveType graveType;

    public ItemBlockGraveStone(BlockGraveStone block, ResourceKey id) {
        super(block, new Item.Properties()
                .setId(id)
                .stacksTo(64));
        this.graveType = block.graveType;
        this.material = block.material;
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(keyFor(graveType));
    }

    private static String keyFor(EnumGraveType graveType) {
        return switch (graveType) {
            case GRAVE_STONE -> "block.gravestone.grave_stone";
            case GRAVE_PLATE -> "block.gravestone.grave_plate";
            case CROSS -> "block.gravestone.cross";
            case OBELISK -> "block.gravestone.obelisk";
            case CELTIC_CROSS -> "block.gravestone.celtic_cross";
            case PET_GRAVE_STONE -> "block.gravestone.pet_grave_stone";
            case VILLAGER_GRAVE_STONE -> "block.gravestone.villager_grave_stone";
            default -> "block.gravestone.grave_stone";
        };
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nonnull Item.TooltipContext context,
                                @Nonnull TooltipDisplay tooltipDisplay, @Nonnull Consumer<Component> consumer,
                                @Nonnull TooltipFlag flag) {
        consumer.accept(Component.translatable("material.title")
                .append(" ")
                .append(Component.translatable("material." + this.material.name().toLowerCase())));

        var data = stack.get(DataComponents.CUSTOM_DATA);
        if (data != null) {
            var tag = data.copyTag();
            if (tag != null) {
                if (tag.contains("deathMessageJson")) {
                    var ops = RegistryOps.create(JsonOps.INSTANCE, context.level().registryAccess());
                    var json = JsonParser.parseString(tag.getString("deathMessageJson").get());
                    var component = ComponentSerialization.CODEC
                            .parse(ops, json)
                            .result()
                            .orElse(Component.empty());
                    consumer.accept(component);
                }
                if (tag.getIntOr("Age", 0) > 0) {
                    consumer.accept(Component.translatable("item.grave.age")
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
            super.appendHoverText(stack, context, tooltipDisplay, consumer, flag);
        }
    }

}
