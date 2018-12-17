package nightkosh.gravestone.capability;

import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Backups implements IBackups {

    private Deque<Backup> backups = new ArrayDeque<>(5);

    @Override
    public Deque<Backup> getBackups() {
        return backups;
    }

    @Override
    public void setBackups(Deque<Backup> backups) {
        this.backups = backups;
    }

    @Override
    public Backup getBackup(int num) {
        if (num == 0) {
            return backups.getFirst();
        } else {
            Iterator<Backup> it = backups.iterator();
            int i = 0;
            while (it.hasNext()) {
                Backup backup = it.next();
                if (i == num) {
                    return backup;
                }
                i++;
            }
        }
        return null;
    }

    @Override
    public void addBackup(Backup backup) {
        backups.addFirst(backup);
        if (backups.size() > 5) {
            backups.removeLast();
        }
    }
}
