package GraveStone.models.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelVillagerMemorial extends ModelGraveStone {

    /**
     * The head box of the VillagerModel
     */
    public ModelRenderer villagerHead;
    /**
     * The body of the VillagerModel
     */
    public ModelRenderer villagerBody;
    /**
     * The arms of the VillagerModel
     */
    public ModelRenderer villagerArms;
    /**
     * The right leg of the VillagerModel
     */
    public ModelRenderer rightVillagerLeg;
    /**
     * The left leg of the VillagerModel
     */
    public ModelRenderer leftVillagerLeg;
    public ModelRenderer field_82898_f;
    ModelSmallPedestal pedestal;

    public ModelVillagerMemorial() {
        float par1 = 0;
        float par2 = 0;
        int par3 = 64;
        int par4 = 64;
        textureWidth = 64;
        textureHeight = 64;
        this.villagerHead = (new ModelRenderer(this)).setTextureSize(par3, par4);
        this.villagerHead.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.villagerHead.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, par1);
        this.field_82898_f = (new ModelRenderer(this)).setTextureSize(par3, par4);
        this.field_82898_f.setRotationPoint(0.0F, par2 - 2.0F, 0.0F);
        this.field_82898_f.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, par1);
        this.villagerHead.addChild(this.field_82898_f);
        this.villagerBody = (new ModelRenderer(this)).setTextureSize(par3, par4);
        this.villagerBody.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.villagerBody.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, par1);
        this.villagerBody.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, par1 + 0.5F);
        this.villagerArms = (new ModelRenderer(this)).setTextureSize(par3, par4);
        this.villagerArms.setRotationPoint(0.0F, 0.0F + par2 + 2.0F, 0.0F);
        this.villagerArms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, par1);
        this.villagerArms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, par1);
        this.villagerArms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, par1);
        this.rightVillagerLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(par3, par4);
        this.rightVillagerLeg.setRotationPoint(-2.0F, 12.0F + par2, 0.0F);
        this.rightVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.leftVillagerLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(par3, par4);
        this.leftVillagerLeg.mirror = true;
        this.leftVillagerLeg.setRotationPoint(2.0F, 12.0F + par2, 0.0F);
        this.leftVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        pedestal = new ModelSmallPedestal();
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are
     * used for animating the movement of arms and legs, where par1 represents
     * the time(so that arms and legs swing back and forth) and par2 represents
     * how "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        this.villagerHead.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.villagerHead.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.villagerArms.rotationPointY = 3.0F;
        this.villagerArms.rotationPointZ = -1.0F;
        this.villagerArms.rotateAngleX = -0.75F;
        this.rightVillagerLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 * 0.5F;
        this.leftVillagerLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2 * 0.5F;
        this.rightVillagerLeg.rotateAngleY = 0.0F;
        this.leftVillagerLeg.rotateAngleY = 0.0F;
    }

    @Override
    public void renderAll() {
        this.setRotationAngles(0.0625F, 0.0625F, 0.0625F, 0.0625F, 0.0625F, 0.0625F);
        float par7 = 0.0625F;
        ModelSmallPedestal.shiftModel();
        this.villagerHead.render(par7);
        this.villagerBody.render(par7);
        this.rightVillagerLeg.render(par7);
        this.leftVillagerLeg.render(par7);
        this.villagerArms.render(par7);
        pedestal.renderAll();
    }
}
