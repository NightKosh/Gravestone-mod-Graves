package gravestone.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import gravestone.tileentity.TileEntityGSGrave;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            case 1:
                return null;//return new ContainerEnchantment(player.inventory, world, x, y, z);
            case 0:
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            case 0:
                TileEntity tileEntity = world.getTileEntity(x, y, z);

                if (tileEntity != null) {
                    return new GSGuiGrave((TileEntityGSGrave) tileEntity);
                } else {
                    return false;
                }
            default:
                return null;
        }
    }
}
