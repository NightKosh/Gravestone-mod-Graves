package gravestone.core;

import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Resources {

    private Resources() {
    }
    private static final String MOD_NAME = ModInfo.ID.toLowerCase();
    private static final String GUI_LOCATION = MOD_NAME + ":textures/gui/";
    private static final String BLOCK_LOCATION = MOD_NAME + ":textures/blocks/";
    private static final String ENTITY_LOCATION = MOD_NAME + ":textures/entities/";
    private static final String GRAVES_LOCATION = MOD_NAME + ":textures/graves/";
    private static final String MEMORIALS_LOCATION = MOD_NAME + ":textures/memorials/";
    // localization path
    public static final String LOCALIZATION_LOCATION = "/assets/" + MOD_NAME + "/lang/";
    // blocks
    public static final String NIGHT_STONE      = "nether_brick";
    public static final String THUNDER_STONE    = "stonebrick";
    public static final String BONE_BLOCK       = MOD_NAME  + ":bone_block";
    public static final String SKULL_BONE_BLOCK = MOD_NAME  + ":skull_bone_block";
    public static final String PENTAGRAM_ICO    = MOD_NAME  + ":pentagram";
    public static final ResourceLocation PENTAGRAM = new ResourceLocation(BLOCK_LOCATION  + "pentagram.png");
    public static final ResourceLocation CANDLE = new ResourceLocation(BLOCK_LOCATION  + "candle.png");
    public static final String ALTAR_BOTTOM = MOD_NAME  + ":altar_bottom";
    public static final String ALTAR_TOP = MOD_NAME  + ":altar_top";
    public static final String ALTAR_SIDE = MOD_NAME  + ":altar_side";
    // items
    public static final String CHISEL = MOD_NAME + ":chisel";
    public static final String CORPSE_VILLAGER = MOD_NAME + ":villager_corpse";
    public static final String CORPSE_DOG = MOD_NAME + ":dog_corpse";
    public static final String CORPSE_CAT = MOD_NAME + ":cat_corpse";
    public static final String CORPSE_HORSE = MOD_NAME + ":horse_corpse";
    // entities
    public static final ResourceLocation UNDARTAKER = new ResourceLocation(ENTITY_LOCATION + "undertaker.png");
    public static final ResourceLocation ZOMBIE_DOG = new ResourceLocation(ENTITY_LOCATION + "ZombieDog.png");
    public static final ResourceLocation ZOMBIE_OZELOT = new ResourceLocation(ENTITY_LOCATION + "ZombieOzelot.png");
    public static final ResourceLocation ZOMBIE_CAT_BLACK = new ResourceLocation(ENTITY_LOCATION + "ZombieCatBlack.png");
    public static final ResourceLocation ZOMBIE_CAT_RED = new ResourceLocation(ENTITY_LOCATION + "ZombieCatRed.png");
    public static final ResourceLocation ZOMBIE_CAT_SIAMESE = new ResourceLocation(ENTITY_LOCATION + "ZombieCatSiamese.png");
    public static final ResourceLocation SKELETON_DOG = new ResourceLocation(ENTITY_LOCATION + "SkeletonDog.png");
    public static final ResourceLocation SKELETON_CAT = new ResourceLocation(ENTITY_LOCATION + "SkeletonCat.png");
    public static final ResourceLocation SKULL_CRAWLER = new ResourceLocation(ENTITY_LOCATION + "SkullCrawler.png");
    public static final ResourceLocation WITHER_SKULL_CRAWLER = new ResourceLocation(ENTITY_LOCATION + "WitherSkullCrawler.png");
    public static final ResourceLocation ZOMBIE_SKULL_CRAWLER = new ResourceLocation(ENTITY_LOCATION + "ZombieSkullCrawler.png");

    // models - graves
    // vertical plates
    public static final ResourceLocation GRAVE_WOODEN_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "WoodenVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_SANDSTONE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "SandstoneVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_STONE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "StoneVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_MOSSY_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "MossyVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_IRON_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "IronVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_GOLDEN_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "GoldenVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_DIAMOND_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "DiamondVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_EMERALD_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "EmeraldVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "ObsidianVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_QUARTZ_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "QuartzVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_LAPIS_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "LapisVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_REDSTONE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "RedstoneVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_ICE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "IceVerticalPlateGraveStone.png");
    // crosses
    public static final ResourceLocation GRAVE_WOODEN_CROSS = new ResourceLocation(GRAVES_LOCATION + "WoodenCrossGrave.png");
    public static final ResourceLocation GRAVE_SANDSTONE_CROSS = new ResourceLocation(GRAVES_LOCATION + "SandstoneCrossGrave.png");
    public static final ResourceLocation GRAVE_STONE_CROSS = new ResourceLocation(GRAVES_LOCATION + "StoneCrossGrave.png");
    public static final ResourceLocation GRAVE_MOSSY_CROSS = new ResourceLocation(GRAVES_LOCATION + "MossyCrossGrave.png");
    public static final ResourceLocation GRAVE_IRON_CROSS = new ResourceLocation(GRAVES_LOCATION + "IronCrossGrave.png");
    public static final ResourceLocation GRAVE_GOLDEN_CROSS = new ResourceLocation(GRAVES_LOCATION + "GoldenCrossGrave.png");
    public static final ResourceLocation GRAVE_DIAMOND_CROSS = new ResourceLocation(GRAVES_LOCATION + "DiamondCrossGrave.png");
    public static final ResourceLocation GRAVE_EMERALD_CROSS = new ResourceLocation(GRAVES_LOCATION + "EmeraldCrossGrave.png");
    public static final ResourceLocation GRAVE_LAPIS_CROSS = new ResourceLocation(GRAVES_LOCATION + "LapisCrossGrave.png");
    public static final ResourceLocation GRAVE_REDSTONE_CROSS = new ResourceLocation(GRAVES_LOCATION + "RedstoneCrossGrave.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_CROSS = new ResourceLocation(GRAVES_LOCATION + "ObsidianCrossGrave.png");
    public static final ResourceLocation GRAVE_QUARTZ_CROSS = new ResourceLocation(GRAVES_LOCATION + "QuartzCrossGrave.png");
    public static final ResourceLocation GRAVE_ICE_CROSS = new ResourceLocation(GRAVES_LOCATION + "IceCrossGrave.png");
    // horisontal plates
    public static final ResourceLocation GRAVE_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "ModelHorisontalPlateGraveStone.png");
    // dog graves
    public static final ResourceLocation GRAVE_WOODEN_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "WoodenDogGrave.png");
    public static final ResourceLocation GRAVE_SANDSTONE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "SandstoneDogGrave.png");
    public static final ResourceLocation GRAVE_STONE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "StoneDogGrave.png");
    public static final ResourceLocation GRAVE_MOSSY_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "MossyDogGrave.png");
    public static final ResourceLocation GRAVE_IRON_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "IronDogGrave.png");
    public static final ResourceLocation GRAVE_GOLDEN_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "GoldenDogGrave.png");
    public static final ResourceLocation GRAVE_DIAMOND_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "DiamondDogGrave.png");
    public static final ResourceLocation GRAVE_EMERALD_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "EmeraldDogGrave.png");
    public static final ResourceLocation GRAVE_LAPIS_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "LapisDogGrave.png");
    public static final ResourceLocation GRAVE_REDSTONE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "RedstoneDogGrave.png");
    public static final ResourceLocation GRAVE_OBSIDIAN_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "ObsidianDogGrave.png");
    public static final ResourceLocation GRAVE_QUARTZ_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "QuartzDogGrave.png");
    public static final ResourceLocation GRAVE_ICE_DOG_STATUE = new ResourceLocation(GRAVES_LOCATION + "IceDogGrave.png");
    // cats graves
    public static final ResourceLocation CAT_STATUE_GRAVE = new ResourceLocation(GRAVES_LOCATION + "ModelCatStatueGraveStone.png");
    public static final ResourceLocation HORSE_STATUE_GRAVE = new ResourceLocation(GRAVES_LOCATION + "ModelHorseStatueGraveStone.png");
    public static final ResourceLocation WOODEN_SWORD_GRAVE = new ResourceLocation(GRAVES_LOCATION + "WoodenSwordGrave.png");
    public static final ResourceLocation STONE_SWORD_GRAVE = new ResourceLocation(GRAVES_LOCATION + "StoneSwordGrave.png");
    public static final ResourceLocation IRON_SWORD_GRAVE = new ResourceLocation(GRAVES_LOCATION + "IronSwordGrave.png");
    public static final ResourceLocation GOLDEN_SWORD_GRAVE = new ResourceLocation(GRAVES_LOCATION + "GoldenSwordGrave.png");
    public static final ResourceLocation DIAMOND_SWORD_GRAVE = new ResourceLocation(GRAVES_LOCATION + "DiamondSwordGrave.png");
    // models - memorials
    public static final ResourceLocation MEMORIAL_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "ModelMemorialCross.png");
    public static final ResourceLocation MEMORIAL_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "ModelMemorialObelisk.png");
    public static final ResourceLocation STEVE_STATUE_MEMORIAL = new ResourceLocation(MEMORIALS_LOCATION + "ModelSteveStatueMemorial.png");
    public static final ResourceLocation VILLAGER_STATUE_MEMORIAL = new ResourceLocation(MEMORIALS_LOCATION + "ModelVillagerStatueMemorial.png");
    public static final ResourceLocation ANGEL_STAUTE_MEMORIAL = new ResourceLocation(MEMORIALS_LOCATION + "ModelAngelStatueMemorial.png");
    public static final ResourceLocation DOG_STATUE_MEMORIAL = new ResourceLocation(MEMORIALS_LOCATION + "ModelDogStatueMemorial.png");
    public static final ResourceLocation CAT_STAUTE_MEMORIAL = new ResourceLocation(MEMORIALS_LOCATION + "ModelCatStatueMemorial.png");
    public static final ResourceLocation CREEPER_STATUE_MEMORIAL = new ResourceLocation(MEMORIALS_LOCATION + "ModelCreeperStatueMemorial.png");
    // models - parts
    public static final ResourceLocation CREEPER_AURA = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    public static final ResourceLocation SWORD_AURA = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    public static final ResourceLocation SMALL_PEDESTAL = new ResourceLocation(MEMORIALS_LOCATION + "ModelSmallPedestal.png");
    public static final ResourceLocation BIG_PEDESTAL = new ResourceLocation(MEMORIALS_LOCATION + "ModelBigPedestal.png");
    public static final ResourceLocation STEVE_ARMOR = new ResourceLocation(MEMORIALS_LOCATION + "ModelSteveStatueMemorialArmor.png");
    
    // haunted chest
    public static final ResourceLocation CHRISTMAS_CHEST = new ResourceLocation("textures/entity/chest/christmas.png");
    public static final ResourceLocation DEFAULT_CHEST = new ResourceLocation("textures/entity/chest/normal.png");
    
    // skull candle
    public static final ResourceLocation SKELETON_SKULL_CANDLE = new ResourceLocation(BLOCK_LOCATION + "SkeletonSkullCandle.png");
    public static final ResourceLocation WITHER_SKULL_CANDLE = new ResourceLocation(BLOCK_LOCATION + "WitherSkullCandle.png");
    public static final ResourceLocation ZOMBIE_SKULL_CANDLE = new ResourceLocation(BLOCK_LOCATION + "ZombieSkullCandle.png");
}
