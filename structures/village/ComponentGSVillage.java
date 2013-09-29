package gravestone.structures.village;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.gen.structure.ComponentVillage;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ComponentGSVillage extends ComponentVillage {

    protected String structureId;

    protected ComponentGSVillage(ComponentVillageStartPiece componentVillageStartPiece, int componentType) {
        super(componentVillageStartPiece, componentType);
    }

    protected void setStructureId(String id) {
        structureId = id;
    }

    @Override
    public NBTTagCompound func_143010_b() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setString("id", structureId);
        nbttagcompound.setTag("BB", this.boundingBox.func_143047_a("BB"));
        nbttagcompound.setInteger("O", this.coordBaseMode);
        nbttagcompound.setInteger("GD", this.componentType);
        this.func_143012_a(nbttagcompound);
        return nbttagcompound;
    }
}
