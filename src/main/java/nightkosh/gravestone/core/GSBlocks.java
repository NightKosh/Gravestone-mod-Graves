package nightkosh.gravestone.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone.block.ItemBlockGraveStone;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBlocks {

    public static final DeferredRegister<Block> BLOCKS_REGISTER =
            DeferredRegister.create(Registries.BLOCK, ModInfo.ID);

    public static final DeferredRegister<Item> ITEMS_REGISTER =
            DeferredRegister.create(Registries.ITEM, ModInfo.ID);

    public static final Map<EnumGraveMaterial, DeferredHolder<Block, BlockGraveStone>> GRAVE_STONES = new EnumMap<>(EnumGraveMaterial.class);
    public static final Map<EnumGraveMaterial, DeferredHolder<Block, BlockGraveStone>> GRAVE_PLATES = new EnumMap<>(EnumGraveMaterial.class);
    public static final Map<EnumGraveMaterial, DeferredHolder<Block, BlockGraveStone>> CROSSES = new EnumMap<>(EnumGraveMaterial.class);
    public static final Map<EnumGraveMaterial, DeferredHolder<Block, BlockGraveStone>> OBELISKS = new EnumMap<>(EnumGraveMaterial.class);
    public static final Map<EnumGraveMaterial, DeferredHolder<Block, BlockGraveStone>> CELTIC_CROSSES = new EnumMap<>(EnumGraveMaterial.class);
    public static final Map<EnumGraveMaterial, DeferredHolder<Block, BlockGraveStone>> PET_GRAVE_STONES = new EnumMap<>(EnumGraveMaterial.class);
    public static final Map<EnumGraveMaterial, DeferredHolder<Block, BlockGraveStone>> VILLAGER_GRAVE_STONES = new EnumMap<>(EnumGraveMaterial.class);

    public static final List<DeferredHolder<Block, BlockGraveStone>> GRAVE_LIST = new ArrayList<>();

    static {
        ResourceKey id = null;
        for (var mat : EnumGraveMaterial.values()) {
            var graveStone = registerBlock(EnumGraveType.GRAVE_STONE, mat);
            GRAVE_STONES.put(mat, graveStone);
            GRAVE_LIST.add(graveStone);

            var cross = registerBlock(EnumGraveType.CROSS, mat);
            CROSSES.put(mat, cross);
            GRAVE_LIST.add(cross);

            var obelisk = registerBlock(EnumGraveType.OBELISK, mat);
            OBELISKS.put(mat, obelisk);
            GRAVE_LIST.add(obelisk);

            var celticCross = registerBlock(EnumGraveType.CELTIC_CROSS, mat);
            CELTIC_CROSSES.put(mat, celticCross);
            GRAVE_LIST.add(celticCross);

            var gravePlate = registerBlock(EnumGraveType.GRAVE_PLATE, mat);
            GRAVE_PLATES.put(mat, gravePlate);
            GRAVE_LIST.add(gravePlate);

            var petGrave = registerBlock(EnumGraveType.PET_GRAVE_STONE, mat);
            PET_GRAVE_STONES.put(mat, petGrave);
            GRAVE_LIST.add(petGrave);

            var villagerGrave = registerBlock(EnumGraveType.VILLAGER_GRAVE_STONE, mat);
            VILLAGER_GRAVE_STONES.put(mat, villagerGrave);
            GRAVE_LIST.add(villagerGrave);
        }
    }

    private static <T extends Block> DeferredHolder<Block, BlockGraveStone> registerBlock(
            EnumGraveType gravetype, EnumGraveMaterial mat) {
        String name = gravetype.name().toLowerCase() + "_" + mat.name().toLowerCase();
        ResourceKey id = ResourceKey.create(Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, name));
        var block = BLOCKS_REGISTER.register(name, () -> new BlockGraveStone(gravetype, mat, id));
        ITEMS_REGISTER.register(name, () -> new ItemBlockGraveStone(block.get(), id));
        return block;
    }

    public static Block getGraveBlock(EnumGraveType graveType, EnumGraveMaterial material) {
        return switch (graveType) {
            case GRAVE_STONE -> getGraveStone(material);
            case GRAVE_PLATE -> getGravePlate(material);
            case CROSS -> getCross(material);
            case OBELISK -> getObelisk(material);
            case CELTIC_CROSS -> getCelticCross(material);
            case PET_GRAVE_STONE -> getPetGraveStone(material);
            case VILLAGER_GRAVE_STONE -> getVillagerGraveStone(material);
            case SWORD -> getGravePlate(material);//TODO
        };
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

    public static Block getObelisk(EnumGraveMaterial material) {
        return OBELISKS.get(material).get();
    }

    public static Block getCelticCross(EnumGraveMaterial material) {
        return CELTIC_CROSSES.get(material).get();
    }

    public static Block getPetGraveStone(EnumGraveMaterial material) {
        return PET_GRAVE_STONES.get(material).get();
    }

    public static Block getVillagerGraveStone(EnumGraveMaterial material) {
        return VILLAGER_GRAVE_STONES.get(material).get();
    }

}
