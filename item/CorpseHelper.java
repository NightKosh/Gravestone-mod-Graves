package gravestone.item;

import gravestone.ModGraveStone;
import gravestone.core.GSItem;
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
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class CorpseHelper {

    public static enum CORPSE_TYPE {

        VILLAGER,
        DOG,
        CAT,
        HORSE
    }

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
        entity.setPosition(x, y + 1, z);
        world.spawnEntityInWorld(entity);
    }

    protected static void addNameInfo(List list, NBTTagCompound nbtTag) {
        if (nbtTag.hasKey("Name") && nbtTag.getString("Name").length() != 0) {
            list.add(ModGraveStone.proxy.getLocalizedString("item.corpse.entity_name") + " " + nbtTag.getString("Name"));
        }
    }
    
    public static void addInfo(int corpseType, List list, NBTTagCompound nbtTag) {
            switch (CORPSE_TYPE.values()[corpseType]) {
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
    
    public static List<ItemStack> getCorpse(Entity entity, CORPSE_TYPE type) {
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

    protected static void spawnMob(int type, World world, int x, int y, int z, NBTTagCompound nbtTag, EntityPlayer player) {
            switch (CORPSE_TYPE.values()[type]) {
                case VILLAGER:
                    VillagerCorpseHelper.spawnVillager(world, x, y, z, nbtTag);
                    break;
                case HORSE:
                    HorseCorpseHelper.spawnHorse(world, x, y, z, nbtTag);
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
