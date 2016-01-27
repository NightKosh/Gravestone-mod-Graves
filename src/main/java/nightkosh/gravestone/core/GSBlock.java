package nightkosh.gravestone.core;

import nightkosh.gravestone.ModGraveStone;
import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone.item.itemblock.ItemBlockGraveStone;
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

    public static BlockGraveStone graveStone;

    public static void registration() {
        // nightkosh.gravestone
        graveStone = new BlockGraveStone();
        GameRegistry.registerBlock(graveStone, ItemBlockGraveStone.class, "GSGraveStone");

        ModGraveStone.proxy.registerBlocksModels();
    }
}
