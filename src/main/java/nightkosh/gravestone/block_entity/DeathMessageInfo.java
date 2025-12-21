package nightkosh.gravestone.block_entity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DeathMessageInfo {

    private final String name;
    private final String deathMessage;
    private String killerName;
    private final String itemName;
    private final DeathType deathType;

    public enum DeathType {
        ALL,
        ARROW,
        FIREBALL,
        BLOW,
        MAGIC
    }

    public DeathMessageInfo(String name, String deathMessage, String killerName) {
        this(name, deathMessage, killerName, null, null);
    }

    public DeathMessageInfo(String name, String deathMessage, String killerName, String itemName) {
        this(name, deathMessage, killerName, itemName, null);
    }

    public DeathMessageInfo(String name, String deathMessage, String killerName, String itemName, DeathType deathType) {
        this.name = name;
        this.deathMessage = deathMessage;
        this.killerName = killerName;
        this.itemName = itemName;
        this.deathType = deathType;
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

    public void setKillerName(String killerName) {
        this.killerName = killerName;
    }

    public String getKillerNameForTE() {
        return (killerName == null) ? "" : killerName;
    }

    public String getItemName() {
        return itemName;
    }

    public DeathType getDeathType() {
        return deathType;
    }

}
