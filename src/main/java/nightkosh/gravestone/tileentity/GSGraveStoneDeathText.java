package nightkosh.gravestone.tileentity;

import net.minecraft.nbt.NBTTagCompound;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSGraveStoneDeathText {

    private String name = "";
    private String deathText = "";
    private String killerName = "";
    private boolean isLocalized = false;

    public void readText(NBTTagCompound nbtTag) {
        if (nbtTag.hasKey("isLocalized")) {
            isLocalized = nbtTag.getBoolean("isLocalized");
        }

        if (isLocalized) {
            name = nbtTag.getString("name");
            deathText = nbtTag.getString("DeathText");
            killerName = nbtTag.getString("KillerName");
        } else {
            deathText = nbtTag.getString("DeathText");
        }
    }

    public void saveText(NBTTagCompound nbtTag) {
        if (isLocalized) {
            nbtTag.setString("name", name);
            nbtTag.setString("DeathText", deathText);
            nbtTag.setString("KillerName", killerName);
        } else {
            nbtTag.setString("DeathText", deathText);
        }

        nbtTag.setBoolean("isLocalized", isLocalized);
    }

    public boolean isLocalized() {
        return isLocalized;
    }

    public void setLocalized() {
        isLocalized = true;
    }

    public String getName() {
        return name;
    }

    public String getDeathText() {
        return deathText;
    }

    public String getKillerName() {
        return killerName;
    }

    public void setName(String name) {
        this.name = (name == null) ? "" : name;
    }

    public void setDeathText(String text) {
        deathText = (text == null) ? "" : text;
    }

    public void setKillerName(String name) {
        killerName = (name == null) ? "" : name;
    }

}
