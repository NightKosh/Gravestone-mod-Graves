package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.GraveStoneLogger;
import gravestone.ModGraveStone;
import gravestone.block.enums.EnumGraves;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSTabs;
import gravestone.tileentity.DeathMessageInfo;
import gravestone.tileentity.GSGraveStoneItems;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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

    public static final byte[] TAB_PLAYER_GRAVES = {
            // vertical plates
            (byte) EnumGraves.WOODEN_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.SANDSTONE_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.STONE_VERTICAL_PLATE.ordinal(),
            (byte) EnumGraves.MOSSY_VERTICAL_PLATE.ordinal(),
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
            (byte) EnumGraves.MOSSY_CROSS.ordinal(),
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
            (byte) EnumGraves.MOSSY_HORISONTAL_PLATE.ordinal(),
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
            (byte) EnumGraves.MOSSY_DOG_STATUE.ordinal(),
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
            (byte) EnumGraves.MOSSY_CAT_STATUE.ordinal(),
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
            (byte) EnumGraves.MOSSY_HORSE_STATUE.ordinal(),
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
        this.setBlockName("GraveStone");
        this.setHardness(4.5F);
        this.setResistance(5F);
        this.setCreativeTab(GSTabs.gravesTab);
        this.setTickRandomly(GraveStoneConfig.removeEmptyGraves);
        this.setBlockTextureName("stone");
        this.setHarvestLevel("pickaxe", 1);
    }

    /**
     * Called when the block is placed in the world
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
        GraveStoneHelper.replaceGround(world, x, y - 1, z);
        int direction = MathHelper.floor_float(player.rotationYaw);

        if (direction < 0) {
            direction = 360 + direction;
        }

        int metadata = GraveStoneHelper.getMetadataBasedOnRotation(direction);
        world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            if (itemStack.stackTagCompound != null) {
                if (itemStack.stackTagCompound.hasKey("GraveType")) {
                    tileEntity.setGraveType(itemStack.stackTagCompound.getByte("GraveType"));
                } else {
                    tileEntity.setGraveType((byte) 0);
                }

                if (itemStack.stackTagCompound.hasKey("isLocalized") && itemStack.stackTagCompound.getBoolean("isLocalized")) {
                    tileEntity.getDeathTextComponent().setLocalized();

                    if (itemStack.stackTagCompound.hasKey("name") && itemStack.stackTagCompound.hasKey("KillerName")) {
                        tileEntity.getDeathTextComponent().setName(itemStack.stackTagCompound.getString("name"));
                        tileEntity.getDeathTextComponent().setKillerName(itemStack.stackTagCompound.getString("KillerName"));
                    }
                }

                if (itemStack.stackTagCompound.hasKey("DeathText")) {
                    tileEntity.getDeathTextComponent().setDeathText(itemStack.stackTagCompound.getString("DeathText"));
                }

                if (itemStack.stackTagCompound.hasKey("Age")) {
                    tileEntity.setAge(itemStack.stackTagCompound.getInteger("Age"));
                }

                if (itemStack.stackTagCompound.hasKey("Sword")) {
                    tileEntity.setSword(ItemStack.loadItemStackFromNBT(itemStack.getTagCompound().getCompoundTag("Sword")));
                }

                if (itemStack.stackTagCompound.hasKey("Enchanted")) {
                    tileEntity.setEnchanted(itemStack.stackTagCompound.getBoolean("Enchanted"));
                }
            }
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified
     * coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return GraveStoneHelper.canPlaceBlockAt(world.getBlock(x, y - 1, z));
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y,
     * z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
        int meta = access.getBlockMetadata(x, y, z);
        EnumGraves graveType;
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) access.getTileEntity(x, y, z);

        if (tileEntity != null) {
            graveType = tileEntity.getGraveType();
        } else {
            graveType = EnumGraves.STONE_VERTICAL_PLATE;
        }

        switch (graveType) {
            case WOODEN_VERTICAL_PLATE:
            case SANDSTONE_VERTICAL_PLATE:
            case STONE_VERTICAL_PLATE:
            case MOSSY_VERTICAL_PLATE:
            case IRON_VERTICAL_PLATE:
            case GOLDEN_VERTICAL_PLATE:
            case DIAMOND_VERTICAL_PLATE:
            case EMERALD_VERTICAL_PLATE:
            case LAPIS_VERTICAL_PLATE:
            case REDSTONE_VERTICAL_PLATE:
            case OBSIDIAN_VERTICAL_PLATE:
            case QUARTZ_VERTICAL_PLATE:
            case ICE_VERTICAL_PLATE:
                switch (meta) {
                    case 0:
                        this.setBlockBounds(0.125F, 0, 0.0625F, 0.875F, 0.9375F, 0.1875F);
                        break;
                    case 1:
                        this.setBlockBounds(0.125F, 0, 0.8125F, 0.875F, 0.9375F, 0.9375F);
                        break;
                    case 2:
                        this.setBlockBounds(0.0625F, 0, 0.125F, 0.1875F, 0.9375F, 0.875F);
                        break;
                    case 3:
                        this.setBlockBounds(0.8125F, 0, 0.125F, 0.9375F, 0.9375F, 0.875F);
                        break;
                }
                break;
            case WOODEN_CROSS:
            case SANDSTONE_CROSS:
            case STONE_CROSS:
            case MOSSY_CROSS:
            case IRON_CROSS:
            case GOLDEN_CROSS:
            case DIAMOND_CROSS:
            case EMERALD_CROSS:
            case LAPIS_CROSS:
            case REDSTONE_CROSS:
            case OBSIDIAN_CROSS:
            case QUARTZ_CROSS:
            case ICE_CROSS:
                switch (meta) {
                    case 0:
                        this.setBlockBounds(0.125F, 0, 0.0625F, 0.875F, 1, 0.1875F);
                        break;
                    case 1:
                        this.setBlockBounds(0.125F, 0, 0.8125F, 0.875F, 1, 0.9375F);
                        break;
                    case 2:
                        this.setBlockBounds(0.0625F, 0, 0.125F, 0.1875F, 1, 0.875F);
                        break;
                    case 3:
                        this.setBlockBounds(0.8125F, 0, 0.125F, 0.9375F, 1, 0.875F);
                        break;
                }
                break;
            case WOODEN_HORISONTAL_PLATE:
            case SANDSTONE_HORISONTAL_PLATE:
            case STONE_HORISONTAL_PLATE:
            case MOSSY_HORISONTAL_PLATE:
            case IRON_HORISONTAL_PLATE:
            case GOLDEN_HORISONTAL_PLATE:
            case DIAMOND_HORISONTAL_PLATE:
            case EMERALD_HORISONTAL_PLATE:
            case LAPIS_HORISONTAL_PLATE:
            case REDSTONE_HORISONTAL_PLATE:
            case OBSIDIAN_HORISONTAL_PLATE:
            case QUARTZ_HORISONTAL_PLATE:
            case ICE_HORISONTAL_PLATE:
                switch (meta) {
                    case 0:
                        this.setBlockBounds(0.09375F, 0, 0.0625F, 0.90625F, 0.0625F, 0.9375F);
                        break;
                    case 1:
                        this.setBlockBounds(0.09375F, 0, 0.0625F, 0.90625F, 0.0625F, 0.9375F);
                        break;
                    case 2:
                        this.setBlockBounds(0.0625F, 0, 0.09375F, 0.9375F, 0.0625F, 0.90625F);
                        break;
                    case 3:
                        this.setBlockBounds(0.0625F, 0, 0.09375F, 0.9375F, 0.0625F, 0.90625F);
                        break;
                }
                break;
            case WOODEN_DOG_STATUE:
            case SANDSTONE_DOG_STATUE:
            case STONE_DOG_STATUE:
            case MOSSY_DOG_STATUE:
            case IRON_DOG_STATUE:
            case GOLDEN_DOG_STATUE:
            case DIAMOND_DOG_STATUE:
            case EMERALD_DOG_STATUE:
            case LAPIS_DOG_STATUE:
            case REDSTONE_DOG_STATUE:
            case OBSIDIAN_DOG_STATUE:
            case QUARTZ_DOG_STATUE:
            case ICE_DOG_STATUE:
                switch (meta) {
                    case 0:
                        this.setBlockBounds(0.35F, 0, 0.3F, 0.6F, 0.5F, 0.9F);
                        break;
                    case 1:
                        this.setBlockBounds(0.35F, 0, 0.7F, 0.6F, 0.5F, 0.1F);
                        break;
                    case 2:
                        this.setBlockBounds(0.3F, 0, 0.35F, 0.9F, 0.5F, 0.6F);
                        break;
                    case 3:
                        this.setBlockBounds(0.7F, 0, 0.35F, 0.1F, 0.5F, 0.6F);
                        break;
                }
                break;
            case WOODEN_CAT_STATUE:
            case SANDSTONE_CAT_STATUE:
            case STONE_CAT_STATUE:
            case MOSSY_CAT_STATUE:
            case IRON_CAT_STATUE:
            case GOLDEN_CAT_STATUE:
            case DIAMOND_CAT_STATUE:
            case EMERALD_CAT_STATUE:
            case LAPIS_CAT_STATUE:
            case REDSTONE_CAT_STATUE:
            case OBSIDIAN_CAT_STATUE:
            case QUARTZ_CAT_STATUE:
            case ICE_CAT_STATUE:
                switch (meta) {
                    case 0:
                        this.setBlockBounds(0.43F, 0, 0.3F, 0.57F, 0.5F, 0.75F);
                        break;
                    case 1:
                        this.setBlockBounds(0.43F, 0, 0.7F, 0.57F, 0.5F, 0.25F);
                        break;
                    case 2:
                        this.setBlockBounds(0.3F, 0, 0.43F, 0.75F, 0.5F, 0.57F);
                        break;
                    case 3:
                        this.setBlockBounds(0.7F, 0, 0.43F, 0.25F, 0.5F, 0.57F);
                        break;
                }
                break;
            case SWORD:
                switch (meta) {
                    case 0:
                        this.setBlockBounds(0.375F, 0, 0.4375F, 0.625F, 0.9F, 0.5625F);
                        break;
                    case 1:
                        this.setBlockBounds(0.375F, 0, 0.4375F, 0.625F, 0.9F, 0.5625F);
                        break;
                    case 2:
                        this.setBlockBounds(0.4375F, 0, 0.375F, 0.5625F, 0.9F, 0.625F);
                        break;
                    case 3:
                        this.setBlockBounds(0.4375F, 0, 0.375F, 0.5625F, 0.9F, 0.625F);
                        break;
                }
                break;
            case WOODEN_HORSE_STATUE:
            case SANDSTONE_HORSE_STATUE:
            case STONE_HORSE_STATUE:
            case MOSSY_HORSE_STATUE:
            case IRON_HORSE_STATUE:
            case GOLDEN_HORSE_STATUE:
            case DIAMOND_HORSE_STATUE:
            case EMERALD_HORSE_STATUE:
            case LAPIS_HORSE_STATUE:
            case REDSTONE_HORSE_STATUE:
            case OBSIDIAN_HORSE_STATUE:
            case QUARTZ_HORSE_STATUE:
            case ICE_HORSE_STATUE:
                switch (meta) {
                    case 0:
                        this.setBlockBounds(0.375F, 0, 0.275F, 0.625F, 0.85F, 0.725F);
                        break;
                    case 1:
                        this.setBlockBounds(0.375F, 0, 0.275F, 0.625F, 0.85F, 0.725F);
                        break;
                    case 2:
                        this.setBlockBounds(0.275F, 0, 0.375F, 0.725F, 0.85F, 0.625F);
                        break;
                    case 3:
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
    public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player) {
        player.addExhaustion(0.025F);
        GraveStoneHelper.spawnMob(world, x, y, z);

        if (GraveStoneConfig.silkTouchForGraves) {
            if (EnchantmentHelper.getSilkTouchModifier(player)) {
                dropBlock(world, x, y, z);
            } else {
                TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(x, y, z);

                if (tileEntity != null && tileEntity.isSwordGrave()) {
                    tileEntity.dropSword();
                }
            }
        } else {
            dropBlock(world, x, y, z);
        }
    }

    /**
     * This returns a complete list of items dropped from this block.
     *
     * @param world    The current world
     * @param x        X Position
     * @param y        Y Position
     * @param z        Z Position
     * @param metadata Current metadata
     * @param fortune  Breakers fortune level
     * @return A ArrayList containing all items this block drops
     */
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        if (!GraveStoneConfig.silkTouchForGraves) {
            ret.add(getBlockItemStack(world, x, y, z));
        } else {
            TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(x, y, z);

            if (tileEntity != null && tileEntity.isSwordGrave()) {
                ret.add(tileEntity.getSword());
            }
        }

        return ret;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it.
     * (x, y, z) are the coordinates of the block and metadata is the block's
     * subtype/damage.
     */
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly,
     * and not its normal drops.
     */
    @Override
    public boolean canSilkHarvest() {
        return GraveStoneConfig.silkTouchForGraves;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int par1, Random random, int par3) {
        return GraveStoneConfig.silkTouchForGraves ? null : super.getItemDropped(par1, random, par3);
    }

    /**
     * If this block doesn't render as an ordinary block it will return False
     * (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return GraveStoneConfig.graveRenderID;
    }

    /**
     * Called right before the block is destroyed by a player. Args: world, x,
     * y, z, metaData
     */
    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
        GraveStoneHelper.spawnMob(world, x, y, z);
    }

    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return getExplosionResistance(par1Entity);
    }

    @Override
    public float getExplosionResistance(Entity par1Entity) {
        return 18000000F;
    }

    /**
     * Called when the block is destroyed by an explosion. Useful for allowing
     * the block to take into account tile entities, metadata, etc. when
     * exploded, before it is removed.
     */
    @Override
    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        TileEntityGSGraveStone te = (TileEntityGSGraveStone) world.getTileEntity(x, y, z);

        if (te != null) {
            if (world.isRemote) {
                if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ItemSpade) {
                } else {
                    String name;
                    String deathText;
                    String killerName;
                    deathText = te.getDeathTextComponent().getDeathText();

                    if (deathText.length() != 0) {
                        if (te.getDeathTextComponent().isLocalized()) {
                            name = te.getDeathTextComponent().getName();
                            killerName = ModGraveStone.proxy.getLocalizedEntityName(te.getDeathTextComponent().getKillerName());

                            if (killerName.length() == 0) {
                                player.addChatComponentMessage(new ChatComponentTranslation(deathText, new Object[]{name}));
                            } else {
                                player.addChatComponentMessage(new ChatComponentTranslation(deathText, new Object[]{name, killerName}));
                            }
                        } else {
                            player.addChatComponentMessage(new ChatComponentTranslation(deathText));
                        }

                        if (te.getAge() != -1) {
                            //entityPlayer.sendChatToPlayer("Had lived " + entity.getAge() + " days");
                        }
                    }
                }
            } else {
                if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ItemSpade) {
                    GraveStoneLogger.logInfo(player.getCommandSenderName() + " loot grave at " + x + "/" + y + "/" + z);
                    te.dropAllItems();
                }
            }
        }

        return false;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing
     * the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityGSGraveStone(world);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        GraveStoneHelper.replaceGround(world, x, y - 1, z);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an
     * update, as appropriate
     */
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            tileEntity.dropAllItems();
        }

        super.breakBlock(world, x, y, z, block, par6);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isSideSolid(x, y - 1, z, ForgeDirection.DOWN, true)) {
            this.dropBlockAsItem(world, x, y, z, 0, 0);
            world.setBlock(x, y, z, Blocks.air, 0, 2);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and
     * wood.
     */
    @Override
    public int damageDropped(int metadata) {
        return 0;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
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
            ItemStack swordStack = new ItemStack(sword, 1);
            EnchantmentHelper.addRandomEnchantment(new Random(), swordStack, 5);

            ItemStack graveStoneStack = GraveStoneHelper.getSwordAsGrave(gravestone, swordStack);

            list.add(graveStoneStack);
        }
    }

    /**
     * Drop grave as item block
     */
    private void dropBlock(World world, int x, int y, int z) {
        ItemStack itemStack = getBlockItemStack(world, x, y, z);

        if (itemStack != null) {
            this.dropBlockAsItem(world, x, y, z, itemStack);
        }
    }

    /**
     * Get grave block as item block
     */
    private ItemStack getBlockItemStack(World world, int x, int y, int z) {
        ItemStack itemStack = this.createStackedBlock(0);
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(x, y, z);

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

            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }

    /**
     * Create grave on death
     */
    public void createOnDeath(World world, int x, int y, int z, DeathMessageInfo deathInfo, int direction, List<ItemStack> items, int age, EnumGraveType entityType, boolean isMagic) {
        if (direction < 0) {
            direction = 360 + direction;
        }

        byte graveType = 0;
        ItemStack sword = null;

        if (GraveStoneConfig.generateSwordGraves && world.rand.nextInt(4) == 0 && entityType.equals(EnumGraveType.PLAYER_GRAVES)) {
            sword = GraveStoneHelper.oldCheckSword(items);
        }

        switch (entityType) {
            case PLAYER_GRAVES:
                if (sword == null) {
                    graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getGeneratedGraveTypes(world, x, z), rand);
                } else {
                    graveType = (byte) EnumGraves.SWORD.ordinal();
                }
                break;
            case DOGS_GRAVES:
                graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getDogGraveTypes(world, x, z), rand);
                break;
            case CATS_GRAVES:
                graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getCatGraveTypes(world, x, z), rand);
                break;
            case HORSE_GRAVES:
                graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getHorseGraveTypes(world, x, z), rand);
                break;
        }


        // TODO rework place finding
        boolean canGenerateGrave = false;
        while ((world.isAirBlock(x, y - 1, z) || world.getBlock(x, y - 1, z).getMaterial().isLiquid() ||
                world.getBlock(x, y - 1, z).getMaterial().isReplaceable()) && y > 1) {
            y--;
        }
        if (canGenerateGraveAtCoordinates(world, x, y, z)) {
            canGenerateGrave = true;
        } else {
            for (byte xShift = -5; xShift < 6; xShift++) {
                for (byte zShift = -5; zShift < 6; zShift++) {
                    if (canGenerateGraveAtCoordinates(world, x + xShift, y, z + zShift)) {
                        x += xShift;
                        z += zShift;
                        canGenerateGrave = true;
                        break;
                    }
                }
                if (canGenerateGrave) {
                    break;
                }
            }
        }

        if (canGenerateGrave) {
            world.setBlock(x, y, z, this, GraveStoneHelper.getMetadataBasedOnRotation(direction), 0x02);
            TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(x, y, z);

            if (tileEntity != null) {
                if (sword != null) {
                    if (isMagic) {
                        if (sword.getTagCompound() == null) {
                            sword.setTagCompound(new NBTTagCompound());
                        }
                        sword.getTagCompound().setTag("ench", new NBTTagList());
                    }
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
            }
            GraveStoneLogger.logInfo("Create " + deathInfo.getName() + "'s grave at " + x + "x" + y + "x" + z);
        } else {
            ItemStack itemStack = this.createStackedBlock(0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", graveType);
            nbt.setBoolean("isLocalized", true);
            nbt.setString("name", deathInfo.getName());
            nbt.setString("DeathText", deathInfo.getDeathMessage());
            nbt.setString("KillerName", deathInfo.getKillerNameForTE());
            nbt.setBoolean("Enchanted", isMagic);
//            nbt.setInteger("Age", age);

            if (graveType == EnumGraves.SWORD.ordinal()) {
                GraveStoneHelper.addSwordInfo(nbt, sword);
            }

            itemStack.setTagCompound(nbt);
            this.dropBlockAsItem(world, x, y, z, itemStack);

            if (items != null) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i) != null) {
                        GSGraveStoneItems.dropItem(items.get(i), world, x, y, z);
                    }
                }
            }
            GraveStoneLogger.logInfo("Can not create " + deathInfo.getName() + "'s grave at " + x + "x" + y + "x" + z);
        }
    }

    private boolean canGenerateGraveAtCoordinates(World world, int x, int y, int z) {
        return (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).getMaterial().isLiquid() || world.getBlock(x, y, z).getMaterial().isReplaceable());
    }

    /**
     * A randomly called display update to be able to add particles or other
     * items for display
     */
    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (GraveStoneConfig.removeEmptyGraves) {
            if (!world.isRemote) {
                TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(x, y, z);
                if (tileEntity != null) {
                    if (!tileEntity.isSwordGrave() && tileEntity.isEmpty()) {
                        if (GraveStoneConfig.showGravesRemovingMessages) {
                            GraveStoneLogger.logInfo("Remove empty grave at " + x + "/" + y + "/" + z);
                        }

                        world.removeTileEntity(x, y, z);
                        world.setBlock(x, y, z, this, 0, 2);
                    }
                }
            }
        }
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
