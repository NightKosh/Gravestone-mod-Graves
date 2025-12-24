package nightkosh.gravestone.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone.block_entity.GraveStoneBlockEntity;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.core.GSBlocks;
import nightkosh.gravestone.gui.container.GraveInventory;
import org.apache.commons.lang3.StringUtils;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneHelper {

    /**
     * Check ground type and replace it on dirt if it grass or mycelium
     */
    public static void replaceGround(Level level, BlockPos pos) {
        var block = level.getBlockState(pos).getBlock();

        if (block.equals(Blocks.GRASS_BLOCK) ||
                block.equals(Blocks.MYCELIUM) ||
                block.equals(Blocks.DIRT_PATH) ||
                block.equals(Blocks.PODZOL)) {
            level.setBlock(pos, Blocks.DIRT.defaultBlockState(), 3);
        } else if (block.equals(Blocks.STONE)) {
            level.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 3);
        }
    }

    public static void spawnMob(Level level, BlockPos pos) {
        //TODO !!!
//        if (GSConfigs.spawnMobAtGraveDestruction && level.random.nextInt(10) == 0) {
//            if (level.getBlockEntity(pos) instanceof GraveStoneBlockEntity grave) {
////        TODO
//                var mob = GSMobSpawn.getMobEntity(level, grave.getGraveType(), pos.getX(), pos.getY(), pos.getZ());
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
    public static boolean canPlaceBlockAt(BlockState state) {
        return GSConfigs.CAN_PLACE_GRAVES_EVERY_WHERE.get() ||
                state.is(BlockTags.MINEABLE_WITH_SHOVEL);
    }

    public static void addSwordInfo(CompoundTag tag, ItemStack sword) {
        var swordTag = new CompoundTag();
        //TODO
//        sword.writeToNBT(swordTag);
        tag.put("Sword", swordTag);
    }

    public static ItemStack getSwordAsGrave(Item grave, ItemStack sword) {
        var graveStoneStack = new ItemStack(grave);//TODO, 1, EnumGraves.SWORD.ordinal());
        var tag = new CompoundTag();
        tag.putBoolean("Purified", false);
        GraveStoneHelper.addSwordInfo(tag, sword);
//TODO
//        graveStoneStack.setTagCompound(tag);
        return graveStoneStack;
    }

    public static boolean canFlowerBePlaced(Level level, BlockPos pos, ItemStack itemStack, GraveStoneBlockEntity entity) {
        if (Block.byItem(itemStack.getItem()) instanceof FlowerBlock flower) {
            var posBelow = pos.below();
            var soil = level.getBlockState(posBelow);
            return soil.canSustainPlant(level, posBelow, Direction.UP, flower);
        }
        return false;
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
            return //TODO level.provider.getDimension() == dimensionId &&
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

    public static void dropBlockWithoutInfo(Level level, GraveStoneBlockEntity graveEntity) {
        if (GSConfigs.DROP_GRAVE_BLOCK_AT_DESTRUCTION.get()) {
            if (graveEntity != null) {
                var grave = ((BlockGraveStone) graveEntity.getBlockState().getBlock());
                var itemStack = new ItemStack(GSBlocks.getGraveBlock(grave.graveType, grave.material).asItem());

                if (graveEntity.isSwordGrave()) {
                    graveEntity.dropSword();
                } else {
                    var tag = new CompoundTag();
                    tag.putBoolean("Purified", true);

                    itemStack.setTag(tag);
                    GraveInventory.dropItem(itemStack, level, graveEntity.getBlockPos());
                }
            }
        }
    }

    /**
     * Get grave block as item block
     */
    public static ItemStack getBlockItemStack(Level level, BlockPos pos, BlockState state) {
        if (GSConfigs.DROP_GRAVE_BLOCK_AT_DESTRUCTION.get() &&
                level.getBlockEntity(pos) instanceof GraveStoneBlockEntity grave) {
            var graveBlock = ((BlockGraveStone) grave.getBlockState().getBlock());
            var itemStack = new ItemStack(GSBlocks.getGraveBlock(graveBlock.graveType, graveBlock.material).asItem());

            var tag = new CompoundTag();
            tag.putBoolean("Purified", true);
            if (grave.getAge() > 0) {
                tag.putInt("Age", grave.getAge());
            }

            if (StringUtils.isNoneBlank(grave.getDeathMessageJson())) {
                tag.putString("deathMessageJson", grave.getDeathMessageJson());
            }
            if (grave.isSwordGrave()) {
                GraveStoneHelper.addSwordInfo(tag, grave.getSword());
            }

            itemStack.setTag(tag);
            return itemStack;
        }
        return ItemStack.EMPTY;
    }

}
