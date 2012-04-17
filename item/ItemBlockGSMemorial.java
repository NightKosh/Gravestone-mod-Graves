
package net.minecraft.GraveStone.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockGSMemorial extends ItemBlock {

    private final static String[] subNames = {
        "Cross Memorial"
    };

    public ItemBlockGSMemorial(int id) {
        super(id);
        setHasSubtypes(true);
        setUnlocalizedName("multiBlock");
    }

    @Override
    public int getMetadata(int damageValue) {
        return damageValue - (damageValue % 4);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName() + "." + subNames[(int) (itemStack.getItemDamage() / 4)];
    }
}
