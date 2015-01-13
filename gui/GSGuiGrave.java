package gravestone.gui;

import gravestone.ModGraveStone;
import gravestone.core.GSMessageHandler;
import gravestone.packets.GraveDeathMessageToServer;
import gravestone.tileentity.TileEntityGSGrave;
import gravestone.tileentity.TileEntityGSMemorial;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.lwjgl.input.Keyboard;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSGuiGrave extends GuiScreen {

    private final String titleStr = ModGraveStone.proxy.getLocalizedString("gui.edit_grave.title");
    private final String closeStr = ModGraveStone.proxy.getLocalizedString("gui.edit_grave.close");
    private final String randomTextStr = ModGraveStone.proxy.getLocalizedString("gui.edit_grave.randomText");
    private GuiButton closeButton;
    private GuiButton randomTextButton;
    private GuiTextField textField;
    private TileEntityGSGrave teGrave;
    private boolean isRandomTextButtonClicked = false;

    public GSGuiGrave(TileEntityGSGrave teGrave) {
        this.teGrave = teGrave;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui() {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(randomTextButton = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 95, randomTextStr));
        this.buttonList.add(closeButton = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, closeStr));
        this.textField = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, 100, 200, 20);
        this.textField.setText("");
        this.textField.setEnabled(true);
        this.textField.setFocused(true);
        this.textField.setCanLoseFocus(false);
        this.textField.setMaxStringLength(30);
    }

    @Override
    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                mc.displayGuiScreen((GuiScreen) null);
                break;
            case 1:
                isRandomTextButtonClicked = true;
                mc.displayGuiScreen((GuiScreen) null);
                break;
        }
        GSMessageHandler.networkWrapper.sendToServer(new GraveDeathMessageToServer(teGrave.getWorld(), teGrave.getPos().getX(), teGrave.getPos().getY(), teGrave.getPos().getZ(), this.textField.getText(), isRandomTextButtonClicked));
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        drawDefaultBackground();
        this.drawString(this.fontRendererObj, titleStr, this.width / 2 - 40, 60, 16777215);
        this.textField.drawTextBox();
        super.drawScreen(x, y, f);
    }

    /**
     * Fired when a key is typed.
     */
    @Override
    protected void keyTyped(char key, int keyCode) {
        this.textField.textboxKeyTyped(key, keyCode);

        if (keyCode == 1) {
            actionPerformed(closeButton);
        }
    }
}
