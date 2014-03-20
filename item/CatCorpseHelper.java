package gravestone.item;

import gravestone.ModGraveStone;
import java.util.List;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatCorpseHelper extends CorpseHelper {

    private CatCorpseHelper() {
    }

    public static void setNbt(EntityOcelot cat, NBTTagCompound nbt) {
        setName(cat, nbt);
        nbt.setByte("CatType", (byte) cat.getTameSkin());
    }

    public static void spawnCat(World world, int x, int y, int z, NBTTagCompound nbtTag, EntityPlayer player) {
        EntityOcelot cat = new EntityOcelot(world);
        setMobName(cat, nbtTag);

        cat.setTamed(true);
        cat.setTameSkin(nbtTag.getByte("CatType"));
        cat.setOwner(player.getCommandSenderName());
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
        return ModGraveStone.proxy.getLocalizedString("item.corpse.cat_type") + " " + nbtTag.getByte("CatType");
    }
}
