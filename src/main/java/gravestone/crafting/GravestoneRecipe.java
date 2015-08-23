package gravestone.crafting;

import gravestone.block.enums.EnumGraveMaterial;
import gravestone.block.enums.EnumGraves;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GravestoneRecipe {
    private ItemStack resultItem;
    private List<ItemStack> requiredItems;
    private boolean isGravestone;
    private EnumGraves.EnumGraveType graveType;
    private EnumGraveMaterial material;

    public GravestoneRecipe(boolean isGravestone, EnumGraves.EnumGraveType graveType, EnumGraveMaterial material, List<ItemStack> requiredItems, ItemStack resultItem) {
        this.isGravestone = isGravestone;
        this.graveType = graveType;
        this.material = material;
        this.requiredItems = requiredItems;
        this.resultItem = resultItem;
    }

    public ItemStack getResultItem() {
        return resultItem.copy();
    }

    public List<ItemStack> getRequiredItems() {
        return requiredItems;
    }

    public boolean isGravestone() {
        return isGravestone;
    }

    public EnumGraves.EnumGraveType getGraveType() {
        return graveType;
    }

    public EnumGraveMaterial getMaterial() {
        return material;
    }

    public boolean match(GravestoneRecipe recipe) {
        return this.match(recipe.isGravestone(), recipe.getGraveType(), recipe.getMaterial(), recipe.getRequiredItems());
    }

    public boolean match(boolean isGravestone, EnumGraves.EnumGraveType graveType, EnumGraveMaterial material, List<ItemStack> requiredItems) {
        return  (this.isGravestone() == isGravestone && this.getGraveType() == graveType &&
                this.getMaterial() == material && this.containItems(requiredItems));
    }

    public boolean containItems(List<ItemStack> items) {
        if (items == null || requiredItems.size() > items.size()) {
            return false;
        } else {
            for (ItemStack requiredItem : requiredItems) {
                boolean haveItem = false;
                for (ItemStack item : items) {
                    if (item != null && requiredItem.getItem().equals(item.getItem()) && requiredItem.getMetadata() == item.getMetadata() &&
                            requiredItem.stackSize <= item.stackSize) {
                        haveItem = true;
                        break;
                    }
                }
                if (!haveItem) {
                    return false;
                }
            }
        }
        return true;
    }
}
