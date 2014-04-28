package gravestone.core;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import gravestone.tileentity.TileEntityGSGrave;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.tileentity.TileEntity;

import java.io.IOException;

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

//            try {
//                int bytesCount = payload.readableBytes();
//                if (bytesCount >= 12) {
//                    TileEntity tileEntity = world.getTileEntity(payload.readInt(), payload.readInt(), payload.readInt());
//
//                    if (tileEntity != null) {
//                        StringBuffer str = new StringBuffer();
//                        for (int i = 12; i < bytesCount; i++) {
//                            str.append(payload.readChar());
//                        }
//                        ((TileEntityGSGrave) tileEntity).getDeathTextComponent().setDeathText(str.toString());
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
