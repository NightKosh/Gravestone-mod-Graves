
package gravestone.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import gravestone.entity.item.EntityGhostlyChest;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemGhostly extends Item {

    public static final String[] NAMES = new String[]{
        "GhostlyChest"
    };
    public static final String[] ICONS_PATH = new String[]{
        ""
    };
    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public ItemGhostly(int id) {
        super(id);
        this.maxStackSize = 1;
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch);
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw);

        double xPosition = player.prevPosX + player.posX - player.prevPosX;
        double yPosition = player.prevPosY + player.posY - player.prevPosY + 1.62 - player.yOffset;
        double zPosition = player.prevPosZ + player.posZ - player.prevPosZ;
        Vec3 positionVec = world.getWorldVec3Pool().getVecFromPool(xPosition, yPosition, zPosition);

        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5;
        Vec3 vec31 = positionVec.addVector(f7 * d3, f6 * d3, f8 * d3);
        MovingObjectPosition objectPosition = world.clip(positionVec, vec31, true);

        if (objectPosition == null) {
            return itemStack;
        } else {
            Vec3 vec32 = player.getLook(1);
            boolean flag = false;
            float f9 = 1;
            List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand(f9, f9, f9));

            for (int i = 0; i < list.size(); i++) {
                Entity entity = (Entity) list.get(i);

                if (entity.canBeCollidedWith()) {
                    float collisionSize = entity.getCollisionBorderSize();
                    AxisAlignedBB axisalignedbb = entity.boundingBox.expand(collisionSize, collisionSize, collisionSize);

                    if (axisalignedbb.isVecInside(positionVec)) {
                        flag = true;
                    }
                }
            }

            if (flag) {
                return itemStack;
            } else {
                int x = objectPosition.blockX;
                int y = objectPosition.blockY;
                int z = objectPosition.blockZ;

                EntityGhostlyChest item;
                switch (itemStack.getItemDamage()) {
                    default:
                        item = new EntityGhostlyChest(world, x, y, z);
                        break;
                }

                item.rotationYaw = (MathHelper.floor_double(player.rotationYaw * 4 / 360F + 0.5) & 3 - 1) * 90;

                if (!world.getCollidingBoundingBoxes(item, item.boundingBox.expand(-0.1, -0.1, -0.1)).isEmpty()) {
                    return itemStack;
                }

                if (!world.isRemote) {
                    world.spawnEntityInWorld(item);
                }

                if (!player.capabilities.isCreativeMode) {
                    --itemStack.stackSize;
                }
            }

            return itemStack;
        }
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye
     * returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(int id, CreativeTabs tab, List list) {
        for (int i = 0; i < NAMES.length; i++) {
            if (i == 0 || i == 3) //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            {
                list.add(new ItemStack(id, 1, i));
            }
        }
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int damage) {
        if (damage < 0 || damage >= NAMES.length) {
            damage = 0;
        }

        return this.icons[damage];
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an
     * ItemStack so different stacks can have different names based on their
     * damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int i = stack.getItemDamage();
        if (i < 0 || i >= NAMES.length) {
            i = 0;
        }

        return NAMES[i];
    }

    /**
     *
     * @param register
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
        this.icons = new Icon[ICONS_PATH.length];

        for (int i = 0; i < ICONS_PATH.length; i++) {
            this.icons[i] = register.registerIcon(ICONS_PATH[i]);
        }
    }
}
