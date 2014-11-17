package gravestone.models.block;

import gravestone.block.enums.EnumMemorials;
import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ModelMemorial extends ModelGraveStone {

    public void setPedestalTexture(ResourceLocation texture) {
    }

    public void customRender(EnumMemorials memorialType, boolean enchanted) {
        if (enchanted) {
            renderEnchanted();
        } else {
            renderAll();
        }
    }

    public void customRender(EnumMemorials memorialType, byte mob, int villagerProfession) {
    }
}
