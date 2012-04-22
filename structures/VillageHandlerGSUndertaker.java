package GraveStone.structures;

import cpw.mods.fml.common.registry.VillagerRegistry;
import java.util.List;
import java.util.Random;
import GraveStone.mod_GraveStone;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureVillagePieceWeight;

public class VillageHandlerGSUndertaker implements VillagerRegistry.IVillageCreationHandler, VillagerRegistry.IVillageTradeHandler {

    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
        recipeList.add(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(mod_GraveStone.chisel, 1, 0)));
        recipeList.add(new MerchantRecipe(
                new ItemStack(mod_GraveStone.chisel, 1, 0),
                new ItemStack(Item.emerald, 1)));
        
        ItemStack stack = new ItemStack(mod_GraveStone.graveStone, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte("GraveType", (byte) 0);
        stack.setTagCompound(nbt);
        recipeList.add(new MerchantRecipe( new ItemStack(Item.emerald, 3), stack));
        /*
        stack = new ItemStack(mod_GraveStone.graveStone, 1, 0);
        nbt = new NBTTagCompound();
        nbt.setByte("GraveType", (byte) 1);
        stack.setTagCompound(nbt);
        recipeList.add(new MerchantRecipe( new ItemStack(Item.emerald, 3), stack));
        
        stack = new ItemStack(mod_GraveStone.graveStone, 1, 0);
        nbt = new NBTTagCompound();
        nbt.setByte("GraveType", (byte) 2);
        stack.setTagCompound(nbt);
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), stack));
        */
        
        stack = new ItemStack(mod_GraveStone.memorial, 1, 0);
        nbt = new NBTTagCompound();
        nbt.setByte("GraveType", (byte) 0);
        stack.setTagCompound(nbt);
        recipeList.add(new MerchantRecipe( new ItemStack(Item.emerald, 15), stack));

    }

    public StructureVillagePieceWeight getVillagePieceWeight(Random random, int size) {
        return new StructureVillagePieceWeight(ComponentGSVillageUndertaker.class, 12, MathHelper.getRandomIntegerInRange(random, 0, 1));
    }

    public Class getComponentClass() {
        return ComponentGSVillageUndertaker.class;
    }

    public Object buildComponent(StructureVillagePieceWeight villagePiece, ComponentVillageStartPiece startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
        return ComponentGSVillageUndertaker.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
    }
}
