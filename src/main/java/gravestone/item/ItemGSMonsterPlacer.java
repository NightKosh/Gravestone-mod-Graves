package gravestone.item;

import gravestone.core.GSEntity;
import gravestone.core.GSTabs;
import gravestone.core.ModInfo;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author Portablejim
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemGSMonsterPlacer extends ItemMonsterPlacer {
    public static enum EnumEggs {
        ZOMBIE_DOG(GSEntity.ZOMBIE_DOG_NAME, 0xD7D3D3, 0x799C65),
        ZOMBIE_CAT(GSEntity.ZOMBIE_CAT_NAME, 0xEFDE7D, 0x799c65),
        SKELETON_DOG(GSEntity.SKELETON_DOG_NAME, 0xD7D3D3, 0x494949),
        SKELETON_CAT(GSEntity.SKELETON_CAT_NAME, 0xEFDE7D, 0x494949),
        SKULL_CRAWLER(GSEntity.SKULL_CRAWLER_NAME, 0x000000, 0xA80E0E),
        WITHER_SKULL_CRAWLER(GSEntity.WITHER_SKULL_CRAWLER_NAME, 0xC1C1C1, 0xA80E0E),
        ZOMBIE_SKULL_CRAWLER(GSEntity.ZOMBIE_SKULL_CRAWLER_NAME, 0x00AFAF, 0xA80E0E),
        SKELETON(GSEntity.SKELETON_NAME, 12698049, 4802889),
        ZOMBIE_HORSE(GSEntity.ZOMBIE_HORSE_NAME, 44975, 15656192),
        SKELETON_HORSE(GSEntity.SKELETON_HORSE_NAME, 12698049, 15656192),
        ZOMBIE_RAIDER(GSEntity.ZOMBIE_RAIDER_NAME, 44975, 15656192),
        SKELETON_RAIDER(GSEntity.SKELETON_RAIDER_NAME, 12698049, 15656192),
        RAVEN(GSEntity.RAVEN_NAME, 14144467, 11013646);

        private String name;
        private int backgroundColor;
        private int foregroundColor;

        private EnumEggs(String name, int backgroundColor, int foregroundColor) {
            this.name = name;
            this.backgroundColor = backgroundColor;
            this.foregroundColor = foregroundColor;
        }

        public static EnumEggs getById(int id) {
            if (id > EnumEggs.values().length || id < 0) {
                return ZOMBIE_DOG;
            }
            return EnumEggs.values()[id];
        }

        public String getName() {
            return name;
        }

        public int getBackgroundColor() {
            return backgroundColor;
        }

        public int getForegroundColor() {
            return foregroundColor;
        }
    }

    public ItemGSMonsterPlacer() {
        this.setHasSubtypes(true);
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setUnlocalizedName("monsterPlacer");
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        StringBuilder str = new StringBuilder();
        str.append(StatCollector.translateToLocal(this.getUnlocalizedName() + ".name").trim());

        String name = EnumEggs.getById(itemStack.getItemDamage()).getName();
        if (StringUtils.isNotBlank(name) ) {
            str.append(" ").append(StatCollector.translateToLocal("entity." + name + ".name"));
        }

        return str.toString();
    }

    @Override
    public int getColorFromItemStack(ItemStack item, int colorID) {
        int itemDamage = item.getItemDamage();
        if (itemDamage >= 0 && itemDamage < EnumEggs.values().length) {
            if ((colorID & 1) == 0) {
                return EnumEggs.getById(itemDamage).getBackgroundColor();
            } else {
                return EnumEggs.getById(itemDamage).getForegroundColor();
            }
        }
        return 0xFFFFFF;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, BlockPos blockPos, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else {
            IBlockState block = world.getBlockState(blockPos);
            double d0 = 0;
            if (facing == EnumFacing.UP && block instanceof BlockFence) {
                d0 = 0.5;
            }

            blockPos = blockPos.offset(facing);
            Entity entity = spawnCreature(world, item.getItemDamage(), blockPos.getX() + 0.5, blockPos.getY() + d0, blockPos.getZ() + 0.5);
            if (entity != null) {
                if (entity instanceof EntityLivingBase && item.hasDisplayName()) {
                    ((EntityLiving) entity).setCustomNameTag(item.getDisplayName());
                }

                if (!player.capabilities.isCreativeMode) {
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
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
        if (world.isRemote) {
            return item;
        } else {
            MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

            if (movingobjectposition == null) {
                return item;
            } else {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    if (!world.canMineBlockBody(player, movingobjectposition.getBlockPos())) {
                        return item;
                    }

                    if (!player.canPlayerEdit(movingobjectposition.getBlockPos(), movingobjectposition.sideHit, item)) {
                        return item;
                    }

                    if (world.getBlockState(movingobjectposition.getBlockPos()).getBlock() instanceof BlockLiquid) {
                        Entity entity = spawnCreature(world, item.getItemDamage(), movingobjectposition.getBlockPos().getX(),
                                movingobjectposition.getBlockPos().getY(), movingobjectposition.getBlockPos().getZ());

                        if (entity != null) {
                            if (entity instanceof EntityLivingBase && item.hasDisplayName()) {
                                entity.setCustomNameTag(item.getDisplayName());
                            }

                            if (!player.capabilities.isCreativeMode) {
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
        if (world.isRemote || damageValue < 0 || damageValue >= EnumEggs.values().length) {
            return null;
        }

        String fullEntityName = String.format("%s.%s", ModInfo.ID, EnumEggs.getById(damageValue).getName());
        Entity entity = EntityList.createEntityByName(fullEntityName, world);

        if (entity != null && entity instanceof EntityLivingBase) {
            EntityLiving entityliving = (EntityLiving) entity;
            entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360), 0);
            entityliving.rotationYawHead = entityliving.rotationYaw;
            entityliving.renderYawOffset = entityliving.rotationYaw;
            entityliving.func_180482_a(world.getDifficultyForLocation(new BlockPos(entityliving)), (IEntityLivingData) null);
            world.spawnEntityInWorld(entity);
            entityliving.playLivingSound();
        }

        return entity;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List subitems) {
        for (int i = 0; i < EnumEggs.values().length; i++) {
            subitems.add(new ItemStack(item, 1, i));
        }
    }
}
