package nightkosh.gravestone.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSTabs {

    public static final DeferredRegister<CreativeModeTab> GS_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModInfo.ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ADVANCED_FISHING_TAB =
            GS_TAB.register("gravestone", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(GSBlocks.getGraveStone(EnumGraveMaterial.STONE)))
                    .title(Component.translatable("itemGroup." + ModInfo.ID))
                    .displayItems((parameters, output) -> {

                        output.accept(new ItemStack(GSItems.CHISEL.get()));

                        // graves
                        for (var mat : EnumGraveMaterial.values()) {
                            output.accept(new ItemStack(GSBlocks.getGraveStone(mat)));
                        }
                        for (var mat : EnumGraveMaterial.values()) {
                            output.accept(new ItemStack(GSBlocks.getCross(mat)));
                        }
                        for (var mat : EnumGraveMaterial.values()) {
                            output.accept(new ItemStack(GSBlocks.getGravePlate(mat)));
                        }
                        for (var mat : EnumGraveMaterial.values()) {
                            output.accept(new ItemStack(GSBlocks.getObelisk(mat)));
                        }
                        for (var mat : EnumGraveMaterial.values()) {
                            output.accept(new ItemStack(GSBlocks.getCelticCross(mat)));
                        }
                        for (var mat : EnumGraveMaterial.values()) {
                            output.accept(new ItemStack(GSBlocks.getPetGraveStone(mat)));
                        }
                        for (var mat : EnumGraveMaterial.values()) {
                            output.accept(new ItemStack(GSBlocks.getVillagerGraveStone(mat)));
                        }
                    })
                    .build()
            );

    public static void register(IEventBus modEventBus) {
        GS_TAB.register(modEventBus);
    }

}
