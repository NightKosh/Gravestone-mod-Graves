package gravestone.core.event;

import gravestone.config.GraveStoneConfig;
import cpw.mods.fml.common.FMLCommonHandler;
import gravestone.GraveStoneLogger;
import gravestone.core.GSBlock;
import gravestone.core.compatibility.GSCompatibility;
import gravestone.tileentity.DeathMessageInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
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
                    createGrave(event.entity, event, null, ((EntityVillager) event.entity).getAge(), (byte) 0, false);
                }

                if (GraveStoneConfig.generatePetGraves && event.entity instanceof EntityTameable) {
                    createPetGrave(event.entity, event);
                }
            }

            // drop creeper statue if entity is a charged creeper
            if (event.entity instanceof EntityCreeper && ((EntityCreeper) event.entity).getPowered()) {
                GSBlock.memorial.dropCreeperMemorial(event.entity.worldObj, (int) event.entity.posX,
                        (int) event.entity.posY, (int) event.entity.posZ);
            }
        }
    }

    private void createPlayerGrave(EntityPlayer player, LivingDeathEvent event) {
        if (player.worldObj != null && !player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory") && GraveStoneConfig.graveItemsCount > 0) {
            ItemStack[] items = new ItemStack[46];

            if (GSCompatibility.isArsMagicaInstalled()) {
                int itemNumber = 0;
                for (int slot = 0; slot < player.inventory.getSizeInventory(); slot++) {
                    ItemStack stack = player.inventory.getStackInSlot(slot);
                    if (stack == null || GSCompatibility.hasSoulbound(stack)) {
                        continue;
                    }
                    items[itemNumber] = stack.copy();
                    itemNumber++;
                    player.inventory.setInventorySlotContents(slot, null);
                }
                if (GSCompatibility.isBattlegearInstalled()) {
                    for (int slot = GSCompatibility.BATTLEGEAR_FIRST_SLOT; slot <= GSCompatibility.BATTLEGEAR_LAST_SLOT; slot++) {
                        items[itemNumber] = player.inventory.getStackInSlot(slot);
                        itemNumber++;
                        player.inventory.setInventorySlotContents(slot, null);
                    }
                }
            } else {
                System.arraycopy(player.inventory.mainInventory, 0, items, 0, 36);
                System.arraycopy(player.inventory.armorInventory, 0, items, 36, 4);
                if (GSCompatibility.isBattlegearInstalled()) {
                    int itemSlot = 40;
                    for (int slot = GSCompatibility.BATTLEGEAR_FIRST_SLOT; slot <= GSCompatibility.BATTLEGEAR_LAST_SLOT; slot++) {
                        items[itemSlot] = player.inventory.getStackInSlot(slot);
                        itemSlot++;
                    }
                }
                player.inventory.clearInventory(-1, -1);
            }

            createGrave(player, event, items, player.getAge(), (byte) 0, false);
        } else {
            createGrave(player, event, null, player.getAge(), (byte) 0, false);
        }
    }

    private void createGrave(Entity entity, LivingDeathEvent event, ItemStack[] items, int age, byte entityType, boolean isVillager) {
        GSBlock.graveStone.createOnDeath(entity.worldObj, (int) entity.posX, (int) entity.posY, (int) entity.posZ - 1,
                getDeathMessage((EntityLivingBase) entity, event.source.damageType, isVillager),
                MathHelper.floor_float(entity.rotationYaw), items, age, entityType);
    }

    private void createPetGrave(Entity entity, LivingDeathEvent event) {
        EntityTameable pet = (EntityTameable) entity;

        if (pet.isTamed()) {
            if (pet instanceof EntityWolf) {
                createGrave(entity, event, null, pet.getAge(), (byte) 1, false);
            } else if (pet instanceof EntityOcelot) {
                createGrave(entity, event, null, pet.getAge(), (byte) 2, false);
            }
        }
    }

    private DeathMessageInfo getDeathMessage(EntityLivingBase entity, String damageType, boolean isVillager) {
        EntityLivingBase killer = entity.func_94060_bK();
        String shortString = "death.attack." + damageType;
        String fullString = shortString + ".player";

        if (killer != null) {
            String killerName;
            if (killer instanceof EntityPlayer) {
                killerName = ((EntityPlayer) killer).username;
                if (isVillager) {
                    GraveStoneLogger.logInfo("Villager was killed by " + killerName);
                }
            } else {
                killerName = EntityList.getEntityString(killer);
                if (killerName == null) {
                    killerName = "entity.generic.name";
                } else {
                    killerName = "entity." + killerName + ".name";
                }
            }
            if (StatCollector.func_94522_b(fullString)) {
                return new DeathMessageInfo(entity.getTranslatedEntityName(), fullString, killerName);
            } else {
                return new DeathMessageInfo(entity.getTranslatedEntityName(), shortString, killerName);
            }
        } else {
            return new DeathMessageInfo(entity.getTranslatedEntityName(), shortString, null);
        }
    }
}
