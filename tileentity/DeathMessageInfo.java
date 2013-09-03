package gravestone.tileentity;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DeathMessageInfo {

    public enum KillerTypes {
        ALL,
        ARROW,
        FIREBALL,
        BLOW,
        MAGIC
    }
    private String name;
    private String deathMessage;
    private String killerName;
    private String itemName;
    private KillerTypes killerTypes;
    
    public static final DeathMessageInfo[] LOCALIZED_DEATH_TEXT = {
        new DeathMessageInfo("", "death.attack.anvil", null, null, null),
        new DeathMessageInfo("", "death.attack.arrow", "", null, KillerTypes.ARROW),
        new DeathMessageInfo("", "death.attack.cactus", null, null, null),
        new DeathMessageInfo("", "death.attack.cactus.player", "", null, KillerTypes.ALL),
        new DeathMessageInfo("", "death.attack.drown", null, null, null),
        new DeathMessageInfo("", "death.attack.drown.player", "", null, KillerTypes.ALL),
        new DeathMessageInfo("", "death.attack.explosion", null, null, null),
        new DeathMessageInfo("", "death.attack.explosion.player", "", null, KillerTypes.BLOW),
        new DeathMessageInfo("", "death.attack.fall", null, null, null),
        new DeathMessageInfo("", "death.attack.fallingBlock", null, null, null),
        new DeathMessageInfo("", "death.attack.fireball", "", null, KillerTypes.FIREBALL),
        new DeathMessageInfo("", "death.attack.generic", null, null, null),
        new DeathMessageInfo("", "death.attack.inFire", null, null, null),
        new DeathMessageInfo("", "death.attack.inFire.player", "", null, KillerTypes.ALL),
        new DeathMessageInfo("", "death.attack.inWall", null, null, null),
        new DeathMessageInfo("", "death.attack.indirectMagic", "", null, KillerTypes.MAGIC),
        new DeathMessageInfo("", "death.attack.lava", null, null, null),
        new DeathMessageInfo("", "death.attack.lava.player", "", null, KillerTypes.ALL),
        new DeathMessageInfo("", "death.attack.magic", null, null, null),
        new DeathMessageInfo("", "death.attack.mob", "", null, KillerTypes.ALL),
        new DeathMessageInfo("", "death.attack.onFire", null, null, null),
        new DeathMessageInfo("", "death.attack.onFire.player", "", null, KillerTypes.ALL),
        new DeathMessageInfo("", "death.attack.outOfWorld", null, null, null),
        new DeathMessageInfo("", "death.attack.player", "", null, KillerTypes.ALL),
        new DeathMessageInfo("", "death.attack.starve", null, null, null),
        new DeathMessageInfo("", "death.attack.thorns", "", null, KillerTypes.ALL),
        new DeathMessageInfo("", "death.attack.thrown", "", null, KillerTypes.ALL),
        new DeathMessageInfo("", "death.attack.wither", null, null, null),
        new DeathMessageInfo("", "death.fell.accident.generic", null, null, null),
        new DeathMessageInfo("", "death.fell.accident.ladder", null, null, null),
        new DeathMessageInfo("", "death.fell.accident.vines", null, null, null),
        new DeathMessageInfo("", "death.fell.accident.water", null, null, null),
        new DeathMessageInfo("", "death.fell.assist", "", null, KillerTypes.ALL),
        new DeathMessageInfo("", "death.fell.finish", "", null, KillerTypes.ALL),
        new DeathMessageInfo("", "death.fell.killer", null, null, null),
        new DeathMessageInfo("", "death.GS.Herobrine", null, null, null),
        new DeathMessageInfo("", "death.GS.death_sentence", null, null, null),
        new DeathMessageInfo("", "death.GS.tortures", null, null, null)
    };
    public static final DeathMessageInfo[] SPECIAL_LOCALIZED_DEATH_TEXT = {
        new DeathMessageInfo("Notch", "death.GS.Herobrine", null),
        new DeathMessageInfo("Steve", "death.GS.Herobrine", null),
        new DeathMessageInfo("Jeb", "death.GS.Herobrine", null),
        new DeathMessageInfo("Leeroy Jenkins", "death.attack.onFire.player", "entity.EnderDragon.name")
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
        "entity.Witch.name",
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

    public DeathMessageInfo(String name, String deathMessage, String killerName) {
        this(name, deathMessage, killerName, null, null);
    }

    public DeathMessageInfo(String name, String deathMessage, String killerName, String itemName) {
        this(name, deathMessage, killerName, itemName, null);
    }

    public DeathMessageInfo(String name, String deathMessage, String killerName, String itemName, KillerTypes messageTypes) {
        this.name = name;
        this.deathMessage = deathMessage;
        this.killerName = killerName;
        this.itemName = itemName;
        this.killerTypes = messageTypes;
    }

    public String getName() {
        return name;
    }

    public String getDeathMessage() {
        return deathMessage;
    }

    public String getKillerName() {
        return killerName;
    }

    public String getKillerNameForTE() {
        return (killerName == null) ? "" : killerName;
    }

    public String getItemName() {
        return itemName;
    }

    public static DeathMessageInfo getRandomDeathMessage(Random random) {
        DeathMessageInfo info;
        if (random.nextInt(50) == 0) {
            info = SPECIAL_LOCALIZED_DEATH_TEXT[random.nextInt(SPECIAL_LOCALIZED_DEATH_TEXT.length)];
        } else {
            info = LOCALIZED_DEATH_TEXT[random.nextInt(LOCALIZED_DEATH_TEXT.length)];
            if (info.killerName != null) {
                info.killerName = getRandomKillerName(random, info.killerTypes);
            }
        }
        return info;
    }

    public static String getRandomKillerName(Random random, KillerTypes killerTypes) {
        switch (killerTypes) {
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
