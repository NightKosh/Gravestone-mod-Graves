package nightkosh.gravestone.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import nightkosh.gravestone.block_entity.GraveStoneBlockEntity;
import nightkosh.gravestone.core.GSMessages;
import nightkosh.gravestone.packet.ChiselMessageToServer;

import javax.annotation.Nonnull;

/**
 * GraveStone mod
 *
 * @author metroidfood
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ChiselScreen extends Screen {

    private final Component title = Component.translatable("gui.gravestone.chisel.title");
    private final GraveStoneBlockEntity grave;

    private EditBox nameField;

    public ChiselScreen(GraveStoneBlockEntity grave) {
        super(Component.empty());
        this.grave = grave;
    }

    public static void open(GraveStoneBlockEntity grave) {
        Minecraft.getInstance().setScreen(new ChiselScreen(grave));
    }

    @Override
    public void init() {
        int x = this.width / 2;
        int y = this.height / 4;

        this.nameField = new EditBox(this.font, x - 50, 100, 100, 20,
                null, Component.empty());
        this.nameField.setMaxLength(32);
        this.nameField.setFocused(true);
        this.nameField.setCanLoseFocus(false);
        this.nameField.moveCursorToEnd(false);

        this.addRenderableWidget(
                Button.builder(CommonComponents.GUI_DONE, (button) -> {
                            grave.setDeathMessageJson(this.nameField.getValue());
                            GSMessages.sendToServer(
                                    new ChiselMessageToServer(
                                            this.grave.getBlockPos().getX(),
                                            this.grave.getBlockPos().getY(),
                                            this.grave.getBlockPos().getZ(),
                                            this.nameField.getValue())
                            );
                            this.minecraft.setScreen(null); // close GUI
                        })
                        .bounds(x + 5, y + 120, 55, 20)
                        .build()
        );
        this.addRenderableWidget(nameField);
    }

    @Override
    public void render(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 60, 0xffffff);
    }

    @Override
    public boolean keyPressed(@Nonnull KeyEvent keyEvent) {
        this.nameField.keyPressed(keyEvent);
        return super.keyPressed(keyEvent);
    }

    @Override
    public boolean charTyped(@Nonnull CharacterEvent characterEvent) {
        return this.nameField.charTyped(characterEvent);
    }

}
