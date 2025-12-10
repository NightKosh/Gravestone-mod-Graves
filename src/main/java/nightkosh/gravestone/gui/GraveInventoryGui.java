package nightkosh.gravestone.gui;

import nightkosh.gravestone.core.GSResources;
import nightkosh.gravestone.gui.container.GraveContainer;
import nightkosh.gravestone.tileentity.GraveStoneBlockEntity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

//TODO
//@SideOnly(Side.CLIENT)
public class GraveInventoryGui extends GuiContainerBase {

//    public GraveInventoryGui(InventoryPlayer inventoryPlayer, GraveStoneBlockEntity tileEntity) {
//        super(new GraveContainer(inventoryPlayer, tileEntity));
//        this.ySize = 114 + GraveContainer.ROWS_COUNT * GraveContainer.SLOT_WIDTH;
//    }
//
//    @Override
//    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
//        GL11.glColor4f(1, 1, 1, 1);
//        this.mc.getTextureManager().bindTexture(GSResources.CHEST_GUI);
//        int x = (width - xSize) / 2;
//        int y = (height - ySize) / 2;
//        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
//    }

}
