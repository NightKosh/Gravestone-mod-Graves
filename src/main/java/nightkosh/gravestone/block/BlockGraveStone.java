package nightkosh.gravestone.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import nightkosh.gravestone.ModGraveStone;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.GSBlock;
import nightkosh.gravestone.core.GSTabs;
import nightkosh.gravestone.core.GuiHandler;
import nightkosh.gravestone.helper.GraveGenerationHelper;
import nightkosh.gravestone.helper.GraveStoneHelper;
import nightkosh.gravestone.inventory.GraveInventory;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mojang.text2speech.Narrator.LOGGER;
import static nightkosh.gravestone.ModGraveStone.GRAVE_LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGraveStone extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockGraveStone() {
        super(Material.ROCK);
        this.setSoundType(SoundType.STONE);
        this.setHardness(0.5F);
        this.setResistance(5);
        this.setCreativeTab(GSTabs.GS_TAB);
        this.setTickRandomly(Config.removeEmptyGraves);
        this.setRegistryName("GSGraveStone");
    }

    /**
     * Called when the block is placed in the level
     */
    @Override
    public void onBlockPlacedBy(Level level, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack itemStack) {
        GraveStoneHelper.replaceGround(level, pos.down());

        var enumfacing = EnumFacing.getHorizontal(MathHelper.floor((double) (player.rotationYaw * 4 / 360F) + 0.5D) & 3).getOpposite();
        state = state.withProperty(FACING, enumfacing);
        level.setBlockState(pos, state, 2);
        TileEntityGraveStone tileEntity = (TileEntityGraveStone) level.getTileEntity(pos);

        if (tileEntity != null) {
            NBTTagCompound nbt = itemStack.getTagCompound();
            if (nbt != null) {
                tileEntity.setGraveType(itemStack.getItemDamage());

                if (nbt.hasKey("isLocalized") && nbt.getBoolean("isLocalized")) {
                    tileEntity.getDeathTextComponent().setLocalized();

                    if (nbt.hasKey("name") && nbt.hasKey("KillerName")) {
                        tileEntity.getDeathTextComponent().setName(nbt.getString("name"));
                        tileEntity.getDeathTextComponent().setKillerName(nbt.getString("KillerName"));
                    }
                }

                tileEntity.getDeathTextComponent().setDeathText(nbt.getString("DeathText"));

                tileEntity.setAge(nbt.getInteger("Age"));

                tileEntity.setEnchanted(nbt.getBoolean("Enchanted"));

                tileEntity.setMossy(nbt.getBoolean("Mossy"));

                tileEntity.setPurified(nbt.getBoolean("Purified"));

                if (nbt.hasKey("Sword")) {
                    ItemStack sword = new ItemStack(nbt.getCompoundTag("Sword"));
                    tileEntity.setSword(sword);
                    if (sword.isItemEnchanted()) {
                        tileEntity.setEnchanted(true);
                    }
                }
            }
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified
     * coordinates. Args: level, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(Level level, BlockPos pos) {
        return GraveStoneHelper.canPlaceBlockAt(level, pos.below());
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    private static final AxisAlignedBB VP_SOUTH_BB = new AxisAlignedBB(0.125, 0, 0.0625, 0.875, 0.9375, 0.1875);
    private static final AxisAlignedBB VP_NORTH_BB = new AxisAlignedBB(0.125F, 0, 0.8125F, 0.875F, 0.9375F, 0.9375F);
    private static final AxisAlignedBB VP_EAST_BB = new AxisAlignedBB(0.0625F, 0, 0.125F, 0.1875F, 0.9375F, 0.875F);
    private static final AxisAlignedBB VP_WEST_BB = new AxisAlignedBB(0.8125F, 0, 0.125F, 0.9375F, 0.9375F, 0.875F);
    private static final AxisAlignedBB CROSS_SOUTH_BB = new AxisAlignedBB(0.125F, 0, 0.0625F, 0.875F, 1, 0.1875F);
    private static final AxisAlignedBB CROSS_NORTH_BB = new AxisAlignedBB(0.125F, 0, 0.8125F, 0.875F, 1, 0.9375F);
    private static final AxisAlignedBB CROSS_EAST_BB = new AxisAlignedBB(0.0625F, 0, 0.125F, 0.1875F, 1, 0.875F);
    private static final AxisAlignedBB CROSS_WEST_BB = new AxisAlignedBB(0.8125F, 0, 0.125F, 0.9375F, 1, 0.875F);
    private static final AxisAlignedBB CC_NORTH_SOUTH_BB = new AxisAlignedBB(0.125F, 0, 0.35F, 0.875F, 1.3F, 0.65F);
    private static final AxisAlignedBB CC_EAST_WEST_BB = new AxisAlignedBB(0.35F, 0, 0.125F, 0.65F, 1.3F, 0.875F);
    private static final AxisAlignedBB PL_STATUES_BB = new AxisAlignedBB(0.35F, 0, 0.35F, 0.65F, 0.92F, 0.65F);
    private static final AxisAlignedBB HP_NORTH_SOUTH_BB = new AxisAlignedBB(0.09375F, 0, 0.0625F, 0.90625F, 0.0625F, 0.9375F);
    private static final AxisAlignedBB HP_EAST_WEST_BB = new AxisAlignedBB(0.0625F, 0, 0.09375F, 0.9375F, 0.0625F, 0.90625F);
    private static final AxisAlignedBB DOG_SOUTH_BB = new AxisAlignedBB(0.35F, 0, 0.3F, 0.6F, 0.5F, 0.9F);
    private static final AxisAlignedBB DOG_NORTH_BB = new AxisAlignedBB(0.35F, 0, 0.7F, 0.6F, 0.5F, 0.1F);
    private static final AxisAlignedBB DOG_EAST_BB = new AxisAlignedBB(0.3F, 0, 0.35F, 0.9F, 0.5F, 0.6F);
    private static final AxisAlignedBB DOG_WEST_BB = new AxisAlignedBB(0.7F, 0, 0.35F, 0.1F, 0.5F, 0.6F);
    private static final AxisAlignedBB CAT_SOUTH_BB = new AxisAlignedBB(0.43F, 0, 0.3F, 0.57F, 0.5F, 0.75F);
    private static final AxisAlignedBB CAT_NORTH_BB = new AxisAlignedBB(0.43F, 0, 0.7F, 0.57F, 0.5F, 0.25F);
    private static final AxisAlignedBB CAT_EAST_BB = new AxisAlignedBB(0.3F, 0, 0.43F, 0.75F, 0.5F, 0.57F);
    private static final AxisAlignedBB CAT_WEST_BB = new AxisAlignedBB(0.7F, 0, 0.43F, 0.25F, 0.5F, 0.57F);
    private static final AxisAlignedBB CORPSE_BB = new AxisAlignedBB(0, 0, 0, 1, 0.3F, 1);
    private static final AxisAlignedBB SWORD_SOUTH_NORTH_BB = new AxisAlignedBB(0.375F, 0, 0.4375F, 0.625F, 0.9F, 0.5625F);
    private static final AxisAlignedBB SWORD_EAST_WEST_BB = new AxisAlignedBB(0.4375F, 0, 0.375F, 0.5625F, 0.9F, 0.625F);
    private static final AxisAlignedBB HS_SOUTH_NORTH_BB = new AxisAlignedBB(0.375F, 0, 0.275F, 0.625F, 0.85F, 0.725F);
    private static final AxisAlignedBB HS_EAST_WEST_BB = new AxisAlignedBB(0.275F, 0, 0.375F, 0.725F, 0.85F, 0.625F);

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess access, BlockPos pos) {
        if (state.getBlock() == GSBlock.getGraveStone()) {
            EnumFacing facing = state.getValue(FACING);
            EnumGraveType graveType;
            TileEntityGraveStone tileEntity = (TileEntityGraveStone) access.getTileEntity(pos);

            if (tileEntity != null) {
                graveType = tileEntity.getGraveType().getGraveType();
            } else {
                graveType = EnumGraveType.VERTICAL_PLATE;
            }

            switch (graveType) {
                case VERTICAL_PLATE:
                    switch (facing) {
                        case SOUTH:
                            return VP_SOUTH_BB;
                        case NORTH:
                            return VP_NORTH_BB;
                        case EAST:
                            return VP_EAST_BB;
                        case WEST:
                            return VP_WEST_BB;
                    }
                    break;
                case CROSS:
                    switch (facing) {
                        case SOUTH:
                            return CROSS_SOUTH_BB;
                        case NORTH:
                            return CROSS_NORTH_BB;
                        case EAST:
                            return CROSS_EAST_BB;
                        case WEST:
                            return CROSS_WEST_BB;
                    }
                    break;
                case CELTIC_CROSS:
                    switch (facing) {
                        case SOUTH:
                        case NORTH:
                            return CC_NORTH_SOUTH_BB;
                        case EAST:
                        case WEST:
                            return CC_EAST_WEST_BB;
                    }
                    break;
                case OBELISK:
                case CREEPER_STATUE:
                case VILLAGER_STATUE:
                    return PL_STATUES_BB;
                case HORIZONTAL_PLATE:
                    switch (facing) {
                        case SOUTH:
                        case NORTH:
                            return HP_NORTH_SOUTH_BB;
                        case EAST:
                        case WEST:
                            return HP_EAST_WEST_BB;
                    }
                    break;
                case DOG_STATUE:
                    switch (facing) {
                        case SOUTH:
                            return DOG_SOUTH_BB;
                        case NORTH:
                            return DOG_NORTH_BB;
                        case EAST:
                            return DOG_EAST_BB;
                        case WEST:
                            return DOG_WEST_BB;
                    }
                    break;
                case CAT_STATUE:
                    switch (facing) {
                        case SOUTH:
                            return CAT_SOUTH_BB;
                        case NORTH:
                            return CAT_NORTH_BB;
                        case EAST:
                            return CAT_EAST_BB;
                        case WEST:
                            return CAT_WEST_BB;
                    }
                    break;
                case STARVED_CORPSE:
                case WITHERED_CORPSE:
                    return CORPSE_BB;
                case SWORD:
                    switch (facing) {
                        case SOUTH:
                        case NORTH:
                            return SWORD_SOUTH_NORTH_BB;
                        case EAST:
                        case WEST:
                            return SWORD_EAST_WEST_BB;
                    }
                    break;
                case HORSE_STATUE:
                    switch (facing) {
                        case SOUTH:
                        case NORTH:
                            return HS_SOUTH_NORTH_BB;
                        case EAST:
                        case WEST:
                            return HS_EAST_WEST_BB;
                    }
                    break;
            }
        }
        return new AxisAlignedBB(0, 0, 0, 1, 1, 1);
    }

    /**
     * Called when the block is attempted to be harvested
     */
    @Override
    public void onBlockHarvested(Level level, BlockPos pos, IBlockState state, Player player) {
        player.addExhaustion(0.025F);

        if (!level.isRemote && !level.restoringBlockSnapshots) {
            TileEntityGraveStone tileEntity = (TileEntityGraveStone) level.getTileEntity(pos);
            if (tileEntity != null && tileEntity.canBeLooted(player)) {
                GraveStoneHelper.spawnMob(level, pos);

                if (tileEntity.hasFlower()) {
                    tileEntity.dropFlower();
                }

                GraveStoneHelper.dropBlock(level, pos, state);
            }
        }
    }

    /**
     * This returns a complete list of items dropped from this block.
     */
    @Override
    public List<ItemStack> getDrops(IBlockAccess access, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> ret = new ArrayList<>();
        ret.add(GraveStoneHelper.getBlockItemStack(access, pos, state));
        return ret;
    }

    @Override
    public void harvestBlock(Level level, Player player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess access, IBlockState state, BlockPos pos, EnumFacing facing) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public void onBlockDestroyedByPlayer(Level level, BlockPos pos, IBlockState state) {
        GraveStoneHelper.spawnMob(level, pos);
    }

    @Override
    public float getExplosionResistance(Entity entity) {
        return Float.MAX_VALUE;
    }

    /**
     * Called when the block is destroyed by an explosion. Useful for allowing
     * the block to take into account tile entities, metadata, etc. when
     * exploded, before it is removed.
     */
    @Override
    public void onBlockExploded(Level level, BlockPos pos, Explosion explosion) {
    }

    @Override
    public boolean onBlockActivated(Level level, BlockPos pos, IBlockState state, Player player,
                                    EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntityGraveStone te = (TileEntityGraveStone) level.getTileEntity(pos);

        if (te != null) {
            if (player.inventory.getCurrentItem() != null) {
                ItemStack item = player.inventory.getCurrentItem();
                if (item.getItem().getToolClasses(item).contains("shovel")) {
                    if (!level.isRemote) {
                        if (te.canBeLooted(player)) {
                            player.openGui(ModGraveStone.INSTANCE, GuiHandler.GRAVE_INVENTORY_GUI_ID, level, pos.getX(), pos.getY(), pos.getZ());
                            GRAVE_LOGGER.info(player.getName() + " open grave inventory at " + pos.getX() + "/" + pos.getY() + "/" + pos.getZ());
                            GraveStoneHelper.replaceGround(level, pos.down());
                        } else {
                            player.sendMessage(new TextComponentTranslation("grave.cant_be_looted").setStyle(new Style().setColor(TextFormatting.RED)));
                        }
                    }
                    return false;
                } else {
                    if (te.isMossy()) {
                        if (item.getItem() instanceof ItemShears) {
                            if (!level.isRemote) {
                                GraveInventory.dropItem(new ItemStack(Blocks.VINE, 1), level, pos);
                            }
                            te.setMossy(false);
                            return false;
                        }
                    } else {
                        if (Block.getBlockFromItem(item.getItem()) instanceof BlockVine && te.canBeMossy()) {
                            te.setMossy(true);
                            player.inventory.getCurrentItem().setCount(player.inventory.getCurrentItem().getCount() - 1);
                            return true;
                        }
                    }
                    if (te.hasFlower()) {
                        if (item.getItem() instanceof ItemShears) {
                            if (!level.isRemote) {
                                te.dropFlower();
                            }
                            te.setFlower(null);
                            return false;
                        }
                    } else {
                        if (GraveStoneHelper.FLOWERS.contains(Block.getBlockFromItem(item.getItem())) &&
                                GraveStoneHelper.canFlowerBePlaced(level, pos, item, te)) {
                            te.setFlower(new ItemStack(item.getItem(), 1, item.getItemDamage()));
                            player.inventory.getCurrentItem().setCount(player.inventory.getCurrentItem().getCount() - 1);
                            return true;
                        }
                    }
                }
            }
            if (level.isRemote) {
                String name;
                String deathText;
                String killerName;
                deathText = te.getDeathTextComponent().getDeathText();

                if (deathText.length() != 0) {
                    if (te.getDeathTextComponent().isLocalized()) {
                        name = ModGraveStone.proxy.getLocalizedEntityName(te.getDeathTextComponent().getName());
                        killerName = ModGraveStone.proxy.getLocalizedEntityName(te.getDeathTextComponent().getKillerName());

                        if (killerName.length() == 0) {
                            player.sendMessage(new TextComponentTranslation(deathText, new Object[]{name}));
                        } else {
                            player.sendMessage(new TextComponentTranslation(deathText, new Object[]{name, killerName}));
                        }
                    } else {
                        player.sendMessage(new TextComponentTranslation(deathText));
                    }

                    if (te.getAge() > 0) {
                        String ageStr = ModGraveStone.proxy.getLocalizedString("item.grave.age") +
                                " " +
                                te.getAge() +
                                " " +
                                ModGraveStone.proxy.getLocalizedString("item.grave.days");
                        player.sendMessage(new TextComponentTranslation(ageStr));
                    }
                }
            }
        }

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(Level level, int var2) {
        return new TileEntityGraveStone(level);
    }

    @Override
    public void onBlockAdded(Level level, BlockPos pos, IBlockState state) {
        super.onBlockAdded(level, pos, state);
        GraveStoneHelper.replaceGround(level, pos.below());
    }

    /**
     * ejects contained items into the level, and notifies neighbours of an
     * update, as appropriate
     */
    @Override
    public void breakBlock(Level level, BlockPos pos, IBlockState state) {
        TileEntityGraveStone tileEntity = (TileEntityGraveStone) level.getTileEntity(pos);

        if (tileEntity != null) {
            tileEntity.getInventory().dropAllItems();
        }

        super.breakBlock(level, pos, state);
    }

    @Override
    public void neighborChanged(IBlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos) {
        if (!level.isSideSolid(pos.down(), EnumFacing.DOWN, true)) {
            TileEntityGraveStone te = (TileEntityGraveStone) level.getTileEntity(pos);
            if (te != null) {
                if (te.canBeLooted(null)) {
                    GraveStoneHelper.dropBlockWithoutInfo(te.getWorld(), pos, level.getBlockState(pos));
                    te.getWorld().setBlockToAir(pos);
                }
            }
        }
    }

    @Override
    public boolean removedByPlayer(IBlockState state, Level level, BlockPos pos, Player player, boolean willHarvest) {
        TileEntityGraveStone te = (TileEntityGraveStone) level.getTileEntity(pos);
        if (te != null && !te.canBeLooted(player)) {
            player.sendMessage(new TextComponentTranslation("grave.cant_be_looted").setStyle(new Style().setColor(TextFormatting.RED)));
            return false;
        }
        return super.removedByPlayer(state, level, pos, player, willHarvest);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs tabs, NonNullList<ItemStack> list) {
        for (int i = 0; i < EnumGraves.values().length - 1; i++) {
            ItemStack stack = new ItemStack(this, 1, i);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setBoolean("Purified", false);

            stack.setTagCompound(nbt);
            list.add(stack);
        }

        // custom swords
        for (Item sword : GraveGenerationHelper.swordsList) {
            list.add(GraveStoneHelper.getSwordAsGrave(Item.getItemFromBlock(this), new ItemStack(sword, 1)));
        }
        for (Item sword : GraveGenerationHelper.swordsList) {
            try {
                ItemStack swordStack = new ItemStack(sword, 1);
                EnchantmentHelper.addRandomEnchantment(new Random(), swordStack, 5, true);

                ItemStack graveStoneStack = GraveStoneHelper.getSwordAsGrave(Item.getItemFromBlock(this), swordStack);

                list.add(graveStoneStack);
            } catch (IllegalArgumentException exception) {
                LOGGER.error("Can't create enchanted sword gravestone");
                exception.printStackTrace();
            }
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, Level level, BlockPos pos, Player player) {
        ItemStack itemStack = new ItemStack(Item.getItemFromBlock(this), 1);
        TileEntityGraveStone tileEntity = (TileEntityGraveStone) level.getTileEntity(pos);

        if (tileEntity != null) {
            if (itemStack != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                itemStack.setItemDamage(tileEntity.getGraveTypeNum());
                nbt.setBoolean("Mossy", tileEntity.isMossy());

                itemStack.setTagCompound(nbt);
                if (tileEntity.isSwordGrave()) {
                    GraveStoneHelper.addSwordInfo(nbt, tileEntity.getSword());
                }
            }
        }
        return itemStack;
    }

    /**
     * A randomly called display update to be able to add particles or other
     * items for display
     */
    @Override
    public void updateTick(Level level, BlockPos pos, IBlockState state, Random random) {
        if (Config.removeEmptyGraves) {
            if (!level.isRemote) {
                TileEntityGraveStone tileEntity = (TileEntityGraveStone) level.getTileEntity(pos);
                if (tileEntity != null) {
                    if (!tileEntity.isSwordGrave() && tileEntity.isEmpty()) {
                        if (Config.showGravesRemovingMessages) {
                            GRAVE_LOGGER.info("Remove empty grave at " + pos.getX() + "/" + pos.getY() + "/" + pos.getZ());
                        }

                        level.removeTileEntity(pos);
                        level.setBlockToAir(pos);
                    }
                }
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess blockAccess, BlockPos pos, Entity entity) {
        return false;
    }

}
