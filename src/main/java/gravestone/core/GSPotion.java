package gravestone.core;

import gravestone.config.GSConfig;
import gravestone.potion.CursePotion;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSPotion {
    public static final int REGENERATION_POTION_ID = 8193;
    public static final int SWIFTNESS_POTION_ID = 8194;
    public static final int FIRE_RESISTANCE_POTION_ID = 8227;
    public static final int HEALING_POTION_ID = 8261;
    public static final int NIGHT_VISION_POTION_ID = 8230;
    public static final int STRENGTH_POTION_ID = 8201;
    public static final int INVISIBILITY_POTION_ID = 8238;
    public static final int WATER_BREATHING_POTION_ID = 8237;
    public static final int LEAPING_POTION_ID = 8267;
    public static final int SPLASH_REGENERATION_POTION_ID = 16385;
    public static final int SPLASH_SWIFTNESS_POTION_ID = 16386;
    public static final int SPLASH_FIRE_RESISTANCE_POTION_ID = 16419;
    public static final int SPLASH_HEALING_POTION_ID = 16453;
    public static final int SPLASH_NIGHT_VISION_POTION_ID = 16422;
    public static final int SPLASH_STRENGTH_POTION_ID = 16393;
    public static final int SPLASH_INVISIBILITY_POTION_ID = 16430;
    public static final int SPLASH_WATER_BREATHING_POTION_ID = 16429;
    public static final int SPLASH_LEAPING_POTION_ID = 16459;

    public static final int SPLASH_POISON_2_POTION_ID = 16420;
    public static final int SPLASH_HARM_POTION_2_ID = 16428;
    public static final int SPLASH_WEAKNESS_POTION_ID = 16424;
    public static final int SPLASH_SLOWNESS_POTION_ID = 16458;

    public static CursePotion curse;

    public static final int CURSE_DEFAULT_ID = 31;

    public static void init() {
        curse = new CursePotion(GSConfig.cursePotionEffectId);
    }
}
