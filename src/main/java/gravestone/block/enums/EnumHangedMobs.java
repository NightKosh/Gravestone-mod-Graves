package gravestone.block.enums;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumHangedMobs {
    NONE,
    STEVE,
    VILLAGER,
    ZOMBIE,
    ZOMBIE_VILLAGER,
    SKELETON,
    WITHER_SKELETON,
    WITCH,
    ZOMBIE_PIGMAN;

    public static EnumHangedMobs getById(int id) {
        if (id < values().length) {
            return values()[id];
        }
        return NONE;
    }
}
