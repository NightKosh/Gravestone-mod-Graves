package gravestone.gui;

import gravestone.block.enums.EnumGraveMaterial;
import gravestone.block.enums.EnumGraves;
import gravestone.core.GSMessageHandler;
import gravestone.core.Resources;
import gravestone.gui.container.ChiselContainer;
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

    private final int GRAVE_BUTTON_ID = 0;
    private final int MEMORIAL_BUTTON_ID = 1;
    //TODO id == 2 may be needed for additional types
    private final int TYPE_BUTTON_1_ID = 3;
    private final int TYPE_BUTTON_2_ID = 4;
    private final int TYPE_BUTTON_3_ID = 5;
    private final int TYPE_BUTTON_4_ID = 6;
    private final int TYPE_BUTTON_5_ID = 7;
    private final int TYPE_BUTTON_6_ID = 8;
    private final int TYPE_BUTTON_7_ID = 9;
    private final int TYPE_BUTTON_8_ID = 10;
    private final int TYPE_BUTTON_9_ID = 11;
    private final int TYPE_BUTTON_10_ID = 12;
    private final int TYPE_BUTTON_11_ID = 13;
    private final int TYPE_BUTTON_12_ID = 14;

    private final int MATERIAL_BUTTON_1_ID = 15;
    private final int MATERIAL_BUTTON_2_ID = 16;
    private final int MATERIAL_BUTTON_3_ID = 17;
    private final int MATERIAL_BUTTON_4_ID = 18;
    private final int MATERIAL_BUTTON_5_ID = 19;
    private final int MATERIAL_BUTTON_6_ID = 20;
    private final int MATERIAL_BUTTON_7_ID = 21;
    private final int MATERIAL_BUTTON_8_ID = 22;
    private final int MATERIAL_BUTTON_9_ID = 23;
    private final int MATERIAL_BUTTON_10_ID = 24;
    private final int MATERIAL_BUTTON_11_ID = 25;
    private final int MATERIAL_BUTTON_12_ID = 26;
    private final int MATERIAL_BUTTON_13_ID = 27;
    private final int MATERIAL_BUTTON_14_ID = 28;
    private final int MATERIAL_BUTTON_15_ID = 29;
    private final int MATERIAL_BUTTON_16_ID = 30;
    private final int MATERIAL_BUTTON_17_ID = 31;
    private final int MATERIAL_BUTTON_18_ID = 32;
    private final int MATERIAL_BUTTON_19_ID = 33;
    private final int MATERIAL_BUTTON_20_ID = 34;
    private final int MATERIAL_BUTTON_21_ID = 35;
    private final int MATERIAL_BUTTON_22_ID = 36;
    private final int MATERIAL_BUTTON_23_ID = 37;
    private final int MATERIAL_BUTTON_24_ID = 38;
    private final int MATERIAL_BUTTON_25_ID = 39;

    private final int IS_ENCHANTED_BUTTON_ID = 40;
    private final int IS_MOSSY_BUTTON_ID = 41;

    private final String GRAVE_BUTTON_STR = "Gravestone";//ModGraveStone.proxy.getLocalizedString("gui.edit_grave.title");
    private final String MEMORIAL_BUTTON_STR = "Memorial";//ModGraveStone.proxy.getLocalizedString("gui.edit_grave.close");
    private final String TYPE_STR = "Object to craft";//ModGraveStone.proxy.getLocalizedString("gui.edit_grave.close");

    private GuiButton graveButton;
    private GuiButton memorialButton;

    private GuiButton typeButton1;
    private GuiButton typeButton2;
    private GuiButton typeButton3;
    private GuiButton typeButton4;
    private GuiButton typeButton5;
    private GuiButton typeButton6;
    private GuiButton typeButton7;
    private GuiButton typeButton8;
    private GuiButton typeButton9;
    private GuiButton typeButton10;
    private GuiButton typeButton11;
    private GuiButton typeButton12;

    private GuiButton materialButton1;
    private GuiButton materialButton2;
    private GuiButton materialButton3;
    private GuiButton materialButton4;
    private GuiButton materialButton5;
    private GuiButton materialButton6;
    private GuiButton materialButton7;
    private GuiButton materialButton8;
    private GuiButton materialButton9;
    private GuiButton materialButton10;
    private GuiButton materialButton11;
    private GuiButton materialButton12;
    private GuiButton materialButton13;
    private GuiButton materialButton14;
    private GuiButton materialButton15;
    private GuiButton materialButton16;
    private GuiButton materialButton17;
    private GuiButton materialButton18;
    private GuiButton materialButton19;
    private GuiButton materialButton20;
    private GuiButton materialButton21;
    private GuiButton materialButton22;
    private GuiButton materialButton23;
    private GuiButton materialButton24;
    private GuiButton materialButton25;

    private GuiButton isEnchantedButton;
    private GuiButton isMossyButton;

    private boolean isGravestone = true;
    private EnumGraves.EnumGraveType graveType = EnumGraves.EnumGraveType.VERTICAL_PLATE;
    private EnumGraveMaterial material = EnumGraveMaterial.STONE;
    private boolean isEnchanted = false;
    private boolean isMossy = false;
    //TODO sword;

    public GSChiselCraftingGui(InventoryPlayer inventoryPlayer) {
        super(new ChiselContainer(inventoryPlayer));
        this.player = inventoryPlayer.player;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(graveButton = new GuiButton(GRAVE_BUTTON_ID, (width - xSize) / 2, (height - ySize) / 2, 75, 20, this.GRAVE_BUTTON_STR));
        this.buttonList.add(memorialButton = new GuiButton(1, (width - xSize) / 2 + 100, (height - ySize) / 2, 75, 20, this.MEMORIAL_BUTTON_STR));

        //type
        this.buttonList.add(typeButton1 = new GuiButton(TYPE_BUTTON_1_ID, (width - xSize) / 2, (height - ySize) / 2 + 25, 20, 20, ""));
        this.buttonList.add(typeButton2 = new GuiButton(TYPE_BUTTON_2_ID, (width - xSize) / 2 + 25, (height - ySize) / 2 + 25, 20, 20, ""));
        this.buttonList.add(typeButton3 = new GuiButton(TYPE_BUTTON_3_ID, (width - xSize) / 2 + 50, (height - ySize) / 2 + 25, 20, 20, ""));
        this.buttonList.add(typeButton4 = new GuiButton(TYPE_BUTTON_4_ID, (width - xSize) / 2 + 75, (height - ySize) / 2 + 25, 20, 20, ""));
        this.buttonList.add(typeButton5 = new GuiButton(TYPE_BUTTON_5_ID, (width - xSize) / 2 + 100, (height - ySize) / 2 + 25, 20, 20, ""));
        this.buttonList.add(typeButton6 = new GuiButton(TYPE_BUTTON_6_ID, (width - xSize) / 2 + 125, (height - ySize) / 2 + 25, 20, 20, ""));
        this.buttonList.add(typeButton7 = new GuiButton(TYPE_BUTTON_7_ID, (width - xSize) / 2 + 150, (height - ySize) / 2 + 25, 20, 20, ""));
        this.buttonList.add(typeButton8 = new GuiButton(TYPE_BUTTON_8_ID, (width - xSize) / 2 + 175, (height - ySize) / 2 + 25, 20, 20, ""));
        this.buttonList.add(typeButton9 = new GuiButton(TYPE_BUTTON_9_ID, (width - xSize) / 2 + 200, (height - ySize) / 2 + 25, 20, 20, ""));
        this.buttonList.add(typeButton10 = new GuiButton(TYPE_BUTTON_10_ID, (width - xSize) / 2 + 225, (height - ySize) / 2 + 25, 20, 20, ""));
        this.buttonList.add(typeButton11 = new GuiButton(TYPE_BUTTON_11_ID, (width - xSize) / 2 + 250, (height - ySize) / 2 + 25, 20, 20, ""));
        this.buttonList.add(typeButton12 = new GuiButton(TYPE_BUTTON_12_ID, (width - xSize) / 2 + 275, (height - ySize) / 2 + 25, 20, 20, ""));
        //TODO not all buttons

        //material
        this.buttonList.add(materialButton1 = new GuiButton(MATERIAL_BUTTON_1_ID, (width - xSize) / 2, (height - ySize) / 2 + 50, 20, 20, ""));
        this.buttonList.add(materialButton2 = new GuiButton(MATERIAL_BUTTON_2_ID, (width - xSize) / 2 + 25, (height - ySize) / 2 + 50, 20, 20, ""));
        this.buttonList.add(materialButton3 = new GuiButton(MATERIAL_BUTTON_3_ID, (width - xSize) / 2 + 50, (height - ySize) / 2 + 50, 20, 20, ""));
        this.buttonList.add(materialButton4 = new GuiButton(MATERIAL_BUTTON_4_ID, (width - xSize) / 2 + 75, (height - ySize) / 2 + 50, 20, 20, ""));
        this.buttonList.add(materialButton5 = new GuiButton(MATERIAL_BUTTON_5_ID, (width - xSize) / 2 + 100, (height - ySize) / 2 + 50, 20, 20, ""));
        this.buttonList.add(materialButton6 = new GuiButton(MATERIAL_BUTTON_6_ID, (width - xSize) / 2 + 125, (height - ySize) / 2 + 50, 20, 20, ""));
        this.buttonList.add(materialButton7 = new GuiButton(MATERIAL_BUTTON_7_ID, (width - xSize) / 2 + 150, (height - ySize) / 2 + 50, 20, 20, ""));
        this.buttonList.add(materialButton8 = new GuiButton(MATERIAL_BUTTON_8_ID, (width - xSize) / 2 + 175, (height - ySize) / 2 + 50, 20, 20, ""));
        this.buttonList.add(materialButton9 = new GuiButton(MATERIAL_BUTTON_9_ID, (width - xSize) / 2 + 200, (height - ySize) / 2 + 50, 20, 20, ""));
        this.buttonList.add(materialButton10 = new GuiButton(MATERIAL_BUTTON_10_ID, (width - xSize) / 2 + 225, (height - ySize) / 2 + 50, 20, 20, ""));
        this.buttonList.add(materialButton11 = new GuiButton(MATERIAL_BUTTON_11_ID, (width - xSize) / 2 + 250, (height - ySize) / 2 + 50, 20, 20, ""));
        this.buttonList.add(materialButton12 = new GuiButton(MATERIAL_BUTTON_12_ID, (width - xSize) / 2 + 275, (height - ySize) / 2 + 50, 20, 20, ""));
        //TODO not all materials' buttons

        this.buttonList.add(isEnchantedButton = new GuiButton(IS_ENCHANTED_BUTTON_ID, (width - xSize) / 2, (height - ySize) / 2 + 75, 75, 20, "Is Enchanted"));
        this.buttonList.add(isMossyButton = new GuiButton(IS_MOSSY_BUTTON_ID, (width - xSize) / 2 + 100, (height - ySize) / 2 + 75, 75, 20, "Is Mossy"));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1, 1, 1, 1);
        this.mc.renderEngine.bindTexture(Resources.INVENTORY);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        this.drawString(this.fontRendererObj, this.TYPE_STR, this.width / 2 - 40, (height - ySize) / 2 + 55, 16777215);

        if (isGravestone) {
            TileEntityGSGraveStoneRenderer.instance.renderGraveInGui(350, 0, player.worldObj, EnumGraves.getByTypeAndMaterial(graveType, material), isEnchanted, isMossy, false, null, par1);
        }
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
            case GRAVE_BUTTON_ID:
                GSMessageHandler.networkWrapper.sendToServer(new ChiselMessageToServer(player, true, graveType.ordinal(), material.ordinal(), isEnchanted, isMossy));
                this.isGravestone = true;
                break;
            case MEMORIAL_BUTTON_ID:
                GSMessageHandler.networkWrapper.sendToServer(new ChiselMessageToServer(player, false, graveType.ordinal(), material.ordinal(), isEnchanted, isMossy));
                this.isGravestone = false;
                break;
            case TYPE_BUTTON_1_ID:
                this.graveType = EnumGraves.EnumGraveType.values()[0];
                break;
            case TYPE_BUTTON_2_ID:
                this.graveType = EnumGraves.EnumGraveType.values()[1];
                break;
            case TYPE_BUTTON_3_ID:
                this.graveType = EnumGraves.EnumGraveType.values()[2];
                break;
            case TYPE_BUTTON_4_ID:
                this.graveType = EnumGraves.EnumGraveType.values()[3];
                break;
            case TYPE_BUTTON_5_ID:
                this.graveType = EnumGraves.EnumGraveType.values()[4];
                break;
            case TYPE_BUTTON_6_ID:
                this.graveType = EnumGraves.EnumGraveType.values()[5];
                break;
            case TYPE_BUTTON_7_ID:
                this.graveType = EnumGraves.EnumGraveType.values()[6];
                break;
            case TYPE_BUTTON_8_ID:
                //no more graves
                break;
            case TYPE_BUTTON_9_ID:
                break;
            case TYPE_BUTTON_10_ID:
                break;
            case TYPE_BUTTON_11_ID:
                break;
            case TYPE_BUTTON_12_ID:
                break;

            case MATERIAL_BUTTON_1_ID:
                material = EnumGraveMaterial.values()[0];
                break;
            case MATERIAL_BUTTON_2_ID:
                material = EnumGraveMaterial.values()[1];
                break;
            case MATERIAL_BUTTON_3_ID:
                material = EnumGraveMaterial.values()[2];
                break;
            case MATERIAL_BUTTON_4_ID:
                material = EnumGraveMaterial.values()[3];
                break;
            case MATERIAL_BUTTON_5_ID:
                material = EnumGraveMaterial.values()[4];
                break;
            case MATERIAL_BUTTON_6_ID:
                material = EnumGraveMaterial.values()[5];
                break;
            case MATERIAL_BUTTON_7_ID:
                material = EnumGraveMaterial.values()[6];
                break;
            case MATERIAL_BUTTON_8_ID:
                material = EnumGraveMaterial.values()[7];
                break;
            case MATERIAL_BUTTON_9_ID:
                material = EnumGraveMaterial.values()[8];
                break;
            case MATERIAL_BUTTON_10_ID:
                material = EnumGraveMaterial.values()[9];
                break;
            case MATERIAL_BUTTON_11_ID:
                material = EnumGraveMaterial.values()[10];
                break;
            case MATERIAL_BUTTON_12_ID:
                material = EnumGraveMaterial.values()[11];
                break;
            case MATERIAL_BUTTON_13_ID:
                material = EnumGraveMaterial.values()[12];
                break;
            case MATERIAL_BUTTON_14_ID:
                material = EnumGraveMaterial.values()[13];
                break;
            case MATERIAL_BUTTON_15_ID:
                material = EnumGraveMaterial.values()[14];
                break;
            case MATERIAL_BUTTON_16_ID:
                material = EnumGraveMaterial.values()[15];
                break;
            case MATERIAL_BUTTON_17_ID:
                material = EnumGraveMaterial.values()[16];
                break;
            case MATERIAL_BUTTON_18_ID:
                //no more materials
                break;
            case MATERIAL_BUTTON_19_ID:
                break;
            case MATERIAL_BUTTON_20_ID:
                break;
            case MATERIAL_BUTTON_21_ID:
                break;
            case MATERIAL_BUTTON_22_ID:
                break;
            case MATERIAL_BUTTON_23_ID:
                break;
            case MATERIAL_BUTTON_24_ID:
                break;
            case MATERIAL_BUTTON_25_ID:
                break;

            case IS_ENCHANTED_BUTTON_ID:
                isEnchanted = !isEnchanted;
                break;
            case IS_MOSSY_BUTTON_ID:
                isMossy = !isMossy;
                break;
        }
    }
}
