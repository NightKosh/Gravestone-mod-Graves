package nightkosh.gravestone.core.event;

import nightkosh.gravestone.api.death_handler.*;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.MobHandler;
import nightkosh.gravestone.core.logger.GravesLogger;
import nightkosh.gravestone.helper.GraveGenerationHelper;
import nightkosh.gravestone.helper.api.APIGraveGeneration;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EventsHandler {

    // Hopefully ensure we capture items before other things do (set to high so other mods can run before if they have more specialness
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onEntityLivingDeath(LivingDeathEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (!Config.generateGravesInLava && event.source.damageType.equals("lava")) {
                return;
            }

            if (Config.generatePlayerGraves && event.entityLiving instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.entity;
                for (IPlayerDeathHandler playerDeathHandler : APIGraveGeneration.PLAYER_DEATH_HANDLERS) {
                    if (playerDeathHandler.cancelGraveGeneration(player, event.source)) {
                        return;
                    }
                }

                GraveGenerationHelper.createPlayerGrave(player, event, MobHandler.getAndRemoveSpawnTime(event.entity));
            } else {
                if (Config.generateVillagerGraves && event.entity instanceof EntityVillager) {
                    EntityVillager villager = (EntityVillager) event.entity;
                    for (IVillagerDeathHandler villagerDeathHandler : APIGraveGeneration.VILLAGER_DEATH_HANDLERS) {
                        if (villagerDeathHandler.cancelGraveGeneration(villager, event.source)) {
                            return;
                        }
                    }
                    GraveGenerationHelper.createVillagerGrave(villager, event);
                } else if (Config.generatePetGraves) {
                    if (event.entity instanceof EntityTameable) {
                        if (event.entity instanceof EntityWolf) {
                            EntityWolf dog = (EntityWolf) event.entity;
                            for (IDogDeathHandler dogDeathHandler : APIGraveGeneration.DOG_DEATH_HANDLERS) {
                                if (dogDeathHandler.cancelGraveGeneration(dog, event.source)) {
                                    return;
                                }
                            }
                            GraveGenerationHelper.createDogGrave(dog, event);
                        } else if (event.entity instanceof EntityOcelot) {
                            EntityOcelot cat = (EntityOcelot) event.entity;
                            for (ICatDeathHandler catDeathHandler : APIGraveGeneration.CAT_DEATH_HANDLERS) {
                                if (catDeathHandler.cancelGraveGeneration(cat, event.source)) {
                                    return;
                                }
                            }
                            GraveGenerationHelper.createCatGrave(cat, event);
                        }
                    } else if (event.entity instanceof EntityHorse) {
                        EntityHorse horse = (EntityHorse) event.entity;
                        for (IHorseDeathHandler horseDeathHandler : APIGraveGeneration.HORSE_DEATH_HANDLERS) {
                            if (horseDeathHandler.cancelGraveGeneration(horse, event.source)) {
                                return;
                            }
                        }
                        GraveGenerationHelper.createHorseGrave(horse, event);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            Entity entity = event.entity;
            if (entity instanceof EntityVillager ||
                    entity instanceof EntityWolf ||
                    entity instanceof EntityOcelot ||
                    entity instanceof EntityHorse) {
                MobHandler.setMobSpawnTime(event.entity);
            }
        }
    }

    // TODO remove mobs info at despawn

    @SubscribeEvent
    public void worldLoading(WorldEvent.Load event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            MobHandler.loadMobsSpawnTime(event.world);
            GravesLogger.setWorldDirectory(event.world.getSaveHandler().getWorldDirectory());
        }
    }
}
