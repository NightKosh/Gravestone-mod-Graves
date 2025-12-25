package nightkosh.gravestone.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import nightkosh.gravestone.helper.GraveStoneHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSConfigs {

    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static ModConfigSpec.ConfigValue<Boolean> CAN_PLACE_GRAVES_EVERY_WHERE;
    public static ModConfigSpec.ConfigValue<Boolean> GENERATE_PLAYER_GRAVES;
    public static ModConfigSpec.ConfigValue<Boolean> GENERATE_VILLAGER_GRAVES;
    public static ModConfigSpec.ConfigValue<Boolean> GENERATE_PET_GRAVES;
    public static ModConfigSpec.ConfigValue<Boolean> GENERATE_GRAVES_IN_LAVA;
    //    public static ModConfigSpec.ConfigValue<Boolean> GENERATE_SWORD_GRAVES;
    public static ModConfigSpec.ConfigValue<Boolean> GENERATE_EMPTY_PLAYER_GRAVES;
    public static ModConfigSpec.ConfigValue<Boolean> ONLY_OWNER_CAN_LOOT_GRAVE;
    //    public static ModConfigSpec.ConfigValue<Boolean> REMOVE_EMPTY_GRAVES;
//    public static ModConfigSpec.ConfigValue<Boolean> SHOW_GRAVE_REMOVAL_MESSAGES;
    public static ModConfigSpec.ConfigValue<Boolean> DROP_GRAVE_BLOCK_AT_DESTRUCTION;
    //    public static ModConfigSpec.ConfigValue<Boolean> VANILLA_RENDERER_FOR_SWORDS_GRAVES;
    public static ModConfigSpec.ConfigValue<Boolean> CREATE_BACKUPS;

    public static ModConfigSpec.ConfigValue<Integer> GRAVE_ITEMS_COUNT;

    public static ModConfigSpec.ConfigValue<List<? extends String>> PLAYER_GRAVES_DIMENSIONAL_BLACKLIST;

    public static List<GraveStoneHelper.RestrictedArea> restrictGraveGenerationInArea;

    public static ModConfigSpec.ConfigValue<Boolean> DEBUG_MODE;

    static {
        BUILDER.push("Configs for Gravestone mod - Graves");

        CAN_PLACE_GRAVES_EVERY_WHERE = BUILDER.define("AllowToPlaceGravesEveryWhere", false);
        GENERATE_PLAYER_GRAVES = BUILDER.define("GeneratePlayersGraves", true);
        GENERATE_VILLAGER_GRAVES = BUILDER.define("GenerateVillagersGraves", true);
        GENERATE_PET_GRAVES = BUILDER.define("GeneratePetsGraves", true);
        GENERATE_GRAVES_IN_LAVA = BUILDER.define("GenerateGravesInLava", true);
//        GENERATE_SWORD_GRAVES = BUILDER.define("GenerateSwordGraves", true);
        GENERATE_EMPTY_PLAYER_GRAVES = BUILDER.define("GenerateEmptyPlayerGraves", true);
        ONLY_OWNER_CAN_LOOT_GRAVE = BUILDER.define("OnlyOwnerCanLootGrave", false);
//        REMOVE_EMPTY_GRAVES = BUILDER.define("RemoveEmptyGraves", false);
//        SHOW_GRAVE_REMOVAL_MESSAGES = BUILDER.define("ShowGraveRemovalMessages", true);
        DROP_GRAVE_BLOCK_AT_DESTRUCTION = BUILDER.define("DropGraveBlockAtDestruction", true);
//        VANILLA_RENDERER_FOR_SWORDS_GRAVES = BUILDER.define("VanillaRendererForSwordsGraves", true);
        CREATE_BACKUPS = BUILDER.define("CreateBackups", true);

        GRAVE_ITEMS_COUNT = BUILDER.comment("This value is amount of items which should be saved in percents. It should be in range of 0 an 100!")
                .defineInRange("AmountOfSavedItems", 100, 0, 100);

        //TODO
//        Property restrictGraveGenerationInAreaProperty = config.get(CATEGORY_GRAVES, "RestrictGraveGenerationInArea", "");
//        restrictGraveGenerationInAreaProperty.setComment("List of coordinates in which graves generation must be disabled. \"dimension_id,start_x,start_y,start_z,end_x,end_y,end_z;\". Dimension id is optional - it will be set to 0 by default.");
//        String ar = restrictGraveGenerationInAreaProperty.getString();
//        String[] areas = ar.split(";");
        restrictGraveGenerationInArea = new ArrayList<>();//(areas.length);
//        for (String area : areas) {
//            GraveStoneHelper.RestrictedArea restrictedArea = GraveStoneHelper.RestrictedArea.getFromString(area);
//            if (restrictedArea != null) {
//                restrictGraveGenerationInArea.add(restrictedArea);
//            }
//        }
//
        PLAYER_GRAVES_DIMENSIONAL_BLACKLIST = BUILDER
                .comment("List of dimensions where player graves should not be created",
                        "Format: \"namespace:path\" (example: minecraft:the_nether)")
                .defineList(
                        "PlayerGravesDimensionalBlackList",
                        List.of(
                                "minecraft:the_nether",
                                "minecraft:the_end"
                        ),
                        o -> o instanceof String
                );


        DEBUG_MODE = BUILDER.comment("Enable debug mode for additional dev logs")
                .define("Debug Mode", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
