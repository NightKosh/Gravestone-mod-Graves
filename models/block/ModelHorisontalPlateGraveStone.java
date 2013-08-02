
package GraveStone.models.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelHorisontalPlateGraveStone extends ModelGraveStone {

    ModelRenderer Plate;

    public ModelHorisontalPlateGraveStone() {
        textureWidth = 64;
        textureHeight = 32;

        Plate = new ModelRenderer(this, 0, 0);
        Plate.addBox(0F, 0F, 0F, 12, 1, 14);
        Plate.setRotationPoint(-6F, 23F, -7F);
        Plate.setTextureSize(64, 32);
        Plate.mirror = true;
        setRotation(Plate, 0F, 0F, 0F);
    }

    @Override
    public void renderAll() {
        Plate.render(0.0625F);
    }
}
