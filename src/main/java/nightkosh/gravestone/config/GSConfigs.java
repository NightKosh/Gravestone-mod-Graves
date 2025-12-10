package nightkosh.gravestone.config;

import net.minecraftforge.common.ForgeConfigSpec;
import nightkosh.gravestone.helper.GraveStoneHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSConfigs {

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec.ConfigValue<Boolean> CAN_PLACE_GRAVES_EVERY_WHERE;
    public static ForgeConfigSpec.ConfigValue<Boolean> GENERATE_PLAYER_GRAVES;
    public static ForgeConfigSpec.ConfigValue<Boolean> GENERATE_VILLAGER_GRAVES;
    public static ForgeConfigSpec.ConfigValue<Boolean> GENERATE_PET_GRAVES;
    public static ForgeConfigSpec.ConfigValue<Boolean> GENERATE_GRAVES_IN_LAVA;
    public static ForgeConfigSpec.ConfigValue<Boolean> GENERATE_SWORD_GRAVES;
    public static ForgeConfigSpec.ConfigValue<Boolean> GENERATE_EMPTY_PLAYER_GRAVES;
    public static ForgeConfigSpec.ConfigValue<Boolean> ONLY_OWNER_CAN_LOOT_GRAVE;
    public static ForgeConfigSpec.ConfigValue<Boolean> REMOVE_EMPTY_GRAVES;
    public static ForgeConfigSpec.ConfigValue<Boolean> SHOW_GRAVE_REMOVAL_MESSAGES;
    public static ForgeConfigSpec.ConfigValue<Boolean> DROP_GRAVE_BLOCK_AT_DESTRUCTION;
    public static ForgeConfigSpec.ConfigValue<Boolean> RENDER_GRAVES_FLOWERS;
    public static ForgeConfigSpec.ConfigValue<Boolean> VANILLA_RENDERER_FOR_SWORDS_GRAVES;
    public static ForgeConfigSpec.ConfigValue<Boolean> CREATE_BACKUPS;

    public static ForgeConfigSpec.ConfigValue<Integer> GRAVE_ITEMS_COUNT;

    //TODO
//    public static List<Integer> playerGravesDimensionalBlackList;

    public static List<GraveStoneHelper.RestrictedArea> restrictGraveGenerationInArea;

    public static ForgeConfigSpec.ConfigValue<Boolean> DEBUG_MODE;


    // COMPATIBILITY
//    public static boolean storeTheCampingModItems;
//    public static boolean storeRpgInventoryItems;
//    public static boolean storeBackpacksItems;
//    public static boolean enableEnderIOSoulbound;
//    public static boolean enableTconstructSoulbound;
//    public static boolean enableTwilightForestKeeping;

    static {
        BUILDER.push("Configs for Sophisticated Wolves Mod");

        CAN_PLACE_GRAVES_EVERY_WHERE = BUILDER.define("AllowToPlaceGravesEveryWhere", false);
        GENERATE_PLAYER_GRAVES = BUILDER.define("GeneratePlayersGraves", true);
        GENERATE_VILLAGER_GRAVES = BUILDER.define("GenerateVillagersGraves", true);
        GENERATE_PET_GRAVES = BUILDER.define("GeneratePetsGraves", true);
        GENERATE_GRAVES_IN_LAVA = BUILDER.define("GenerateGravesInLava", true);
        GENERATE_SWORD_GRAVES = BUILDER.define("GenerateSwordGraves", true);
        GENERATE_EMPTY_PLAYER_GRAVES = BUILDER.define("GenerateEmptyPlayerGraves", true);
        ONLY_OWNER_CAN_LOOT_GRAVE = BUILDER.define("OnlyOwnerCanLootGrave", false);
        REMOVE_EMPTY_GRAVES = BUILDER.define("RemoveEmptyGraves", false);
        SHOW_GRAVE_REMOVAL_MESSAGES = BUILDER.define("ShowGraveRemovalMessages", true);
        DROP_GRAVE_BLOCK_AT_DESTRUCTION = BUILDER.define("DropGraveBlockAtDestruction", true);
        RENDER_GRAVES_FLOWERS = BUILDER.define("RenderGravesFlowers", true);
        VANILLA_RENDERER_FOR_SWORDS_GRAVES = BUILDER.define("VanillaRendererForSwordsGraves", true);
        CREATE_BACKUPS = BUILDER.define("CreateBackups", true);

        GRAVE_ITEMS_COUNT = BUILDER.comment("This value is amount of items which should be saved in percents. It should be in range of 0 an 100!")
                .defineInRange("AmountOfSavedItems", 100, 0, 100);

        //TODO
//        Property restrictGraveGenerationInAreaProperty = config.get(CATEGORY_GRAVES, "RestrictGraveGenerationInArea", "");
//        restrictGraveGenerationInAreaProperty.setComment("List of coordinates in which graves generation must be disabled. \"dimension_id,start_x,start_y,start_z,end_x,end_y,end_z;\". Dimension id is optional - it will be set to 0 by default.");
//        String ar = restrictGraveGenerationInAreaProperty.getString();
//        String[] areas = ar.split(";");
//        restrictGraveGenerationInArea = new ArrayList<>(areas.length);
//        for (String area : areas) {
//            GraveStoneHelper.RestrictedArea restrictedArea = GraveStoneHelper.RestrictedArea.getFromString(area);
//            if (restrictedArea != null) {
//                restrictGraveGenerationInArea.add(restrictedArea);
//            }
//        }
//
//        playerGravesDimensionalBlackList = ConfigsHelper.getDimensionList(config, CATEGORY_GRAVES, "PlayerGravesDimensionalBlackList", "",
//                "List of dimension ids in which player's graves will not be generated at death");

        DEBUG_MODE = BUILDER.comment("Enable debug mode for additional dev logs")
                .define("Debug Mode", false);

////        storeBattlegearItems = config.get(CATEGORY_COMPATIBILITY, "StoreBattlegearItems", true).getBoolean(true);
////        storeTheCampingModItems = config.get(CATEGORY_COMPATIBILITY, "StoreTheCampingModItems", true).getBoolean(true);
////        storeRpgInventoryItems = config.get(CATEGORY_COMPATIBILITY, "StoreRpgInventoryItems", true).getBoolean(true);
////        storeBackpacksItems = config.get(CATEGORY_COMPATIBILITY, "StoreBackpacksItems", true).getBoolean(true);
////        enableEnderIOSoulbound = config.get(CATEGORY_COMPATIBILITY, "EnableEnderIOSoulbound", true).getBoolean(true);
////        enableTconstructSoulbound = config.get(CATEGORY_COMPATIBILITY, "EnableTconstructSoulbound", true).getBoolean(true);
////        enableTwilightForestKeeping = config.get(CATEGORY_COMPATIBILITY, "EnableTwilightForestCharmsOfKeeping", true).getBoolean(true);

        BUILDER.pop();
        SPEC = BUILDER.build();


        getGravesText();
    }


    // grave names
    public static List<String> graveNames;
    public static List<String> graveDogsNames;
    public static List<String> graveCatsNames;
    public static List<String> graveDeathMessages;

    private static void getGravesText() {
        graveNames = readStringsFromFile("graveNames.txt", GravesDefaultText.NAMES);//TODO path +
        graveDogsNames = readStringsFromFile("graveDogsNames.txt", GravesDefaultText.DOG_NAMES);//TODO path +
        graveCatsNames = readStringsFromFile("graveCatsNames.txt", GravesDefaultText.CAT_NAMES);//TODO path +
        graveDeathMessages = readStringsFromFile("graveDeathMessages.txt", GravesDefaultText.DEATH_TEXT);//TODO path +
    }

    /*
     * Read text from file if it exist or get default text
     */
    private static List<String> readStringsFromFile(String fileName, String[] defaultValues) {
        //TODO fileName ignored!
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(defaultValues));

        return list;
    }

}
