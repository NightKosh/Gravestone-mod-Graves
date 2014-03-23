package gravestone.core;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import gravestone.block.BlockGSAltar;
import gravestone.block.BlockGSBoneBlock;
import gravestone.block.BlockGSBoneSlab;
import gravestone.block.BlockGSBoneStairs;
import gravestone.block.BlockGSHauntedChest;
import gravestone.config.GraveStoneConfig;
import gravestone.block.BlockGSGraveStone;
import gravestone.block.BlockGSMemorial;
import gravestone.block.BlockGSSkullCandle;
import gravestone.block.BlockGSTrap;
import gravestone.block.BlockGSSpawner;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumMemorials;
import gravestone.block.GraveStoneHelper;
import gravestone.block.enums.EnumBoneBlock;
import gravestone.block.enums.EnumHauntedChest;
import gravestone.block.enums.EnumSkullCandle;
import gravestone.block.enums.EnumSpawner;
import gravestone.block.enums.EnumTrap;
import gravestone.block.enums.IBlockEnum;
import gravestone.item.ItemBlockGSBoneBlock;
import gravestone.item.ItemBlockGSGraveStone;
import gravestone.item.ItemBlockGSHauntedChest;
import gravestone.item.ItemBlockGSMemorial;
import gravestone.item.ItemBlockGSSkullCandle;
import gravestone.item.ItemBlockGSSpawner;
import gravestone.item.ItemBlockGSTrap;
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
    // GraveStone
    public static BlockGSGraveStone graveStone;
    // boss spawer
    public static BlockGSSpawner spawner;
    // Trap stones
    public static BlockGSTrap trap;
    // block memorial
    public static BlockGSMemorial memorial;
    // bone blocks
    public static BlockGSBoneBlock boneBlock;
    public static BlockGSBoneSlab boneSlab;
    public static BlockGSBoneStairs boneStairs;
    // Haunted Chest
    public static BlockGSHauntedChest hauntedChest;
    // skull candle
    public static BlockGSSkullCandle skullCandle;
    // altar
    public static BlockGSAltar altar;

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
        spawner = new BlockGSSpawner(GraveStoneConfig.spawnerID);
        advancedMetaBlockRegistration(spawner, "GSSpawner", "Spawner", "pickaxe", 1, EnumSpawner.values(), ItemBlockGSSpawner.class);

        // trap
        trap = new BlockGSTrap(GraveStoneConfig.trapID);
        advancedMetaBlockRegistration(trap, "GSTimeTrap", "Night stone", "pickaxe", 1, EnumTrap.values(), ItemBlockGSTrap.class);

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

        // skull candle
        skullCandle = new BlockGSSkullCandle(GraveStoneConfig.skullCandleID);
        advancedMetaBlockRegistration(skullCandle, "GSSkullCandle", "Skull candle", EnumSkullCandle.values(), ItemBlockGSSkullCandle.class);
                
        // altar
        altar = new BlockGSAltar(GraveStoneConfig.altarID);
        simpleBlockRegistration(altar, "GSAltar", "Altar", "pickaxe", 2);
    }

    private static void simpleBlockRegistration(Block block, String registerName, String name, String tool, int harvestLevel) {
        GameRegistry.registerBlock(block, registerName);
        LanguageRegistry.addName(block, name);
        MinecraftForge.setBlockHarvestLevel(block, tool, harvestLevel);
    }
    
    private static void advancedMetaBlockRegistration(Block block, String registerName, String name, IBlockEnum[] blockEnums, Class itemClass) {
 	GameRegistry.registerBlock(block, itemClass);
        subMetaBlocksRegistration(block, blockEnums);
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
