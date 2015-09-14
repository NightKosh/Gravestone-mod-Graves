package gravestone.gui.slider;

import gravestone.ModGraveStone;
import gravestone.block.enums.EnumGraveMaterial;
import gravestone.block.enums.EnumGraves;
import gravestone.gui.GSChiselCraftingGui;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ChiselTypeSlider extends AbstractSlider {
    GSChiselCraftingGui gui;

    public ChiselTypeSlider(int id, int xPos, int yPos, int width, int height, double currentVal, GSChiselCraftingGui gui) {
        super(id, xPos, yPos, width, height, "", "", 0, EnumGraves.EnumGraveType.values().length - 2, currentVal, false, false, (slider) -> {

            gui.setType(EnumGraves.EnumGraveType.values()[slider.getValueInt()]);
            gui.sendMessage();
        });

        this.gui = gui;
    }

    @Override
    public String getString() {
        int num = this.getValueInt() * EnumGraveMaterial.values().length;
        if (this.gui != null) {
            num += this.gui.getMaterial().ordinal();
        }
        return ModGraveStone.proxy.getLocalizedString(EnumGraves.getById(num).getUnLocalizedName() + ".name");
    }
}