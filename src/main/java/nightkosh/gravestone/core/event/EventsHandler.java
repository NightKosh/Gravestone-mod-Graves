package nightkosh.gravestone.core.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nightkosh.gravestone.api.death_handler.*;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.core.MobHandler;
import nightkosh.gravestone.core.logger.GravesLogger;
import nightkosh.gravestone.helper.BackupsHelper;
import nightkosh.gravestone.helper.GraveGenerationHelper;
import nightkosh.gravestone.helper.api.APIGraveGeneration;

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
            BackupsHelper.clonePlayer(event.getOriginal(), event.getPlayer());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onEntityLivingDeath(LivingDeathEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (!GSConfigs.GENERATE_GRAVES_IN_LAVA.get() && event.getSource().damageType.equals("lava")) {
                return;
            }

            if (GSConfigs.GENERATE_VILLAGER_GRAVES.get() && event.getEntity() instanceof Villager villager) {
                for (IVillagerDeathHandler villagerDeathHandler : APIGraveGeneration.VILLAGER_DEATH_HANDLERS) {
                    if (villagerDeathHandler.cancelGraveGeneration(villager, event.getSource())) {
                        return;
                    }
                }
                GraveGenerationHelper.createVillagerGrave(villager, event.getSource());
                return;
            } else if (GSConfigs.GENERATE_PET_GRAVES.get()) {
                if (event.getEntity() instanceof TamableAnimal) {
                    if (event.getEntity() instanceof Wolf dog) {
                        for (IDogDeathHandler dogDeathHandler : APIGraveGeneration.DOG_DEATH_HANDLERS) {
                            if (dogDeathHandler.cancelGraveGeneration(dog, event.getSource())) {
                                return;
                            }
                        }
                        GraveGenerationHelper.createDogGrave(dog, event.getSource());
                        return;
                    } else if (event.getEntity() instanceof Cat cat) {
                        for (ICatDeathHandler catDeathHandler : APIGraveGeneration.CAT_DEATH_HANDLERS) {
                            if (catDeathHandler.cancelGraveGeneration(cat, event.getSource())) {
                                return;
                            }
                        }
                        GraveGenerationHelper.createCatGrave(cat, event.getSource());
                        return;
                    }
                } else if (event.getEntity() instanceof AbstractHorse horse) {
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
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (!GSConfigs.GENERATE_GRAVES_IN_LAVA.get() && event.getSource().damageType.equals("lava")) {
                return;
            }

            if (GSConfigs.GENERATE_PLAYER_GRAVES.get() && event.getEntityLiving() instanceof Player player) {
//                if (!GSConfigs.playerGravesDimensionalBlackList.contains(player.dimension)) {//TODO
                if (true) {
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
            if (entity instanceof Villager ||
                    entity instanceof Wolf ||
                    entity instanceof Cat ||
                    entity instanceof Horse) {
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
