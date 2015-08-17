package gravestone.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ChiselMessageToServer implements IMessage, IMessageHandler<ChiselMessageToServer, IMessage> {

    private int playerID;
    private int dimensionID;
    private boolean isGravestone;

    public ChiselMessageToServer() {
    }

    public ChiselMessageToServer(EntityPlayer player, boolean isGravestone) {
        this.playerID = player.getEntityId();
        this.dimensionID = player.worldObj.provider.getDimensionId();
        this.isGravestone = isGravestone;

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.playerID = buf.readInt();
        this.dimensionID = buf.readInt();
        this.isGravestone = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(playerID);
        buf.writeInt(dimensionID);
        buf.writeBoolean(isGravestone);
    }

    @Override
    public IMessage onMessage(ChiselMessageToServer message, MessageContext ctx) {
        if (ctx.side.isServer()) {
            World world = DimensionManager.getWorld(message.dimensionID);
            if (world == null || ((ctx.getServerHandler().playerEntity != null) && (ctx.getServerHandler().playerEntity.getEntityId() != message.playerID))) {
                return null;
            }
            EntityPlayer player = (EntityPlayer) world.getEntityByID(message.playerID);
        }
        return null;
    }
}
