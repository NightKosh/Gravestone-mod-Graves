package nightkosh.gravestone.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BackupProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IBackups.class)
    public static final Capability<IBackups> BACKUP_CAP = null;
    private IBackups instance = BACKUP_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == BACKUP_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == BACKUP_CAP ? BACKUP_CAP.<T>cast(this.instance) : null;

    }

    @Override
    public NBTBase serializeNBT() {
        return BACKUP_CAP.getStorage().writeNBT(BACKUP_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        BACKUP_CAP.getStorage().readNBT(BACKUP_CAP, this.instance, null, nbt);
    }
}
