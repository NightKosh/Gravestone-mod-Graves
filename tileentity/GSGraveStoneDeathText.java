package GraveStone.tileentity;

import GraveStone.GraveStoneConfig;
import GraveStone.block.EnumMemorials;
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
        this.name = name;
    }

    public void setDeathText(String text) {
        deathText = text;
    }

    public void setKillerName(String name) {
        killerName = name;
    }

    public void setRandomDeathTextAndName(Random random, byte graveType, boolean isMemorial) {
        isLocalized = true;

        if (isMemorial) {
            if (graveType == EnumMemorials.CREEPER_STATUE.getId()) {
                deathText = "Sssssssssssssss...";
            } else {
                if (random.nextInt(5) > 2) {
                    switch (graveType) {
                        case 5:
                            deathText = this.getValue(random, GraveStoneConfig.dogsMemorialText);
                            break;
                        case 6:
                            deathText = this.getValue(random, GraveStoneConfig.catsMemorialText);
                            break;
                        default:
                            deathText = this.getValue(random, GraveStoneConfig.memorialText);
                    }
                } else {
                    switch (graveType) {
                        case 5:
                            name = this.getValue(random, GraveStoneConfig.graveDogsNames);
                            break;
                        case 6:
                            name = this.getValue(random, GraveStoneConfig.graveCatsNames);
                            break;
                        default:
                            name = this.getValue(random, GraveStoneConfig.graveNames);
                    }
                    deathText = this.getValue(random, GraveStoneConfig.graveDeathMessages);
                }
            }
        } else {
            switch (graveType) {
                case 3:
                    name = this.getValue(random, GraveStoneConfig.graveDogsNames);
                    break;
                case 4:
                    name = this.getValue(random, GraveStoneConfig.graveCatsNames);
                    break;
                default:
                    name = this.getValue(random, GraveStoneConfig.graveNames);
            }

            deathText = this.getValue(random, GraveStoneConfig.graveDeathMessages);
        }
    }

    private String getValue(Random random, ArrayList<String> list) {
        if (list != null && list.size() > 0) {
            return list.get(random.nextInt(list.size()));
        } else {
            return "";
        }
    }
}
