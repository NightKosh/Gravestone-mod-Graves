package GraveStone.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomItem;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSGraveStoneSpawnData extends WeightedRandomItem {

    public final NBTTagCompound field_92032_b;
    public final String field_92033_c;
    final TileEntityGSGraveStone field_92031_d;

    public TileEntityGSGraveStoneSpawnData(TileEntityGSGraveStone tileEntity, NBTTagCompound nbtTag) {
        super(nbtTag.getInteger("Weight"));
        this.field_92031_d = tileEntity;
        this.field_92032_b = nbtTag.getCompoundTag("Properties");
        this.field_92033_c = nbtTag.getString("Type");
    }

    public TileEntityGSGraveStoneSpawnData(TileEntityGSGraveStone tileEntity, NBTTagCompound nbtTag, String par3Str) {
        super(1);
        this.field_92031_d = tileEntity;
        this.field_92032_b = nbtTag;
        this.field_92033_c = par3Str;
    }

    public NBTTagCompound func_92030_a() {
        NBTTagCompound var1 = new NBTTagCompound();
        var1.setCompoundTag("Properties", this.field_92032_b);
        var1.setString("Type", this.field_92033_c);
        var1.setInteger("Weight", this.itemWeight);
        return var1;
    }
}