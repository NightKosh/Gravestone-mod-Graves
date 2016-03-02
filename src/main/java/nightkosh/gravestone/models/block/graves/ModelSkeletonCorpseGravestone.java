package nightkosh.gravestone.models.block.graves;

import nightkosh.gravestone.models.block.ModelGraveStone;
import nightkosh.gravestone.models.block.ModelLyingSkeleton;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelSkeletonCorpseGravestone extends ModelGraveStone {

    private static final ModelLyingSkeleton skeletonModel = new ModelLyingSkeleton(false);
    private static final ModelLyingSkeleton witheredSkeletonModel = new ModelLyingSkeleton(true);

    private final boolean isWithered;

    public ModelSkeletonCorpseGravestone(boolean isWithered) {
        this.isWithered = isWithered;
    }

    @Override
    public void renderAll() {
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 1.4F, 0);
        GL11.glRotatef(90, -1, 0, 0);
        if (isWithered) {
            witheredSkeletonModel.renderAll();
        } else {
            skeletonModel.renderAll();
        }
        GL11.glPopMatrix();
    }
}
