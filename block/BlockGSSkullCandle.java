package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import gravestone.config.GraveStoneConfig;
import gravestone.tileentity.TileEntityGSSkullCandle;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSSkullCandle extends BlockSkull {

    public BlockGSSkullCandle(int id) {
        super(id);
        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("skull_candle");
        this.setHardness(1.0F);
        this.setResistance(5F);
        this.setTextureName("skull");
        this.setCreativeTab(ModGraveStone.creativeTab);
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return -1;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing
     * the block.
     */
    @Override
    public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityGSSkullCandle();
    }

    /**
     * only called by clickMiddleMouseButton , and passed to
     * inventory.setCurrentItem (along with isCreative)
     */
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4) {
        return GraveStoneConfig.skullCandleID;
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    @Override
    public int getDamageValue(World par1World, int par2, int par3, int par4) {
        return 0;
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList();

        if ((metadata & 8) == 0) {
            ItemStack itemstack = new ItemStack(GraveStoneConfig.skullCandleID, 1, this.getDamageValue(world, x, y, z));
            TileEntityGSSkullCandle tileEntity = (TileEntityGSSkullCandle) world.getBlockTileEntity(x, y, z);

            if (tileEntity == null) {
                return drops;
            }

            drops.add(itemstack);
        }

        return drops;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return GraveStoneConfig.skullCandleID;
    }

    @Override
    public void makeWither(World par1World, int par2, int par3, int par4, TileEntitySkull par5TileEntitySkull) {
    }
    
    /**
     * From the specified side and block metadata retrieves the blocks texture.
     * Args: side, metadata
     */
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2) {
        return Block.slowSand.getBlockTextureFromSide(par1);
    }

    /**
     * Gets the icon name of the ItemBlock corresponding to this block. Used by
     * hoppers.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public String getItemIconName() {
        return this.getTextureName() + "_" + ItemSkull.field_94587_a[0];
    }
}
