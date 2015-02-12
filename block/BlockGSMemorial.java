package gravestone.block;

import gravestone.ModGraveStone;
import gravestone.block.enums.EnumHangedMobs;
import gravestone.block.enums.EnumMemorials;
import gravestone.core.GSTabs;
import gravestone.item.ItemGSCorpse;
import gravestone.item.corpse.VillagerCorpseHelper;
import gravestone.item.enums.EnumCorpse;
import gravestone.particle.EntityBigFlameFX;
import gravestone.tileentity.GSGraveStoneItems;
import gravestone.tileentity.TileEntityGSMemorial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSMemorial extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public static final byte[] TAB_MEMORIALS = {
            (byte) EnumMemorials.WOODEN_CROSS.ordinal(),
            (byte) EnumMemorials.SANDSTONE_CROSS.ordinal(),
            (byte) EnumMemorials.STONE_CROSS.ordinal(),
            (byte) EnumMemorials.IRON_CROSS.ordinal(),
            (byte) EnumMemorials.GOLDEN_CROSS.ordinal(),
            (byte) EnumMemorials.DIAMOND_CROSS.ordinal(),
            (byte) EnumMemorials.EMERALD_CROSS.ordinal(),
            (byte) EnumMemorials.LAPIS_CROSS.ordinal(),
            (byte) EnumMemorials.REDSTONE_CROSS.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_CROSS.ordinal(),
            (byte) EnumMemorials.QUARTZ_CROSS.ordinal(),
            (byte) EnumMemorials.ICE_CROSS.ordinal(),
            // obelisks
//            (byte) EnumMemorials.WOODEN_OBELISK.ordinal(),
//            (byte) EnumMemorials.SANDSTONE_OBELISK.ordinal(),
//            (byte) EnumMemorials.STONE_OBELISK.ordinal(),
//            (byte) EnumMemorials.IRON_OBELISK.ordinal(),
//            (byte) EnumMemorials.GOLDEN_OBELISK.ordinal(),
//            (byte) EnumMemorials.DIAMOND_OBELISK.ordinal(),
//            (byte) EnumMemorials.EMERALD_OBELISK.ordinal(),
//            (byte) EnumMemorials.LAPIS_OBELISK.ordinal(),
//            (byte) EnumMemorials.REDSTONE_OBELISK.ordinal(),
//            (byte) EnumMemorials.OBSIDIAN_OBELISK.ordinal(),
            (byte) EnumMemorials.QUARTZ_OBELISK.ordinal(),
//            (byte) EnumMemorials.ICE_OBELISK.ordinal(),
            // ANGEL memorials
            (byte) EnumMemorials.WOODEN_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.SANDSTONE_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.IRON_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.GOLDEN_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.DIAMOND_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.EMERALD_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.LAPIS_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.REDSTONE_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.QUARTZ_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.ICE_STEVE_STATUE.ordinal(),
            // villager memorials
            (byte) EnumMemorials.WOODEN_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.SANDSTONE_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.IRON_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.GOLDEN_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.DIAMOND_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.EMERALD_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.LAPIS_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.REDSTONE_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.QUARTZ_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.ICE_VILLAGER_STATUE.ordinal(),
            // angel memorials
            (byte) EnumMemorials.WOODEN_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.SANDSTONE_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.IRON_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.GOLDEN_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.DIAMOND_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.EMERALD_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.LAPIS_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.REDSTONE_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.QUARTZ_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.ICE_ANGEL_STATUE.ordinal(),
            // dog memorials
            (byte) EnumMemorials.WOODEN_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.SANDSTONE_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.IRON_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.GOLDEN_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.DIAMOND_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.EMERALD_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.LAPIS_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.REDSTONE_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.QUARTZ_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.ICE_DOG_STATUE.ordinal(),
            // cat memorials
            (byte) EnumMemorials.WOODEN_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.SANDSTONE_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.IRON_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.GOLDEN_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.DIAMOND_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.EMERALD_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.LAPIS_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.REDSTONE_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.QUARTZ_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.ICE_CAT_STATUE.ordinal(),
            // creeper memorials
            (byte) EnumMemorials.WOODEN_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.SANDSTONE_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.IRON_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.GOLDEN_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.DIAMOND_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.EMERALD_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.LAPIS_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.REDSTONE_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.QUARTZ_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.ICE_CREEPER_STATUE.ordinal()//,
            // gibbets
//            (byte) EnumMemorials.GIBBET.ordinal(),
//            // stocks
//            (byte) EnumMemorials.STOCKS.ordinal()
    };
    public static final EnumMemorials[] WOODEN_GENERATED_MEMORIALS = {
            EnumMemorials.WOODEN_CROSS,
            EnumMemorials.QUARTZ_OBELISK,
            EnumMemorials.WOODEN_STEVE_STATUE,
            EnumMemorials.WOODEN_VILLAGER_STATUE,
            EnumMemorials.WOODEN_ANGEL_STATUE,
            EnumMemorials.WOODEN_DOG_STATUE,
            EnumMemorials.WOODEN_CAT_STATUE
    };
    public static final EnumMemorials[] SANDSTONE_GENERATED_MEMORIALS = {
            EnumMemorials.SANDSTONE_CROSS,
            EnumMemorials.QUARTZ_OBELISK,
            EnumMemorials.SANDSTONE_STEVE_STATUE,
            EnumMemorials.SANDSTONE_VILLAGER_STATUE,
            EnumMemorials.SANDSTONE_ANGEL_STATUE,
            EnumMemorials.SANDSTONE_DOG_STATUE,
            EnumMemorials.SANDSTONE_CAT_STATUE
    };
    public static final EnumMemorials[] STONE_GENERATED_MEMORIALS = {
            EnumMemorials.STONE_CROSS,
            EnumMemorials.QUARTZ_OBELISK,
            EnumMemorials.STONE_STEVE_STATUE,
            EnumMemorials.STONE_VILLAGER_STATUE,
            EnumMemorials.STONE_ANGEL_STATUE,
            EnumMemorials.STONE_DOG_STATUE,
            EnumMemorials.STONE_CAT_STATUE
    };
    public static final EnumMemorials[] QUARTZ_GENERATED_MEMORIALS = {
            EnumMemorials.QUARTZ_CROSS,
            EnumMemorials.QUARTZ_OBELISK,
            EnumMemorials.QUARTZ_STEVE_STATUE,
            EnumMemorials.QUARTZ_VILLAGER_STATUE,
            EnumMemorials.QUARTZ_ANGEL_STATUE,
            EnumMemorials.QUARTZ_DOG_STATUE,
            EnumMemorials.QUARTZ_CAT_STATUE
    };
    public static final EnumMemorials[] ICE_GENERATED_MEMORIALS = {
            EnumMemorials.ICE_CROSS,
            EnumMemorials.QUARTZ_OBELISK,
            EnumMemorials.ICE_STEVE_STATUE,
            EnumMemorials.ICE_VILLAGER_STATUE,
            EnumMemorials.ICE_ANGEL_STATUE,
            EnumMemorials.ICE_DOG_STATUE,
            EnumMemorials.ICE_CAT_STATUE
    };
    public static final EnumMemorials[] WOODEN_DOG_MEMORIALS = {EnumMemorials.WOODEN_DOG_STATUE};
    public static final EnumMemorials[] SANDSTONE_DOG_MEMORIALS = {EnumMemorials.SANDSTONE_DOG_STATUE};
    public static final EnumMemorials[] STONE_DOG_MEMORIALS = {EnumMemorials.STONE_DOG_STATUE};
    public static final EnumMemorials[] QUARTZ_DOG_MEMORIALS = {EnumMemorials.QUARTZ_DOG_STATUE};
    public static final EnumMemorials[] ICE_DOG_MEMORIALS = {EnumMemorials.ICE_DOG_STATUE};

    public static final EnumMemorials[] WOODEN_CAT_MEMORIALS = {EnumMemorials.WOODEN_CAT_STATUE};
    public static final EnumMemorials[] SANDSTONE_CAT_MEMORIALS = {EnumMemorials.SANDSTONE_CAT_STATUE};
    public static final EnumMemorials[] STONE_CAT_MEMORIALS = {EnumMemorials.STONE_CAT_STATUE};
    public static final EnumMemorials[] QUARTZ_CAT_MEMORIALS = {EnumMemorials.QUARTZ_CAT_STATUE};
    public static final EnumMemorials[] ICE_CAT_MEMORIALS = {EnumMemorials.ICE_CAT_STATUE};

    public static final EnumMemorials[] WOODEN_CREEPER_MEMORIALS = {EnumMemorials.WOODEN_CREEPER_STATUE};
    public static final EnumMemorials[] SANDSTONE_CREEPER_MEMORIALS = {EnumMemorials.SANDSTONE_CREEPER_STATUE};
    public static final EnumMemorials[] STONE_CREEPER_MEMORIALS = {EnumMemorials.STONE_CREEPER_STATUE};
    public static final EnumMemorials[] QUARTZ_CREEPER_MEMORIALS = {EnumMemorials.QUARTZ_CREEPER_STATUE};
    public static final EnumMemorials[] ICE_CREEPER_MEMORIALS = {EnumMemorials.ICE_CREEPER_STATUE};

    public static final EnumMemorials[] WOODEN_STATUES_MEMORIALS = {
            EnumMemorials.WOODEN_STEVE_STATUE,
            EnumMemorials.WOODEN_VILLAGER_STATUE,
            EnumMemorials.WOODEN_ANGEL_STATUE
    };
    public static final EnumMemorials[] SANDSTONE_STATUES_MEMORIALS = {
            EnumMemorials.SANDSTONE_STEVE_STATUE,
            EnumMemorials.SANDSTONE_VILLAGER_STATUE,
            EnumMemorials.SANDSTONE_ANGEL_STATUE
    };
    public static final EnumMemorials[] STONE_STATUES_MEMORIALS = {
            EnumMemorials.STONE_STEVE_STATUE,
            EnumMemorials.STONE_VILLAGER_STATUE,
            EnumMemorials.STONE_ANGEL_STATUE
    };
    public static final EnumMemorials[] QUARTZ_STATUES_MEMORIALS = {
            EnumMemorials.QUARTZ_STEVE_STATUE,
            EnumMemorials.QUARTZ_VILLAGER_STATUE,
            EnumMemorials.QUARTZ_ANGEL_STATUE
    };
    public static final EnumMemorials[] ICE_STATUES_MEMORIALS = {
            EnumMemorials.ICE_STEVE_STATUE,
            EnumMemorials.ICE_VILLAGER_STATUE,
            EnumMemorials.ICE_ANGEL_STATUE
    };

    public static final EnumMemorials[] TORTURE_MEMORIALS = {
            EnumMemorials.GIBBET,
            EnumMemorials.STOCKS,
            EnumMemorials.BURNING_STAKE
    };

    public BlockGSMemorial() {
        super(Material.rock);
        this.isBlockContainer = true;
        this.setStepSound(Block.soundTypeStone);
        this.setUnlocalizedName("Memorial");
        this.setHardness(1);
        this.setResistance(5);
        this.setCreativeTab(GSTabs.memorialsTab);
    }

    /**
     * Return random memorial type memorialTypetype - type of memorial 0 - all
     * memorials(20% for pets graves), except creeper 1 - only pets memorials 2
     * - only dogs memorials 3 - only cats memorials 4 - creeper memorials 5 -
     * only statues memorials(steve, villager, angel)
     */
    public static byte getMemorialType(World world, BlockPos pos, Random random, int memorialType) {
        switch (memorialType) {
            default:
            case 0:
                return getRandomMemorial(getGeneratedMemorialsTypes(world, pos), random);
            case 1:
                return getRandomMemorial(getPetsMemorialsTypes(world, pos), random);
            case 2:
                return getRandomMemorial(getDogsMemorialsTypes(world, pos), random);
            case 3:
                return getRandomMemorial(getCatsMemorialsTypes(world, pos), random);
            case 4:
                return getRandomMemorial(getCreeperMemorialsTypes(world, pos), random);
            case 5:
                return getRandomMemorial(getStatuesMemorialsTypes(world, pos), random);
        }
    }

    public static byte getRandomMemorial(List<EnumMemorials> memorialTypes, Random rand) {
        if (memorialTypes.size() > 0) {
            return (byte) memorialTypes.get(rand.nextInt(memorialTypes.size())).ordinal();
        } else {
            return 0;
        }
    }

    public static ArrayList<EnumMemorials> getGeneratedMemorialsTypes(World world, BlockPos pos) {
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumMemorials> memorialTypes = new ArrayList<EnumMemorials>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            memorialTypes.addAll(Arrays.asList(SANDSTONE_GENERATED_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            //TODO memorialTypes.addAll(Arrays.asList(MOSSY_GENERATED_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            memorialTypes.addAll(Arrays.asList(STONE_GENERATED_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            memorialTypes.addAll(Arrays.asList(WOODEN_GENERATED_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            memorialTypes.addAll(Arrays.asList(ICE_GENERATED_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            memorialTypes.addAll(Arrays.asList(QUARTZ_GENERATED_MEMORIALS));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (memorialTypes.isEmpty()) {
            memorialTypes.addAll(Arrays.asList(STONE_GENERATED_MEMORIALS));
        }

        return memorialTypes;
    }

    public static ArrayList<EnumMemorials> getPetsMemorialsTypes(World world, BlockPos pos) {
        ArrayList<EnumMemorials> memorialTypes = new ArrayList<EnumMemorials>();
        memorialTypes.addAll(getDogsMemorialsTypes(world, pos));
        memorialTypes.addAll(getCatsMemorialsTypes(world, pos));

        return memorialTypes;
    }

    public static ArrayList<EnumMemorials> getDogsMemorialsTypes(World world, BlockPos pos) {
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumMemorials> memorialTypes = new ArrayList<EnumMemorials>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            memorialTypes.addAll(Arrays.asList(SANDSTONE_DOG_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            //TODO memorialTypes.addAll(Arrays.asList(MOSSY_DOG_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            memorialTypes.addAll(Arrays.asList(STONE_DOG_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            memorialTypes.addAll(Arrays.asList(WOODEN_DOG_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            memorialTypes.addAll(Arrays.asList(ICE_DOG_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            memorialTypes.addAll(Arrays.asList(QUARTZ_DOG_MEMORIALS));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (memorialTypes.isEmpty()) {
            memorialTypes.addAll(Arrays.asList(STONE_DOG_MEMORIALS));
        }

        return memorialTypes;
    }

    public static ArrayList<EnumMemorials> getCatsMemorialsTypes(World world, BlockPos pos) {
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumMemorials> memorialTypes = new ArrayList<EnumMemorials>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            memorialTypes.addAll(Arrays.asList(SANDSTONE_CAT_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            //TODO memorialTypes.addAll(Arrays.asList(MOSSY_CAT_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            memorialTypes.addAll(Arrays.asList(STONE_CAT_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            memorialTypes.addAll(Arrays.asList(WOODEN_CAT_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            memorialTypes.addAll(Arrays.asList(ICE_CAT_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            memorialTypes.addAll(Arrays.asList(QUARTZ_CAT_MEMORIALS));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (memorialTypes.isEmpty()) {
            memorialTypes.addAll(Arrays.asList(STONE_CAT_MEMORIALS));
        }

        return memorialTypes;
    }

    public static ArrayList<EnumMemorials> getCreeperMemorialsTypes(World world, BlockPos pos) {
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumMemorials> memorialTypes = new ArrayList<EnumMemorials>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            memorialTypes.addAll(Arrays.asList(SANDSTONE_CREEPER_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            //TODO memorialTypes.addAll(Arrays.asList(MOSSY_CREEPER_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            memorialTypes.addAll(Arrays.asList(STONE_CREEPER_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            memorialTypes.addAll(Arrays.asList(WOODEN_CREEPER_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            memorialTypes.addAll(Arrays.asList(ICE_CREEPER_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            memorialTypes.addAll(Arrays.asList(QUARTZ_CREEPER_MEMORIALS));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (memorialTypes.isEmpty()) {
            memorialTypes.addAll(Arrays.asList(STONE_CREEPER_MEMORIALS));
        }

        return memorialTypes;
    }

    public static ArrayList<EnumMemorials> getStatuesMemorialsTypes(World world, BlockPos pos) {
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumMemorials> memorialTypes = new ArrayList<EnumMemorials>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            memorialTypes.addAll(Arrays.asList(SANDSTONE_STATUES_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            //TODO memorialTypes.addAll(Arrays.asList(MOSSY_STATUES_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            memorialTypes.addAll(Arrays.asList(STONE_STATUES_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            memorialTypes.addAll(Arrays.asList(WOODEN_STATUES_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            memorialTypes.addAll(Arrays.asList(ICE_STATUES_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            memorialTypes.addAll(Arrays.asList(QUARTZ_STATUES_MEMORIALS));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (memorialTypes.isEmpty()) {
            memorialTypes.addAll(Arrays.asList(STONE_STATUES_MEMORIALS));
        }

        return memorialTypes;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack itemStack) {
        EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double((double) (player.rotationYaw * 4 / 360F) + 0.5D) & 3).getOpposite();
        state = state.withProperty(FACING, enumfacing);
        world.setBlockState(pos, state, 2);

        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getTileEntity(pos);

        if (tileEntity != null) {
            if (itemStack.hasTagCompound()) {
                NBTTagCompound nbt = itemStack.getTagCompound();
                if (nbt.hasKey("isLocalized") && nbt.getBoolean("isLocalized")) {
                    tileEntity.getDeathTextComponent().setLocalized();

                    if (nbt.hasKey("name") && nbt.hasKey("KillerName")) {
                        tileEntity.getDeathTextComponent().setName(nbt.getString("name"));
                        tileEntity.getDeathTextComponent().setKillerName(nbt.getString("KillerName"));
                    }
                }

                if (nbt.hasKey("DeathText")) {
                    tileEntity.getDeathTextComponent().setDeathText(nbt.getString("DeathText"));
                }

                tileEntity.setGraveType(nbt.getByte("GraveType"));
                tileEntity.setMossy(nbt.getBoolean("Mossy"));

                tileEntity.setHangedMob(EnumHangedMobs.getById(nbt.getByte("HangedMob")));
                tileEntity.setHangedVillagerProfession(nbt.getInteger("HangedVillagerProfession"));
            }
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
        EnumFacing facing = (EnumFacing) access.getBlockState(pos).getValue(FACING);
        EnumMemorials memorialType;
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) access.getTileEntity(pos);

        if (tileEntity != null) {
            memorialType = tileEntity.getMemorialType();
        } else {
            memorialType = EnumMemorials.STONE_CROSS;
        }

        switch (memorialType) {
            case WOODEN_CROSS:
            case SANDSTONE_CROSS:
            case STONE_CROSS:
            case IRON_CROSS:
            case GOLDEN_CROSS:
            case DIAMOND_CROSS:
            case EMERALD_CROSS:
            case LAPIS_CROSS:
            case REDSTONE_CROSS:
            case OBSIDIAN_CROSS:
            case QUARTZ_CROSS:
            case ICE_CROSS:
            case WOODEN_OBELISK:
            case SANDSTONE_OBELISK:
            case STONE_OBELISK:
            case IRON_OBELISK:
            case GOLDEN_OBELISK:
            case DIAMOND_OBELISK:
            case EMERALD_OBELISK:
            case LAPIS_OBELISK:
            case REDSTONE_OBELISK:
            case OBSIDIAN_OBELISK:
            case QUARTZ_OBELISK:
            case ICE_OBELISK:
                this.setBlockBounds(-1, 0, -1, 2, 5, 2);
                break;
            case WOODEN_STEVE_STATUE:
            case SANDSTONE_STEVE_STATUE:
            case STONE_STEVE_STATUE:
            case IRON_STEVE_STATUE:
            case GOLDEN_STEVE_STATUE:
            case DIAMOND_STEVE_STATUE:
            case EMERALD_STEVE_STATUE:
            case LAPIS_STEVE_STATUE:
            case REDSTONE_STEVE_STATUE:
            case OBSIDIAN_STEVE_STATUE:
            case QUARTZ_STEVE_STATUE:
            case ICE_STEVE_STATUE:
                // villager
            case WOODEN_VILLAGER_STATUE:
            case SANDSTONE_VILLAGER_STATUE:
            case STONE_VILLAGER_STATUE:
            case IRON_VILLAGER_STATUE:
            case GOLDEN_VILLAGER_STATUE:
            case DIAMOND_VILLAGER_STATUE:
            case EMERALD_VILLAGER_STATUE:
            case LAPIS_VILLAGER_STATUE:
            case REDSTONE_VILLAGER_STATUE:
            case OBSIDIAN_VILLAGER_STATUE:
            case QUARTZ_VILLAGER_STATUE:
            case ICE_VILLAGER_STATUE:
                //angel
            case WOODEN_ANGEL_STATUE:
            case SANDSTONE_ANGEL_STATUE:
            case STONE_ANGEL_STATUE:
            case IRON_ANGEL_STATUE:
            case GOLDEN_ANGEL_STATUE:
            case DIAMOND_ANGEL_STATUE:
            case EMERALD_ANGEL_STATUE:
            case LAPIS_ANGEL_STATUE:
            case REDSTONE_ANGEL_STATUE:
            case OBSIDIAN_ANGEL_STATUE:
            case QUARTZ_ANGEL_STATUE:
            case ICE_ANGEL_STATUE:
                this.setBlockBounds(0.125F, 0, 0.125F, 0.875F, 3F, 0.875F);
                break;
            case WOODEN_DOG_STATUE:
            case WOODEN_CAT_STATUE:
            case SANDSTONE_DOG_STATUE:
            case SANDSTONE_CAT_STATUE:
            case STONE_DOG_STATUE:
            case STONE_CAT_STATUE:
            case IRON_DOG_STATUE:
            case IRON_CAT_STATUE:
            case GOLDEN_DOG_STATUE:
            case GOLDEN_CAT_STATUE:
            case DIAMOND_DOG_STATUE:
            case DIAMOND_CAT_STATUE:
            case EMERALD_DOG_STATUE:
            case EMERALD_CAT_STATUE:
            case LAPIS_DOG_STATUE:
            case LAPIS_CAT_STATUE:
            case REDSTONE_DOG_STATUE:
            case REDSTONE_CAT_STATUE:
            case OBSIDIAN_DOG_STATUE:
            case OBSIDIAN_CAT_STATUE:
            case QUARTZ_DOG_STATUE:
            case QUARTZ_CAT_STATUE:
            case ICE_DOG_STATUE:
            case ICE_CAT_STATUE:
                this.setBlockBounds(0.125F, 0, 0.125F, 0.875F, 2, 0.875F);
                break;
            case WOODEN_CREEPER_STATUE:
            case SANDSTONE_CREEPER_STATUE:
            case STONE_CREEPER_STATUE:
            case IRON_CREEPER_STATUE:
            case GOLDEN_CREEPER_STATUE:
            case DIAMOND_CREEPER_STATUE:
            case EMERALD_CREEPER_STATUE:
            case LAPIS_CREEPER_STATUE:
            case REDSTONE_CREEPER_STATUE:
            case OBSIDIAN_CREEPER_STATUE:
            case QUARTZ_CREEPER_STATUE:
            case ICE_CREEPER_STATUE:
                this.setBlockBounds(0.125F, 0, 0.125F, 0.875F, 2.5F, 0.875F);
                break;
            case GIBBET:
            case BURNING_STAKE:
                this.setBlockBounds(0, 0, 0, 1, 2.5F, 1);
                break;
            case STOCKS:
                switch (facing) {
                    case SOUTH:
                    case NORTH:
                        this.setBlockBounds(-0.5F, 0, 0, 1.5F, 2, 1);
                        break;
                    case EAST:
                    case WEST:
                        this.setBlockBounds(0, 0, -0.5F, 1, 2, 1.5F);
                        break;
                }
                break;
        }
    }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0, 0, 0, 1, 1, 2);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    //TODO
//    @Override
//    public int getRenderType() {
//        return GraveStoneConfig.memorialRenderID;
//    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntityGSMemorial te = (TileEntityGSMemorial) world.getTileEntity(pos);

        if (te != null) {
            ItemStack item = player.inventory.getCurrentItem();
            if (item != null) {
                if (te.getMemorialType().equals(EnumMemorials.GIBBET) || te.getMemorialType().equals(EnumMemorials.STOCKS) || te.getMemorialType().equals(EnumMemorials.BURNING_STAKE)) {
                    if (item.getItem() instanceof ItemGSCorpse && EnumCorpse.getById((byte) item.getItemDamage()).equals(EnumCorpse.VILLAGER) && te.getHangedMob() == EnumHangedMobs.NONE) {
                        te.setHangedMob(EnumHangedMobs.VILLAGER);
                        te.setHangedVillagerProfession(VillagerCorpseHelper.getVillagerType(item.getTagCompound()));
                        item.stackSize--;
                        return true;
                    }
                } else {
                    if (te.isMossy()) {
                        if (item.getItem() instanceof ItemShears) {
                            if (!world.isRemote) {
                                GSGraveStoneItems.dropItem(new ItemStack(Blocks.vine, 1), world, pos);
                            }
                            te.setMossy(false);
                            return false;
                        }
                    } else {
                        if (Block.getBlockFromItem(item.getItem()) instanceof BlockVine) {
                            te.setMossy(true);
                            player.inventory.getCurrentItem().stackSize--;
                            return true;
                        }
                    }
                }
            }

            if (world.isRemote) {
                String name;
                String killerName;
                String deathText = te.getDeathTextComponent().getDeathText();

                if (deathText.length() != 0) {
                    if (te.getDeathTextComponent().isLocalized()) {
                        name = ModGraveStone.proxy.getLocalizedEntityName(te.getDeathTextComponent().getName());
                        if (name.length() != 0) {
                            killerName = ModGraveStone.proxy.getLocalizedEntityName(te.getDeathTextComponent().getKillerName());

                            if (killerName.length() == 0) {
                                player.addChatComponentMessage(new ChatComponentTranslation(deathText, new Object[]{name}));
                            } else {
                                player.addChatComponentMessage(new ChatComponentTranslation(deathText, new Object[]{name, killerName}));
                            }
                            return false;
                        }
                    }
                    player.addChatComponentMessage(new ChatComponentTranslation(deathText));
                }
            }
        }

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityGSMemorial(world);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (byte i = 0; i < TAB_MEMORIALS.length; i++) {
            list.add(getMemorialItemForCreativeInventory(item, TAB_MEMORIALS[i]));
        }

        // gibbets
        for (byte mobType = 0; mobType < EnumHangedMobs.values().length; mobType++) {
            ItemStack stack = getMemorialItemForCreativeInventory(item, (byte) EnumMemorials.GIBBET.ordinal());
            stack.getTagCompound().setByte("HangedMob", mobType);
            switch (EnumHangedMobs.values()[mobType]) {
                case VILLAGER:
                    ItemStack villagerStack;
                    for (byte villagerProfession = 0; villagerProfession <= 4; villagerProfession++) {
                        villagerStack = stack.copy();
                        villagerStack.getTagCompound().setInteger("HangedVillagerProfession", villagerProfession);
                        list.add(villagerStack);
                    }

                    Collection<Integer> villagerIds = VillagerRegistry.getRegisteredVillagers();
                    Iterator<Integer> it = villagerIds.iterator();
                    while (it.hasNext()) {
                        villagerStack = stack.copy();
                        villagerStack.getTagCompound().setInteger("HangedVillagerProfession", it.next());
                        list.add(villagerStack);
                    }
                    break;
                default:
                    list.add(stack);
            }
        }

        // stocks
        for (byte mobType = 0; mobType < EnumHangedMobs.values().length; mobType++) {
            ItemStack stack = getMemorialItemForCreativeInventory(item, (byte) EnumMemorials.STOCKS.ordinal());
            stack.getTagCompound().setByte("HangedMob", mobType);
            switch (EnumHangedMobs.values()[mobType]) {
                case VILLAGER:
                    ItemStack villagerStack;
                    for (byte villagerProfession = 0; villagerProfession <= 4; villagerProfession++) {
                        villagerStack = stack.copy();
                        villagerStack.getTagCompound().setInteger("HangedVillagerProfession", villagerProfession);
                        list.add(villagerStack);
                    }

                    Collection<Integer> villagerIds = VillagerRegistry.getRegisteredVillagers();
                    Iterator<Integer> it = villagerIds.iterator();
                    while (it.hasNext()) {
                        villagerStack = stack.copy();
                        villagerStack.getTagCompound().setInteger("HangedVillagerProfession", it.next());
                        list.add(villagerStack);
                    }
                    break;
                default:
                    list.add(stack);
            }
        }
        // burning stake
        for (byte mobType = 0; mobType < EnumHangedMobs.values().length; mobType++) {
            ItemStack stack = getMemorialItemForCreativeInventory(item, (byte) EnumMemorials.BURNING_STAKE.ordinal());
            stack.getTagCompound().setByte("HangedMob", mobType);
            switch (EnumHangedMobs.values()[mobType]) {
                case VILLAGER:
                    ItemStack villagerStack;
                    for (byte villagerProfession = 0; villagerProfession <= 4; villagerProfession++) {
                        villagerStack = stack.copy();
                        villagerStack.getTagCompound().setInteger("HangedVillagerProfession", villagerProfession);
                        list.add(villagerStack);
                    }

                    Collection<Integer> villagerIds = VillagerRegistry.getRegisteredVillagers();
                    Iterator<Integer> it = villagerIds.iterator();
                    while (it.hasNext()) {
                        villagerStack = stack.copy();
                        villagerStack.getTagCompound().setInteger("HangedVillagerProfession", it.next());
                        list.add(villagerStack);
                    }
                    break;
                default:
                    list.add(stack);
            }
        }
    }

    private static ItemStack getMemorialItemForCreativeInventory(Item item, byte graveType) {
        ItemStack stack = new ItemStack(item, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte("GraveType", graveType);
        stack.setTagCompound(nbt);
        return stack;
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        player.addExhaustion(0.025F);

        ItemStack itemStack;
        if (EnchantmentHelper.getSilkTouchModifier(player)) {
            itemStack = getBlockItemStack(world, pos);
        } else {
            itemStack = getBlockItemStackWithoutInfo(world, pos);
        }

        if (itemStack != null) {
            GSGraveStoneItems.dropItem(itemStack, world, pos);
        }
    }

    private ItemStack getBlockItemStack(World world, BlockPos pos) {
        ItemStack itemStack = this.createStackedBlock(this.getDefaultState());
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getTileEntity(pos);

        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            if (tileEntity.getDeathTextComponent().isLocalized()) {
                nbt.setBoolean("isLocalized", true);
                nbt.setString("name", tileEntity.getDeathTextComponent().getName());
                nbt.setString("KillerName", tileEntity.getDeathTextComponent().getKillerName());
            }
            nbt.setString("DeathText", tileEntity.getDeathTextComponent().getDeathText());
            nbt.setByte("GraveType", tileEntity.getGraveTypeNum());

            nbt.setBoolean("Enchanted", tileEntity.isEnchanted());

            nbt.setBoolean("Mossy", tileEntity.isMossy());

            nbt.setByte("HangedMob", (byte) tileEntity.getHangedMob().ordinal());
            nbt.setInteger("HangedVillagerProfession", tileEntity.getHangedVillagerProfession());

            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }

    private ItemStack getBlockItemStackWithoutInfo(World world, BlockPos pos) {
        ItemStack itemStack = this.createStackedBlock(this.getDefaultState());
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getTileEntity(pos);

        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", tileEntity.getGraveTypeNum());
            nbt.setBoolean("Mossy", tileEntity.isMossy());
            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it.
     */
    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
    }

    /*
     * Drop sword as item
     */
    public void dropCreeperMemorial(World world, BlockPos pos) {
        byte memorialType = BlockGSMemorial.getMemorialType(world, pos, new Random(), 4);
        ItemStack itemStack = new ItemStack(this);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte("GraveType", memorialType);
        itemStack.setTagCompound(nbt);
        GSGraveStoneItems.dropItem(itemStack, world, pos);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
        ItemStack itemStack = this.createStackedBlock(this.getDefaultState());
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getTileEntity(pos);

        if (tileEntity != null) {
            if (itemStack != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte("GraveType", tileEntity.getGraveTypeNum());

                nbt.setByte("HangedMob", (byte) tileEntity.getHangedMob().ordinal());
                nbt.setInteger("HangedVillagerProfession", tileEntity.getHangedVillagerProfession());

                itemStack.setTagCompound(nbt);
            }
        }
        return itemStack;
    }

    @Override
    public int getLightValue(IBlockAccess access, BlockPos pos) {
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) access.getTileEntity(pos);

        if (tileEntity != null && tileEntity.getMemorialType() == EnumMemorials.BURNING_STAKE && tileEntity.getHangedMob() != EnumHangedMobs.NONE) {
            return 15;
        } else {
            return super.getLightValue(access, pos);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random random) {
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getTileEntity(pos);
        if (tileEntity != null && tileEntity.getMemorialType() == EnumMemorials.BURNING_STAKE && tileEntity.getHangedMob() != EnumHangedMobs.NONE) {
            double xPos, zPos, yPos;

            yPos = pos.getY() + 0.25;
            for (int angle = 0; angle < 20; angle++) {
                xPos = pos.getX() + 0.5 + Math.sin(angle * 0.2792) * 0.75;
                zPos = pos.getZ() + 0.5 + Math.cos(angle * 0.2792) * 0.75;

                EntityFX entityfx = new EntityBigFlameFX(world, xPos, yPos, zPos, 0, 0, 0);
                Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
            }

            yPos += 0.25;
            for (int angle = 0; angle < 11; angle++) {
                xPos = pos.getX() + 0.5 + Math.sin(angle * 0.5584) * 0.5;
                zPos = pos.getZ() + 0.5 + Math.cos(angle * 0.5584) * 0.5;

                EntityFX entityfx = new EntityBigFlameFX(world, xPos, yPos, zPos, 0, 0, 0);
                Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
            }

            yPos += 0.35;
            for (int angle = 0; angle < 5; angle++) {
                xPos = pos.getX() + 0.5 + Math.sin(angle * 1.1168) * 0.2;
                zPos = pos.getZ() + 0.5 + Math.cos(angle * 1.1168) * 0.2;

                EntityFX entityfx = new EntityBigFlameFX(world, xPos, yPos, zPos, 0, 0, 0);
                Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
                world.spawnParticle(EnumParticleTypes.LAVA, xPos, yPos, zPos, 0, 0, 0);
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, xPos, yPos, zPos, 0, 0, 0);
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getIndex();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{FACING});
    }
}
