package gravestone.config;

import cpw.mods.fml.client.registry.RenderingRegistry;
import gravestone.block.GraveStoneHelper;
import gravestone.core.GSPotion;
import gravestone.structures.GraveStoneWorldGenerator;
import gravestone.structures.catacombs.CatacombsGenerator;
import gravestone.structures.catacombs.CatacombsLevel;
import gravestone.structures.village.VillageHandlerGSUndertaker;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

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
public class GraveStoneConfig {

    private static Configuration config;
    private static GraveStoneConfig instance;
    private static String path;
    // CATEGORIES
    public static final String CATEGORY_COMPATIBILITY = "compatibility";
    public static final String CATEGORY_POTIONS = "potions";
    public static final String CATEGORY_STRUCTURES_CATACOMBS = "structures_catacombs";
    public static final String CATEGORY_RECIPES = "recipes";
    // renderer Id
    public static int graveRenderID = RenderingRegistry.getNextAvailableRenderId();
    public static int memorialRenderID = RenderingRegistry.getNextAvailableRenderId();
    public static int spawnerRenderID = RenderingRegistry.getNextAvailableRenderId();
    public static int skullCandleRenderID = RenderingRegistry.getNextAvailableRenderId();
    public static int candleRenderID = RenderingRegistry.getNextAvailableRenderId();
    public static int pileOfBonesRenderID = RenderingRegistry.getNextAvailableRenderId();
    // world generator
    public static int structuresDimensionId;
    public static boolean generateCatacombs;
    public static boolean generateSingleGraves;
    public static boolean generateMemorials;
    public static int maxCatacombsHeight;
    public static double catacombsGenerationChance;
    // village generator
    public static boolean generateCemeteries;
    public static boolean generateVillageMemorials;
    public static boolean generateUndertaker;
    // graves for entities
    public static boolean generatePlayerGraves;
    public static boolean generateVillagerGraves;
    public static boolean generatePetGraves;
    // graves by damage
    public static boolean generateGravesInLava;
    // saved items count
    public static int graveItemsCount;
    // spawn rate
    public static int graveSpawnRate;
    // allowed ground for graves
    public static boolean canPlaceGravesEveryWhere;
    public static boolean spawnMobAtGraveDestruction;
    public static boolean isFogEnabled;
    // disable/enable time changing by night stone
    public static boolean enableNightStone;
    public static boolean enableThunderStone;
    public static boolean showNightStoneMessage;
    // creeper memorials recipes
    public static boolean enableCreeperStatuesRecipes;
    // spawner reciepes
    public static boolean enableBossSpawnerCraftingRecipe;
    public static boolean enableSpawnerCraftingRecipe;
    // nightstone
    public static boolean craftableNightStone;
    public static boolean craftableThunderStone;
    // altar recipe
    public static boolean hardAltarRecipe;

    // haunted chest
    public static boolean replaceHauntedChest;
    // grave names
    public static ArrayList<String> graveNames;
    public static ArrayList<String> graveDogsNames;
    public static ArrayList<String> graveCatsNames;
    public static ArrayList<String> graveDeathMessages;
    public static ArrayList<String> memorialText;
    public static ArrayList<String> dogsMemorialText;
    public static ArrayList<String> catsMemorialText;
    // spawn undead pets in the world
    public static boolean spawnZombieDogs;
    public static boolean spawnZombieCats;
    public static boolean spawnSkeletonDogs;
    public static boolean spawnSkeletonCats;
    public static boolean spawnSkullCrawlersAtMobsDeath;
    public static boolean spawnSkullCrawlersAtBoneBlockDestruction;
    // sword grave
    public static boolean generateSwordGraves;
    // spawn chance
    public static int spawnChance;
    public static boolean removeEmptyGraves;
    public static boolean showGravesRemovingMessages;
    //structures
    public static boolean generateCatacombsGraveyard;
    public static int catacombsMinRoomsCountAt1Level;
    public static int catacombsMaxRoomsCountAt1Level;
    public static int catacombsMinRoomsCountAt2Level;
    public static int catacombsMaxRoomsCountAt2Level;
    public static int catacombsMinRoomsCountAt3Level;
    public static int catacombsMaxRoomsCountAt3Level;
    public static int catacombsMinRoomsCountAt4Level;
    public static int catacombsMaxRoomsCountAt4Level;
    public static boolean generatePilesOfBones;
    public static boolean generateGravesInMushroomBiomes;
    // villager
    public static int undertakerId;
    // potions id
    public static int cursePotionEffectId;

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
    public static boolean enableAntiqueAtlasDeathMarkers;



    public static List<GraveStoneHelper.RestrictedArea> restrictGraveGenerationInArea;


    private GraveStoneConfig(String path, File configFile) {
        this.config = new Configuration(configFile);
        this.path = path;
        getConfigs();
    }

    public static GraveStoneConfig getInstance(String path, String configFile) {
        if (instance == null) {
            return new GraveStoneConfig(path, new File(path + configFile));
        } else {
            return instance;
        }
    }

    public final void getConfigs() {
        config.load();
        structures();
        gravesConfig();
        entityConfig();
        compatibilityConfigs();
        config.save();
        getGravesText();
    }

    private static void structures() {
        structuresDimensionId = config.get(Configuration.CATEGORY_GENERAL, "StructuresDimensionId", GraveStoneWorldGenerator.DEFAULT_DIMENSION_ID).getInt();
        generateCatacombs = config.get(Configuration.CATEGORY_GENERAL, "GenerateCatacombs", true).getBoolean(true);
        maxCatacombsHeight = config.get(Configuration.CATEGORY_GENERAL, "MaximumCatacombsGenerationHeight", 75).getInt();
        catacombsGenerationChance = config.get(Configuration.CATEGORY_GENERAL, "CatacombsGenerationChance", CatacombsGenerator.DEFAULT_GENERATION_CHANCE).getDouble();
        generateCatacombsGraveyard = config.get(Configuration.CATEGORY_GENERAL, "GenerateCatacombsGraveyard", true).getBoolean(true);
        generateGravesInMushroomBiomes = config.get(Configuration.CATEGORY_GENERAL, "GenerateGravesInMushroomBiomes", true).getBoolean(true);
        generateMemorials = config.get(Configuration.CATEGORY_GENERAL, "GenerateMemorials", true).getBoolean(true);
        generateSingleGraves = config.get(Configuration.CATEGORY_GENERAL, "GenerateSingleGraves", true).getBoolean(true);
        generateCemeteries = config.get(Configuration.CATEGORY_GENERAL, "GenerateCemeteries", true).getBoolean(true);
        generateVillageMemorials = config.get(Configuration.CATEGORY_GENERAL, "GenerateVillageMemorials", true).getBoolean(true);
        generateUndertaker = config.get(Configuration.CATEGORY_GENERAL, "GenerateUndertaker", true).getBoolean(true);
        undertakerId = config.get(Configuration.CATEGORY_GENERAL, "undertakerId", VillageHandlerGSUndertaker.UNDERTAKER_ID).getInt();

        generatePilesOfBones = config.get(CATEGORY_STRUCTURES_CATACOMBS, "GeneratePilesOfBones", true).getBoolean(true);

        catacombsMinRoomsCountAt1Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMinRoomsCountAt1Level", CatacombsLevel.DEFAULT_MIN_ROOMS_COUNT_AT_1_LEVEL).getInt();
        catacombsMaxRoomsCountAt1Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMaxRoomsCountAt1Level", CatacombsLevel.DEFAULT_MAX_ROOMS_COUNT_AT_1_LEVEL).getInt();

        catacombsMinRoomsCountAt2Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMinRoomsCountAt2Level", CatacombsLevel.DEFAULT_MIN_ROOMS_COUNT_AT_2_LEVEL).getInt();
        catacombsMaxRoomsCountAt2Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMaxRoomsCountAt2Level", CatacombsLevel.DEFAULT_MAX_ROOMS_COUNT_AT_2_LEVEL).getInt();

        catacombsMinRoomsCountAt3Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMinRoomsCountAt3Level", CatacombsLevel.DEFAULT_MIN_ROOMS_COUNT_AT_3_LEVEL).getInt();
        catacombsMaxRoomsCountAt3Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMaxRoomsCountAt3Level", CatacombsLevel.DEFAULT_MAX_ROOMS_COUNT_AT_3_LEVEL).getInt();

        catacombsMinRoomsCountAt4Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMinRoomsCountAt4Level", CatacombsLevel.DEFAULT_MIN_ROOMS_COUNT_AT_4_LEVEL).getInt();
        catacombsMaxRoomsCountAt4Level = config.get(CATEGORY_STRUCTURES_CATACOMBS, "CatacombsMaxRoomsCountAt4Level", CatacombsLevel.DEFAULT_MAX_ROOMS_COUNT_AT_4_LEVEL).getInt();

    }

    private static void gravesConfig() {
        canPlaceGravesEveryWhere = config.get(Configuration.CATEGORY_GENERAL, "CanPlaceGravesEveryWhere", false).getBoolean(false);
        generatePlayerGraves = config.get(Configuration.CATEGORY_GENERAL, "GeneratePlayerGraves", true).getBoolean(true);
        generateVillagerGraves = config.get(Configuration.CATEGORY_GENERAL, "GenerateVillagerGraves", true).getBoolean(true);
        generatePetGraves = config.get(Configuration.CATEGORY_GENERAL, "GeneratePetGraves", true).getBoolean(true);
        generateGravesInLava = config.get(Configuration.CATEGORY_GENERAL, "GenerateGravesInLava", true).getBoolean(true);
        generateSwordGraves = config.get(Configuration.CATEGORY_GENERAL, "GenerateSwordGraves", true).getBoolean(true);
        
        // store items
        Property graveItemsCountProperty = config.get(Configuration.CATEGORY_GENERAL, "SavedItemsCount", 40);
        graveItemsCountProperty.comment = "This value must be between 0 an 40(in this case all items will be stored)!";
        graveItemsCount = graveItemsCountProperty.getInt();

        if (graveItemsCount > 40 || graveItemsCount < 0) {
            graveItemsCount = 40;
        }

        // spawn rate
        Property graveSpawnRateProperty = config.get(Configuration.CATEGORY_GENERAL, "SpawnRate", 1000);
        graveSpawnRateProperty.comment = "This value must be bigger than 600!";
        graveSpawnRate = graveSpawnRateProperty.getInt();

        if (graveSpawnRate < 600) {
            graveSpawnRate = 600;
        }

        spawnMobAtGraveDestruction = config.get(Configuration.CATEGORY_GENERAL, "SpawnMobAtGraveDestruction", true).getBoolean(true);
        spawnChance = config.get(Configuration.CATEGORY_GENERAL, "SpawnChance", 80).getInt();

        isFogEnabled = config.get(Configuration.CATEGORY_GENERAL, "IsFogEnabled", true).getBoolean(true);
        
        enableNightStone   = config.get(Configuration.CATEGORY_GENERAL, "EnableNightStone", true).getBoolean(true);
        enableThunderStone = config.get(Configuration.CATEGORY_GENERAL, "EnableThunderStone", true).getBoolean(true);
        showNightStoneMessage = config.get(Configuration.CATEGORY_GENERAL, "ShowNightStoneMessage", true).getBoolean(true);
        cursePotionEffectId = config.get(CATEGORY_POTIONS, "CursePotionEffectId", GSPotion.CURSE_DEFAULT_ID).getInt();

        // creeper statues
        enableCreeperStatuesRecipes = config.get(Configuration.CATEGORY_GENERAL, "EnableCreeperStatuesRecipes", false).getBoolean(false);

        // spawners recipes
        enableBossSpawnerCraftingRecipe = config.get(Configuration.CATEGORY_GENERAL, "EnableBossSpawnerCraftingRecipe", true).getBoolean(true);
        enableSpawnerCraftingRecipe = config.get(Configuration.CATEGORY_GENERAL, "EnableMonsterSpawnerCraftingRecipe", true).getBoolean(true);

        // haunted chest
        replaceHauntedChest = config.get(Configuration.CATEGORY_GENERAL, "ReplaceHauntedChest", false).getBoolean(false);


        removeEmptyGraves = config.get(Configuration.CATEGORY_GENERAL, "RemoveEmptyGraves", false).getBoolean(false);
        showGravesRemovingMessages = config.get(Configuration.CATEGORY_GENERAL, "ShowGravesRemovingMessages", true).getBoolean(true);


        Property restrictGraveGenerationInAreaProperty = config.get(Configuration.CATEGORY_GENERAL, "RestrictGraveGenerationInArea", "");
        restrictGraveGenerationInAreaProperty.comment = "List of coordinates in which graves generation must be disabled. \"start_x,start_y,start_z,end_x,end_y,end_z;\"";
        String ar = restrictGraveGenerationInAreaProperty.getString();
        String[] areas = ar.split(";");
        restrictGraveGenerationInArea = new ArrayList<GraveStoneHelper.RestrictedArea>(areas.length);
        for (String area : areas) {
            GraveStoneHelper.RestrictedArea restrictedArea = GraveStoneHelper.RestrictedArea.getFromString(area);
            if (restrictedArea != null) {
                restrictGraveGenerationInArea.add(restrictedArea);
            }
        }

        craftableNightStone = config.get(CATEGORY_RECIPES, "CraftableNightStone", true).getBoolean(true);
        craftableThunderStone = config.get(CATEGORY_RECIPES, "CraftableThunderStone", true).getBoolean(true);
        hardAltarRecipe = config.get(CATEGORY_RECIPES, "HardAltarRecipe", false).getBoolean(false);
    }

    private static void entityConfig() {
        spawnZombieDogs = config.get(Configuration.CATEGORY_GENERAL, "SpawnZombieDogs", true).getBoolean(true);
        spawnZombieCats = config.get(Configuration.CATEGORY_GENERAL, "SpawnZombieCats", true).getBoolean(true);
        spawnSkeletonDogs = config.get(Configuration.CATEGORY_GENERAL, "SpawnSkeletonDogs", true).getBoolean(true);
        spawnSkeletonCats = config.get(Configuration.CATEGORY_GENERAL, "SpawnSkeletonCats", true).getBoolean(true);

        spawnSkullCrawlersAtMobsDeath = config.get(Configuration.CATEGORY_GENERAL, "SpawnSkullCrawlersAtMobsDeath", true).getBoolean(true);
        spawnSkullCrawlersAtBoneBlockDestruction = config.get(Configuration.CATEGORY_GENERAL, "SpawnSkullCrawlersOnBoneBlockDestruction", true).getBoolean(true);
    }

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

        enableAntiqueAtlasDeathMarkers = config.get(CATEGORY_COMPATIBILITY, "EnableAntiqueAtlasDeathMarkers", true).getBoolean(true);
    }

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
