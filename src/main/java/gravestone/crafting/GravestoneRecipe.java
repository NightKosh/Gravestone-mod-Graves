package gravestone.crafting;

import gravestone.block.enums.EnumGraveMaterial;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.IEnumGraveType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
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
    private IEnumGraveType graveType;
    private EnumGraveMaterial material;
    private boolean canBeMossy;

    public GravestoneRecipe(boolean isGravestone, IEnumGraveType graveType, EnumGraveMaterial material, List<ItemStack> requiredItems, ItemStack resultItem) {
        this(isGravestone, graveType, material, true, requiredItems, resultItem);
    }

    public GravestoneRecipe(boolean isGravestone, IEnumGraveType graveType, EnumGraveMaterial material, boolean canBeMossy, List<ItemStack> requiredItems, ItemStack resultItem) {
        this.isGravestone = isGravestone;
        this.graveType = graveType;
        this.material = material;
        this.requiredItems = requiredItems;
        this.resultItem = resultItem;
        this.canBeMossy = canBeMossy;
    }

    public ItemStack getResultItem() {
        return resultItem.copy();
    }

    public ItemStack getResultItem(List<ItemStack> requiredItems) {
        boolean isEnchanted = requiredItems.stream().anyMatch((item) -> item != null && item.getItem() instanceof ItemEnchantedBook);
        boolean isMossy = requiredItems.stream().anyMatch((item) -> item != null && Block.getBlockFromItem(item.getItem()) instanceof BlockVine);
        return this.getResultItem(isEnchanted, isMossy);
    }

    public ItemStack getResultItem(boolean isEnchanted, boolean isMossy) {
        ItemStack item = this.getResultItem();
        if (item != null) {
            NBTTagCompound nbt = item.getTagCompound();
            if (isEnchanted) {
                nbt.setBoolean("Enchanted", isEnchanted);
            }
            if (isMossy) {
                nbt.setBoolean("Mossy", isMossy);
            }
        }
        return item;
    }

    public List<ItemStack> getRequiredItems() {
        return new ArrayList<ItemStack>(requiredItems);
    }

    public List<ItemStack> getRequiredItems(boolean isEnchanted, boolean isMossy) {
        List<ItemStack> items = this.getRequiredItems();
        if (isEnchanted) {
            items.add(new ItemStack(Items.enchanted_book, 1));
        }
        if (isMossy) {
            items.add(new ItemStack(Blocks.vine, 1));
        }
        return items;
    }

    public boolean isGravestone() {
        return isGravestone;
    }

    public IEnumGraveType getGraveType() {
        return graveType;
    }

    public EnumGraveMaterial getMaterial() {
        return material;
    }

    public boolean match(GravestoneRecipe recipe) {
        return this.match(recipe.isGravestone(), recipe.getGraveType(), recipe.getMaterial(), false, false, recipe.getRequiredItems());
    }

    public boolean match(boolean isGravestone, IEnumGraveType graveType, EnumGraveMaterial material, boolean isEnchanted, boolean isMossy) {
        return  this.isGravestone() == isGravestone && this.getGraveType() == graveType &&
                this.getMaterial() == material && (this.canBeMossy || !isMossy);
    }

    public boolean match(boolean isGravestone, IEnumGraveType graveType, EnumGraveMaterial material, boolean isEnchanted, boolean isMossy, List<ItemStack> requiredItems) {
        return  this.match(isGravestone, graveType, material, isEnchanted, isMossy) && this.containItems(requiredItems);
    }

    public boolean containItems(List<ItemStack> items) {
        if (items == null || requiredItems.size() > items.size()) {
            return false;
        } else {
            for (ItemStack requiredItem : requiredItems) {
                if (!items.stream().anyMatch((item) -> item != null && requiredItem.getItem().equals(item.getItem()) &&
                        requiredItem.getMetadata() == item.getMetadata() && requiredItem.stackSize <= item.stackSize)) {
                    return false;
                }
            }
        }
        return true;
    }
}
