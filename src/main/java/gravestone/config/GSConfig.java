package gravestone.config;

import gravestone.core.GSPotion;
import gravestone.structures.GraveStoneWorldGenerator;
import gravestone.structures.catacombs.CatacombsGenerator;
import gravestone.structures.catacombs.CatacombsLevel;
import gravestone.structures.village.undertaker.VillageHandlerGSUndertaker;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSConfig {

    private static Configuration config;
    private static GSConfig instance;
    private static String path;
    // CATEGORIES
    public static final String CATEGORY_COMPATIBILITY = "compatibility";
    public static final String CATEGORY_STRUCTURES_CATACOMBS = "structures_catacombs";
    public static final String CATEGORY_STRUCTURES_VILLAGE = "structures_village";
    public static final String CATEGORY_STRUCTURES_OTHER = "structures_other";
    public static final String CATEGORY_POTIONS = "potions";
    public static final String CATEGORY_GRAVES = "graves";
    public static final String CATEGORY_RECIPES = "recipes";
    public static final String CATEGORY_MOBS = "mobs";
    // renderer Id
    //TODO
//    public static int graveRenderID = RenderingRegistry.getNextAvailableRenderId();
//    public static int memorialRenderID = RenderingRegistry.getNextAvailableRenderId();
//    public static int spawnerRenderID = RenderingRegistry.getNextAvailableRenderId();
//    public static int skullCandleRenderID = RenderingRegistry.getNextAvailableRenderId();
//    public static int candleRenderID = RenderingRegistry.getNextAvailableRenderId();
//    public static int pileOfBonesRenderID = RenderingRegistry.getNextAvailableRenderId();

    private GSConfig(String path, File configFile) {
        this.config = new Configuration(configFile);
        this.path = path;
        getConfigs();
    }

    public static GSConfig getInstance(String path, String configFile) {
        if (instance == null) {
            return new GSConfig(path, new File(path + configFile));
        } else {
            return instance;
        }
    }

    public final void getConfigs() {
        config.load();
        structures();
        gravesConfig();
        otherConfigs();
        recipesConfigs();
        entityConfig();
        compatibilityConfigs();
        config.save();
        getGravesText();
    }

    // catacombs
    public static int structuresDimensionId;
    public static boolean generateCatacombs;
    public static int maxCatacombsHeight;
    public static double catacombsGenerationChance;
    public static boolean generateCatacombsGraveyard;
    public static int catacombsMinRoomsCountAt1Level;
    public static int catacombsMaxRoomsCountAt1Level;
    public static int catacombsMinRoomsCountAt2Level;
    public static int catacombsMaxRoomsCountAt2Level;
    public static int catacombsMinRoomsCountAt3Level;
    public static int catacombsMaxRoomsCountAt3Level;
    public static int catacombsMinRoomsCountAt4Level;
    public static int catacombsMaxRoomsCountAt4Level;
    // other structures
    public static boolean generateGravesInMushroomBiomes;
    public static boolean generateSingleGraves;
    public static boolean generateMemorials;
    // village
    public static boolean generateCemeteries;
    public static boolean generateVillageMemorials;
    public static boolean generateUndertaker;
    public static int undertakerId;

    private static void structures() {
        // catacombs
        structuresDimensionId = config.get(CATEGORY_STRUCTURES_CATACOMBS, "StructuresDimensionId", GraveStoneWorldGenerator.DEFAULT_DIMENSION_ID).getInt();
        generateCatacombs = config.get(CATEGORY_STRUCTURES_CATACOMBS, "GenerateCatacombs", true).getBoolean(true);
        maxCatacombsHeight = config.get(CATEGORY_STRUCTURES_CATACOMBS, "MaximumCatacombsGenerationHeight", 75).getInt();
        catacombsGenerationChance = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsGenerationChance", CatacombsGenerator.DEFAULT_GENERATION_CHANCE).getDouble();
        generateCatacombsGraveyard = config.get(CATEGORY_STRUCTURES_CATACOMBS, "GenerateCatacombsGraveyard", true).getBoolean(true);

        catacombsMinRoomsCountAt1Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMinRoomsCountAt1Level", CatacombsLevel.DEFAULT_MIN_ROOMS_COUNT_AT_1_LEVEL).getInt();
        catacombsMaxRoomsCountAt1Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMaxRoomsCountAt1Level", CatacombsLevel.DEFAULT_MAX_ROOMS_COUNT_AT_1_LEVEL).getInt();

        catacombsMinRoomsCountAt2Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMinRoomsCountAt2Level", CatacombsLevel.DEFAULT_MIN_ROOMS_COUNT_AT_2_LEVEL).getInt();
        catacombsMaxRoomsCountAt2Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMaxRoomsCountAt2Level", CatacombsLevel.DEFAULT_MAX_ROOMS_COUNT_AT_2_LEVEL).getInt();

        catacombsMinRoomsCountAt3Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMinRoomsCountAt3Level", CatacombsLevel.DEFAULT_MIN_ROOMS_COUNT_AT_3_LEVEL).getInt();
        catacombsMaxRoomsCountAt3Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMaxRoomsCountAt3Level", CatacombsLevel.DEFAULT_MAX_ROOMS_COUNT_AT_3_LEVEL).getInt();

        catacombsMinRoomsCountAt4Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMinRoomsCountAt4Level", CatacombsLevel.DEFAULT_MIN_ROOMS_COUNT_AT_4_LEVEL).getInt();
        catacombsMaxRoomsCountAt4Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMaxRoomsCountAt4Level", CatacombsLevel.DEFAULT_MAX_ROOMS_COUNT_AT_4_LEVEL).getInt();

        // other
        generateGravesInMushroomBiomes = config.get(CATEGORY_STRUCTURES_OTHER, "GenerateGravesInMushroomBiomes", true).getBoolean(true);
        generateMemorials = config.get(CATEGORY_STRUCTURES_OTHER, "GenerateMemorials", true).getBoolean(true);
        generateSingleGraves = config.get(CATEGORY_STRUCTURES_OTHER, "GenerateSingleGraves", true).getBoolean(true);
        // village
        generateCemeteries = config.get(CATEGORY_STRUCTURES_VILLAGE, "GenerateCemeteries", true).getBoolean(true);
        generateVillageMemorials = config.get(CATEGORY_STRUCTURES_VILLAGE, "GenerateVillageMemorials", true).getBoolean(true);
        generateUndertaker = config.get(CATEGORY_STRUCTURES_VILLAGE, "GenerateUndertaker", true).getBoolean(true);
        undertakerId = config.get(CATEGORY_STRUCTURES_VILLAGE, "undertakerId", VillageHandlerGSUndertaker.UNDERTAKER_ID).getInt();
    }

    // graves for entities
    public static boolean generatePlayerGraves;
    public static boolean generateVillagerGraves;
    public static boolean generatePetGraves;
    public static boolean generateGravesInLava;
    public static int graveItemsCount;
    public static int graveSpawnRate;
    public static boolean canPlaceGravesEveryWhere;
    public static boolean spawnMobAtGraveDestruction;
    public static boolean isFogEnabled;
    public static boolean generateSwordGraves;
    public static int spawnChance;
    public static boolean removeEmptyGraves;
    public static boolean showGravesRemovingMessages;
    public static boolean onlyOwnerCanLootGrave;

    private static void gravesConfig() {
        canPlaceGravesEveryWhere = config.get(CATEGORY_GRAVES, "AllowToPlaceGravesEveryWhere", false).getBoolean(false);
        generatePlayerGraves = config.get(CATEGORY_GRAVES, "GeneratePlayersGraves", true).getBoolean(true);
        generateVillagerGraves = config.get(CATEGORY_GRAVES, "GenerateVillagersGraves", true).getBoolean(true);
        generatePetGraves = config.get(CATEGORY_GRAVES, "GeneratePetsGraves", true).getBoolean(true);
        generateGravesInLava = config.get(CATEGORY_GRAVES, "GenerateGravesInLava", true).getBoolean(true);
        generateSwordGraves = config.get(CATEGORY_GRAVES, "GenerateSwordGraves", true).getBoolean(true);
        onlyOwnerCanLootGrave = config.get(CATEGORY_GRAVES, "OnlyOwnerCanLootGrave", false).getBoolean(false);


        // store items
        Property graveItemsCountProperty = config.get(CATEGORY_GRAVES, "SavedItemsCount", 40);
        graveItemsCountProperty.comment = "This value must be between 0 an 40(in this case all items will be stored)!";
        graveItemsCount = graveItemsCountProperty.getInt();

        if (graveItemsCount > 40 || graveItemsCount < 0) {
            graveItemsCount = 40;
        }

        // spawn rate
        Property graveSpawnRateProperty = config.get(CATEGORY_GRAVES, "GravesMobsSpawnRate", 1000);
        graveSpawnRateProperty.comment = "This value must be bigger than 600!";
        graveSpawnRate = graveSpawnRateProperty.getInt();

        if (graveSpawnRate < 600) {
            graveSpawnRate = 600;
        }

        spawnMobAtGraveDestruction = config.get(CATEGORY_GRAVES, "SpawnMobAtGraveDestruction", true).getBoolean(true);
        spawnChance = config.get(CATEGORY_GRAVES, "GravesMobsSpawnChance", 80).getInt();

        removeEmptyGraves = config.get(CATEGORY_GRAVES, "RemoveEmptyGraves", false).getBoolean(false);
        showGravesRemovingMessages = config.get(CATEGORY_GRAVES, "ShowGravesRemovingMessages", true).getBoolean(true);

        isFogEnabled = config.get(CATEGORY_GRAVES, "CemeteryFogEnabled", true).getBoolean(true);
    }

    // disable/enable time changing by night stone
    public static boolean enableNightStone;
    public static boolean enableThunderStone;
    public static boolean showNightStoneMessage;
    // haunted chest
    public static boolean replaceHauntedChest;
    // potions id
    public static int cursePotionEffectId;

    private static void otherConfigs() {
        // trap blocks
        enableNightStone = config.get(Configuration.CATEGORY_GENERAL, "EnableNightStone", true).getBoolean(true);
        enableThunderStone = config.get(Configuration.CATEGORY_GENERAL, "EnableThunderStone", true).getBoolean(true);
        showNightStoneMessage = config.get(Configuration.CATEGORY_GENERAL, "ShowNightStoneMessage", true).getBoolean(true);

        // haunted chest
        replaceHauntedChest = config.get(Configuration.CATEGORY_GENERAL, "ReplaceHauntedChest", false).getBoolean(false);

        // potions
        cursePotionEffectId = config.get(CATEGORY_POTIONS, "CursePotionEffectId", GSPotion.CURSE_DEFAULT_ID).getInt();
    }

    // recipes
    public static boolean enableCreeperStatuesRecipes;
    public static boolean enableBossSpawnerCraftingRecipe;
    public static boolean enableSpawnerCraftingRecipe;

    private static void recipesConfigs() {
        // creeper statues
        enableCreeperStatuesRecipes = config.get(CATEGORY_RECIPES, "EnableCreeperStatuesRecipes", false).getBoolean(false);

        // spawners recipes
        enableBossSpawnerCraftingRecipe = config.get(CATEGORY_RECIPES, "EnableBossSpawnerCraftingRecipe", true).getBoolean(true);
        enableSpawnerCraftingRecipe = config.get(CATEGORY_RECIPES, "EnableMonsterSpawnerCraftingRecipe", true).getBoolean(true);
    }

    // mobs
    public static boolean spawnZombieDogs;
    public static boolean spawnZombieCats;
    public static boolean spawnSkeletonDogs;
    public static boolean spawnSkeletonCats;
    public static boolean spawnSkullCrawlersAtMobsDeath;
    public static boolean spawnSkullCrawlersAtBoneBlockDestruction;
    public static boolean spawnSkeletonRaiders;
    public static boolean spawnZombieRaiders;

    private static void entityConfig() {
        spawnZombieDogs = config.get(CATEGORY_MOBS, "SpawnZombieDogsInTheWorld", true).getBoolean(true);
        spawnZombieCats = config.get(CATEGORY_MOBS, "SpawnZombieCatsInTheWorld", true).getBoolean(true);
        spawnSkeletonDogs = config.get(CATEGORY_MOBS, "SpawnSkeletonDogsInTheWorld", true).getBoolean(true);
        spawnSkeletonCats = config.get(CATEGORY_MOBS, "SpawnSkeletonCatsInTheWorld", true).getBoolean(true);
        //raiders
        spawnSkeletonRaiders = config.get(CATEGORY_MOBS, "SpawnSkeletonRaidersInTheWorld", true).getBoolean(true);
        spawnZombieRaiders = config.get(CATEGORY_MOBS, "SpawnZombieRaidersInTheWorld", true).getBoolean(true);

        spawnSkullCrawlersAtMobsDeath = config.get(CATEGORY_MOBS, "SpawnSkullCrawlersAtMobsDeath", true).getBoolean(true);
        spawnSkullCrawlersAtBoneBlockDestruction = config.get(CATEGORY_MOBS, "SpawnSkullCrawlersAnBoneBlockDestruction", true).getBoolean(true);
    }

    // COMPATIBILITY
    public static boolean spawnMoCreaturesMobs;
    public static boolean enableForestryBackpacks;
    public static boolean storeBattlegearItems;
    public static boolean storeTheCampingModItems;
    public static boolean storeBaublesItems;
    public static boolean storeMaricultureItems;
    public static boolean storeTinkerConstructItems;
    public static boolean storeRpgInventoryItems;
    public static boolean storeGalacticraftItems;
    public static boolean storeBackpacksItems;
    public static boolean enableArsMagicaSoulbound;
    public static boolean enableEnderIOSoulbound;
    public static boolean enableTwilightForestKeeping;

    private static void compatibilityConfigs() {
        spawnMoCreaturesMobs = config.get(CATEGORY_COMPATIBILITY, "SpawnMoCreaturesMobs", true).getBoolean(true);

        enableForestryBackpacks = config.get(CATEGORY_COMPATIBILITY, "EnableForestryBackpacks", true).getBoolean(true);

        storeBattlegearItems = config.get(CATEGORY_COMPATIBILITY, "StoreBattlegearItems", true).getBoolean(true);
        storeTheCampingModItems = config.get(CATEGORY_COMPATIBILITY, "StoreTheCampingModItems", true).getBoolean(true);
        storeBaublesItems = config.get(CATEGORY_COMPATIBILITY, "StoreBaublesItems", true).getBoolean(true);
        storeMaricultureItems = config.get(CATEGORY_COMPATIBILITY, "StoreMaricultureItems", true).getBoolean(true);
        storeTinkerConstructItems = config.get(CATEGORY_COMPATIBILITY, "StoreTinkerConstructItems", true).getBoolean(true);
        storeRpgInventoryItems = config.get(CATEGORY_COMPATIBILITY, "StoreRpgInventoryItems", true).getBoolean(true);
        storeGalacticraftItems = config.get(CATEGORY_COMPATIBILITY, "StoreGalacticraftItems", true).getBoolean(true);
        storeBackpacksItems = config.get(CATEGORY_COMPATIBILITY, "StoreBackpacksItems", true).getBoolean(true);

        enableArsMagicaSoulbound = config.get(CATEGORY_COMPATIBILITY, "EnableArsMagicaSoulbound", true).getBoolean(true);
        enableEnderIOSoulbound = config.get(CATEGORY_COMPATIBILITY, "EnableEnderIOSoulbound", true).getBoolean(true);
        enableTwilightForestKeeping = config.get(CATEGORY_COMPATIBILITY, "EnableTwilightForestCharmsOfKeeping", true).getBoolean(true);
    }

    // grave names
    public static ArrayList<String> graveNames;
    public static ArrayList<String> graveDogsNames;
    public static ArrayList<String> graveCatsNames;
    public static ArrayList<String> graveDeathMessages;
    public static ArrayList<String> memorialText;
    public static ArrayList<String> dogsMemorialText;
    public static ArrayList<String> catsMemorialText;

    private void getGravesText() {
        graveNames = readStringsFromFile(path + "graveNames.txt", GravesDefaultText.NAMES);
        graveDogsNames = readStringsFromFile(path + "graveDogsNames.txt", GravesDefaultText.DOG_NAMES);
        graveCatsNames = readStringsFromFile(path + "graveCatsNames.txt", GravesDefaultText.CAT_NAMES);
        graveDeathMessages = readStringsFromFile(path + "graveDeathMessages.txt", GravesDefaultText.DEATH_TEXT);
        memorialText = readStringsFromFile(path + "memorialText.txt", GravesDefaultText.MEMORIAL_TEXT);
        dogsMemorialText = readStringsFromFile(path + "dogsMemorialText.txt", GravesDefaultText.DOGS_MEMORIAL_TEXT);
        catsMemorialText = readStringsFromFile(path + "catsMemorialText.txt", GravesDefaultText.CATS_MEMORIAL_TEXT);
    }

    /*
     * Read text from file if it exist or get default text
     */
    private static ArrayList<String> readStringsFromFile(String fileName, String[] defaultValues) {
        ArrayList<String> list = new ArrayList();
        /*boolean exception = false;
         File file = new File(fileName);

         if (file.exists() && file.canRead()) {
         try {
         BufferedReader reader = new BufferedReader(new FileReader(file));
         String line;

         while ((line = reader.readLine()) != null) {
         list.add(line);
         }

         reader.close();
         } catch (IOException e) {
         exception = true;
         e.printStackTrace();
         }
         } else {
         try {
         file.createNewFile();
         } catch (IOException e) {
         e.printStackTrace();
         }
         }

         if (list.isEmpty() || exception) {
         list = new ArrayList();
         list.addAll(Arrays.asList(defaultValues));

         if (file.canWrite()) {
         try {
         BufferedWriter writer = new BufferedWriter(new FileWriter(file));

         for (int i = 0; i < list.size(); i++) {
         writer.write(list.get(i));
         writer.newLine();
         }

         writer.close();
         } catch (IOException e) {
         e.printStackTrace();
         }
         }
         }
         */
        list.addAll(Arrays.asList(defaultValues));

        return list;
    }
}