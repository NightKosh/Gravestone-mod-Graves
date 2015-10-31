package gravestone.core.compatibility;

import gravestone.config.GraveStoneConfig;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.MarkerAPI;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

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
        if (isInstalled() && GraveStoneConfig.enableAntiqueAtlasDeathMarkers) {
            List<Integer> atlasesIdList = AtlasAPI.getPlayerAtlases(player);
            MarkerAPI markerAPI = AtlasAPI.getMarkerAPI();
            if (markerAPI != null && atlasesIdList != null) {
                for (Integer atlasId : atlasesIdList) {
                    markerAPI.putMarker(player.worldObj, true, atlasId, "tomb", player.func_110142_aN().func_151521_b().getFormattedText(), (int) player.posX, (int) player.posZ);
                }
            }
        }

    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}
