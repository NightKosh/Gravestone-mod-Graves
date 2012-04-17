package net.minecraft.GraveStone.gui;

import net.minecraft.GraveStone.tileentity.TileEntityGSGrave;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiGrave extends GuiScreen {

    /**
     * This String is just a local copy of the characters allowed in text rendering of minecraft.
     */
    private static final String allowedCharacters = ChatAllowedCharacters.allowedCharacters;
    /** The title string that is displayed in the top-center of the screen. */
    protected String screenTitle = "Set text";
    private GuiButton button;
    
    final int xSizeOfTexture = 192, ySizeOfTexture = 135;
    int posX, posY;
    //If you want your gui to change based on TileEntity values, reference the tile entity in the constructor
    //you must pass the tile entity using "return new BlockGuiWindow(world.getBlockTileEntity(x, y, z))" in the GuiHandler
    private TileEntityGSGrave entityGrave;
    private StringBuilder graveText = new StringBuilder();
    
    public GuiGrave(TileEntityGSGrave tileEntity) {
        entityGrave = tileEntity;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui() {
        this.buttonList.clear();

        posX = (this.width - xSizeOfTexture) / 2;
        posY = (this.height - ySizeOfTexture) / 2;

        //this.buttonList.add(new GuiButton(0, posX + 4, posY + 4, 20, 20, "ButtonText"));
        /*Parameters:
         * button id used when checking what to do when a button is pressed
         * The X position of the button
         * The Y position of the button
         * The width
         * The height (keep this at 20 if you can)
         * The text to be displayed on the button*/
        
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
        mc.renderEngine.bindTexture("/GraveStone/resources/textures/ModelMemorialCross.png");

        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture); //This draws the background
        //Make sure your background texture is a multiple of 256x256.
        //The xSizeOfTexture and ySizeOfTexture assume that the texture is 256x256. so 128 and 128 always reference half of the texture.
        //Look in the Gui class to see what else you can do here (like rendering textures and strings)
        this.drawString(fontRenderer, "Text", posX + 20, posY + 31, 16777215); //this is where the white variable we set up at the beginning is used
        this.drawString(fontRenderer, graveText.toString(), posX + 20, posY + 41, 16777215);
        super.drawScreen(x, y, f);
        /*Here is a trick:
        If you reset the texture after "super.drawScreen(x, y, f);" (this.mc.renderEngine.bindTexture("path/to/the/background/texture");),
        you can draw on top of everything, including buttons.
        Use this to texture buttons, if you don't want them to have text.
         */
    }
    
    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        entityGrave.setDeathText(graveText.toString());
        entityGrave.setEditable(true);
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char key, int keyCode) {
        if (keyCode == 14 && entityGrave.getDeathText().length() > 0) {
            graveText.deleteCharAt(graveText.length() - 1);
        }

        if (allowedCharacters.indexOf(key) >= 0 && entityGrave.getDeathText().length() < 50) {
            graveText.append(key);
        }

        if (keyCode == 1) {
            actionPerformed(button);
        }
    }
}
