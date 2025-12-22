package nightkosh.gravestone.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block_entity.GraveStoneBlockEntity;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.helper.GraveStoneHelper;
import nightkosh.gravestone.inventory.GraveInventory;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

import static nightkosh.gravestone.ModGraveStone.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGraveStone extends BaseEntityBlock {

    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public final EnumGraveMaterial material;
    public final EnumGraveType graveType;

    public BlockGraveStone(EnumGraveType graveType, EnumGraveMaterial material) {
        super(BlockBehaviour.Properties.of(Material.STONE)
                .strength(1.5F, 6)
                .explosionResistance(Float.MAX_VALUE)
                .requiresCorrectToolForDrops()
                .noCollission());
        this.graveType = graveType;
        this.material = material;
        //TODO
//        this.setTickRandomly(GSConfigs.REMOVE_EMPTY_GRAVES.get());

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Nonnull
    @Override
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.MODEL;
    }

    @Nonnull
    @Override
    public String getDescriptionId() {
        return switch (graveType) {
            case GRAVE_STONE -> "block.gravestone.grave_stone";
            case GRAVE_PLATE -> "block.gravestone.grave_plate";
            case CROSS -> "block.gravestone.cross";
            case OBELISK -> "block.gravestone.obelisk";
            case CELTIC_CROSS -> "block.gravestone.celtic_cross";
            case PET_GRAVE_STONE -> "block.gravestone.pet_grave_stone";
            case VILLAGER_GRAVE_STONE -> "block.gravestone.villager_grave_stone";
            default -> "block.gravestone.grave_stone";
        };
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (!level.isClientSide) {
            level.setBlock(pos, state, 2);
            var blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof GraveStoneBlockEntity grave) {
                if (stack.hasTag()) {
                    var tag = stack.getTag();
                    if (tag.contains("deathMessageJson")) {
                        grave.setDeathMessageJson(tag.getString("deathMessageJson"));
                    }

                    grave.setAge(tag.getInt("Age"));
                    grave.setPurified(tag.getBoolean("Purified"));

                    if (tag.contains("Sword")) {
                        var sword = ItemStack.of(tag.getCompound("Sword"));
                        grave.setSword(sword);
                    }
                }
            }
        }
    }
//
//    /**
//     * Checks to see if its valid to put this block at the specified
//     * coordinates. Args: level, x, y, z
//     */
//    @Override
//    public boolean canPlaceBlockAt(Level level, BlockPos pos) {
//        return GraveStoneHelper.canPlaceBlockAt(level, pos.below());
//    }
//
    private static final VoxelShape GS_SOUTH = Block.box(2, 0, 2, 14, 16, 4);
    private static final VoxelShape GS_NORTH = Block.box(2, 0, 12, 14, 16, 14);
    private static final VoxelShape GS_EAST = Block.box(2, 0, 2, 4, 16, 14);
    private static final VoxelShape GS_WEST = Block.box(12, 0, 2, 14, 16, 14);

    private static final VoxelShape CROSS_SOUTH = Block.box(2, 0, 2, 14, 16, 4);
    private static final VoxelShape CROSS_NORTH = Block.box(2, 0, 12, 14, 16, 14);
    private static final VoxelShape CROSS_EAST = Block.box(2, 0, 2, 4, 16, 14);
    private static final VoxelShape CROSS_WEST = Block.box(12, 0, 2, 14, 16, 14);

    private static final VoxelShape CC_SOUTH = Block.box(1, 0, 2, 15, 18, 4);
    private static final VoxelShape CC_NORTH = Block.box(1, 0, 12, 15, 18, 14);
    private static final VoxelShape CC_EAST = Block.box(2, 0, 1, 4, 18, 15);
    private static final VoxelShape CC_WEST = Block.box(12, 0, 1, 14, 18, 15);

    private static final VoxelShape OBELISK = Block.box(0.35F, 0, 0.35F, 0.65F, 0.92F, 0.65F);

    private static final VoxelShape GP_NORTH_SOUTH = Block.box(2, 0, 1, 14, 1, 15);
    private static final VoxelShape GP_EAST_WEST = Block.box(1, 0, 2, 15, 1, 14);
    //TODO
    private static final VoxelShape SWORD_SOUTH_NORTH = Block.box(0.375F, 0, 0.4375F, 0.625F, 0.9F, 0.5625F);
    private static final VoxelShape SWORD_EAST_WEST = Block.box(0.4375F, 0, 0.375F, 0.5625F, 0.9F, 0.625F);

    @Override
    public VoxelShape getShape(
            @Nonnull BlockState blockState, @Nonnull BlockGetter blockGetter,
            @Nonnull BlockPos blockPos, @Nonnull CollisionContext collisionContext) {
        var facing = blockState.getValue(FACING);

        return switch (graveType) {
            case GRAVE_STONE, VILLAGER_GRAVE_STONE, PET_GRAVE_STONE -> switch (facing) {
                case SOUTH -> GS_SOUTH;
                case EAST -> GS_EAST;
                case WEST -> GS_WEST;
                case NORTH -> GS_NORTH;
                default -> GS_NORTH;
            };
            case CROSS -> switch (facing) {
                case SOUTH -> CROSS_SOUTH;
                case EAST -> CROSS_EAST;
                case WEST -> CROSS_WEST;
                case NORTH -> CROSS_NORTH;
                default -> CROSS_NORTH;
            };
            case CELTIC_CROSS -> switch (facing) {
                case SOUTH -> CC_SOUTH;
                case EAST -> CC_EAST;
                case WEST -> CC_WEST;
                case NORTH -> CC_NORTH;
                default -> CC_NORTH;
            };
            case OBELISK -> OBELISK;
            case GRAVE_PLATE -> switch (facing) {
                case EAST, WEST -> GP_EAST_WEST;
                case SOUTH, NORTH -> GP_NORTH_SOUTH;
                default -> GP_NORTH_SOUTH;
            };
            case SWORD -> switch (facing) {
                case EAST, WEST -> SWORD_EAST_WEST;
                case SOUTH, NORTH -> SWORD_SOUTH_NORTH;
                default -> SWORD_SOUTH_NORTH;
            };
        };
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            player.causeFoodExhaustion(0.025F);

            if (!level.isClientSide() && !level.restoringBlockSnapshots) {
                if (level.getBlockEntity(pos) instanceof GraveStoneBlockEntity grave && grave.canBeLooted(player)) {
                    GraveStoneHelper.spawnMob(level, pos);

                    if (grave.hasFlower()) {
                        grave.dropFlower();
                    }

                    //TODO
//                    GraveStoneHelper.dropBlock(level, pos, state);
                }
            }
        }
        super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public InteractionResult use(
            BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        var te = (GraveStoneBlockEntity) level.getBlockEntity(pos);

        if (te != null) {
//            if (player.getInventory().getCurrentItem() != null) {
//                var item = player.getInventory().getCurrentItem();
//                if (item.getItem().getToolClasses(item).contains("shovel")) {
//                    if (!level.isClientSide()) {
//                        if (te.canBeLooted(player)) {
//                            player.openGui(ModGraveStone.INSTANCE, GuiHandler.GRAVE_INVENTORY_GUI_ID, level, pos.getX(), pos.getY(), pos.getZ());
//                            GRAVE_LOGGER.info(player.getName() + " open grave inventory at " + pos.getX() + "/" + pos.getY() + "/" + pos.getZ());
//                            GraveStoneHelper.replaceGround(level, pos.below());
//                        } else {
//                            player.sendMessage(new TextComponentTranslation("grave.cant_be_looted").setStyle(new Style().setColor(TextFormatting.RED)));
//                        }
//                    }
//                    return InteractionResult.SUCCESS;
//                } else {
////                    if (te.isMossy()) {
////                        if (item.getItem() instanceof ShearsItem) {
////                            if (!level.isClientSide()) {
////                                GraveInventory.dropItem(new ItemStack(Blocks.VINE, 1), level, pos);
////                            }
////                            te.setMossy(false);
////                            return InteractionResult.SUCCESS;
////                        }
////                    } else {
////                        if (Block.getBlockFromItem(item.getItem()) instanceof VineBlock && te.canBeMossy()) {
////                            te.setMossy(true);
////                            player.getInventory().getCurrentItem().setCount(player.getInventory().getCurrentItem().getCount() - 1);
////                            return InteractionResult.SUCCESS;
////                        }
////                    }
////                    if (te.hasFlower()) {
////                        if (item.getItem() instanceof ShearsItem) {
////                            if (!level.isClientSide()) {
////                                te.dropFlower();
////                            }
////                            te.setFlower(null);
////                            return InteractionResult.SUCCESS;
////                        }
////                    } else {
////                        if (GraveStoneHelper.FLOWERS.contains(Block.getBlockFromItem(item.getItem())) &&
////                                GraveStoneHelper.canFlowerBePlaced(level, pos, item, te)) {
////                            te.setFlower(new ItemStack(item.getItem(), 1, item.getItemDamage()));
////                            player.getInventory().getCurrentItem().setCount(player.getInventory().getCurrentItem().getCount() - 1);
////                            return InteractionResult.SUCCESS;
////                        }
////                    }
//                }
//            }
            if (level.isClientSide()) {
                String deathMessageJson = te.getDeathMessageJson();
                if (StringUtils.isNoneBlank(deathMessageJson)) {
                    player.displayClientMessage(Component.Serializer.fromJson(deathMessageJson), false);
                }

//                if (deathText.length() != 0) {
//
//                    if (te.getAge() > 0) {
//                        String ageStr = ModGraveStone.proxy.getLocalizedString("item.grave.age") +
//                                " " +
//                                te.getAge() +
//                                " " +
//                                ModGraveStone.proxy.getLocalizedString("item.grave.days");
//                        player.sendMessage(new TextComponentTranslation(ageStr));
//                    }
//                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);

        if (!level.isClientSide) {
            GraveStoneHelper.replaceGround(level, pos.below());
        }
    }

    /**
     * ejects contained items into the level, and notifies neighbours of an
     * update, as appropriate
     */
    @Override
    public void onRemove(BlockState state1, Level level, BlockPos pos, BlockState state2, boolean xz) {
        var blockEntity = level.getBlockEntity(pos);

        if (blockEntity != null && blockEntity instanceof GraveStoneBlockEntity graveEntity) {
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("Grave destroyed. Going to drop all stored items");
            }
            graveEntity.getInventory().dropAllItems();
        }

        GraveInventory.dropItem(GraveStoneHelper.getBlockItemStack(level, pos, state1), level, pos);

        super.onRemove(state1, level, pos, state2, xz);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GraveStoneBlockEntity(pos, state);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            var belowPos = pos.below();
            if (!level.getBlockState(belowPos).isFaceSturdy(level, belowPos, Direction.UP)) {
                var blockEntity = level.getBlockEntity(pos);
                if (blockEntity != null && blockEntity instanceof GraveStoneBlockEntity graveEntity) {
                    if (graveEntity.canBeLooted(null)) {
                        GraveStoneHelper.dropBlockWithoutInfo(level, graveEntity);
                        level.removeBlock(pos, false);
                    }
                }
            }
        }
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        if (!level.isClientSide) {
            var be = level.getBlockEntity(pos);
            if (be instanceof GraveStoneBlockEntity grave && !grave.canBeLooted(player)) {
                player.displayClientMessage(
                        Component.translatable("grave.cant_be_looted")
                                .withStyle(ChatFormatting.RED),
                        true);
                return;
            }
        }
        super.attack(state, level, pos, player);
    }

//TODO
//    @Override
//    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, Level level, BlockPos pos, Player player) {
//        var itemStack = new ItemStack(this.asItem(), 1);
//
//        if (level.getBlockEntity(pos) instanceof GraveStoneBlockEntity grave) {
//            if (itemStack != null) {
//                var tag = new CompoundTag();
//
//                itemStack.setTag(tag);
//                if (grave.isSwordGrave()) {
//                    GraveStoneHelper.addSwordInfo(tag, grave.getSword());
//                }
//            }
//        }
//        return itemStack;
//    }


    //TODO
//    /**
//     * A randomly called display update to be able to add particles or other
//     * items for display
//     */
//    @Override
//    public void updateTick(Level level, BlockPos pos, IBlockState state, Random random) {
//        if (GSConfigs.REMOVE_EMPTY_GRAVES.get()) {
//            if (!level.isClientSide()) {
//                GraveStoneBlockEntity tileEntity = (GraveStoneBlockEntity) level.getBlockEntity(pos);
//                if (tileEntity != null) {
//                    if (!tileEntity.isSwordGrave() && tileEntity.isEmpty()) {
//                        if (GSConfigs.SHOW_GRAVE_REMOVAL_MESSAGES.get()) {
//                            GRAVE_LOGGER.info("Remove empty grave at " + pos.getX() + "/" + pos.getY() + "/" + pos.getZ());
//                        }
//
//                        level.removeTileEntity(pos);
//                        level.setBlockToAir(pos);
//                    }
//                }
//            }
//        }
//    }

    @Override
    public boolean canEntityDestroy(BlockState state, BlockGetter blockGetter, BlockPos pos, Entity entity) {
        return false;
    }

}
