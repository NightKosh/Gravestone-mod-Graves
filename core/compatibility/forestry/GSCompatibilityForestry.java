package gravestone.core.compatibility.forestry;

import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.recipes.RecipeManagers;
import forestry.api.storage.BackpackManager;
import forestry.api.storage.EnumBackpackType;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSItem;
import gravestone.core.GSReciepes;
import gravestone.core.GSTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityForestry {

    public static boolean isInstalled = false;

    public static Item backpackItemT1;
    public static Item backpackItemT2;

    private GSCompatibilityForestry() {

    }

    // TODO villagers ID

    public static void addBackpack() {
        if (BackpackManager.backpackInterface != null) {
            if (GraveStoneConfig.enableForestryBackpacks) {
                String backpackT1Name = "backpack.undertaker.t1";
                backpackItemT1 = BackpackManager.backpackInterface.addBackpack(UndertakerBackpack.getInstance(), EnumBackpackType.T1);
                backpackItemT1.setCreativeTab(GSTabs.otherItemsTab);
                backpackItemT1.setUnlocalizedName(backpackT1Name);
                GSItem.registryExternalItems(backpackItemT1, "GSUndertakerBackpackT1");

                ItemStack backpackStackT1 = new ItemStack(backpackItemT1);
                GSReciepes.addForestryBackpack(backpackStackT1, GSItem.chisel);


                String backpackT2Name = "backpack.undertaker.t2";
                backpackItemT2 = BackpackManager.backpackInterface.addBackpack(UndertakerBackpack.getInstance(), EnumBackpackType.T2);
                backpackItemT2.setCreativeTab(GSTabs.otherItemsTab);
                backpackItemT2.setUnlocalizedName(backpackT2Name);
                GSItem.registryExternalItems(backpackItemT2, "GSUndertakerBackpackT2");

                Item itemSilk = GameRegistry.findItem("Forestry", "craftingMaterial");
                if (itemSilk == null) {
                    ItemStack silk = new ItemStack(itemSilk, 1);

                    ItemStack backpackStackT2 = new ItemStack(backpackItemT2);
                    RecipeManagers.carpenterManager.addRecipe(200, FluidRegistry.getFluidStack("water", 1000), null, backpackStackT2,
                            new Object[]{
                                    "sds", "sbs", "s",
                                    'd', Items.diamond, 's', silk, 'b', backpackItemT2
                            }
                    );
                }
            }
        }
    }
}
