package gravestone.core;

import gravestone.tileentity.TileEntityGSGrave;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSPacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (packet.channel.equals("GSDeathText")) {
            World world = ((EntityPlayer) player).worldObj;

            if (world != null) {
                DataInputStream InputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

                try {
                    TileEntity tileEntity = world.getBlockTileEntity(InputStream.readInt(), InputStream.readInt(), InputStream.readInt());

                    if (tileEntity != null) {
                        String str = InputStream.readUTF();
                        ((TileEntityGSGrave) tileEntity).getDeathTextComponent().setDeathText(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
