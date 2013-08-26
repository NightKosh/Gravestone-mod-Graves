package GraveStone.structures.village;

import cpw.mods.fml.common.registry.VillagerRegistry;
import java.util.List;
import java.util.Random;
import GraveStone.ModGraveStone;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureVillagePieceWeight;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class VillageHandlerGSUndertaker implements VillagerRegistry.IVillageCreationHandler, VillagerRegistry.IVillageTradeHandler {

    @Override
    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
        // chisel
        recipeList.add(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(ModGraveStone.chisel, 1, 0)));
        recipeList.add(new MerchantRecipe(
                new ItemStack(ModGraveStone.chisel, 1, 0),
                new ItemStack(Item.ingotGold, 1)));
        // graves
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), getTradeStack(ModGraveStone.graveStone, (byte) 0)));
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), getTradeStack(ModGraveStone.graveStone, (byte) 1)));
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), getTradeStack(ModGraveStone.graveStone, (byte) 2)));
        // pet graves
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 5), getTradeStack(ModGraveStone.graveStone, (byte) 3)));
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 5), getTradeStack(ModGraveStone.graveStone, (byte) 4)));
        // memorials
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 15), getTradeStack(ModGraveStone.memorial, (byte) 0)));
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 15), getTradeStack(ModGraveStone.memorial, (byte) 1)));
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 25), getTradeStack(ModGraveStone.memorial, (byte) 2)));
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 25), getTradeStack(ModGraveStone.memorial, (byte) 3)));
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 25), getTradeStack(ModGraveStone.memorial, (byte) 4)));
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 20), getTradeStack(ModGraveStone.memorial, (byte) 5)));
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 20), getTradeStack(ModGraveStone.memorial, (byte) 6)));
    }

    @Override
    public StructureVillagePieceWeight getVillagePieceWeight(Random random, int size) {
        return new StructureVillagePieceWeight(ComponentGSVillageUndertaker.class, 3, MathHelper.getRandomIntegerInRange(random, 0, 1));
    }

    @Override
    public Class getComponentClass() {
        return ComponentGSVillageUndertaker.class;
    }

    @Override
    public Object buildComponent(StructureVillagePieceWeight villagePiece, ComponentVillageStartPiece startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
        return ComponentGSVillageUndertaker.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
    }

    private ItemStack getTradeStack(Block block, byte graveType) {
        ItemStack stack = new ItemStack(block, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte("GraveType", graveType);
        stack.setTagCompound(nbt);
        return stack;
    }
}
