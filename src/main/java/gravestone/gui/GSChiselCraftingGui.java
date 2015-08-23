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
import net.minecraftforge.fml.client.config.GuiCheckBox;
import net.minecraftforge.fml.client.config.GuiSlider;
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
    private final int TYPE_SLIDER_ID = 3;
    private final int MATERIAL_SLIDER_ID = 4;

    private final int IS_ENCHANTED_CHECKBOX_ID = 5;
    private final int IS_MOSSY_CHECKBOX_ID = 6;

    private final String GRAVE_BUTTON_STR = "Gravestone";//ModGraveStone.proxy.getLocalizedString("gui.edit_grave.title");
    private final String MEMORIAL_BUTTON_STR = "Memorial";//ModGraveStone.proxy.getLocalizedString("gui.edit_grave.close");
    private final String GRAVE_STR = "Object to craft";//ModGraveStone.proxy.getLocalizedString("gui.edit_grave.close");
    private final String TYPE_STR = "Choose type";//ModGraveStone.proxy.getLocalizedString("gui.edit_grave.close");
    private final String MATERIAL_STR = "Choose material";//ModGraveStone.proxy.getLocalizedString("gui.edit_grave.close");

    private GuiButton graveButton;
    private GuiButton memorialButton;

    private GuiSlider typeSlider;

    private GuiSlider materialSlider;

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
        this.buttonList.add(graveButton = new GuiButton(GRAVE_BUTTON_ID, (width - xSize) / 2, 20, 75, 20, this.GRAVE_BUTTON_STR));
        this.buttonList.add(memorialButton = new GuiButton(1, (width - xSize) / 2 + 100, 20, 75, 20, this.MEMORIAL_BUTTON_STR));

        //type
        GuiSlider.ISlider iTypeSlider = new GuiSlider.ISlider() {

            public void onChangeSliderValue(GuiSlider slider) {

                graveType = EnumGraves.EnumGraveType.values()[typeSlider.getValueInt()];
            }
        };

        this.buttonList.add(typeSlider = new GuiSlider(TYPE_SLIDER_ID, (width - xSize) / 2, 45, 176, 20, "", "", 0,
                EnumGraves.EnumGraveType.values().length - 1, 0, false, false, iTypeSlider));

        //material
        GuiSlider.ISlider iMaterialSlider = new GuiSlider.ISlider() {

            public void onChangeSliderValue(GuiSlider slider) {

                material = EnumGraveMaterial.values()[materialSlider.getValueInt()];
            }
        };
        this.buttonList.add(materialSlider = new GuiSlider(MATERIAL_SLIDER_ID, (width - xSize) / 2, 70, 176, 20, "", "", 0,
                EnumGraveMaterial.values().length - 1, 0, false, false, iMaterialSlider));

        this.buttonList.add(isEnchantedButton = new GuiCheckBox(IS_ENCHANTED_CHECKBOX_ID, 125, 95, "Is Enchanted", false));
        this.buttonList.add(isMossyButton = new GuiCheckBox(IS_MOSSY_CHECKBOX_ID, 200, 95, "Is Mossy", false));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1, 1, 1, 1);
        this.mc.renderEngine.bindTexture(Resources.INVENTORY);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        this.drawString(this.fontRendererObj, this.GRAVE_STR, this.width / 2 - 150, 25, 16777215);
        this.drawString(this.fontRendererObj, this.TYPE_STR, this.width / 2 - 150, 50, 16777215);
        this.drawString(this.fontRendererObj, this.MATERIAL_STR, this.width / 2 - 150, 75, 16777215);

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

            case IS_ENCHANTED_CHECKBOX_ID:
                isEnchanted = !isEnchanted;
                break;
            case IS_MOSSY_CHECKBOX_ID:
                isMossy = !isMossy;
                break;
        }
    }
}
