package gravestone.core.compatibility;

import gravestone.config.GSConfig;
import net.minecraft.entity.player.EntityPlayer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityAntiqueAtlas {

    protected static boolean isInstalled = false;

    private GSCompatibilityAntiqueAtlas() {
    }

    public static void placeDeathMarkerAtDeath(EntityPlayer player) {
        if (isInstalled() && GSConfig.enableAntiqueAtlasDeathMarkers) {
//            List<Integer> atlasesIdList = AtlasAPI.getPlayerAtlases(player);
//            MarkerAPI markerAPI = AtlasAPI.getMarkerAPI();
//            if (markerAPI != null && atlasesIdList != null) {
//                for (Integer atlasId : atlasesIdList) {
//                    markerAPI.putMarker(player.worldObj, true, atlasId, "tomb", player.getCombatTracker().getDeathMessage().getFormattedText(), (int) player.getPosition().getX(), (int) player.getPosition().getZ());
//                }
//            }
        }

    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}
