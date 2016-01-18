package gravestone.core;

import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Resources {

    protected static final String MOD_NAME = ModInfo.ID.toLowerCase();
    protected static final String GRAVES_LOCATION = MOD_NAME + ":textures/graves/";

    // gui
    public static final ResourceLocation CHEST_GUI = new ResourceLocation("textures/gui/container/generic_54.png");

    // entities
    public static final ResourceLocation STEVE = new ResourceLocation("textures/entity/steve.png");
    public static final ResourceLocation ZOMBIE = new ResourceLocation("textures/entity/zombie/zombie.png");
    public static final ResourceLocation ZOMBIE_VILLAGER = new ResourceLocation("textures/entity/zombie/zombie_villager.png");
    public static final ResourceLocation ZOMBIE_PIGMAN = new ResourceLocation("textures/entity/zombie_pigman.png");
    public static final ResourceLocation SKELETON = new ResourceLocation("textures/entity/skeleton/skeleton.png");
    public static final ResourceLocation WITHER_SKELETON = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
    public static final ResourceLocation WITCH = new ResourceLocation("textures/entity/witch.png");

    // models - graves
    // vertical plates
    public static final ResourceLocation GRAVE_WOODEN_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "WoodenVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_SANDSTONE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "SandstoneVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_RED_SANDSTONE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "RedSandstoneVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_STONE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "StoneVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_DIORITE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "DioriteVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_ANDESITE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "AndesiteVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_GRANITE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "GraniteVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_IRON_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "IronVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_GOLDEN_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "GoldenVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_DIAMOND_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "DiamondVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_EMERALD_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "EmeraldVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_LAPIS_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "LapisVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_REDSTONE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "RedstoneVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "ObsidianVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_QUARTZ_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "QuartzVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_PRIZMARINE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "PrizmarineVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_ICE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "IceVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_MOSSY_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "MossyVerticalPlateGraveStone.png");
    // crosses
    public static final ResourceLocation GRAVE_WOODEN_CROSS = new ResourceLocation(GRAVES_LOCATION + "WoodenCrossGrave.png");
    public static final ResourceLocation GRAVE_SANDSTONE_CROSS = new ResourceLocation(GRAVES_LOCATION + "SandstoneCrossGrave.png");
    public static final ResourceLocation GRAVE_RED_SANDSTONE_CROSS = new ResourceLocation(GRAVES_LOCATION + "RedSandstoneCrossGrave.png");
    public static final ResourceLocation GRAVE_STONE_CROSS = new ResourceLocation(GRAVES_LOCATION + "StoneCrossGrave.png");
    public static final ResourceLocation GRAVE_DIORITE_CROSS = new ResourceLocation(GRAVES_LOCATION + "DioriteCrossGrave.png");
    public static final ResourceLocation GRAVE_ANDESITE_CROSS = new ResourceLocation(GRAVES_LOCATION + "AndesiteCrossGrave.png");
    public static final ResourceLocation GRAVE_GRANITE_CROSS = new ResourceLocation(GRAVES_LOCATION + "GraniteCrossGrave.png");
    public static final ResourceLocation GRAVE_IRON_CROSS = new ResourceLocation(GRAVES_LOCATION + "IronCrossGrave.png");
    public static final ResourceLocation GRAVE_GOLDEN_CROSS = new ResourceLocation(GRAVES_LOCATION + "GoldenCrossGrave.png");
    public static final ResourceLocation GRAVE_DIAMOND_CROSS = new ResourceLocation(GRAVES_LOCATION + "DiamondCrossGrave.png");
    public static final ResourceLocation GRAVE_EMERALD_CROSS = new ResourceLocation(GRAVES_LOCATION + "EmeraldCrossGrave.png");
    public static final ResourceLocation GRAVE_LAPIS_CROSS = new ResourceLocation(GRAVES_LOCATION + "LapisCrossGrave.png");
    public static final ResourceLocation GRAVE_REDSTONE_CROSS = new ResourceLocation(GRAVES_LOCATION + "RedstoneCrossGrave.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_CROSS = new ResourceLocation(GRAVES_LOCATION + "ObsidianCrossGrave.png");
    public static final ResourceLocation GRAVE_QUARTZ_CROSS = new ResourceLocation(GRAVES_LOCATION + "QuartzCrossGrave.png");
    public static final ResourceLocation GRAVE_PRIZMARINE_CROSS = new ResourceLocation(GRAVES_LOCATION + "PrizmarineCrossGrave.png");
    public static final ResourceLocation GRAVE_ICE_CROSS = new ResourceLocation(GRAVES_LOCATION + "IceCrossGrave.png");
    public static final ResourceLocation GRAVE_MOSSY_CROSS = new ResourceLocation(GRAVES_LOCATION + "MossyCrossGrave.png");
    // obelisk //TODO rename textures
    public static final ResourceLocation GRAVE_WOODEN_OBELISK = new ResourceLocation(GRAVES_LOCATION + "WoodenObeliskMemorial.png");
    public static final ResourceLocation GRAVE_SANDSTONE_OBELISK = new ResourceLocation(GRAVES_LOCATION + "SandstoneObeliskMemorial.png");
    public static final ResourceLocation GRAVE_RED_SANDSTONE_OBELISK = new ResourceLocation(GRAVES_LOCATION + "RedSandstoneObeliskMemorial.png");
    public static final ResourceLocation GRAVE_STONE_OBELISK = new ResourceLocation(GRAVES_LOCATION + "StoneObeliskMemorial.png");
    public static final ResourceLocation GRAVE_DIORITE_OBELISK = new ResourceLocation(GRAVES_LOCATION + "DioriteObeliskMemorial.png");
    public static final ResourceLocation GRAVE_ANDESITE_OBELISK = new ResourceLocation(GRAVES_LOCATION + "AndesiteObeliskMemorial.png");
    public static final ResourceLocation GRAVE_GRANITE_OBELISK = new ResourceLocation(GRAVES_LOCATION + "GraniteObeliskMemorial.png");
    public static final ResourceLocation GRAVE_IRON_OBELISK = new ResourceLocation(GRAVES_LOCATION + "IronObeliskMemorial.png");
    public static final ResourceLocation GRAVE_GOLDEN_OBELISK = new ResourceLocation(GRAVES_LOCATION + "GoldenObeliskMemorial.png");
    public static final ResourceLocation GRAVE_DIAMOND_OBELISK = new ResourceLocation(GRAVES_LOCATION + "DiamondObeliskMemorial.png");
    public static final ResourceLocation GRAVE_EMERALD_OBELISK = new ResourceLocation(GRAVES_LOCATION + "EmeraldObeliskMemorial.png");
    public static final ResourceLocation GRAVE_LAPIS_OBELISK = new ResourceLocation(GRAVES_LOCATION + "LapisObeliskMemorial.png");
    public static final ResourceLocation GRAVE_REDSTONE_OBELISK = new ResourceLocation(GRAVES_LOCATION + "RedstoneObeliskMemorial.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_OBELISK = new ResourceLocation(GRAVES_LOCATION + "ObsidianObeliskMemorial.png");
    public static final ResourceLocation GRAVE_QUARTZ_OBELISK = new ResourceLocation(GRAVES_LOCATION + "QuartzObeliskMemorial.png");
    public static final ResourceLocation GRAVE_PRIZMARINE_OBELISK = new ResourceLocation(GRAVES_LOCATION + "PrizmarineObeliskMemorial.png");
    public static final ResourceLocation GRAVE_ICE_OBELISK = new ResourceLocation(GRAVES_LOCATION + "IceObeliskMemorial.png");
    public static final ResourceLocation GRAVE_MOSSY_OBELISK = new ResourceLocation(GRAVES_LOCATION + "MossyObeliskMemorial.png");
    // horisontal plates
    public static final ResourceLocation GRAVE_WOODEN_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "WoodenHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_SANDSTONE_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "SandstoneHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_RED_SANDSTONE_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "RedSandstoneHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_STONE_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "StoneHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_DIORITE_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "DioriteHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_ANDESITE_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "AndesiteHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_GRANITE_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "GraniteHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_IRON_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "IronHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_GOLDEN_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "GoldenHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_DIAMOND_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "DiamondHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_EMERALD_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "EmeraldHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_LAPIS_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "LapisHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_REDSTONE_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "RedstoneHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "ObsidianHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_QUARTZ_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "QuartzHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_PRIZMARINE_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "PrizmarineHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_ICE_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "IceHorisontalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_MOSSY_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "MossyHorisontalPlateGraveStone.png");
    // dog graves
    public static final ResourceLocation GRAVE_WOODEN_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "WoodenDogGrave.png");
    public static final ResourceLocation GRAVE_SANDSTONE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "SandstoneDogGrave.png");
    public static final ResourceLocation GRAVE_RED_SANDSTONE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "RedSandstoneDogGrave.png");
    public static final ResourceLocation GRAVE_STONE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "StoneDogGrave.png");
    public static final ResourceLocation GRAVE_DIORITE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "DioriteDogGrave.png");
    public static final ResourceLocation GRAVE_ANDESITE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "AndesiteDogGrave.png");
    public static final ResourceLocation GRAVE_GRANITE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "GraniteDogGrave.png");
    public static final ResourceLocation GRAVE_IRON_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "IronDogGrave.png");
    public static final ResourceLocation GRAVE_GOLDEN_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "GoldenDogGrave.png");
    public static final ResourceLocation GRAVE_DIAMOND_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "DiamondDogGrave.png");
    public static final ResourceLocation GRAVE_EMERALD_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "EmeraldDogGrave.png");
    public static final ResourceLocation GRAVE_LAPIS_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "LapisDogGrave.png");
    public static final ResourceLocation GRAVE_REDSTONE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "RedstoneDogGrave.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "ObsidianDogGrave.png");
    public static final ResourceLocation GRAVE_QUARTZ_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "QuartzDogGrave.png");
    public static final ResourceLocation GRAVE_PRIZMARINE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "PrizmarineDogGrave.png");
    public static final ResourceLocation GRAVE_ICE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "IceDogGrave.png");
    public static final ResourceLocation GRAVE_MOSSY_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "MossyDogGrave.png");
    // cats graves
    public static final ResourceLocation GRAVE_WOODEN_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "WoodenCatGrave.png");
    public static final ResourceLocation GRAVE_SANDSTONE_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "SandstoneCatGrave.png");
    public static final ResourceLocation GRAVE_RED_SANDSTONE_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "RedSandstoneCatGrave.png");
    public static final ResourceLocation GRAVE_STONE_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "StoneCatGrave.png");
    public static final ResourceLocation GRAVE_DIORITE_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "DioriteCatGrave.png");
    public static final ResourceLocation GRAVE_ANDESITE_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "AndesiteCatGrave.png");
    public static final ResourceLocation GRAVE_GRANITE_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "GraniteCatGrave.png");
    public static final ResourceLocation GRAVE_IRON_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "IronCatGrave.png");
    public static final ResourceLocation GRAVE_GOLDEN_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "GoldenCatGrave.png");
    public static final ResourceLocation GRAVE_DIAMOND_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "DiamondCatGrave.png");
    public static final ResourceLocation GRAVE_EMERALD_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "EmeraldCatGrave.png");
    public static final ResourceLocation GRAVE_LAPIS_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "LapisCatGrave.png");
    public static final ResourceLocation GRAVE_REDSTONE_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "RedstoneCatGrave.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "ObsidianCatGrave.png");
    public static final ResourceLocation GRAVE_QUARTZ_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "QuartzCatGrave.png");
    public static final ResourceLocation GRAVE_PRIZMARINE_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "PrizmarineCatGrave.png");
    public static final ResourceLocation GRAVE_ICE_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "IceCatGrave.png");
    public static final ResourceLocation GRAVE_MOSSY_CAT_STATUE = new ResourceLocation(GRAVES_LOCATION + "MossyCatGrave.png");
    // horses graves
    public static final ResourceLocation GRAVE_WOODEN_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "WoodenHorseGrave.png");
    public static final ResourceLocation GRAVE_SANDSTONE_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "SandstoneHorseGrave.png");
    public static final ResourceLocation GRAVE_RED_SANDSTONE_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "RedSandstoneHorseGrave.png");
    public static final ResourceLocation GRAVE_STONE_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "StoneHorseGrave.png");
    public static final ResourceLocation GRAVE_DIORITE_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "DioriteHorseGrave.png");
    public static final ResourceLocation GRAVE_ANDESITE_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "AndesiteHorseGrave.png");
    public static final ResourceLocation GRAVE_GRANITE_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "GraniteHorseGrave.png");
    public static final ResourceLocation GRAVE_IRON_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "IronHorseGrave.png");
    public static final ResourceLocation GRAVE_GOLDEN_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "GoldenHorseGrave.png");
    public static final ResourceLocation GRAVE_DIAMOND_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "DiamondHorseGrave.png");
    public static final ResourceLocation GRAVE_EMERALD_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "EmeraldHorseGrave.png");
    public static final ResourceLocation GRAVE_LAPIS_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "LapisHorseGrave.png");
    public static final ResourceLocation GRAVE_REDSTONE_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "RedstoneHorseGrave.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "ObsidianHorseGrave.png");
    public static final ResourceLocation GRAVE_QUARTZ_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "QuartzHorseGrave.png");
    public static final ResourceLocation GRAVE_PRIZMARINE_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "PrizmarineHorseGrave.png");
    public static final ResourceLocation GRAVE_ICE_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "IceHorseGrave.png");
    public static final ResourceLocation GRAVE_MOSSY_HORSE_STATUE = new ResourceLocation(GRAVES_LOCATION + "MossyHorseGrave.png");

    // models - parts
    public static final ResourceLocation CREEPER_AURA = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    public static final ResourceLocation SWORD_AURA = new ResourceLocation("textures/misc/enchanted_item_glint.png");

}
