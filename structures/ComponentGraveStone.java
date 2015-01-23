package gravestone.structures;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ComponentGraveStone extends StructureComponent {

    protected ComponentGraveStone(int componentType, EnumFacing facing) {
        super(componentType);
        this.coordBaseMode = facing;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs,
     * Mob Spawners, it closes Mineshafts at the end, it adds Fences... not
     * used!!!
     */
    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
        return true;
    }

    /**
     * Build component
     */
    public boolean addComponentParts(World world, Random random) {
        return true;
    }

    /**
     * Place block
     */
    @Override
    public void func_175811_a(World world, IBlockState blockState, int x, int y, int z, StructureBoundingBox boundingBox) {
        super.func_175811_a(world, blockState, x, y, z, boundingBox);
    }
    // TODO Temporal fix
    public void placeBlockAtCurrentPosition(World world, IBlockState blockState, int x, int y, int z, StructureBoundingBox boundingBox) {
        func_175811_a(world, blockState, x, y, z, boundingBox);
    }

    @Override
    protected void func_175804_a(World world, StructureBoundingBox boundingBox, int startX, int startY, int startZ, int endX, int endY, int endZ, IBlockState state1, IBlockState state2, boolean replace) {
        super.func_175804_a(world, boundingBox, startX, startY, startZ, endX, endY, endZ, state1, state2, replace);
    }
    // TODO Temporal fix
    protected void fillWithBlocks(World world, StructureBoundingBox boundingBox, int startX, int startY, int startZ, int endX, int endY, int endZ, IBlockState state1, IBlockState state2, boolean replace) {
        func_175804_a(world, boundingBox, startX, startY, startZ, endX, endY, endZ, state1, state2, replace);
    }
    protected void fillWithBlocks(World world, StructureBoundingBox boundingBox, int startX, int startY, int startZ, int endX, int endY, int endZ, IBlockState state, boolean replace) {
        fillWithBlocks(world, boundingBox, startX, startY, startZ, endX, endY, endZ, state, state, replace);
    }

    @Override
    protected void func_175805_a(World world, StructureBoundingBox boundingBox, Random random, float chance, int startX, int startY, int startZ, int endX, int endY, int endZ, IBlockState state1, IBlockState state2, boolean replace) {
        super.func_175805_a(world, boundingBox, random, chance, startX, startY, startZ, endX, endY, endZ, state1, state2, replace);
    }
    // TODO Temporal fix
    protected void randomlyFillWithBlocks(World world, StructureBoundingBox boundingBox, Random random, float chance, int startX, int startY, int startZ, int endX, int endY, int endZ, IBlockState state1, IBlockState state2, boolean replace) {
        func_175805_a(world, boundingBox, random, chance, startX, startY, startZ, endX, endY, endZ, state1, state2, replace);
    }
    protected void randomlyFillWithBlocks(World world, StructureBoundingBox boundingBox, Random random, float chance, int startX, int startY, int startZ, int endX, int endY, int endZ, IBlockState state, boolean replace) {
        randomlyFillWithBlocks(world, boundingBox, random, chance, startX, startY, startZ, endX, endY, endZ, state, state, replace);
    }

    /**
     * Return world X coord
     *
     * @param x Bounding box X coord
     * @param z Bounding box Z coord
     */
    @Override
    public int getXWithOffset(int x, int z) {
        return super.getXWithOffset(x, z);
    }

    /**
     * Return world y coord
     *
     * @param y Bounding box Y coord
     */
    @Override
    public int getYWithOffset(int y) {
        return super.getYWithOffset(y);
    }

    /**
     * Return world Z coord
     *
     * @param x Bounding box X coord
     * @param z Bounding box Z coord
     */
    @Override
    public int getZWithOffset(int x, int z) {
        return super.getZWithOffset(x, z);
    }

    /**
     * Used to generate chests with items in it. ex: Temple Chests, Village
     * Blacksmith Chests, Mineshaft Chests.
     */
    @Override
    public boolean func_180778_a(World world, StructureBoundingBox boundingBox, Random random, int x, int y, int z, List chestContent, int par8) {
        return super.func_180778_a(world, boundingBox, random, x, y, z, chestContent, par8);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound nbttagcompound) {
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound nbttagcompound) {
    }
}
