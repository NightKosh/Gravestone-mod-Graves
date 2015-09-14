package gravestone.gui.slider;

import gravestone.ModGraveStone;
import gravestone.block.enums.EnumGraveMaterial;
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
public class ChiselMaterialSlider extends AbstractSlider {

    public ChiselMaterialSlider(int id, int xPos, int yPos, int width, int height, double currentVal, GSChiselCraftingGui gui) {
        super(id, xPos, yPos, width, height, "", "", 0, EnumGraveMaterial.values().length - 2, currentVal, false, false, (slider) -> {

            gui.setMaterial(EnumGraveMaterial.values()[slider.getValueInt()]);
            gui.sendMessage();
        });
    }

    @Override
    public String getString() {
        return new StringBuilder(ModGraveStone.proxy.getLocalizedString("material.title")).append(" ").append(EnumGraveMaterial.values()[this.getValueInt()].getLocalizedMaterial()).toString();
    }
}
