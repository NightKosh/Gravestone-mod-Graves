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
public class ModelCatStatueMemorial extends ModelMemorial {

    private ModelRenderer ocelotBackLeftLeg;
    private ModelRenderer ocelotBackRightLeg;
    private ModelRenderer ocelotFrontLeftLeg;
    private ModelRenderer ocelotFrontRightLeg;
    private ModelRenderer ocelotHead;
    private ModelRenderer ocelotBody;
    private ModelRenderer ocelotTail;
    private ModelRenderer ocelotTail2;

    private ModelBigPedestal pedestal;

    public ModelCatStatueMemorial() {
        textureWidth = 64;
        textureHeight = 32;
        this.setTextureOffset("head.main", 0, 0);
        this.setTextureOffset("head.nose", 0, 24);
        this.setTextureOffset("head.ear1", 0, 10);
        this.setTextureOffset("head.ear2", 6, 10);
        this.ocelotHead = new ModelRenderer(this, "head");
        this.ocelotHead.addBox("main", -2.5F, -2, -3, 5, 4, 5);
        this.ocelotHead.addBox("nose", -1.5F, 0, -4, 3, 2, 2);
        this.ocelotHead.addBox("ear1", -2, -3, 0, 1, 1, 2);
        this.ocelotHead.addBox("ear2", 1, -3, 0, 1, 1, 2);
        this.ocelotHead.setRotationPoint(0, 15, -9);
        this.ocelotBody = new ModelRenderer(this, 20, 0);
        this.ocelotBody.addBox(-2, 3, -8, 4, 16, 6, 0);
        this.ocelotBody.setRotationPoint(0, 12, -10);
        this.ocelotTail = new ModelRenderer(this, 0, 15);
        this.ocelotTail.addBox(-0.5F, 0, 0, 1, 8, 1);
        this.ocelotTail.rotateAngleX = 0.9F;
        this.ocelotTail.setRotationPoint(0, 15, 8);
        this.ocelotTail2 = new ModelRenderer(this, 4, 15);
        this.ocelotTail2.addBox(-0.5F, 0, 0, 1, 8, 1);
        this.ocelotTail2.setRotationPoint(0, 20, 14);
        this.ocelotBackLeftLeg = new ModelRenderer(this, 8, 13);
        this.ocelotBackLeftLeg.addBox(-1, 0, 1, 2, 6, 2);
        this.ocelotBackLeftLeg.setRotationPoint(1.1F, 18, 5);
        this.ocelotBackRightLeg = new ModelRenderer(this, 8, 13);
        this.ocelotBackRightLeg.addBox(-1, 0, 1, 2, 6, 2);
        this.ocelotBackRightLeg.setRotationPoint(-1.1F, 18, 5);
        this.ocelotFrontLeftLeg = new ModelRenderer(this, 40, 0);
        this.ocelotFrontLeftLeg.addBox(-1, 0, 0, 2, 10, 2);
        this.ocelotFrontLeftLeg.setRotationPoint(1.2F, 13.8F, -5);
        this.ocelotFrontRightLeg = new ModelRenderer(this, 40, 0);
        this.ocelotFrontRightLeg.addBox(-1, 0, 0, 2, 10, 2);
        this.ocelotFrontRightLeg.setRotationPoint(-1.2F, 13.8F, -5);
        pedestal = new ModelBigPedestal();
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are
     * used for animating the movement of arms and legs, where par1 represents
     * the time(so that arms and legs swing back and forth) and par2 represents
     * how "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par4, float par5) {
        this.ocelotHead.rotateAngleX = par5 / (180 / (float) Math.PI);
        this.ocelotHead.rotateAngleY = par4 / (180 / (float) Math.PI);
        this.ocelotBody.rotateAngleX = ((float) Math.PI / 4F);
        this.ocelotBody.rotationPointY = 8;
        this.ocelotBody.rotationPointZ = -5;
        this.ocelotHead.rotationPointY = 11.7F;
        this.ocelotHead.rotationPointZ = -8;
        this.ocelotTail.rotationPointY = 23;
        this.ocelotTail.rotationPointZ = 6;
        this.ocelotTail2.rotationPointY = 22;
        this.ocelotTail2.rotationPointZ = 13.2F;
        this.ocelotTail.rotateAngleX = 1.7278761F;
        this.ocelotTail2.rotateAngleX = 2.670354F;
        this.ocelotFrontLeftLeg.rotateAngleX = this.ocelotFrontRightLeg.rotateAngleX = -0.15707964F;
        this.ocelotFrontLeftLeg.rotationPointY = this.ocelotFrontRightLeg.rotationPointY = 15.8F;
        this.ocelotFrontLeftLeg.rotationPointZ = this.ocelotFrontRightLeg.rotationPointZ = -7;
        this.ocelotBackLeftLeg.rotateAngleX = this.ocelotBackRightLeg.rotateAngleX = -((float) Math.PI / 2F);
        this.ocelotBackLeftLeg.rotationPointY = this.ocelotBackRightLeg.rotationPointY = 21;
        this.ocelotBackLeftLeg.rotationPointZ = this.ocelotBackRightLeg.rotationPointZ = 1;
    }

    @Override
    public void renderAll() {
        this.setRotationAngles(0.0625F, 0.0625F, 0.0625F, 0.0625F);
        float par7 = 0.0625F;
        pedestal.shiftModel();
        this.ocelotHead.render(par7);
        this.ocelotBody.render(par7);
        this.ocelotBackLeftLeg.render(par7);
        this.ocelotBackRightLeg.render(par7);
        this.ocelotFrontLeftLeg.render(par7);
        this.ocelotFrontRightLeg.render(par7);
        this.ocelotTail.render(par7);
        this.ocelotTail2.render(par7);
        pedestal.renderAll();
    }

    @Override
    public void setPedestalTexture(ResourceLocation texture) {
        pedestal.setTexture(texture);
    }
}
