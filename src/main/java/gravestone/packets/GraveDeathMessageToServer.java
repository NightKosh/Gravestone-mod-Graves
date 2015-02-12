package gravestone.packets;

import gravestone.tileentity.TileEntityGSGrave;
import gravestone.tileentity.TileEntityGSMemorial;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveDeathMessageToServer implements IMessage, IMessageHandler<GraveDeathMessageToServer, IMessage> {

    private int dimensionID;
    private int x;
    private int y;
    private int z;
    private String text;
    private boolean randomText;

    public GraveDeathMessageToServer() {

    }

    public GraveDeathMessageToServer(World world, int x, int y, int z, String text, boolean randomText) {
        this.dimensionID = world.provider.getDimensionId();
        this.x = x;
        this.y = y;
        this.z = z;
        this.text = text;
        this.randomText = randomText;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.dimensionID = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.text = ByteBufUtils.readUTF8String(buf);
        this.randomText = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(dimensionID);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        ByteBufUtils.writeUTF8String(buf, text);
        buf.writeBoolean(randomText);

    }

    @Override
    public IMessage onMessage(GraveDeathMessageToServer message, MessageContext ctx) {
        if (ctx.side.isServer()) {
            World world = DimensionManager.getWorld(message.dimensionID);
            if (world != null) {
                TileEntity te = world.getTileEntity(new BlockPos(message.x, message.y, message.z));
                if (te != null && te instanceof TileEntityGSGrave) {
                    TileEntityGSGrave tileEntity = (TileEntityGSGrave) te;

                    if (message.randomText) {
                        tileEntity.getDeathTextComponent().setRandomDeathTextAndName(new Random(), tileEntity.getGraveTypeNum(), tileEntity instanceof TileEntityGSMemorial, false);
                    } else {
                        tileEntity.getDeathTextComponent().setDeathText(message.text);
                    }
                    world.markBlockForUpdate(new BlockPos(message.x, message.y, message.z));
                }
            }
        }
        return null;
    }
}
