package gravestone.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
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
        GameRegistry.registerBlock(graveStone, ItemBlockGSGraveStone.class, "GSGraveStone");
        for (byte i = 0; i < EnumGraves.values().length; i++) {
            ItemStack graveStoneStack = new ItemStack(graveStone, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", i);
            if (GraveStoneHelper.isSwordGrave(i)) {
                nbt.setByte("SwordType", GraveStoneHelper.oldGraveTypeToSwordType(i));
            }

            graveStoneStack.setTagCompound(nbt);
            LanguageRegistry.addName(graveStoneStack, EnumGraves.values()[i].getLocalizedName());
        }
//        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(graveStone), 0, Resources.graveStoneModel);

        // memorials
        memorial = new BlockGSMemorial();
        advancedNTBBlockRegistration(memorial, "GSMemorial", "Memorial", EnumMemorials.values(), "GraveType", ItemBlockGSMemorial.class);

        // wither spawner
        spawner = new BlockGSSpawner();
        advancedMetaBlockRegistration(spawner, ItemBlockGSSpawner.class, "GSSpawner", EnumSpawner.values());

        // trap
        trap = new BlockGSTrap();
        advancedMetaBlockRegistration(trap, ItemBlockGSTrap.class, "GSTrap", EnumTrap.values());
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(trap), 0, Resources.trapModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(trap), 1, Resources.trapModel);
        ModelBakery.addVariantName(Item.getItemFromBlock(trap), new String[]{"gravestone:GSTrap_night_stone", "gravestone:GSTrap_thunder_stone"});

        //pile of bones
        pileOfBones = new BlockGSPileOfBones();
        advancedMetaBlockRegistration(pileOfBones, ItemBlockGSPileOfBones.class, "GSPileOfBones", EnumPileOfBones.values());
        // bone block
        boneBlock = new BlockGSBoneBlock();
        advancedMetaBlockRegistration(boneBlock, ItemBlockGSBoneBlock.class, "GSBoneBlock", EnumBoneBlock.values());
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(boneBlock), 0, Resources.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(boneBlock), 1, Resources.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(boneBlock), 2, Resources.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(boneBlock), 3, Resources.boneBlockModel);
        ModelBakery.addVariantName(Item.getItemFromBlock(boneBlock), new String[]{"gravestone:GSBoneBlock", "gravestone:GSBoneBlock_with_skull",
                "gravestone:GSBoneBlock_with_crawler", "gravestone:GSBoneBlock_with_skull_and_crawler"});
        // bone slab
        boneSlab = new BlockGSBoneSlab();
        simpleBlockRegistration(boneSlab, "GSBoneSlab", "Bone slab");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(boneSlab), 0, Resources.boneSlabModel);
        // bone stairs
        boneStairs = new BlockGSBoneStairs();
        simpleBlockRegistration(boneStairs, "GSBoneStairs", "Bone stairs");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(boneStairs), 0, Resources.boneStairsModel);
        // hauntedChest
        hauntedChest = new BlockGSHauntedChest();
        advancedNTBBlockRegistration(hauntedChest, "GSHauntedChest", "Haunted chest", EnumHauntedChest.values(), "ChestType", ItemBlockGSHauntedChest.class);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(hauntedChest), 0, Resources.hauntedChestModel);

        // skull candle
        candle = new BlockGSCandle();
        simpleBlockRegistration(candle, "Candle", ItemBlockGSCandle.class, "GSCandle");
        skullCandle = new BlockGSSkullCandle();
        advancedMetaBlockRegistration(skullCandle, ItemBlockGSSkullCandle.class, "GSSkullCandle", EnumSkullCandle.values());

        // altar
        altar = new BlockGSAltar();
        simpleBlockRegistration(altar, "GSAltar", "Altar");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(altar), 0, Resources.altarModel);
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
            LanguageRegistry.addName(new ItemStack(block, 1, meta), blockEnums[meta].getLocalizedName());
        }
    }

    private static void subNTBBlocksRegistration(Block block, IBlockEnum[] blockEnums, String nbtName) {
        for (byte i = 0; i < blockEnums.length; i++) {
            ItemStack stack = new ItemStack(block, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte(nbtName, i);
            stack.setTagCompound(nbt);
            LanguageRegistry.addName(stack, blockEnums[i].getLocalizedName());
        }
    }
}
