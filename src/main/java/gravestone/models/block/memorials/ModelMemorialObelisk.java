package gravestone.models.block.memorials;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import gravestone.models.block.ModelMemorial;
import net.minecraft.client.model.ModelRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelMemorialObelisk extends ModelMemorial {

    ModelRenderer Plate1;
    ModelRenderer Plate2;
    ModelRenderer Pillar1;
    ModelRenderer Pillar2;
    ModelRenderer Pillar3;

    public ModelMemorialObelisk() {
        textureWidth = 256;
        textureHeight = 128;
        Plate1 = new ModelRenderer(this, 0, 0);
        Plate1.addBox(0F, 0F, 0F, 48, 8, 48);
        Plate1.setRotationPoint(-24F, 16F, -24F);
        Plate1.setTextureSize(256, 128);
        Plate1.mirror = true;
        setRotation(Plate1, 0F, 0F, 0F);
        Plate2 = new ModelRenderer(this, 0, 56);
        Plate2.addBox(0F, 0F, 0F, 32, 8, 32);
        Plate2.setRotationPoint(-16F, 8F, -16F);
        Plate2.setTextureSize(256, 128);
        Plate2.mirror = true;
        setRotation(Plate2, 0F, 0F, 0F);
        Pillar1 = new ModelRenderer(this, 128, 56);
        Pillar1.addBox(0F, 0F, 0F, 20, 20, 20);
        Pillar1.setRotationPoint(-10F, -12F, -10F);
        Pillar1.setTextureSize(256, 128);
        Pillar1.mirror = true;
        setRotation(Pillar1, 0F, 0F, 0F);
        Pillar2 = new ModelRenderer(this, 144, 0);
        Pillar2.addBox(0F, 0F, 0F, 16, 23, 16);
        Pillar2.setRotationPoint(-8F, -35F, -8F);
        Pillar2.setTextureSize(256, 128);
        Pillar2.mirror = true;
        setRotation(Pillar2, 0F, 0F, 0F);
        Pillar3 = new ModelRenderer(this, 0, 0);
        Pillar3.addBox(0F, 0F, 0F, 12, 27, 12);
        Pillar3.setRotationPoint(-6F, -62F, -6F);
        Pillar3.setTextureSize(256, 128);
        Pillar3.mirror = true;
        setRotation(Pillar3, 0F, 0F, 0F);
    }

    @Override
    public void renderAll() {
        Plate1.render(0.0625F);
        Plate2.render(0.0625F);
        Pillar1.render(0.0625F);
        Pillar2.render(0.0625F);
        Pillar3.render(0.0625F);
    }
}
