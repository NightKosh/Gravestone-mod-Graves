package gravestone.models.block.graves;

import gravestone.models.block.ModelGraveStone;
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
public class ModelMemorialObelisk extends ModelGraveStone {

    private ModelRenderer plate1;
    private ModelRenderer plate2;
    private ModelRenderer pillar1;
    private ModelRenderer pillar2;
    private ModelRenderer pillar3;

    public ModelMemorialObelisk() {
        textureWidth = 256;
        textureHeight = 128;
        plate1 = new ModelRenderer(this, 0, 0);
        plate1.addBox(0, 0, 0, 48, 8, 48);
        plate1.setRotationPoint(-24, 16, -24);
        plate1.setTextureSize(256, 128);
        plate1.mirror = true;
        setRotation(plate1, 0, 0, 0);
        plate2 = new ModelRenderer(this, 0, 56);
        plate2.addBox(0, 0, 0, 32, 8, 32);
        plate2.setRotationPoint(-16, 8, -16);
        plate2.setTextureSize(256, 128);
        plate2.mirror = true;
        setRotation(plate2, 0, 0, 0);
        pillar1 = new ModelRenderer(this, 128, 56);
        pillar1.addBox(0, 0, 0, 20, 20, 20);
        pillar1.setRotationPoint(-10, -12, -10);
        pillar1.setTextureSize(256, 128);
        pillar1.mirror = true;
        setRotation(pillar1, 0, 0, 0);
        pillar2 = new ModelRenderer(this, 144, 0);
        pillar2.addBox(0, 0, 0, 16, 23, 16);
        pillar2.setRotationPoint(-8, -35, -8);
        pillar2.setTextureSize(256, 128);
        pillar2.mirror = true;
        setRotation(pillar2, 0, 0, 0);
        pillar3 = new ModelRenderer(this, 0, 0);
        pillar3.addBox(0, 0, 0, 12, 27, 12);
        pillar3.setRotationPoint(-6, -62, -6);
        pillar3.setTextureSize(256, 128);
        pillar3.mirror = true;
        setRotation(pillar3, 0, 0, 0);
    }

    @Override
    public void renderAll() {
        plate1.render(0.0625F);
        plate2.render(0.0625F);
        pillar1.render(0.0625F);
        pillar2.render(0.0625F);
        pillar3.render(0.0625F);
    }
}
