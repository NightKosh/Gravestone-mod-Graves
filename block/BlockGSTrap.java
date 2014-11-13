package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import gravestone.block.enums.EnumTrap;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSPotion;
import gravestone.core.GSTabs;
import gravestone.core.Resources;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSTrap extends Block {

    private static final int PRE_NIGHT = 12000;
    private static final int NIGHT = 14000;
    private static final int PRE_MORNING = 22500;

    @SideOnly(Side.CLIENT)
    private IIcon thunderStoneIcon;

    public BlockGSTrap() {
        super(Material.rock);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockName("trap.night");
        this.setHardness(4.5F);
        this.setResistance(5F);
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setHarvestLevel("pickaxe", 1);
    }


    /**
     * When this method is called, your block should register all the icons it
     * needs with the given IconRegister. This is the only chance you get to
     * register icons.
     */
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Resources.NIGHT_STONE);
        this.thunderStoneIcon = iconRegister.registerIcon(Resources.THUNDER_STONE);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture.
     */
    @Override
    public IIcon getIcon(int side, int metadata) {
        switch (metadata) {
            case 1:
                return thunderStoneIcon;
            case 0:
            default:
                return blockIcon;
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int par1, Random random, int metadata) {
        switch (metadata) {
            case 1:
                return Item.getItemFromBlock(Blocks.stonebrick);
            case 0:
            default:
                return Item.getItemFromBlock(Blocks.nether_brick);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and
     * wood.
     */
    @Override
    public int damageDropped(int metadata) {
        return metadata;
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
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        if (entity instanceof EntityPlayer) {
            int meta = world.getBlockMetadata(x, y, z);
            if (meta == 0) {
                if (GraveStoneConfig.enableNightStone) {
                    long time = world.getWorldTime();
                    long dayTime = time % 24000;
                    if (dayTime < PRE_NIGHT || dayTime > PRE_MORNING) {
                        time = time - time % 24000 + PRE_NIGHT;
                        world.setWorldTime(time);
                        if (GraveStoneConfig.showNightStoneMessage) {
                            ((EntityPlayer) entity).addChatComponentMessage(new ChatComponentTranslation(ModGraveStone.proxy.getLocalizedString("block.trap.curse")));
                        }
                    } else if (dayTime > 20000 && dayTime < PRE_MORNING) {
                        time = time - time % 24000 + NIGHT;
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
