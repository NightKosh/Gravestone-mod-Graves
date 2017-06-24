package nightkosh.gravestone.core;

import net.minecraft.item.ItemBlock;
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

    public static final BlockGraveStone GRAVE_STONE = new BlockGraveStone();
    public static final ItemBlock GRAVE_STONE_IB = new ItemBlockGraveStone(GRAVE_STONE);

    public static void registration() {
        GameRegistry.register(GRAVE_STONE);
        GameRegistry.register(GRAVE_STONE_IB);

        ModGraveStone.proxy.registerBlocksModels();
    }
}
