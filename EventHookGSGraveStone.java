package net.minecraft.GraveStone;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EventHookGSGraveStone {

    @ForgeSubscribe
    public void onEntityLivingDeath(LivingDeathEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (event.entityLiving instanceof EntityPlayer) {
                createPlayerGrave((EntityPlayer) event.entityLiving, event);
            } else {
                if (GraveStoneConfig.generateVillagerGraves && event.entityLiving instanceof EntityVillager) {
                    createGrave(event.entityLiving, event, null);
                }

                if (GraveStoneConfig.generatePetGraves && event.entityLiving instanceof EntityTameable) {
                    //createPetGrave(event.entityLiving, event);
                }
            }
        }
    }
    
    private void createPlayerGrave(EntityPlayer player, LivingDeathEvent event) {
        ItemStack[] items = new ItemStack[40];
        System.arraycopy(player.inventory.mainInventory, 0, items, 0, 36);
        System.arraycopy(player.inventory.armorInventory, 0, items, 36, 4);
        player.inventory.clearInventory(-1, -1);
        
        createGrave(player, event, items);
    }
    
    private void createGrave(EntityLiving entity, LivingDeathEvent event, ItemStack[] items) {
        Vec3 position = entity.getPosition(1);

        // Create grave
        mod_GraveStone.graveStone.createOnDeath(entity.worldObj, (int) position.xCoord, (int) position.yCoord, (int) position.zCoord - 1,
                event.source.getDeathMessage(entity), MathHelper.floor_float(entity.rotationYaw), items);
    }

    private void createPetGrave(EntityLiving entity, LivingDeathEvent event) {
        EntityTameable pet = (EntityTameable) entity;
        if (pet.isTamed()) {
            createGrave(entity, event, null);
            //Vec3 position = entity.getPosition(1);
            // Create grave
            //mod_GraveStone.graveStone.createOnDeath(entity.worldObj, (int) position.xCoord, (int) position.yCoord, (int) position.zCoord - 1,
            //        event.source.getDeathMessage(entity), MathHelper.floor_float(entity.rotationYaw), null);
        }
    }
}
