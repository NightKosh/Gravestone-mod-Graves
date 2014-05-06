package gravestone.gui;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import gravestone.ModGraveStone;
import gravestone.tileentity.TileEntityGSGrave;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.network.NetHandlerPlayClient;
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
        this.entityGrave = tileEntity;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui() {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(button = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, "Done"));
        this.textField = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, 100, 200, 20);
        this.textField.setText("");
        this.textField.setEnabled(true);
        this.textField.setFocused(true);
        this.textField.setCanLoseFocus(false);
        this.textField.setMaxStringLength(30);
        this.entityGrave.setEditable(false);
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
        this.drawString(this.fontRendererObj, titleStr, this.width / 2 - 40, 60, 16777215);
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

            ByteBuf buf = Unpooled.compositeBuffer();
            buf.writeInt(entityGrave.xCoord);
            buf.writeInt(entityGrave.yCoord);
            buf.writeInt(entityGrave.zCoord);
            ByteBufUtils.writeUTF8String(buf, text);

            FMLProxyPacket packet = new FMLProxyPacket(buf, "GSDeathText");

            NetHandlerPlayClient nethandlerplayclient = this.mc.getNetHandler();


            if (nethandlerplayclient != null) {
                nethandlerplayclient.addToSendQueue(packet);//new C12PacketUpdateSign(this.tileSign.xCoord, this.tileSign.yCoord, this.tileSign.zCoord, this.tileSign.signText));
            }

            entityGrave.getDeathTextComponent().setDeathText(text);
        }
        entityGrave.setEditable(true);
    }

    /**
     * Fired when a key is typed.
     */
    @Override
    protected void keyTyped(char key, int keyCode) {
        this.textField.textboxKeyTyped(key, keyCode);

        if (keyCode == 1) {
            actionPerformed(button);
        }
    }
}
