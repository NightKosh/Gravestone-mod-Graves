package gravestone.core;

import gravestone.config.GraveStoneConfig;
import gravestone.potion.CursePotion;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSPotion {
    public static CursePotion curse;

    public static final int CURSE_DEFAULT_ID = 31;

    public static void init() {
        curse = new CursePotion(GraveStoneConfig.cursePotionEffectId, true, 0);
    }
}
