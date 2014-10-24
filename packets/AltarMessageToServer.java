package gravestone.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import gravestone.core.GSMessageHandler;
import gravestone.item.ItemGSCorpse;
import gravestone.item.corpse.CorpseHelper;
import gravestone.tileentity.TileEntityGSAltar;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AltarMessageToServer implements IMessage, IMessageHandler<AltarMessageToServer, IMessage> {

    private int playerID;
    private int dimensionID;
    private int x;
    private int y;
    private int z;
    private MOB_TYPE mobType;

    public enum MOB_TYPE {
        LIVED,
        ZOMBIE,
        SKELETON,
        GHOST;

        public static MOB_TYPE getMobType(int num) {
            if (MOB_TYPE.values().length <= num) {
                return LIVED;
            } else {
                return MOB_TYPE.values()[num];
            }
        }
    }

    public AltarMessageToServer() {

    }

    public AltarMessageToServer(EntityPlayer player, int x, int y, int z, MOB_TYPE mobType) {
        this.playerID = player.getEntityId();
        this.dimensionID = player.worldObj.provider.dimensionId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.mobType = mobType;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.playerID = buf.readInt();
        this.dimensionID = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.mobType = MOB_TYPE.getMobType(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(playerID);
        buf.writeInt(dimensionID);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(mobType.ordinal());
    }

    @Override
    public IMessage onMessage(AltarMessageToServer message, MessageContext ctx) {
        if (ctx.side.isServer()) {
            World world = DimensionManager.getWorld(message.dimensionID);
            if (world == null || ((ctx.getServerHandler().playerEntity != null) && (ctx.getServerHandler().playerEntity.getEntityId() != message.playerID))) {
                return null;
            }
            EntityPlayer player = (EntityPlayer) world.getEntityByID(message.playerID);
            TileEntity te = world.getTileEntity(message.x, message.y, message.z);
            if (te != null && te instanceof TileEntityGSAltar) {
                TileEntityGSAltar tileEntity = (TileEntityGSAltar) te;
                if (tileEntity.hasCorpse()) {
                    ItemStack corpse = tileEntity.getCorpse();
                    if (corpse != null && corpse.getItem() instanceof ItemGSCorpse && CorpseHelper.canSpawnMob(player, corpse.getItemDamage())) {
                        CorpseHelper.spawnMob(corpse.getItemDamage(), tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, corpse.stackTagCompound, player);
                        CorpseHelper.getExperience(player, corpse.getItemDamage());
                        GSMessageHandler.networkWrapper.sendToAll(new AltarMessageToClient(message.playerID, corpse.getItemDamage()));
                        tileEntity.setCorpse(null);
                    }
                }
            }
        }
        return null;
    }
}
