package nightkosh.gravestone.block;

import nightkosh.gravestone.ModGraveStone;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.config.GSConfig;
import nightkosh.gravestone.core.GSGuiHandler;
import nightkosh.gravestone.core.GSTabs;
import nightkosh.gravestone.core.logger.GSLogger;
import nightkosh.gravestone.helper.GraveStoneHelper;
import nightkosh.gravestone.inventory.GraveInventory;
import nightkosh.gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSGraveStone extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public static final int[] TAB_PLAYER_GRAVES = {
            // vertical plates
            EnumGraves.WOODEN_VERTICAL_PLATE.ordinal(),
            EnumGraves.SANDSTONE_VERTICAL_PLATE.ordinal(),
            EnumGraves.RED_SANDSTONE_VERTICAL_PLATE.ordinal(),
            EnumGraves.STONE_VERTICAL_PLATE.ordinal(),
            EnumGraves.DIORITE_VERTICAL_PLATE.ordinal(),
            EnumGraves.ANDESITE_VERTICAL_PLATE.ordinal(),
            EnumGraves.GRANITE_VERTICAL_PLATE.ordinal(),
            EnumGraves.IRON_VERTICAL_PLATE.ordinal(),
            EnumGraves.GOLDEN_VERTICAL_PLATE.ordinal(),
            EnumGraves.DIAMOND_VERTICAL_PLATE.ordinal(),
            EnumGraves.EMERALD_VERTICAL_PLATE.ordinal(),
            EnumGraves.LAPIS_VERTICAL_PLATE.ordinal(),
            EnumGraves.REDSTONE_VERTICAL_PLATE.ordinal(),
            EnumGraves.OBSIDIAN_VERTICAL_PLATE.ordinal(),
            EnumGraves.QUARTZ_VERTICAL_PLATE.ordinal(),
            EnumGraves.PRIZMARINE_VERTICAL_PLATE.ordinal(),
            EnumGraves.ICE_VERTICAL_PLATE.ordinal(),
            // crosses
            EnumGraves.WOODEN_CROSS.ordinal(),
            EnumGraves.SANDSTONE_CROSS.ordinal(),
            EnumGraves.RED_SANDSTONE_CROSS.ordinal(),
            EnumGraves.STONE_CROSS.ordinal(),
            EnumGraves.DIORITE_CROSS.ordinal(),
            EnumGraves.ANDESITE_CROSS.ordinal(),
            EnumGraves.GRANITE_CROSS.ordinal(),
            EnumGraves.IRON_CROSS.ordinal(),
            EnumGraves.GOLDEN_CROSS.ordinal(),
            EnumGraves.DIAMOND_CROSS.ordinal(),
            EnumGraves.EMERALD_CROSS.ordinal(),
            EnumGraves.LAPIS_CROSS.ordinal(),
            EnumGraves.REDSTONE_CROSS.ordinal(),
            EnumGraves.OBSIDIAN_CROSS.ordinal(),
            EnumGraves.QUARTZ_CROSS.ordinal(),
            EnumGraves.PRIZMARINE_CROSS.ordinal(),
            EnumGraves.ICE_CROSS.ordinal(),
            // obelisks
            EnumGraves.WOODEN_OBELISK.ordinal(),
            EnumGraves.SANDSTONE_OBELISK.ordinal(),
            EnumGraves.RED_SANDSTONE_OBELISK.ordinal(),
            EnumGraves.STONE_OBELISK.ordinal(),
            EnumGraves.DIORITE_OBELISK.ordinal(),
            EnumGraves.ANDESITE_OBELISK.ordinal(),
            EnumGraves.GRANITE_OBELISK.ordinal(),
            EnumGraves.IRON_OBELISK.ordinal(),
            EnumGraves.GOLDEN_OBELISK.ordinal(),
            EnumGraves.DIAMOND_OBELISK.ordinal(),
            EnumGraves.EMERALD_OBELISK.ordinal(),
            EnumGraves.LAPIS_OBELISK.ordinal(),
            EnumGraves.REDSTONE_OBELISK.ordinal(),
            EnumGraves.OBSIDIAN_OBELISK.ordinal(),
            EnumGraves.QUARTZ_OBELISK.ordinal(),
            EnumGraves.PRIZMARINE_OBELISK.ordinal(),
            EnumGraves.ICE_OBELISK.ordinal(),
            // horisontal plates
            EnumGraves.WOODEN_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.SANDSTONE_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.RED_SANDSTONE_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.STONE_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.DIORITE_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.ANDESITE_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.GRANITE_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.IRON_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.GOLDEN_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.DIAMOND_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.EMERALD_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.LAPIS_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.REDSTONE_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.OBSIDIAN_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.QUARTZ_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.PRIZMARINE_HORIZONTAL_PLATE.ordinal(),
            EnumGraves.ICE_HORIZONTAL_PLATE.ordinal()
    };

    public static final int[] TAB_PETS_GRAVES = {
            // dogs graves
            EnumGraves.WOODEN_DOG_STATUE.ordinal(),
            EnumGraves.SANDSTONE_DOG_STATUE.ordinal(),
            EnumGraves.RED_SANDSTONE_DOG_STATUE.ordinal(),
            EnumGraves.STONE_DOG_STATUE.ordinal(),
            EnumGraves.DIORITE_DOG_STATUE.ordinal(),
            EnumGraves.ANDESITE_DOG_STATUE.ordinal(),
            EnumGraves.GRANITE_DOG_STATUE.ordinal(),
            EnumGraves.IRON_DOG_STATUE.ordinal(),
            EnumGraves.GOLDEN_DOG_STATUE.ordinal(),
            EnumGraves.DIAMOND_DOG_STATUE.ordinal(),
            EnumGraves.EMERALD_DOG_STATUE.ordinal(),
            EnumGraves.LAPIS_DOG_STATUE.ordinal(),
            EnumGraves.REDSTONE_DOG_STATUE.ordinal(),
            EnumGraves.OBSIDIAN_DOG_STATUE.ordinal(),
            EnumGraves.QUARTZ_DOG_STATUE.ordinal(),
            EnumGraves.PRIZMARINE_DOG_STATUE.ordinal(),
            EnumGraves.ICE_DOG_STATUE.ordinal(),
            // cats graves
            EnumGraves.WOODEN_CAT_STATUE.ordinal(),
            EnumGraves.SANDSTONE_CAT_STATUE.ordinal(),
            EnumGraves.RED_SANDSTONE_CAT_STATUE.ordinal(),
            EnumGraves.STONE_CAT_STATUE.ordinal(),
            EnumGraves.DIORITE_CAT_STATUE.ordinal(),
            EnumGraves.ANDESITE_CAT_STATUE.ordinal(),
            EnumGraves.GRANITE_CAT_STATUE.ordinal(),
            EnumGraves.IRON_CAT_STATUE.ordinal(),
            EnumGraves.GOLDEN_CAT_STATUE.ordinal(),
            EnumGraves.DIAMOND_CAT_STATUE.ordinal(),
            EnumGraves.EMERALD_CAT_STATUE.ordinal(),
            EnumGraves.LAPIS_CAT_STATUE.ordinal(),
            EnumGraves.REDSTONE_CAT_STATUE.ordinal(),
            EnumGraves.OBSIDIAN_CAT_STATUE.ordinal(),
            EnumGraves.QUARTZ_CAT_STATUE.ordinal(),
            EnumGraves.PRIZMARINE_CAT_STATUE.ordinal(),
            EnumGraves.ICE_CAT_STATUE.ordinal(),
            // horses graves
            EnumGraves.WOODEN_HORSE_STATUE.ordinal(),
            EnumGraves.SANDSTONE_HORSE_STATUE.ordinal(),
            EnumGraves.RED_SANDSTONE_HORSE_STATUE.ordinal(),
            EnumGraves.STONE_HORSE_STATUE.ordinal(),
            EnumGraves.DIORITE_HORSE_STATUE.ordinal(),
            EnumGraves.ANDESITE_HORSE_STATUE.ordinal(),
            EnumGraves.GRANITE_HORSE_STATUE.ordinal(),
            EnumGraves.IRON_HORSE_STATUE.ordinal(),
            EnumGraves.GOLDEN_HORSE_STATUE.ordinal(),
            EnumGraves.DIAMOND_HORSE_STATUE.ordinal(),
            EnumGraves.EMERALD_HORSE_STATUE.ordinal(),
            EnumGraves.LAPIS_HORSE_STATUE.ordinal(),
            EnumGraves.REDSTONE_HORSE_STATUE.ordinal(),
            EnumGraves.OBSIDIAN_HORSE_STATUE.ordinal(),
            EnumGraves.QUARTZ_HORSE_STATUE.ordinal(),
            EnumGraves.PRIZMARINE_HORSE_STATUE.ordinal(),
            EnumGraves.ICE_HORSE_STATUE.ordinal()
    };

    public BlockGSGraveStone() {
        super(Material.rock);
        this.isBlockContainer = true;
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(0.5F);
        this.setResistance(5);
        this.setCreativeTab(GSTabs.gravesTab);
        this.setTickRandomly(GSConfig.removeEmptyGraves);
    }

    /**
     * Called when the block is placed in the world
     */
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack itemStack) {
        GraveStoneHelper.replaceGround(world, pos.down());

        EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double((double) (player.rotationYaw * 4 / 360F) + 0.5D) & 3).getOpposite();
        state = state.withProperty(FACING, enumfacing);
        world.setBlockState(pos, state, 2);
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(pos);

        if (tileEntity != null) {
            NBTTagCompound nbt = itemStack.getTagCompound();
            if (nbt != null) {
                tileEntity.setGraveType(nbt.getInteger("Type"));

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

                if (nbt.hasKey("Sword")) {
                    tileEntity.setSword(ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("Sword")));
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

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
        return null;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
        EnumFacing facing = (EnumFacing) access.getBlockState(pos).getValue(FACING);
        EnumGraves.EnumGraveType graveType;
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) access.getTileEntity(pos);

        if (tileEntity != null) {
            graveType = tileEntity.getGraveType().getGraveType();
        } else {
            graveType = EnumGraves.EnumGraveType.VERTICAL_PLATE;
        }

        switch (graveType) {
            case VERTICAL_PLATE:
                switch (facing) {
                    case SOUTH:
                        this.setBlockBounds(0.125F, 0, 0.0625F, 0.875F, 0.9375F, 0.1875F);
                        break;
                    case NORTH:
                        this.setBlockBounds(0.125F, 0, 0.8125F, 0.875F, 0.9375F, 0.9375F);
                        break;
                    case EAST:
                        this.setBlockBounds(0.0625F, 0, 0.125F, 0.1875F, 0.9375F, 0.875F);
                        break;
                    case WEST:
                        this.setBlockBounds(0.8125F, 0, 0.125F, 0.9375F, 0.9375F, 0.875F);
                        break;
                }
                break;
            case CROSS:
                switch (facing) {
                    case SOUTH:
                        this.setBlockBounds(0.125F, 0, 0.0625F, 0.875F, 1, 0.1875F);
                        break;
                    case NORTH:
                        this.setBlockBounds(0.125F, 0, 0.8125F, 0.875F, 1, 0.9375F);
                        break;
                    case EAST:
                        this.setBlockBounds(0.0625F, 0, 0.125F, 0.1875F, 1, 0.875F);
                        break;
                    case WEST:
                        this.setBlockBounds(0.8125F, 0, 0.125F, 0.9375F, 1, 0.875F);
                        break;
                }
                break;
            case OBELISK:
                this.setBlockBounds(0.35F, 0, 0.35F, 0.65F, 0.92F, 0.65F);
                break;
            case HORIZONTAL_PLATE:
                switch (facing) {
                    case SOUTH:
                        this.setBlockBounds(0.09375F, 0, 0.0625F, 0.90625F, 0.0625F, 0.9375F);
                        break;
                    case NORTH:
                        this.setBlockBounds(0.09375F, 0, 0.0625F, 0.90625F, 0.0625F, 0.9375F);
                        break;
                    case EAST:
                        this.setBlockBounds(0.0625F, 0, 0.09375F, 0.9375F, 0.0625F, 0.90625F);
                        break;
                    case WEST:
                        this.setBlockBounds(0.0625F, 0, 0.09375F, 0.9375F, 0.0625F, 0.90625F);
                        break;
                }
                break;
            case DOG_STATUE:
                switch (facing) {
                    case SOUTH:
                        this.setBlockBounds(0.35F, 0, 0.3F, 0.6F, 0.5F, 0.9F);
                        break;
                    case NORTH:
                        this.setBlockBounds(0.35F, 0, 0.7F, 0.6F, 0.5F, 0.1F);
                        break;
                    case EAST:
                        this.setBlockBounds(0.3F, 0, 0.35F, 0.9F, 0.5F, 0.6F);
                        break;
                    case WEST:
                        this.setBlockBounds(0.7F, 0, 0.35F, 0.1F, 0.5F, 0.6F);
                        break;
                }
                break;
            case CAT_STATUE:
                switch (facing) {
                    case SOUTH:
                        this.setBlockBounds(0.43F, 0, 0.3F, 0.57F, 0.5F, 0.75F);
                        break;
                    case NORTH:
                        this.setBlockBounds(0.43F, 0, 0.7F, 0.57F, 0.5F, 0.25F);
                        break;
                    case EAST:
                        this.setBlockBounds(0.3F, 0, 0.43F, 0.75F, 0.5F, 0.57F);
                        break;
                    case WEST:
                        this.setBlockBounds(0.7F, 0, 0.43F, 0.25F, 0.5F, 0.57F);
                        break;
                }
                break;
            case SWORD:
                switch (facing) {
                    case SOUTH:
                    case NORTH:
                        this.setBlockBounds(0.375F, 0, 0.4375F, 0.625F, 0.9F, 0.5625F);
                        break;
                    case EAST:
                    case WEST:
                        this.setBlockBounds(0.4375F, 0, 0.375F, 0.5625F, 0.9F, 0.625F);
                        break;
                }
                break;
            case HORSE_STATUE:
                switch (facing) {
                    case SOUTH:
                    case NORTH:
                        this.setBlockBounds(0.375F, 0, 0.275F, 0.625F, 0.85F, 0.725F);
                        break;
                    case EAST:
                    case WEST:
                        this.setBlockBounds(0.275F, 0, 0.375F, 0.725F, 0.85F, 0.625F);
                        break;
                }
                break;
        }
    }

    /**
     * Called when the block is attempted to be harvested
     */
    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        player.addExhaustion(0.025F);

        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(pos);
        if (tileEntity != null && tileEntity.canBeLooted(player.getUniqueID().toString())) {
            GraveStoneHelper.spawnMob(world, pos);

            if (tileEntity.hasFlower()) {
                tileEntity.dropFlower();
            }

            if (EnchantmentHelper.getSilkTouchModifier(player)) {
                GraveStoneHelper.dropBlock(world, pos, state);
            } else {
                GraveStoneHelper.dropBlockWithoutInfo(world, pos, state);
            }
        }
    }

    /**
     * This returns a complete list of items dropped from this block.
     */
    @Override
    public List<ItemStack> getDrops(IBlockAccess access, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(GraveStoneHelper.getBlockItemStack(access, pos, state));
        return ret;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    //TODO
//    @Override
//    public int getRenderType() {
//        return GraveStoneConfig.graveRenderID;
//    }

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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntityGSGraveStone te = (TileEntityGSGraveStone) world.getTileEntity(pos);

        if (te != null) {
            if (player.inventory.getCurrentItem() != null) {
                ItemStack item = player.inventory.getCurrentItem();
                if (item.getItem().getToolClasses(item).contains("shovel")) {
                    if (!world.isRemote) {
                        if (te.canBeLooted(player.getUniqueID().toString())) {
                            player.openGui(ModGraveStone.instance, GSGuiHandler.GRAVE_INVENTORY_GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
                            GSLogger.logInfoGrave(player.getName() + " open grave inventory at " + pos.getX() + "/" + pos.getY() + "/" + pos.getZ());
                        } else {
                            player.addChatComponentMessage(new ChatComponentTranslation("grave.cant_be_looted").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                        }
                    }
                    return false;
                } else {
                    if (te.isMossy()) {
                        if (item.getItem() instanceof ItemShears) {
                            if (!world.isRemote) {
                                GraveInventory.dropItem(new ItemStack(Blocks.vine, 1), world, pos);
                            }
                            te.setMossy(false);
                            return false;
                        }
                    } else {
                        if (!te.isSwordGrave() && Block.getBlockFromItem(item.getItem()) instanceof BlockVine) {
                            te.setMossy(true);
                            player.inventory.getCurrentItem().stackSize--;
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
                            player.inventory.getCurrentItem().stackSize--;
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
                            player.addChatComponentMessage(new ChatComponentTranslation(deathText, new Object[]{name}));
                        } else {
                            player.addChatComponentMessage(new ChatComponentTranslation(deathText, new Object[]{name, killerName}));
                        }
                    } else {
                        player.addChatComponentMessage(new ChatComponentTranslation(deathText));
                    }

                    if (te.getAge() > 0) {
                        StringBuilder ageStr = new StringBuilder();
                        ageStr.append(ModGraveStone.proxy.getLocalizedString("item.grave.age"))
                                .append(" ")
                                .append(te.getAge())
                                .append(" ")
                                .append(ModGraveStone.proxy.getLocalizedString("item.grave.days"));
                        player.addChatComponentMessage(new ChatComponentTranslation(ageStr.toString()));
                    }
                }
            }
        }

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityGSGraveStone(world);
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
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(pos);

        if (tileEntity != null) {
            tileEntity.getInventory().dropAllItems();
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
        if (!world.isSideSolid(pos.down(), EnumFacing.DOWN, true)) {
            TileEntityGSGraveStone te = (TileEntityGSGraveStone) world.getTileEntity(pos);
            if (te != null) {
                if (te.canBeLooted("")) {
                    GraveStoneHelper.dropBlockWithoutInfo(world, pos, state);
                    world.setBlockToAir(pos);
                }
            }
        }
    }

    @Override
    public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        TileEntityGSGraveStone te = (TileEntityGSGraveStone) world.getTileEntity(pos);
        if (te != null && !te.canBeLooted(player.getUniqueID().toString())) {
            player.addChatComponentMessage(new ChatComponentTranslation("grave.cant_be_looted").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            return false;
        }
        return super.removedByPlayer(world, pos, player, willHarvest);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item gravestone, CreativeTabs tabs, List list) {
        for (int i = 0; i < TAB_PLAYER_GRAVES.length; i++) {
            ItemStack stack = new ItemStack(gravestone, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("Type", TAB_PLAYER_GRAVES[i]);

            stack.setTagCompound(nbt);
            list.add(stack);
        }

        // pets graves
        for (int i = 0; i < TAB_PETS_GRAVES.length; i++) {
            ItemStack stack = new ItemStack(gravestone, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("Type", TAB_PETS_GRAVES[i]);

            stack.setTagCompound(nbt);
            list.add(stack);
        }

        // custom swords
        for (Item sword : GraveStoneHelper.swordsList) {
            list.add(GraveStoneHelper.getSwordAsGrave(gravestone, new ItemStack(sword, 1)));
        }
        for (Item sword : GraveStoneHelper.swordsList) {
            try {
                ItemStack swordStack = new ItemStack(sword, 1);
                EnchantmentHelper.addRandomEnchantment(new Random(), swordStack, 5);

                ItemStack graveStoneStack = GraveStoneHelper.getSwordAsGrave(gravestone, swordStack);

                list.add(graveStoneStack);
            } catch (IllegalArgumentException exception) {
                GSLogger.logError("Can't create enchanted sword nightkosh.gravestone");
                exception.printStackTrace();
            }
        }
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
        ItemStack itemStack = this.createStackedBlock(this.getDefaultState());
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(pos);

        if (tileEntity != null) {
            if (itemStack != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setInteger("Type", tileEntity.getGraveTypeNum());
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
        if (GSConfig.removeEmptyGraves) {
            if (!world.isRemote) {
                TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(pos);
                if (tileEntity != null) {
                    if (!tileEntity.isSwordGrave() && tileEntity.isEmpty()) {
                        if (GSConfig.showGravesRemovingMessages) {
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
        return ((EnumFacing) state.getValue(FACING)).getIndex();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{FACING});
    }

    @Override
    public ItemStack createStackedBlock(IBlockState state) {
        return super.createStackedBlock(state);
    }
}
