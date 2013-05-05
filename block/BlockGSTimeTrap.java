package GraveStone.block;

import GraveStone.ModGraveStone;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class BlockGSTimeTrap extends Block {

    private static Icon texture;
    
    public BlockGSTimeTrap(int par1) {
        super(par1, Material.rock);

        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("Time Trap");
        this.setHardness(4.5F);
        this.setResistance(5F);
        this.setCreativeTab(ModGraveStone.creativeTab);
    }
    
    public void registerIcons(IconRegister iconRegister) {
        texture = iconRegister.registerIcon("netherBrick");
    }

    public Icon getBlockTextureFromSideAndMetadata(int direction, int meta) {
        return texture;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube() {
        return true;
    }

    public int quantityDropped(int par1) {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random random, int par3) {
        return Block.netherBrick.blockID;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    public boolean canSilkHarvest() {
        return true;
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    public void onEntityWalking(World world, int par2, int par3, int par4, Entity entity) {
        if (entity instanceof EntityPlayer) {
            long time = world.getWorldTime();
            if (time < 12000 || time > 22500) {
                world.setWorldTime(12000);
            } else if (time > 20000 && time < 22500) {
                world.setWorldTime(14000);
            }
        }
    }
}
