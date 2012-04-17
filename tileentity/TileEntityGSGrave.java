package net.minecraft.GraveStone.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityGSGrave extends TileEntity {

    protected GSGraveStoneItems gSItems;
    protected GSGraveStoneDeathText gSDeathText;
    protected boolean isEditable = true;

    public void setGraveContent() {
        gSItems.setRandomGraveContent();
        gSDeathText.setRandomDeathText();
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        gSItems.setInventorySlotContents(slot, itemStack);

        this.onInventoryChanged();
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName() {
        return "container.gravestone";
    }

    public void setItems(ItemStack[] items) {
        gSItems.setItems(items);
    }

    public void dropAllItems() {
        gSItems.dropAllItems();
    }

    public String getDeathText() {
        return gSDeathText.getDeathText();
    }

    public void setDeathText(String text) {
        gSDeathText.setDeathText(text);
    }

    public boolean isEditable() {
        return isEditable;
    }

    @SideOnly(Side.CLIENT)
    /**
     * Sets the grave's isEditable flag to the specified parameter.
     */
    public void setEditable(boolean par1) {
        isEditable = par1;
    }
}
