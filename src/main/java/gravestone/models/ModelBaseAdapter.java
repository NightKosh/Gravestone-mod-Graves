package gravestone.models;

import net.minecraft.client.model.ModelBase;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelBaseAdapter extends ModelBase implements IModelBaseAdapter {

    @Override
    public void setTexturesOffset(String name, int xPos, int zPos) {
        super.setTextureOffset(name, xPos, zPos);
    }
}
