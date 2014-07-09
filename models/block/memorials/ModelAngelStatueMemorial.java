package gravestone.models.block.memorials;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.models.block.ModelMemorial;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelAngelStatueMemorial extends ModelMemorial {

    private ModelRenderer Legs;
    private ModelRenderer Body;
    private ModelRenderer Head;
    private ModelRenderer RightArm;
    private ModelRenderer RightArm2;
    private ModelRenderer LeftArm;
    private ModelRenderer LeftArm2;
    private ModelRenderer RightWing;
    private ModelRenderer RightWing2;
    private ModelRenderer LeftWing;
    private ModelRenderer LeftWing2;
    private ModelBigPedestal pedestal;

    public ModelAngelStatueMemorial() {
        textureWidth = 64;
        textureHeight = 64;
        Legs = new ModelRenderer(this, 0, 16);
        Legs.addBox(0F, 0F, 0F, 6, 11, 3);
        Legs.setRotationPoint(-3F, 13F, 0F);
        Legs.setTextureSize(64, 64);
        Legs.mirror = true;
        setRotation(Legs, 0F, 0F, 0F);
        Body = new ModelRenderer(this, 0, 0);
        Body.addBox(0F, 0F, 0F, 8, 12, 4);
        Body.setRotationPoint(-4F, 1F, -0.5F);
        Body.setTextureSize(64, 64);
        Body.mirror = true;
        setRotation(Body, 0F, 0F, 0F);
        Head = new ModelRenderer(this, 24, 0);
        Head.addBox(0F, 0F, 0F, 8, 8, 8);
        Head.setRotationPoint(-4F, -8F, 1F);
        Head.setTextureSize(64, 64);
        Head.mirror = true;
        setRotation(Head, -1.047198F, 0F, 0F);
        RightArm = new ModelRenderer(this, 18, 16);
        RightArm.addBox(0F, 0F, 0F, 3, 7, 3);
        RightArm.setRotationPoint(-6.5F, 1.5F, 1F);
        RightArm.setTextureSize(64, 64);
        RightArm.mirror = true;
        setRotation(RightArm, -0.837758F, -0.4014257F, 0F);
        RightArm2 = new ModelRenderer(this, 30, 16);
        RightArm2.addBox(0F, 0F, 0F, 3, 7, 3);
        RightArm2.setRotationPoint(-3F, 1F, -4.5F);
        RightArm2.setTextureSize(64, 64);
        RightArm2.mirror = true;
        setRotation(RightArm2, 0.122173F, -0.4363323F, 0.2268928F);
        LeftArm = new ModelRenderer(this, 18, 16);
        LeftArm.addBox(0F, 0F, 0F, 3, 7, 3);
        LeftArm.setRotationPoint(3.5F, 1.5F, 2F);
        LeftArm.setTextureSize(64, 64);
        LeftArm.mirror = true;
        setRotation(LeftArm, -0.837758F, 0.4014257F, 0F);
        LeftArm2 = new ModelRenderer(this, 30, 16);
        LeftArm2.addBox(0F, 0F, 0F, 3, 7, 3);
        LeftArm2.setRotationPoint(0.2F, 2F, -4F);
        LeftArm2.setTextureSize(64, 64);
        LeftArm2.mirror = true;
        setRotation(LeftArm2, 0.296706F, 0.4537856F, -0.1570796F);
        RightWing = new ModelRenderer(this, 28, 30);
        RightWing.addBox(0F, 0F, 0F, 0, 7, 7);
        RightWing.setRotationPoint(-1F, 2F, 2.5F);
        RightWing.setTextureSize(64, 64);
        RightWing.mirror = true;
        setRotation(RightWing, 0.6108652F, -0.5061455F, -0.0872665F);
        RightWing2 = new ModelRenderer(this, 0, 30);
        RightWing2.addBox(0F, 0F, 0F, 0, 14, 14);
        RightWing2.setRotationPoint(-4F, -2F, 7.3F);
        RightWing2.setTextureSize(64, 64);
        RightWing2.mirror = true;
        setRotation(RightWing2, -1.064651F, 0.2094395F, 0.3839724F);
        LeftWing = new ModelRenderer(this, 28, 30);
        LeftWing.addBox(0F, 0F, 0F, 0, 7, 7);
        LeftWing.setRotationPoint(1F, 2F, 2.5F);
        LeftWing.setTextureSize(64, 64);
        LeftWing.mirror = true;
        setRotation(LeftWing, 0.6108652F, 0.5061455F, 0.0872665F);
        LeftWing2 = new ModelRenderer(this, 0, 30);
        LeftWing2.addBox(0F, 0F, 0F, 0, 14, 14);
        LeftWing2.setRotationPoint(4F, -2F, 7.3F);
        LeftWing2.setTextureSize(64, 64);
        LeftWing2.mirror = true;
        setRotation(LeftWing2, -1.082104F, -0.1745329F, -0.3839724F);
        pedestal = new ModelBigPedestal();
    }

    @Override
    public void renderAll() {
        float par7 = 0.0625F;
        pedestal.shiftModel();
        Legs.render(par7);
        Body.render(par7);
        Head.render(par7);
        RightArm.render(par7);
        RightArm2.render(par7);
        LeftArm.render(par7);
        LeftArm2.render(par7);
        RightWing.render(par7);
        RightWing2.render(par7);
        LeftWing.render(par7);
        LeftWing2.render(par7);
        pedestal.renderAll();
    }

    @Override
    public void setPedestalTexture(ResourceLocation texture) {
        pedestal.setTexture(texture);
    }
}
