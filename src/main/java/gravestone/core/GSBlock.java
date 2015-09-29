package gravestone.core;

import gravestone.ModGraveStone;
import gravestone.block.*;
import gravestone.item.itemblock.*;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBlock {

    public static final IBlockState DEFAULT_STONE_STATE = Blocks.stone.getDefaultState();
    public static final int DIORITE_META = Blocks.stone.getMetaFromState(DEFAULT_STONE_STATE.withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE_SMOOTH));
    public static final int ANDESITE_META = Blocks.stone.getMetaFromState(DEFAULT_STONE_STATE.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
    public static final int GRANITE_META = Blocks.stone.getMetaFromState(DEFAULT_STONE_STATE.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH));

    public static BlockGSGraveStone graveStone;
    public static BlockGSSpawner spawner;
    public static BlockGSTrap trap;
    public static BlockGSMemorial memorial;
    public static BlockGSPileOfBones pileOfBones;

    public static BlockGSBoneBlock boneBlock;
    public static BlockGSBoneSlab boneSlab;
    public static BlockGSBoneStairs boneStairs;

    public static BlockGSHauntedChest hauntedChest;

    public static BlockGSCandle candle;
    public static BlockGSSkullCandle skullCandle;

    public static BlockGSAltar altar;

    public static BlockGSInvisibleWall invisibleWall;

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

        invisibleWall = new BlockGSInvisibleWall();
        GameRegistry.registerBlock(invisibleWall, "GSInvisibleWall");

        ModGraveStone.proxy.registerBlocksModels();
    }
}
