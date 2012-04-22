
package GraveStone.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomItem;

public class TileEntityGSGraveStoneSpawnData extends WeightedRandomItem {
    public final NBTTagCompound field_92032_b;
    public final String field_92033_c;

    final TileEntityGSGraveStone field_92031_d;

    public TileEntityGSGraveStoneSpawnData(TileEntityGSGraveStone par1TileEntityGraveStone, NBTTagCompound par2NBTTagCompound) {
        super(par2NBTTagCompound.getInteger("Weight"));
        this.field_92031_d = par1TileEntityGraveStone;
        this.field_92032_b = par2NBTTagCompound.getCompoundTag("Properties");
        this.field_92033_c = par2NBTTagCompound.getString("Type");
    }

    public TileEntityGSGraveStoneSpawnData(TileEntityGSGraveStone par1TileEntityGraveStone, NBTTagCompound par2NBTTagCompound, String par3Str) {
        super(1);
        this.field_92031_d = par1TileEntityGraveStone;
        this.field_92032_b = par2NBTTagCompound;
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