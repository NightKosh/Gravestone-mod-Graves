package GraveStone.gui;

import GraveStone.tileentity.TileEntityGSGrave;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GSGuiGrave extends GuiScreen {

    private static final String allowedCharacters = ChatAllowedCharacters.allowedCharacters;
    private GuiButton button;
    final int xSizeOfTexture = 192, ySizeOfTexture = 135;
    int posX, posY;
    private TileEntityGSGrave entityGrave;
    private StringBuilder graveText = new StringBuilder();

    public GSGuiGrave(TileEntityGSGrave tileEntity) {
        entityGrave = tileEntity;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui() {
        this.buttonList.clear();

        posX = (this.width - xSizeOfTexture) / 2;
        posY = (this.height - ySizeOfTexture) / 2;

        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(button = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, "Done"));
        entityGrave.setEditable(false);
    }

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

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture("/mods/GraveStone/textures/gui/DeathMessageBackground.png");

        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
        this.drawString(fontRenderer, "Set grave text", posX + 20, posY + 31, 16777215);
        this.drawString(fontRenderer, graveText.toString(), posX + 20, posY + 41, 16777215);
        super.drawScreen(x, y, f);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat
     * events
     */
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);

        Packet250CustomPayload packet = new Packet250CustomPayload();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        try {
            data.writeInt(entityGrave.xCoord);
            data.writeInt(entityGrave.yCoord);
            data.writeInt(entityGrave.zCoord);
            data.writeUTF(graveText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        packet.channel = "GSDeathText";
        packet.data = bytes.toByteArray();
        packet.length = packet.data.length;
        ModLoader.getMinecraftInstance().getNetHandler().addToSendQueue(packet);
        
        //ModLoader.getMinecraftServerInstance().getConfigurationManager().sendPacketToAllPlayers(packet);
        entityGrave.setDeathText(graveText.toString());


        entityGrave.setEditable(true);
    }

    /**
     * Fired when a key is typed. This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char key, int keyCode) {
        if (keyCode == 14 && graveText.length() > 0) {
            graveText.deleteCharAt(graveText.length() - 1);
        }

        if (allowedCharacters.indexOf(key) >= 0 && graveText.length() < 30) {
            graveText.append(key);
        }

        if (keyCode == 1) {
            actionPerformed(button);
        }
    }
}
