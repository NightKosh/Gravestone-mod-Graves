package nightkosh.gravestone.capability;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Backups implements IBackups {

    private static final String KEY_LIST = "Backups";
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
        while (this.backups.size() > MAX) {
            this.backups.removeLast();
        }
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

    public void write(ValueOutput out) {
        var list = out.childrenList(KEY_LIST);
        for (var b : backups) {
            if (b != null) {
                b.write(list.addChild());
            }
        }
    }

    public void read(ValueInput in) {
        backups.clear();

        var list = in.childrenListOrEmpty(KEY_LIST);
        for (var child : list) {
            backups.addLast(Backup.read(child));
        }

        while (backups.size() > MAX) {
            backups.removeLast();
        }
    }

}
