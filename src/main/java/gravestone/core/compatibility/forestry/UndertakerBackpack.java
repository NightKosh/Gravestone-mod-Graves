package gravestone.core.compatibility.forestry;

import forestry.api.storage.IBackpackDefinition;
import gravestone.ModGraveStone;
import gravestone.core.GSBlock;
import gravestone.core.GSItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UndertakerBackpack implements IBackpackDefinition {
    protected List<ItemStack> items = new ArrayList<ItemStack>(45);

    protected static final List allowedBlocks = Arrays.asList(
            GSBlock.graveStone,
            GSBlock.memorial,
            GSBlock.candle,
            GSBlock.skullCandle
    );
    protected static final List allowedItems = Arrays.asList(
            GSItem.chisel,
            GSItem.corpse,
            Items.skull
    );

    private static UndertakerBackpack instance;

    public static UndertakerBackpack getInstance() {
        if (instance == null)
            instance = new UndertakerBackpack();
        return instance;
    }

    @Override
    public String getKey() {
        return "UNDERTAKER";
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getName(ItemStack backpack) {
        return ModGraveStone.proxy.getLocalizedString(backpack.getItem().getUnlocalizedName());
    }

    @Override
    public int getPrimaryColour() {
        return 1842478;
    }

    @Override
    public int getSecondaryColour() {
        return 3552587;
    }

    @Override
    public void addValidItem(ItemStack item) {
        if (item != null) {
            this.items.add(item);
        }
    }

    @Override
    public boolean isValidItem(EntityPlayer player, ItemStack itemstack) {
        return allowedBlocks.contains(Block.getBlockFromItem(itemstack.getItem())) ||
                allowedItems.contains(itemstack.getItem());
    }
}
