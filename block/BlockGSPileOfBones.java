package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.block.enums.EnumPileOfBones;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSBlock;
import gravestone.core.GSTabs;
import gravestone.tileentity.TileEntityGSPileOfBones;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSPileOfBones extends BlockContainer {

    public BlockGSPileOfBones() {
        super(Material.circuits);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockName("pile of bones");
        this.setHardness(0.1F);
        this.setResistance(0);
        this.setBlockTextureName("snow");
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setBlockBounds(0.1F, 0, 0.1F, 0.9F, 0.2F, 0.9F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityGSPileOfBones();
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return GraveStoneConfig.pileOfBonesRenderID;
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Items.bone;
    }

    @Override
    public int quantityDropped(Random random) {
        return 4;
    }

    @Override
    public int damageDropped(int damage) {
        return damage;
    }

    protected ItemStack createStackedBlock(int meta) {
        return new ItemStack(GSBlock.pileOfBones, 1, meta);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        world.setBlockMetadataWithNotify(x, y, z, stack.getItemDamage(), 2);

        TileEntityGSPileOfBones te = (TileEntityGSPileOfBones) world.getTileEntity(x, y, z);
        if (te != null) {
            te.setDirection((byte) (MathHelper.floor_double((double) (entity.rotationYaw * 4F / 360F) + 0.5D) & 3));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (byte i = 0; i < EnumPileOfBones.values().length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return world.doesBlockHaveSolidTopSurface(world, x, y - 1, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!canPlaceBlockAt(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }
}
