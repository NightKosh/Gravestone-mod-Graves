package nightkosh.gravestone.core.compatibility;

//import thaumcraft.api.ThaumcraftApi;
//import thaumcraft.api.aspects.Aspect;
//import thaumcraft.api.aspects.AspectList;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityThaumcraft implements ICompatibility {

    public static final String MOD_ID = "Thaumcraft";

    protected CompatibilityThaumcraft() {
        if (isModLoaded(MOD_ID)) {
//            ThaumcraftApi.registerObjectTag(new ItemStack(GSBlock.graveStone), new AspectList().add(Aspect.SOUL, 3)
//                    .add(Aspect.DEATH, 3).add(Aspect.UNDEAD, 3));
//
//            if (Config.addThaumcraftSwordsAsGravestones) {
//                GraveStoneAPI.graveStone.addSwordToSwordsList(GameRegistry.findItem(MOD_ID, "thaumium_sword"));
//                GraveStoneAPI.graveStone.addSwordToSwordsList(GameRegistry.findItem(MOD_ID, "elemental_sword"));
//                GraveStoneAPI.graveStone.addSwordToSwordsList(GameRegistry.findItem(MOD_ID, "void_sword"));
//            }
        }
    }
}
