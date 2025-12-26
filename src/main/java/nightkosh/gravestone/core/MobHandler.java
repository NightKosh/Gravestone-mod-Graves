package nightkosh.gravestone.core;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import nightkosh.gravestone.core.config.GSConfigs;

import javax.annotation.Nullable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static nightkosh.gravestone.ModGraveStone.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MobHandler {

    private static final String MOBS_SPAWN_TIME_FILE_NAME = "MOBS_SPAWN_TIME.mst";
    private static final String MOBS_SPAWN_TIME_BACKUP_FILE_NAME = "MOBS_SPAWN_TIME_Backup.mst";

    private static Path file;
    private static Path backup;

    private static final Map<UUID, Long> MOBS_SPAWN_TIME = new HashMap<>();

    public static void removeSpawnTime(LivingEntity entity) {
        var uuid = entity.getUUID();
        if (MOBS_SPAWN_TIME.containsKey(uuid)) {
            MOBS_SPAWN_TIME.remove(uuid);
            saveMobsSpawnTime(entity.level());
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("Remove spawn time for entity {} with uuid {}",
                        entity.getScoreboardName(), uuid);
            }
        } else if (GSConfigs.DEBUG_MODE.get()) {
            LOGGER.info("Cant remove spawn time for entity {} with uuid {} - no records",
                    entity.getScoreboardName(), uuid);
        }
    }

    public static long getAndRemoveSpawnTime(LivingEntity entity) {
        var uuid = entity.getUUID();
        if (MOBS_SPAWN_TIME.containsKey(uuid)) {
            long time = MOBS_SPAWN_TIME.get(uuid);
            MOBS_SPAWN_TIME.remove(uuid);
            saveMobsSpawnTime(entity.level());
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("Found spawn time for entity {} with uuid {} - {}. Record will be removed.",
                        entity.getScoreboardName(), uuid, time);
            }
            return time;
        } else {
            var time = entity.level().getGameTime();
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("Not found spawn time for entity {} with uuid {}, return current time {}",
                        entity.getScoreboardName(), uuid, time);
            }
            return time;
        }
    }

    public static void setMobSpawnTime(LivingEntity entity) {
        var uuid = entity.getUUID();
        if (GSConfigs.DEBUG_MODE.get()) {
            LOGGER.info("Set current time {} as spawn time for entity {} with uuid {}",
                    entity.level().getGameTime(), entity.getScoreboardName(), uuid);
        }

        MOBS_SPAWN_TIME.putIfAbsent(uuid, entity.level().getGameTime());
        saveMobsSpawnTime(entity.level());
    }

    public static void loadMobsSpawnTime(Level level, Path worldDir) {
        if (level != null && !level.isClientSide()) {
            try {
                file = worldDir.resolve(MOBS_SPAWN_TIME_FILE_NAME);
                backup = worldDir.resolve(MOBS_SPAWN_TIME_BACKUP_FILE_NAME);

                if (Files.notExists(file)) {
                    Files.createFile(file);
                }

                CompoundTag data = null;
                boolean save = false;

                if (Files.exists(file)) {
                    data = readDataFromFile(file);
                }
                if (data == null || data.isEmpty()) {
                    LOGGER.error("Data not found. Trying to load backup data.");
                    if (Files.exists(backup)) {
                        data = readDataFromFile(backup);
                    }
                }
                if (data != null && !data.isEmpty()) {
                    for (String key : data.keySet()) {
                        MOBS_SPAWN_TIME.put(UUID.fromString(key), data.getLong(key).get());
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Error loading mobs spawn time", e);
            }
        }
    }

    public static void saveMobsSpawnTime(Level level) {
        if (level != null && !level.isClientSide() && level instanceof ServerLevel serverLevel) {
            try {
                if (Files.exists(file)) {
                    try {
                        Files.copy(file, backup, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        LOGGER.error("Could not backup old spawn time file", e);
                    }
                }

                var data = new CompoundTag();
                for (var entry : MOBS_SPAWN_TIME.entrySet()) {
                    if (entry != null && entry.getKey() != null) {
                        data.putLong(entry.getKey().toString(), entry.getValue());
                    }
                }

                try (var out = Files.newOutputStream(file)) {
                    NbtIo.writeCompressed(data, out);
                } catch (Exception e) {
                    LOGGER.error("Could not save spawn time file: {}", file, e);
                }
            } catch (Exception e) {
                LOGGER.error("Error saving mobs spawn time", e);
            }
        }
    }

    @Nullable
    private static CompoundTag readDataFromFile(Path file) {
        try (var in = Files.newInputStream(file)) {
            return NbtIo.readCompressed(in, NbtAccounter.unlimitedHeap());
        } catch (Exception e) {
            LOGGER.error("Could not read spawn time file: {}", file, e);
            return null;
        }
    }

}
