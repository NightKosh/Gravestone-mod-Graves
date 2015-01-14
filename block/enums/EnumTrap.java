
package gravestone.block.enums;

import gravestone.ModGraveStone;
import net.minecraft.util.IStringSerializable;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumTrap implements IBlockEnum, IStringSerializable {

    NIGHT_STONE("tile.trap.night.name"),
    THUNDER_STONE("tile.trap.thunder.name");
    private String name;

    private EnumTrap(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    public static EnumTrap getById(byte id) {
        if (id < values().length) {
            return values()[id];
        }
        return NIGHT_STONE;
    }

}
