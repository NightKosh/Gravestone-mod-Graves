
package gravestone.item.enums;

import gravestone.ModGraveStone;
import gravestone.block.enums.IBlockEnum;
import gravestone.core.Resources;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumCorpse implements IBlockEnum {
    VILLAGER("item.corpse.villager", Resources.CORPSE_VILLAGER),
    DOG("item.corpse.dog", Resources.CORPSE_DOG),
    COT("item.corpse.cat", Resources.CORPSE_CAT),
    HORSE("item.corpse.horse", Resources.CORPSE_HORSE);
    private String name;
    private String icon;

    private EnumCorpse(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    @Override
    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }
    
    public String getIcon() {
        return this.icon;
    }

    public static EnumCorpse getById(byte id) {
        if (id < values().length) {
            return values()[id];
        }
        return VILLAGER;
    }
}
