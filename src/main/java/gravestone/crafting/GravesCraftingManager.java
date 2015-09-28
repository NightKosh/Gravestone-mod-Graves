package gravestone.crafting;

import gravestone.block.enums.EnumGraveMaterial;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumMemorials;
import gravestone.config.GSConfig;
import gravestone.core.GSBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GravesCraftingManager {


    private static final GravesCraftingManager instance = new GravesCraftingManager();
    private static final List<GravestoneRecipe> recipes = new ArrayList<GravestoneRecipe>();//TODO set recipes count

    private GravesCraftingManager() {
        addGravesRecipesForAllMaterials(EnumGraves.EnumGraveType.VERTICAL_PLATE);
        addGravesRecipesForAllMaterials(EnumGraves.EnumGraveType.CROSS);
        addGravesRecipesForAllMaterials(EnumGraves.EnumGraveType.HORIZONTAL_PLATE);
        addGravesRecipesForAllMaterials(EnumGraves.EnumGraveType.DOG_STATUE);
        addGravesRecipesForAllMaterials(EnumGraves.EnumGraveType.CAT_STATUE);
        addGravesRecipesForAllMaterials(EnumGraves.EnumGraveType.HORSE_STATUE);
        //TODO EnumGraves.EnumGraveType.SWORD

        // Memorials
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.CROSS, 6);
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.OBELISK, 6);
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.STEVE_STATUE, 3);
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.VILLAGER_STATUE, 3);
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.ANGEL_STATUE, 3);
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.DOG_STATUE, 2);
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.CAT_STATUE, 2);
        // CREEPER_STATUE
        if (GSConfig.enableCreeperStatuesRecipes) {
            addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.CREEPER_STATUE, 3);
        }
    }

    private static void addGravesRecipesForAllMaterials(EnumGraves.EnumGraveType graveType) {
        for (int i = 0; i <= EnumGraveMaterial.ICE.ordinal(); i++) {
            recipes.add(new GravestoneRecipe(true, graveType, EnumGraveMaterial.values()[i],
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(EnumGraveMaterial.values()[i].getBlock(), 1))),
                    getStackWithNTB(GSBlock.graveStone, EnumGraves.getByTypeAndMaterial(graveType, EnumGraveMaterial.values()[i]).ordinal())));
        }
    }

    private static void addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType memorialType, int amountOfBlocks) {
        for (int i = 0; i <= EnumGraveMaterial.ICE.ordinal(); i++) {
            recipes.add(new GravestoneRecipe(true, memorialType, EnumGraveMaterial.values()[i],
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(EnumGraveMaterial.values()[i].getBlock(), amountOfBlocks))),
                    getStackWithNTB(GSBlock.memorial, EnumMemorials.getByTypeAndMaterial(memorialType, EnumGraveMaterial.values()[i]).ordinal())));
        }
    }

    public List<ItemStack> findMatchingRecipe(boolean isGravestone, EnumGraves.EnumGraveType graveType, EnumGraveMaterial material, boolean isEnchanted, boolean isMossy) {
        for (GravestoneRecipe recipe : recipes) {
            if (recipe.match(isGravestone, graveType, material, isEnchanted, isMossy)) {
                return recipe.getRequiredItems(isEnchanted, isMossy);
            }
        }
        return null;
    }

    public ItemStack findMatchingRecipe(List<ItemStack> requiredItems, boolean isGravestone, EnumGraves.EnumGraveType graveType, EnumGraveMaterial material, boolean isEnchanted, boolean isMossy) {
        for (GravestoneRecipe recipe : recipes) {
            if (recipe.match(isGravestone, graveType, material, isEnchanted, isMossy, requiredItems)) {
                return recipe.getResultItem(requiredItems);
            }
        }
        return null;
    }

    public List<GravestoneRecipe> getRecipes() {
        return recipes;
    }

    private static ItemStack getStackWithNTB(Block block, int graveType) {
        ItemStack stack = new ItemStack(block, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Type", graveType);
        stack.setTagCompound(nbt);
        return stack;
    }

    public static GravesCraftingManager getInstance() {
        return instance;
    }
}
