
package GraveStone.tileentity;

import GraveStone.GraveStoneConfig;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GSGraveStoneDeathText {
    
    // grave text
    private String deathText = "";
    private TileEntityGSGrave tileEntity;
    
    public GSGraveStoneDeathText(TileEntityGSGrave tileEntity) {
        this.tileEntity = tileEntity;
    }
    
    public void readText(NBTTagCompound nbtTag) {
        deathText = nbtTag.getString("DeathText");
    }
    
    public void saveText(NBTTagCompound nbtTag) {
        nbtTag.setString("DeathText", deathText);
    }
    
    public String getDeathText() {
        return deathText;
    }
    
    public void setDeathText(String text) {
        deathText = text;
    }
    
    public void setRandomDeathText(Random random, byte graveType, boolean isMemorial) {
        if (isMemorial && random.nextInt(5) > 2) {
            deathText = this.getValue(random, GraveStoneConfig.memorialText);
            return;
        }
        switch (graveType) {
            case 3:
                deathText = this.getValue(random, GraveStoneConfig.graveDogsNames);
                break;
            case 4:
                deathText = this.getValue(random, GraveStoneConfig.graveCatsNames);
                break;
            default:
                deathText = this.getValue(random, GraveStoneConfig.graveNames);
        }
        deathText += this.getValue(random, GraveStoneConfig.graveDeathMessages);
    }
    
    private String getValue(Random random, ArrayList<String> list) {
        if (list != null) {
            return list.get(random.nextInt(list.size()));
        } else {
            return "";
        }
    }
}
