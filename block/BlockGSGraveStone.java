package gravestone.block;

import gravestone.ModGraveStone;
import gravestone.block.enums.EnumGraves;
import gravestone.config.GSConfig;
import gravestone.core.GSTabs;
import gravestone.core.logger.GSLogger;
import gravestone.tileentity.DeathMessageInfo;
import gravestone.tileentity.GSGraveStoneItems;
import gravestone.tileentity.TileEntityGSGraveStone;
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
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSpade;
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

    public static final byte[] TAB_PLAYER_GRAVES = {
            // vertical plates
            (byte) EnumGraves.WOODEN_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.SANDSTONE_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.STONE_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.IRON_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.GOLDEN_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.DIAMOND_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.EMERALD_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.LAPIS_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.REDSTONE_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.OBSIDIAN_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.QUARTZ_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.ICE_VERTICAL_PLATE.ordinal(),
            // crosses
            (byte) EnumGraves.WOODEN_CROSS.ordinal(),
            (byte) EnumGraves.SANDSTONE_CROSS.ordinal(),
            (byte) EnumGraves.STONE_CROSS.ordinal(),
            (byte) EnumGraves.IRON_CROSS.ordinal(),
            (byte) EnumGraves.GOLDEN_CROSS.ordinal(),
            (byte) EnumGraves.DIAMOND_CROSS.ordinal(),
            (byte) EnumGraves.EMERALD_CROSS.ordinal(),
            (byte) EnumGraves.LAPIS_CROSS.ordinal(),
            (byte) EnumGraves.REDSTONE_CROSS.ordinal(),
            (byte) EnumGraves.OBSIDIAN_CROSS.ordinal(),
            (byte) EnumGraves.QUARTZ_CROSS.ordinal(),
            (byte) EnumGraves.ICE_CROSS.ordinal(),
            // horisontal plates
            (byte) EnumGraves.WOODEN_HORISONTAL_PLATE.ordinal(),
            (byte) EnumGraves.SANDSTONE_HORISONTAL_PLATE.ordinal(),
            (byte) EnumGraves.STONE_HORISONTAL_PLATE.ordinal(),
            (byte) EnumGraves.IRON_HORISONTAL_PLATE.ordinal(),
            (byte) EnumGraves.GOLDEN_HORISONTAL_PLATE.ordinal(),
            (byte) EnumGraves.DIAMOND_HORISONTAL_PLATE.ordinal(),
            (byte) EnumGraves.EMERALD_HORISONTAL_PLATE.ordinal(),
            (byte) EnumGraves.LAPIS_HORISONTAL_PLATE.ordinal(),
            (byte) EnumGraves.REDSTONE_HORISONTAL_PLATE.ordinal(),
            (byte) EnumGraves.OBSIDIAN_HORISONTAL_PLATE.ordinal(),
            (byte) EnumGraves.QUARTZ_HORISONTAL_PLATE.ordinal(),
            (byte) EnumGraves.ICE_HORISONTAL_PLATE.ordinal()
    };

    public static final byte[] TAB_PETS_GRAVES = {
            // dogs graves
            (byte) EnumGraves.WOODEN_DOG_STATUE.ordinal(),
            (byte) EnumGraves.SANDSTONE_DOG_STATUE.ordinal(),
            (byte) EnumGraves.STONE_DOG_STATUE.ordinal(),
            (byte) EnumGraves.IRON_DOG_STATUE.ordinal(),
            (byte) EnumGraves.GOLDEN_DOG_STATUE.ordinal(),
            (byte) EnumGraves.DIAMOND_DOG_STATUE.ordinal(),
            (byte) EnumGraves.EMERALD_DOG_STATUE.ordinal(),
            (byte) EnumGraves.LAPIS_DOG_STATUE.ordinal(),
            (byte) EnumGraves.REDSTONE_DOG_STATUE.ordinal(),
            (byte) EnumGraves.OBSIDIAN_DOG_STATUE.ordinal(),
            (byte) EnumGraves.QUARTZ_DOG_STATUE.ordinal(),
            (byte) EnumGraves.ICE_DOG_STATUE.ordinal(),
            // cats graves
            (byte) EnumGraves.WOODEN_CAT_STATUE.ordinal(),
            (byte) EnumGraves.SANDSTONE_CAT_STATUE.ordinal(),
            (byte) EnumGraves.STONE_CAT_STATUE.ordinal(),
            (byte) EnumGraves.IRON_CAT_STATUE.ordinal(),
            (byte) EnumGraves.GOLDEN_CAT_STATUE.ordinal(),
            (byte) EnumGraves.DIAMOND_CAT_STATUE.ordinal(),
            (byte) EnumGraves.EMERALD_CAT_STATUE.ordinal(),
            (byte) EnumGraves.LAPIS_CAT_STATUE.ordinal(),
            (byte) EnumGraves.REDSTONE_CAT_STATUE.ordinal(),
            (byte) EnumGraves.OBSIDIAN_CAT_STATUE.ordinal(),
            (byte) EnumGraves.QUARTZ_CAT_STATUE.ordinal(),
            (byte) EnumGraves.ICE_CAT_STATUE.ordinal(),
            // horses graves
            (byte) EnumGraves.WOODEN_HORSE_STATUE.ordinal(),
            (byte) EnumGraves.SANDSTONE_HORSE_STATUE.ordinal(),
            (byte) EnumGraves.STONE_HORSE_STATUE.ordinal(),
            (byte) EnumGraves.IRON_HORSE_STATUE.ordinal(),
            (byte) EnumGraves.GOLDEN_HORSE_STATUE.ordinal(),
            (byte) EnumGraves.DIAMOND_HORSE_STATUE.ordinal(),
            (byte) EnumGraves.EMERALD_HORSE_STATUE.ordinal(),
            (byte) EnumGraves.LAPIS_HORSE_STATUE.ordinal(),
            (byte) EnumGraves.REDSTONE_HORSE_STATUE.ordinal(),
            (byte) EnumGraves.OBSIDIAN_HORSE_STATUE.ordinal(),
            (byte) EnumGraves.QUARTZ_HORSE_STATUE.ordinal(),
            (byte) EnumGraves.ICE_HORSE_STATUE.ordinal()
    };

    private static final Random rand = new Random();

    public BlockGSGraveStone() {
        super(Material.rock);
        this.isBlockContainer = true;
        this.setStepSound(Block.soundTypeStone);
        this.setUnlocalizedName("GraveStone");
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
                tileEntity.setGraveType(nbt.getByte("GraveType"));

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
        EnumGraves graveType;
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) access.getTileEntity(pos);

        if (tileEntity != null) {
            graveType = tileEntity.getGraveType();
        } else {
            graveType = EnumGraves.STONE_VERTICAL_PLATE;
        }

        switch (graveType) {
            case WOODEN_VERTICAL_PLATE:
            case SANDSTONE_VERTICAL_PLATE:
            case STONE_VERTICAL_PLATE:
            case IRON_VERTICAL_PLATE:
            case GOLDEN_VERTICAL_PLATE:
            case DIAMOND_VERTICAL_PLATE:
            case EMERALD_VERTICAL_PLATE:
            case LAPIS_VERTICAL_PLATE:
            case REDSTONE_VERTICAL_PLATE:
            case OBSIDIAN_VERTICAL_PLATE:
            case QUARTZ_VERTICAL_PLATE:
            case ICE_VERTICAL_PLATE:
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
            case WOODEN_CROSS:
            case SANDSTONE_CROSS:
            case STONE_CROSS:
            case IRON_CROSS:
            case GOLDEN_CROSS:
            case DIAMOND_CROSS:
            case EMERALD_CROSS:
            case LAPIS_CROSS:
            case REDSTONE_CROSS:
            case OBSIDIAN_CROSS:
            case QUARTZ_CROSS:
            case ICE_CROSS:
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
            case WOODEN_HORISONTAL_PLATE:
            case SANDSTONE_HORISONTAL_PLATE:
            case STONE_HORISONTAL_PLATE:
            case IRON_HORISONTAL_PLATE:
            case GOLDEN_HORISONTAL_PLATE:
            case DIAMOND_HORISONTAL_PLATE:
            case EMERALD_HORISONTAL_PLATE:
            case LAPIS_HORISONTAL_PLATE:
            case REDSTONE_HORISONTAL_PLATE:
            case OBSIDIAN_HORISONTAL_PLATE:
            case QUARTZ_HORISONTAL_PLATE:
            case ICE_HORISONTAL_PLATE:
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
            case WOODEN_DOG_STATUE:
            case SANDSTONE_DOG_STATUE:
            case STONE_DOG_STATUE:
            case IRON_DOG_STATUE:
            case GOLDEN_DOG_STATUE:
            case DIAMOND_DOG_STATUE:
            case EMERALD_DOG_STATUE:
            case LAPIS_DOG_STATUE:
            case REDSTONE_DOG_STATUE:
            case OBSIDIAN_DOG_STATUE:
            case QUARTZ_DOG_STATUE:
            case ICE_DOG_STATUE:
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
            case WOODEN_CAT_STATUE:
            case SANDSTONE_CAT_STATUE:
            case STONE_CAT_STATUE:
            case IRON_CAT_STATUE:
            case GOLDEN_CAT_STATUE:
            case DIAMOND_CAT_STATUE:
            case EMERALD_CAT_STATUE:
            case LAPIS_CAT_STATUE:
            case REDSTONE_CAT_STATUE:
            case OBSIDIAN_CAT_STATUE:
            case QUARTZ_CAT_STATUE:
            case ICE_CAT_STATUE:
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
            case WOODEN_HORSE_STATUE:
            case SANDSTONE_HORSE_STATUE:
            case STONE_HORSE_STATUE:
            case IRON_HORSE_STATUE:
            case GOLDEN_HORSE_STATUE:
            case DIAMOND_HORSE_STATUE:
            case EMERALD_HORSE_STATUE:
            case LAPIS_HORSE_STATUE:
            case REDSTONE_HORSE_STATUE:
            case OBSIDIAN_HORSE_STATUE:
            case QUARTZ_HORSE_STATUE:
            case ICE_HORSE_STATUE:
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
        GraveStoneHelper.spawnMob(world, pos);

        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(pos);

        if (tileEntity != null) {
            if (tileEntity.hasFlower()) {
                tileEntity.dropFlower();
            }

            if (EnchantmentHelper.getSilkTouchModifier(player)) {
                dropBlock(world, pos, state);
            } else {
                dropBlockWithoutInfo(world, pos, state);
            }
        }
    }

    /**
     * This returns a complete list of items dropped from this block.
     */
    @Override
    public List<ItemStack> getDrops(IBlockAccess access, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(getBlockItemStack(access, pos, state));
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
                if (item.getItem() instanceof ItemSpade) {
                    if (!world.isRemote) {
                        if (te.canBeLooted(player.getUniqueID().toString())) {
                            GSLogger.logInfoGrave(player.getName() + " loot grave at " + pos.getX() + "/" + pos.getY() + "/" + pos.getZ());
                            te.dropAllItems();
                        } else {
                            player.addChatComponentMessage(new ChatComponentTranslation("grave.cant_be_looted").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                        }
                    }
                    return false;
                } else {
                    if (te.isMossy()) {
                        if (item.getItem() instanceof ItemShears) {
                            if (!world.isRemote) {
                                GSGraveStoneItems.dropItem(new ItemStack(Blocks.vine, 1), world, pos);
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
            tileEntity.dropAllItems();
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
        if (!world.isSideSolid(pos.down(), EnumFacing.DOWN, true)) {
            this.dropBlockWithoutInfo(world, pos, state);
            world.setBlockToAir(pos);
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item gravestone, CreativeTabs tabs, List list) {
        for (byte i = 0; i < TAB_PLAYER_GRAVES.length; i++) {
            ItemStack stack = new ItemStack(gravestone, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", TAB_PLAYER_GRAVES[i]);

            stack.setTagCompound(nbt);
            list.add(stack);
        }

        // pets graves
        for (byte i = 0; i < TAB_PETS_GRAVES.length; i++) {
            ItemStack stack = new ItemStack(gravestone, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", TAB_PETS_GRAVES[i]);

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
                GSLogger.logError("Can't create enchanted sword gravestone");
                exception.printStackTrace();
            }
        }
    }

    /**
     * Drop grave as item block
     */
    private void dropBlock(World world, BlockPos pos, IBlockState state) {
        ItemStack itemStack = getBlockItemStack(world, pos, state);

        if (itemStack != null) {
            GSGraveStoneItems.dropItem(itemStack, world, pos);

        }
    }

    private void dropBlockWithoutInfo(World world, BlockPos pos, IBlockState state) {
        ItemStack itemStack = this.createStackedBlock(this.getDefaultState());
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(pos);

        if (tileEntity != null) {
            if (tileEntity.isSwordGrave()) {
                tileEntity.dropSword();
            } else if (itemStack != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte("GraveType", tileEntity.getGraveTypeNum());
                nbt.setBoolean("Mossy", tileEntity.isMossy());

                itemStack.setTagCompound(nbt);
                GSGraveStoneItems.dropItem(itemStack, world, pos);
            }
        }
    }

    /**
     * Get grave block as item block
     */
    private ItemStack getBlockItemStack(IBlockAccess access, BlockPos pos, IBlockState state) {
        ItemStack itemStack = this.createStackedBlock(this.getDefaultState());
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) access.getTileEntity(pos);

        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", tileEntity.getGraveTypeNum());

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

            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }

    /**
     * Create grave on death
     */
    public void createOnDeath(Entity entity, World world, BlockPos pos, DeathMessageInfo deathInfo, List<ItemStack> items, int age, EnumGraveType entityType, DamageSource damageSource) {
        EnumFacing direction = EnumFacing.getHorizontal(MathHelper.floor_double((double) (entity.rotationYaw * 4 / 360F) + 0.5D) & 3);

        byte graveType = 0;
        ItemStack sword = null;

        if (GSConfig.generateSwordGraves && world.rand.nextInt(4) == 0 && entityType.equals(EnumGraveType.PLAYER_GRAVES)) {
            sword = GraveStoneHelper.oldCheckSword(items);
        }

        switch (entityType) {
            case PLAYER_GRAVES:
                graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getPlayerGraveForLevel(entity), rand);
                if (graveType == 0) {
                    if (sword == null) {
                        graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getPlayerGraveForDeath(damageSource, damageSource.damageType), rand);
                        if (graveType == 0) {
                            graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getPlayerGraveTypes(world, pos), rand);
                        }
                    } else {
                        graveType = (byte) EnumGraves.SWORD.ordinal();
                    }
                }
                break;
            case DOGS_GRAVES:
                graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getDogGraveForDeath(damageSource, damageSource.damageType), rand);
                if (graveType == 0) {
                    graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getDogGraveTypes(world, pos), rand);
                }
                break;
            case CATS_GRAVES:
                graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getCatGraveForDeath(damageSource, damageSource.damageType), rand);
                if (graveType == 0) {
                    graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getCatGraveTypes(world, pos), rand);
                }
                break;
            case HORSE_GRAVES:
                graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getHorseGraveForDeath(damageSource, damageSource.damageType), rand);
                if (graveType == 0) {
                    graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getHorseGraveTypes(world, pos), rand);
                }
                break;
        }

        boolean isMagic = GraveStoneHelper.isMagicDamage(damageSource, damageSource.damageType);
        boolean isMossy = false; //TODO

        BlockPos newPos = GraveStoneHelper.findPlaceForGrave(world, pos);
        if (newPos != null) {
            world.setBlockState(newPos, this.getDefaultState().withProperty(FACING, direction), 2);
            TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(newPos);

            if (tileEntity != null) {
                if (sword != null) {
                    tileEntity.setSword(sword);
                }

                tileEntity.getDeathTextComponent().setLocalized();
                tileEntity.getDeathTextComponent().setName(deathInfo.getName());
                tileEntity.getDeathTextComponent().setDeathText(deathInfo.getDeathMessage());
                tileEntity.getDeathTextComponent().setKillerName(deathInfo.getKillerName());
                tileEntity.setItems(items);
                tileEntity.setGraveType(graveType);
                tileEntity.setAge(age);
                tileEntity.setEnchanted(isMagic);
                tileEntity.setMossy(isMossy);
                if (entity instanceof EntityPlayer) {
                    tileEntity.setOwner(entity.getUniqueID().toString());
                } else if (entity instanceof EntityTameable && ((EntityTameable) entity).isTamed()) {
                    tileEntity.setOwner(((EntityTameable) entity).getOwner().getUniqueID().toString());
                }
            }
            GSLogger.logInfoGrave("Create " + deathInfo.getName() + "'s grave at " + newPos.getX() + "x" + newPos.getY() + "x" + newPos.getZ());
        } else {
            ItemStack itemStack = this.createStackedBlock(this.getDefaultState());
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", graveType);
            nbt.setBoolean("isLocalized", true);
            nbt.setString("name", deathInfo.getName());
            nbt.setString("DeathText", deathInfo.getDeathMessage());
            nbt.setString("KillerName", deathInfo.getKillerNameForTE());
            nbt.setBoolean("Enchanted", isMagic);
            nbt.setBoolean("Mossy", isMossy);
            nbt.setInteger("Age", age);

            if (graveType == EnumGraves.SWORD.ordinal()) {
                GraveStoneHelper.addSwordInfo(nbt, sword);
            }

            itemStack.setTagCompound(nbt);
            GSGraveStoneItems.dropItem(itemStack, world, pos);

            if (items != null) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i) != null) {
                        GSGraveStoneItems.dropItem(items.get(i), world, pos);
                    }
                }
            }
            GSLogger.logInfoGrave("Can not create " + deathInfo.getName() + "'s grave at " + pos.getX() + "x" + pos.getY() + "x" + pos.getZ());
        }
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
        ItemStack itemStack = this.createStackedBlock(this.getDefaultState());
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(pos);

        if (tileEntity != null) {
            if (itemStack != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte("GraveType", tileEntity.getGraveTypeNum());

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

    public enum EnumGraveType {
        ALL_GRAVES,
        PLAYER_GRAVES,
        PETS_GRAVES,
        DOGS_GRAVES,
        CATS_GRAVES,
        HORSE_GRAVES
    }
}
