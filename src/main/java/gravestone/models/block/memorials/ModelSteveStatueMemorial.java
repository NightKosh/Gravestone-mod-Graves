package gravestone.models.block.memorials;

import gravestone.models.block.ModelMemorial;
import gravestone.renderer.tileentity.TileEntityGSMemorialRenderer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
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
public class ModelSteveStatueMemorial extends ModelMemorial {

    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    /**
     * Records whether the model should be rendered holding an item in the left
     * hand, and if that item is a block.
     */
    public int heldItemLeft;
    /**
     * Records whether the model should be rendered holding an item in the right
     * hand, and if that item is a block.
     */
    public int heldItemRight;
    ModelBigPedestal pedestal;

    public ModelSteveStatueMemorial() {
        float par1 = 0;
        float par2 = 0;
        textureWidth = 64;
        textureHeight = 32;
        this.heldItemLeft = 0;
        this.heldItemRight = 0;
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4, -8, -4, 8, 8, 8, par1);
        this.bipedHead.setRotationPoint(0, 0 + par2, 0);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.addBox(-4, 0, -2, 8, 12, 4, par1);
        this.bipedBody.setRotationPoint(0, 0 + par2, 0);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-3, -2, -2, 4, 12, 4, par1);
        this.bipedRightArm.setRotationPoint(-5, 2 + par2, 0);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1, -2, -2, 4, 12, 4, par1);
        this.bipedLeftArm.setRotationPoint(5, 2 + par2, 0);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.addBox(-2, 0, -2, 4, 12, 4, par1);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12 + par2, 0);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-2, 0, -2, 4, 12, 4, par1);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12 + par2, 0);
        pedestal = new ModelBigPedestal();
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are
     * used for animating the movement of arms and legs, where par1 represents
     * the time(so that arms and legs swing back and forth) and par2 represents
     * how "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        this.bipedHead.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.bipedHead.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.bipedRightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2 * par2 * 0.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2 * par2 * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0;
        this.bipedLeftArm.rotateAngleZ = 0;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.bipedRightLeg.rotateAngleY = 0;
        this.bipedLeftLeg.rotateAngleY = 0;

        this.bipedRightArm.rotateAngleY = 0;
        this.bipedLeftArm.rotateAngleY = 0;
        this.bipedBody.rotateAngleX = 0;
        this.bipedRightLeg.rotationPointZ = 0.1F;
        this.bipedLeftLeg.rotationPointZ = 0.1F;
        this.bipedRightLeg.rotationPointY = 12;
        this.bipedLeftLeg.rotationPointY = 12;
        this.bipedHead.rotationPointY = 0;
        this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
    }

    @Override
    public void renderAll() {
        this.setRotationAngles(0.0625F, 0.0625F, 0.0625F, 0.0625F, 0.0625F, 0.0625F);
        pedestal.shiftModel();
        renderSteve();
        renderSteveLegs();
        pedestal.renderAll();
    }

    private void renderSteve() {
        float par7 = 0.0625F;
        this.bipedHead.render(par7);
        this.bipedBody.render(par7);
        this.bipedRightArm.render(par7);
        this.bipedLeftArm.render(par7);
    }

    private void renderSteveLegs() {
        float par7 = 0.0625F;
        this.bipedRightLeg.render(par7);
        this.bipedLeftLeg.render(par7);
    }

    @Override
    public void customRender(ResourceLocation armorTexture, boolean enchanted) {
        if (enchanted) {
            renderEnchanted();
        } else {
            renderAll();
        }
        renderArmor(armorTexture);
    }

    private void renderArmor(ResourceLocation armorTexture) {
        pedestal.shiftModel();
        if (armorTexture != null) {
            float scale = 1.1F;
            GL11.glScalef(scale, scale, scale);
            TileEntityGSMemorialRenderer.instance.bindTextureByName(armorTexture);
            renderSteve();
        }
    }

    @Override
    public void setPedestalTexture(ResourceLocation texture) {
        pedestal.setTexture(texture);
    }
}
