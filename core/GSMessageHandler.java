package gravestone.core;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import gravestone.packets.AltarMessageToClient;
import gravestone.packets.AltarMessageToServer;
import gravestone.packets.GraveDeathMessageToServer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSMessageHandler {
    public static final SimpleNetworkWrapper networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.ID.toLowerCase());

    public static void init() {
        networkWrapper.registerMessage(GraveDeathMessageToServer.class, GraveDeathMessageToServer.class, 0, Side.SERVER);
        networkWrapper.registerMessage(AltarMessageToServer.class, AltarMessageToServer.class, 1, Side.SERVER);
        networkWrapper.registerMessage(AltarMessageToClient.class, AltarMessageToClient.class, 2, Side.CLIENT);
    }
}
