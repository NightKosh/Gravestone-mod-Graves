package gravestone.core.event;

import gravestone.config.GraveStoneConfig;
import cpw.mods.fml.common.FMLCommonHandler;
import gravestone.GraveStoneLogger;
import gravestone.block.BlockGSGraveStone.EnumGraveType;
import gravestone.core.GSBlock;
import gravestone.core.GSMobSpawn;
import gravestone.core.compatibility.GSCompatibilityBattlegear;
import gravestone.core.compatibility.GSCompatibilityTheCampingMod;
import gravestone.core.compatibility.GSCompatibilityisArsMagica;
import gravestone.entity.monster.EntitySkullCrawler;
import gravestone.entity.monster.EntityWitherSkullCrawler;
import gravestone.entity.monster.EntityZombieSkullCrawler;
import gravestone.tileentity.DeathMessageInfo;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
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
                    createGrave(event.entity, event, null, ((EntityVillager) event.entity).getAge(), EnumGraveType.PLAYER_GRAVES, true);
                    return;
                }
                
                if (GraveStoneConfig.generatePetGraves && event.entity instanceof EntityTameable) {
                    createPetGrave(event.entity, event);
                    return;
                }
            }
            
            if (event.entity instanceof EntitySkeleton) {
                EntitySkullCrawler crawler;
                if (GSMobSpawn.isWitherSkeleton((EntitySkeleton) event.entity)) {
                    crawler = new EntityWitherSkullCrawler(event.entity.worldObj);
                } else {
                    crawler = new EntitySkullCrawler(event.entity.worldObj);
                }
                spawnCrawler(event.entity, crawler);
            } else if (event.entity instanceof EntityZombie) {
                spawnCrawler(event.entity, new EntityZombieSkullCrawler(event.entity.worldObj));
            } else if (event.entity instanceof EntityCreeper && ((EntityCreeper) event.entity).getPowered()) {
                // drop creeper statue if entity is a charged creeper
                GSBlock.memorial.dropCreeperMemorial(event.entity.worldObj, (int) event.entity.posX,
                        (int) event.entity.posY, (int) event.entity.posZ);
            }
        }
    }
    
    private void createPlayerGrave(EntityPlayer player, LivingDeathEvent event) {
        if (player.worldObj != null && !player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory") && GraveStoneConfig.graveItemsCount > 0) {
            List<ItemStack> items = new LinkedList<ItemStack>();
            
            items.addAll(Arrays.asList(player.inventory.mainInventory));
            items.addAll(Arrays.asList(player.inventory.armorInventory));
            
            GSCompatibilityBattlegear.addItems(items, player);
            GSCompatibilityTheCampingMod.addItems(items, player);
            player.inventory.clearInventory(-1, -1);
            
            GSCompatibilityisArsMagica.getSoulboundItemsBack(items, player);
            
            createGrave(player, event, items, player.getAge(), EnumGraveType.PLAYER_GRAVES, false);
        } else {
            createGrave(player, event, null, player.getAge(), EnumGraveType.PLAYER_GRAVES, false);
        }
    }
    
    private void createGrave(Entity entity, LivingDeathEvent event, List<ItemStack> items, int age, EnumGraveType entityType, boolean isVillager) {
        GSBlock.graveStone.createOnDeath(entity.worldObj, (int) entity.posX, (int) entity.posY, (int) entity.posZ - 1,
                getDeathMessage((EntityLivingBase) entity, event.source.damageType, isVillager),
                MathHelper.floor_float(entity.rotationYaw), items, age, entityType);
    }
    
    private void createPetGrave(Entity entity, LivingDeathEvent event) {
        EntityTameable pet = (EntityTameable) entity;
        
        if (pet.isTamed()) {
            if (pet instanceof EntityWolf) {
                createGrave(entity, event, null, pet.getAge(), EnumGraveType.DOGS_GRAVES, false);
            } else if (pet instanceof EntityOcelot) {
                createGrave(entity, event, null, pet.getAge(), EnumGraveType.CATS_GRAVES, false);
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
    
    private void spawnCrawler(Entity entity, EntitySkullCrawler crawler) {
        if (entity.worldObj.rand.nextInt(10) == 0) {
            GSMobSpawn.spawnMob(entity.worldObj, crawler,
                    (int) Math.floor(entity.posX), entity.posY + 1.5, (int) Math.floor(entity.posZ),
                    entity.rotationYaw, false);
        }
    }
}
