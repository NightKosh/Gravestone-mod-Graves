package gravestone.config;

import cpw.mods.fml.client.registry.RenderingRegistry;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

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
    // renderer Id
    public static int graveRenderID = RenderingRegistry.getNextAvailableRenderId();
    public static int memorialRenderID = RenderingRegistry.getNextAvailableRenderId();
    public static int spawnerRenderID = RenderingRegistry.getNextAvailableRenderId();
    public static int skullCandleRenderID = RenderingRegistry.getNextAvailableRenderId();
    public static int candleRenderID = RenderingRegistry.getNextAvailableRenderId();
    // world generator
    public static boolean generateCatacombs;
    public static boolean generateSingleGraves;
    public static boolean generateMemorials;
    public static int maxCatacombsHeight;
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
    // forestry
    public static boolean enableForestryBackpacks;

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
    //foreign mobs spawn
    public static boolean spawnMoCreaturesMobs;
    // sword grave
    public static boolean generateSwordGraves;
    // spawn chance
    public static int spawnChance;
    public static boolean removeEmptyGraves;
    public static boolean showGravesRemovingMessages;

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
        config.save();
        getGravesText();
    }

    private static void structures() {
        generateCatacombs = config.get(Configuration.CATEGORY_GENERAL, "GenerateCatacombs", true).getBoolean(true);
        maxCatacombsHeight = config.get(Configuration.CATEGORY_GENERAL, "MaximumCatacombsGenerationHeight", 75).getInt();
        generateMemorials = config.get(Configuration.CATEGORY_GENERAL, "GenerateMemorials", true).getBoolean(true);
        generateSingleGraves = config.get(Configuration.CATEGORY_GENERAL, "GenerateSingleGraves", true).getBoolean(true);
        generateCemeteries = config.get(Configuration.CATEGORY_GENERAL, "GenerateCemeteries", true).getBoolean(true);
        generateVillageMemorials = config.get(Configuration.CATEGORY_GENERAL, "GenerateVillageMemorials", true).getBoolean(true);
        generateUndertaker = config.get(Configuration.CATEGORY_GENERAL, "GenerateUndertaker", true).getBoolean(true);
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

        spawnChance = config.get(Configuration.CATEGORY_GENERAL, "SpawnChance", 80).getInt();
        
        // spawned creatures
        spawnMoCreaturesMobs = config.get(Configuration.CATEGORY_GENERAL, "SpawnMoCreaturesMobsByGraves", true).getBoolean(true);

        isFogEnabled = config.get(Configuration.CATEGORY_GENERAL, "IsFogEnabled", true).getBoolean(true);
        
        enableNightStone   = config.get(Configuration.CATEGORY_GENERAL, "EnableNightStone", true).getBoolean(true);
        enableThunderStone = config.get(Configuration.CATEGORY_GENERAL, "EnableThunderStone", true).getBoolean(true);
        showNightStoneMessage = config.get(Configuration.CATEGORY_GENERAL, "ShowNightStoneMessage", true).getBoolean(true);

        // creeper statues
        enableCreeperStatuesRecipes = config.get(Configuration.CATEGORY_GENERAL, "EnableCreeperStatuesRecipes", false).getBoolean(false);

        // spawners recipes
        enableBossSpawnerCraftingRecipe = config.get(Configuration.CATEGORY_GENERAL, "EnableBossSpawnerCraftingRecipe", true).getBoolean(true);
        enableSpawnerCraftingRecipe = config.get(Configuration.CATEGORY_GENERAL, "EnableMonsterSpawnerCraftingRecipe", true).getBoolean(true);

        // haunted chest
        replaceHauntedChest = config.get(Configuration.CATEGORY_GENERAL, "ReplaceHauntedChest", false).getBoolean(false);


        removeEmptyGraves = config.get(Configuration.CATEGORY_GENERAL, "RemoveEmptyGraves", false).getBoolean(false);
        showGravesRemovingMessages = config.get(Configuration.CATEGORY_GENERAL, "ShowGravesRemovingMessages", true).getBoolean(true);

        enableForestryBackpacks = config.get(Configuration.CATEGORY_GENERAL, "EnableForestryBackpacks", true).getBoolean(true);
    }

    private static void entityConfig() {
        spawnZombieDogs = config.get(Configuration.CATEGORY_GENERAL, "SpawnZombieDogs", true).getBoolean(true);
        spawnZombieCats = config.get(Configuration.CATEGORY_GENERAL, "SpawnZombieCats", true).getBoolean(true);
        spawnSkeletonDogs = config.get(Configuration.CATEGORY_GENERAL, "SpawnSkeletonDogs", true).getBoolean(true);
        spawnSkeletonCats = config.get(Configuration.CATEGORY_GENERAL, "SpawnSkeletonCats", true).getBoolean(true);

        spawnSkullCrawlersAtMobsDeath = config.get(Configuration.CATEGORY_GENERAL, "SpawnSkullCrawlersAtMobsDeath", true).getBoolean(true);
        spawnSkullCrawlersAtBoneBlockDestruction = config.get(Configuration.CATEGORY_GENERAL, "SpawnSkullCrawlersOnBoneBlockDestruction", true).getBoolean(true);
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
