package GraveStone;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EventHookGSGraveStone {

    @ForgeSubscribe
    public void onEntityLivingDeath(LivingDeathEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (!GraveStoneConfig.generateGravesInLava && event.source.damageType.equals("lava")) {
                return;
            }
            if (GraveStoneConfig.generatePlayerGraves && event.entityLiving instanceof EntityPlayer) {
                createPlayerGrave((EntityPlayer) event.entity, event);
            } else {
                if (GraveStoneConfig.generateVillagerGraves && event.entity instanceof EntityVillager) {
                    createGrave(event.entity, event, null, ((EntityVillager) event.entity).getAge(), (byte) 0);
                }

                if (GraveStoneConfig.generatePetGraves && event.entity instanceof EntityTameable) {
                    createPetGrave(event.entity, event);
                }
            }
        }
    }

    private void createPlayerGrave(EntityPlayer player, LivingDeathEvent event) {
        if (player.worldObj != null && !player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
            ItemStack[] items = new ItemStack[40];
            System.arraycopy(player.inventory.mainInventory, 0, items, 0, 36);
            System.arraycopy(player.inventory.armorInventory, 0, items, 36, 4);
            player.inventory.clearInventory(-1, -1);
            createGrave(player, event, items, player.getAge(), (byte) 0);
        } else {
            createGrave(player, event, null, player.getAge(), (byte) 0);
        }
    }

    private void createGrave(Entity entity, LivingDeathEvent event, ItemStack[] items, int age, byte entityType) {
        ModGraveStone.graveStone.createOnDeath(entity.worldObj, (int) entity.posX, (int) entity.posY, (int) entity.posZ - 1,
                event.source.getDeathMessage((EntityLiving) entity), MathHelper.floor_float(entity.rotationYaw), items, age, entityType);
    }

    private void createPetGrave(Entity entity, LivingDeathEvent event) {
        EntityTameable pet = (EntityTameable) entity;
        if (pet.isTamed()) {
            if (pet instanceof EntityWolf) {
                createGrave(entity, event, null, pet.getAge(), (byte) 1);
            } else if (pet instanceof EntityOcelot) {
                createGrave(entity, event, null, pet.getAge(), (byte) 2);
            }
        }
    }
}
