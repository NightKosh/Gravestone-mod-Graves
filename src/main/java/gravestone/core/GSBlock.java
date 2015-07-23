package gravestone.core;

import gravestone.block.*;
import gravestone.item.itemblock.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
//        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(graveStone), 0, Resources.graveStoneModel);

        // memorials
        memorial = new BlockGSMemorial();
        GameRegistry.registerBlock(memorial, ItemBlockGSMemorial.class, "GSMemorial");

        // wither spawner
        spawner = new BlockGSSpawner();
        GameRegistry.registerBlock(spawner, ItemBlockGSSpawner.class, "GSSpawner");

        // trap
        trap = new BlockGSTrap();
        GameRegistry.registerBlock(trap, ItemBlockGSTrap.class, "GSTrap");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(trap), 0, Resources.trapModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(trap), 1, Resources.trapModel);
        ModelBakery.addVariantName(Item.getItemFromBlock(trap), new String[]{"gravestone:GSTrap_night_stone", "gravestone:GSTrap_thunder_stone"});

        //pile of bones
        pileOfBones = new BlockGSPileOfBones();
        GameRegistry.registerBlock(pileOfBones, ItemBlockGSPileOfBones.class, "GSPileOfBones");
        // bone block
        boneBlock = new BlockGSBoneBlock();
        GameRegistry.registerBlock(boneBlock, ItemBlockGSBoneBlock.class, "GSBoneBlock");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(boneBlock), 0, Resources.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(boneBlock), 1, Resources.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(boneBlock), 2, Resources.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(boneBlock), 3, Resources.boneBlockModel);
        ModelBakery.addVariantName(Item.getItemFromBlock(boneBlock), new String[]{"gravestone:GSBoneBlock", "gravestone:GSBoneBlock_with_skull",
                "gravestone:GSBoneBlock_with_crawler", "gravestone:GSBoneBlock_with_skull_and_crawler"});
        // bone slab
        boneSlab = new BlockGSBoneSlab();
        GameRegistry.registerBlock(boneSlab, "GSBoneSlab");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(boneSlab), 0, Resources.boneSlabModel);
        // bone stairs
        boneStairs = new BlockGSBoneStairs();
        GameRegistry.registerBlock(boneStairs, "GSBoneStairs");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(boneStairs), 0, Resources.boneStairsModel);
        // hauntedChest
        hauntedChest = new BlockGSHauntedChest();
        GameRegistry.registerBlock(hauntedChest, ItemBlockGSHauntedChest.class, "GSHauntedChest");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(hauntedChest), 0, Resources.hauntedChestModel);

        // skull candle
        candle = new BlockGSCandle();
        GameRegistry.registerBlock(candle, ItemBlockGSCandle.class, "GSCandle");
        skullCandle = new BlockGSSkullCandle();
        GameRegistry.registerBlock(skullCandle, ItemBlockGSSkullCandle.class, "GSSkullCandle");

        // altar
        altar = new BlockGSAltar();
        GameRegistry.registerBlock(altar, "GSAltar");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(altar), 0, Resources.altarModel);
    }
}
