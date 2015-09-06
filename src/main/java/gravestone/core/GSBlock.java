package gravestone.core;

import gravestone.ModGraveStone;
import gravestone.block.*;
import gravestone.item.itemblock.*;
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

        // memorials
        memorial = new BlockGSMemorial();
        GameRegistry.registerBlock(memorial, ItemBlockGSMemorial.class, "GSMemorial");

        // wither spawner
        spawner = new BlockGSSpawner();
        GameRegistry.registerBlock(spawner, ItemBlockGSSpawner.class, "GSSpawner");

        // trap
        trap = new BlockGSTrap();
        GameRegistry.registerBlock(trap, ItemBlockGSTrap.class, "GSTrap");

        //pile of bones
        pileOfBones = new BlockGSPileOfBones();
        GameRegistry.registerBlock(pileOfBones, ItemBlockGSPileOfBones.class, "GSPileOfBones");

        // bone block
        boneBlock = new BlockGSBoneBlock();
        GameRegistry.registerBlock(boneBlock, ItemBlockGSBoneBlock.class, "GSBoneBlock");

        // bone slab
        boneSlab = new BlockGSBoneSlab();
        GameRegistry.registerBlock(boneSlab, "GSBoneSlab");

        // bone stairs
        boneStairs = new BlockGSBoneStairs();
        GameRegistry.registerBlock(boneStairs, "GSBoneStairs");

        // hauntedChest
        hauntedChest = new BlockGSHauntedChest();
        GameRegistry.registerBlock(hauntedChest, ItemBlockGSHauntedChest.class, "GSHauntedChest");

        // skull candle
        candle = new BlockGSCandle();
        GameRegistry.registerBlock(candle, ItemBlockGSCandle.class, "GSCandle");
        skullCandle = new BlockGSSkullCandle();
        GameRegistry.registerBlock(skullCandle, ItemBlockGSSkullCandle.class, "GSSkullCandle");

        // altar
        altar = new BlockGSAltar();
        GameRegistry.registerBlock(altar, "GSAltar");

        ModGraveStone.proxy.registerBlocksModels();
    }
}
