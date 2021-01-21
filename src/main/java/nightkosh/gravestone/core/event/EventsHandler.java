package nightkosh.gravestone.core.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nightkosh.gravestone.api.death_handler.*;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.MobHandler;
import nightkosh.gravestone.core.logger.GravesLogger;
import nightkosh.gravestone.helper.BackupsHelper;
import nightkosh.gravestone.helper.GraveGenerationHelper;
import nightkosh.gravestone.helper.api.APIGraveGeneration;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EventsHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            BackupsHelper.clonePlayer(event.getOriginal(), event.getEntityPlayer());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onEntityLivingDeath(LivingDeathEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (!Config.generateGravesInLava && event.getSource().damageType.equals("lava")) {
                return;
            }

            if (Config.generateVillagerGraves && event.getEntity() instanceof EntityVillager) {
                EntityVillager villager = (EntityVillager) event.getEntity();
                for (IVillagerDeathHandler villagerDeathHandler : APIGraveGeneration.VILLAGER_DEATH_HANDLERS) {
                    if (villagerDeathHandler.cancelGraveGeneration(villager, event.getSource())) {
                        return;
                    }
                }
                GraveGenerationHelper.createVillagerGrave(villager, event.getSource());
                return;
            } else if (Config.generatePetGraves) {
                if (event.getEntity() instanceof EntityTameable) {
                    if (event.getEntity() instanceof EntityWolf) {
                        EntityWolf dog = (EntityWolf) event.getEntity();
                        for (IDogDeathHandler dogDeathHandler : APIGraveGeneration.DOG_DEATH_HANDLERS) {
                            if (dogDeathHandler.cancelGraveGeneration(dog, event.getSource())) {
                                return;
                            }
                        }
                        GraveGenerationHelper.createDogGrave(dog, event.getSource());
                        return;
                    } else if (event.getEntity() instanceof EntityOcelot) {
                        EntityOcelot cat = (EntityOcelot) event.getEntity();
                        for (ICatDeathHandler catDeathHandler : APIGraveGeneration.CAT_DEATH_HANDLERS) {
                            if (catDeathHandler.cancelGraveGeneration(cat, event.getSource())) {
                                return;
                            }
                        }
                        GraveGenerationHelper.createCatGrave(cat, event.getSource());
                        return;
                    }
                } else if (event.getEntity() instanceof AbstractHorse) {
                    AbstractHorse horse = (AbstractHorse) event.getEntity();
                    for (IHorseDeathHandler horseDeathHandler : APIGraveGeneration.HORSE_DEATH_HANDLERS) {
                        if (horseDeathHandler.cancelGraveGeneration(horse, event.getSource())) {
                            return;
                        }
                    }
                    GraveGenerationHelper.createHorseGrave(horse, event.getSource());
                    return;
                }
            }

            for (ICustomEntityDeathHandler customEntityDeathHandler : APIGraveGeneration.CUSTOM_ENTITY_DEATH_HANDLERS) {
                if (event.getEntity().getClass().equals(customEntityDeathHandler.getEntityClass()) &&
                        customEntityDeathHandler.canGenerateGrave(event.getEntity(), event.getSource())) {
                    GraveGenerationHelper.createCustomGrave(event.getEntity(), event, customEntityDeathHandler);
                    return;
                }
            }
        }
    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onPlayerDrops(PlayerDropsEvent event) {

        if (event.isCanceled()) {
            return;
        }

        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (!Config.generateGravesInLava && event.getSource().damageType.equals("lava")) {
                return;
            }

            if (Config.generatePlayerGraves && event.getEntityLiving() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getEntity();
                if (!Config.playerGravesDimensionalBlackList.contains(player.dimension)) {
                    for (IPlayerDeathHandler playerDeathHandler : APIGraveGeneration.PLAYER_DEATH_HANDLERS) {
                        if (playerDeathHandler.cancelGraveGeneration(player, event.getSource())) {
                            return;
                        }
                    }
                    GraveGenerationHelper.createPlayerGrave(player, event.getDrops(), event.getSource(), MobHandler.getAndRemoveSpawnTime(player));
                }
            }
        }
    }

    @SubscribeEvent
    public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            Entity entity = event.getEntity();
            if (entity instanceof EntityVillager ||
                    entity instanceof EntityWolf ||
                    entity instanceof EntityOcelot ||
                    entity instanceof EntityHorse) {
                MobHandler.setMobSpawnTime(event.getEntity());
            }
        }
    }

    // TODO remove mobs info at despawn

    @SubscribeEvent
    public void worldLoading(WorldEvent.Load event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            MobHandler.loadMobsSpawnTime(event.getWorld());
            GravesLogger.setWorldDirectory(event.getWorld().getSaveHandler().getWorldDirectory());
        }
    }

    //TODO remove #245 !!!!!!!!!!!!!!!!!!!!!!!
//    @SubscribeEvent
//    public void onChunkLoad(ChunkEvent.Load event) {
//        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
//            Chunk chunk = event.getChunk();
//            chunk.getTileEntityMap().entrySet().stream().filter(entry -> entry.getValue() instanceof TileEntityGrave).forEach(entry -> {
//                GraveStoneHelper.replaceOldGraveByNew(event.getWorld(), entry.getKey());
//            });
//        }
//
//    }
}
