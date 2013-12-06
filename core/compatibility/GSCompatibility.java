/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravestone.core.compatibility;

import cpw.mods.fml.common.Loader;
import gravestone.core.GraveStoneBiomes;
import gravestone.core.GraveStoneMobSpawn;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

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
    private static boolean isTwilightForestInstalled = false;
    private static boolean isHighlandsInstalled = false;
    private static boolean isBiomesOPlentyInstalled = false;
    private static boolean isExtrabiomesXLInstalled = false;
    private static boolean isBattlegearInstalled = false;
    private static boolean isArsMagicaInstalled = false;

    public static boolean isMoCreaturesInstalled() {
        return isMoCreaturesInstalled;
    }

    public static boolean isTwilightForestInstalled() {
        return isTwilightForestInstalled;
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
            GraveStoneMobSpawn.addMoCreaturesMobs();
        }

        if (Loader.isModLoaded("TwilightForest")) {
            isTwilightForestInstalled = true;
            GraveStoneMobSpawn.addTwilightForestMobs();
        }

        // adding foreign bioms
        if (Loader.isModLoaded("Highlands")) {
            isHighlandsInstalled = true;
            GraveStoneBiomes.loadHighlandsBiomes();
            GraveStoneBiomes.addHighlandsBiomes();
        }

        if (Loader.isModLoaded("BiomesOPlenty")) {
            isBiomesOPlentyInstalled = true;
            GraveStoneBiomes.loadBiomsOPlentyBiomes();
            GraveStoneBiomes.addBiomsOPlentyBiomes();
        }

        if (Loader.isModLoaded("ExtrabiomesXL")) {
            isExtrabiomesXLInstalled = true;
            GraveStoneBiomes.addExtrabiomsXLBiomes();
        }

        if (Loader.isModLoaded("battlegear2")) {
            isBattlegearInstalled = true;
        }

        if (Loader.isModLoaded("arsmagica2")) {
            isArsMagicaInstalled = true;
        }
        /*
         // adding Thaumcraft aspects
         if (Loader.isModLoaded("Thaumcraft")) {
         initThaumCraft();
         }*/

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
    /*
     private void initThaumCraft() {
     try {
     ThaumcraftApi.registerObjectTag(GSBlock.graveStone.blockID, 0, new AspectList().add(Aspect.SOUL, 1).add(Aspect.STONE, 1));
     ThaumcraftApi.registerObjectTag(GSBlock.graveStone.blockID, 1, new AspectList().add(Aspect.SOUL, 1).add(Aspect.STONE, 1));
     ThaumcraftApi.registerObjectTag(GSBlock.graveStone.blockID, 2, new AspectList().add(Aspect.SOUL, 1).add(Aspect.STONE, 1));

     ThaumcraftApi.registerObjectTag(GSBlock.graveStone.blockID, 3, new AspectList().add(Aspect.SOUL, 1).add(Aspect.STONE, 1)
     .add(Aspect.BEAST, 1));
     ThaumcraftApi.registerObjectTag(GSBlock.graveStone.blockID, 4, new AspectList().add(Aspect.SOUL, 1).add(Aspect.STONE, 1)
     .add(Aspect.BEAST, 1));

     ThaumcraftApi.registerObjectTag(GSBlock.graveStone.blockID, 5, new AspectList().add(Aspect.SOUL, 1).add(Aspect.WEAPON, 4));
     ThaumcraftApi.registerObjectTag(GSBlock.graveStone.blockID, 6, new AspectList().add(Aspect.SOUL, 1).add(Aspect.WEAPON, 6)
     .add(Aspect.STONE, 1));
     ThaumcraftApi.registerObjectTag(GSBlock.graveStone.blockID, 7, new AspectList().add(Aspect.SOUL, 1).add(Aspect.WEAPON, 8)
     .add(Aspect.METAL, 13));
     ThaumcraftApi.registerObjectTag(GSBlock.graveStone.blockID, 8, new AspectList().add(Aspect.SOUL, 1).add(Aspect.WEAPON, 4)
     .add(Aspect.GREED, 6).add(Aspect.METAL, 13));
     ThaumcraftApi.registerObjectTag(GSBlock.graveStone.blockID, 9, new AspectList().add(Aspect.SOUL, 1).add(Aspect.WEAPON, 10)
     .add(Aspect.GREED, 6));

     } catch (Exception e) {
     GraveStoneLogger.logError("Error during thaumcraft integration");
     e.printStackTrace();
     }
     }*/
}
