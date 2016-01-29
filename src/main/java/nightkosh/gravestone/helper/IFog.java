package nightkosh.gravestone.helper;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface IFog {

    //    public static final int FOG_RANGE = 30; TODO

    public default void addFog(World world, BlockPos pos) {
        //TODO
//        if (this.worldObj.isRemote && Config.isFogEnabled && TickEventHandler.getFogTicCount() == 0) {
//                EntityPlayer player = this.worldObj.getClosestPlayer(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D, FOG_RANGE);
//                if (player != null && player.getCommandSenderEntity().equals(Minecraft.getMinecraft().thePlayer) && TimeHelper.isFogTime(this.worldObj)) {
//                    RenderEventHandler.addFog();
//                }
//            }
    }
}
