package gravestone.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelRendererSkull extends ModelRenderer {

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

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
