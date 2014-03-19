package gravestone.item;

import gravestone.ModGraveStone;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class CorpseHelper {

    protected static void setMobName(EntityLiving entity, NBTTagCompound nbtTag) {
        if (nbtTag.hasKey("Name") && nbtTag.getString("Name").length() != 0) {
            entity.setCustomNameTag(nbtTag.getString("Name"));
        }
    }

    protected static void spawnMob(EntityLiving entity, World world, int x, int y, int z) {
        entity.setPosition(x, y + 1, z);
        world.spawnEntityInWorld(entity);
    }

    protected static void addNameInfo(List list, NBTTagCompound nbtTag) {
        if (nbtTag.hasKey("Name") && nbtTag.getString("Name").length() != 0) {
            list.add(ModGraveStone.proxy.getLocalizedString("item.corpse.entity_name") + " " + nbtTag.getString("Name"));
        }
    }
}
