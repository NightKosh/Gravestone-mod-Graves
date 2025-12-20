package nightkosh.gravestone.core;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.api.ModInfo;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod.EventBusSubscriber(modid = ModInfo.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GSTabs {

    public static CreativeModeTab GS_TAB;

    @SubscribeEvent
    public static void registerTabs(CreativeModeTabEvent.Register event) {
        GS_TAB = event.registerCreativeModeTab(
                ResourceLocation.fromNamespaceAndPath(ModInfo.ID, "sophisticated_wolves"),
                builder -> builder
                        .icon(() -> new ItemStack(GSBlocks.getGraveStone()))
                        .title(Component.translatable("itemGroup." + ModInfo.ID))
                .build()
        );

        GraveStoneAPI.gravesTab = GS_TAB;
    }

    @SubscribeEvent
    public static void buildContents(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == GS_TAB) {
            event.accept(new ItemStack(GSBlocks.getGraveStone()));
            event.accept(new ItemStack(GSBlocks.getGravePlate()));
            event.accept(new ItemStack(GSBlocks.getCross()));
        }
    }

}
