package nightkosh.gravestone.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.block_entity.GraveStoneBlockEntity;
import nightkosh.gravestone.core.config.GSConfigs;

import javax.annotation.Nonnull;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;
import static nightkosh.gravestone.ModGraveStone.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public record ChiselMessageToServer(
        int xPos,
        int yPos,
        int zPos,
        String text
) implements CustomPacketPayload {

    public static final Type<ChiselMessageToServer> TYPE =
            new Type<>(fromNamespaceAndPath(ModInfo.ID, "chisel"));

    public static final StreamCodec<ByteBuf, ChiselMessageToServer> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT, ChiselMessageToServer::xPos,
                    ByteBufCodecs.INT, ChiselMessageToServer::yPos,
                    ByteBufCodecs.INT, ChiselMessageToServer::zPos,
                    ByteBufCodecs.STRING_UTF8, ChiselMessageToServer::text,
                    ChiselMessageToServer::new
            );

    @Nonnull
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ChiselMessageToServer msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("Going to handle ChiselMessageToServer");
            }
            var pos = new BlockPos(msg.xPos, msg.yPos, msg.zPos);
            var level = context.player().level();
            if (level.getBlockEntity(pos) instanceof GraveStoneBlockEntity grave) {
                if (GSConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("Going to set grave text at {}", pos.toShortString());
                }
                grave.setDeathMessageJson(msg.text());
                grave.setChanged();
            }
        });
    }

}
