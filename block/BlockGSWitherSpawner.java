package net.minecraft.GraveStone.block;

import net.minecraft.GraveStone.mod_GraveStone;
import net.minecraft.GraveStone.tileentity.TileEntityGSWitherSpawner;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.client.renderer.texture.IconRegister;
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
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        world.setBlockTileEntity(x, y, z, new TileEntityGSWitherSpawner());
    }
}
