package nightkosh.gravestone.core;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.packet.ChiselMessageToServer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class GSMessages {

    public static final String NETWORK_VERSION = "1";

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final var registrar = event.registrar(NETWORK_VERSION);

        registrar.playToServer(
                ChiselMessageToServer.TYPE,
                ChiselMessageToServer.STREAM_CODEC,
                ChiselMessageToServer::handle
        );
    }

    public static void sendToServer(CustomPacketPayload payload) {
        ClientPacketDistributor.sendToServer(payload);
    }

}
