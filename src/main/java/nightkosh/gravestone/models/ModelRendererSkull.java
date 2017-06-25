package nightkosh.gravestone.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.util.ResourceLocation;
import nightkosh.gravestone.core.Resources;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelRendererSkull extends ModelRenderer {

    public static enum EnumSkullType {
        SKELETON_SKULL,
        WITHER_SKULL,
        ZOMBIE_SKULL,
        STRAY_SKULL;

        public static EnumSkullType getTypeByClass(Entity entity) {
            if (entity instanceof EntityWitherSkeleton) {
                return WITHER_SKULL;
            } else {//if (entity instanceof EntitySkeleton) {
                return SKELETON_SKULL;
            }
        }
    }

    private final static String NAME = "skull";
    private ModelRenderer teeth;

    public <T extends ModelBase & IModelBaseAdapter> ModelRendererSkull(T modelBase) {
        this(modelBase, 0, 0, 0, -4, 16.6F, -4);
    }

    public <T extends ModelBase & IModelBaseAdapter> ModelRendererSkull(T modelBase, float xPos, float yPos, float zPos, float xRot, float yRot, float zRot) {
        super(modelBase, NAME);

        textureWidth = 32;
        textureHeight = 32;

        modelBase.setTexturesOffset("skull.skull", 0, 0);
        modelBase.setTexturesOffset("teeth.teeth", 0, 14);

        this.setRotationPoint(xRot, yRot, zRot);
        setRotation(this, -0.1745329F, 0, 0);
        this.addBox("skull", xPos, yPos, zPos, 8, 6, 8);

        teeth = new ModelRenderer(modelBase, "teeth");
        teeth.setRotationPoint(2, 5.4F, 0.1F);
        teeth.addBox("teeth", xPos, yPos, zPos, 4, 2, 1);

        this.addChild(teeth);
    }

    public void bindTexture(EnumSkullType skullType) {
        ResourceLocation texture;
        switch (skullType) {//TODO
            case SKELETON_SKULL:
            default:
                texture = Resources.SKELETON_SKULL;
                break;
            case WITHER_SKULL:
                texture = Resources.WITHER_SKULL;
                break;
            case ZOMBIE_SKULL:
                texture = Resources.ZOMBIE_SKULL;
                break;
            case STRAY_SKULL:
                texture = Resources.STRAY_SKULL;
                break;
        }
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    }

    public void bindTexture(boolean isWither) {
        Minecraft.getMinecraft().renderEngine.bindTexture(isWither ? Resources.WITHER_SKULL : Resources.SKELETON_SKULL);
    }

    public void renderWithTexture(float p_78785_1_, boolean isWither) {
        bindTexture(isWither);
        this.render(p_78785_1_);
    }

    public void renderWithTexture(float p_78785_1_, EnumSkullType skullType) {
        bindTexture(skullType);
        this.render(p_78785_1_);
    }

    @Override
    public void render(float p_78785_1_) {
        super.render(p_78785_1_);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
