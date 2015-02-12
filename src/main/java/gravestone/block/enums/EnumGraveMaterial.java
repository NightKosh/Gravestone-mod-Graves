package gravestone.block.enums;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumGraveMaterial {
    WOOD,
    SANDSTONE,
    STONE,
    IRON,
    GOLD,
    DIAMOND,
    EMERALD,
    LAPIS,
    REDSTONE,
    OBSIDIAN,
    QUARTZ,
    ICE,
    OTHER;


    public String getLocalizedMaterial() {
        return ModGraveStone.proxy.getLocalizedString("material." + this.toString().toLowerCase());
    }
}
