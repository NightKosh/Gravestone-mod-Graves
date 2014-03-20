package gravestone.item;

import gravestone.ModGraveStone;
import static gravestone.item.CorpseHelper.setMobName;
import java.util.List;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class HorseCorpseHelper extends CorpseHelper {

    private HorseCorpseHelper() {
    }

    public static void setNbt(EntityHorse horse, NBTTagCompound nbt) {
        setName(horse, nbt);
        
        NBTTagCompound horseNBT = new NBTTagCompound();
        horse.writeEntityToNBT(horseNBT);

        nbt.setInteger("HorseType", horseNBT.getInteger("Type"));
        nbt.setInteger("Variant", horseNBT.getInteger("Variant"));

        nbt.setDouble("Max Health", horse.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue());
        nbt.setDouble("Movement Speed", horse.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
        nbt.setDouble("Jump Strength", horse.getHorseJumpStrength());
    }

    public static void spawnHorse(World world, int x, int y, int z, NBTTagCompound nbtTag) {
        EntityHorse horse = new EntityHorse(world);
        setMobName(horse, nbtTag);

        NBTTagCompound horseNBT = new NBTTagCompound();
        horse.writeEntityToNBT(horseNBT);

        horseNBT.setInteger("HorseType", nbtTag.getInteger("HorseType"));
        horseNBT.setInteger("Variant", nbtTag.getInteger("Variant"));

        NBTTagList attrs = (NBTTagList) horseNBT.getTag("Attributes");
        for (int i = 0; i < attrs.tagCount(); i++) {
            NBTBase tag = attrs.tagAt(i);
            if (tag.getName().equals("Max Health")) {
                ((NBTTagDouble) tag).data = nbtTag.getDouble("Max Health");
            } else if (tag.getName().equals("Movement Speed")) {
                ((NBTTagDouble) tag).data = nbtTag.getDouble("Movement Speed");
            } else if (tag.getName().equals("Jump Strength")) {
                ((NBTTagDouble) tag).data = nbtTag.getDouble("Jump Strength");
            }
        }
        horseNBT.setTag("Attributes", attrs);

        horse.readEntityFromNBT(horseNBT);

        spawnMob(horse, world, x, y, z);
    }

    public static void addInfo(List list, NBTTagCompound nbtTag) {
        addNameInfo(list, nbtTag);
        if (hasType(nbtTag)) {
            list.add(getType(nbtTag));
        }
        if (hasVariant(nbtTag)) {
            list.add(getVariant(nbtTag));
        }
        if (hasHP(nbtTag)) {
            list.add(getHP(nbtTag));
        }
        if (hasSpeed(nbtTag)) {
            list.add(getSpeed(nbtTag));
        }
        if (hasJumpStrength(nbtTag)) {
            list.add(getJumpStrength(nbtTag));
        }
    }

    private static boolean hasType(NBTTagCompound nbtTag) {
        return nbtTag.hasKey("HorseType");
    }

    private static String getType(NBTTagCompound nbtTag) {
        return ModGraveStone.proxy.getLocalizedString("item.corpse.horse_type") + " " + 
                ModGraveStone.proxy.getLocalizedString(getHorseType(nbtTag.getInteger("HorseType")));
    }
    
    private static String getHorseType(int type) {
        switch (type) {
            case 0:
                return "item.corpse.horse_type.horse";
            case 1:
                return "item.corpse.horse_type.donkey";
            case 2:
                return "item.corpse.horse_type.mule";
            case 3:
                return "item.corpse.horse_type.zombie";
            case 4:
                return "item.corpse.horse_type.skeleton";
            default:
                return "item.corpse.horse_type.unknown";
        }
    }

    private static boolean hasVariant(NBTTagCompound nbtTag) {
        return nbtTag.hasKey("Variant");
    }

    private static String getVariant(NBTTagCompound nbtTag) {
        return ModGraveStone.proxy.getLocalizedString("item.corpse.horse_variant") + " " + nbtTag.getInteger("Variant");
    }

    private static boolean hasHP(NBTTagCompound nbtTag) {
        return nbtTag.hasKey("Max Health");
    }

    private static String getHP(NBTTagCompound nbtTag) {
        return ModGraveStone.proxy.getLocalizedString("item.corpse.health") + " " + nbtTag.getDouble("Max Health");
    }

    private static boolean hasSpeed(NBTTagCompound nbtTag) {
        return nbtTag.hasKey("Movement Speed");
    }

    private static String getSpeed(NBTTagCompound nbtTag) {
        return ModGraveStone.proxy.getLocalizedString("item.corpse.speed") + " " + nbtTag.getDouble("Movement Speed");
    }

    private static boolean hasJumpStrength(NBTTagCompound nbtTag) {
        return nbtTag.hasKey("Jump Strength");
    }

    private static String getJumpStrength(NBTTagCompound nbtTag) {
        return ModGraveStone.proxy.getLocalizedString("item.corpse.jump_strength") + " " + nbtTag.getDouble("Jump Strength");
    }
}
