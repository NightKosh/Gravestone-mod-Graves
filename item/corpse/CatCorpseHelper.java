package gravestone.item.corpse;

import gravestone.ModGraveStone;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatCorpseHelper extends CorpseHelper {

    private CatCorpseHelper() {
    }

    public static List<ItemStack> getDefaultCorpses(Item item, int corpseType) {
        List<ItemStack> list = new ArrayList<ItemStack>();
        list.add(getDefaultCatCorpse(item, corpseType, 0)); // Ocelot
        list.add(getDefaultCatCorpse(item, corpseType, 1)); // Black
        list.add(getDefaultCatCorpse(item, corpseType, 2)); // Red
        list.add(getDefaultCatCorpse(item, corpseType, 3)); // Siamese
        return list;
    }

    private static ItemStack getDefaultCatCorpse(Item item, int corpseType, int type) {
        ItemStack corpse = new ItemStack(item, 1, corpseType);
        NBTTagCompound nbtTag = new NBTTagCompound();

        nbtTag.setByte("CatType", (byte) type);

        corpse.setTagCompound(nbtTag);
        return corpse;
    }

    public static void setNbt(EntityOcelot cat, NBTTagCompound nbt) {
        setName(cat, nbt);
        nbt.setByte("CatType", (byte) cat.getTameSkin());
    }

    public static byte getCatType(NBTTagCompound nbtTag) {
        return nbtTag.getByte("CatType");
    }

    public static void spawnCat(World world, int x, int y, int z, NBTTagCompound nbtTag, EntityPlayer player) {
        EntityOcelot cat = new EntityOcelot(world);
        setMobName(cat, nbtTag);

        cat.setTamed(true);
        cat.setTameSkin(getCatType(nbtTag));
        cat.func_152115_b(player.getUniqueID().toString());
        world.setEntityState(cat, (byte) 7);
        spawnMob(cat, world, x, y, z);
    }

    public static void addInfo(List list, NBTTagCompound nbtTag) {
        addNameInfo(list, nbtTag);
        if (hasType(nbtTag)) {
            list.add(getType(nbtTag));
        }
    }

    private static boolean hasType(NBTTagCompound nbtTag) {
        return nbtTag.hasKey("CatType");
    }

    private static String getType(NBTTagCompound nbtTag) {
        return ModGraveStone.proxy.getLocalizedString("item.corpse.cat_type") + " " +
                ModGraveStone.proxy.getLocalizedString(getCatType(nbtTag.getByte("CatType")));
    }

    private static String getCatType(int type) {
        switch (type) {
            case 0:
                return "item.corpse.cat_type.ocelot";
            case 1:
                return "item.corpse.cat_type.black";
            case 2:
                return "item.corpse.cat_type.red";
            case 3:
                return "item.corpse.cat_type.siamese";
            default:
                return "item.corpse.cat_type.unknown";
        }
    }
}
