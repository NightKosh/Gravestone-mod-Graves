package nightkosh.gravestone.capability;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
//TODO
public class BackupStorage {//implements Capability.IStorage<IBackups> {
//
//    @Nullable
//    @Override
//    public NBTBase writeNBT(Capability<IBackups> capability, IBackups backups, EnumFacing side) {
//        var tag = new CompoundTag();
//
//        Deque<Backup> backupDeque = backups.getBackups();
//        if (backupDeque != null) {
//            tag.put("Backups", getBackupsNBT(backupDeque));
//        }
//
//        return tag;
//    }
//
//    private static NBTTagList getBackupsNBT(Deque<Backup> backups) {
//        var list = new NBTTagList();
//
//        for (Backup backup : backups) {
//            var backupNbt = new CompoundTag();
//            backupNbt.putInt("Dimension", backup.getDimensionId());
//            backupNbt.putInt("PosX", backup.getPos().getX());
//            backupNbt.putInt("PosY", backup.getPos().getY());
//            backupNbt.putInt("PosZ", backup.getPos().getZ());
//
//            var ntbList = new NBTTagList();
//
//            for (var stack : backup.getItems()) {
//                if (stack != null && stack != ItemStack.EMPTY) {
//                    CompoundTag nbt = new CompoundTag();
//                    stack.writeToNBT(nbt);
//                    ntbList.appendTag(nbt);
//                }
//            }
//
//            backupNbt.put("Items", ntbList);
//
//            list.appendTag(backupNbt);
//        }
//
//        return list;
//    }
//
//    @Override
//    public void readNBT(Capability<IBackups> capability, IBackups backups, EnumFacing side, NBTBase nbt) {
//        if (((CompoundTag) nbt).contains("Backups")) {
//            backups.setBackups(getBackups((NBTTagList) ((CompoundTag) nbt).getTag("Backups")));
//
//        }
//    }
//
//    private static Deque<Backup> getBackups(NBTTagList backupsNbt) {
//        Deque<Backup> backups = new ArrayDeque<>(5);
//
//        for (int index = 0; index < 5; index++ ) {
//            CompoundTag tag = backupsNbt.getCompoundTagAt(index);
//
//            Backup backup = new Backup();
//            backup.setDimensionId(tag.getInt("Dimension"));
//            backup.setPos(new BlockPos(
//                    tag.getInt("PosX"),
//                    tag.getInt("PosY"),
//                    tag.getInt("PosZ")
//            ));
//
//            NBTTagList ntbItemsList = tag.getTagList("Items", 10);
//            List<ItemStack> items = new ArrayList<>();
//
//            for (int i = 0; i < ntbItemsList.tagCount(); ++i) {
//                items.add(new ItemStack(ntbItemsList.getCompoundTagAt(i)));
//            }
//            backup.setItems(items);
//
//            backups.addLast(backup);
//        }
//        return backups;
//    }
}
