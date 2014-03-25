
package gravestone.block.enums;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumSkullCandle implements IBlockEnum {

    SKELETON_SKULL("block.skull_candle.skeleton.name"),
    WITHER_SKULL("block.skull_candle.wither_skeleton.name"),
    ZOMBIE_SKULL("block.skull_candle.zombie.name");
    private String name;

    private EnumSkullCandle(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }
    
    public static EnumSkullCandle getByID(int id) {
        if (id < values().length) {
            return values()[id];
        }
        return SKELETON_SKULL;
    }
}
