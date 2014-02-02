package gravestone.core;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import gravestone.block.BlockGSBoneBlock;
import gravestone.block.BlockGSBoneSlab;
import gravestone.block.BlockGSBoneStairs;
import gravestone.block.BlockGSHauntedChest;
import gravestone.config.GraveStoneConfig;
import gravestone.block.BlockGSGraveStone;
import gravestone.block.BlockGSMemorial;
import gravestone.block.BlockGSSkullCandle;
import gravestone.block.BlockGSTrap;
import gravestone.block.BlockGSWitherSpawner;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumMemorials;
import gravestone.block.GraveStoneHelper;
import gravestone.block.enums.EnumBoneBlock;
import gravestone.block.enums.EnumHauntedChest;
import gravestone.block.enums.IBlockEnum;
import gravestone.item.ItemBlockGSBoneBlock;
import gravestone.item.ItemBlockGSGraveStone;
import gravestone.item.ItemBlockGSHauntedChest;
import gravestone.item.ItemBlockGSMemorial;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBlock {

    private GSBlock() {
    }
    // block GraveStone
    public static BlockGSGraveStone graveStone;
    // Block wither spawer
    public static BlockGSWitherSpawner witherSpawner;
    // Block Time Trap
    public static BlockGSTrap trap;
    // block memorial
    public static BlockGSMemorial memorial;
    // bone blocks
    public static BlockGSBoneBlock boneBlock;
    public static BlockGSBoneSlab boneSlab;
    public static BlockGSBoneStairs boneStairs;
    // GhostlyChest
    public static BlockGSHauntedChest hauntedChest;
    // skull candle
    public static BlockGSSkullCandle skullCandle;

    public static void registration() {
        // gravestone
        graveStone = new BlockGSGraveStone(GraveStoneConfig.graveStoneID);
        simpleBlockRegistration(graveStone, "GSGraveStone", "GraveStone", "pickaxe", 1);
        GameRegistry.registerBlock(graveStone, ItemBlockGSGraveStone.class);
        for (byte i = 0; i < EnumGraves.values().length; i++) {
            ItemStack graveStoneStack = new ItemStack(graveStone, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", i);

            if (GraveStoneHelper.isSwordGrave(i)) {
                nbt.setByte("SwordType", GraveStoneHelper.graveTypeToSwordType(i));
            }

            graveStoneStack.setTagCompound(nbt);
            LanguageRegistry.addName(graveStoneStack, EnumGraves.values()[i].getName());
        }

        // memorials
        memorial = new BlockGSMemorial(GraveStoneConfig.memorialID);
        advancedNTBBlockRegistration(memorial, "GSMemorial", "Memorial", "pickaxe", 2, EnumMemorials.values(), "GraveType", ItemBlockGSMemorial.class);
        
        // wither spawner
        witherSpawner = new BlockGSWitherSpawner(GraveStoneConfig.witherSpawnerID);
        simpleBlockRegistration(witherSpawner, "GSWitherSpawner", "Wither spawner", "pickaxe", 1);

        // trap
        trap = new BlockGSTrap(GraveStoneConfig.timeTrapID);
        simpleBlockRegistration(trap, "GSTimeTrap", "Night stone", "pickaxe", 1);

        // bone block
        boneBlock = new BlockGSBoneBlock(GraveStoneConfig.boneBlockID);
        advancedMetaBlockRegistration(boneBlock, "GSBoneBlock", "Bone block", "pickaxe", 0, EnumBoneBlock.values(), ItemBlockGSBoneBlock.class);
        // bone slab
        boneSlab = new BlockGSBoneSlab(GraveStoneConfig.boneSlabID);
        simpleBlockRegistration(boneSlab, "GSBoneSlab", "Bone slab", "pickaxe", 0);
        // bone step
        boneStairs = new BlockGSBoneStairs(GraveStoneConfig.boneStairsID);
        simpleBlockRegistration(boneStairs, "GSBoneStairs", "Bone stairs", "pickaxe", 0);
        // hauntedChest
        hauntedChest = new BlockGSHauntedChest(GraveStoneConfig.hauntedChestID);
        advancedNTBBlockRegistration(hauntedChest, "GSHauntedChest", "Haunted chest", "axe", 0, EnumHauntedChest.values(), "ChestType", ItemBlockGSHauntedChest.class);

        /*
        // skull candle
        skullCandle = new BlockGSSkullCandle(GraveStoneConfig.skullCandleID);
        simpleBlockRegistration(skullCandle, "GSSkullCandle", "Skull candle", "pickaxe", 1);
        * */
    }

    private static void simpleBlockRegistration(Block block, String registerName, String name, String tool, int harvestLevel) {
        GameRegistry.registerBlock(block, registerName);
        LanguageRegistry.addName(block, name);
        MinecraftForge.setBlockHarvestLevel(block, tool, harvestLevel);
    }
    
    private static void advancedMetaBlockRegistration(Block block, String registerName, String name, String tool, int harvestLevel, IBlockEnum[] blockEnums, Class itemClass) {
 	GameRegistry.registerBlock(block, itemClass);
        MinecraftForge.setBlockHarvestLevel(block, tool, harvestLevel);
        subMetaBlocksRegistration(block, blockEnums);
    }
    
    private static void advancedNTBBlockRegistration(Block block, String registerName, String name, String tool, int harvestLevel, IBlockEnum[] blockEnums, String nbtName) {
        simpleBlockRegistration(block, registerName, name, tool, harvestLevel);
        subNTBBlocksRegistration(block, blockEnums, nbtName);
    }
    
    private static void advancedNTBBlockRegistration(Block block, String registerName, String name, String tool, int harvestLevel, IBlockEnum[] blockEnums, String nbtName, Class itemClass) {
        simpleBlockRegistration(block, registerName, name, tool, harvestLevel);
        GameRegistry.registerBlock(block, itemClass);
        subNTBBlocksRegistration(block, blockEnums, nbtName);
    }
    
    private static void subMetaBlocksRegistration(Block block, IBlockEnum[] blockEnums) {
        for (byte meta = 0; meta < blockEnums.length; meta++) {
            LanguageRegistry.addName(new ItemStack(block, 1, meta), blockEnums[meta].getName());
        }
    }
    
    private static void subNTBBlocksRegistration(Block block, IBlockEnum[] blockEnums, String nbtName) {
        for (byte i = 0; i < blockEnums.length; i++) {
            ItemStack stack = new ItemStack(block, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte(nbtName, i);
            stack.setTagCompound(nbt);
            LanguageRegistry.addName(stack, blockEnums[i].getName());
        }
    }
}
