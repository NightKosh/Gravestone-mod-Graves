package gravestone.core.compatibility;

import gravestone.core.GSRecipes;
import gravestone.core.logger.GSLogger;
import gravestone.block.GraveStoneHelper;
import gravestone.core.GSBlock;
import gravestone.core.GSEntity;
import gravestone.core.GSItem;
import net.minecraft.item.ItemStack;


/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityThaumcraft {

    private GSCompatibilityThaumcraft() {

    }

    public static void addReciepes() {
//        GSRecipes.addSkullCandleReciepes(ItemApi.getBlock("blockCandle", 0));
    }

    public static void addAspects() {
        try {
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.graveStone), new AspectList().add(Aspect.SOUL, 3)
//                    .add(Aspect.DEATH, 3).add(Aspect.UNDEAD, 3));
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.memorial), new AspectList().add(Aspect.SOUL, 10)
//                    .add(Aspect.DEATH, 10).add(Aspect.UNDEAD, 10));
//            // trap
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.trap), new int[]{0}, new AspectList().add(Aspect.FIRE, 1)
//                    .add(Aspect.EARTH, 2).add(Aspect.MECHANISM, 5).add(Aspect.DARKNESS, 5).add(Aspect.SOUL, 5).add(Aspect.MAGIC, 5));
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.trap), new int[]{1}, new AspectList().add(Aspect.EARTH, 2)
//                    .add(Aspect.MECHANISM, 5).add(Aspect.WEATHER, 5).add(Aspect.SOUL, 5).add(Aspect.MAGIC, 5));
//            // spawner
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.spawner), new int[]{0}, new AspectList().add(Aspect.MAGIC, 20)
//                    .add(Aspect.UNDEAD, 20).add(Aspect.SOUL, 20).add(Aspect.DARKNESS, 20).add(Aspect.ELDRITCH, 20));
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.spawner), new int[]{1}, new AspectList().add(Aspect.MAGIC, 20)
//                    .add(Aspect.UNDEAD, 20).add(Aspect.SOUL, 20).add(Aspect.ELDRITCH, 20));
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.spawner), new int[]{2}, new AspectList().add(Aspect.MAGIC, 20)
//                    .add(Aspect.UNDEAD, 20).add(Aspect.SOUL, 20).add(Aspect.ELDRITCH, 20));
//            // bone blocks
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.boneBlock), new int[]{0}, new AspectList().add(Aspect.DEATH, 9).add(Aspect.FLESH, 9));
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.boneBlock), new int[]{1}, new AspectList().add(Aspect.DEATH, 9).add(Aspect.FLESH, 9)
//                    .add(Aspect.UNDEAD, 9).add(Aspect.SOUL, 9));
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.boneBlock), new int[]{2}, new AspectList().add(Aspect.DEATH, 9).add(Aspect.FLESH, 9)
//                    .add(Aspect.TRAP, 2).add(Aspect.UNDEAD, 5));
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.boneBlock), new int[]{3}, new AspectList().add(Aspect.DEATH, 9).add(Aspect.FLESH, 9)
//                    .add(Aspect.UNDEAD, 9).add(Aspect.SOUL, 9).add(Aspect.TRAP, 2));
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.boneSlab), new AspectList().add(Aspect.DEATH, 4).add(Aspect.FLESH, 4));
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.boneStairs), new AspectList().add(Aspect.DEATH, 6).add(Aspect.FLESH, 6));
//            // haunted chest
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.hauntedChest), new AspectList().add(Aspect.SOUL, 5).add(Aspect.MAGIC, 5)
//                    .add(Aspect.TREE, 3).add(Aspect.VOID, 4));
//            // candle
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.candle), new int[]{0}, new AspectList().add(Aspect.LIGHT, 4));
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.skullCandle), new int[]{0}, new AspectList().add(Aspect.SOUL, 4).add(Aspect.DEATH, 4)
//                    .add(Aspect.UNDEAD, 4).add(Aspect.LIGHT, 4));
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.skullCandle), new int[]{1}, new AspectList().add(Aspect.SOUL, 4).add(Aspect.DEATH, 4)
//                    .add(Aspect.UNDEAD, 4).add(Aspect.LIGHT, 4).add(Aspect.POISON, 4));
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.skullCandle), new int[]{2}, new AspectList().add(Aspect.SOUL, 4).add(Aspect.DEATH, 4)
//                    .add(Aspect.FLESH, 4).add(Aspect.LIGHT, 4));
//
//            // items
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSItem.chisel), new AspectList().add(Aspect.TOOL, 2));
//            // corpses
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSItem.corpse), new int[]{0}, new AspectList().add(Aspect.DEATH, 2)
//                    .add(Aspect.MAN, 3).add(Aspect.AIR, 2)); // villager
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSItem.corpse), new int[]{1}, new AspectList().add(Aspect.DEATH, 2)
//                    .add(Aspect.BEAST, 3).add(Aspect.EARTH, 3)); // wolf
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSItem.corpse), new int[]{2}, new AspectList().add(Aspect.DEATH, 2)
//                    .add(Aspect.BEAST, 3).add(Aspect.ENTROPY, 3)); // cat
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSItem.corpse), new int[]{3}, new AspectList().add(Aspect.DEATH, 2)
//                    .add(Aspect.BEAST, 4).add(Aspect.AIR, 1).add(Aspect.EARTH, 1)); // horse
//
//            // entity
//            ThaumcraftApi.registerEntityTag(GSEntity.ZOMBIE_DOG_NAME, new AspectList().add(Aspect.BEAST, 3)
//                    .add(Aspect.UNDEAD, 3).add(Aspect.EARTH, 3));
//            ThaumcraftApi.registerEntityTag(GSEntity.ZOMBIE_CAT_NAME, new AspectList().add(Aspect.BEAST, 3)
//                    .add(Aspect.UNDEAD, 3).add(Aspect.ENTROPY, 3));
//            ThaumcraftApi.registerEntityTag(GSEntity.SKELETON_DOG_NAME, new AspectList().add(Aspect.BEAST, 2)
//                    .add(Aspect.UNDEAD, 4).add(Aspect.EARTH, 3));
//            ThaumcraftApi.registerEntityTag(GSEntity.SKELETON_CAT_NAME, new AspectList().add(Aspect.BEAST, 2)
//                    .add(Aspect.UNDEAD, 4).add(Aspect.ENTROPY, 3));
//            // Crawlers
//            ThaumcraftApi.registerEntityTag(GSEntity.SKULL_CRAWLER_NAME, new AspectList().add(Aspect.DEATH, 2).add(Aspect.UNDEAD, 2));
//            ThaumcraftApi.registerEntityTag(GSEntity.WITHER_SKULL_CRAWLER_NAME, new AspectList().add(Aspect.DEATH, 2)
//                    .add(Aspect.UNDEAD, 2).add(Aspect.DARKNESS, 2));
//            ThaumcraftApi.registerEntityTag(GSEntity.ZOMBIE_SKULL_CRAWLER_NAME, new AspectList().add(Aspect.DEATH, 2)
//                    .add(Aspect.UNDEAD, 2).add(Aspect.HUNGER, 2));
        } catch (Exception e) {
            GSLogger.logError("Error in thaumcraft integration");
            e.printStackTrace();
        }
    }

    public static void addSwords() {
//        GraveStoneHelper.swordsList.add(ItemApi.getItem("itemSwordThaumium", 0).getItem());
//        GraveStoneHelper.swordsList.add(ItemApi.getItem("itemSwordElemental", 0).getItem());
    }
}
