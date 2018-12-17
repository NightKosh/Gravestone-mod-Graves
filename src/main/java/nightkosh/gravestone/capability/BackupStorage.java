package nightkosh.gravestone.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BackupStorage implements Capability.IStorage<IBackups> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IBackups> capability, IBackups backups, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();

        Deque<Backup> backupDeque = backups.getBackups();
        if (backupDeque != null) {
            nbt.setTag("Backups", getBackupsNBT(backupDeque));
        }

        return nbt;
    }

    private static NBTTagList getBackupsNBT(Deque<Backup> backups) {
        NBTTagList list = new NBTTagList();

        for (Backup backup : backups) {
            NBTTagCompound backupNbt = new NBTTagCompound();
            backupNbt.setInteger("Dimension", backup.getDimensionId());
            backupNbt.setInteger("PosX", backup.getPos().getX());
            backupNbt.setInteger("PosY", backup.getPos().getY());
            backupNbt.setInteger("PosZ", backup.getPos().getZ());

            NBTTagList ntbList = new NBTTagList();

            for (ItemStack stack : backup.getItems()) {
                if (stack != null && stack != ItemStack.EMPTY) {
                    NBTTagCompound nbt = new NBTTagCompound();
                    stack.writeToNBT(nbt);
                    ntbList.appendTag(nbt);
                }
            }

            backupNbt.setTag("Items", ntbList);

            list.appendTag(backupNbt);
        }

        return list;
    }

    @Override
    public void readNBT(Capability<IBackups> capability, IBackups backups, EnumFacing side, NBTBase nbt) {
        if (((NBTTagCompound) nbt).hasKey("Backups")) {
            backups.setBackups(getBackups((NBTTagList) ((NBTTagCompound) nbt).getTag("Backups")));

        }
    }

    private static Deque<Backup> getBackups(NBTTagList backupsNbt) {
        Deque<Backup> backups = new ArrayDeque<>(5);

        for (int index = 0; index < 5; index++ ) {
            NBTTagCompound nbt = backupsNbt.getCompoundTagAt(index);

            Backup backup = new Backup();
            backup.setDimensionId(nbt.getInteger("Dimension"));
            backup.setPos(new BlockPos(
                    nbt.getInteger("PosX"),
                    nbt.getInteger("PosY"),
                    nbt.getInteger("PosZ")
            ));

            NBTTagList ntbItemsList = nbt.getTagList("Items", 10);
            List<ItemStack> items = new ArrayList<>();

            for (int i = 0; i < ntbItemsList.tagCount(); ++i) {
                items.add(new ItemStack(ntbItemsList.getCompoundTagAt(i)));
            }
            backup.setItems(items);

            backups.addLast(backup);
        }
        return backups;
    }
}
