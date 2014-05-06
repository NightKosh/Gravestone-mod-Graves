package gravestone.core;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import gravestone.tileentity.TileEntityGSGrave;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSPacketHandler extends SimpleChannelInboundHandler<FMLProxyPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket packet) throws Exception {
        if (packet.channel().equals("GSDeathText")) {
            ByteBuf payload = packet.payload();

            TileEntity tileEntity = MinecraftServer.getServer().getEntityWorld().getTileEntity(payload.readInt(), payload.readInt(), payload.readInt());

            if (tileEntity != null) {
                ((TileEntityGSGrave) tileEntity).getDeathTextComponent().setDeathText(ByteBufUtils.readUTF8String(payload));
            }
        }
    }
}
