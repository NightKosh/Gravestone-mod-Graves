package nightkosh.gravestone.models.block.graves;

import nightkosh.gravestone.models.block.ModelCelticCross;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelCelticCrossGravestone extends ModelCelticCross {

    @Override
    public void renderAll() {
        GL11.glTranslatef(0, 0.75F, 0);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        super.renderAll();
    }
}
