package nightkosh.gravestone.block_entity;

import net.minecraft.nbt.CompoundTag;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneDeathText {

    private String name = "";
    private String deathText = "";
    private String killerName = "";
    private boolean isLocalized = false;

    public void readText(CompoundTag tag) {
        if (tag.contains("isLocalized")) {
            isLocalized = tag.getBoolean("isLocalized");
        }

        if (isLocalized) {
            name = tag.getString("name");
            deathText = tag.getString("DeathText");
            killerName = tag.getString("KillerName");
        } else {
            deathText = tag.getString("DeathText");
        }
    }

    public void saveText(CompoundTag tag) {
        if (isLocalized) {
            tag.putString("name", name);
            tag.putString("DeathText", deathText);
            tag.putString("KillerName", killerName);
        } else {
            tag.putString("DeathText", deathText);
        }

        tag.putBoolean("isLocalized", isLocalized);
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
