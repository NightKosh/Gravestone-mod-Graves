package gravestone.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.GraveStoneLogger;
import gravestone.ModGraveStone;
import gravestone.core.GSItem;
import gravestone.core.Resources;
import gravestone.item.enums.EnumCorpse;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemGSCorpse extends Item {

    public static enum CORPSE_TYPE {

        VILLAGER,
        DOG,
        CAT,
        HORSE
    }

    public ItemGSCorpse(int id) {
        super(id);
        setCreativeTab(ModGraveStone.creativeTab);
        setUnlocalizedName("Corpse");
        this.setHasSubtypes(true);
        this.setTextureName(Resources.CORPSE);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        } else {
            addName(stack, list);
            switch (CORPSE_TYPE.values()[stack.getItemDamage()]) {
                case VILLAGER:
                    addVillagerType(stack, list);
                    addVillagerTrade(stack, list);
                    break;
                case HORSE:
                    addHorseType(stack, list);
                    addHP(stack, list);
                    addSpeed(stack, list);
                    addJumpPower(stack, list);
                    break;
                case DOG:
                    addCollar(stack, list);
                    break;
                case CAT:
                    addCatType(stack, list);
                    break;
            }
        }
    }

    private static void addName(ItemStack stack, List list) {
        if (stack.stackTagCompound.hasKey("Name") && stack.stackTagCompound.getString("Name").length() != 0) {
            list.add(ModGraveStone.proxy.getLocalizedString("item.corpse.entity_name") + " " + stack.stackTagCompound.getString("Name"));
        }
    }

    private static void addVillagerType(ItemStack stack, List list) {
        if (stack.stackTagCompound.hasKey("VillagerType")) {
            list.add(ModGraveStone.proxy.getLocalizedString("item.corpse.villager_type") + " " + getVillagerType(stack));
        }
    }

    private static void addVillagerTrade(ItemStack stack, List list) {
    }

    private static void addHorseType(ItemStack stack, List list) {
        if (stack.stackTagCompound.hasKey("HorseType")) {
            list.add(ModGraveStone.proxy.getLocalizedString("item.corpse.horse_type") + " " + stack.stackTagCompound.getByte("HorseType"));
        }
    }

    private static void addHP(ItemStack stack, List list) {
        if (stack.stackTagCompound.hasKey("Health")) {
            list.add(ModGraveStone.proxy.getLocalizedString("item.corpse.health") + " " + stack.stackTagCompound.getByte("Health"));
        }
    }

    private static void addSpeed(ItemStack stack, List list) {
        if (stack.stackTagCompound.hasKey("Speed")) {
            list.add(ModGraveStone.proxy.getLocalizedString("item.corpse.speed") + " " + stack.stackTagCompound.getByte("Speed"));
        }
    }

    private static void addJumpPower(ItemStack stack, List list) {
        if (stack.stackTagCompound.hasKey("JumpPower")) {
            list.add(ModGraveStone.proxy.getLocalizedString("item.corpse.jump_power") + " " + stack.stackTagCompound.getByte("JumpPower"));
        }
    }

    private static void addCollar(ItemStack stack, List list) {
        if (stack.stackTagCompound.hasKey("Collar")) {
            list.add(ModGraveStone.proxy.getLocalizedString("item.corpse.collar") + " " + stack.stackTagCompound.getByte("Collar"));
        }
    }

    private static void addCatType(ItemStack stack, List list) {
        if (stack.stackTagCompound.hasKey("CatType")) {
            list.add(ModGraveStone.proxy.getLocalizedString("item.corpse.cat_type") + " " + stack.stackTagCompound.getByte("CatType"));
        }
    }

    public static List<ItemStack> getCorpse(Entity entity, CORPSE_TYPE type) {
        NBTTagCompound nbt = new NBTTagCompound();

        if (((EntityLiving) entity).hasCustomNameTag()) {
            nbt.setString("Name", ((EntityLiving) entity).getCustomNameTag());
        }
        switch (type) {
            case VILLAGER:
                EntityVillager villager = (EntityVillager) entity;
                nbt.setInteger("VillagerType", villager.getProfession());
                nbt.setTag("Recipes", villager.getRecipes(null).getRecipiesAsTags().getTagList("Recipes"));
                break;
            case HORSE:
                EntityHorse horse = (EntityHorse) entity;

                nbt.setByte("Speed", (byte) horse.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue());
                nbt.setByte("Speed", (byte) horse.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                getPrivateByte(nbt, "Health", horse, "jumpPower");
                //getPrivateByte(nbt, "HorseType", horse, "");
                //                nbt.setInt("HorseType", entity.getH);
                break;
            case DOG:
                EntityWolf dog = (EntityWolf) entity;
                nbt.setByte("Collar", (byte) dog.getCollarColor());
                break;
            case CAT:
                EntityOcelot cat = (EntityOcelot) entity;
                nbt.setByte("CatType", (byte) cat.getTameSkin());
                break;
        }
        List<ItemStack> corpse = new ArrayList<ItemStack>();
        ItemStack stack = new ItemStack(GSItem.corpse, 1, type.ordinal());
        stack.setTagCompound(nbt);
        corpse.add(stack);

        return corpse;
    }

    private static void getPrivateByte(NBTTagCompound nbt, String nbtName, EntityLiving entity, String fieldName) {
        try {
            Field field = entity.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            float value = (Float) field.get(entity);

            nbt.setByte(nbtName, (byte) value);
        } catch (NoSuchFieldException e) {
            GraveStoneLogger.logError("Can't gat access to " + entity.getEntityName() + " " + fieldName);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            GraveStoneLogger.logError("Can't gat access to " + entity.getEntityName() + " " + fieldName);
            e.printStackTrace();
        }
    }

    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return True if something happen and
     * false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (!world.isRemote) {
            switch (CORPSE_TYPE.values()[stack.getItemDamage()]) {
                case VILLAGER:
                    EntityVillager villager = new EntityVillager(world, getVillagerType(stack));

                    if (stack.stackTagCompound.hasKey("Name") && stack.stackTagCompound.getString("Name").length() != 0) {
                        villager.setCustomNameTag(stack.stackTagCompound.getString("Name"));
                    }
                    NBTTagCompound nbt = new NBTTagCompound();
                    nbt.setTag("Recipes", stack.getTagCompound().getTag("Recipes"));
                    MerchantRecipeList list = new MerchantRecipeList();
                    list.readRecipiesFromTags(nbt);
                    ((EntityVillager) villager).setRecipes(list);
                    villager.setPosition(x, y + 1, z);
                    world.spawnEntityInWorld(villager);
                    break;
                case HORSE:
                    break;
                case DOG:
                    EntityWolf wolf = new EntityWolf(world);
                    if (stack.stackTagCompound.hasKey("Name") && stack.stackTagCompound.getString("Name").length() != 0) {
                        wolf.setCustomNameTag(stack.stackTagCompound.getString("Name"));
                    }
                    wolf.setTamed(true);
                    wolf.setHealth(20);
                    wolf.setOwner(player.getCommandSenderName());
                    wolf.setCollarColor(stack.stackTagCompound.getByte("Collar"));
                    world.setEntityState(wolf, (byte) 7);
                    wolf.setPosition(x, y + 1, z);
                    world.spawnEntityInWorld(wolf);
                    break;
                case CAT:
                    EntityOcelot cat = new EntityOcelot(world);
                    if (stack.stackTagCompound.hasKey("Name") && stack.stackTagCompound.getString("Name").length() != 0) {
                        cat.setCustomNameTag(stack.stackTagCompound.getString("Name"));
                    }
                    cat.setTamed(true);
                    cat.setTameSkin(stack.stackTagCompound.getByte("CatType"));
                    cat.setOwner(player.getCommandSenderName());
                    world.setEntityState(cat, (byte) 7);
                    cat.setPosition(x, y + 1, z);
                    world.spawnEntityInWorld(cat);
                    break;
            }
        }
        return false;
    }

    private static int getVillagerType(ItemStack stack) {
        return stack.stackTagCompound.getInteger("VillagerType");
    }
    /**
     * returns a list of items with the same ID, but different meta (eg: dye
     * returns 16 items)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tab, List list) {
        for (int j = 0; j < EnumCorpse.values().length; ++j) {
            list.add(new ItemStack(id, 1, j));
        }
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }

//    /**
//     * Gets an icon index based on an item's damage value
//     */
//    @Override
//    @SideOnly(Side.CLIENT)
//    public Icon getIconFromDamage(int damage) {
//        if (damage < 0 || damage >= skullTypes.length) {
//            damage = 0;
//        }
//
//        return this.field_94586_c[damage];
//    }
    @Override
    public String getItemDisplayName(ItemStack itemStack) {
        return EnumCorpse.getById((byte) itemStack.getItemDamage()).getName();
    }
//    @SideOnly(Side.CLIENT)
//    public void registerIcons(IconRegister par1IconRegister)
//    {
//        this.field_94586_c = new Icon[field_94587_a.length];
//
//        for (int i = 0; i < field_94587_a.length; ++i)
//        {
//            this.field_94586_c[i] = par1IconRegister.registerIcon(this.getIconString() + "_" + field_94587_a[i]);
//        }
//    }
}
