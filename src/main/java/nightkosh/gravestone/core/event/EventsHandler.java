package nightkosh.gravestone.core.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.core.GSCommands;
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
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("PlayerEvent.Clone event triggered for {}", event.getEntity().getScoreboardName());
            }
            BackupsHelper.clonePlayer(event.getOriginal(), event.getEntity());
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

    @SubscribeEvent
    public static void entityJoinWorldEvent(EntityJoinLevelEvent event) {
        if (!event.getEntity().level.isClientSide() && event.getEntity() instanceof LivingEntity entity) {
            if (entity instanceof Villager ||
                    entity instanceof Wolf ||
                    entity instanceof Cat ||
                    entity instanceof Horse) {
                if (GSConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("EntityJoinLevelEvent event triggered for villager, wolf, cat or horse ({})",
                            entity.getScoreboardName());
                }
                MobHandler.setMobSpawnTime(entity);
            }
        }
    }

    @SubscribeEvent
    public static void entityLeaveLevelEvent(EntityLeaveLevelEvent event) {
        if (!event.getEntity().level.isClientSide() && event.getEntity() instanceof LivingEntity entity) {
            if (!entity.isAlive() &&
                    entity instanceof Villager ||
                    entity instanceof Wolf ||
                    entity instanceof Cat ||
                    entity instanceof Horse) {
                if (GSConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("EntityLeaveLevelEvent event triggered for villager, wolf, cat or horse ({})",
                            entity.getScoreboardName());
                }
                MobHandler.removeSpawnTime(entity);
            }
        }
    }

    @SubscribeEvent
    public static void onLevelLoad(LevelEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel &&
                serverLevel.dimension() == Level.OVERWORLD &&
                !serverLevel.isClientSide()) {
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("LevelEvent.Load event triggered");
            }
            var worldPath = serverLevel.getServer().getWorldPath(LevelResource.ROOT);
            MobHandler.loadMobsSpawnTime(serverLevel, worldPath);
            GravesLogger.setWorldDirectory(worldPath);
        }
    }

    @SubscribeEvent
    public static void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().level.isClientSide()) {
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("PlayerEvent.PlayerLoggedInEvent triggered for {}", event.getEntity().getScoreboardName());
            }
            MobHandler.setMobSpawnTime(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void playerRespawnInEvent(PlayerEvent.PlayerRespawnEvent event) {
        if (!event.getEntity().level.isClientSide()) {
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("PlayerEvent.PlayerRespawnEvent triggered for {}", event.getEntity().getScoreboardName());
            }
            MobHandler.setMobSpawnTime(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        if (GSConfigs.DEBUG_MODE.get()) {
            LOGGER.info("RegisterCommandsEvent triggered");
        }

        var dispatcher = event.getDispatcher();
        dispatcher.register(GSCommands.COMMANDS_LIST);
        dispatcher.register(GSCommands.GRAVE_POSITION);
        dispatcher.register(GSCommands.RESTORE_ITEMS);
    }

}
