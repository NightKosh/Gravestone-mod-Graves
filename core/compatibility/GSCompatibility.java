/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravestone.core.compatibility;

import cpw.mods.fml.common.Loader;
import forestry.api.core.BlockInterface;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSBiomes;
import gravestone.core.GSMobSpawn;
import gravestone.core.GSReciepes;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibility {

    private static GSCompatibility instance;

    private GSCompatibility() {
        instance = this;
    }

    public static GSCompatibility getInstance() {
        return (instance == null) ? new GSCompatibility() : instance;
    }
    public static final short BATTLEGEAR_FIRST_SLOT = 150;
    public static final short BATTLEGEAR_LAST_SLOT = 155;
    private static boolean isMoCreaturesInstalled = false;
    private static boolean isHighlandsInstalled = false;
    private static boolean isBiomesOPlentyInstalled = false;
    private static boolean isExtrabiomesXLInstalled = false;
    private static boolean isBattlegearInstalled = false;
    private static boolean isArsMagicaInstalled = false;
    // mo_creatures mobs classes
    public static final String MO_CREATURES_S_SKELETON = "drzhark.mocreatures.entity.monster.MoCEntitySilverSkeleton";
    public static final String MO_CREATURES_WRAITH = "drzhark.mocreatures.entity.monster.MoCEntityWraith";
    public static final String MO_CREATURES_F_WRAITH = "drzhark.mocreatures.entity.monster.MoCEntityFlameWraith";
    public static final String MO_CREATURES_SCORPIONS = "drzhark.mocreatures.entity.monster.MoCEntityScorpion";

    public static boolean isMoCreaturesInstalled() {
        return isMoCreaturesInstalled;
    }

    public static boolean isHighlandsInstalled() {
        return isHighlandsInstalled;
    }

    public static boolean isBiomesOPlentyInstalled() {
        return isBiomesOPlentyInstalled;
    }

    public static boolean isExtrabiomesXLInstalled() {
        return isExtrabiomesXLInstalled;
    }

    public static boolean isBattlegearInstalled() {
        return isBattlegearInstalled;
    }

    public static boolean isArsMagicaInstalled() {
        return isArsMagicaInstalled;
    }

    public void checkMods() {

        // adding foreign mobs
        if (Loader.isModLoaded("MoCreatures")) {
            isMoCreaturesInstalled = true;
            if (GraveStoneConfig.spawnMoCreaturesMobs) {
                GSMobSpawn.addMoCreaturesMobs();
            }
        }

        //if (Loader.isModLoaded("TwilightForest")) {
            //isTwilightForestInstalled = true;
        //}

        // adding foreign bioms
        if (Loader.isModLoaded("Highlands")) {
            isHighlandsInstalled = true;
            GSBiomes.loadHighlandsBiomes();
            GSBiomes.addHighlandsBiomes();
        }

        if (Loader.isModLoaded("BiomesOPlenty")) {
            isBiomesOPlentyInstalled = true;
            GSBiomes.loadBiomsOPlentyBiomes();
            GSBiomes.addBiomsOPlentyBiomes();
        }

        if (Loader.isModLoaded("ExtrabiomesXL")) {
            isExtrabiomesXLInstalled = true;
            GSBiomes.addExtrabiomsXLBiomes();
        }

        if (Loader.isModLoaded("battlegear2")) {
            isBattlegearInstalled = true;
        }

        if (Loader.isModLoaded("arsmagica2")) {
            isArsMagicaInstalled = true;
        }

        if (Loader.isModLoaded("Thaumcraft")) {
            GSCompatibilityThaumcraft.addAspects();
            GSReciepes.addSkullCandleReciepes(ItemApi.getBlock("blockCandle", 0));
        }
        
        if (Loader.isModLoaded("Forestry")) {
            GSReciepes.addSkullCandleReciepes(BlockInterface.getBlock("candle"));
        }
    }

    public static boolean hasSoulbound(ItemStack stack) {
        Map enchantments = EnchantmentHelper.getEnchantments(stack);
        for (Object id : enchantments.keySet()) {
            Enchantment ench = Enchantment.enchantmentsList[((Integer) id).shortValue()];
            if (ench.getClass().getName().equals("am2.enchantments.EnchantmentSoulbound")) {
                return true;
            }
        }
        return false;
    }
}
