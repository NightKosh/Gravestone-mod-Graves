package net.minecraft.GraveStone.tileentity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import net.minecraft.GraveStone.GraveStoneConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class GSGraveStoneItems {

    private TileEntityGSGraveStone tileEntity;
    private Random rand = new Random();
    // grave max loot stacks
    public static final byte MAX_SLOTS = 40;
    // grave loot holder
    private static final int[] POTION_LIST = {16273, 16307, 16341, 16310, 16281, 16318,
        32657, 32658, 32659, 32725, 32694, 32665, 32702};
    protected ItemStack[] graveContents = new ItemStack[MAX_SLOTS];

    public GSGraveStoneItems(TileEntityGSGraveStone tileEntity) {
        this.tileEntity = tileEntity;
    }

    public void readItems(NBTTagCompound nbtTag) {
        NBTTagList ntbItemsList = nbtTag.getTagList("Items");
        graveContents = new ItemStack[MAX_SLOTS];

        for (int i = 0; i < ntbItemsList.tagCount(); ++i) {
            NBTTagCompound var4 = (NBTTagCompound) ntbItemsList.tagAt(i);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < graveContents.length) {
                graveContents[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    public void saveItems(NBTTagCompound nbtTag) {
        NBTTagList ntbList = new NBTTagList();

        for (int i = 0; i < graveContents.length; ++i) {
            if (graveContents[i] != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) i);
                graveContents[i].writeToNBT(var4);
                ntbList.appendTag(var4);
            }
        }

        nbtTag.setTag("Items", ntbList);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        graveContents[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit() {
        return 64;
    }

    public void setItems(ItemStack[] items) {
        if (items != null) {
            switch (GraveStoneConfig.graveItemsCount) {
                case 0:
                    for (int i = 0; i < items.length; i++) {
                        dropItem(items[i]);
                    }
                    break;
                case 40:
                    for (int i = 0; i < items.length; i++) {
                        setInventorySlotContents(i, items[i]);
                    }
                    break;
                default:
                    int savedItems = GraveStoneConfig.graveItemsCount;

                    Collections.shuffle(Arrays.asList(items.length), rand);

                    for (int i = 0; i < items.length; i++) {
                        if (items[i] != null && savedItems > 0) {
                            setInventorySlotContents(i, items[i]);
                            savedItems--;
                        } else {
                            dropItem(items[i]);
                        }
                    }
                    break;
            }
        }
    }

    public void dropItem(ItemStack items) {
        if (items != null) {
            float var10 = rand.nextFloat() * 0.8F + 0.1F;
            float var11 = rand.nextFloat() * 0.8F + 0.1F;
            EntityItem entityItem;

            for (float var12 = rand.nextFloat() * 0.8F + 0.1F; items.stackSize > 0; tileEntity.worldObj.spawnEntityInWorld(entityItem)) {
                int var13 = rand.nextInt(21) + 10;

                if (var13 > items.stackSize) {
                    var13 = items.stackSize;
                }

                items.stackSize -= var13;
                entityItem = new EntityItem(tileEntity.worldObj, tileEntity.xCoord + var10, tileEntity.yCoord + var11, tileEntity.zCoord + var12, new ItemStack(items.itemID, var13, items.getItemDamage()));
                entityItem.motionX = (double) (rand.nextGaussian() * 0.05F);
                entityItem.motionY = (double) (rand.nextGaussian() * 0.15F);
                entityItem.motionZ = (double) (rand.nextGaussian() * 0.05F);

                if (items.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) items.getTagCompound().copy());
                }
            }
        }
    }

    public void dropItem(int slot) {
        dropItem(graveContents[slot]);
    }

    public void dropAllItems() {
        for (int slot = 0; slot < MAX_SLOTS; slot++) {
            dropItem(slot);
        }
    }

    public void setRandomGraveContent() {
        setInventorySlotContents(0, new ItemStack(Item.bone.itemID, 1 + rand.nextInt(5), 0));
        setInventorySlotContents(1, new ItemStack(Item.rottenFlesh.itemID, 1 + rand.nextInt(5), 0));
        if (rand.nextInt(50) == 0) {
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(2, new ItemStack(Item.skull.itemID, 1, 0));
            } else {
                setInventorySlotContents(2, new ItemStack(Item.skull.itemID, 1, 2));
            }
        }

        int graveType = rand.nextInt(40);
        if (graveType < 4) {
            fillAdventureGrave();
        } else if (graveType < 7) {
            fillWorkerGrave();
        } else if (graveType < 10) {
            fillWizardGrave();
        } else if (graveType < 12) {
            fillMinerGrave();
        } else if (graveType == 13) {
            fillWarriorGrave();
        }
    }

    private void fillWarriorGrave() {
        int armorType = rand.nextInt(10);
        if (armorType > 5) { // Iron
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(3, new ItemStack(Item.plateSteel.itemID, 1, 0));
            }
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(4, new ItemStack(Item.legsSteel.itemID, 1, 0));
            }
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(5, new ItemStack(Item.helmetSteel.itemID, 1, 0));
            }
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(6, new ItemStack(Item.bootsSteel.itemID, 1, 0));
            }

            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(7, new ItemStack(Item.swordSteel.itemID, 1, 0));
            } else if (rand.nextInt(2) == 0) {
                setInventorySlotContents(7, new ItemStack(Item.bow.itemID, 1, 0));
                setInventorySlotContents(8, new ItemStack(Item.arrow.itemID, 10 + rand.nextInt(54), 0));
            }
        } else if (armorType > 2) {
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(3, new ItemStack(Item.plateChain.itemID, 1, 0));
            }
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(4, new ItemStack(Item.legsChain.itemID, 1, 0));
            }
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(5, new ItemStack(Item.helmetChain.itemID, 1, 0));
            }
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(6, new ItemStack(Item.bootsChain.itemID, 1, 0));
            }

            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(7, new ItemStack(Item.swordSteel.itemID, 1, 0));
            } else if (rand.nextInt(2) == 0) {
                setInventorySlotContents(7, new ItemStack(Item.bow.itemID, 1, 0));
                setInventorySlotContents(8, new ItemStack(Item.arrow.itemID, 10 + rand.nextInt(54), 0));
            }
        } else if (armorType > 0) {
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(3, new ItemStack(Item.plateGold.itemID, 1, 0));
            }
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(4, new ItemStack(Item.legsGold.itemID, 1, 0));
            }
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(5, new ItemStack(Item.helmetGold.itemID, 1, 0));
            }
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(6, new ItemStack(Item.bootsGold.itemID, 1, 0));
            }

            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(7, new ItemStack(Item.swordGold.itemID, 1, 0));
            } else if (rand.nextInt(2) == 0) {
                setInventorySlotContents(7, new ItemStack(Item.bow.itemID, 1, 0));
                setInventorySlotContents(8, new ItemStack(Item.arrow.itemID, 10 + rand.nextInt(54), 0));
            }
        } else {
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(3, new ItemStack(Item.plateDiamond.itemID, 1, 0));
            }
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(4, new ItemStack(Item.legsDiamond.itemID, 1, 0));
            }
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(5, new ItemStack(Item.helmetDiamond.itemID, 1, 0));
            }
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(6, new ItemStack(Item.bootsDiamond.itemID, 1, 0));
            }

            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(7, new ItemStack(Item.swordDiamond.itemID, 1, 0));
            } else if (rand.nextInt(2) == 0) {
                setInventorySlotContents(7, new ItemStack(Item.bow.itemID, 1, 0));
                setInventorySlotContents(8, new ItemStack(Item.arrow.itemID, 10 + rand.nextInt(54), 0));
            }
        }
    }

    private void fillMinerGrave() {
        if (rand.nextInt(2) == 0) {
            int pickAxeType = rand.nextInt(10);
            if (pickAxeType > 3) {
                setInventorySlotContents(3, new ItemStack(Item.pickaxeSteel.itemID, 1, 0));
            } else if (pickAxeType > 0) {
                setInventorySlotContents(3, new ItemStack(Item.pickaxeGold.itemID, 1, 0));
            } else {
                setInventorySlotContents(3, new ItemStack(Item.pickaxeDiamond.itemID, 1, 0));
            }
        }

        switch (rand.nextInt(10)) {
            case 0:
                setInventorySlotContents(4, new ItemStack(Item.diamond.itemID, 1 + rand.nextInt(3), 0));
                break;
            case 1:
                setInventorySlotContents(4, new ItemStack(Item.emerald.itemID, 1 + rand.nextInt(3), 0));
                break;
        }
        switch (rand.nextInt(5)) {
            case 0:
                setInventorySlotContents(5, new ItemStack(Item.ingotGold.itemID, 3 + rand.nextInt(5), 0));
                break;
            case 1:
            case 2:
                setInventorySlotContents(5, new ItemStack(Item.ingotIron.itemID, 3 + rand.nextInt(5), 0));
                break;
        }
        switch (rand.nextInt(5)) {
            case 0:
                setInventorySlotContents(6, new ItemStack(Item.redstone.itemID, 3 + rand.nextInt(8), 0));
                break;
            case 1:
                setInventorySlotContents(6, new ItemStack(Item.dyePowder.itemID, 3 + rand.nextInt(8), 4));
                break;
        }
    }

    private void fillWizardGrave() {
        switch (rand.nextInt(10)) {
            case 0: // enchanted book
                EnchantmentData data = new EnchantmentData(Enchantment.field_92090_c[rand.nextInt(Enchantment.field_92090_c.length)], 1 + rand.nextInt(3));
                ItemStack items = Item.enchantedBook.func_92111_a(data);
                setInventorySlotContents(3, items);
                break;
            case 1:
                setInventorySlotContents(3, new ItemStack(Item.potion.itemID, 1 + rand.nextInt(5), POTION_LIST[rand.nextInt(POTION_LIST.length)]));
                break;
            case 2:
            case 3:
                setInventorySlotContents(3, new ItemStack(Item.book.itemID, 3 + rand.nextInt(8), 0));
                break;
        }
        switch (rand.nextInt(15)) {
            case 0:
                setInventorySlotContents(4, new ItemStack(Item.enderPearl.itemID, 1, 0));
                break;
            case 1:
                setInventorySlotContents(4, new ItemStack(Item.blazePowder.itemID, 1, 0));
                break;
            case 2:
                setInventorySlotContents(4, new ItemStack(Item.lightStoneDust.itemID, 3 + rand.nextInt(8), 0));
                break;
        }
        switch (rand.nextInt(6)) {
            case 0:
                setInventorySlotContents(5, new ItemStack(Item.magmaCream.itemID, 1, 0));
                break;
            case 1:
                setInventorySlotContents(5, new ItemStack(Item.gunpowder.itemID, 1, 0));
                break;
        }
        switch (rand.nextInt(10)) {
            case 0:
                setInventorySlotContents(6, new ItemStack(Item.ghastTear.itemID, 1, 0));
                break;
            case 1:
                setInventorySlotContents(6, new ItemStack(Item.netherStalkSeeds.itemID, 1, 0));
                break;
        }
        switch (rand.nextInt(5)) {
            case 0:
                setInventorySlotContents(7, new ItemStack(Item.spiderEye.itemID, 1, 0));
                break;
            case 1:
                setInventorySlotContents(7, new ItemStack(Item.fermentedSpiderEye.itemID, 1, 0));
                break;
        }
        switch (rand.nextInt(8)) {
            case 0:
                setInventorySlotContents(8, new ItemStack(Item.goldenCarrot.itemID, 1, 0));
                break;
            case 1:
                setInventorySlotContents(8, new ItemStack(Item.speckledMelon.itemID, 1, 0));
                break;
        }


    }

    private void fillWorkerGrave() {
        int toolType = rand.nextInt(10);
        if (toolType > 3) {
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(3, new ItemStack(Item.axeSteel.itemID, 1, 0));
            } else {
                setInventorySlotContents(3, new ItemStack(Item.shovelSteel.itemID, 1, 0));
            }
        } else if (toolType > 0) {
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(3, new ItemStack(Item.axeGold.itemID, 1, 0));
            } else {
                setInventorySlotContents(3, new ItemStack(Item.shovelGold.itemID, 1, 0));
            }
        } else {
            if (rand.nextInt(2) == 0) {
                setInventorySlotContents(3, new ItemStack(Item.axeDiamond.itemID, 1, 0));
            } else {
                setInventorySlotContents(3, new ItemStack(Item.shovelDiamond.itemID, 1, 0));
            }
        }

        switch (rand.nextInt(6)) {
            case 0:
                setInventorySlotContents(4, new ItemStack(Item.clay.itemID, 1 + rand.nextInt(8), 0));
                break;
            case 1:
                setInventorySlotContents(4, new ItemStack(Item.brick.itemID, 3 + rand.nextInt(5), 0));
                break;
        }
        switch (rand.nextInt(6)) {
            case 0:
                setInventorySlotContents(5, new ItemStack(Item.leather.itemID, 1 + rand.nextInt(5), 0));
                break;
            case 1:
                setInventorySlotContents(5, new ItemStack(Item.bucketEmpty.itemID, 1, 0));
                break;

        }
        if (rand.nextInt(8) == 0) {
            setInventorySlotContents(6, new ItemStack(Item.saddle.itemID, 1, 0));
        }
    }

    private void fillAdventureGrave() {
        switch (rand.nextInt(8)) {
            case 0:
                setInventorySlotContents(3, new ItemStack(Item.compass.itemID, 1, 0));
                break;
            case 1:
                setInventorySlotContents(3, new ItemStack(Item.pocketSundial.itemID, 1, 0));
                break;
            case 2:
                setInventorySlotContents(3, new ItemStack(Item.emptyMap.itemID, 1, 0));
                break;
        }
        switch (rand.nextInt(10)) {
            case 0:
                setInventorySlotContents(4, new ItemStack(Item.painting.itemID, 1 + rand.nextInt(5), 0));
                break;
            case 1:
                setInventorySlotContents(4, getRandomRecord());
                break;
            case 2:
                setInventorySlotContents(4, new ItemStack(Item.writableBook.itemID, 1, 0));
                break;
        }
        if (rand.nextInt(4) == 0) {
            setInventorySlotContents(5, new ItemStack(Item.stick.itemID, 3 + rand.nextInt(9), 0));
        }
        if (rand.nextInt(5) == 0) {
            setInventorySlotContents(6, new ItemStack(Item.cookie.itemID, 3 + rand.nextInt(5), 0));
        }
        if (rand.nextInt(15) == 0) {
            setInventorySlotContents(7, getRandomEgg());
        }
    }

    private ItemStack getRandomRecord() {
        switch (rand.nextInt(12)) {
            case 0:
                return new ItemStack(Item.record13.itemID, 1, 0);
            case 1:
                return new ItemStack(Item.recordCat.itemID, 1, 0);
            case 2:
                return new ItemStack(Item.recordBlocks.itemID, 1, 0);
            case 3:
                return new ItemStack(Item.recordChirp.itemID, 1, 0);
            case 4:
                return new ItemStack(Item.recordFar.itemID, 1, 0);
            case 5:
                return new ItemStack(Item.recordMall.itemID, 1, 0);
            case 6:
                return new ItemStack(Item.recordMellohi.itemID, 1, 0);
            case 7:
                return new ItemStack(Item.recordStal.itemID, 1, 0);
            case 8:
                return new ItemStack(Item.recordStrad.itemID, 1, 0);
            case 9:
                return new ItemStack(Item.recordWard.itemID, 1, 0);
            case 10:
                return new ItemStack(Item.record11.itemID, 1, 0);
            case 11:
                return new ItemStack(Item.recordWait.itemID, 1, 0);
            default:
                return new ItemStack(Item.recordCat.itemID, 1, 0);
        }
    }

    private ItemStack getRandomEgg() {
        switch (rand.nextInt(10)) {
            case 0:
                return new ItemStack(Item.monsterPlacer.itemID, 1, 65);
            case 1:
                return new ItemStack(Item.monsterPlacer.itemID, 1, 90);
            case 2:
                return new ItemStack(Item.monsterPlacer.itemID, 1, 91);
            case 3:
                return new ItemStack(Item.monsterPlacer.itemID, 1, 92);
            case 4:
                return new ItemStack(Item.monsterPlacer.itemID, 1, 93);
            case 5:
                return new ItemStack(Item.monsterPlacer.itemID, 1, 94);
            case 6:
                return new ItemStack(Item.monsterPlacer.itemID, 1, 95);
            case 7:
                return new ItemStack(Item.monsterPlacer.itemID, 1, 96);
            case 8:
                return new ItemStack(Item.monsterPlacer.itemID, 1, 98);
            case 9:
                return new ItemStack(Item.monsterPlacer.itemID, 1, 120);
            default:
                return new ItemStack(Item.monsterPlacer.itemID, 1, 120);
        }
    }
}
