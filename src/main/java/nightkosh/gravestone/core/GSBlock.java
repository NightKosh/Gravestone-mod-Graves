package nightkosh.gravestone.core;

import nightkosh.gravestone.ModGraveStone;
import nightkosh.gravestone.block.BlockGSGraveStone;
import nightkosh.gravestone.item.itemblock.ItemBlockGSGraveStone;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBlock {

    public static final IBlockState DEFAULT_STONE_STATE = Blocks.stone.getDefaultState();
    public static final int DIORITE_META = Blocks.stone.getMetaFromState(DEFAULT_STONE_STATE.withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE_SMOOTH));
    public static final int ANDESITE_META = Blocks.stone.getMetaFromState(DEFAULT_STONE_STATE.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
    public static final int GRANITE_META = Blocks.stone.getMetaFromState(DEFAULT_STONE_STATE.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH));

    public static BlockGSGraveStone graveStone;

    public static void registration() {
        // nightkosh.gravestone
        graveStone = new BlockGSGraveStone();
        GameRegistry.registerBlock(graveStone, ItemBlockGSGraveStone.class, "GSGraveStone");

        ModGraveStone.proxy.registerBlocksModels();
    }
}
