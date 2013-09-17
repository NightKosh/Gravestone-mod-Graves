package gravestone.block;

import gravestone.ModGraveStone;
import gravestone.core.Resources;
import gravestone.tileentity.TileEntityGSWitherSpawner;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSWitherSpawner extends BlockMobSpawner {

    public BlockGSWitherSpawner(int par1) {
        super(par1);
        this.setUnlocalizedName("spawner.wither");
        this.setHardness(5.0F);
        this.setStepSound(soundMetalFootstep);
        this.disableStats();
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.setTextureName(Resources.WITHER_SPAWNER);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing
     * the block.
     */
    @Override
    public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityGSWitherSpawner();
    }
}
