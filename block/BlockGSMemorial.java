package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import gravestone.block.enums.EnumMemorials;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSTabs;
import gravestone.tileentity.TileEntityGSMemorial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSMemorial extends BlockContainer {

    public static final byte[] TAB_MEMORIALS = {
            (byte) EnumMemorials.WOODEN_CROSS.ordinal(),
            (byte) EnumMemorials.SANDSTONE_CROSS.ordinal(),
            (byte) EnumMemorials.STONE_CROSS.ordinal(),
            (byte) EnumMemorials.MOSSY_CROSS.ordinal(),
            (byte) EnumMemorials.IRON_CROSS.ordinal(),
            (byte) EnumMemorials.GOLDEN_CROSS.ordinal(),
            (byte) EnumMemorials.DIAMOND_CROSS.ordinal(),
            (byte) EnumMemorials.EMERALD_CROSS.ordinal(),
            (byte) EnumMemorials.LAPIS_CROSS.ordinal(),
            (byte) EnumMemorials.REDSTONE_CROSS.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_CROSS.ordinal(),
            (byte) EnumMemorials.QUARTZ_CROSS.ordinal(),
            (byte) EnumMemorials.ICE_CROSS.ordinal(),
            // obelisks
//            (byte) EnumMemorials.WOODEN_OBELISK.ordinal(),
//            (byte) EnumMemorials.SANDSTONE_OBELISK.ordinal(),
//            (byte) EnumMemorials.STONE_OBELISK.ordinal(),
//            (byte) EnumMemorials.MOSSY_OBELISK.ordinal(),
//            (byte) EnumMemorials.IRON_OBELISK.ordinal(),
//            (byte) EnumMemorials.GOLDEN_OBELISK.ordinal(),
//            (byte) EnumMemorials.DIAMOND_OBELISK.ordinal(),
//            (byte) EnumMemorials.EMERALD_OBELISK.ordinal(),
//            (byte) EnumMemorials.LAPIS_OBELISK.ordinal(),
//            (byte) EnumMemorials.REDSTONE_OBELISK.ordinal(),
//            (byte) EnumMemorials.OBSIDIAN_OBELISK.ordinal(),
            (byte) EnumMemorials.QUARTZ_OBELISK.ordinal(),
//            (byte) EnumMemorials.ICE_OBELISK.ordinal(),
            // steve memorials
            (byte) EnumMemorials.WOODEN_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.SANDSTONE_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.MOSSY_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.IRON_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.GOLDEN_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.DIAMOND_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.EMERALD_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.LAPIS_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.REDSTONE_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.QUARTZ_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.ICE_STEVE_STATUE.ordinal(),
            // villager memorials
            (byte) EnumMemorials.WOODEN_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.SANDSTONE_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.MOSSY_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.IRON_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.GOLDEN_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.DIAMOND_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.EMERALD_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.LAPIS_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.REDSTONE_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.QUARTZ_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.ICE_VILLAGER_STATUE.ordinal(),
            // angel memorials
            (byte) EnumMemorials.WOODEN_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.SANDSTONE_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.MOSSY_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.IRON_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.GOLDEN_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.DIAMOND_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.EMERALD_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.LAPIS_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.REDSTONE_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.QUARTZ_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.ICE_ANGEL_STATUE.ordinal(),
            // dog memorials
            (byte) EnumMemorials.WOODEN_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.SANDSTONE_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.MOSSY_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.IRON_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.GOLDEN_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.DIAMOND_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.EMERALD_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.LAPIS_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.REDSTONE_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.QUARTZ_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.ICE_DOG_STATUE.ordinal(),
            // cat memorials
            (byte) EnumMemorials.WOODEN_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.SANDSTONE_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.MOSSY_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.IRON_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.GOLDEN_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.DIAMOND_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.EMERALD_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.LAPIS_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.REDSTONE_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.QUARTZ_CAT_STATUE.ordinal(),
            (byte) EnumMemorials.ICE_CAT_STATUE.ordinal(),
            // creeper memorials
            (byte) EnumMemorials.WOODEN_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.SANDSTONE_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.MOSSY_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.IRON_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.GOLDEN_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.DIAMOND_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.EMERALD_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.LAPIS_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.REDSTONE_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.OBSIDIAN_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.QUARTZ_CREEPER_STATUE.ordinal(),
            (byte) EnumMemorials.ICE_CREEPER_STATUE.ordinal()
    };
    public static final byte[] GENERATED_MEMORIALS = {
            (byte) EnumMemorials.STONE_CROSS.ordinal(),
            (byte) EnumMemorials.QUARTZ_OBELISK.ordinal(),
            (byte) EnumMemorials.STONE_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_ANGEL_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_CAT_STATUE.ordinal()
    };
    public static final byte[] PETS_MEMORIALS = {
            (byte) EnumMemorials.STONE_DOG_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_CAT_STATUE.ordinal()
    };
    public static final byte[] DOG_MEMORIALS = {(byte) EnumMemorials.STONE_DOG_STATUE.ordinal()};
    public static final byte[] CAT_MEMORIALS = {(byte) EnumMemorials.STONE_CAT_STATUE.ordinal()};
    public static final byte[] CREEPER_MEMORIALS = {(byte) EnumMemorials.STONE_CREEPER_STATUE.ordinal()};
    public static final byte[] STATUES_MEMORIALS = {
            (byte) EnumMemorials.STONE_STEVE_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_VILLAGER_STATUE.ordinal(),
            (byte) EnumMemorials.STONE_ANGEL_STATUE.ordinal()
    };

    public BlockGSMemorial() {
        super(Material.rock);
        this.isBlockContainer = true;
        this.setStepSound(Block.soundTypeStone);
        this.setBlockName("Memorial");
        this.setHardness(4.5F);
        this.setResistance(5F);
        this.setCreativeTab(GSTabs.memorialsTab);
        this.setBlockTextureName("stone");
        this.setHarvestLevel("pickaxe", 2);
    }

    /*
     * Return memorial metadata by direction
     */
    public static int getMetaDirection(int direction) {
        switch (direction) {
            case 0: // S
                return 1;
            case 1: // W
                return 2;
            case 2: // N
                return 0;
            case 3: // E
                return 3;
            default:
                return 0;
        }
    }

    /**
     * Return random memorial type memorialTypetype - type of memorial 0 - all
     * memorials(20% for pets graves), except creeper 1 - only pets memorials 2
     * - only dogs memorials 3 - only cats memorials 4 - creeper memorials 5 -
     * only statues memorials(steve, villager, angel)
     */
    public static byte getMemorialType(Random random, int memorialType) {
        switch (memorialType) {
            default:
            case 0:
                return GENERATED_MEMORIALS[random.nextInt(GENERATED_MEMORIALS.length)];
            case 1:
                return PETS_MEMORIALS[random.nextInt(PETS_MEMORIALS.length)];
            case 2:
                return DOG_MEMORIALS[random.nextInt(DOG_MEMORIALS.length)];
            case 3:
                return CAT_MEMORIALS[random.nextInt(CAT_MEMORIALS.length)];
            case 4:
                return CREEPER_MEMORIALS[random.nextInt(CREEPER_MEMORIALS.length)];
            case 5:
                return STATUES_MEMORIALS[random.nextInt(STATUES_MEMORIALS.length)];
        }
    }

    /**
     * Called when the block is placed in the world
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
        int direction = MathHelper.floor_float(player.rotationYaw);

        if (direction < 0) {
            direction = 360 + direction;
        }

        int metadata = getMetadataBasedOnRotation(direction);
        world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            if (itemStack.stackTagCompound != null) {
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

                if (itemStack.stackTagCompound.hasKey("GraveType")) {
                    tileEntity.setGraveType(itemStack.stackTagCompound.getByte("GraveType"));
                } else {
                    tileEntity.setGraveType((byte) 0);
                }
            }
        }
    }

    private int getMetadataBasedOnRotation(int rotation) {
        if (rotation >= 315 || rotation < 45) {
            return 1;
        } else if (rotation >= 45 && rotation < 135) {
            return 2;
        } else if (rotation >= 135 && rotation < 225) {
            return 0;
        } else {
            return 3;
        }
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y,
     * z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
        EnumMemorials memorialType;
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) access.getTileEntity(x, y, z);

        if (tileEntity != null) {
            memorialType = tileEntity.getMemorialType();
        } else {
            memorialType = EnumMemorials.STONE_CROSS;
        }

        switch (memorialType) {
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
            case WOODEN_OBELISK:
            case SANDSTONE_OBELISK:
            case STONE_OBELISK:
            case MOSSY_OBELISK:
            case IRON_OBELISK:
            case GOLDEN_OBELISK:
            case DIAMOND_OBELISK:
            case EMERALD_OBELISK:
            case LAPIS_OBELISK:
            case REDSTONE_OBELISK:
            case OBSIDIAN_OBELISK:
            case QUARTZ_OBELISK:
            case ICE_OBELISK:
                this.setBlockBounds(-1, 0, -1, 2, 5, 2);
                break;
            case WOODEN_STEVE_STATUE:
            case SANDSTONE_STEVE_STATUE:
            case STONE_STEVE_STATUE:
            case MOSSY_STEVE_STATUE:
            case IRON_STEVE_STATUE:
            case GOLDEN_STEVE_STATUE:
            case DIAMOND_STEVE_STATUE:
            case EMERALD_STEVE_STATUE:
            case LAPIS_STEVE_STATUE:
            case REDSTONE_STEVE_STATUE:
            case OBSIDIAN_STEVE_STATUE:
            case QUARTZ_STEVE_STATUE:
            case ICE_STEVE_STATUE:
            case STONE_VILLAGER_STATUE:
            case STONE_ANGEL_STATUE:
                this.setBlockBounds(0.125F, 0, 0.125F, 0.875F, 3F, 0.875F);
                break;
            case WOODEN_DOG_STATUE:
            case WOODEN_CAT_STATUE:
            case SANDSTONE_DOG_STATUE:
            case SANDSTONE_CAT_STATUE:
            case STONE_DOG_STATUE:
            case STONE_CAT_STATUE:
            case MOSSY_DOG_STATUE:
            case MOSSY_CAT_STATUE:
            case IRON_DOG_STATUE:
            case IRON_CAT_STATUE:
            case GOLDEN_DOG_STATUE:
            case GOLDEN_CAT_STATUE:
            case DIAMOND_DOG_STATUE:
            case DIAMOND_CAT_STATUE:
            case EMERALD_DOG_STATUE:
            case EMERALD_CAT_STATUE:
            case LAPIS_DOG_STATUE:
            case LAPIS_CAT_STATUE:
            case REDSTONE_DOG_STATUE:
            case REDSTONE_CAT_STATUE:
            case OBSIDIAN_DOG_STATUE:
            case OBSIDIAN_CAT_STATUE:
            case QUARTZ_DOG_STATUE:
            case QUARTZ_CAT_STATUE:
            case ICE_DOG_STATUE:
            case ICE_CAT_STATUE:
                this.setBlockBounds(0.125F, 0, 0.125F, 0.875F, 2, 0.875F);
                break;
            case WOODEN_CREEPER_STATUE:
            case SANDSTONE_CREEPER_STATUE:
            case STONE_CREEPER_STATUE:
            case MOSSY_CREEPER_STATUE:
            case IRON_CREEPER_STATUE:
            case GOLDEN_CREEPER_STATUE:
            case DIAMOND_CREEPER_STATUE:
            case EMERALD_CREEPER_STATUE:
            case LAPIS_CREEPER_STATUE:
            case REDSTONE_CREEPER_STATUE:
            case OBSIDIAN_CREEPER_STATUE:
            case QUARTZ_CREEPER_STATUE:
            case ICE_CREEPER_STATUE:
                this.setBlockBounds(0.125F, 0, 0.125F, 0.875F, 2.5F, 0.875F);
                break;
        }
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0, 0, 0, 1, 1, 2);
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly,
     * and not its normal drops.
     */
    @Override
    public boolean canSilkHarvest() {
        return true;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int par1, Random random, int par3) {
        return null;
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
        return GraveStoneConfig.memorialRenderID;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (world.isRemote) {
            TileEntityGSMemorial entity = (TileEntityGSMemorial) world.getTileEntity(x, y, z);

            if (entity != null) {
                String name;
                String killerName;
                String deathText = entity.getDeathTextComponent().getDeathText();

                if (deathText.length() != 0) {
                    //entityPlayer.sendChatToPlayer(ChatMessageComponent.func_111066_d(deathText));

                    if (entity.getDeathTextComponent().isLocalized()) {
                        name = entity.getDeathTextComponent().getName();
                        if (name.length() != 0) {
                            killerName = ModGraveStone.proxy.getLocalizedEntityName(entity.getDeathTextComponent().getKillerName());

                            if (killerName.length() == 0) {
                                player.addChatComponentMessage(new ChatComponentTranslation(deathText, new Object[]{name}));
                            } else {
                                player.addChatComponentMessage(new ChatComponentTranslation(deathText, new Object[]{name, killerName}));
                            }
                            return false;
                        }
                    }
                    player.addChatComponentMessage(new ChatComponentTranslation(deathText));
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
        return new TileEntityGSMemorial(world);
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
     * Returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (byte i = 0; i < TAB_MEMORIALS.length; i++) {
            ItemStack stack = new ItemStack(item, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", TAB_MEMORIALS[i]);
            stack.setTagCompound(nbt);
            list.add(stack);
        }
    }

    /**
     * Called when the block is attempted to be harvested
     */
    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player) {
        player.addExhaustion(0.025F);

        if (EnchantmentHelper.getSilkTouchModifier(player)) {
            ItemStack itemStack = getBlockItemStack(world, x, y, z);

            if (itemStack != null) {
                this.dropBlockAsItem(world, x, y, z, itemStack);
            }
        }
    }

    private ItemStack getBlockItemStack(World world, int x, int y, int z) {
        ItemStack itemStack = this.createStackedBlock(0);
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            if (tileEntity.getDeathTextComponent().isLocalized()) {
                nbt.setBoolean("isLocalized", true);
                nbt.setString("name", tileEntity.getDeathTextComponent().getName());
                nbt.setString("KillerName", tileEntity.getDeathTextComponent().getKillerName());
            }
            nbt.setString("DeathText", tileEntity.getDeathTextComponent().getDeathText());
            nbt.setByte("GraveType", tileEntity.getGraveTypeNum());
            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it.
     * (i, j, k) are the coordinates of the block and l is the block's
     * subtype/damage.
     */
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
    }

    /*
     * Drop sword as item
     */
    public void dropCreeperMemorial(World world, int x, int y, int z) {
        byte memorialType = BlockGSMemorial.getMemorialType(new Random(), 4);
        ItemStack itemStack = new ItemStack(this);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte("GraveType", memorialType);
        itemStack.setTagCompound(nbt);
        this.dropBlockAsItem(world, x, y, z, itemStack);
    }
}
