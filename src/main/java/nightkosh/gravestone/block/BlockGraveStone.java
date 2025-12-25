package nightkosh.gravestone.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ToolActions;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block_entity.GraveStoneBlockEntity;
import nightkosh.gravestone.core.config.GSConfigs;
import nightkosh.gravestone.gui.container.GraveInventory;
import nightkosh.gravestone.helper.GraveStoneHelper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

import static nightkosh.gravestone.ModGraveStone.GRAVE_LOGGER;
import static nightkosh.gravestone.ModGraveStone.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGraveStone extends BaseEntityBlock {

    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<FlowerType> FLOWER = EnumProperty.create("flower", FlowerType.class);

    public static final MapCodec<BlockGraveStone> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    EnumGraveType.CODEC.fieldOf("grave_type").forGetter(b -> b.graveType),
                    EnumGraveMaterial.CODEC.fieldOf("material").forGetter(b -> b.material)
            ).apply(instance, BlockGraveStone::new)
    );

    public final EnumGraveMaterial material;
    public final EnumGraveType graveType;

    public BlockGraveStone(EnumGraveType graveType, EnumGraveMaterial material) {
        super(BlockBehaviour.Properties.of()
                .sound(SoundType.STONE)
                .strength(1.5F, 6)
                .explosionResistance(Float.MAX_VALUE)
                .requiresCorrectToolForDrops()
                .noCollission());
        this.graveType = graveType;
        this.material = material;
        //TODO
//        this.setTickRandomly(GSConfigs.REMOVE_EMPTY_GRAVES.get());

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(FLOWER, FlowerType.NONE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, FLOWER);
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
    public void setPlacedBy(Level level, @Nonnull BlockPos pos, @Nonnull BlockState state,
                            LivingEntity placer, @Nonnull ItemStack stack) {
        if (!level.isClientSide) {
            level.setBlock(pos, state, 2);
            var blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof GraveStoneBlockEntity grave) {
                var data = stack.get(DataComponents.CUSTOM_DATA);
                if (data != null) {
                    var tag = data.getUnsafe();
                    if (tag != null) {
                        if (tag.contains("deathMessageJson")) {
                            grave.setDeathMessageJson(tag.getString("deathMessageJson"));
                        }

                        grave.setAge(tag.getInt("Age"));
                        grave.setPurified(tag.getBoolean("Purified"));

                        if (tag.contains("Sword")) {
                            var sword = ItemStack.parse(level.registryAccess(), tag.getCompound("Sword")).get();
                            grave.setSword(sword);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canSurvive(@Nonnull BlockState state, LevelReader level, BlockPos pos) {
        return GraveStoneHelper.canPlaceBlockAt(level.getBlockState(pos.below()));
    }

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

    private static final VoxelShape OBELISK_SOUTH = Block.box(6, 0, 2, 10, 15, 5);
    private static final VoxelShape OBELISK_NORTH = Block.box(6, 0, 11, 10, 15, 14);
    private static final VoxelShape OBELISK_EAST = Block.box(2, 0, 6, 5, 15, 10);
    private static final VoxelShape OBELISK_WEST = Block.box(11, 0, 6, 14, 15, 10);

    private static final VoxelShape GP_NORTH_SOUTH = Block.box(2, 0, 1, 14, 1, 15);
    private static final VoxelShape GP_EAST_WEST = Block.box(1, 0, 2, 15, 1, 14);
    //TODO
    private static final VoxelShape SWORD_SOUTH_NORTH = Block.box(0.375F, 0, 0.4375F, 0.625F, 0.9F, 0.5625F);
    private static final VoxelShape SWORD_EAST_WEST = Block.box(0.4375F, 0, 0.375F, 0.5625F, 0.9F, 0.625F);

    @Nonnull
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
            case OBELISK -> switch (facing) {
                case SOUTH -> OBELISK_SOUTH;
                case EAST -> OBELISK_EAST;
                case WEST -> OBELISK_WEST;
                case NORTH -> OBELISK_NORTH;
                default -> OBELISK_NORTH;
            };
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

    @Nonnull
    @Override
    public BlockState playerWillDestroy(Level level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull Player player) {
        if (!level.isClientSide) {
            player.causeFoodExhaustion(0.025F);

            if (!level.isClientSide() && !level.restoringBlockSnapshots) {
                if (level.getBlockEntity(pos) instanceof GraveStoneBlockEntity grave && grave.canBeLooted(player)) {
                    GraveStoneHelper.spawnMob(level, pos);

                    var flower = state.getValue(FLOWER);
                    if (flower != FlowerType.NONE) {
                        GraveInventory.dropItem(new ItemStack(Items.POPPY), level, pos);
                    }
                }
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Nonnull
    @Override
    public ItemInteractionResult useItemOn(
            @Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos,
            @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        if (level.getBlockEntity(pos) instanceof GraveStoneBlockEntity grave) {
            if (!level.isClientSide()) {
                if (stack.canPerformAction(ToolActions.SHOVEL_DIG)) {
                    if (grave.canBeLooted(player)) {
                        player.openMenu(grave, pos);
                        GRAVE_LOGGER.info("{} open grave inventory at {}", player.getScoreboardName(), pos.toShortString());
                        GraveStoneHelper.replaceGround(level, pos.below());
                        return ItemInteractionResult.SUCCESS;
                    } else {
                        player.displayClientMessage(
                                Component.translatable("grave.cant_be_looted")
                                        .withStyle(ChatFormatting.RED), false);
                        return ItemInteractionResult.FAIL;
                    }
                } else {
                    var held = player.getItemInHand(hand);
                    var flower = FlowerType.fromItem(held);
                    var flowerType = state.getValue(FLOWER);
                    if (flowerType != FlowerType.NONE) {
                        if (stack.getItem() instanceof ShearsItem) {
                            GraveInventory.dropItem(new ItemStack(flowerType.getFlower()), level, pos);
                            level.setBlock(pos, state.setValue(FLOWER, FlowerType.NONE), 2);
                            return ItemInteractionResult.SUCCESS;
                        }
                    } else if (flower != FlowerType.NONE &&
                            GraveStoneHelper.canFlowerBePlaced(level, pos, stack, grave)) {
                        level.setBlock(pos, state.setValue(FLOWER, flower), 2);//.sendBlockUpdated()
                        if (!player.getAbilities().instabuild) {
                            held.shrink(1);
                        }
                        return ItemInteractionResult.SUCCESS;
                    }
                }
            } else {
                if (player.getMainHandItem() == null || ItemStack.EMPTY.equals(player.getMainHandItem()) ||
                        !stack.canPerformAction(ToolActions.SHOVEL_DIG)) {
                    String deathMessageJson = grave.getDeathMessageJson();
                    if (StringUtils.isNoneBlank(deathMessageJson)) {
                        player.displayClientMessage(Component.Serializer.fromJson(
                                        deathMessageJson,
                                        Minecraft.getInstance().level.registryAccess()),
                                false);
                    }

                    if (grave.getAge() > 0) {
                        player.displayClientMessage(
                                Component.translatable("item.grave.age")
                                        .append(" " + grave.getAge() + " ")
                                        .append(Component.translatable("item.grave.days")), false);
                    }

                    return ItemInteractionResult.SUCCESS;
                }
            }
        }

        return ItemInteractionResult.FAIL;
    }

    @Override
    public void onPlace(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos,
                        @Nonnull BlockState oldState, boolean isMoving) {
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
    public void onRemove(BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, BlockState newState, boolean xz) {
        if (state.getBlock() != newState.getBlock()) {
            var blockEntity = level.getBlockEntity(pos);

            if (blockEntity != null && blockEntity instanceof GraveStoneBlockEntity graveEntity) {
                if (GSConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("Grave destroyed. Going to drop all stored items");
                }
                graveEntity.getInventory().dropAllItems();
            }

            GraveInventory.dropItem(GraveStoneHelper.getBlockItemStack(level, pos, state), level, pos);
        }
        super.onRemove(state, level, pos, newState, xz);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new GraveStoneBlockEntity(pos, state);
    }

    @Override
    public void neighborChanged(@Nonnull BlockState state, Level level, @Nonnull BlockPos pos,
                                @Nonnull Block neighborBlock, @Nonnull BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            var belowPos = pos.below();
            if (!level.getBlockState(belowPos).isFaceSturdy(level, belowPos, Direction.UP)) {
                if (level.getBlockEntity(pos) instanceof GraveStoneBlockEntity graveEntity) {
                    if (graveEntity.canBeLooted(null)) {
                        GraveStoneHelper.dropBlockWithoutInfo(level, graveEntity);
                        level.removeBlock(pos, false);
                    }
                }
            }
        }
    }

    @Override
    public void attack(@Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player) {
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
//                itemStack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
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
    public boolean canEntityDestroy(@Nonnull BlockState state, @Nonnull BlockGetter blockGetter,
                                    @Nonnull BlockPos pos, @Nonnull Entity entity) {
        return false;
    }

    @Nonnull
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

}
