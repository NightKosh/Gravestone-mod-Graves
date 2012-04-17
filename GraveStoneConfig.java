package net.minecraft.GraveStone;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.GraveStone.block.BlockGSGraveStone;
import net.minecraft.GraveStone.block.BlockGSMemorial;
import net.minecraft.GraveStone.block.BlockGSTimeTrap;
import net.minecraft.GraveStone.block.BlockGSWitherSpawner;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class GraveStoneConfig {

    private static Configuration config;
    private static GraveStoneConfig instance;

    
    // block GraveStone
    public static int graveStoneID;
    public static BlockGSGraveStone graveStone;
    // Block wither spawer
    public static int witherSpawnerID;
    public static BlockGSWitherSpawner witherSpawner;
    // Block Time Trap
    public static int timeTrapID;
    public static BlockGSTimeTrap timeTrap;
    // block memorial
    public static int memorialID;
    public static BlockGSMemorial memorial;
    // renderer Id
    public static int graveRenderID = RenderingRegistry.getNextAvailableRenderId();
    public static int memorialRenderID = RenderingRegistry.getNextAvailableRenderId();
    // world generator
    public static boolean generateCatacombs;
    public static boolean generateSingleGraves;
    public static boolean generateMemorials;
    // graves for entities
    public static boolean generateVillagerGraves;
    public static boolean generatePetGraves;
    // saved items count
    public static int graveItemsCount;
    // silk touch for graves
    public static boolean silkTouchForGraves;
    // item chisel
    public static int chiselId;
    public static Item chisel;
    
    private GraveStoneConfig(Configuration config) {
        this.config = config;
    }
    
    public static GraveStoneConfig getInstance(Configuration config) {
        if (instance == null) {
            return new GraveStoneConfig(config);
        } else {
            return instance;
        }
        
    }

    public void getConfigs() {
        config.load();

        idConfig();
        structures();
        gravesConfig();

        config.save();
    }

    private static void idConfig() {
        // blocks
        graveStoneID = config.getBlock("GraveStone", 1551).getInt();
        witherSpawnerID = config.getBlock("WitherSpawner", 1552).getInt();
        timeTrapID = config.getBlock("TimeTrap", 1553).getInt();
        memorialID = config.getBlock("Memorial", 1554).getInt();
        // items
        chiselId = config.getItem("Chisel", 9001 - 256).getInt();
    }

    private static void structures() {
        generateCatacombs = config.get(Configuration.CATEGORY_GENERAL, "GenerateCatacombs", true).getBoolean(true);
        generateMemorials = config.get(Configuration.CATEGORY_GENERAL, "GenerateMemorials", true).getBoolean(true);
        generateSingleGraves = config.get(Configuration.CATEGORY_GENERAL, "GenerateSingleGraves", true).getBoolean(true);
    }

    private static void gravesConfig() {
        silkTouchForGraves = config.get(Configuration.CATEGORY_GENERAL, "SilkTouchForGraves", true).getBoolean(true);
        generateVillagerGraves = config.get(Configuration.CATEGORY_GENERAL, "GenerateVillagerGraves", true).getBoolean(true);
        generatePetGraves = config.get(Configuration.CATEGORY_GENERAL, "GeneratePetGraves", true).getBoolean(true);

        Property graveItemsCountProperty = config.get(Configuration.CATEGORY_GENERAL, "SavedItemsCount", 0);
        graveItemsCountProperty.comment = "This value must be between 0 an 40!";
        
        graveItemsCount = graveItemsCountProperty.getInt();
        if (graveItemsCount > 40 || graveItemsCount < 0) {
            graveItemsCount = 40;
        }
    }
}
