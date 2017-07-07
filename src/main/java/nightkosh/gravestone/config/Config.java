package nightkosh.gravestone.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import nightkosh.gravestone.helper.GraveStoneHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Config {

    private static Configuration config;
    private static Config instance;
    private static String path;
    // CATEGORIES
    public static final String CATEGORY_COMPATIBILITY = "compatibility";
    public static final String CATEGORY_GRAVES = "graves";

    private Config(String path, File configFile) {
        this.config = new Configuration(configFile);
        this.path = path;
        getConfigs();
    }

    public static Config getInstance(String path, String configFile) {
        if (instance == null) {
            return new Config(path, new File(path + configFile));
        } else {
            return instance;
        }
    }

    public final void getConfigs() {
        config.load();
        gravesConfig();
        compatibilityConfigs();
        config.save();
        getGravesText();
    }


    // graves for entities
    public static boolean generatePlayerGraves;
    public static boolean generateVillagerGraves;
    public static boolean generatePetGraves;
    public static boolean generateGravesInLava;
    public static int graveItemsCount;
    public static boolean canPlaceGravesEveryWhere;
    public static boolean generateSwordGraves;
    public static boolean removeEmptyGraves;
    public static boolean showGravesRemovingMessages;
    public static boolean onlyOwnerCanLootGrave;
    public static boolean renderGravesFlowers;
    public static boolean vanillaRendererForSwordsGraves;

    public static List<GraveStoneHelper.RestrictedArea> restrictGraveGenerationInArea;

    private static void gravesConfig() {
        canPlaceGravesEveryWhere = config.get(CATEGORY_GRAVES, "AllowToPlaceGravesEveryWhere", false).getBoolean(false);
        generatePlayerGraves = config.get(CATEGORY_GRAVES, "GeneratePlayersGraves", true).getBoolean(true);
        generateVillagerGraves = config.get(CATEGORY_GRAVES, "GenerateVillagersGraves", true).getBoolean(true);
        generatePetGraves = config.get(CATEGORY_GRAVES, "GeneratePetsGraves", true).getBoolean(true);
        generateGravesInLava = config.get(CATEGORY_GRAVES, "GenerateGravesInLava", true).getBoolean(true);
        generateSwordGraves = config.get(CATEGORY_GRAVES, "GenerateSwordGraves", true).getBoolean(true);
        onlyOwnerCanLootGrave = config.get(CATEGORY_GRAVES, "OnlyOwnerCanLootGrave", false).getBoolean(false);


        // store items
        Property graveItemsCountProperty = config.get(CATEGORY_GRAVES, "AmountOfSavedItems", 100);
        graveItemsCountProperty.setComment("This value is amount of items which should be saved in percents. It should be in range of 0 an 100!");
        graveItemsCount = graveItemsCountProperty.getInt();

        if (graveItemsCount > 100 || graveItemsCount < 0) {
            graveItemsCount = 100;
        }

        removeEmptyGraves = config.get(CATEGORY_GRAVES, "RemoveEmptyGraves", false).getBoolean(false);
        showGravesRemovingMessages = config.get(CATEGORY_GRAVES, "ShowGravesRemovingMessages", true).getBoolean(true);

        renderGravesFlowers = config.get(CATEGORY_GRAVES, "RenderGravesFlowers", true).getBoolean(true);
        vanillaRendererForSwordsGraves = config.get(CATEGORY_GRAVES, "VanillaRendererForSwordsGraves", true).getBoolean(true);

        Property restrictGraveGenerationInAreaProperty = config.get(CATEGORY_GRAVES, "RestrictGraveGenerationInArea", "");
        restrictGraveGenerationInAreaProperty.setComment("List of coordinates in which graves generation must be disabled. \"dimension_id,start_x,start_y,start_z,end_x,end_y,end_z;\". Dimension id is optional - it will be set to 0 by default.");
        String ar = restrictGraveGenerationInAreaProperty.getString();
        String[] areas = ar.split(";");
        restrictGraveGenerationInArea = new ArrayList<>(areas.length);
        for (String area : areas) {
            GraveStoneHelper.RestrictedArea restrictedArea = GraveStoneHelper.RestrictedArea.getFromString(area);
            if (restrictedArea != null) {
                restrictGraveGenerationInArea.add(restrictedArea);
            }
        }
    }


    // COMPATIBILITY
    public static boolean storeBattlegearItems;
    public static boolean storeTheCampingModItems;
    public static boolean storeBaublesItems;
    public static boolean storeMaricultureItems;
    public static boolean storeRpgInventoryItems;
    public static boolean storeGalacticraftItems;
    public static boolean storeBackpacksItems;
    public static boolean enableArsMagicaSoulbound;
    public static boolean enableEnderIOSoulbound;
    public static boolean enableTwilightForestKeeping;
    public static boolean addThaumcraftSwordsAsGravestones;

    private static void compatibilityConfigs() {

        storeBattlegearItems = config.get(CATEGORY_COMPATIBILITY, "StoreBattlegearItems", true).getBoolean(true);
        storeTheCampingModItems = config.get(CATEGORY_COMPATIBILITY, "StoreTheCampingModItems", true).getBoolean(true);
        storeBaublesItems = config.get(CATEGORY_COMPATIBILITY, "StoreBaublesItems", true).getBoolean(true);
        storeMaricultureItems = config.get(CATEGORY_COMPATIBILITY, "StoreMaricultureItems", true).getBoolean(true);
        storeRpgInventoryItems = config.get(CATEGORY_COMPATIBILITY, "StoreRpgInventoryItems", true).getBoolean(true);
        storeGalacticraftItems = config.get(CATEGORY_COMPATIBILITY, "StoreGalacticraftItems", true).getBoolean(true);
        storeBackpacksItems = config.get(CATEGORY_COMPATIBILITY, "StoreBackpacksItems", true).getBoolean(true);

        enableArsMagicaSoulbound = config.get(CATEGORY_COMPATIBILITY, "EnableArsMagicaSoulbound", true).getBoolean(true);
        enableEnderIOSoulbound = config.get(CATEGORY_COMPATIBILITY, "EnableEnderIOSoulbound", true).getBoolean(true);
        enableTwilightForestKeeping = config.get(CATEGORY_COMPATIBILITY, "EnableTwilightForestCharmsOfKeeping", true).getBoolean(true);

        addThaumcraftSwordsAsGravestones = config.get(CATEGORY_COMPATIBILITY, "AddThaumcraftSwordsAsGravestones", true).getBoolean(true);
    }

    // grave names
    public static ArrayList<String> graveNames;
    public static ArrayList<String> graveDogsNames;
    public static ArrayList<String> graveCatsNames;
    public static ArrayList<String> graveDeathMessages;

    private void getGravesText() {
        graveNames = readStringsFromFile(path + "graveNames.txt", GravesDefaultText.NAMES);
        graveDogsNames = readStringsFromFile(path + "graveDogsNames.txt", GravesDefaultText.DOG_NAMES);
        graveCatsNames = readStringsFromFile(path + "graveCatsNames.txt", GravesDefaultText.CAT_NAMES);
        graveDeathMessages = readStringsFromFile(path + "graveDeathMessages.txt", GravesDefaultText.DEATH_TEXT);
    }

    /*
     * Read text from file if it exist or get default text
     */
    private static ArrayList<String> readStringsFromFile(String fileName, String[] defaultValues) {
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList(defaultValues));

        return list;
    }
}