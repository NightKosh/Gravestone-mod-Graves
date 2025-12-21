package nightkosh.gravestone.core;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone.item.itemblock.ItemBlockGraveStone;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBlocks {

    public static final DeferredRegister<Block> BLOCKS_REGISTER =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ModInfo.ID);

    public static final DeferredRegister<Item> ITEMS_REGISTER =
            DeferredRegister.create(ForgeRegistries.ITEMS, ModInfo.ID);

    public static final Map<EnumGraveMaterial, RegistryObject<BlockGraveStone>> GRAVE_STONES = new EnumMap<>(EnumGraveMaterial.class);
    public static final Map<EnumGraveMaterial, RegistryObject<BlockGraveStone>> GRAVE_PLATES = new EnumMap<>(EnumGraveMaterial.class);
    public static final Map<EnumGraveMaterial, RegistryObject<BlockGraveStone>> CROSSES = new EnumMap<>(EnumGraveMaterial.class);

    public static final List<RegistryObject<BlockGraveStone>> GRAVE_LIST = new ArrayList<>();

    static {
        for (var mat : EnumGraveMaterial.values()) {
            var graveStone = registerBlock(
                    "grave_stone_" + mat.name().toLowerCase(),
                    () -> new BlockGraveStone(EnumGraveType.GRAVE_STONE, mat));
            GRAVE_STONES.put(mat, graveStone);
            GRAVE_LIST.add(graveStone);

            var cross = registerBlock(
                    "cross_" + mat.name().toLowerCase(),
                    () -> new BlockGraveStone(EnumGraveType.CROSS, mat));
            CROSSES.put(mat, cross);
            GRAVE_LIST.add(cross);

            var gravePlate = registerBlock(
                    "grave_plate_" + mat.name().toLowerCase(),
                    () -> new BlockGraveStone(EnumGraveType.GRAVE_PLATE, mat));
            GRAVE_PLATES.put(mat, gravePlate);
            GRAVE_LIST.add(gravePlate);
        }
    }

    private static <T extends Block> RegistryObject<T> registerBlock(
            String name, Supplier<T> supplier) {
        var block = BLOCKS_REGISTER.register(name, supplier);
        ITEMS_REGISTER.register(name, () -> new ItemBlockGraveStone((BlockGraveStone) block.get()));
        return block;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS_REGISTER.register(eventBus);
        ITEMS_REGISTER.register(eventBus);
    }

    public static Block getGraveStone(EnumGraveMaterial material) {
        return GRAVE_STONES.get(material).get();
    }

    public static Block getGravePlate(EnumGraveMaterial material) {
        return GRAVE_PLATES.get(material).get();
    }

    public static Block getCross(EnumGraveMaterial material) {
        return CROSSES.get(material).get();
    }

}
