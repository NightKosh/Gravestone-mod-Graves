package gravestone.item;

import gravestone.ModGraveStone;
import gravestone.core.GSItem;
import gravestone.item.enums.EnumCorpse;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumGameType;
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

    protected static void setName(EntityLiving entity, NBTTagCompound nbtTag) {
        if (entity.hasCustomNameTag()) {
            nbtTag.setString("Name", entity.getCustomNameTag());
        }
    }

    protected static void spawnMob(EntityLiving entity, World world, int x, int y, int z) {
        entity.setPosition(x + 0.5, y + 1, z + 0.5);
        world.spawnEntityInWorld(entity);
        entity.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 300));
    }

    protected static void addNameInfo(List list, NBTTagCompound nbtTag) {
        if (nbtTag.hasKey("Name") && nbtTag.getString("Name").length() != 0) {
            list.add(ModGraveStone.proxy.getLocalizedString("item.corpse.entity_name") + " " + nbtTag.getString("Name"));
        }
    }

    public static void addInfo(int corpseType, List list, NBTTagCompound nbtTag) {
        switch (EnumCorpse.values()[corpseType]) {
            case VILLAGER:
                VillagerCorpseHelper.addInfo(list, nbtTag);
                break;
            case HORSE:
                HorseCorpseHelper.addInfo(list, nbtTag);
                break;
            case DOG:
                DogCorpseHelper.addInfo(list, nbtTag);
                break;
            case CAT:
                CatCorpseHelper.addInfo(list, nbtTag);
                break;
        }
    }

    public static ItemStack getDefaultCorpse(int id, int type) {
        switch (EnumCorpse.values()[type]) {
            case HORSE:
                return HorseCorpseHelper.getDefaultCorpse(id, type);
            case DOG:
                return DogCorpseHelper.getDefaultCorpse(id, type);
            case CAT:
                return CatCorpseHelper.getDefaultCorpse(id, type);
            case VILLAGER:
            default:
                return VillagerCorpseHelper.getDefaultCorpse(id, type);
        }
    }

    public static List<ItemStack> getCorpse(Entity entity, EnumCorpse type) {
        NBTTagCompound nbtTag = new NBTTagCompound();
        switch (type) {
            case VILLAGER:
                VillagerCorpseHelper.setNbt((EntityVillager) entity, nbtTag);
                break;
            case HORSE:
                HorseCorpseHelper.setNbt((EntityHorse) entity, nbtTag);
                break;
            case DOG:
                DogCorpseHelper.setNbt((EntityWolf) entity, nbtTag);
                break;
            case CAT:
                CatCorpseHelper.setNbt((EntityOcelot) entity, nbtTag);
                break;
        }

        List<ItemStack> corpse = new ArrayList<ItemStack>();
        ItemStack stack = new ItemStack(GSItem.corpse, 1, type.ordinal());
        stack.setTagCompound(nbtTag);
        corpse.add(stack);
        return corpse;
    }

    public static void spawnMob(int type, World world, int x, int y, int z, NBTTagCompound nbtTag, EntityPlayer player) {
        if (!world.isRemote) {
            switch (EnumCorpse.values()[type]) {
                case VILLAGER:
                    VillagerCorpseHelper.spawnVillager(world, x, y, z, nbtTag);
                    break;
                case HORSE:
                    HorseCorpseHelper.spawnHorse(world, x, y, z, nbtTag, player);
                    break;
                case DOG:
                    DogCorpseHelper.spawnDog(world, x, y, z, nbtTag, player);
                    break;
                case CAT:
                    CatCorpseHelper.spawnCat(world, x, y, z, nbtTag, player);
                    break;
            }
        }
    }

    public static boolean canSpawnMob(EntityPlayer player, int damage) {
        if (player.worldObj.getWorldInfo().getGameType().equals(EnumGameType.CREATIVE)) {
            return true;
        } else {
            switch (EnumCorpse.getById((byte) damage)) {
                case VILLAGER:
                    return player.experienceLevel >= 20;
                case DOG:
                case CAT:
                    return player.experienceLevel >= 7;
                case HORSE:
                    return player.experienceLevel >= 15;
            }
        }
        return false;
    }

    public static void getExperience(EntityPlayer player, int damage) {
        switch (EnumCorpse.getById((byte) damage)) {
            case VILLAGER:
                player.experienceLevel -= 20;
                break;
            case DOG:
            case CAT:
                player.experienceLevel -= 7;
                break;
            case HORSE:
                player.experienceLevel -= 15;
                break;
        }
    }
}
