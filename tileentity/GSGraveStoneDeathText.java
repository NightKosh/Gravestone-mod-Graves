package gravestone.tileentity;

import gravestone.GraveStoneConfig;
import gravestone.block.EnumGraves;
import gravestone.block.EnumMemorials;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSGraveStoneDeathText {

    // grave text
    private String name = "";
    private String deathText = "";
    private String killerName = "";
    private boolean isLocalized = false;
    private TileEntityGSGrave tileEntity;

    public GSGraveStoneDeathText(TileEntityGSGrave tileEntity) {
        this.tileEntity = tileEntity;
    }

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

    public void setRandomDeathTextAndName(Random random, byte grave, boolean isMemorial) {
        isLocalized = true;
        EnumGraves graveType = EnumGraves.getByID(grave);
        EnumMemorials memorialType = EnumMemorials.getByID(grave);

        if (isMemorial) {
            switch (memorialType) {
                case DOG_STATUE:
                    getRandomMemorialContent(random, GraveStoneConfig.graveDogsNames, GraveStoneConfig.dogsMemorialText);
                    break;
                case CAT_STATUE:
                    getRandomMemorialContent(random, GraveStoneConfig.graveCatsNames, GraveStoneConfig.catsMemorialText);
                    break;
                case CREEPER_STATUE:
                    deathText = "Sssssssssssssss...";
                default:
                    getRandomMemorialContent(random, GraveStoneConfig.graveNames, GraveStoneConfig.memorialText);
            }
        } else {
            switch (graveType) {
                case DOG_STATUE:
                    name = this.getValue(random, GraveStoneConfig.graveDogsNames);
                    break;
                case CAT_STATUE:
                    name = this.getValue(random, GraveStoneConfig.graveCatsNames);
                    break;
                default:
                    name = this.getValue(random, GraveStoneConfig.graveNames);
            }

            getDeathMessage(random);
        }
    }

    private void getRandomMemorialContent(Random random, ArrayList<String> nameList, ArrayList<String> textList) {
        if (random.nextInt(5) > 2) {
            deathText = this.getValue(random, textList);
        } else {
            name = this.getValue(random, nameList);
            getDeathMessage(random);
        }
    }

    private void getDeathMessage(Random random) {
        DeathMessageInfo deathMessageInfo = DeathMessageInfo.getRandomDeathMessage(random);
        deathText = deathMessageInfo.getDeathMessage();
        killerName = deathMessageInfo.getKillerNameForTE();
    }

    private String getValue(Random random, ArrayList<String> list) {
        if (list != null && list.size() > 0) {
            return list.get(random.nextInt(list.size()));
        } else {
            return "";
        }
    }
}
