package nightkosh.gravestone.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import nightkosh.gravestone.ModGraveStone;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.core.GSBlocks;
import nightkosh.gravestone.core.GuiHandler;
import nightkosh.gravestone.helper.GraveGenerationHelper;
import nightkosh.gravestone.helper.GraveStoneHelper;
import nightkosh.gravestone.inventory.GraveInventory;
import nightkosh.gravestone.tileentity.GraveStoneBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static nightkosh.gravestone.ModGraveStone.LOGGER;
import static nightkosh.gravestone.ModGraveStone.GRAVE_LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGraveStone extends BaseEntityBlock {

    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public BlockGraveStone() {
        super(BlockBehaviour.Properties.of(Material.STONE)
                .strength(-1, Float.MAX_VALUE)//explosion protection - TODO test
                .noOcclusion());
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

    /**
     * Called when the block is placed in the level
     */
    //TODO
//    @Override
//    public void onBlockPlacedBy(Level level, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack itemStack) {
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
//    @Nullable
//    @Override
//    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
//        return null;
//    }
//
//    private static final AxisAlignedBB VP_SOUTH_BB = new AxisAlignedBB(0.125, 0, 0.0625, 0.875, 0.9375, 0.1875);
//    private static final AxisAlignedBB VP_NORTH_BB = new AxisAlignedBB(0.125F, 0, 0.8125F, 0.875F, 0.9375F, 0.9375F);
//    private static final AxisAlignedBB VP_EAST_BB = new AxisAlignedBB(0.0625F, 0, 0.125F, 0.1875F, 0.9375F, 0.875F);
//    private static final AxisAlignedBB VP_WEST_BB = new AxisAlignedBB(0.8125F, 0, 0.125F, 0.9375F, 0.9375F, 0.875F);
//    private static final AxisAlignedBB CROSS_SOUTH_BB = new AxisAlignedBB(0.125F, 0, 0.0625F, 0.875F, 1, 0.1875F);
//    private static final AxisAlignedBB CROSS_NORTH_BB = new AxisAlignedBB(0.125F, 0, 0.8125F, 0.875F, 1, 0.9375F);
//    private static final AxisAlignedBB CROSS_EAST_BB = new AxisAlignedBB(0.0625F, 0, 0.125F, 0.1875F, 1, 0.875F);
//    private static final AxisAlignedBB CROSS_WEST_BB = new AxisAlignedBB(0.8125F, 0, 0.125F, 0.9375F, 1, 0.875F);
//    private static final AxisAlignedBB CC_NORTH_SOUTH_BB = new AxisAlignedBB(0.125F, 0, 0.35F, 0.875F, 1.3F, 0.65F);
//    private static final AxisAlignedBB CC_EAST_WEST_BB = new AxisAlignedBB(0.35F, 0, 0.125F, 0.65F, 1.3F, 0.875F);
//    private static final AxisAlignedBB PL_STATUES_BB = new AxisAlignedBB(0.35F, 0, 0.35F, 0.65F, 0.92F, 0.65F);
//    private static final AxisAlignedBB HP_NORTH_SOUTH_BB = new AxisAlignedBB(0.09375F, 0, 0.0625F, 0.90625F, 0.0625F, 0.9375F);
//    private static final AxisAlignedBB HP_EAST_WEST_BB = new AxisAlignedBB(0.0625F, 0, 0.09375F, 0.9375F, 0.0625F, 0.90625F);
//    private static final AxisAlignedBB DOG_SOUTH_BB = new AxisAlignedBB(0.35F, 0, 0.3F, 0.6F, 0.5F, 0.9F);
//    private static final AxisAlignedBB DOG_NORTH_BB = new AxisAlignedBB(0.35F, 0, 0.7F, 0.6F, 0.5F, 0.1F);
//    private static final AxisAlignedBB DOG_EAST_BB = new AxisAlignedBB(0.3F, 0, 0.35F, 0.9F, 0.5F, 0.6F);
//    private static final AxisAlignedBB DOG_WEST_BB = new AxisAlignedBB(0.7F, 0, 0.35F, 0.1F, 0.5F, 0.6F);
//    private static final AxisAlignedBB CAT_SOUTH_BB = new AxisAlignedBB(0.43F, 0, 0.3F, 0.57F, 0.5F, 0.75F);
//    private static final AxisAlignedBB CAT_NORTH_BB = new AxisAlignedBB(0.43F, 0, 0.7F, 0.57F, 0.5F, 0.25F);
//    private static final AxisAlignedBB CAT_EAST_BB = new AxisAlignedBB(0.3F, 0, 0.43F, 0.75F, 0.5F, 0.57F);
//    private static final AxisAlignedBB CAT_WEST_BB = new AxisAlignedBB(0.7F, 0, 0.43F, 0.25F, 0.5F, 0.57F);
//    private static final AxisAlignedBB CORPSE_BB = new AxisAlignedBB(0, 0, 0, 1, 0.3F, 1);
//    private static final AxisAlignedBB SWORD_SOUTH_NORTH_BB = new AxisAlignedBB(0.375F, 0, 0.4375F, 0.625F, 0.9F, 0.5625F);
//    private static final AxisAlignedBB SWORD_EAST_WEST_BB = new AxisAlignedBB(0.4375F, 0, 0.375F, 0.5625F, 0.9F, 0.625F);
//    private static final AxisAlignedBB HS_SOUTH_NORTH_BB = new AxisAlignedBB(0.375F, 0, 0.275F, 0.625F, 0.85F, 0.725F);
//    private static final AxisAlignedBB HS_EAST_WEST_BB = new AxisAlignedBB(0.275F, 0, 0.375F, 0.725F, 0.85F, 0.625F);
//
//    @Override
//    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess access, BlockPos pos) {
//        if (state.getBlock() == GSBlocks.getGraveStone()) {
//            EnumFacing facing = state.getValue(FACING);
//            EnumGraveType graveType;
//            GraveStoneBlockEntity tileEntity = (GraveStoneBlockEntity) access.getBlockEntity(pos);
//
//            if (tileEntity != null) {
//                graveType = tileEntity.getGraveType().getGraveType();
//            } else {
//                graveType = EnumGraveType.VERTICAL_PLATE;
//            }
//
//            switch (graveType) {
//                case VERTICAL_PLATE:
//                    switch (facing) {
//                        case SOUTH:
//                            return VP_SOUTH_BB;
//                        case NORTH:
//                            return VP_NORTH_BB;
//                        case EAST:
//                            return VP_EAST_BB;
//                        case WEST:
//                            return VP_WEST_BB;
//                    }
//                    break;
//                case CROSS:
//                    switch (facing) {
//                        case SOUTH:
//                            return CROSS_SOUTH_BB;
//                        case NORTH:
//                            return CROSS_NORTH_BB;
//                        case EAST:
//                            return CROSS_EAST_BB;
//                        case WEST:
//                            return CROSS_WEST_BB;
//                    }
//                    break;
//                case CELTIC_CROSS:
//                    switch (facing) {
//                        case SOUTH:
//                        case NORTH:
//                            return CC_NORTH_SOUTH_BB;
//                        case EAST:
//                        case WEST:
//                            return CC_EAST_WEST_BB;
//                    }
//                    break;
//                case OBELISK:
//                case CREEPER_STATUE:
//                case VILLAGER_STATUE:
//                    return PL_STATUES_BB;
//                case HORIZONTAL_PLATE:
//                    switch (facing) {
//                        case SOUTH:
//                        case NORTH:
//                            return HP_NORTH_SOUTH_BB;
//                        case EAST:
//                        case WEST:
//                            return HP_EAST_WEST_BB;
//                    }
//                    break;
//                case DOG_STATUE:
//                    switch (facing) {
//                        case SOUTH:
//                            return DOG_SOUTH_BB;
//                        case NORTH:
//                            return DOG_NORTH_BB;
//                        case EAST:
//                            return DOG_EAST_BB;
//                        case WEST:
//                            return DOG_WEST_BB;
//                    }
//                    break;
//                case CAT_STATUE:
//                    switch (facing) {
//                        case SOUTH:
//                            return CAT_SOUTH_BB;
//                        case NORTH:
//                            return CAT_NORTH_BB;
//                        case EAST:
//                            return CAT_EAST_BB;
//                        case WEST:
//                            return CAT_WEST_BB;
//                    }
//                    break;
//                case STARVED_CORPSE:
//                case WITHERED_CORPSE:
//                    return CORPSE_BB;
//                case SWORD:
//                    switch (facing) {
//                        case SOUTH:
//                        case NORTH:
//                            return SWORD_SOUTH_NORTH_BB;
//                        case EAST:
//                        case WEST:
//                            return SWORD_EAST_WEST_BB;
//                    }
//                    break;
//                case HORSE_STATUE:
//                    switch (facing) {
//                        case SOUTH:
//                        case NORTH:
//                            return HS_SOUTH_NORTH_BB;
//                        case EAST:
//                        case WEST:
//                            return HS_EAST_WEST_BB;
//                    }
//                    break;
//            }
//        }
//        return new AxisAlignedBB(0, 0, 0, 1, 1, 1);
//    }
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
//    public BlockFaceShape getBlockFaceShape(IBlockAccess access, IBlockState state, BlockPos pos, EnumFacing facing) {
//        return BlockFaceShape.UNDEFINED;
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

        //TODO from othe method - drop grave block
        List<ItemStack> ret = new ArrayList<>();
        ret.add(GraveStoneHelper.getBlockItemStack(level, pos, state1));

        super.onRemove(state1, level, pos, state2, xz);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GraveStoneBlockEntity(pos, state);
    }

//    @Override
//    public void neighborChanged(IBlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos) {
//        if (!level.isSideSolid(pos.below(), EnumFacing.DOWN, true)) {
//            var blockEntity = (GraveStoneBlockEntity) level.getBlockEntity(pos);
//            if (blockEntity != null) {
//                if (blockEntity.canBeLooted(null)) {
//                    GraveStoneHelper.dropBlockWithoutInfo(blockEntity.getLevel(), pos, level.getBlockState(pos));
//                    blockEntity.getLevel().setBlockToAir(pos);
//                }
//            }
//        }
//    }
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
//    @Override
//    public int damageDropped(IBlockState state) {
//        return 0;
//    }
//
//    @Override
//    @SideOnly(MixinEnvironment.Side.CLIENT)
//    public void getSubBlocks(CreativeModeTab tabs, NonNullList<ItemStack> list) {
//        for (int i = 0; i < EnumGraves.values().length - 1; i++) {
//            var stack = new ItemStack(this, 1, i);
//            var tag = new CompoundTag();
//            tag.putBoolean("Purified", false);
//
//            stack.setTag(tag);
//            list.add(stack);
//        }
//
//        // custom swords
//        for (var sword : GraveGenerationHelper.swordsList) {
//            list.add(GraveStoneHelper.getSwordAsGrave(Item.getItemFromBlock(this), new ItemStack(sword, 1)));
//        }
//        for (var sword : GraveGenerationHelper.swordsList) {
//            try {
//                var swordStack = new ItemStack(sword);
//                EnchantmentHelper.addRandomEnchantment(new Random(), swordStack, 5, true);
//
//                ItemStack graveStoneStack = GraveStoneHelper.getSwordAsGrave(Item.getItemFromBlock(this), swordStack);
//
//                list.add(graveStoneStack);
//            } catch (IllegalArgumentException exception) {
//                LOGGER.error("Can't create enchanted sword gravestone");
//                exception.printStackTrace();
//            }
//        }
//    }
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
//    public IBlockState getStateFromMeta(int meta) {
//        var enumfacing = EnumFacing.getFront(meta);
//
//        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
//            enumfacing = EnumFacing.NORTH;
//        }
//
//        return this.getDefaultState().withProperty(FACING, enumfacing);
//    }
//
//
//    @Override
//    protected BlockStateContainer createBlockState() {
//        return new BlockStateContainer(this, new IProperty[]{FACING});
//    }
//
//    @Override
//    public boolean canEntityDestroy(IBlockState state, IBlockAccess blockAccess, BlockPos pos, Entity entity) {
//        return false;
//    }

}
