package nightkosh.gravestone.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block_entity.GraveStoneBlockEntity;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.helper.GraveStoneHelper;
import nightkosh.gravestone.inventory.GraveInventory;
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
                .strength(-1, Float.MAX_VALUE)//explosion protection - TODO test
                .noCollission());
        this.graveType = graveType;
        this.material = material;
        //TODO
//        this.setSoundType(SoundType.STONE);
//        this.setHardness(0.5F);
//        this.setResistance(5);
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
            default -> "block.gravestone.grave_stone";
        };
    }

    /**
     * Called when the block is placed in the level
     */
    //TODO
//    @Override
//    public void onBlockPlacedBy(Level level, BlockPos pos, IBlockState state, LivingEntity player, ItemStack itemStack) {
//        GraveStoneHelper.replaceGround(level, pos.below());
//
//        var enumfacing = EnumFacing.getHorizontal(Mth.floor((double) (player.getYRot() * 4 / 360F) + 0.5D) & 3).getOpposite();
//        state = state.withProperty(FACING, enumfacing);
//        level.setBlockState(pos, state, 2);
//        var blockEntity = (GraveStoneBlockEntity) level.getBlockEntity(pos);
//
//        if (blockEntity != null) {
//            if (itemStack.hasTag()) {
//                var tag = itemStack.getTag();
//                blockEntity.setGraveType(itemStack.getDamageValue());//TODO no damage
//
//                if (tag.contains("isLocalized") && tag.getBoolean("isLocalized")) {
//                    blockEntity.getDeathTextComponent().setLocalized();
//
//                    if (tag.contains("name") && tag.contains("KillerName")) {
//                        blockEntity.getDeathTextComponent().setName(tag.getString("name"));
//                        blockEntity.getDeathTextComponent().setKillerName(tag.getString("KillerName"));
//                    }
//                }
//
//                blockEntity.getDeathTextComponent().setDeathText(tag.getString("DeathText"));
//
//                blockEntity.setAge(tag.getInt("Age"));
//
//                blockEntity.setEnchanted(tag.getBoolean("Enchanted"));
//
//                blockEntity.setMossy(tag.getBoolean("Mossy"));
//
//                blockEntity.setPurified(tag.getBoolean("Purified"));
//
//                if (tag.contains("Sword")) {
//                    var sword = new ItemStack(tag.get("Sword"));
//                    blockEntity.setSword(sword);
//                    if (sword.isEnchanted()) {
//                        blockEntity.setEnchanted(true);
//                    }
//                }
//            }
//        }
//    }
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

    private static final VoxelShape CC_NORTH_SOUTH = Block.box(0.125F, 0, 0.35F, 0.875F, 1.3F, 0.65F);
    private static final VoxelShape CC_EAST_WEST = Block.box(0.35F, 0, 0.125F, 0.65F, 1.3F, 0.875F);

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
                case EAST, WEST -> CC_EAST_WEST;
                case SOUTH, NORTH -> CC_NORTH_SOUTH;
                default -> CC_NORTH_SOUTH;
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
//
//    /**
//     * Called when the block is attempted to be harvested
//     */
//    @Override
//    public void onBlockHarvested(Level level, BlockPos pos, IBlockState state, Player player) {
//        player.causeFoodExhaustion(0.025F);
//
//        if (!level.isClientSide() && !level.restoringBlockSnapshots) {
//            var tileEntity = (GraveStoneBlockEntity) level.getBlockEntity(pos);
//            if (tileEntity != null && tileEntity.canBeLooted(player)) {
//                GraveStoneHelper.spawnMob(level, pos);
//
//                if (tileEntity.hasFlower()) {
//                    tileEntity.dropFlower();
//                }
//
//                GraveStoneHelper.dropBlock(level, pos, state);
//            }
//        }
//    }

//    @Override
//    public void onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest) {
//        if (!level.isClientSide) {
//            GraveStoneHelper.spawnMob(level, pos);
//        }
//    }

//    @Override
//    public boolean onBlockActivated(Level level, BlockPos pos, IBlockState state, Player player,
//                                    EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
//        var te = (GraveStoneBlockEntity) level.getBlockEntity(pos);
//
//        if (te != null) {
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
//                    return false;
//                } else {
//                    if (te.isMossy()) {
//                        if (item.getItem() instanceof ShearsItem) {
//                            if (!level.isClientSide()) {
//                                GraveInventory.dropItem(new ItemStack(Blocks.VINE, 1), level, pos);
//                            }
//                            te.setMossy(false);
//                            return false;
//                        }
//                    } else {
//                        if (Block.getBlockFromItem(item.getItem()) instanceof VineBlock && te.canBeMossy()) {
//                            te.setMossy(true);
//                            player.getInventory().getCurrentItem().setCount(player.getInventory().getCurrentItem().getCount() - 1);
//                            return true;
//                        }
//                    }
//                    if (te.hasFlower()) {
//                        if (item.getItem() instanceof ShearsItem) {
//                            if (!level.isClientSide()) {
//                                te.dropFlower();
//                            }
//                            te.setFlower(null);
//                            return false;
//                        }
//                    } else {
//                        if (GraveStoneHelper.FLOWERS.contains(Block.getBlockFromItem(item.getItem())) &&
//                                GraveStoneHelper.canFlowerBePlaced(level, pos, item, te)) {
//                            te.setFlower(new ItemStack(item.getItem(), 1, item.getItemDamage()));
//                            player.getInventory().getCurrentItem().setCount(player.getInventory().getCurrentItem().getCount() - 1);
//                            return true;
//                        }
//                    }
//                }
//            }
//            if (level.isClientSide()) {
//                String name;
//                String deathText;
//                String killerName;
//                deathText = te.getDeathTextComponent().getDeathText();
//
//                if (deathText.length() != 0) {
//                    if (te.getDeathTextComponent().isLocalized()) {
//                        name = ModGraveStone.proxy.getLocalizedEntityName(te.getDeathTextComponent().getName());
//                        killerName = ModGraveStone.proxy.getLocalizedEntityName(te.getDeathTextComponent().getKillerName());
//
//                        if (killerName.length() == 0) {
//                            player.sendMessage(new TextComponentTranslation(deathText, new Object[]{name}));
//                        } else {
//                            player.sendMessage(new TextComponentTranslation(deathText, new Object[]{name, killerName}));
//                        }
//                    } else {
//                        player.sendMessage(new TextComponentTranslation(deathText));
//                    }
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
//            }
//        }
//
//        return false;
//    }
//
//    @Override
//    public void onBlockAdded(Level level, BlockPos pos, IBlockState state) {
//        super.onBlockAdded(level, pos, state);
//        GraveStoneHelper.replaceGround(level, pos.below());
//    }

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
//
//    @Override
//    public boolean removedByPlayer(IBlockState state, Level level, BlockPos pos, Player player, boolean willHarvest) {
//        GraveStoneBlockEntity te = (GraveStoneBlockEntity) level.getBlockEntity(pos);
//        if (te != null && !te.canBeLooted(player)) {
//            player.sendMessage(new TextComponentTranslation("grave.cant_be_looted").setStyle(new Style().setColor(TextFormatting.RED)));
//            return false;
//        }
//        return super.removedByPlayer(state, level, pos, player, willHarvest);
//    }
//
//
//    @Override
//    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, Level level, BlockPos pos, Player player) {
//        var itemStack = new ItemStack(Item.getItemFromBlock(this), 1);
//        var tileEntity = (GraveStoneBlockEntity) level.getBlockEntity(pos);
//
//        if (tileEntity != null) {
//            if (itemStack != null) {
//                var tag = new CompoundTag();
//                itemStack.setDamageValue(tileEntity.getGraveTypeNum());
//                tag.putBoolean("Mossy", tileEntity.isMossy());
//
//                itemStack.setTag(tag);
//                if (tileEntity.isSwordGrave()) {
//                    GraveStoneHelper.addSwordInfo(tag, tileEntity.getSword());
//                }
//            }
//        }
//        return itemStack;
//    }
//
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
//
//    @Override
//    public boolean canEntityDestroy(IBlockState state, IBlockAccess blockAccess, BlockPos pos, Entity entity) {
//        return false;
//    }

}
