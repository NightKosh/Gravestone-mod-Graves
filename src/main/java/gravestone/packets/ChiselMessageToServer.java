package gravestone.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
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
    private int graveType;
    private int material;
    private boolean isEnchanted;
    private boolean isMossy;

    public ChiselMessageToServer() {
    }

    public ChiselMessageToServer(EntityPlayer player, boolean isGravestone, int graveType, int material, boolean isEnchanted, boolean isMossy) {
        this.playerID = player.getEntityId();
        this.dimensionID = player.worldObj.provider.getDimensionId();
        this.isGravestone = isGravestone;
        this.graveType = graveType;
        this.material = material;
        this.isEnchanted = isEnchanted;
        this.isMossy = isMossy;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.playerID = buf.readInt();
        this.dimensionID = buf.readInt();
        this.isGravestone = buf.readBoolean();
        this.graveType = buf.readInt();
        this.material = buf.readInt();
        this.isEnchanted = buf.readBoolean();
        this.isMossy = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(playerID);
        buf.writeInt(dimensionID);
        buf.writeBoolean(isGravestone);
        buf.writeInt(graveType);
        buf.writeInt(material);
        buf.writeBoolean(isEnchanted);
        buf.writeBoolean(isMossy);
    }

    @Override
    public IMessage onMessage(ChiselMessageToServer message, MessageContext ctx) {
        if (ctx.side.isServer()) {
            World world = DimensionManager.getWorld(message.dimensionID);
            if (world == null || ((ctx.getServerHandler().playerEntity != null) && (ctx.getServerHandler().playerEntity.getEntityId() != message.playerID))) {
                return null;
            }
            EntityPlayer player = (EntityPlayer) world.getEntityByID(message.playerID);
            NBTTagCompound playerNbt = new NBTTagCompound();// = player.getEntityData();
            player.writeEntityToNBT(playerNbt);
            NBTTagCompound nbt = new NBTTagCompound();
            NBTTagCompound graveNbt = new NBTTagCompound();
            graveNbt.setBoolean("IsGravestone", isGravestone);
            graveNbt.setInteger("GraveType", graveType);
            graveNbt.setInteger("Material", material);
            graveNbt.setBoolean("IsEnchanted", isEnchanted);
            graveNbt.setBoolean("IsMossy", isMossy);
            playerNbt.setTag("GraveCrafting", graveNbt);
//            player.writeToNBT(nbt);
            player.readEntityFromNBT(playerNbt);
        }
        return null;
    }
}
