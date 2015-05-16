package gravestone.core.compatibility.forestry;

import forestry.api.core.ForestryAPI;
import forestry.api.recipes.RecipeManagers;
import forestry.api.storage.BackpackManager;
import forestry.api.storage.EnumBackpackType;
import gravestone.config.GSConfig;
import gravestone.core.GSItem;
import gravestone.core.GSRecipes;
import gravestone.core.GSTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

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

    public static final int DEFAULT_BEEKEEPER_ID = 80;
    public static final int DEFAULT_LUMBERJACK_ID = 81;

    private GSCompatibilityForestry() {

    }

    public static int getApicultureVillagerID() {
        if (isInstalled && ForestryAPI.forestryConstants != null) {
            return ForestryAPI.forestryConstants.getApicultureVillagerID();
        } else {
            return DEFAULT_BEEKEEPER_ID;
        }
    }

    public static int getArboricultureVillagerID() {
        if (isInstalled && ForestryAPI.forestryConstants != null) {
            return ForestryAPI.forestryConstants.getArboricultureVillagerID();
        } else {
            return DEFAULT_LUMBERJACK_ID;
        }
    }

    public static void addBackpack() {
        if (isInstalled && BackpackManager.backpackInterface != null) {
            if (GSConfig.enableForestryBackpacks) {
                String backpackT1Name = "backpack.undertaker.t1";
                backpackItemT1 = BackpackManager.backpackInterface.addBackpack(UndertakerBackpack.getInstance(), EnumBackpackType.T1);
                backpackItemT1.setCreativeTab(GSTabs.otherItemsTab);
                backpackItemT1.setUnlocalizedName(backpackT1Name);
                GSItem.registryExternalItems(backpackItemT1, "GSUndertakerBackpackT1");

                ItemStack backpackStackT1 = new ItemStack(backpackItemT1);
                GSRecipes.addForestryBackpack(backpackStackT1, GSItem.chisel);


                String backpackT2Name = "backpack.undertaker.t2";
                backpackItemT2 = BackpackManager.backpackInterface.addBackpack(UndertakerBackpack.getInstance(), EnumBackpackType.T2);
                backpackItemT2.setCreativeTab(GSTabs.otherItemsTab);
                backpackItemT2.setUnlocalizedName(backpackT2Name);
                GSItem.registryExternalItems(backpackItemT2, "GSUndertakerBackpackT2");

                Item itemSilk = GameRegistry.findItem("Forestry", "craftingMaterial");
                if (itemSilk != null) {
                    ItemStack wovenSilk = new ItemStack(itemSilk, 1, 3);

                    ItemStack backpackStackT2 = new ItemStack(backpackItemT2);
                    RecipeManagers.carpenterManager.addRecipe(200, FluidRegistry.getFluidStack("water", 1000), null, backpackStackT2,
                            new Object[]{
                                    "sds", "sbs", "sss",
                                    'd', Items.diamond, 'b', backpackItemT1, 's', wovenSilk
                            }
                    );
                }
            }
        }
    }
}