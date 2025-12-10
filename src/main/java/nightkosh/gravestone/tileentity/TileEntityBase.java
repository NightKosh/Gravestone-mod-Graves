package nightkosh.gravestone.tileentity;

import net.minecraft.tileentity.TileEntity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityBase extends TileEntity {

    @Override
    public int getBlockMetadata() {//It should prevent some potential Null pointers in Minecraft
        if (this.hasWorld()) {
            try {
                return super.getBlockMetadata();
            } catch (NullPointerException e) {
                return 0;
            }
        }
        return super.getBlockMetadata();
    }

}
