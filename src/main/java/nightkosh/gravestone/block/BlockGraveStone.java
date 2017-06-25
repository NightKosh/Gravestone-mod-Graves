package nightkosh.gravestone.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockVine;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone.ModGraveStone;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.GuiHandler;
import nightkosh.gravestone.core.Tabs;
import nightkosh.gravestone.core.logger.GSLogger;
import nightkosh.gravestone.helper.GraveGenerationHelper;
import nightkosh.gravestone.helper.GraveStoneHelper;
import nightkosh.gravestone.inventory.GraveInventory;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        this.isBlockContainer = true;
        this.setSoundType(SoundType.STONE);
        this.setHardness(0.5F);
        this.setResistance(5);
        this.setCreativeTab(Tabs.gravesTab);
        this.setTickRandomly(Config.removeEmptyGraves);
        this.setRegistryName("GSGraveStone");
    }

    /**
     * Called when the block is placed in the world
     */
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack itemStack) {
        GraveStoneHelper.replaceGround(world, pos.down());

        EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor((double) (player.rotationYaw * 4 / 360F) + 0.5D) & 3).getOpposite();
        state = state.withProperty(FACING, enumfacing);
        world.setBlockState(pos, state, 2);
        TileEntityGraveStone tileEntity = (TileEntityGraveStone) world.getTileEntity(pos);

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
     * coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return GraveStoneHelper.canPlaceBlockAt(world, pos.down());
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    //TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Create constants !!!!!!!!!!!!!!!!!!!!!!!!!!
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess access, BlockPos pos) {
        EnumFacing facing = (EnumFacing) access.getBlockState(pos).getValue(FACING);
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
                        return new AxisAlignedBB(0.125, 0, 0.0625, 0.875, 0.9375, 0.1875);
                    case NORTH:
                        return new AxisAlignedBB(0.125F, 0, 0.8125F, 0.875F, 0.9375F, 0.9375F);
                    case EAST:
                        return new AxisAlignedBB(0.0625F, 0, 0.125F, 0.1875F, 0.9375F, 0.875F);
                    case WEST:
                        return new AxisAlignedBB(0.8125F, 0, 0.125F, 0.9375F, 0.9375F, 0.875F);
                }
                break;
            case CROSS:
                switch (facing) {
                    case SOUTH:
                        return new AxisAlignedBB(0.125F, 0, 0.0625F, 0.875F, 1, 0.1875F);
                    case NORTH:
                        return new AxisAlignedBB(0.125F, 0, 0.8125F, 0.875F, 1, 0.9375F);
                    case EAST:
                        return new AxisAlignedBB(0.0625F, 0, 0.125F, 0.1875F, 1, 0.875F);
                    case WEST:
                        return new AxisAlignedBB(0.8125F, 0, 0.125F, 0.9375F, 1, 0.875F);
                }
                break;
            case CELTIC_CROSS:
                switch (facing) {
                    case SOUTH:
                    case NORTH:
                        return new AxisAlignedBB(0.125F, 0, 0.35F, 0.875F, 1.3F, 0.65F);
                    case EAST:
                    case WEST:
                        return new AxisAlignedBB(0.35F, 0, 0.125F, 0.65F, 1.3F, 0.875F);
                }
                break;
            case OBELISK:
            case CREEPER_STATUE:
            case VILLAGER_STATUE:
                return new AxisAlignedBB(0.35F, 0, 0.35F, 0.65F, 0.92F, 0.65F);
            case HORIZONTAL_PLATE:
                switch (facing) {
                    case SOUTH:
                        return new AxisAlignedBB(0.09375F, 0, 0.0625F, 0.90625F, 0.0625F, 0.9375F);
                    case NORTH:
                        return new AxisAlignedBB(0.09375F, 0, 0.0625F, 0.90625F, 0.0625F, 0.9375F);
                    case EAST:
                        return new AxisAlignedBB(0.0625F, 0, 0.09375F, 0.9375F, 0.0625F, 0.90625F);
                    case WEST:
                        return new AxisAlignedBB(0.0625F, 0, 0.09375F, 0.9375F, 0.0625F, 0.90625F);
                }
                break;
            case DOG_STATUE:
                switch (facing) {
                    case SOUTH:
                        return new AxisAlignedBB(0.35F, 0, 0.3F, 0.6F, 0.5F, 0.9F);
                    case NORTH:
                        return new AxisAlignedBB(0.35F, 0, 0.7F, 0.6F, 0.5F, 0.1F);
                    case EAST:
                        return new AxisAlignedBB(0.3F, 0, 0.35F, 0.9F, 0.5F, 0.6F);
                    case WEST:
                        return new AxisAlignedBB(0.7F, 0, 0.35F, 0.1F, 0.5F, 0.6F);
                }
                break;
            case CAT_STATUE:
                switch (facing) {
                    case SOUTH:
                        return new AxisAlignedBB(0.43F, 0, 0.3F, 0.57F, 0.5F, 0.75F);
                    case NORTH:
                        return new AxisAlignedBB(0.43F, 0, 0.7F, 0.57F, 0.5F, 0.25F);
                    case EAST:
                        return new AxisAlignedBB(0.3F, 0, 0.43F, 0.75F, 0.5F, 0.57F);
                    case WEST:
                        return new AxisAlignedBB(0.7F, 0, 0.43F, 0.25F, 0.5F, 0.57F);
                }
                break;
            case STARVED_CORPSE:
            case WITHERED_CORPSE:
                return new AxisAlignedBB(0, 0, 0, 1, 0.3F, 1);
            case SWORD:
                switch (facing) {
                    case SOUTH:
                    case NORTH:
                        return new AxisAlignedBB(0.375F, 0, 0.4375F, 0.625F, 0.9F, 0.5625F);
                    case EAST:
                    case WEST:
                        return new AxisAlignedBB(0.4375F, 0, 0.375F, 0.5625F, 0.9F, 0.625F);
                }
                break;
            case HORSE_STATUE:
                switch (facing) {
                    case SOUTH:
                    case NORTH:
                        return new AxisAlignedBB(0.375F, 0, 0.275F, 0.625F, 0.85F, 0.725F);
                    case EAST:
                    case WEST:
                        return new AxisAlignedBB(0.275F, 0, 0.375F, 0.725F, 0.85F, 0.625F);
                }
                break;
        }
        return new AxisAlignedBB(0, 0, 0, 1, 1, 1);
    }

    /**
     * Called when the block is attempted to be harvested
     */
    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        player.addExhaustion(0.025F);

        if (!world.isRemote && !world.restoringBlockSnapshots) {
            TileEntityGraveStone tileEntity = (TileEntityGraveStone) world.getTileEntity(pos);
            if (tileEntity != null && tileEntity.canBeLooted(player.getUniqueID().toString())) {
                GraveStoneHelper.spawnMob(world, pos);

                if (tileEntity.hasFlower()) {
                    tileEntity.dropFlower();
                }

                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItemMainhand()) > 0) {
                    GraveStoneHelper.dropBlock(world, pos, state);
                } else {
                    GraveStoneHelper.dropBlockWithoutInfo(world, pos, state);
                }
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
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
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
    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
        GraveStoneHelper.spawnMob(world, pos);
    }

    @Override
    public float getExplosionResistance(Entity entity) {
        return 18000000F;
    }

    /**
     * Called when the block is destroyed by an explosion. Useful for allowing
     * the block to take into account tile entities, metadata, etc. when
     * exploded, before it is removed.
     */
    @Override
    public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntityGraveStone te = (TileEntityGraveStone) world.getTileEntity(pos);

        if (te != null) {
            if (player.inventory.getCurrentItem() != null) {
                ItemStack item = player.inventory.getCurrentItem();
                if (item.getItem().getToolClasses(item).contains("shovel")) {
                    if (!world.isRemote) {
                        if (te.canBeLooted(player.getUniqueID().toString())) {
                            player.openGui(ModGraveStone.instance, GuiHandler.GRAVE_INVENTORY_GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
                            GSLogger.logInfoGrave(player.getName() + " open grave inventory at " + pos.getX() + "/" + pos.getY() + "/" + pos.getZ());
                            GraveStoneHelper.replaceGround(world, pos.down());
                        } else {
                            player.sendMessage(new TextComponentTranslation("grave.cant_be_looted").setStyle(new Style().setColor(TextFormatting.RED)));
                        }
                    }
                    return false;
                } else {
                    if (te.isMossy()) {
                        if (item.getItem() instanceof ItemShears) {
                            if (!world.isRemote) {
                                GraveInventory.dropItem(new ItemStack(Blocks.VINE, 1), world, pos);
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
                            if (!world.isRemote) {
                                te.dropFlower();
                            }
                            te.setFlower(null);
                            return false;
                        }
                    } else {
                        if (GraveStoneHelper.FLOWERS.contains(Block.getBlockFromItem(item.getItem())) &&
                                GraveStoneHelper.canFlowerBePlaced(world, pos, item, te)) {
                            te.setFlower(new ItemStack(item.getItem(), 1, item.getItemDamage()));
                            player.inventory.getCurrentItem().setCount(player.inventory.getCurrentItem().getCount() - 1);
                            return true;
                        }
                    }
                }
            }
            if (world.isRemote) {
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
                        StringBuilder ageStr = new StringBuilder();
                        ageStr.append(ModGraveStone.proxy.getLocalizedString("item.grave.age"))
                                .append(" ")
                                .append(te.getAge())
                                .append(" ")
                                .append(ModGraveStone.proxy.getLocalizedString("item.grave.days"));
                        player.sendMessage(new TextComponentTranslation(ageStr.toString()));
                    }
                }
            }
        }

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityGraveStone(world);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);
        GraveStoneHelper.replaceGround(world, pos.down());
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an
     * update, as appropriate
     */
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityGraveStone tileEntity = (TileEntityGraveStone) world.getTileEntity(pos);

        if (tileEntity != null) {
            tileEntity.getInventory().dropAllItems();
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block,  BlockPos fromPos) {
        if (!world.isSideSolid(pos.down(), EnumFacing.DOWN, true)) {
            TileEntityGraveStone te = (TileEntityGraveStone) world.getTileEntity(pos);
            if (te != null) {
                if (te.canBeLooted("")) {
                    GraveStoneHelper.dropBlockWithoutInfo(te.getWorld(), pos, world.getBlockState(pos));
                    te.getWorld().setBlockToAir(pos);
                }
            }
        }
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        TileEntityGraveStone te = (TileEntityGraveStone) world.getTileEntity(pos);
        if (te != null && !te.canBeLooted(player.getUniqueID().toString())) {
            player.sendMessage(new TextComponentTranslation("grave.cant_be_looted").setStyle(new Style().setColor(TextFormatting.RED)));
            return false;
        }
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item gravestone, CreativeTabs tabs, NonNullList<ItemStack> list) {
        for (int i = 0; i < EnumGraves.values().length - 1; i++) {
            ItemStack stack = new ItemStack(gravestone, 1, i);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setBoolean("Purified", false);

            stack.setTagCompound(nbt);
            list.add(stack);
        }

        // custom swords
        for (Item sword : GraveGenerationHelper.swordsList) {
            list.add(GraveStoneHelper.getSwordAsGrave(gravestone, new ItemStack(sword, 1)));
        }
        for (Item sword : GraveGenerationHelper.swordsList) {
            try {
                ItemStack swordStack = new ItemStack(sword, 1);
                EnchantmentHelper.addRandomEnchantment(new Random(), swordStack, 5, true);

                ItemStack graveStoneStack = GraveStoneHelper.getSwordAsGrave(gravestone, swordStack);

                list.add(graveStoneStack);
            } catch (IllegalArgumentException exception) {
                GSLogger.logError("Can't create enchanted sword gravestone");
                exception.printStackTrace();
            }
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        ItemStack itemStack = new ItemStack(Item.getItemFromBlock(this), 1);
        TileEntityGraveStone tileEntity = (TileEntityGraveStone) world.getTileEntity(pos);

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
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
        if (Config.removeEmptyGraves) {
            if (!world.isRemote) {
                TileEntityGraveStone tileEntity = (TileEntityGraveStone) world.getTileEntity(pos);
                if (tileEntity != null) {
                    if (!tileEntity.isSwordGrave() && tileEntity.isEmpty()) {
                        if (Config.showGravesRemovingMessages) {
                            GSLogger.logInfoGrave("Remove empty grave at " + pos.getX() + "/" + pos.getY() + "/" + pos.getZ());
                        }

                        world.removeTileEntity(pos);
                        world.setBlockToAir(pos);
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

//    @Override
//    public ItemStack createStackedBlock(IBlockState state) {
//        return new ItemStack(Item.getItemFromBlock(this), 1);
//    }
}
