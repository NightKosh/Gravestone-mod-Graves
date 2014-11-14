package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.block.enums.EnumPileOfBones;
import gravestone.config.GraveStoneConfig;
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
        super(Material.rock);
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

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        world.setBlockMetadataWithNotify(x, y, z, stack.getItemDamage(), 2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (byte i = 0; i < EnumPileOfBones.values().length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}
