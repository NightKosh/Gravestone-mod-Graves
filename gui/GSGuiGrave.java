package gravestone.gui;

import gravestone.ModGraveStone;
import gravestone.tileentity.TileEntityGSGrave;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.src.ModLoader;
import org.lwjgl.input.Keyboard;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSGuiGrave extends GuiScreen {
    
    private final String titleStr = ModGraveStone.proxy.getLocalizedString("gui.edit_grave");
    private GuiButton button;
    private GuiTextField textField;
    private TileEntityGSGrave entityGrave;

    public GSGuiGrave(TileEntityGSGrave tileEntity) {
        entityGrave = tileEntity;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui() {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(button = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, "Done"));
        this.textField = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 100, 200, 20);
        this.textField.setText("");
        this.textField.setEnabled(true);
        this.textField.setFocused(true);
        this.textField.setCanLoseFocus(false);
        this.textField.setMaxStringLength(30);
        entityGrave.setEditable(false);
    }

    @Override
    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                mc.displayGuiScreen((GuiScreen) null);
                break;
        }
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        drawDefaultBackground();
        this.drawString(fontRenderer, titleStr, this.width / 2 - 40, 60, 16777215);
        this.textField.drawTextBox();
        super.drawScreen(x, y, f);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat
     * events
     */
    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (this.textField != null && !this.textField.getText().isEmpty()) {
            String text = this.textField.getText();
            Packet250CustomPayload packet = new Packet250CustomPayload();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream data = new DataOutputStream(bytes);

            try {
                data.writeInt(entityGrave.xCoord);
                data.writeInt(entityGrave.yCoord);
                data.writeInt(entityGrave.zCoord);
                data.writeUTF(text);
            } catch (IOException e) {
                e.printStackTrace();
            }

            packet.channel = "GSDeathText";
            packet.data = bytes.toByteArray();
            packet.length = packet.data.length;
            ModLoader.getMinecraftInstance().getNetHandler().addToSendQueue(packet);
            entityGrave.getDeathTextComponent().setDeathText(text);
        }
        entityGrave.setEditable(true);
    }

    /**
     * Fired when a key is typed. This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e).
     */
    @Override
    protected void keyTyped(char key, int keyCode) {
        this.textField.textboxKeyTyped(key, keyCode);

        if (keyCode == 1) {
            actionPerformed(button);
        }
    }
}
