package GraveStone;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EventHookGSGraveStone {

    @ForgeSubscribe
    public void onEntityLivingDeath(LivingDeathEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (GraveStoneConfig.generatePlayerGraves && event.entityLiving instanceof EntityPlayer) {
                createPlayerGrave((EntityPlayer) event.entity, event);
            } else {
                if (GraveStoneConfig.generateVillagerGraves && event.entity instanceof EntityVillager) {
                    createGrave(event.entity, event, null);
                }

                if (GraveStoneConfig.generatePetGraves && event.entity instanceof EntityTameable) {
                    //createPetGrave(event.entity, event);
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
            createGrave(player, event, items);
        } else {
            createGrave(player, event, null);
        }
    }

    private void createGrave(Entity entity, LivingDeathEvent event, ItemStack[] items) {
        mod_GraveStone.graveStone.createOnDeath(entity.worldObj, (int) entity.posX, (int) entity.posY, (int) entity.posZ - 1,
                event.source.getDeathMessage((EntityLiving) entity), MathHelper.floor_float(entity.rotationYaw), items);
    }

    private void createPetGrave(EntityLiving entity, LivingDeathEvent event) {
        EntityTameable pet = (EntityTameable) entity;
        if (pet.isTamed()) {
            createGrave(entity, event, null);
            // Create grave
//            mod_GraveStone.graveStone.createOnDeath(entity.worldObj, (int) entity.posX, (int) entity.posY, (int) entity.posZ - 1,
//                    event.source.getDeathMessage((EntityLiving) entity), MathHelper.floor_float(entity.rotationYaw), null);
        }
    }
}
