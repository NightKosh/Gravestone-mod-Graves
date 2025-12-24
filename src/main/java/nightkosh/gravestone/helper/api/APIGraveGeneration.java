package nightkosh.gravestone.helper.api;

import nightkosh.gravestone.api.IGraveGeneration;
import nightkosh.gravestone.api.death_handler.*;
import nightkosh.gravestone.api.grave_items.*;
import nightkosh.gravestone.api.grave_position.IGravePositionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class APIGraveGeneration implements IGraveGeneration {
    public static final IGraveGeneration INSTANCE = new APIGraveGeneration();

    public static final List<IPlayerDeathHandler> PLAYER_DEATH_HANDLERS = new ArrayList<>();
    public static final List<IVillagerDeathHandler> VILLAGER_DEATH_HANDLERS = new ArrayList<>();
    public static final List<IDogDeathHandler> DOG_DEATH_HANDLERS = new ArrayList<>();
    public static final List<ICatDeathHandler> CAT_DEATH_HANDLERS = new ArrayList<>();
    public static final List<IHorseDeathHandler> HORSE_DEATH_HANDLERS = new ArrayList<>();

    public static final List<ICustomEntityDeathHandler> CUSTOM_ENTITY_DEATH_HANDLERS = new ArrayList<>();

    @Override
    public void addPlayerDeathHandler(IPlayerDeathHandler playerDeathHandler) {
        if (playerDeathHandler != null) {
            PLAYER_DEATH_HANDLERS.add(playerDeathHandler);
        }
    }

    @Override
    public void addVillagerDeathHandler(IVillagerDeathHandler villagerDeathHandler) {
        if (villagerDeathHandler != null) {
            VILLAGER_DEATH_HANDLERS.add(villagerDeathHandler);
        }
    }

    @Override
    public void addDogDeathHandler(IDogDeathHandler dogDeathHandler) {
        if (dogDeathHandler != null) {
            DOG_DEATH_HANDLERS.add(dogDeathHandler);
        }
    }

    @Override
    public void addCatDeathHandler(ICatDeathHandler catDeathHandler) {
        if (catDeathHandler != null) {
            CAT_DEATH_HANDLERS.add(catDeathHandler);
        }
    }

    @Override
    public void addHorseDeathHandler(IHorseDeathHandler horseDeathHandler) {
        if (horseDeathHandler != null) {
            HORSE_DEATH_HANDLERS.add(horseDeathHandler);
        }
    }


    public static final List<IPlayerItems> PLAYER_ITEMS = new ArrayList<>();
    public static final List<IVillagerItems> VILLAGER_ITEMS = new ArrayList<>();
    public static final List<IDogItems> DOG_ITEMS = new ArrayList<>();
    public static final List<ICatItems> CAT_ITEMS = new ArrayList<>();
    public static final List<IHorseItems> HORSE_ITEMS = new ArrayList<>();

    @Override
    public void addPlayerItemsHandler(IPlayerItems playerItems) {
        if (playerItems != null) {
            PLAYER_ITEMS.add(playerItems);
        }
    }

    @Override
    public void addVillagerItemsHandler(IVillagerItems villagerItems) {
        if (villagerItems != null) {
            VILLAGER_ITEMS.add(villagerItems);
        }
    }

    @Override
    public void addDogItemsHandler(IDogItems dogItems) {
        if (dogItems != null) {
            DOG_ITEMS.add(dogItems);
        }
    }

    @Override
    public void addCatItemsHandler(ICatItems catItems) {
        if (catItems != null) {
            CAT_ITEMS.add(catItems);
        }
    }

    @Override
    public void addHorseItemsHandler(IHorseItems horseItems) {
        if (horseItems != null) {
            HORSE_ITEMS.add(horseItems);
        }
    }

    @Override
    public void addCustomEntityDeathHandler(ICustomEntityDeathHandler customEntityDeathHandler) {
        if (customEntityDeathHandler != null) {
            CUSTOM_ENTITY_DEATH_HANDLERS.add(customEntityDeathHandler);
        }
    }

    public static final List<IGravePositionHandler> GRAVE_POSITION_HANDLERS = new ArrayList<>();

    @Override
    public void addGravePositionHandler(IGravePositionHandler gravePositionHandler) {
        if (gravePositionHandler != null) {
            GRAVE_POSITION_HANDLERS.add(gravePositionHandler);
        }
    }

}
