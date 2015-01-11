package gravestone.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AltarMessageToClient implements IMessage, IMessageHandler<AltarMessageToClient, IMessage> {

    public AltarMessageToClient() {

    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public IMessage onMessage(AltarMessageToClient message, MessageContext ctx) {
        if (ctx.side.isClient()) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen) null);
        }
        return null;
    }
}
