package gravestone.block;

import gravestone.block.enums.EnumBoneBlock;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSTabs;
import gravestone.entity.monster.EntitySkullCrawler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSBoneBlock extends Block {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumBoneBlock.class);
//    @SideOnly(Side.CLIENT)
//    private IIcon skullIcon;

    public BlockGSBoneBlock() {
        super(Material.rock);
        this.setStepSound(Block.soundTypeStone);
        this.setUnlocalizedName("bone_block");
        this.setHardness(2F);
        this.setResistance(2F);
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setHarvestLevel("pickaxe", 0);
    }

//    @Override
//    public void registerBlockIcons(IIconRegister iconRegister) {
//        this.blockIcon = iconRegister.registerIcon(Resources.BONE_BLOCK);
//        this.skullIcon = iconRegister.registerIcon(Resources.SKULL_BONE_BLOCK);
//    }

    /**
     * From the specified side and block metadata retrieves the blocks texture.
     */
//    @Override
//    public IIcon getIcon(int side, int metadata) {
//        if (metadata == 1 || metadata == 3) {
//            return skullIcon;
//        } else {
//            return blockIcon;
//        }
//    }

    /**
     * Returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (byte meta = 0; meta < EnumBoneBlock.values().length; meta++) {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(IBlockState state) {
        int metadata = ((Enum) state.getValue(VARIANT)).ordinal();
        if (isSkullCrawlerBlock(state)) {
            metadata -= 2;
        }
        return metadata;
    }

    public boolean isSkullCrawlerBlock(IBlockState state) {
        int metadata = ((Enum) state.getValue(VARIANT)).ordinal();
        return metadata == 2 || metadata == 3;
    }

    /**
     * Called right before the block is destroyed by a player. Args: world, x, y, z, metaData
     */
    @Override
    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
        if (!world.isRemote && isSkullCrawlerBlock(state) && GraveStoneConfig.spawnSkullCrawlersAtBoneBlockDestruction) {
            EntitySkullCrawler skullCrawler = new EntitySkullCrawler(world);
            skullCrawler.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0, 0);
            world.spawnEntityInWorld(skullCrawler);
            skullCrawler.spawnExplosionParticle();
        }

        super.onBlockDestroyedByPlayer(world, pos, state);
    }
}
