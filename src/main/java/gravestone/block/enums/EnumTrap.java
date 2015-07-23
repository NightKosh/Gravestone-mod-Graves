package gravestone.block.enums;

import net.minecraft.util.IStringSerializable;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumTrap implements IBlockEnum, IStringSerializable {

    NIGHT_STONE("block.trap.night", "night_stone"),
    THUNDER_STONE("block.trap.thunder", "thunder_stone");
    private String name;
    private String blockModelName;

    private EnumTrap(String name, String blockModelName) {
        this.name = name;
        this.blockModelName = blockModelName;
    }

    @Override
    public String getUnLocalizedName() {
        return this.name;
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
