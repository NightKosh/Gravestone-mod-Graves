package gravestone.block;

import gravestone.block.enums.EnumSpawner;
import gravestone.core.GSBlock;
import gravestone.core.GSTabs;
import gravestone.particle.EntityGreenFlameFX;
import gravestone.tileentity.TileEntityGSSpawner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSSpawner extends BlockMobSpawner {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumSpawner.class);

    public static final List<EnumSpawner> MOB_SPAWNERS = new ArrayList<EnumSpawner>(Arrays.asList(
            EnumSpawner.SKELETON_SPAWNER,
            EnumSpawner.ZOMBIE_SPAWNER));
    public static final List<EnumSpawner> BOSS_SPAWNERS = new ArrayList<EnumSpawner>(Arrays.asList(
            EnumSpawner.WITHER_SPAWNER));

    public BlockGSSpawner() {
        super();
        this.setHardness(4);
        this.setLightLevel(0.45F);
        this.setStepSound(Block.soundTypeMetal);
        this.disableStats();
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setBlockBounds(-0.5F, 0, -0.5F, 1.5F, 0.05F, 1.5F);
        this.setHarvestLevel("pickaxe", 1);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing
     * the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityGSSpawner();
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        //TODO
//        return GraveStoneConfig.spawnerRenderID;
        return -1;
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

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
        return null;
    }

    /**
     * A randomly called display update to be able to add particles or other
     * items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random random) {
        double xPos = pos.getX() + 0.5F;
        double yPos = pos.getY() + 0.85;
        double zPos = pos.getZ() + 0.5F;
        double dRotation = Math.toRadians(72);
        double rotation = Math.toRadians(-36);
        double d = 1.07;
        double dx;
        double dz;

        for (int i = 0; i < 5; i++) {
            dx = -Math.sin(rotation) * d;
            dz = Math.cos(rotation) * d;
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, xPos + dx, yPos, zPos + dz, 0, 0, 0);
            EntityFX entityfx = new EntityGreenFlameFX(world, xPos + dx, yPos, zPos + dz, 0, 0, 0);
            Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
            rotation += dRotation;
        }
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess access, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        Random random = new Random();
        int metadata = ((Enum) state.getValue(VARIANT)).ordinal();
        ret.add(new ItemStack(getItemDropped(access.getBlockState(pos), random, fortune), quantityDropped(random), getItemMeta(metadata)));

        for (int i = 0; i < 5; i++) {
            if ((fortune > 0 && random.nextInt(100) < 5 * fortune) ||
                    random.nextInt(100) < 5 * fortune) {
                ret.add(getCustomItemsDropped(metadata));
            }
        }
        return ret;
    }

    public ItemStack getCustomItemsDropped(int meta) {
        switch (meta) {
            case 1:
                return new ItemStack(GSBlock.skullCandle, 1, 0);
            case 2:
                return new ItemStack(GSBlock.skullCandle, 1, 2);
            case 0:
            default:
                return new ItemStack(GSBlock.skullCandle, 1, 1);
        }
    }


    public int getItemMeta(int metadata) {
        return 15;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return Items.dye;
    }

    @Override
    public int quantityDropped(Random random) {
        return 3;
    }

    /**
     * Returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (EnumSpawner meta : MOB_SPAWNERS) {
            list.add(new ItemStack(item, 1, meta.ordinal()));
        }
        for (EnumSpawner meta : BOSS_SPAWNERS) {
            list.add(new ItemStack(item, 1, meta.ordinal()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumSpawner.getById((byte) meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumSpawner) state.getValue(VARIANT)).ordinal();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{VARIANT});
    }
}
