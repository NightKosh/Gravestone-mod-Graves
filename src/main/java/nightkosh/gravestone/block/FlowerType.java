package nightkosh.gravestone.block;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import javax.annotation.Nonnull;
import java.util.Locale;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum FlowerType  implements StringRepresentable {
    NONE(null),
    POPPY(Items.POPPY),
    DANDELION(Items.DANDELION),
    BLUE_ORCHID(Items.BLUE_ORCHID),
    ALLIUM(Items.ALLIUM),
    AZURE_BLUET(Items.AZURE_BLUET),
    RED_TULIP(Items.RED_TULIP),
    ORANGE_TULIP(Items.ORANGE_TULIP),
    WHITE_TULIP(Items.WHITE_TULIP),
    PINK_TULIP(Items.PINK_TULIP),
    OXEYE_DAISY(Items.OXEYE_DAISY),
    CORNFLOWER(Items.CORNFLOWER),
    LILY_OF_THE_VALLEY(Items.LILY_OF_THE_VALLEY);

    private final Item flower;

    FlowerType(Item flower) {
        this.flower = flower;
    }

    @Nonnull
    public Item getFlower() {
        return flower;
    }

    @Nonnull
    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    @Nonnull
    public static FlowerType fromItem(ItemStack stack) {
        if (stack.isEmpty()) return FlowerType.NONE;

        var item = stack.getItem();
        for (var flower : values()) {
            if (flower.getFlower() == item) {
                return flower;
            }
        }

        return FlowerType.NONE;
    }

}
