package gravestone.gui;

import gravestone.ModGraveStone;
import gravestone.block.enums.EnumGraves;
import gravestone.core.GSMessageHandler;
import gravestone.core.Resources;
import gravestone.gui.container.ChiselContainer;
import gravestone.models.block.graves.ModelCrossGraveStone;
import gravestone.packets.ChiselMessageToServer;
import gravestone.renderer.tileentity.TileEntityGSGraveStoneRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
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
public class GSChiselCraftingGui extends GuiContainer {

    private EntityPlayer player;

    private final String graveButtonStr = "Gravestone";//ModGraveStone.proxy.getLocalizedString("gui.edit_grave.title");
    private final String memorialButtonStr = "Memorial";//ModGraveStone.proxy.getLocalizedString("gui.edit_grave.close");
    private final String typeStr = "Object to craft";//ModGraveStone.proxy.getLocalizedString("gui.edit_grave.close");

    private GuiButton graveButton;
    private GuiButton memorialButton;

    public GSChiselCraftingGui(InventoryPlayer inventoryPlayer) {
        super(new ChiselContainer(inventoryPlayer));
        this.player = inventoryPlayer.player;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(graveButton = new GuiButton(0, (width - xSize) / 2 + 100, (height - ySize) / 2 + 25, this.graveButtonStr));
        this.buttonList.add(memorialButton = new GuiButton(1, (width - xSize) / 2 + 100, (height - ySize) / 2 + 25, this.memorialButtonStr));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1, 1, 1, 1);
        this.mc.renderEngine.bindTexture(Resources.INVENTORY);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        this.drawString(this.fontRendererObj, this.typeStr, this.width / 2 - 40, (height - ySize) / 2 + 55, 16777215);

        TileEntityGSGraveStoneRenderer.instance.renderGraveInGui(350, 0, player.worldObj, EnumGraves.GOLDEN_DOG_STATUE, false, false, false, null, par1);
    }

//    @Override
//    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//        TileEntityGSGraveStoneRenderer.instance.renderGraveInGui(150, 150, player.worldObj, EnumGraves.GOLDEN_DOG_STATUE, false, false, false, null, partialTicks);
//
//        super.drawScreen(mouseX, mouseY, partialTicks);
//    }

    @Override
    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                GSMessageHandler.networkWrapper.sendToServer(new ChiselMessageToServer(player, true));
                break;
            case 1:
                GSMessageHandler.networkWrapper.sendToServer(new ChiselMessageToServer(player, false));
                break;
        }
    }
}
