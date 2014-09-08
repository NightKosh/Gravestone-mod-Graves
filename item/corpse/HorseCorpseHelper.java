package gravestone.item.corpse;

import gravestone.ModGraveStone;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class HorseCorpseHelper extends CorpseHelper {

    private HorseCorpseHelper() {
    }

    public static ItemStack getDefaultCorpse(Item item, int type) {
        ItemStack corpse = new ItemStack(item, 1, type);
        NBTTagCompound nbtTag = new NBTTagCompound();

        nbtTag.setInteger("HorseType", 0);
        nbtTag.setInteger("Variant", 0);

        nbtTag.setDouble("Max Health", 25);
        nbtTag.setDouble("Movement Speed", 0.3);
        nbtTag.setDouble("Jump Strength", 0.7);

        corpse.setTagCompound(nbtTag);
        return corpse;
    }

    public static void setNbt(EntityHorse horse, NBTTagCompound nbt) {
        setName(horse, nbt);

        nbt.setInteger("HorseType", horse.getHorseType());
        nbt.setInteger("Variant", horse.getHorseVariant());

        BaseAttributeMap attrMap = horse.getAttributeMap();
        nbt.setDouble("Max Health", attrMap.getAttributeInstanceByName("Max Health").getAttributeValue());
        nbt.setDouble("Movement Speed", attrMap.getAttributeInstanceByName("Movement Speed").getAttributeValue());
        nbt.setDouble("Jump Strength", attrMap.getAttributeInstanceByName("Jump Strength").getAttributeValue());
    }

    public static void spawnHorse(World world, int x, int y, int z, NBTTagCompound nbtTag, EntityPlayer player) {
        EntityHorse horse = new EntityHorse(world);
        setMobName(horse, nbtTag);

        horse.setHorseType(nbtTag.getInteger("HorseType"));
        horse.setHorseVariant(nbtTag.getInteger("Variant"));

        BaseAttributeMap attrMap = horse.getAttributeMap();
        attrMap.getAttributeInstanceByName("Max Health").setBaseValue(nbtTag.getDouble("Max Health"));
        attrMap.getAttributeInstanceByName("Movement Speed").setBaseValue(nbtTag.getDouble("Movement Speed"));
        attrMap.getAttributeInstanceByName("Jump Strength").setBaseValue(nbtTag.getDouble("Jump Strength"));

        horse.setTamedBy(player);

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
