package gravestone.crafting;

import gravestone.block.enums.EnumGraveMaterial;
import gravestone.block.enums.EnumGraves;
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
    private EnumGraves.EnumGraveType graveType;
    private EnumGraveMaterial material;
    private boolean canBeMossy;

    public GravestoneRecipe(boolean isGravestone, EnumGraves.EnumGraveType graveType, EnumGraveMaterial material, boolean canBeMossy, List<ItemStack> requiredItems, ItemStack resultItem) {
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
        boolean isEnchanted = false;
        boolean isMossy = false;
        for (ItemStack item : requiredItems) {
            if (item != null) {
                if (item.getItem() instanceof ItemEnchantedBook) {
                    isEnchanted = true;
                    continue;
                }
                if (Block.getBlockFromItem(item.getItem()) instanceof BlockVine) {
                    isMossy = true;
                    continue;
                }
            }
        }
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
        return this.match(recipe.isGravestone(), recipe.getGraveType(), recipe.getMaterial(), false, false, recipe.getRequiredItems());//TODO
    }

    public boolean match(boolean isGravestone, EnumGraves.EnumGraveType graveType, EnumGraveMaterial material, boolean isEnchanted, boolean isMossy) {//TODO
        return  this.isGravestone() == isGravestone && this.getGraveType() == graveType &&
                this.getMaterial() == material;
    }

    public boolean match(boolean isGravestone, EnumGraves.EnumGraveType graveType, EnumGraveMaterial material, boolean isEnchanted, boolean isMossy, List<ItemStack> requiredItems) {
        return  this.match(isGravestone, graveType, material, isEnchanted, isMossy) && this.containItems(requiredItems);
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
