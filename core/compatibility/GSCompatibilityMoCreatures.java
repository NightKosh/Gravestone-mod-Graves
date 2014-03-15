package gravestone.core.compatibility;

import gravestone.GraveStoneLogger;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSMobSpawn;
import java.lang.reflect.Constructor;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityMoCreatures {

    protected static boolean isInstalled = false;

    // mobs classes
    public static final String MO_CREATURES_S_SKELETON = "drzhark.mocreatures.entity.monster.MoCEntitySilverSkeleton";
    public static final String MO_CREATURES_WRAITH = "drzhark.mocreatures.entity.monster.MoCEntityWraith";
    public static final String MO_CREATURES_F_WRAITH = "drzhark.mocreatures.entity.monster.MoCEntityFlameWraith";
    public static final String MO_CREATURES_SCORPIONS = "drzhark.mocreatures.entity.monster.MoCEntityScorpion";
    
    private GSCompatibilityMoCreatures() {
    }

    public static void addMobs() {
        if (GraveStoneConfig.spawnMoCreaturesMobs) {
        GraveStoneLogger.logInfo("start Mo'Creatures mobs loading");

        addMobToList(GSMobSpawn.MOB_ID, "SilverSkeleton", getForeinMobConstructor(GSCompatibilityMoCreatures.MO_CREATURES_S_SKELETON));
        addMobToList(GSMobSpawn.MOB_ID, "Wraith", getForeinMobConstructor(GSCompatibilityMoCreatures.MO_CREATURES_WRAITH));
        addMobToList(GSMobSpawn.HELL_MOB_ID, "FlameWraith", getForeinMobConstructor(GSCompatibilityMoCreatures.MO_CREATURES_F_WRAITH));
        
        
        addMobToList(GSMobSpawn.skeletonSpawnerMobs, "SilverSkeleton", getForeinMobConstructor(GSCompatibilityMoCreatures.MO_CREATURES_S_SKELETON));

        GraveStoneLogger.logInfo("end Mo'Creatures mobs loading");
        }
    }

    /**
     * Return constructor for forein mobs classes based on class path
     *
     * @param path Class Path with name
     */
    private static Constructor getForeinMobConstructor(String path) {
        Constructor<EntityLiving> constructor = null;

        try {
            Class mobClass = Class.forName(path);
            constructor = mobClass.getConstructor(World.class);
        } catch (ClassNotFoundException e) {
            GraveStoneLogger.logError("getForeinMobConstructor ClassNotFoundException. class path " + path);
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            GraveStoneLogger.logError("getForeinMobConstructor NoSuchMethodException. class path " + path);
            e.printStackTrace();
        }

        return constructor;
    }

    private static void addMobToList(List<String> MOB_ID, String mobName, Constructor<EntityLiving> constructor) {
        if (constructor != null) {
            MOB_ID.add(mobName);
            GSMobSpawn.mobNameToClassMapping.put(mobName, constructor);
        }
    }

    public static boolean isLoaded() {
        return isInstalled;
    }
}
