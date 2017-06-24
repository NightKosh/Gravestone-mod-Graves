package nightkosh.gravestone.helper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.GSBlock;
import nightkosh.gravestone.inventory.GraveInventory;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;

import java.util.Arrays;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneHelper {


    public static final List<BlockFlower> FLOWERS = Arrays.asList(Blocks.YELLOW_FLOWER, Blocks.RED_FLOWER);


    /**
     * Check ground type and replace it on dirt if it grass or mycelium
     */
    public static void replaceGround(World world, BlockPos pos) {
        Block botBlock = world.getBlockState(pos).getBlock();

        if (botBlock.equals(Blocks.GRASS) || botBlock.equals(Blocks.MYCELIUM)) {
            world.setBlockState(pos, Blocks.DIRT.getBlockState().getBaseState());
        }
    }

    public static void spawnMob(World world, BlockPos pos) {
        //TODO !!!
//        if (Config.spawnMobAtGraveDestruction && world.rand.nextInt(10) == 0) {
//            TileEntityGraveStone tileEntity = (TileEntityGraveStone) world.getTileEntity(pos);
//
//            if (tileEntity != null) {
        //TODO
//                Entity mob = GSMobSpawn.getMobEntity(world, tileEntity.getGraveType(), pos.getX(), pos.getY(), pos.getZ());
//
//                if (mob != null) {
//                    GSMobSpawn.spawnMob(world, mob, pos.getX(), pos.getY(), pos.getZ(), false);
//                }
//            }
//        }
    }

    /**
     * Check can be grave placed on this type of surface
     */
    public static boolean canPlaceBlockAt(World world, BlockPos pos) {
        return canPlaceBlockAt(world, world.getBlockState(pos).getBlock(), pos);
    }

    public static boolean canPlaceBlockAt(World world, Block block, BlockPos pos) {
        if (Config.canPlaceGravesEveryWhere) {
            return true;
        } else {
            String tool = block.getHarvestTool(world.getBlockState(pos));
            return tool != null && tool.equals("shovel");
        }
    }

    public static void addSwordInfo(NBTTagCompound nbt, ItemStack sword) {
        NBTTagCompound swordNBT = new NBTTagCompound();
        sword.writeToNBT(swordNBT);
        nbt.setTag("Sword", swordNBT);
    }

    public static ItemStack getSwordAsGrave(Item grave, ItemStack sword) {
        ItemStack graveStoneStack = new ItemStack(grave, 1, EnumGraves.SWORD.ordinal());
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("Purified", false);
        GraveStoneHelper.addSwordInfo(nbt, sword);

        graveStoneStack.setTagCompound(nbt);
        return graveStoneStack;
    }

    public static boolean canFlowerBePlaced(World world, BlockPos pos, ItemStack itemStack, TileEntityGraveStone te) {
        if (canFlowerBePlacedOnGrave(te)) {
            Item item = itemStack.getItem();
            if (Block.getBlockFromItem(item) instanceof BlockFlower) {
                return true;
            } else if (item instanceof IPlantable) {
                IBlockState soil = world.getBlockState(pos.down());
                return Block.getBlockFromItem(item).canSustainPlant(soil, world, pos.down(), EnumFacing.UP, (IPlantable) item);
            }
        }
        return false;
    }

    public static boolean canFlowerBePlacedOnGrave(TileEntityGraveStone te) {
        return !te.isSwordGrave() && (te.getGraveType().getGraveType() == EnumGraveType.VERTICAL_PLATE ||
                te.getGraveType().getGraveType() == EnumGraveType.CROSS);
    }

    public static class RestrictedArea {
        private final BlockPos firstPoint;
        private final BlockPos lastPoint;

        public RestrictedArea(int startX, int startY, int startZ, int endX, int endY, int endZ) {
            firstPoint = new BlockPos(startX, startY, startZ);
            lastPoint = new BlockPos(endX, endY, endZ);
        }

        public boolean isInArea(BlockPos pos) {
            return pos.getX() >= firstPoint.getX() && pos.getX() <= lastPoint.getX() &&
                    pos.getY() >= firstPoint.getY() && pos.getY() <= lastPoint.getY() &&
                    pos.getZ() >= firstPoint.getZ() && pos.getZ() <= lastPoint.getZ();
        }

        public static RestrictedArea getFromString(String area) {
            String[] coordinates = area.split(",");
            if (coordinates.length == 6) {
                try {
                    return new GraveStoneHelper.RestrictedArea(
                            Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2]),
                            Integer.parseInt(coordinates[3]), Integer.parseInt(coordinates[4]), Integer.parseInt(coordinates[5]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    /**
     * Drop grave as item block
     */
    public static void dropBlock(World world, BlockPos pos, IBlockState state) {
        ItemStack itemStack = getBlockItemStack(world, pos, state);

        if (itemStack != null) {
            GraveInventory.dropItem(itemStack, world, pos);
        }
    }

    public static void dropBlockWithoutInfo(World world, BlockPos pos, IBlockState state) {
        ItemStack itemStack = new ItemStack(Item.getItemFromBlock(GSBlock.GRAVE_STONE), 1);
        TileEntityGraveStone tileEntity = (TileEntityGraveStone) world.getTileEntity(pos);

        if (tileEntity != null) {
            if (tileEntity.isSwordGrave()) {
                tileEntity.dropSword();
            } else if (itemStack != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                itemStack.setItemDamage(tileEntity.getGraveTypeNum());
                nbt.setBoolean("Mossy", tileEntity.isMossy());
                nbt.setBoolean("Purified", true);

                itemStack.setTagCompound(nbt);
                GraveInventory.dropItem(itemStack, world, pos);
            }
        }
    }

    /**
     * Get grave block as item block
     */
    public static ItemStack getBlockItemStack(IBlockAccess access, BlockPos pos, IBlockState state) {
        ItemStack itemStack = new ItemStack(Item.getItemFromBlock(GSBlock.GRAVE_STONE), 1);
        TileEntityGraveStone tileEntity = (TileEntityGraveStone) access.getTileEntity(pos);

        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            itemStack.setItemDamage(tileEntity.getGraveTypeNum());

            if (tileEntity.getDeathTextComponent().isLocalized()) {
                nbt.setBoolean("isLocalized", true);
                nbt.setString("name", tileEntity.getDeathTextComponent().getName());
                nbt.setString("KillerName", tileEntity.getDeathTextComponent().getKillerName());
            }

            nbt.setString("DeathText", tileEntity.getDeathTextComponent().getDeathText());
            nbt.setInteger("Age", tileEntity.getAge());

            if (tileEntity.isSwordGrave()) {
                GraveStoneHelper.addSwordInfo(nbt, tileEntity.getSword());
            }

            nbt.setBoolean("Enchanted", tileEntity.isEnchanted());
            nbt.setBoolean("Mossy", tileEntity.isMossy());

            nbt.setBoolean("Purified", true);

            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }
}