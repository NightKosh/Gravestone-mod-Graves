package gravestone.item;

import gravestone.core.GSEntity;
import gravestone.core.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author Portablejim
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemGSMonsterPlacer extends ItemMonsterPlacer {
    public static String[] eggs = {
        GSEntity.ZOMBIE_DOG_NAME,
        GSEntity.ZOMBIE_CAT_NAME,
        GSEntity.SKEKETON_DOG_NAME,
        GSEntity.SKEKETON_CAT_NAME,
        GSEntity.SKULL_CRAWLER_NAME,
        GSEntity.WITHER_SKULL_CRAWLER_NAME,
        GSEntity.ZOMBIE_SKULL_CRAWLER_NAME
    };
    public static int[][] eggColor = {
            {0xD7D3D3, 0x799C65},
            {0xEFDE7D, 0x799c65},
            {0xD7D3D3, 0x494949},
            {0xEFDE7D, 0x494949},
            {0x000000, 0xA80E0E},
            {0xC1C1C1, 0xA80E0E},
            {0x00AFAF, 0xA80E0E}
    };

    public ItemGSMonsterPlacer() {
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("monsterPlacer");
        this.iconString = "spawn_egg";
    }

    public String getItemStackDisplayName(ItemStack p_77653_1_)
    {
        String s = ("" + StatCollector.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
        String s1 = eggs[p_77653_1_.getItemDamage()];

        if (s1 != null)
        {
            s = s + " " + StatCollector.translateToLocal("entity." + s1 + ".name");
        }

        return s;
    }

    public int getColorFromItemStack(ItemStack item, int colorID) {
        int itemDamage = item.getItemDamage();
        if(itemDamage >= 0 && itemDamage < eggColor.length) {
            return eggColor[itemDamage][colorID & 1];
        }
        return 0xFFFFFF;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            Block block = world.getBlock(x, y, z);
            x += Facing.offsetsXForSide[side];
            y += Facing.offsetsYForSide[side];
            z += Facing.offsetsZForSide[side];
            double d0 = 0.0D;

            if (side == 1 && block.getRenderType() == 11)
            {
                d0 = 0.5D;
            }

            Entity entity = spawnCreature(world, item.getItemDamage(), (double) x + 0.5D, (double) y + d0, (double) z + 0.5D);

            if (entity != null)
            {
                if (entity instanceof EntityLivingBase && item.hasDisplayName())
                {
                    ((EntityLiving)entity).setCustomNameTag(item.getDisplayName());
                }

                if (!player.capabilities.isCreativeMode)
                {
                    --item.stackSize;
                }
            }

            return true;
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        if (world.isRemote)
        {
            return item;
        }
        else
        {
            MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

            if (movingobjectposition == null)
            {
                return item;
            }
            else
            {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
                {
                    int i = movingobjectposition.blockX;
                    int j = movingobjectposition.blockY;
                    int k = movingobjectposition.blockZ;

                    if (!world.canMineBlock(player, i, j, k))
                    {
                        return item;
                    }

                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, item))
                    {
                        return item;
                    }

                    if (world.getBlock(i, j, k) instanceof BlockLiquid)
                    {
                        Entity entity = spawnCreature(world, item.getItemDamage(), (double) i, (double) j, (double) k);

                        if (entity != null)
                        {
                            if (entity instanceof EntityLivingBase && item.hasDisplayName())
                            {
                                ((EntityLiving)entity).setCustomNameTag(item.getDisplayName());
                            }

                            if (!player.capabilities.isCreativeMode)
                            {
                                --item.stackSize;
                            }
                        }
                    }
                }

                return item;
            }
        }
    }

    public static Entity spawnCreature(World world, int damageValue, double x, double y, double z) {
        if(world.isRemote || damageValue < 0 || damageValue >= eggs.length) {
            return null;
        }

        String fullEntityName = String.format("%s.%s", ModInfo.ID, eggs[damageValue]);
        Entity entity = EntityList.createEntityByName(fullEntityName, world);

        if (entity != null && entity instanceof EntityLivingBase)
        {
            EntityLiving entityliving = (EntityLiving)entity;
            entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
            entityliving.rotationYawHead = entityliving.rotationYaw;
            entityliving.renderYawOffset = entityliving.rotationYaw;
            entityliving.onSpawnWithEgg(null);
            world.spawnEntityInWorld(entity);
            entityliving.playLivingSound();
        }

        return entity;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List subitems) {
        for(int i = 0; i < eggs.length; i++) {
            subitems.add(new ItemStack(item, 1, i));
        }
    }
}
