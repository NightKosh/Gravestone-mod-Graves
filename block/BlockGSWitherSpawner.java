package GraveStone.block;

import GraveStone.ModGraveStone;
import GraveStone.tileentity.TileEntityGSWitherSpawner;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class BlockGSWitherSpawner extends BlockMobSpawner {

    private static Icon texture;

    public BlockGSWitherSpawner(int par1) {
        super(par1);

        this.setUnlocalizedName("Wither Spawner");
        this.setHardness(5.0F);
        this.setStepSound(soundMetalFootstep);
        this.disableStats();
        this.setCreativeTab(ModGraveStone.creativeTab);
    }

    public void registerIcons(IconRegister iconRegister) {
        texture = iconRegister.registerIcon("mobSpawner");
    }

    public Icon getBlockTextureFromSideAndMetadata(int direction, int meta) {
        return texture;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityGSWitherSpawner();
    }
}
