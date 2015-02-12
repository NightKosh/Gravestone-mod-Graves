package gravestone.models.block;

import gravestone.block.enums.EnumHangedMobs;
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

    public void customRender(ResourceLocation texture, boolean enchanted) {
        if (enchanted) {
            renderEnchanted();
        } else {
            renderAll();
        }
    }

    public void customRender(EnumMemorials memorialType, EnumHangedMobs mob, int villagerProfession) {
    }
}
