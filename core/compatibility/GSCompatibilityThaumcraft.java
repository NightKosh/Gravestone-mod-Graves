
package gravestone.core.compatibility;

import gravestone.GraveStoneLogger;
import gravestone.core.GSBlock;
import gravestone.core.GSEntity;
import gravestone.core.GSItem;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityThaumcraft {
    
    private GSCompatibilityThaumcraft(){
        
    }

    public static void addAspects() {
        try {
            ThaumcraftApi.registerObjectTag(GSBlock.graveStone.blockID, -1, new AspectList().add(Aspect.SOUL, 3)
                    .add(Aspect.DEATH, 3).add(Aspect.UNDEAD, 3));
            ThaumcraftApi.registerObjectTag(GSBlock.memorial.blockID, -1, new AspectList().add(Aspect.SOUL, 10)
                    .add(Aspect.DEATH, 10).add(Aspect.UNDEAD, 10));
            // trap
            ThaumcraftApi.registerObjectTag(GSBlock.trap.blockID, 0, new AspectList().add(Aspect.FIRE, 1).add(Aspect.STONE, 2)
                    .add(Aspect.MECHANISM, 5).add(Aspect.DARKNESS, 5).add(Aspect.SOUL, 5).add(Aspect.MAGIC, 5));
            ThaumcraftApi.registerObjectTag(GSBlock.trap.blockID, 1, new AspectList().add(Aspect.STONE, 2).add(Aspect.MECHANISM, 5)
                    .add(Aspect.WEATHER, 5).add(Aspect.SOUL, 5).add(Aspect.MAGIC, 5));
            // spawner
            ThaumcraftApi.registerObjectTag(GSBlock.witherSpawner.blockID, 0, new AspectList().add(Aspect.MAGIC, 20)
                    .add(Aspect.UNDEAD, 20).add(Aspect.SOUL, 20).add(Aspect.DARKNESS, 20).add(Aspect.ELDRITCH, 20));
            // bone blocks
            ThaumcraftApi.registerObjectTag(GSBlock.boneBlock.blockID, 0, new AspectList().add(Aspect.DEATH, 9).add(Aspect.FLESH, 9));
            ThaumcraftApi.registerObjectTag(GSBlock.boneBlock.blockID, 1, new AspectList().add(Aspect.DEATH, 9).add(Aspect.FLESH, 9)
                    .add(Aspect.UNDEAD, 9).add(Aspect.SOUL, 9));
            ThaumcraftApi.registerObjectTag(GSBlock.boneSlab.blockID, -1, new AspectList().add(Aspect.DEATH, 4).add(Aspect.FLESH, 4));
            ThaumcraftApi.registerObjectTag(GSBlock.boneStairs.blockID, -1, new AspectList().add(Aspect.DEATH, 6).add(Aspect.FLESH, 6));
            // haunted chest
            ThaumcraftApi.registerObjectTag(GSBlock.hauntedChest.blockID, -1, new AspectList().add(Aspect.SOUL, 5).add(Aspect.MAGIC, 5)
                    .add(Aspect.TREE, 3).add(Aspect.VOID, 4));
            // skull candle
            ThaumcraftApi.registerObjectTag(GSBlock.skullCandle.blockID, 0, new AspectList().add(Aspect.SOUL, 4).add(Aspect.DEATH, 4)
                    .add(Aspect.UNDEAD, 4).add(Aspect.LIGHT, 4));
            ThaumcraftApi.registerObjectTag(GSBlock.skullCandle.blockID, 1, new AspectList().add(Aspect.SOUL, 4).add(Aspect.DEATH, 4)
                    .add(Aspect.UNDEAD, 4).add(Aspect.LIGHT, 4).add(Aspect.POISON, 4));
            ThaumcraftApi.registerObjectTag(GSBlock.skullCandle.blockID, 2, new AspectList().add(Aspect.SOUL, 4).add(Aspect.DEATH, 4)
                    .add(Aspect.FLESH, 4).add(Aspect.LIGHT, 4));
            
            // items
            ThaumcraftApi.registerObjectTag(GSItem.chisel.itemID, 0, new AspectList().add(Aspect.TOOL, 2));
            
            // entity
            ThaumcraftApi.registerEntityTag(GSEntity.ZOMBIE_DOG_NAME, new AspectList().add(Aspect.BEAST, 2)
                    .add(Aspect.UNDEAD, 2).add(Aspect.EARTH, 2));
            ThaumcraftApi.registerEntityTag(GSEntity.ZOMBIE_CAT_NAME, new AspectList().add(Aspect.BEAST, 2)
                    .add(Aspect.UNDEAD, 2).add(Aspect.ENTROPY, 1));
            ThaumcraftApi.registerEntityTag(GSEntity.SKEKETON_DOG_NAME, new AspectList().add(Aspect.BEAST, 1)
                    .add(Aspect.UNDEAD, 3).add(Aspect.EARTH, 1));
            ThaumcraftApi.registerEntityTag(GSEntity.SKEKETON_CAT_NAME, new AspectList().add(Aspect.BEAST, 1)
                    .add(Aspect.UNDEAD, 3).add(Aspect.ENTROPY, 1));
            // Crawlers
            ThaumcraftApi.registerEntityTag(GSEntity.SKULL_CRAWLER_NAME, new AspectList().add(Aspect.DEATH, 2).add(Aspect.UNDEAD, 2));
            ThaumcraftApi.registerEntityTag(GSEntity.WITHER_SKULL_CRAWLER_NAME, new AspectList().add(Aspect.DEATH, 2)
                    .add(Aspect.UNDEAD, 2).add(Aspect.DARKNESS, 2));
            ThaumcraftApi.registerEntityTag(GSEntity.ZOMBIE_SKULL_CRAWLER_NAME, new AspectList().add(Aspect.DEATH, 2).add(Aspect.UNDEAD, 2));
        } catch (Exception e) {
            GraveStoneLogger.logError("Error in thaumcraft integration");
            e.printStackTrace();
        }
    }

}
