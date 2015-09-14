package gravestone.gui;

import gravestone.block.enums.EnumGraveMaterial;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumMemorials;
import gravestone.core.GSMessageHandler;
import gravestone.core.Resources;
import gravestone.gui.container.ChiselContainer;
import gravestone.gui.slider.ChiselMaterialSlider;
import gravestone.gui.slider.ChiselTypeSlider;
import gravestone.packets.ChiselMessageToServer;
import gravestone.renderer.tileentity.TileEntityGSGraveStoneRenderer;
import gravestone.renderer.tileentity.TileEntityGSMemorialRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
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
    private final String REQUIRED_ITEMS_STR = "Required items";//ModGraveStone.proxy.getLocalizedString("gui.edit_grave.close");

    private GuiButton graveButton;
    private GuiButton memorialButton;

    private GuiSlider typeSlider;

    private GuiSlider materialSlider;

    private GuiButton isEnchantedButton;
    private GuiButton isMossyButton;

    private boolean isGravestone = ChiselContainer.IS_GRAVESTONE;
    private EnumGraves.EnumGraveType graveType = ChiselContainer.TYPE;
    private EnumMemorials.EnumMemorialType memorialType = EnumMemorials.EnumMemorialType.CROSS;
    private EnumGraveMaterial material = ChiselContainer.MATERIAL;
    private boolean isEnchanted = ChiselContainer.IS_ENCHANTED;
    private boolean isMossy = ChiselContainer.IS_MOSSY;
    //TODO sword;

    public GSChiselCraftingGui(EntityPlayer player, InventoryPlayer inventoryPlayer) {
        super(new ChiselContainer(player, inventoryPlayer));
        this.player = player;
    }

    @Override
    public void initGui() {
        super.initGui();
        final int HALF_W = (width - xSize) / 2;

        this.buttonList.add(graveButton = new GuiButton(GRAVE_BUTTON_ID, HALF_W, 20, 75, 20, this.GRAVE_BUTTON_STR));
        this.buttonList.add(memorialButton = new GuiButton(1, HALF_W + 100, 20, 75, 20, this.MEMORIAL_BUTTON_STR));
        graveButton.enabled = false;
        //type
        this.buttonList.add(typeSlider = new ChiselTypeSlider(TYPE_SLIDER_ID, HALF_W, 45, 176, 20, 0, this));

        //material
        this.buttonList.add(materialSlider = new ChiselMaterialSlider(MATERIAL_SLIDER_ID, HALF_W, 70, 176, 20, 0, this));

        this.buttonList.add(isEnchantedButton = new GuiCheckBox(IS_ENCHANTED_CHECKBOX_ID, 125, 95, "Enchanted", false));
        this.buttonList.add(isMossyButton = new GuiCheckBox(IS_MOSSY_CHECKBOX_ID, 200, 95, "Mossy", false));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1, 1, 1, 1);
        this.mc.renderEngine.bindTexture(Resources.CHISEL_GUI);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2 + 30;

        this.drawTexturedModalRect(x, y, 0, 0, 256, ySize);

        this.drawString(this.fontRendererObj, this.REQUIRED_ITEMS_STR, 310, 170, 16777215);

        if (isGravestone) {
            TileEntityGSGraveStoneRenderer.instance.renderGraveInGui(350, 0, player.worldObj, EnumGraves.getByTypeAndMaterial(graveType, material), isEnchanted, isMossy, false, null, par1);
        } else {
            TileEntityGSMemorialRenderer.instance.renderMemorialInGui(350, 0, EnumMemorials.getByTypeAndMaterial(memorialType, material), isEnchanted, isMossy, null, 0, par1);
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
                this.isGravestone = true;
                this.graveButton.enabled = false;
                this.memorialButton.enabled = true;
                this.sendMessage();
                break;
            case MEMORIAL_BUTTON_ID:
                this.isGravestone = false;
                this.graveButton.enabled = true;
                this.memorialButton.enabled = false;
                this.sendMessage();
                break;

            case IS_ENCHANTED_CHECKBOX_ID:
                isEnchanted = !isEnchanted;
                this.sendMessage();
                break;
            case IS_MOSSY_CHECKBOX_ID:
                isMossy = !isMossy;
                this.sendMessage();
                break;
        }
    }

    public void sendMessage() {
//        NBTTagCompound playerNbt = new NBTTagCompound();// = player.getEntityData();
//        player.writeEntityToNBT(playerNbt);
//        NBTTagCompound nbt = new NBTTagCompound();
//        NBTTagCompound graveNbt = new NBTTagCompound();
//        graveNbt.setBoolean("IsGravestone", isGravestone);
//        graveNbt.setInteger("GraveType", graveType.ordinal());
//        graveNbt.setInteger("Material", material.ordinal());
//        graveNbt.setBoolean("IsEnchanted", isEnchanted);
//        graveNbt.setBoolean("IsMossy", isMossy);
//        playerNbt.setTag("GraveCrafting", graveNbt);
////            player.writeToNBT(nbt);
//        player.readEntityFromNBT(playerNbt);
//
//        GSMessageHandler.networkWrapper.sendToServer(new ChiselMessageToServer(player, isGravestone, graveType.ordinal(), material.ordinal(), isEnchanted, isMossy));

        ((ChiselContainer) this.inventorySlots).isGravestone = isGravestone;
        ((ChiselContainer) this.inventorySlots).graveType = graveType;
        ((ChiselContainer) this.inventorySlots).material = material;
        ((ChiselContainer) this.inventorySlots).isEnchanted = isEnchanted;
        ((ChiselContainer) this.inventorySlots).isMossy = isMossy;
    }

    public void setType(EnumGraves.EnumGraveType graveType) {
        this.graveType = graveType;
    }

    public void setMaterial(EnumGraveMaterial material) {
        this.material = material;
    }

    public EnumGraveMaterial getMaterial() {
        return material;
    }
}
