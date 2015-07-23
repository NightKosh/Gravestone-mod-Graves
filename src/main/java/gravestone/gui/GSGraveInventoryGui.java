package gravestone.gui;

import gravestone.core.Resources;
import gravestone.gui.container.GraveContainer;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSGraveInventoryGui extends GuiContainer {

    public GSGraveInventoryGui(InventoryPlayer inventoryPlayer, TileEntityGSGraveStone tileEntity) {
        super(new GraveContainer(inventoryPlayer, tileEntity));
        this.ySize = 114 + 6 * 18;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1, 1, 1, 1);
        this.mc.getTextureManager().bindTexture(Resources.CHEST_GUI);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
