package nightkosh.gravestone.core;

import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone.ModGraveStone;
import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone.item.itemblock.ItemBlockGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBlock {

    public static BlockGraveStone graveStone;

    public static void registration() {
        graveStone = new BlockGraveStone();
        GameRegistry.registerBlock(graveStone, ItemBlockGraveStone.class, "GSGraveStone");

        ModGraveStone.proxy.registerBlocksModels();
    }
}
