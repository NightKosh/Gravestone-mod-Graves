package nightkosh.gravestone.core;

import net.minecraft.resources.ResourceLocation;
import nightkosh.gravestone.api.ModInfo;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Resources {

    public static final String BLOCK_LOCATION = "textures/blocks/";
    public static final String GRAVES_LOCATION = "textures/graves/";
    public static final String STATUES_LOCATION = "textures/statues/";
    public static final String SWORDS_LOCATION = "textures/swords/";

    // gui
    public static final ResourceLocation CHEST_GUI = fromNamespaceAndPath("minecraft", "textures/gui/container/generic_54.png");

    // skull
    public static final ResourceLocation SKELETON_SKULL = fromNamespaceAndPath(ModInfo.ID, BLOCK_LOCATION + "skeleton_skull.png");
    public static final ResourceLocation WITHER_SKULL = fromNamespaceAndPath(ModInfo.ID, BLOCK_LOCATION + "wither_skull.png");
    public static final ResourceLocation ZOMBIE_SKULL = fromNamespaceAndPath(ModInfo.ID, BLOCK_LOCATION + "zombie_skull.png");
    public static final ResourceLocation STRAY_SKULL = fromNamespaceAndPath(ModInfo.ID, BLOCK_LOCATION + "stray_skull.png");

    // entities
    public static final ResourceLocation SKELETON = fromNamespaceAndPath("minecraft", "textures/entity/skeleton/skeleton.png");
    public static final ResourceLocation WITHER_SKELETON = fromNamespaceAndPath("minecraft", "textures/entity/skeleton/wither_skeleton.png");

    // models - graves
    // vertical plates
    public static final ResourceLocation GRAVE_WOODEN_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/wooden.png");
    public static final ResourceLocation GRAVE_SANDSTONE_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/sandstone.png");
    public static final ResourceLocation GRAVE_RED_SANDSTONE_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/redsandstone.png");
    public static final ResourceLocation GRAVE_STONE_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/stone.png");
    public static final ResourceLocation GRAVE_DIORITE_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/diorite.png");
    public static final ResourceLocation GRAVE_ANDESITE_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/andesite.png");
    public static final ResourceLocation GRAVE_GRANITE_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/granite.png");
    public static final ResourceLocation GRAVE_IRON_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/iron.png");
    public static final ResourceLocation GRAVE_GOLDEN_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/golden.png");
    public static final ResourceLocation GRAVE_DIAMOND_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/diamond.png");
    public static final ResourceLocation GRAVE_EMERALD_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/emerald.png");
    public static final ResourceLocation GRAVE_LAPIS_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/lapis.png");
    public static final ResourceLocation GRAVE_REDSTONE_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/redstone.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/obsidian.png");
    public static final ResourceLocation GRAVE_QUARTZ_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/quartz.png");
    public static final ResourceLocation GRAVE_PRIZMARINE_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/prizmarine.png");
    public static final ResourceLocation GRAVE_ICE_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/ice.png");
    public static final ResourceLocation GRAVE_MOSSY_VERTICAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "vertical_plate/mossy.png");
    // crosses
    public static final ResourceLocation GRAVE_WOODEN_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/wooden.png");
    public static final ResourceLocation GRAVE_SANDSTONE_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/sandstone.png");
    public static final ResourceLocation GRAVE_RED_SANDSTONE_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/redsandstone.png");
    public static final ResourceLocation GRAVE_STONE_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/stone.png");
    public static final ResourceLocation GRAVE_DIORITE_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/diorite.png");
    public static final ResourceLocation GRAVE_ANDESITE_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/andesite.png");
    public static final ResourceLocation GRAVE_GRANITE_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/granite.png");
    public static final ResourceLocation GRAVE_IRON_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/iron.png");
    public static final ResourceLocation GRAVE_GOLDEN_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/golden.png");
    public static final ResourceLocation GRAVE_DIAMOND_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/diamond.png");
    public static final ResourceLocation GRAVE_EMERALD_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/emerald.png");
    public static final ResourceLocation GRAVE_LAPIS_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/lapis.png");
    public static final ResourceLocation GRAVE_REDSTONE_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/redstone.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/obsidian.png");
    public static final ResourceLocation GRAVE_QUARTZ_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/quartz.png");
    public static final ResourceLocation GRAVE_PRIZMARINE_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/prizmarine.png");
    public static final ResourceLocation GRAVE_ICE_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/ice.png");
    public static final ResourceLocation GRAVE_MOSSY_CROSS = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "cross/mossy.png");
    // horisontal plates
    public static final ResourceLocation GRAVE_WOODEN_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/wooden.png");
    public static final ResourceLocation GRAVE_SANDSTONE_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/sandstone.png");
    public static final ResourceLocation GRAVE_RED_SANDSTONE_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/redsandstone.png");
    public static final ResourceLocation GRAVE_STONE_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/stone.png");
    public static final ResourceLocation GRAVE_DIORITE_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/diorite.png");
    public static final ResourceLocation GRAVE_ANDESITE_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/andesite.png");
    public static final ResourceLocation GRAVE_GRANITE_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/granite.png");
    public static final ResourceLocation GRAVE_IRON_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/iron.png");
    public static final ResourceLocation GRAVE_GOLDEN_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/golden.png");
    public static final ResourceLocation GRAVE_DIAMOND_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/diamond.png");
    public static final ResourceLocation GRAVE_EMERALD_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/emerald.png");
    public static final ResourceLocation GRAVE_LAPIS_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/lapis.png");
    public static final ResourceLocation GRAVE_REDSTONE_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/redstone.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/obsidian.png");
    public static final ResourceLocation GRAVE_QUARTZ_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/quartz.png");
    public static final ResourceLocation GRAVE_PRIZMARINE_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/prizmarine.png");
    public static final ResourceLocation GRAVE_ICE_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/ice.png");
    public static final ResourceLocation GRAVE_MOSSY_HORISONTAL_PLATE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horizontal_plate/mossy.png");
    // horses graves
    public static final ResourceLocation GRAVE_WOODEN_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/wooden.png");
    public static final ResourceLocation GRAVE_SANDSTONE_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/sandstone.png");
    public static final ResourceLocation GRAVE_RED_SANDSTONE_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/redsandstone.png");
    public static final ResourceLocation GRAVE_STONE_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/stone.png");
    public static final ResourceLocation GRAVE_DIORITE_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/diorite.png");
    public static final ResourceLocation GRAVE_ANDESITE_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/andesite.png");
    public static final ResourceLocation GRAVE_GRANITE_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/granite.png");
    public static final ResourceLocation GRAVE_IRON_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/iron.png");
    public static final ResourceLocation GRAVE_GOLDEN_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/golden.png");
    public static final ResourceLocation GRAVE_DIAMOND_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/diamond.png");
    public static final ResourceLocation GRAVE_EMERALD_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/emerald.png");
    public static final ResourceLocation GRAVE_LAPIS_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/lapis.png");
    public static final ResourceLocation GRAVE_REDSTONE_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/redstone.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/obsidian.png");
    public static final ResourceLocation GRAVE_QUARTZ_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/quartz.png");
    public static final ResourceLocation GRAVE_PRIZMARINE_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/prizmarine.png");
    public static final ResourceLocation GRAVE_ICE_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/ice.png");
    public static final ResourceLocation GRAVE_MOSSY_HORSE_STATUE = fromNamespaceAndPath(ModInfo.ID, GRAVES_LOCATION + "horse/mossy.png");


    // obelisk
    public static final ResourceLocation WOODEN_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/wooden.png");
    public static final ResourceLocation SANDSTONE_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/sandstone.png");
    public static final ResourceLocation RED_SANDSTONE_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/redsandstone.png");
    public static final ResourceLocation STONE_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/stone.png");
    public static final ResourceLocation DIORITE_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/diorite.png");
    public static final ResourceLocation ANDESITE_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/andesite.png");
    public static final ResourceLocation GRANITE_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/granite.png");
    public static final ResourceLocation IRON_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/iron.png");
    public static final ResourceLocation GOLDEN_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/golden.png");
    public static final ResourceLocation DIAMOND_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/diamond.png");
    public static final ResourceLocation EMERALD_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/emerald.png");
    public static final ResourceLocation LAPIS_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/lapis.png");
    public static final ResourceLocation REDSTONE_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/redstone.png");
    public static final ResourceLocation OBSIDIAN_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/obsidian.png");
    public static final ResourceLocation QUARTZ_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/quartz.png");
    public static final ResourceLocation PRIZMARINE_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/prizmarine.png");
    public static final ResourceLocation ICE_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/ice.png");
    public static final ResourceLocation MOSSY_OBELISK = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "obelisk/mossy.png");
    // celtic crosses
    public static final ResourceLocation WOODEN_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/wooden.png");
    public static final ResourceLocation SANDSTONE_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/sandstone.png");
    public static final ResourceLocation RED_SANDSTONE_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/redsandstone.png");
    public static final ResourceLocation STONE_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/stone.png");
    public static final ResourceLocation DIORITE_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/diorite.png");
    public static final ResourceLocation ANDESITE_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/andesite.png");
    public static final ResourceLocation GRANITE_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/granite.png");
    public static final ResourceLocation IRON_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/iron.png");
    public static final ResourceLocation GOLDEN_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/golden.png");
    public static final ResourceLocation DIAMOND_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/diamond.png");
    public static final ResourceLocation EMERALD_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/emerald.png");
    public static final ResourceLocation LAPIS_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/lapis.png");
    public static final ResourceLocation REDSTONE_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/redstone.png");
    public static final ResourceLocation OBSIDIAN_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/obsidian.png");
    public static final ResourceLocation QUARTZ_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/quartz.png");
    public static final ResourceLocation PRIZMARINE_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/prizmarine.png");
    public static final ResourceLocation ICE_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/ice.png");
    public static final ResourceLocation MOSSY_CELTIC_CROSS = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "celtic_cross/mossy.png");
    // villagers statues
    public static final ResourceLocation WOODEN_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/wooden.png");
    public static final ResourceLocation SANDSTONE_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/sandstone.png");
    public static final ResourceLocation RED_SANDSTONE_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/redsandstone.png");
    public static final ResourceLocation STONE_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/stone.png");
    public static final ResourceLocation DIORITE_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/diorite.png");
    public static final ResourceLocation ANDESITE_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/andesite.png");
    public static final ResourceLocation GRANITE_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/granite.png");
    public static final ResourceLocation IRON_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/iron.png");
    public static final ResourceLocation GOLDEN_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/golden.png");
    public static final ResourceLocation DIAMOND_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/diamond.png");
    public static final ResourceLocation EMERALD_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/emerald.png");
    public static final ResourceLocation LAPIS_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/lapis.png");
    public static final ResourceLocation REDSTONE_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/redstone.png");
    public static final ResourceLocation OBSIDIAN_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/obsidian.png");
    public static final ResourceLocation QUARTZ_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/quartz.png");
    public static final ResourceLocation PRIZMARINE_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/prizmarine.png");
    public static final ResourceLocation ICE_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/ice.png");
    public static final ResourceLocation MOSSY_VILLAGER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "villager/mossy.png");
    // dogs statues
    public static final ResourceLocation WOODEN_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/wooden.png");
    public static final ResourceLocation SANDSTONE_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/sandstone.png");
    public static final ResourceLocation RED_SANDSTONE_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/redsandstone.png");
    public static final ResourceLocation STONE_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/stone.png");
    public static final ResourceLocation DIORITE_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/diorite.png");
    public static final ResourceLocation ANDESITE_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/andesite.png");
    public static final ResourceLocation GRANITE_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/granite.png");
    public static final ResourceLocation IRON_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/iron.png");
    public static final ResourceLocation GOLDEN_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/golden.png");
    public static final ResourceLocation DIAMOND_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/diamond.png");
    public static final ResourceLocation EMERALD_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/emerald.png");
    public static final ResourceLocation LAPIS_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/lapis.png");
    public static final ResourceLocation REDSTONE_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/redstone.png");
    public static final ResourceLocation OBSIDIAN_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/obsidian.png");
    public static final ResourceLocation QUARTZ_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/quartz.png");
    public static final ResourceLocation PRIZMARINE_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/prizmarine.png");
    public static final ResourceLocation ICE_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/ice.png");
    public static final ResourceLocation MOSSY_DOG_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "dog/mossy.png");
    // cats statues
    public static final ResourceLocation WOODEN_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/wooden.png");
    public static final ResourceLocation SANDSTONE_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/sandstone.png");
    public static final ResourceLocation RED_SANDSTONE_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/redsandstone.png");
    public static final ResourceLocation STONE_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/stone.png");
    public static final ResourceLocation DIORITE_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/diorite.png");
    public static final ResourceLocation ANDESITE_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/andesite.png");
    public static final ResourceLocation GRANITE_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/granite.png");
    public static final ResourceLocation IRON_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/iron.png");
    public static final ResourceLocation GOLDEN_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/golden.png");
    public static final ResourceLocation DIAMOND_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/diamond.png");
    public static final ResourceLocation EMERALD_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/emerald.png");
    public static final ResourceLocation LAPIS_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/lapis.png");
    public static final ResourceLocation REDSTONE_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/redstone.png");
    public static final ResourceLocation OBSIDIAN_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/obsidian.png");
    public static final ResourceLocation QUARTZ_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/quartz.png");
    public static final ResourceLocation PRIZMARINE_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/prizmarine.png");
    public static final ResourceLocation ICE_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/ice.png");
    public static final ResourceLocation MOSSY_CAT_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "cat/mossy.png");
    // creepers statues
    public static final ResourceLocation WOODEN_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/wooden.png");
    public static final ResourceLocation SANDSTONE_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/sandstone.png");
    public static final ResourceLocation RED_SANDSTONE_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/redsandstone.png");
    public static final ResourceLocation STONE_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/stone.png");
    public static final ResourceLocation DIORITE_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/diorite.png");
    public static final ResourceLocation ANDESITE_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/andesite.png");
    public static final ResourceLocation GRANITE_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/granite.png");
    public static final ResourceLocation IRON_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/iron.png");
    public static final ResourceLocation GOLDEN_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/golden.png");
    public static final ResourceLocation DIAMOND_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/diamond.png");
    public static final ResourceLocation EMERALD_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/emerald.png");
    public static final ResourceLocation LAPIS_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/lapis.png");
    public static final ResourceLocation REDSTONE_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/redstone.png");
    public static final ResourceLocation OBSIDIAN_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/obsidian.png");
    public static final ResourceLocation QUARTZ_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/quartz.png");
    public static final ResourceLocation PRIZMARINE_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/prizmarine.png");
    public static final ResourceLocation ICE_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/ice.png");
    public static final ResourceLocation MOSSY_CREEPER_STATUE = fromNamespaceAndPath(ModInfo.ID, STATUES_LOCATION + "creeper/mossy.png");
    // swords
    public static final ResourceLocation WOODEN_SWORD = fromNamespaceAndPath(ModInfo.ID, SWORDS_LOCATION + "wooden.png");
    public static final ResourceLocation STONE_SWORD = fromNamespaceAndPath(ModInfo.ID, SWORDS_LOCATION + "stone.png");
    public static final ResourceLocation IRON_SWORD = fromNamespaceAndPath(ModInfo.ID, SWORDS_LOCATION + "iron.png");
    public static final ResourceLocation GOLDEN_SWORD = fromNamespaceAndPath(ModInfo.ID, SWORDS_LOCATION + "golden.png");
    public static final ResourceLocation DIAMOND_SWORD = fromNamespaceAndPath(ModInfo.ID, SWORDS_LOCATION + "diamond.png");

    // models - parts
    public static final ResourceLocation CREEPER_AURA = fromNamespaceAndPath("minecraft", "textures/entity/creeper/creeper_armor.png");
    public static final ResourceLocation SWORD_AURA = fromNamespaceAndPath("minecraft", "textures/misc/enchanted_item_glint.png");

}
