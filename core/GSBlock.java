package gravestone.core;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import gravestone.block.*;
import gravestone.block.enums.*;
import gravestone.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBlock {

    // GraveStone
    public static BlockGSGraveStone graveStone;
    // boss spawer
    public static BlockGSSpawner spawner;
    // Trap stones
    public static BlockGSTrap trap;
    // block memorial
    public static BlockGSMemorial memorial;
    public static BlockGSInvisibleWall invisibleWall;
    // bone blocks
    public static BlockGSPileOfBones pileOfBones;
    public static BlockGSBoneBlock boneBlock;
    public static BlockGSBoneSlab boneSlab;
    public static BlockGSBoneStairs boneStairs;
    // Haunted Chest
    public static BlockGSHauntedChest hauntedChest;
    // skull candle
    public static BlockGSCandle candle;
    public static BlockGSSkullCandle skullCandle;
    // altar
    public static BlockGSAltar altar;

    private GSBlock() {
    }

    public static void registration() {
        // gravestone
        graveStone = new BlockGSGraveStone();
//        simpleBlockRegistration(graveStone, "GSGraveStone", "GraveStone");
        GameRegistry.registerBlock(graveStone, ItemBlockGSGraveStone.class, "GSGraveStone");
        for (byte i = 0; i < EnumGraves.values().length; i++) {
            ItemStack graveStoneStack = new ItemStack(graveStone, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", i);
            //TODO
            if (GraveStoneHelper.isSwordGrave(i)) {
                nbt.setByte("SwordType", GraveStoneHelper.oldGraveTypeToSwordType(i));
            }

            graveStoneStack.setTagCompound(nbt);
            LanguageRegistry.addName(graveStoneStack, EnumGraves.values()[i].getName());
        }

        // memorials
        memorial = new BlockGSMemorial();
        advancedNTBBlockRegistration(memorial, "GSMemorial", "Memorial", EnumMemorials.values(), "GraveType", ItemBlockGSMemorial.class);

        invisibleWall = new BlockGSInvisibleWall();
        GameRegistry.registerBlock(invisibleWall, "GSInvisibleWall");

        // wither spawner
        spawner = new BlockGSSpawner();
        advancedMetaBlockRegistration(spawner, ItemBlockGSSpawner.class, "GSSpawner", EnumSpawner.values());

        // trap
        trap = new BlockGSTrap();
        advancedMetaBlockRegistration(trap, ItemBlockGSTrap.class, "GSTrap", EnumTrap.values());

        //pile of bones
        pileOfBones = new BlockGSPileOfBones();
        advancedMetaBlockRegistration(pileOfBones, ItemBlockGSPileOfBones.class, "GSPileOfBones", EnumPileOfBones.values());
        // bone block
        boneBlock = new BlockGSBoneBlock();
        advancedMetaBlockRegistration(boneBlock, ItemBlockGSBoneBlock.class, "GSBoneBlock", EnumBoneBlock.values());
        // bone slab
        boneSlab = new BlockGSBoneSlab();
        simpleBlockRegistration(boneSlab, "GSBoneSlab", "Bone slab");
        // bone step
        boneStairs = new BlockGSBoneStairs();
        simpleBlockRegistration(boneStairs, "GSBoneStairs", "Bone stairs");
        // hauntedChest
        hauntedChest = new BlockGSHauntedChest();
        advancedNTBBlockRegistration(hauntedChest, "GSHauntedChest", "Haunted chest", EnumHauntedChest.values(), "ChestType", ItemBlockGSHauntedChest.class);

        // skull candle
        candle = new BlockGSCandle();
        simpleBlockRegistration(candle, "Candle", ItemBlockGSCandle.class, "GSCandle");
        skullCandle = new BlockGSSkullCandle();
        advancedMetaBlockRegistration(skullCandle, ItemBlockGSSkullCandle.class, "GSSkullCandle", EnumSkullCandle.values());

        // altar
        altar = new BlockGSAltar();
        simpleBlockRegistration(altar, "GSAltar", "Altar");
    }

    private static void simpleBlockRegistration(Block block, String name, Class itemClass, String registerName) {
        GameRegistry.registerBlock(block, itemClass, registerName);
        LanguageRegistry.addName(block, name);
    }

    private static void simpleBlockRegistration(Block block, String registerName, String name) {
        GameRegistry.registerBlock(block, registerName);
        LanguageRegistry.addName(block, name);
    }

    private static void advancedMetaBlockRegistration(Block block, Class itemClass, String registerName, IBlockEnum[] blockEnums) {
        GameRegistry.registerBlock(block, itemClass, registerName);
        subMetaBlocksRegistration(block, blockEnums);
    }

    private static void advancedNTBBlockRegistration(Block block, String registerName, String name, IBlockEnum[] blockEnums, String nbtName) {
        simpleBlockRegistration(block, registerName, name);
        subNTBBlocksRegistration(block, blockEnums, nbtName);
    }

    private static void advancedNTBBlockRegistration(Block block, String registerName, String name, IBlockEnum[] blockEnums, String nbtName, Class itemClass) {
        GameRegistry.registerBlock(block, itemClass, registerName);
        LanguageRegistry.addName(block, name);
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
