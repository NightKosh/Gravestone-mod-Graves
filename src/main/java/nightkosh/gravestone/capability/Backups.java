package nightkosh.gravestone.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Backups implements IBackups, INBTSerializable<CompoundTag> {

    private static final int MAX = 5;

    private Deque<Backup> backups = new ArrayDeque<>(MAX);

    @Override
    public Deque<Backup> getBackups() {
        return backups;
    }

    @Override
    public void setBackups(Deque<Backup> backups) {
        this.backups = backups == null ?
                new ArrayDeque<>(MAX) :
                backups;
    }

    @Override
    public Backup getBackup(int num) {
        if (num == 0) {
            return backups.getFirst();
        } else if (num > 0 && num < backups.size()) {
            var it = backups.iterator();
            for (int i = 0; it.hasNext(); i++) {
                var backup = it.next();
                if (i == num) {
                    return backup;
                }
            }
        }
        return null;
    }

    @Override
    public void addBackup(Backup backup) {
        if (backup != null) {
            backups.addFirst(backup);
            if (backups.size() > 5) {
                backups.removeLast();
            }
        }
    }


    @Override
    public CompoundTag serializeNBT() {
        var tag = new CompoundTag();
        var list = new ListTag();

        for (var b : backups) {
            list.add(b.toNBT());
        }

        tag.put("Backups", list);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        backups.clear();

        if (!tag.contains("Backups", Tag.TAG_LIST)) return;

        var list = tag.getList("Backups", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            backups.addLast(Backup.fromNBT(list.getCompound(i)));
        }

        while (backups.size() > MAX) {
            backups.removeLast();
        }
    }

}
