package gravestone.item.corpse;

import gravestone.ModGraveStone;
import gravestone.core.compatibility.GSCompatibilitySophisticatedWolves;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sophisticated_wolves.api.ISophisticatedWolf;
import sophisticated_wolves.api.SophisticatedWolvesAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DogCorpseHelper extends CorpseHelper {

    private DogCorpseHelper() {
    }

    public static List<ItemStack> getDefaultCorpses(Item item, int type) {
        List<ItemStack> list = new ArrayList<ItemStack>();

        ItemStack corpse = new ItemStack(item, 1, type);
        NBTTagCompound nbtTag = new NBTTagCompound();

        nbtTag.setByte("Collar", (byte) 14);

        corpse.setTagCompound(nbtTag);

        list.add(corpse);
        return list;
    }

    public static void setNbt(EntityWolf dog, NBTTagCompound nbt) {
        setName(dog, nbt);
        nbt.setByte("Collar", (byte) dog.getCollarColor());

        if (GSCompatibilitySophisticatedWolves.isInstalled() && dog instanceof ISophisticatedWolf) {
            nbt.setInteger("Species", ((ISophisticatedWolf) dog).getSpecies());
        }
    }

    public static void spawnDog(World world, int x, int y, int z, NBTTagCompound nbtTag, EntityPlayer player) {
        EntityWolf wolf;
        if (GSCompatibilitySophisticatedWolves.isInstalled() && nbtTag.hasKey("Species")) {
            wolf = SophisticatedWolvesAPI.getSophisticatedWolf(world);
            ((ISophisticatedWolf) wolf).updateSpecies(nbtTag.getInteger("Species"));
        } else {
            wolf = new EntityWolf(world);
        }
        setMobName(wolf, nbtTag);
        wolf.setTamed(true);
        wolf.setHealth(20);
        wolf.func_152115_b(player.getUniqueID().toString());
        wolf.setCollarColor(nbtTag.getByte("Collar"));
        world.setEntityState(wolf, (byte) 7);
        spawnMob(wolf, world, x, y, z);
    }

    public static void addInfo(List list, NBTTagCompound nbtTag) {
        addNameInfo(list, nbtTag);
        if (hasCollar(nbtTag)) {
            list.add(getCollarStr(nbtTag));
        }
        if (GSCompatibilitySophisticatedWolves.isInstalled() && nbtTag.hasKey("Species")) {
            list.add(getSpeciesStr(nbtTag));
        }
    }

    private static boolean hasCollar(NBTTagCompound nbtTag) {
        return nbtTag.hasKey("Collar");
    }

    private static String getCollarStr(NBTTagCompound nbtTag) {
        return ModGraveStone.proxy.getLocalizedString("item.corpse.collar") + " " +
                ModGraveStone.proxy.getLocalizedString(getCollar(nbtTag.getByte("Collar")));
    }

    private static String getSpeciesStr(NBTTagCompound nbtTag) {
        return ModGraveStone.proxy.getLocalizedString("item.corpse.dog_type") + " " + nbtTag.getInteger("Species");
    }

    private static String getCollar(int type) {
        switch (type) {
            case 0:
                return "item.corpse.collar.white";
            case 1:
                return "item.corpse.collar.orange";
            case 2:
                return "item.corpse.collar.purple";
            case 3:
                return "item.corpse.collar.azure";
            case 4:
                return "item.corpse.collar.yellow";
            case 5:
                return "item.corpse.collar.lime";
            case 6:
                return "item.corpse.collar.pink";
            case 7:
                return "item.corpse.collar.grey";
            case 8:
                return "item.corpse.collar.light_grey";
            case 9:
                return "item.corpse.collar.turquoise";//бирюзовый
            case 10:
                return "item.corpse.collar.violet";
            case 11:
                return "item.corpse.collar.blue";
            case 12:
                return "item.corpse.collar.brown";
            case 13:
                return "item.corpse.collar.green";
            case 14:
                return "item.corpse.collar.red";
            case 15:
                return "item.corpse.collar.black";
            default:
                return "item.corpse.collar.unknown";
        }
    }
}
