package nightkosh.gravestone.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class GuiContainerBase extends GuiContainer {
    public GuiContainerBase(Container container) {
        super(container);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        for (int i = 0; i < this.inventorySlots.inventorySlots.size(); ++i) {
            Slot slot = this.inventorySlots.inventorySlots.get(i);

            if (this.isMouseOverSlot(slot, mouseX, mouseY) && slot.isEnabled()) {
                renderHoveredToolTip(slot, mouseX, mouseY);
            }
        }
    }

    private boolean isMouseOverSlot(Slot slot, int mouseX, int mouseY) {
        return this.isPointInRegion(slot.xPos, slot.yPos, 16, 16, mouseX, mouseY);
    }

    protected void renderHoveredToolTip(Slot slot, int p_191948_1_, int p_191948_2_) {
        if (this.mc.player.inventory.getItemStack().isEmpty() && slot != null && slot.getHasStack()) {
            this.renderToolTip(slot.getStack(), p_191948_1_, p_191948_2_);
        }
    }

}
