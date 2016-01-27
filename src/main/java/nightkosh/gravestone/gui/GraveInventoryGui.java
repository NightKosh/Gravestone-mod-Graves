package nightkosh.gravestone.gui;

import nightkosh.gravestone.core.Resources;
import nightkosh.gravestone.gui.container.GraveContainer;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class GraveInventoryGui extends GuiContainer {

    public GraveInventoryGui(InventoryPlayer inventoryPlayer, TileEntityGraveStone tileEntity) {
        super(new GraveContainer(inventoryPlayer, tileEntity));
        this.ySize = 114 + GraveContainer.ROWS_COUNT * GraveContainer.SLOT_WIDTH;
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
