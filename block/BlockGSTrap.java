package gravestone.block;

import gravestone.ModGraveStone;
import gravestone.block.enums.EnumTrap;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSPotion;
import gravestone.core.GSTabs;
import gravestone.core.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSTrap extends Block {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumTrap.class);
    public static final String NIGHT_STONE_CURSE_TEXT = "block.trap.curse";

    public BlockGSTrap() {
        super(Material.rock);
        this.setStepSound(Block.soundTypeStone);
        this.setUnlocalizedName("trap.night");
        this.setHardness(4.5F);
        this.setResistance(5);
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setHarvestLevel("pickaxe", 1);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        switch ((EnumTrap) state.getValue(VARIANT)) {
            case THUNDER_STONE:
                return Item.getItemFromBlock(Blocks.stonebrick);
            case NIGHT_STONE:
            default:
                return Item.getItemFromBlock(Blocks.nether_brick);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and
     * wood.
     */
    @Override
    public int damageDropped(IBlockState state) {
        return ((Enum) state.getValue(VARIANT)).ordinal();
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
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) {
        if (entity instanceof EntityPlayer) {
            if (world.getBlockState(pos).getValue(VARIANT) == EnumTrap.NIGHT_STONE) {
                if (GraveStoneConfig.enableNightStone) {
                    long time = world.getWorldTime();
                    long dayTime = TimeHelper.getDayTime(time);
                    if (dayTime < TimeHelper.PRE_NIGHT || dayTime > TimeHelper.PRE_MORNING) {
                        time = time - dayTime + TimeHelper.PRE_NIGHT;
                        world.setWorldTime(time);
                        if (GraveStoneConfig.showNightStoneMessage) {
                            ((EntityPlayer) entity).addChatComponentMessage(new ChatComponentTranslation(ModGraveStone.proxy.getLocalizedString(NIGHT_STONE_CURSE_TEXT)));
                        }
                    } else if (dayTime > 20000 && dayTime < TimeHelper.PRE_MORNING) {
                        time = time - dayTime + TimeHelper.NIGHT;
                        world.setWorldTime(time);
                    }
                    ((EntityPlayer) entity).addPotionEffect(new PotionEffect(GSPotion.curse.getId(), 1200));
                }
            } else {
                if (GraveStoneConfig.enableThunderStone) {
                    if (!world.isThundering() || world.getWorldInfo().getThunderTime() < 1000) {
                        world.getWorldInfo().setRaining(true);
                        world.getWorldInfo().setRainTime(10000);
                        world.getWorldInfo().setThundering(true);
                        world.getWorldInfo().setThunderTime(10000);
                    }
                }
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumTrap.getById((byte) meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumTrap) state.getValue(VARIANT)).ordinal();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{VARIANT});
    }

    /**
     * Returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (byte meta = 0; meta < EnumTrap.values().length; meta++) {
            list.add(new ItemStack(item, 1, meta));
        }
    }
}
