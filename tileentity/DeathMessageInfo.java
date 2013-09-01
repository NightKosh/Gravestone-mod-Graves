
package gravestone.tileentity;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DeathMessageInfo {
    private String name;
    private String deathMessage;
    private String killerName;
    private String itemName;
    
    public static final DeathMessageInfo[] LOCALIZED_DEATH_TEXT = {
        new DeathMessageInfo("", "death.attack.anvil", null),
        new DeathMessageInfo("", "death.attack.arrow", ""),
        new DeathMessageInfo("", "death.attack.cactus", null),
        new DeathMessageInfo("", "death.attack.cactus.player", ""),
        new DeathMessageInfo("", "death.attack.drown", null),
        new DeathMessageInfo("", "death.attack.drown.player", ""),
        new DeathMessageInfo("", "death.attack.explosion", null),
        new DeathMessageInfo("", "death.attack.explosion.player", ""),
        new DeathMessageInfo("", "death.attack.fall", null),
        new DeathMessageInfo("", "death.attack.fallingBlock", null),
        new DeathMessageInfo("", "death.attack.fireball", ""),
        new DeathMessageInfo("", "death.attack.generic", null),
        new DeathMessageInfo("", "death.attack.inFire", null),
        new DeathMessageInfo("", "death.attack.inFire.player", ""),
        new DeathMessageInfo("", "death.attack.inWall", null),
        new DeathMessageInfo("", "death.attack.indirectMagic", ""),
        new DeathMessageInfo("", "death.attack.lava", null),
        new DeathMessageInfo("", "death.attack.lava.player", ""),
        new DeathMessageInfo("", "death.attack.magic", null),
        new DeathMessageInfo("", "death.attack.mob", ""),
        new DeathMessageInfo("", "death.attack.onFire", null),
        new DeathMessageInfo("", "death.attack.onFire.player", ""),
        new DeathMessageInfo("", "death.attack.outOfWorld", null),
        new DeathMessageInfo("", "death.attack.player", ""),
        new DeathMessageInfo("", "death.attack.starve", null),
        new DeathMessageInfo("", "death.attack.thorns", ""),
        new DeathMessageInfo("", "death.attack.thrown", ""),
        new DeathMessageInfo("", "death.attack.wither", null),
        new DeathMessageInfo("", "death.fell.accident.generic", null),
        new DeathMessageInfo("", "death.fell.accident.ladder", null),
        new DeathMessageInfo("", "death.fell.accident.vines", null),
        new DeathMessageInfo("", "death.fell.accident.water", null),
        new DeathMessageInfo("", "death.fell.assist", ""),
        new DeathMessageInfo("", "death.fell.finish", ""),
        new DeathMessageInfo("", "death.fell.killer", null)
    };
    
    public static final String[] LOCALIZED_KILLER_NAMES = {
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
        "entity.zombiehorse.name"
    };
    
    public DeathMessageInfo(String name, String deathMessage, String killerName) {
        this(name, deathMessage, killerName, null);
    }
    
    public DeathMessageInfo(String name, String deathMessage, String killerName, String itemName) {
        this.name  = name;
        this.deathMessage  = deathMessage;
        this.killerName  = killerName;
        this.itemName  = itemName;
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
        DeathMessageInfo info = LOCALIZED_DEATH_TEXT[random.nextInt(LOCALIZED_DEATH_TEXT.length)];
        if (info.killerName != null) {
            info.killerName = getRandomKillerName(random);
        }
        return info;
    }
    
    public static String getRandomKillerName(Random random) {
        return LOCALIZED_KILLER_NAMES[random.nextInt(LOCALIZED_KILLER_NAMES.length)];
    }
   
}
