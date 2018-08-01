package nightkosh.gravestone.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import nightkosh.gravestone.block.BlockGraveStoneOld;
import nightkosh.gravestone.item.itemblock.ItemBlockGraveStoneOld;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
//@GameRegistry.ObjectHolder("gravestone")
public class GSBlockOld {
//TODO remove !!!!!!!!!!!!!!!!!!!!!!!

    public static final Block GRAVE_STONE = new BlockGraveStoneOld();
    public static final ItemBlock GRAVE_STONE_IB = new ItemBlockGraveStoneOld(GRAVE_STONE);

    //@Mod.EventBusSubscriber(modid = "gravestone")
    public static class RegistrationHandler {

        //@SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(GRAVE_STONE);
        }

        //@SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
            event.getRegistry().register(GRAVE_STONE_IB);
        }
    }
}
