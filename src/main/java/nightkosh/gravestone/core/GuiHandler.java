package nightkosh.gravestone.core;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import nightkosh.gravestone.gui.GraveInventoryGui;
import nightkosh.gravestone.gui.container.GraveContainer;
import nightkosh.gravestone.tileentity.GraveStoneBlockEntity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
//TODO
public class GuiHandler {// implements IGuiHandler {

    public static final int GRAVE_INVENTORY_GUI_ID = 0;

//    @Override
//    public Object getServerGuiElement(int id, Player player, Level level, int x, int y, int z) {
//        BlockEntity tileEntity;
//        switch (id) {
//            case GRAVE_INVENTORY_GUI_ID:
//                tileEntity = level.getBlockEntity(new BlockPos(x, y, z));
//                if (tileEntity instanceof GraveStoneBlockEntity) {
//                    return new GraveContainer(player.inventory, (GraveStoneBlockEntity) tileEntity);
//                }
//                break;
//        }
//        return null;
//    }
//
//    @Override
//    public Object getClientGuiElement(int id, Player player, Level level, int x, int y, int z) {
//        BlockEntity tileEntity;
//        switch (id) {
//            case GRAVE_INVENTORY_GUI_ID:
//                tileEntity = level.getBlockEntity(new BlockPos(x, y, z));
//                if (tileEntity instanceof GraveStoneBlockEntity) {
//                    return new GraveInventoryGui(player.inventory, (GraveStoneBlockEntity) tileEntity);
//                }
//                break;
//        }
//        return null;
//    }

}
