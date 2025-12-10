package nightkosh.gravestone.helper;

import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.tileentity.DeathMessageInfo;
import nightkosh.gravestone.tileentity.GraveStoneDeathText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DeathTextHelper {

    public enum DeathType {
        ALL,
        ARROW,
        FIREBALL,
        BLOW,
        MAGIC
    }

    public static final DeathMessageInfo[] LOCALIZED_DEATH_TEXT = {
            new DeathMessageInfo("", "death.attack.anvil", null, null, null),
            new DeathMessageInfo("", "death.attack.arrow", "", null, DeathType.ARROW),
            new DeathMessageInfo("", "death.attack.cactus", null, null, null),
            new DeathMessageInfo("", "death.attack.cactus.player", "", null, DeathType.ALL),
            new DeathMessageInfo("", "death.attack.drown", null, null, null),
            new DeathMessageInfo("", "death.attack.drown.player", "", null, DeathType.ALL),
            new DeathMessageInfo("", "death.attack.explosion", null, null, null),
            new DeathMessageInfo("", "death.attack.explosion.player", "", null, DeathType.BLOW),
            new DeathMessageInfo("", "death.attack.fall", null, null, null),
            new DeathMessageInfo("", "death.attack.fallingBlock", null, null, null),
            new DeathMessageInfo("", "death.attack.fireball", "", null, DeathType.FIREBALL),
            new DeathMessageInfo("", "death.attack.inFire", null, null, null),
            new DeathMessageInfo("", "death.attack.inFire.player", "", null, DeathType.ALL),
            new DeathMessageInfo("", "death.attack.inWall", null, null, null),
            new DeathMessageInfo("", "death.attack.indirectMagic", "", null, DeathType.MAGIC),
            new DeathMessageInfo("", "death.attack.lava", null, null, null),
            new DeathMessageInfo("", "death.attack.lava.player", "", null, DeathType.ALL),
            new DeathMessageInfo("", "death.attack.magic", null, null, null),
            new DeathMessageInfo("", "death.attack.mob", "", null, DeathType.ALL),
            new DeathMessageInfo("", "death.attack.onFire", null, null, null),
            new DeathMessageInfo("", "death.attack.onFire.player", "", null, DeathType.ALL),
            new DeathMessageInfo("", "death.attack.outOfWorld", null, null, null),
            new DeathMessageInfo("", "death.attack.player", "", null, DeathType.ALL),
            new DeathMessageInfo("", "death.attack.starve", null, null, null),
            new DeathMessageInfo("", "death.attack.thorns", "", null, DeathType.ALL),
            new DeathMessageInfo("", "death.attack.thrown", "", null, DeathType.ALL),
            new DeathMessageInfo("", "death.attack.wither", null, null, null),
            new DeathMessageInfo("", "death.fell.accident.generic", null, null, null),
            new DeathMessageInfo("", "death.fell.accident.ladder", null, null, null),
            new DeathMessageInfo("", "death.fell.accident.vines", null, null, null),
            new DeathMessageInfo("", "death.fell.accident.water", null, null, null),
            new DeathMessageInfo("", "death.fell.assist", "", null, DeathType.ALL),
            new DeathMessageInfo("", "death.fell.finish", "", null, DeathType.ALL),
            new DeathMessageInfo("", "death.fell.killer", null, null, null),
            new DeathMessageInfo("", "death.GS.Herobrine", null, null, null),
            new DeathMessageInfo("", "death.GS.death_sentence", null, null, null),
            new DeathMessageInfo("", "death.GS.tortures", null, null, null)
    };
    public static final DeathMessageInfo[] SPECIAL_LOCALIZED_DEATH_TEXT = {
            new DeathMessageInfo("Notch", "death.GS.Herobrine", null),
            new DeathMessageInfo("Steve", "death.GS.Herobrine", null),
            new DeathMessageInfo("Jeb", "death.GS.Herobrine", null),
            new DeathMessageInfo("Leeroy Jenkins", "death.attack.onFire.player", "entity.EnderDragon.name"),
            new DeathMessageInfo("Wilson", "death.attack.starve", null),
            new DeathMessageInfo("Guide", "death.attack.lava", null),
            new DeathMessageInfo("death.GS.mom", "death.GS.Herobrine", null),
            new DeathMessageInfo("Horke", "death.attack.drown", null)
    };
    public static final String[] ALL_KILLER_NAMES = {
            "entity.Blaze.name",
            "entity.CaveSpider.name",
            "entity.Creeper.name",
            "entity.EnderDragon.name",
            "entity.Enderman.name",
            "entity.Ghast.name",
            "entity.Giant.name",
            "entity.LavaSlime.name",
            "entity.PigZombie.name",
            "entity.Silverfish.name",
            "entity.Skeleton.name",
            "entity.Slime.name",
            "entity.SnowMan.name",
            "entity.Spider.name",
            "entity.VillagerGolem.name",
            "entity.Witch.name",
            "entity.WitherBoss.name",
            "entity.Wolf.name",
            "entity.Zombie.name",
            "entity.skeletonhorse.name",
            "entity.zombiehorse.name",
            "entity.GSZombieDog.name",
            "entity.GSZombieCat.name",
            "entity.GSSkeletonDog.name",
            "entity.GSSkeletonCat.name"
    };
    public static final String[] ARROW_KILLER_NAMES = {
            "entity.Blaze.name",
            "entity.Ghast.name",
            "entity.Skeleton.name",
            "entity.SnowMan.name",
            "entity.WitherBoss.name",
    };
    public static final String[] FIREBALL_KILLER_NAMES = {
            "entity.Blaze.name",
            "entity.Ghast.name",
            "entity.WitherBoss.name",
    };
    public static final String[] BLOW_KILLER_NAMES = {
            "entity.Creeper.name",
            "entity.Ghast.name",
            "entity.WitherBoss.name"
    };
    public static final String[] MAGIC_KILLER_NAMES = {
            "entity.EnderDragon.name",
            "entity.Enderman.name",
            "entity.Witch.name",
            "entity.WitherBoss.name"
    };

    public static GraveStoneDeathText getRandomDeathTextAndNameForGrave(Random random, EnumGraveType graveType) {
        switch (graveType) {
            case DOG_STATUE:
                return getRandomDeathTextAndNameForGrave(random, GraveGenerationHelper.EnumGraveTypeByEntity.DOGS_GRAVES);
            case CAT_STATUE:
                return getRandomDeathTextAndNameForGrave(random, GraveGenerationHelper.EnumGraveTypeByEntity.CATS_GRAVES);
            case HORSE_STATUE:
                return getRandomDeathTextAndNameForGrave(random, GraveGenerationHelper.EnumGraveTypeByEntity.HORSE_GRAVES);
            default:
                return getRandomDeathTextAndNameForGrave(random, GraveGenerationHelper.EnumGraveTypeByEntity.PLAYER_GRAVES);
        }
    }

    public static GraveStoneDeathText getRandomDeathTextAndNameForGrave(Random random, GraveGenerationHelper.EnumGraveTypeByEntity graveTypeByEntity) {
        GraveStoneDeathText deathText = new GraveStoneDeathText();
        deathText.setLocalized();

        if (getDeathMessage(deathText, random)) {
            switch (graveTypeByEntity) {
                case DOGS_GRAVES:
                    deathText.setName(getValue(random, GSConfigs.graveDogsNames));
                    break;
                case CATS_GRAVES:
                    deathText.setName(getValue(random, GSConfigs.graveCatsNames));
                    break;
                case HORSE_GRAVES:
                    //TODO
                    deathText.setName(getValue(random, GSConfigs.graveDogsNames));
                    break;
                default:
                    deathText.setName(getValue(random, GSConfigs.graveNames));
                    break;
            }
        }
        return deathText;
    }

    private static String getValue(Random random, List<String> list) {
        if (list != null && list.size() > 0) {
            return list.get(random.nextInt(list.size()));
        } else {
            return "";
        }
    }

    private static void getRandomMemorialContent(GraveStoneDeathText deathText, Random random, ArrayList<String> nameList) {
        if (getDeathMessage(deathText, random)) {
            deathText.setDeathText(getValue(random, nameList));
        }
    }

    private static boolean getDeathMessage(GraveStoneDeathText deathText, Random random) {
        DeathMessageInfo deathMessageInfo = getRandomDeathMessage(random);
        deathText.setDeathText(deathMessageInfo.getDeathMessage());
        deathText.setKillerName(deathMessageInfo.getKillerNameForTE());
        if (deathMessageInfo.getName().length() != 0) {
            deathText.setDeathText(deathMessageInfo.getName());
            return false;
        }
        return true;
    }

    public static DeathMessageInfo getRandomDeathMessage(Random random) {
        DeathMessageInfo info;
        if (random.nextInt(50) == 0) {
            info = SPECIAL_LOCALIZED_DEATH_TEXT[random.nextInt(SPECIAL_LOCALIZED_DEATH_TEXT.length)];
        } else {
            info = LOCALIZED_DEATH_TEXT[random.nextInt(LOCALIZED_DEATH_TEXT.length)];
            if (info.getKillerName() != null) {
                info.setKillerName(getRandomKillerName(random, info.getDeathType()));
            }
        }
        return info;
    }

    public static String getRandomKillerName(Random random, DeathType deathType) {
        switch (deathType) {
            case ARROW:
                return ARROW_KILLER_NAMES[random.nextInt(ARROW_KILLER_NAMES.length)];
            case FIREBALL:
                return FIREBALL_KILLER_NAMES[random.nextInt(FIREBALL_KILLER_NAMES.length)];
            case BLOW:
                return BLOW_KILLER_NAMES[random.nextInt(BLOW_KILLER_NAMES.length)];
            case MAGIC:
                return MAGIC_KILLER_NAMES[random.nextInt(MAGIC_KILLER_NAMES.length)];
            case ALL:
            default:
                return ALL_KILLER_NAMES[random.nextInt(ALL_KILLER_NAMES.length)];
        }
    }

}
