package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import gravestone.block.enums.EnumSkullCandle;
import gravestone.config.GraveStoneConfig;
import gravestone.tileentity.TileEntityGSSkullCandle;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSSkullCandle extends BlockContainer {

    public BlockGSSkullCandle(int id) {
        super(id, Material.circuits);
        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("Skull Candle");
        this.setHardness(0.5F);
        this.setResistance(5F);
        this.setLightValue(1);
        this.setTextureName("snow");
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return GraveStoneConfig.skullCandleRenderID;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing
     * the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityGSSkullCandle();
    }

    @Override
    public int damageDropped(int damage) {
        return damage;
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        world.setBlockMetadataWithNotify(x, y, z, stack.getItemDamage(), 2);

        TileEntityGSSkullCandle tileEntity = (TileEntityGSSkullCandle) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            float skullRotation = entity.rotationYaw - 180 - 22.5F;
            if (skullRotation < 0) {
                skullRotation = 360 + skullRotation;
            }
            tileEntity.setRotation((byte) MathHelper.ceiling_double_int(skullRotation * 8 / 360F));
        }
    }

    /**
     * Returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List list) {
        for (byte i = 0; i < EnumSkullCandle.values().length; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    /**
     * A randomly called display update to be able to add particles or other
     * items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {

        TileEntityGSSkullCandle tileEntity = (TileEntityGSSkullCandle) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            double xPos = x + 0.5F;
            double yPos = y + 0.85;
            double zPos = z + 0.5F;
            double rotation = Math.toRadians(tileEntity.getRotation() * 360 / 8F);
            double d = 0.07;
            double dx = -Math.sin(rotation) * d;
            double dz = Math.cos(rotation) * d;

            world.spawnParticle("smoke", xPos + dx, yPos, zPos + dz, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", xPos + dx, yPos, zPos + dz, 0.0D, 0.0D, 0.0D);
        }
    }
}
