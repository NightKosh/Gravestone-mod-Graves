
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

    NIGHT_STONE("tile.trap.night.name", "night_stone"),
    THUNDER_STONE("tile.trap.thunder.name", "thunder_stone");
    private String name;
    private String blockModelName;

    private EnumTrap(String name, String blockModelName) {
        this.name = name;
        this.blockModelName = blockModelName;
    }

    @Override
    public String getLocalizedName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    @Override
    public String getName() {
        return blockModelName;
    }

    public static EnumTrap getById(byte id) {
        if (id < values().length) {
            return values()[id];
        }
        return NIGHT_STONE;
    }
}
