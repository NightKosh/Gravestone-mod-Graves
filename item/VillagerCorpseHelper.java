package gravestone.item;

import gravestone.ModGraveStone;
import java.util.List;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class VillagerCorpseHelper extends CorpseHelper {

    private VillagerCorpseHelper() {
    }

    public static void setNbt(EntityVillager villager, NBTTagCompound nbt) {
        setName(villager, nbt);
        nbt.setInteger("VillagerType", villager.getProfession());
        nbt.setCompoundTag("Offers", villager.getRecipes(null).getRecipiesAsTags());
    }

    public static void spawnVillager(World world, int x, int y, int z, NBTTagCompound nbtTag) {
        EntityVillager villager = new EntityVillager(world, getVillagerType(nbtTag));
        setMobName(villager, nbtTag);

        NBTTagCompound nbt = new NBTTagCompound();
        villager.writeEntityToNBT(nbt);
        if (nbtTag.hasKey("Offers")) {
            nbt.setCompoundTag("Offers", nbtTag.getCompoundTag("Offers"));
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
        return ModGraveStone.proxy.getLocalizedString("item.corpse.villager_type") + " " + getVillagerType(nbtTag);
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
