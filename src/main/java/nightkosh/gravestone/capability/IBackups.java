package nightkosh.gravestone.capability;

import java.util.Deque;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface IBackups {

    Deque<Backup> getBackups();

    void setBackups(Deque<Backup> backups);

    Backup getBackup(int num);

    void addBackup(Backup backup);
}
