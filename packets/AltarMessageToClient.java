package gravestone.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import gravestone.item.corpse.CorpseHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AltarMessageToClient implements IMessage, IMessageHandler<AltarMessageToClient, IMessage> {

    private int playerID;
    private int mobTypeId;

    public AltarMessageToClient() {

    }

    public AltarMessageToClient(int playerID, int mobTypeId) {
        this.playerID = playerID;
        this.mobTypeId = mobTypeId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.playerID = buf.readInt();
        this.mobTypeId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(playerID);
        buf.writeInt(mobTypeId);
    }

    @Override
    public IMessage onMessage(AltarMessageToClient message, MessageContext ctx) {
        if (ctx.side.isClient()) {
            World world = Minecraft.getMinecraft().theWorld;
            if (world != null) {
                Entity player = world.getEntityByID(message.playerID);
                if (player != null && player instanceof EntityPlayer) {
                    CorpseHelper.getExperience((EntityPlayer) player, message.mobTypeId);
                    Minecraft.getMinecraft().displayGuiScreen((GuiScreen) null);
                }
            }
        }
        return null;
    }
}
