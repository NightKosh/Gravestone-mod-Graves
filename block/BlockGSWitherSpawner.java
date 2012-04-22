package GraveStone.block;

import GraveStone.mod_GraveStone;
import GraveStone.tileentity.TileEntityGSWitherSpawner;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockGSWitherSpawner extends BlockMobSpawner {

    private static Icon texture;

    public BlockGSWitherSpawner(int par1) {
        super(par1);

        this.setUnlocalizedName("Wither Spawner");
        this.setHardness(5.0F);
        this.setStepSound(soundMetalFootstep);
        this.disableStats();
        this.setCreativeTab(mod_GraveStone.creativeTab);
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
