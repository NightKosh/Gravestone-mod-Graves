package gravestone.core;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import gravestone.block.BlockGSBoneBlock;
import gravestone.block.BlockGSBoneSlab;
import gravestone.block.BlockGSBoneStairs;
import gravestone.block.BlockGSGhostlyChest;
import gravestone.config.GraveStoneConfig;
import gravestone.block.BlockGSGraveStone;
import gravestone.block.BlockGSMemorial;
import gravestone.block.BlockGSSkullCandle;
import gravestone.block.BlockGSTrap;
import gravestone.block.BlockGSWitherSpawner;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumMemorials;
import gravestone.block.GraveStoneHelper;
import gravestone.block.enums.EnumChestTypes;
import gravestone.block.enums.IBlockEnum;
import gravestone.item.ItemBlockGSGraveStone;
import gravestone.item.ItemBlockGSMemorial;
import net.minecraft.block.Block;
import gravestone.item.ItemBlockGSGraveStone;
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
    public static BlockGSGhostlyChest ghostlyChest;
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
        advancedBlockRegistration(memorial, "GSMemorial", "Memorial", "pickaxe", 2, EnumMemorials.values(), "GraveType");
        GameRegistry.registerBlock(memorial, ItemBlockGSMemorial.class);
        
        // wither spawner
        witherSpawner = new BlockGSWitherSpawner(GraveStoneConfig.witherSpawnerID);
        simpleBlockRegistration(witherSpawner, "GSWitherSpawner", "Wither spawner", "pickaxe", 1);

        // trap
        trap = new BlockGSTrap(GraveStoneConfig.timeTrapID);
        simpleBlockRegistration(trap, "GSTimeTrap", "Night stone", "pickaxe", 1);

        // bone block
        boneBlock = new BlockGSBoneBlock(GraveStoneConfig.boneBlockID);
        simpleBlockRegistration(boneBlock, "GSBoneBlock", "Bone block", "pickaxe", 1);
        // bone slab
        boneSlab = new BlockGSBoneSlab(GraveStoneConfig.boneSlabID);
        simpleBlockRegistration(boneSlab, "GSBoneSlab", "Bone slab", "pickaxe", 1);
        // bone step
        boneStairs = new BlockGSBoneStairs(GraveStoneConfig.boneStairsID);
        simpleBlockRegistration(boneStairs, "GSBoneStairs", "Bone stairs", "pickaxe", 1);
        
        // ghostChest
        ghostlyChest = new BlockGSGhostlyChest(GraveStoneConfig.ghostlyChestID);
        advancedBlockRegistration(ghostlyChest, "GSGhostlyChest", "Ghostly chest", "axe", 1, EnumChestTypes.values(), "ChestType");

        // skull candle
        skullCandle = new BlockGSSkullCandle(GraveStoneConfig.skullCandleID);
        simpleBlockRegistration(skullCandle, "GSSkullCandle", "Skull candle", "pickaxe", 1);
    }

    private static void simpleBlockRegistration(Block block, String registerName, String name, String tool, int harvestLevel) {
        GameRegistry.registerBlock(block, registerName);
        LanguageRegistry.addName(block, name);
        MinecraftForge.setBlockHarvestLevel(block, tool, harvestLevel);
    }
    
    private static void advancedBlockRegistration(Block block, String registerName, String name, String tool, int harvestLevel, IBlockEnum[] blockEnums, String nbtName) {
        simpleBlockRegistration(block, registerName, name, tool, harvestLevel);
        for (byte i = 0; i < blockEnums.length; i++) {
            ItemStack stack = new ItemStack(block, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte(nbtName, i);

            stack.setTagCompound(nbt);
            LanguageRegistry.addName(stack, blockEnums[i].getName());
        }
    }
}
