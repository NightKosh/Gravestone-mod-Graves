package GraveStone.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import GraveStone.tileentity.TileEntityGSGrave;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            case 0:
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if (tileEntity != null) {
                    return new GuiGrave((TileEntityGSGrave) tileEntity);
                } else {
                    return false;
                }
            default:
                return null;
        }
    }
}
