package gravestone.structures.village;

import cpw.mods.fml.common.registry.VillagerRegistry;
import gravestone.core.GSBlock;
import gravestone.core.GSItem;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

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
        recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(GSItem.chisel, 1, 0)));
        recipeList.add(new MerchantRecipe(new ItemStack(GSItem.chisel, 1, 0), new ItemStack(Items.gold_ingot, 1)));
        // memorials
        recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 15), getTradeStack(GSBlock.memorial, (byte) 0)));
        recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 15), getTradeStack(GSBlock.memorial, (byte) 1)));
        recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 25), getTradeStack(GSBlock.memorial, (byte) 2)));
        recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 25), getTradeStack(GSBlock.memorial, (byte) 3)));
        recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 25), getTradeStack(GSBlock.memorial, (byte) 4)));
        recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 20), getTradeStack(GSBlock.memorial, (byte) 5)));
        recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 20), getTradeStack(GSBlock.memorial, (byte) 6)));
    }

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int size) {
        return new StructureVillagePieces.PieceWeight(ComponentGSVillageUndertaker.class, 3, MathHelper.getRandomIntegerInRange(random, 0, 1));
    }

    @Override
    public Class getComponentClass() {
        return ComponentGSVillageUndertaker.class;
    }

    @Override
    public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
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
