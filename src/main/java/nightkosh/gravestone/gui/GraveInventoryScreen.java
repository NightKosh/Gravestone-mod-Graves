package nightkosh.gravestone.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import nightkosh.gravestone.gui.container.GraveContainerMenu;

import javax.annotation.Nonnull;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveInventoryScreen extends AbstractContainerScreen<GraveContainerMenu> {

    private static final Identifier TEXTURE = Identifier.parse("textures/gui/container/generic_54.png");
    private static final int TEXTURE_SIZE = 256;

    public GraveInventoryScreen(GraveContainerMenu menu, Inventory inventoryPlayer, Component title) {
        super(menu, inventoryPlayer, title);

        this.imageWidth = 176;
        this.imageHeight = 114 + GraveContainerMenu.ROWS_COUNT * GraveContainerMenu.SLOT_WIDTH;
    }

    @Override
    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {

        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE,
                this.leftPos, this.topPos, 0, 0,
                this.imageWidth, this.imageHeight,
                TEXTURE_SIZE, TEXTURE_SIZE);
    }

    @Override
    public void render(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY) {

    }

}
