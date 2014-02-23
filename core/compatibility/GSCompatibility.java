
package gravestone.core.compatibility;

import cpw.mods.fml.common.Loader;
import forestry.api.core.BlockInterface;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSMobSpawn;
import gravestone.core.GSReciepes;
import thaumcraft.api.ItemApi;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibility {

    private static GSCompatibility instance;

    private GSCompatibility() {
        instance = this;
    }

    public static GSCompatibility getInstance() {
        return (instance == null) ? new GSCompatibility() : instance;
    }
    private static boolean isMoCreaturesInstalled = false;
    // mo_creatures mobs classes
    public static final String MO_CREATURES_S_SKELETON = "drzhark.mocreatures.entity.monster.MoCEntitySilverSkeleton";
    public static final String MO_CREATURES_WRAITH = "drzhark.mocreatures.entity.monster.MoCEntityWraith";
    public static final String MO_CREATURES_F_WRAITH = "drzhark.mocreatures.entity.monster.MoCEntityFlameWraith";
    public static final String MO_CREATURES_SCORPIONS = "drzhark.mocreatures.entity.monster.MoCEntityScorpion";

    public static boolean isMoCreaturesInstalled() {
        return isMoCreaturesInstalled;
    }

    public void checkMods() {
        if (Loader.isModLoaded("MoCreatures")) {
            isMoCreaturesInstalled = true;
            if (GraveStoneConfig.spawnMoCreaturesMobs) {
                GSMobSpawn.addMoCreaturesMobs();
            }
        }

        if (Loader.isModLoaded("Highlands")) {
            GSCompatibilityHighland.isInstalled = true;
            GSCompatibilityHighland.loadBiomes();
            GSCompatibilityHighland.addBiomes();
        }

        if (Loader.isModLoaded("BiomesOPlenty")) {
            GSCompatibilityBiomesOPlenty.isInstalled = true;
            GSCompatibilityBiomesOPlenty.loadBiomes();
            GSCompatibilityBiomesOPlenty.addBiomes();
        }

        if (Loader.isModLoaded("ExtrabiomesXL")) {
            GSCompatibilityExtraBiomesXL.isInstalled = true;
            GSCompatibilityExtraBiomesXL.addBiomes();
        }

        if (Loader.isModLoaded("battlegear2")) {
            GSCompatibilityBattlegear.isInstalled = true;
        }

        if (Loader.isModLoaded("camping")) {
            GSCompatibilityTheCampingMod.isInstalled = true;
        }

        if (Loader.isModLoaded("arsmagica2")) {
            GSCompatibilityisArsMagica.isInstalled = true;
        }
        
        if (Loader.isModLoaded("Backpack")) {
            GSCompatibilityBackpacksMod.isInstalled = true;
        }

        if (Loader.isModLoaded("Thaumcraft")) {
            GSCompatibilityThaumcraft.addAspects();
            GSReciepes.addSkullCandleReciepes(ItemApi.getBlock("blockCandle", 0));
        }
        
        if (Loader.isModLoaded("Forestry")) {
            GSReciepes.addSkullCandleReciepes(BlockInterface.getBlock("candle"));
        }
    }
}
