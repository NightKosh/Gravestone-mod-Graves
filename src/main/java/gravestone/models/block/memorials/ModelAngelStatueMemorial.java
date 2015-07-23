package gravestone.models.block.memorials;

import gravestone.models.block.ModelMemorial;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelAngelStatueMemorial extends ModelMemorial {

    private ModelRenderer legs;
    private ModelRenderer body;
    private ModelRenderer head;
    private ModelRenderer rightArm;
    private ModelRenderer rightArm2;
    private ModelRenderer leftArm;
    private ModelRenderer leftArm2;
    private ModelRenderer rightWing;
    private ModelRenderer rightWing2;
    private ModelRenderer leftWing;
    private ModelRenderer leftWing2;
    private ModelBigPedestal pedestal;

    public ModelAngelStatueMemorial() {
        textureWidth = 64;
        textureHeight = 64;
        legs = new ModelRenderer(this, 0, 16);
        legs.addBox(0, 0, 0, 6, 11, 3);
        legs.setRotationPoint(-3, 13, 0);
        legs.setTextureSize(64, 64);
        legs.mirror = true;
        setRotation(legs, 0, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        body.addBox(0, 0, 0, 8, 12, 4);
        body.setRotationPoint(-4, 1, -0.5F);
        body.setTextureSize(64, 64);
        body.mirror = true;
        setRotation(body, 0, 0, 0);
        head = new ModelRenderer(this, 24, 0);
        head.addBox(0, 0, 0, 8, 8, 8);
        head.setRotationPoint(-4, -8, 1);
        head.setTextureSize(64, 64);
        head.mirror = true;
        setRotation(head, -1.047198F, 0, 0);
        rightArm = new ModelRenderer(this, 18, 16);
        rightArm.addBox(0, 0, 0, 3, 7, 3);
        rightArm.setRotationPoint(-6.5F, 1.5F, 1);
        rightArm.setTextureSize(64, 64);
        rightArm.mirror = true;
        setRotation(rightArm, -0.837758F, -0.4014257F, 0);
        rightArm2 = new ModelRenderer(this, 30, 16);
        rightArm2.addBox(0, 0, 0, 3, 7, 3);
        rightArm2.setRotationPoint(-3, 1, -4.5F);
        rightArm2.setTextureSize(64, 64);
        rightArm2.mirror = true;
        setRotation(rightArm2, 0.122173F, -0.4363323F, 0.2268928F);
        leftArm = new ModelRenderer(this, 18, 16);
        leftArm.addBox(0, 0, 0, 3, 7, 3);
        leftArm.setRotationPoint(3.5F, 1.5F, 2);
        leftArm.setTextureSize(64, 64);
        leftArm.mirror = true;
        setRotation(leftArm, -0.837758F, 0.4014257F, 0);
        leftArm2 = new ModelRenderer(this, 30, 16);
        leftArm2.addBox(0, 0, 0, 3, 7, 3);
        leftArm2.setRotationPoint(0.2F, 2, -4);
        leftArm2.setTextureSize(64, 64);
        leftArm2.mirror = true;
        setRotation(leftArm2, 0.296706F, 0.4537856F, -0.1570796F);
        rightWing = new ModelRenderer(this, 28, 30);
        rightWing.addBox(0, 0, 0, 0, 7, 7);
        rightWing.setRotationPoint(-1, 2, 2.5F);
        rightWing.setTextureSize(64, 64);
        rightWing.mirror = true;
        setRotation(rightWing, 0.6108652F, -0.5061455F, -0.0872665F);
        rightWing2 = new ModelRenderer(this, 0, 30);
        rightWing2.addBox(0, 0, 0, 0, 14, 14);
        rightWing2.setRotationPoint(-4, -2, 7.3F);
        rightWing2.setTextureSize(64, 64);
        rightWing2.mirror = true;
        setRotation(rightWing2, -1.064651F, 0.2094395F, 0.3839724F);
        leftWing = new ModelRenderer(this, 28, 30);
        leftWing.addBox(0, 0, 0, 0, 7, 7);
        leftWing.setRotationPoint(1, 2, 2.5F);
        leftWing.setTextureSize(64, 64);
        leftWing.mirror = true;
        setRotation(leftWing, 0.6108652F, 0.5061455F, 0.0872665F);
        leftWing2 = new ModelRenderer(this, 0, 30);
        leftWing2.addBox(0, 0, 0, 0, 14, 14);
        leftWing2.setRotationPoint(4, -2, 7.3F);
        leftWing2.setTextureSize(64, 64);
        leftWing2.mirror = true;
        setRotation(leftWing2, -1.082104F, -0.1745329F, -0.3839724F);
        pedestal = new ModelBigPedestal();
    }

    @Override
    public void renderAll() {
        float par7 = 0.0625F;
        pedestal.shiftModel();
        legs.render(par7);
        body.render(par7);
        head.render(par7);
        rightArm.render(par7);
        rightArm2.render(par7);
        leftArm.render(par7);
        leftArm2.render(par7);
        rightWing.render(par7);
        rightWing2.render(par7);
        leftWing.render(par7);
        leftWing2.render(par7);
        pedestal.renderAll();
    }

    @Override
    public void setPedestalTexture(ResourceLocation texture) {
        pedestal.setTexture(texture);
    }
}
