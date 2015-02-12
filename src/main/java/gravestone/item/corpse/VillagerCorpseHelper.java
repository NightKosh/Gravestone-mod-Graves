package gravestone.item.corpse;

import gravestone.config.GSConfig;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import gravestone.ModGraveStone;
import gravestone.core.compatibility.forestry.GSCompatibilityForestry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class VillagerCorpseHelper extends CorpseHelper {

    private VillagerCorpseHelper() {
    }

    public static List<ItemStack> getDefaultCorpses(Item item, int corpseType) {
        List<ItemStack> list = new ArrayList<ItemStack>();

        list.add(getDefaultVillagerCorpse(item, corpseType, 0)); // Farmer
        list.add(getDefaultVillagerCorpse(item, corpseType, 1)); // Librarian
        list.add(getDefaultVillagerCorpse(item, corpseType, 2)); // Priest
        list.add(getDefaultVillagerCorpse(item, corpseType, 3)); // Smith
        list.add(getDefaultVillagerCorpse(item, corpseType, 4)); // Butcher

        Collection<Integer> villagerIds = VillagerRegistry.getRegisteredVillagers();
        Iterator<Integer> it = villagerIds.iterator();
        while (it.hasNext()) {
            list.add(getDefaultVillagerCorpse(item, corpseType, it.next()));
        }

        return list;
    }

    private static ItemStack getDefaultVillagerCorpse(Item item, int corpseType, int type) {
        ItemStack corpse = new ItemStack(item, 1, corpseType);

        NBTTagCompound nbtTag = new NBTTagCompound();
        nbtTag.setInteger("VillagerType", type);

        corpse.setTagCompound(nbtTag);
        return corpse;
    }

    public static void setNbt(EntityVillager villager, NBTTagCompound nbt) {
        setName(villager, nbt);
        nbt.setInteger("VillagerType", villager.getProfession());

        MerchantRecipeList recipes = villager.getRecipes(null);
        if (recipes != null) {
            MerchantRecipe recipe;
            NBTTagCompound recipeTag;
            for (int i = 0; i < recipes.size(); i++) {
                recipe = (MerchantRecipe) recipes.get(i);
                recipeTag = recipe.writeToTags();
                recipeTag.setInteger("uses", 0);
                recipeTag.setInteger("maxUses", 7);
                recipe.readFromTags(recipeTag);
            }
            nbt.setTag("Offers", recipes.getRecipiesAsTags());
        }
    }

    public static void spawnVillager(World world, int x, int y, int z, NBTTagCompound nbtTag) {
        EntityVillager villager = new EntityVillager(world, getVillagerType(nbtTag));
        setMobName(villager, nbtTag);

        NBTTagCompound nbt = new NBTTagCompound();
        villager.writeEntityToNBT(nbt);
        if (nbtTag.hasKey("Offers")) {
            nbt.setTag("Offers", nbtTag.getCompoundTag("Offers"));
        }
        villager.readEntityFromNBT(nbt);

        spawnMob(villager, world, x, y, z);
    }

    public static int getVillagerType(NBTTagCompound nbtTag) {
        return nbtTag.getInteger("VillagerType");
    }

    public static void addInfo(List list, NBTTagCompound nbtTag) {
        addNameInfo(list, nbtTag);
        if (hasType(nbtTag)) {
            list.add(getType(nbtTag));
        }
        if (hasTrades(nbtTag)) {
            addTrades(list, nbtTag);
        }
    }

    private static boolean hasType(NBTTagCompound nbtTag) {
        return nbtTag.hasKey("VillagerType");
    }

    private static String getType(NBTTagCompound nbtTag) {
        return ModGraveStone.proxy.getLocalizedString("item.corpse.villager_type") + " " +
                ModGraveStone.proxy.getLocalizedString(getVillagerProfession(getVillagerType(nbtTag)));
    }

    private static String getVillagerProfession(int type) {
        switch (type) {
            case 0:
                return "item.corpse.villager_type.farmer";
            case 1:
                return "item.corpse.villager_type.librarian";
            case 2:
                return "item.corpse.villager_type.priest";
            case 3:
                return "item.corpse.villager_type.smith";
            case 4:
                return "item.corpse.villager_type.butcher";
            default:
                return getNotVanillaVillagerProfession(type);
        }
    }

    private static String getNotVanillaVillagerProfession(int type) {
        if (type == GSConfig.undertakerId) {
            return "item.corpse.villager_type.undertaker";
        } else if (type == GSCompatibilityForestry.getApicultureVillagerID()) {
            return "item.corpse.villager_type.beekeeper";
        } else if (type == GSCompatibilityForestry.getArboricultureVillagerID()) {
            return "item.corpse.villager_type.lumberjack";
        } else if (type == 10) {
            return "item.corpse.villager_type.brewer";
        } else if (type == 206) {
            return "item.corpse.villager_type.thaumaturge";
        }
        return "item.corpse.villager_type.unknown";
    }

    private static boolean hasTrades(NBTTagCompound nbtTag) {
        return nbtTag.hasKey("Offers");
    }

    private static void addTrades(List list, NBTTagCompound nbtTag) {
        MerchantRecipeList trades = new MerchantRecipeList(nbtTag.getCompoundTag("Offers"));
        for (int i = 0; i < trades.size(); i++) {
            MerchantRecipe recipe = (MerchantRecipe) trades.get(i);
            StringBuilder str = new StringBuilder();
            str.append(recipe.getItemToBuy().stackSize).append(" ").append(recipe.getItemToBuy().getDisplayName());
            if (recipe.getSecondItemToBuy() != null) {
                str.append(" + ").append(recipe.getSecondItemToBuy().stackSize).append(" ").append(recipe.getItemToBuy().getDisplayName());
            }
            str.append(" -> ").append(recipe.getItemToSell().stackSize).append(" ").append(recipe.getItemToSell().getDisplayName());
            list.add(str.toString());
        }
    }
}
