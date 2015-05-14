
package gravestone.tileentity;

import gravestone.block.GraveStoneHelper;
import gravestone.block.enums.EnumGraves;
import gravestone.config.GSConfig;
import gravestone.core.GSPotion;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSGraveStoneItems implements IInventory {

    private TileEntityGSGraveStone tileEntity;
    private static final int[] POTION_LIST = {
            GSPotion.REGENERATION_POTION_ID, GSPotion.SWIFTNESS_POTION_ID, GSPotion.FIRE_RESISTANCE_POTION_ID,
            GSPotion.HEALING_POTION_ID, GSPotion.NIGHT_VISION_POTION_ID, GSPotion.STRENGTH_POTION_ID,
            GSPotion.INVISIBILITY_POTION_ID, GSPotion.WATER_BREATHING_POTION_ID, GSPotion.LEAPING_POTION_ID,
            GSPotion.SPLASH_REGENERATION_POTION_ID, GSPotion.SPLASH_SWIFTNESS_POTION_ID, GSPotion.SPLASH_FIRE_RESISTANCE_POTION_ID,
            GSPotion.SPLASH_HEALING_POTION_ID, GSPotion.SPLASH_NIGHT_VISION_POTION_ID, GSPotion.SPLASH_STRENGTH_POTION_ID,
            GSPotion.SPLASH_INVISIBILITY_POTION_ID, GSPotion.SPLASH_WATER_BREATHING_POTION_ID, GSPotion.SPLASH_LEAPING_POTION_ID
    };
    protected List<ItemStack> items = new ArrayList<ItemStack>(54);

    public GSGraveStoneItems(TileEntityGSGraveStone tileEntity) {
        this.tileEntity = tileEntity;
    }

    public void readItems(NBTTagCompound nbtTag) {
        NBTTagList ntbItemsList = nbtTag.getTagList("Items", 10);
        items = new ArrayList<ItemStack>(54);

        for (int i = 0; i < ntbItemsList.tagCount(); ++i) {
            NBTTagCompound nbt = ntbItemsList.getCompoundTagAt(i);
            ItemStack stack = ItemStack.loadItemStackFromNBT(nbt);
            if (stack != null) {
                items.add(stack);
            }
        }
    }

    public void saveItems(NBTTagCompound nbtTag) {
        NBTTagList ntbList = new NBTTagList();

        for (ItemStack stack : items) {
            if (stack != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                stack.writeToNBT(nbt);
                ntbList.appendTag(nbt);
            }
        }

        nbtTag.setTag("Items", ntbList);
    }

    public void addInventoryContent(ItemStack itemStack) {
        if (itemStack != null) {
            items.add(itemStack);
        }
    }

    @Override
    public int getSizeInventory() {
        return items.size();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < items.size()) {
            return items.get(slot);
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amount);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.items.add(slot, stack);
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.worldObj.getTileEntity(this.tileEntity.getPos()) == this.tileEntity &&
                player.getDistanceSq(new BlockPos(this.tileEntity.getPos().getX() + 0.5, this.tileEntity.getPos().getY() + 0.5, this.tileEntity.getPos().getZ() + 0.5)) < 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        this.items.clear();
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }

    /**
     * Set items as grave loot
     *
     * @param items Saving items
     */
    public void setItems(List<ItemStack> items) {
        if (items != null) {
            switch (GSConfig.graveItemsCount) {
                case 0:
                    for (int i = 0; i < items.size(); i++) {
                        dropItem(items.get(i), tileEntity.getWorld(), tileEntity.getPos());
                    }
                    break;
                case 40:
                    for (int i = 0; i < items.size(); i++) {
                        addInventoryContent(items.get(i));
                    }
                    break;
                default:
                    int savedItems = GSConfig.graveItemsCount;
                    Collections.shuffle(Arrays.asList(items.size()), new Random());

                    for (int i = 0; i < items.size(); i++) {
                        if (items.get(i) != null && savedItems > 0) {
                            addInventoryContent(items.get(i));
                            savedItems--;
                        } else {
                            dropItem(items.get(i), tileEntity.getWorld(), tileEntity.getPos());
                        }
                    }
                    break;
            }
        }
    }

    public void setAdditionalItems(ItemStack[] items) {
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                addInventoryContent(items[i]);
            }
        }
    }

    /**
     * Drop item
     *
     * @param items Dropping item
     */
    public static void dropItem(ItemStack items, World world, BlockPos pos) {
        if (items != null) {
            Random random = new Random();
            float var10 = random.nextFloat() * 0.8F + 0.1F;
            float var11 = random.nextFloat() * 0.8F + 0.1F;
            EntityItem entityItem;

            for (float var12 = random.nextFloat() * 0.8F + 0.1F; items.stackSize > 0; world.spawnEntityInWorld(entityItem)) {
                int var13 = random.nextInt(21) + 10;

                if (var13 > items.stackSize) {
                    var13 = items.stackSize;
                }

                items.stackSize -= var13;
                entityItem = new EntityItem(world, pos.getX() + var10, pos.getY() + var11, pos.getZ() + var12, new ItemStack(items.getItem(), var13, items.getItemDamage()));
                entityItem.motionX = random.nextGaussian() * 0.05F;
                entityItem.motionY = random.nextGaussian() * 0.15F;
                entityItem.motionZ = random.nextGaussian() * 0.05F;

                if (items.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) items.getTagCompound().copy());
                }
            }
        }
    }

    /**
     * Drop item by slot number
     *
     * @param slot Item slot number
     */
    public void dropItem(int slot) {
        dropItem(items.get(slot), tileEntity.getWorld(), tileEntity.getPos());
    }

    public void dropItem(ItemStack stack) {
        dropItem(stack, tileEntity.getWorld(), tileEntity.getPos());
    }

    /*
     * Drop all holding items
     */
    public void dropAllItems() {
        for (ItemStack stack : items) {
            dropItem(stack);
        }
        items.clear();
    }

    public List<ItemStack> getGraveContent() {
        return items;
    }

    /**
     * Set random itesm as grave loot
     */
    public void setRandomGraveContent(Random random, boolean isPetGrave, boolean allLoot) {
        addInventoryContent(new ItemStack(Items.bone, 1 + random.nextInt(5), 0));
        addInventoryContent(new ItemStack(Items.rotten_flesh, 1 + random.nextInt(5), 0));

        if (isPetGrave) {
            fillPetGrave(random);
        } else {
            if (tileEntity.graveType != 3 && tileEntity.graveType != 4) {
                if (random.nextInt(50) == 0) {
                    if (random.nextInt(2) == 0) {
                        addInventoryContent(new ItemStack(Items.skull, 1, 0));
                    } else {
                        addInventoryContent(new ItemStack(Items.skull, 1, 2));
                    }
                }

                int graveType = random.nextInt(80);

                if (allLoot) {
                    if (tileEntity.isSwordGrave() && graveType > 5) {
                        fillWarriorGrave(random, true);
                    } else if (graveType < 4) {
                        fillAdventureGrave(random);
                    } else if (graveType < 7) {
                        fillWorkerGrave(random);
                    } else if (graveType < 10) {
                        fillWizardGrave(random);
                    } else if (graveType < 12) {
                        fillMinerGrave(random);
                    } else if (graveType == 13) {
                        fillWarriorGrave(random, false);
                    }
                } else {
                    if (graveType < 3) {
                        fillWorkerGrave(random);
                    }
                }
            }
        }
    }

    /**
     * Fill grave with some random warrior stuff
     */
    private void fillWarriorGrave(Random random, boolean isSwordGrave) {
        if (isSwordGrave) {
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.leather_chestplate, 1, getRandomDamage(random, 30)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.leather_leggings, 1, getRandomDamage(random, 30)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.leather_helmet, 1, getRandomDamage(random, 30)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.leather_boots, 1, getRandomDamage(random, 30)));
            }

            return;
        }

        int armorType = random.nextInt(10);

        if (armorType > 5) { // Iron
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.iron_chestplate, 1, getRandomDamage(random)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.iron_leggings, 1, getRandomDamage(random)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.iron_helmet, 1, getRandomDamage(random)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.iron_boots, 1, getRandomDamage(random)));
            }

            changeGraveTypeToSword(Items.iron_sword, getRandomDamage(random));
        } else if (armorType > 2) {
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.chainmail_chestplate, 1, getRandomDamage(random)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.chainmail_leggings, 1, getRandomDamage(random)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.chainmail_helmet, 1, getRandomDamage(random)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.chainmail_boots, 1, getRandomDamage(random)));
            }

            changeGraveTypeToSword(Items.iron_sword, getRandomDamage(random));
        } else if (armorType > 0) {
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.golden_chestplate, 1, getRandomDamage(random, 50)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.golden_leggings, 1, getRandomDamage(random, 50)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.golden_helmet, 1, getRandomDamage(random, 30)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.golden_boots, 1, getRandomDamage(random, 40)));
            }

            changeGraveTypeToSword(Items.golden_sword, getRandomDamage(random, 15));
        } else {
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.diamond_chestplate, 1, getRandomDamage(random)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.diamond_leggings, 1, getRandomDamage(random)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.diamond_helmet, 1, getRandomDamage(random)));
            }
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.diamond_boots, 1, getRandomDamage(random)));
            }
            changeGraveTypeToSword(Items.diamond_sword, getRandomDamage(random));
        }

        if (random.nextInt(3) == 0) {
            addInventoryContent(new ItemStack(Items.bow, 1, getRandomDamage(random)));
            addInventoryContent(new ItemStack(Items.arrow, 10 + random.nextInt(54), 0));
        }
    }

    /**
     * Change grave type to swordGrave if this is a warrior grave
     *
     * @param sword
     * @param swordDamage
     */
    // TODO
    private void changeGraveTypeToSword(Item sword, int swordDamage) {
        tileEntity.setGraveType(EnumGraves.SWORD.ordinal());
        tileEntity.setSword(new ItemStack(sword, 1, swordDamage));
    }

    /**
     * Fill grave with some random miner stuff
     */
    private void fillMinerGrave(Random random) {
        if (random.nextInt(2) == 0) {
            int pickAxeType = random.nextInt(10);

            if (pickAxeType > 3) {
                addInventoryContent(new ItemStack(Items.iron_pickaxe, 1, getRandomDamage(random)));
                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_IRON_GRAVES), random));
            } else if (pickAxeType > 0) {
                addInventoryContent(new ItemStack(Items.golden_pickaxe, 1, getRandomDamage(random, 15)));
                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_GOLDEN_GRAVES), random));
            } else {
                addInventoryContent(new ItemStack(Items.diamond_pickaxe, 1, getRandomDamage(random)));
                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_DIAMOND_GRAVES), random));
            }
        }

        switch (random.nextInt(10)) {
            case 0:
                addInventoryContent(new ItemStack(Items.diamond, 1 + random.nextInt(3), 0));
                break;
            case 1:
                addInventoryContent(new ItemStack(Items.emerald, 1 + random.nextInt(3), 0));
                break;
        }
        switch (random.nextInt(5)) {
            case 0:
                addInventoryContent(new ItemStack(Items.gold_ingot, 3 + random.nextInt(5), 0));
                break;
            case 1:
            case 2:
                addInventoryContent(new ItemStack(Items.gold_ingot, 3 + random.nextInt(5), 0));
                break;
        }
        switch (random.nextInt(5)) {
            case 0:
                addInventoryContent(new ItemStack(Items.redstone, 3 + random.nextInt(8), 0));
                break;
            case 1:
                addInventoryContent(new ItemStack(Items.dye, 3 + random.nextInt(8), 4));
                break;
        }
    }

    /**
     * Fill grave with some random wizard stuff
     */
    private void fillWizardGrave(Random random) {
        switch (random.nextInt(10)) {
            case 0: // enchanted book
                EnchantmentData data = new EnchantmentData(Enchantment.enchantmentsBookList[random.nextInt(Enchantment.enchantmentsBookList.length)], 1 + random.nextInt(5));
                ItemStack items = Items.enchanted_book.getEnchantedItemStack(data);
                addInventoryContent(items);
                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_REDSTONE_GRAVES), random));
                break;
            case 1:
                addInventoryContent(new ItemStack(Items.potionitem, 1 + random.nextInt(5), POTION_LIST[random.nextInt(POTION_LIST.length)]));
                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_QUARTZ_GRAVES), random));
                break;
            case 2:
            case 3:
                addInventoryContent(new ItemStack(Items.book, 3 + random.nextInt(8), 0));
                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_LAPIS_GRAVES), random));
                break;
        }
        switch (random.nextInt(15)) {
            case 0:
                addInventoryContent(new ItemStack(Items.ender_pearl, 1, 0));
                break;
            case 1:
                addInventoryContent(new ItemStack(Items.blaze_powder, 1, 0));
                break;
            case 2:
                addInventoryContent(new ItemStack(Items.glowstone_dust, 3 + random.nextInt(8), 0));
                break;
        }
        switch (random.nextInt(6)) {
            case 0:
                addInventoryContent(new ItemStack(Items.magma_cream, 1, 0));
                break;
            case 1:
                addInventoryContent(new ItemStack(Items.gunpowder, 1, 0));
                break;
        }
        switch (random.nextInt(10)) {
            case 0:
                addInventoryContent(new ItemStack(Items.ghast_tear, 1, 0));
                break;
            case 1:
                addInventoryContent(new ItemStack(Items.nether_wart, 1, 0));
                break;
        }
        switch (random.nextInt(5)) {
            case 0:
                addInventoryContent(new ItemStack(Items.spider_eye, 1, 0));
                break;
            case 1:
                addInventoryContent(new ItemStack(Items.fermented_spider_eye, 1, 0));
                break;
        }
        switch (random.nextInt(8)) {
            case 0:
                addInventoryContent(new ItemStack(Items.golden_carrot, 1, 0));
                break;
            case 1:
                addInventoryContent(new ItemStack(Items.speckled_melon, 1, 0));
                break;
        }
    }

    /**
     * Fill grave with some random worker stuff
     */
    private void fillWorkerGrave(Random random) {
        int toolType = random.nextInt(10);

        if (toolType > 3) {
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.iron_axe, 1, getRandomDamage(random)));
            } else {
                addInventoryContent(new ItemStack(Items.iron_shovel, 1, getRandomDamage(random)));
            }
            tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_IRON_GRAVES), random));
        } else if (toolType > 0) {
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.golden_axe, 1, getRandomDamage(random, 15)));
            } else {
                addInventoryContent(new ItemStack(Items.golden_shovel, 1, getRandomDamage(random, 15)));
            }
            tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_GOLDEN_GRAVES), random));
        } else {
            if (random.nextInt(2) == 0) {
                addInventoryContent(new ItemStack(Items.diamond_axe, 1, getRandomDamage(random)));
            } else {
                addInventoryContent(new ItemStack(Items.diamond_shovel, 1, getRandomDamage(random)));
            }
            tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_DIAMOND_GRAVES), random));
        }

        switch (random.nextInt(6)) {
            case 0:
                addInventoryContent(new ItemStack(Items.clay_ball, 1 + random.nextInt(8), 0));
                break;
            case 1:
                addInventoryContent(new ItemStack(Items.brick, 3 + random.nextInt(5), 0));
                break;
        }

        switch (random.nextInt(6)) {
            case 0:
                addInventoryContent(new ItemStack(Items.leather, 1 + random.nextInt(5), 0));
                break;
            case 1:
                addInventoryContent(new ItemStack(Items.bucket, 1, 0));
                break;
        }

        if (random.nextInt(8) == 0) {
            addInventoryContent(new ItemStack(Items.saddle, 1, 0));
        }
    }

    /**
     * Fill grave with some random adventurer stuff
     */
    private void fillAdventureGrave(Random random) {
        switch (random.nextInt(8)) {
            case 0:
                addInventoryContent(new ItemStack(Items.compass, 1, 0));
                break;
            case 1:
                addInventoryContent(new ItemStack(Items.clock, 1, 0));
                break;
            case 2:
                addInventoryContent(new ItemStack(Items.map, 1, 0));
                break;
        }

        switch (random.nextInt(10)) {
            case 0:
                addInventoryContent(new ItemStack(Items.painting, 1 + random.nextInt(5), 0));
                break;
            case 1:
                addInventoryContent(getRandomRecord(random));
                break;
            case 2:
                addInventoryContent(new ItemStack(Items.writable_book, 1, 0));
                break;
        }

        if (random.nextInt(4) == 0) {
            addInventoryContent(new ItemStack(Items.stick, 3 + random.nextInt(9), 0));
        }

        if (random.nextInt(5) == 0) {
            addInventoryContent(new ItemStack(Items.cookie, 3 + random.nextInt(5), 0));
        }

        if (random.nextInt(15) == 0) {
            addInventoryContent(getRandomEgg(random));
            tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_EMERALD_GRAVES), random));
        }
    }

    /**
     * Fill pet grave
     */
    private void fillPetGrave(Random random) {
        if (random.nextInt(10) == 0) {
            addInventoryContent(new ItemStack(Items.lead, 1, 0));
            if (Arrays.asList(GraveStoneHelper.DOGS_GRAVES).contains(tileEntity.getGraveType())) {
                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.DOG_GOLDEN_GRAVES), random));
            } else if (Arrays.asList(GraveStoneHelper.CATS_GRAVES).contains(tileEntity.getGraveType())) {
                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.CAT_GOLDEN_GRAVES), random));
            }
        }

        if (random.nextInt(10) == 0) {
            addInventoryContent(new ItemStack(Items.name_tag, 1, 0));
            if (Arrays.asList(GraveStoneHelper.DOGS_GRAVES).contains(tileEntity.getGraveType())) {
                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.DOG_DIAMOND_GRAVES), random));
            } else if (Arrays.asList(GraveStoneHelper.CATS_GRAVES).contains(tileEntity.getGraveType())) {
                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.CAT_DIAMOND_GRAVES), random));
            }
        }
    }

    /**
     * Return random damage values for items
     */
    public static int getRandomDamage(Random random) {
        return 20 + random.nextInt(100);
    }

    /**
     * Return random damage values for items with maximum damage value
     *
     * @param random
     * @param maxDamage Max item damage
     */
    public static int getRandomDamage(Random random, int maxDamage) {
        return random.nextInt(maxDamage);
    }

    /**
     * Return random record
     */
    private ItemStack getRandomRecord(Random random) {
        switch (random.nextInt(13)) {
            case 1:
                return new ItemStack(Items.record_cat, 1, 0);
            case 2:
                return new ItemStack(Items.record_blocks, 1, 0);
            case 3:
                return new ItemStack(Items.record_chirp, 1, 0);
            case 4:
                return new ItemStack(Items.record_far, 1, 0);
            case 5:
                return new ItemStack(Items.record_mall, 1, 0);
            case 6:
                return new ItemStack(Items.record_mellohi, 1, 0);
            case 7:
                return new ItemStack(Items.record_stal, 1, 0);
            case 8:
                return new ItemStack(Items.record_strad, 1, 0);
            case 9:
                return new ItemStack(Items.record_ward, 1, 0);
            case 10:
                return new ItemStack(Items.record_11, 1, 0);
            case 11:
                return new ItemStack(Items.record_wait, 1, 0);
            case 12:
                return new ItemStack(Items.record_13, 1, 0);
            case 0:
            default:
                return new ItemStack(Items.record_cat, 1, 0);
        }
    }

    private static final int EGG_PIG = 90;
    private static final int EGG_SHEEP = 91;
    private static final int EGG_COW = 92;
    private static final int EGG_CHICKEN = 93;
    private static final int EGG_SQUID = 94;
    private static final int EGG_WOLF = 95;
    private static final int EGG_MUSHROOM_COW = 96;
    private static final int EGG_CAT = 98;
    private static final int EGG_HORSE = 100;
    private static final int EGG_VILLAGER = 120;

    /**
     * Return random egg
     */
    private ItemStack getRandomEgg(Random random) {
        switch (random.nextInt(11)) {
            case 1:
                return new ItemStack(Items.spawn_egg, 1, EGG_PIG);
            case 2:
                return new ItemStack(Items.spawn_egg, 1, EGG_SHEEP);
            case 3:
                return new ItemStack(Items.spawn_egg, 1, EGG_COW);
            case 4:
                return new ItemStack(Items.spawn_egg, 1, EGG_CHICKEN);
            case 5:
                return new ItemStack(Items.spawn_egg, 1, EGG_SQUID);
            case 6:
                return new ItemStack(Items.spawn_egg, 1, EGG_WOLF);
            case 7:
                return new ItemStack(Items.spawn_egg, 1, EGG_MUSHROOM_COW);
            case 8:
                return new ItemStack(Items.spawn_egg, 1, EGG_CAT);
            case 9:
                return new ItemStack(Items.spawn_egg, 1, EGG_HORSE);
            case 10:
                return new ItemStack(Items.spawn_egg, 1, EGG_VILLAGER);
            case 0:
            default:
                return new ItemStack(Items.spawn_egg, 1, 120);
        }
    }

}