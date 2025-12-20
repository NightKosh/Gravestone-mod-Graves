package nightkosh.gravestone.core.event;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.api.death_handler.*;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.core.MobHandler;
import nightkosh.gravestone.core.logger.GravesLogger;
import nightkosh.gravestone.helper.BackupsHelper;
import nightkosh.gravestone.helper.GraveGenerationHelper;
import nightkosh.gravestone.helper.api.APIGraveGeneration;

import static nightkosh.gravestone.ModGraveStone.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod.EventBusSubscriber(modid = ModInfo.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventsHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            //TODO
//            BackupsHelper.clonePlayer(event.getOriginal(), event.getPlayer());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onEntityLivingDeath(LivingDeathEvent event) {
        if (!event.getEntity().level.isClientSide()) {
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("LivingDeathEvent event triggered");
            }

            if (!GSConfigs.GENERATE_GRAVES_IN_LAVA.get() && event.getSource().is(DamageTypes.LAVA)) {
                if (GSConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("Event skipped - death in lava");
                }
                return;
            }

            if (GSConfigs.GENERATE_VILLAGER_GRAVES.get() && event.getEntity() instanceof Villager villager) {
                for (var villagerDeathHandler : APIGraveGeneration.VILLAGER_DEATH_HANDLERS) {
                    if (villagerDeathHandler.cancelGraveGeneration(villager, event.getSource())) {
                        if (GSConfigs.DEBUG_MODE.get()) {
                            LOGGER.info("Villager grave generation cancelled");
                        }
                        return;
                    }
                }
                GraveGenerationHelper.createVillagerGrave(villager, event.getSource());
                return;
            } else if (GSConfigs.GENERATE_PET_GRAVES.get()) {
                if (event.getEntity() instanceof TamableAnimal) {
                    if (event.getEntity() instanceof Wolf dog) {
                        for (var dogDeathHandler : APIGraveGeneration.DOG_DEATH_HANDLERS) {
                            if (dogDeathHandler.cancelGraveGeneration(dog, event.getSource())) {
                                if (GSConfigs.DEBUG_MODE.get()) {
                                    LOGGER.info("Dog grave generation cancelled");
                                }
                                return;
                            }
                        }
                        GraveGenerationHelper.createDogGrave(dog, event.getSource());
                        return;
                    } else if (event.getEntity() instanceof Cat cat) {
                        for (var catDeathHandler : APIGraveGeneration.CAT_DEATH_HANDLERS) {
                            if (catDeathHandler.cancelGraveGeneration(cat, event.getSource())) {
                                if (GSConfigs.DEBUG_MODE.get()) {
                                    LOGGER.info("Cat grave generation cancelled");
                                }
                                return;
                            }
                        }
                        GraveGenerationHelper.createCatGrave(cat, event.getSource());
                        return;
                    }
                } else if (event.getEntity() instanceof AbstractHorse horse) {
                    for (var horseDeathHandler : APIGraveGeneration.HORSE_DEATH_HANDLERS) {
                        if (horseDeathHandler.cancelGraveGeneration(horse, event.getSource())) {
                            if (GSConfigs.DEBUG_MODE.get()) {
                                LOGGER.info("Horse grave generation cancelled");
                            }
                            return;
                        }
                    }
                    GraveGenerationHelper.createHorseGrave(horse, event.getSource());
                    return;
                }
            }

            for (var customEntityDeathHandler : APIGraveGeneration.CUSTOM_ENTITY_DEATH_HANDLERS) {
                if (event.getEntity().getClass().equals(customEntityDeathHandler.getEntityClass()) &&
                        customEntityDeathHandler.canGenerateGrave(event.getEntity(), event.getSource())) {
                    GraveGenerationHelper.createCustomGrave(event.getEntity(), event, customEntityDeathHandler);
                    return;
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerDrops(LivingDropsEvent event) {
        if (!event.getEntity().level.isClientSide()) {
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("LivingDropsEvent event triggered");
            }

            if (!GSConfigs.GENERATE_GRAVES_IN_LAVA.get() && event.getSource().is(DamageTypes.LAVA)) {
                if (GSConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("Event skipped - death in lava");
                }
                return;
            }

            if (GSConfigs.GENERATE_PLAYER_GRAVES.get() && event.getEntity() instanceof Player player) {
                if (true) { //TODO !GSConfigs.playerGravesDimensionalBlackList.contains(player.dimension)) {
                    for (var playerDeathHandler : APIGraveGeneration.PLAYER_DEATH_HANDLERS) {
                        if (playerDeathHandler.cancelGraveGeneration(player, event.getSource())) {
                            if (GSConfigs.DEBUG_MODE.get()) {
                                LOGGER.info("Player {} grave generation cancelled", player.getScoreboardName());
                            }
                            return;
                        }
                    }

                    GraveGenerationHelper.createPlayerGrave(player, event.getDrops(), event.getSource(), MobHandler.getAndRemoveSpawnTime(player));
                }
            }
        }
    }

    // TODO
//
//    @SubscribeEvent
//    public static void entityJoinWorldEvent(EntityJoinWorldEvent event) {
//        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
//            Entity entity = event.getEntity();
//            if (entity instanceof Villager ||
//                    entity instanceof Wolf ||
//                    entity instanceof Cat ||
//                    entity instanceof Horse) {
//                MobHandler.setMobSpawnTime(event.getEntity());
//            }
//        }
//    }
//
//    // TODO remove mobs info at despawn
//
//    @SubscribeEvent
//    public static void worldLoading(WorldEvent.Load event) {
//        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
//            MobHandler.loadMobsSpawnTime(event.getWorld());
//            GravesLogger.setWorldDirectory(event.getWorld().getSaveHandler().getWorldDirectory());
//        }
//    }

    //TODO
//    @SubscribeEvent
//    public static void onChunkLoad(ChunkEvent.Load event) {
//        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
//            Chunk chunk = event.getChunk();
//            chunk.getTileEntityMap().entrySet().stream().filter(entry -> entry.getValue() instanceof TileEntityGrave).forEach(entry -> {
//                GraveStoneHelper.replaceOldGraveByNew(event.getWorld(), entry.getKey());
//            });
//        }
//
//    }
}
