package gravestone;

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
    private static final String ENTITY_LOCATION = MOD_NAME + ":textures/entities/";
    private static final String GRAVES_LOCATION = MOD_NAME + ":textures/graves/";
    private static final String MEMORIALS_LOCATION = MOD_NAME + ":textures/memorials/";
    // localization path
    public static final String LOCALIZATION_LOCATION = "/assets/" + MOD_NAME + "/lang/";
    // blocks
    public static final String TIME_TRAP = "nether_brick";
    public static final String WITHER_SPAWNER = "mob_spawner";
    // items
    public static final String CHISEL = MOD_NAME + ":chisel";
    // gui
    public static final ResourceLocation GRAVE_GUI = new ResourceLocation(GUI_LOCATION + "DeathMessageBackground.png");
    // entities
    public static final ResourceLocation UNDARTAKER = new ResourceLocation(ENTITY_LOCATION + "undertaker.png");
    public static final ResourceLocation ZOMBIE_DOG = new ResourceLocation(ENTITY_LOCATION + "ZombieDog.png");
    public static final ResourceLocation ZOMBIE_OZELOT = new ResourceLocation(ENTITY_LOCATION + "ZombieOzelot.png");
    public static final ResourceLocation ZOMBIE_CAT_BLACK = new ResourceLocation(ENTITY_LOCATION + "ZombieCatBlack.png");
    public static final ResourceLocation ZOMBIE_CAT_RED = new ResourceLocation(ENTITY_LOCATION + "ZombieCatRed.png");
    public static final ResourceLocation ZOMBIE_CAT_SIAMESE = new ResourceLocation(ENTITY_LOCATION + "ZombieCatSiamese.png");
    public static final ResourceLocation SKELETON_DOG = new ResourceLocation(ENTITY_LOCATION + "SkeletonDog.png");
    public static final ResourceLocation SKELETON_CAT = new ResourceLocation(ENTITY_LOCATION + "SkeletonCat.png");
    // models - graves
    public static final ResourceLocation GRAVE_VERTICAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "ModelVerticalPlateGraveStone.png");
    public static final ResourceLocation GRAVE_CROSS = new ResourceLocation(GRAVES_LOCATION + "ModelCrossGraveStone.png");
    public static final ResourceLocation GRAVE_HORISONTAL_PLATE = new ResourceLocation(GRAVES_LOCATION + "ModelHorisontalPlateGraveStone.png");
    public static final ResourceLocation DOG_STATUE_GRAVE = new ResourceLocation(GRAVES_LOCATION + "ModelDogStatueGraveStone.png");
    public static final ResourceLocation CAT_STATUE_GRAVE = new ResourceLocation(GRAVES_LOCATION + "ModelCatStatueGraveStone.png");
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
    public static final ResourceLocation STEVE_ARMOR = new ResourceLocation(MEMORIALS_LOCATION + "ModelSteveStatueMemorialArmor.png");
}
