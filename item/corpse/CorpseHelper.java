package gravestone.item.corpse;

import gravestone.ModGraveStone;
import gravestone.core.GSItem;
import gravestone.item.enums.EnumCorpse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ItemStack> getDefaultCorpse(Item item, int corpseType) {
        switch (EnumCorpse.values()[corpseType]) {
            case HORSE:
                return HorseCorpseHelper.getDefaultCorpses(item, corpseType);
            case DOG:
                return DogCorpseHelper.getDefaultCorpses(item, corpseType);
            case CAT:
                return CatCorpseHelper.getDefaultCorpses(item, corpseType);
            case VILLAGER:
            default:
                return VillagerCorpseHelper.getDefaultCorpses(item, corpseType);
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
        if (player.worldObj.getWorldInfo().getGameType().equals(WorldSettings.GameType.CREATIVE)) {
            return true;
        } else {
            return player.experienceLevel >= getRequiredLevel(damage);
        }
    }

    public static void getExperience(EntityPlayer player, int damage) {
        player.addExperienceLevel(-getRequiredLevel(damage));
    }

    public static int getRequiredLevel(int damage) {
        switch (EnumCorpse.getById((byte) damage)) {
            case VILLAGER:
                return 20;
            case DOG:
            case CAT:
                return 7;
            case HORSE:
                return 15;
            default :
                return 0;
        }
    }

    public static int getRequiredLevel(ItemStack itemStack) {
        if (itemStack != null) {
            return getRequiredLevel(itemStack.getItemDamage());
        } else {
            return 0;
        }
    }
}
