package nightkosh.gravestone.core;

import com.google.common.io.Files;
import net.minecraft.world.level.Level;
import nightkosh.gravestone.core.logger.GSLogger;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.CompoundTag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.mojang.text2speech.Narrator.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MobHandler {
    public static final String MOBS_SPAWN_TIME_FILE_NAME = "mobsSpawnTime.gs";
    public static final String MOBS_SPAWN_TIME_BACKUP_FILE_NAME = "mobsSpawnTimeBackup.gs";

    private static HashMap<String, Long> mobsSpawnTime = new HashMap<>();

    public static void clearMobsSpawnTime(Entity entity) {
        mobsSpawnTime.remove(entity.getUniqueID().toString());
        saveMobsSpawnTime(entity.getEntityWorld());
    }

    public static long getAndRemoveSpawnTime(Entity entity) {
        if (mobsSpawnTime.containsKey(entity.getUniqueID().toString())) {
            long time = mobsSpawnTime.get(entity.getUniqueID().toString());
            clearMobsSpawnTime(entity);
            return time;
        } else {
            return entity.getEntityWorld().getWorldTime();
        }
    }

    public static long getMobSpawnTime(Entity entity) {
        if (!mobsSpawnTime.containsKey(entity.getUniqueID().toString())) {
            mobsSpawnTime.put(entity.getUniqueID().toString(), entity.getEntityWorld().getWorldTime());
            saveMobsSpawnTime(entity.getEntityWorld());
        }
        return mobsSpawnTime.get(entity.getUniqueID().toString());
    }

    public static void setMobSpawnTime(Entity entity) {
        if (!mobsSpawnTime.containsKey(entity.getUniqueID().toString())) {
            mobsSpawnTime.put(entity.getUniqueID().toString(), entity.getEntityWorld().getWorldTime());
            saveMobsSpawnTime(entity.getEntityWorld());
        }
    }

    public static void loadMobsSpawnTime(Level level) {
        try {
            File file = new File(level.getSaveHandler().getWorldDirectory(), MOBS_SPAWN_TIME_FILE_NAME);
            File backup = new File(level.getSaveHandler().getWorldDirectory(), MOBS_SPAWN_TIME_BACKUP_FILE_NAME);

            CompoundTag data = null;
            boolean save = false;
            if (file != null && file.exists()) {
                data = getDataFromFile(file);
            }
            if (file == null || !file.exists() || data == null || data.hasNoTags()) {
                LOGGER.error("Data not found. Trying to load backup data.");
                if (backup != null && backup.exists()) {
                    data = getDataFromFile(backup);
                    save = true;
                }
            }
            if (data != null) {
                Set keySet = data.getKeySet();
                Iterator it = keySet.iterator();
                while (it.hasNext()) {
                    String tagName = (String) it.next();
                    mobsSpawnTime.put(tagName, data.getLong(tagName));
                }
                if (save) {
                    saveMobsSpawnTime(level);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error loading mobs spawn time");
            e.printStackTrace();
        }
    }

    public static void saveMobsSpawnTime(World world) {
        if (world != null && !world.isClientSide() && mobsSpawnTime != null) {
            try {
                File file = new File(world.getSaveHandler().getWorldDirectory(), MOBS_SPAWN_TIME_FILE_NAME);
                File backup = new File(world.getSaveHandler().getWorldDirectory(), MOBS_SPAWN_TIME_BACKUP_FILE_NAME);

                if (file != null && file.exists()) {
                    try {
                        Files.copy(file, backup);
                    } catch (Exception e) {
                        LOGGER.error("Could not backup old spawn time file");
                    }
                }
                try {
                    if (file != null) {
                        CompoundTag data = new CompoundTag();
                        for (Map.Entry<String, Long> entry : mobsSpawnTime.entrySet()) {
                            if (entry != null) {
                                data.setLong(entry.getKey(), entry.getValue());
                            }
                        }
                        FileOutputStream fileoutputstream = new FileOutputStream(file);
                        CompressedStreamTools.writeCompressed(data, fileoutputstream);
                        fileoutputstream.close();
                    }
                } catch (Exception e) {
                    LOGGER.error("Could not save spawn time file");
                    e.printStackTrace();
                    if (file.exists()) {
                        try {
                            file.delete();
                        } catch (Exception e2) {
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Error saving mobs spawn time");
                e.printStackTrace();
            }
        }
    }

    private static CompoundTag getDataFromFile(File file) {
        CompoundTag data = null;
        try {
            FileInputStream fileinputstream = new FileInputStream(file);
            data = CompressedStreamTools.readCompressed(fileinputstream);
            fileinputstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
