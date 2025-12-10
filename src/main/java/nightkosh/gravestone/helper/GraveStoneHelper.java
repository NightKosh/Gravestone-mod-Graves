package nightkosh.gravestone.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.IPlantable;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.config.GSConfigs;
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
    public static void replaceGround(Level level, BlockPos pos) {
        var botBlock = level.getBlockState(pos).getBlock();

        if (botBlock.equals(Blocks.GRASS) || botBlock.equals(Blocks.MYCELIUM)) {
            level.setBlockState(pos, Blocks.DIRT.getBlockState().getBaseState());
        }
    }

    public static void spawnMob(Level level, BlockPos pos) {
        //TODO !!!
//        if (Config.spawnMobAtGraveDestruction && level.rand.nextInt(10) == 0) {
//            TileEntityGraveStone tileEntity = (TileEntityGraveStone) level.getTileEntity(pos);
//
//            if (tileEntity != null) {
        //TODO
//                Entity mob = GSMobSpawn.getMobEntity(world, tileEntity.getGraveType(), pos.getX(), pos.getY(), pos.getZ());
//
//                if (mob != null) {
//                    GSMobSpawn.spawnMob(level, mob, pos.getX(), pos.getY(), pos.getZ(), false);
//                }
//            }
//        }
    }

    /**
     * Check can be grave placed on this type of surface
     */
    public static boolean canPlaceBlockAt(Level level, BlockPos pos) {
        return canPlaceBlockAt(level, level.getBlockState(pos).getBlock(), pos);
    }

    public static boolean canPlaceBlockAt(Level level, Block block, BlockPos pos) {
        if (GSConfigs.CAN_PLACE_GRAVES_EVERY_WHERE.get()) {
            return true;
        } else {
            String tool = block.getHarvestTool(level.getBlockState(pos));
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

    public static boolean canFlowerBePlaced(Level level, BlockPos pos, ItemStack itemStack, TileEntityGraveStone te) {
        if (canFlowerBePlacedOnGrave(te)) {
            Item item = itemStack.getItem();
            if (Block.getBlockFromItem(item) instanceof BlockFlower) {
                return true;
            } else if (item instanceof IPlantable) {
                IBlockState soil = level.getBlockState(pos.below());
                return Block.getBlockFromItem(item).canSustainPlant(soil, level, pos.below(), EnumFacing.UP, (IPlantable) item);
            }
        }
        return false;
    }

    public static boolean canFlowerBePlacedOnGrave(TileEntityGraveStone te) {
        return !te.isSwordGrave() && (te.getGraveType().getGraveType() == EnumGraveType.VERTICAL_PLATE ||
                te.getGraveType().getGraveType() == EnumGraveType.CROSS);
    }

    public static class RestrictedArea {
        private final int dimensionId;
        private final BlockPos firstPoint;
        private final BlockPos lastPoint;

        public RestrictedArea(int startX, int startY, int startZ, int endX, int endY, int endZ) {
            this(0, startX, startY, startZ, endX, endY, endZ);
        }

        public RestrictedArea(int dimensionId, int startX, int startY, int startZ, int endX, int endY, int endZ) {
            this.dimensionId = dimensionId;
            this.firstPoint = new BlockPos(startX, startY, startZ);
            this.lastPoint = new BlockPos(endX, endY, endZ);
        }

        public boolean isInArea(Level level, BlockPos pos) {
            return level.provider.getDimension() == dimensionId &&
                    pos.getX() >= firstPoint.getX() && pos.getX() <= lastPoint.getX() &&
                    pos.getY() >= firstPoint.getY() && pos.getY() <= lastPoint.getY() &&
                    pos.getZ() >= firstPoint.getZ() && pos.getZ() <= lastPoint.getZ();
        }

        public static RestrictedArea getFromString(String area) {
            String[] coordinates = area.split(",");
            if (coordinates.length == 7) {
                try {
                    return new GraveStoneHelper.RestrictedArea(
                            Integer.parseInt(coordinates[0]),
                            Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[3]),
                            Integer.parseInt(coordinates[4]), Integer.parseInt(coordinates[5]), Integer.parseInt(coordinates[6]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else if (coordinates.length == 6) {
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
    public static void dropBlock(Level level, BlockPos pos, IBlockState state) {
        ItemStack itemStack = getBlockItemStack(level, pos, state);

        if (itemStack != null) {
            GraveInventory.dropItem(itemStack, level, pos);
        }
    }

    public static void dropBlockWithoutInfo(Level level, BlockPos pos, IBlockState state) {
        if (GSConfigs.DROP_GRAVE_BLOCK_AT_DESTRUCTION.get()) {
            ItemStack itemStack = new ItemStack(Item.getItemFromBlock(GSBlock.getGraveStone()), 1);
            TileEntityGraveStone tileEntity = (TileEntityGraveStone) level.getTileEntity(pos);

            if (tileEntity != null) {
                if (tileEntity.isSwordGrave()) {
                    tileEntity.dropSword();
                } else if (itemStack != null) {
                    NBTTagCompound nbt = new NBTTagCompound();
                    itemStack.setItemDamage(tileEntity.getGraveTypeNum());
                    nbt.setBoolean("Mossy", tileEntity.isMossy());
                    nbt.setBoolean("Purified", true);

                    itemStack.setTagCompound(nbt);
                    GraveInventory.dropItem(itemStack, level, pos);
                }
            }
        }
    }

    /**
     * Get grave block as item block
     */
    public static ItemStack getBlockItemStack(IBlockAccess access, BlockPos pos, IBlockState state) {
        if (GSConfigs.DROP_GRAVE_BLOCK_AT_DESTRUCTION.get()) {
            ItemStack itemStack = new ItemStack(Item.getItemFromBlock(GSBlock.getGraveStone()), 1);
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
        } else {
            return ItemStack.EMPTY;
        }
    }

//TODO #245
//    public static void replaceOldGraveByNew(World world, BlockPos pos) {
//        IBlockState oldState = world.getBlockState(pos);
//        TileEntity oldTe = world.getTileEntity(pos);
//        //TODO !!!!!!!!!!!!!!!!!
//
//        IBlockState newState = GSBlock.GRAVE_STONE.getStateFromMeta(GSBlock.GRAVE_STONE.getMetaFromState(oldState));//= GSBlock.GRAVE_STONE.getDefaultState();
//
//        world.setBlockState(pos, newState);
//        TileEntity newTe = world.getTileEntity(pos);
//        if (oldTe  != null && newTe != null && oldTe instanceof TileEntityGraveStone && newTe instanceof TileEntityGraveStone) {
//            TileEntityGraveStone oldTeGS = (TileEntityGraveStone) oldTe;
//            TileEntityGraveStone newTeGS = (TileEntityGraveStone) newTe;
//
//            newTeGS.setGraveType(oldTeGS.getGraveTypeNum());
//            newTeGS.setEnchanted(oldTeGS.isEnchanted());
//            newTeGS.setMossy(oldTeGS.isMossy());
//            newTeGS.setAge(oldTeGS.getAge());
//            newTeGS.setDeathTextComponent(oldTeGS.getDeathTextComponent());
//            newTeGS.setInventory(oldTeGS.getInventory());
//
//            newTeGS.setSword(oldTeGS.getSword());
//            newTeGS.setFlower(oldTeGS.getFlower());
//            newTeGS.setOwner(oldTeGS.getOwner());
//            newTeGS.setPurified(oldTeGS.isPurified());
//            newTeGS.setSpawnerHelper(oldTeGS.getSpawnerHelper());
//        }
//    }
}