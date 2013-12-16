/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravestone.core.compatibility;

import cpw.mods.fml.common.Loader;
import gravestone.GraveStoneLogger;
import gravestone.core.GSBlock;
import gravestone.core.GSBiomes;
import gravestone.core.GSEntity;
import gravestone.core.GSItem;
import gravestone.core.GSMobSpawn;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

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
            GSMobSpawn.addMoCreaturesMobs();
        }

        if (Loader.isModLoaded("TwilightForest")) {
            isTwilightForestInstalled = true;
            GSMobSpawn.addTwilightForestMobs();
        }

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

        // adding Thaumcraft aspects
        if (Loader.isModLoaded("Thaumcraft")) {
            initThaumCraft();
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

    private void initThaumCraft() {
        try {
            ThaumcraftApi.registerObjectTag(GSBlock.graveStone.blockID, -1, new AspectList().add(Aspect.SOUL, 5)
                    .add(Aspect.DEATH, 5).add(Aspect.UNDEAD, 5));
            ThaumcraftApi.registerObjectTag(GSBlock.memorial.blockID, -1, new AspectList().add(Aspect.SOUL, 20)
                    .add(Aspect.DEATH, 20).add(Aspect.UNDEAD, 20));
            ThaumcraftApi.registerObjectTag(GSBlock.trap.blockID, 0, new AspectList().add(Aspect.FIRE, 1).add(Aspect.STONE, 2)
                    .add(Aspect.MECHANISM, 5).add(Aspect.DARKNESS, 5).add(Aspect.SOUL, 5).add(Aspect.MAGIC, 5));
            ThaumcraftApi.registerObjectTag(GSBlock.witherSpawner.blockID, 0, new AspectList().add(Aspect.MAGIC, 40)
                    .add(Aspect.UNDEAD, 40).add(Aspect.SOUL, 40).add(Aspect.DARKNESS, 40).add(Aspect.ELDRITCH, 40));
            ThaumcraftApi.registerObjectTag(GSItem.chisel.itemID, 0, new AspectList().add(Aspect.TOOL, 2));

            ThaumcraftApi.registerEntityTag(GSEntity.ZOMBIE_DOG_NAME, new AspectList().add(Aspect.BEAST, 2)
                    .add(Aspect.UNDEAD, 2).add(Aspect.EARTH, 2));
            ThaumcraftApi.registerEntityTag(GSEntity.ZOMBIE_CAT_NAME, new AspectList().add(Aspect.BEAST, 2)
                    .add(Aspect.UNDEAD, 2).add(Aspect.ENTROPY, 1));
            ThaumcraftApi.registerEntityTag(GSEntity.SKEKETON_DOG_NAME, new AspectList().add(Aspect.BEAST, 1)
                    .add(Aspect.UNDEAD, 3).add(Aspect.EARTH, 1));
            ThaumcraftApi.registerEntityTag(GSEntity.SKEKETON_CAT_NAME, new AspectList().add(Aspect.BEAST, 1)
                    .add(Aspect.UNDEAD, 3).add(Aspect.ENTROPY, 1));
        } catch (Exception e) {
            GraveStoneLogger.logError("Error in thaumcraft integration");
            e.printStackTrace();
        }
    }
}
