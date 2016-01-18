package gravestone.tileentity;

import gravestone.helper.DeathTextHelper;

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
    private DeathTextHelper.DeathType deathType;

    public DeathMessageInfo(String name, String deathMessage, String killerName) {
        this(name, deathMessage, killerName, null, null);
    }

    public DeathMessageInfo(String name, String deathMessage, String killerName, String itemName) {
        this(name, deathMessage, killerName, itemName, null);
    }

    public DeathMessageInfo(String name, String deathMessage, String killerName, String itemName, DeathTextHelper.DeathType deathType) {
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

    public DeathTextHelper.DeathType getDeathType() {
        return deathType;
    }
}
