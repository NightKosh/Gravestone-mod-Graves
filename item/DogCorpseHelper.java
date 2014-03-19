package gravestone.item;

import gravestone.ModGraveStone;
import java.util.List;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DogCorpseHelper extends CorpseHelper {

    private DogCorpseHelper() {
    }

    public static void setNbt(EntityWolf dog, NBTTagCompound nbt) {
        nbt.setByte("Collar", (byte) dog.getCollarColor());
    }

    public static void spawnDog(World world, int x, int y, int z, NBTTagCompound nbtTag, EntityPlayer player) {
        EntityWolf wolf = new EntityWolf(world);
        setMobName(wolf, nbtTag);
        wolf.setTamed(true);
        wolf.setHealth(20);
        wolf.setOwner(player.getCommandSenderName());
        wolf.setCollarColor(nbtTag.getByte("Collar"));
        world.setEntityState(wolf, (byte) 7);
        spawnMob(wolf, world, x, y, z);
    }

    public static void addInfo(List list, NBTTagCompound nbtTag) {
        addNameInfo(list, nbtTag);
        if (hasCollar(nbtTag)) {
            list.add(getCollarStr(nbtTag));
        }
    }

    private static boolean hasCollar(NBTTagCompound nbtTag) {
        return nbtTag.hasKey("Collar");
    }

    private static String getCollarStr(NBTTagCompound nbtTag) {
        return ModGraveStone.proxy.getLocalizedString("item.corpse.collar") + " " + nbtTag.getByte("Collar");
    }
}
