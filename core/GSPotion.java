package gravestone.core;

import gravestone.potion.CursePotion;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSPotion {
    public static CursePotion curse;

    public static void init() {
        curse = new CursePotion(31, true, 0);
    }
}
