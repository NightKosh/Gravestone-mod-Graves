package nightkosh.gravestone.models.block.graves;

import nightkosh.gravestone.models.block.ModelGraveStone;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelVerticalPlateGraveStone extends ModelGraveStone {

    ModelRenderer Plate;

    public ModelVerticalPlateGraveStone() {
        textureWidth = 32;
        textureHeight = 32;
        Plate = new ModelRenderer(this, 0, 0);
        Plate.addBox(0, 0, 0, 12, 15, 2);
        Plate.setRotationPoint(-6, 9, 5);
        Plate.setTextureSize(32, 32);
        Plate.mirror = true;
        setRotation(Plate, 0, 0, 0);
    }

    @Override
    public void renderAll() {
        Plate.render(0.0625F);
    }
}
