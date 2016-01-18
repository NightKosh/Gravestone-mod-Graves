package gravestone.helper;

import gravestone.core.GSPotion;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveInventoryHelper {
    private static final int[] POTION_LIST = {
            GSPotion.REGENERATION_POTION_ID, GSPotion.SWIFTNESS_POTION_ID, GSPotion.FIRE_RESISTANCE_POTION_ID,
            GSPotion.HEALING_POTION_ID, GSPotion.NIGHT_VISION_POTION_ID, GSPotion.STRENGTH_POTION_ID,
            GSPotion.INVISIBILITY_POTION_ID, GSPotion.WATER_BREATHING_POTION_ID, GSPotion.LEAPING_POTION_ID,
            GSPotion.SPLASH_REGENERATION_POTION_ID, GSPotion.SPLASH_SWIFTNESS_POTION_ID, GSPotion.SPLASH_FIRE_RESISTANCE_POTION_ID,
            GSPotion.SPLASH_HEALING_POTION_ID, GSPotion.SPLASH_NIGHT_VISION_POTION_ID, GSPotion.SPLASH_STRENGTH_POTION_ID,
            GSPotion.SPLASH_INVISIBILITY_POTION_ID, GSPotion.SPLASH_WATER_BREATHING_POTION_ID, GSPotion.SPLASH_LEAPING_POTION_ID
    };

    public static GraveContentType getRandomGraveContentType(Random random) {
        int graveType = random.nextInt(80);
        if (graveType > 5) {
            return GraveContentType.WARRIOR;//leather
        } else if (graveType < 4) {
            return GraveContentType.ADVENTURER;
        } else if (graveType < 7) {
            return GraveContentType.WORKER;
        } else if (graveType < 10) {
            return GraveContentType.WIZARD;
        } else if (graveType < 12) {
            return GraveContentType.MINER;
        } else if (graveType == 13) {
//            fillWarriorGrave(random, false);
            return GraveContentType.WARRIOR;
        }
        return null;//TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    public static enum GraveCorpseContentType {
        EMPTY,
        CORPSE,
        BONES_AND_FLESH,
        SKULL_BONES_AND_FLESH,
        RANDOM
    }

    public static GraveCorpseContentType getRandomCorpseContentType(Random random) {
//            if (canHaveSkullAndBones && random.nextInt(50) == 0) {
//
//            }
        return GraveCorpseContentType.BONES_AND_FLESH;//TODO !!!!!!!!!!!!!!!!!!
    }

    public static void addCorpse(Random random, List<ItemStack> itemList) {
        //TODO !!!!!!!!!!
    }

    public static void addBonesAndFlesh(Random random, List<ItemStack> itemList) {
        if (random.nextInt(2) == 0) {
            itemList.add(new ItemStack(Items.skull, 1, 0));
        } else {
            itemList.add(new ItemStack(Items.skull, 1, 2));
        }
    }

    public static void addSkull(Random random, List<ItemStack> itemList) {
        itemList.add(new ItemStack(Items.bone, 1 + random.nextInt(5), 0));
        itemList.add(new ItemStack(Items.rotten_flesh, 1 + random.nextInt(5), 0));
    }

    public static enum GraveContentType {
        RANDOM,
        WORKER,
        MINER,
        WIZARD,
        WARRIOR,
        ADVENTURER,
        TREASURY,
        JUNK
    }

    public static interface IContentMaterials {
    }

    public static enum ContentMaterials implements IContentMaterials {
        EMPTY,
        IRON,
        GOLDEN,
        DIAMOND
    }

    public static enum WarriorContentMaterials implements IContentMaterials {
        LEATHER,
        IRON,
        CHAINMAIL,
        GOLDEN,
        DIAMOND
    }

    /**
     * Fill grave with some random warrior stuff
     */
    public static void fillWarriorGrave(Random random, List<ItemStack> itemList, WarriorContentMaterials materials) {
        materials = WarriorContentMaterials.DIAMOND;//TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        switch (materials) {
            case LEATHER:
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.leather_chestplate, 1, getRandomDamage(random, 30)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.leather_leggings, 1, getRandomDamage(random, 30)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.leather_helmet, 1, getRandomDamage(random, 30)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.leather_boots, 1, getRandomDamage(random, 30)));
                }
                break;
            case IRON:
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.iron_chestplate, 1, getRandomDamage(random)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.iron_leggings, 1, getRandomDamage(random)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.iron_helmet, 1, getRandomDamage(random)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.iron_boots, 1, getRandomDamage(random)));
                }
                break;
            case CHAINMAIL:
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.chainmail_chestplate, 1, getRandomDamage(random)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.chainmail_leggings, 1, getRandomDamage(random)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.chainmail_helmet, 1, getRandomDamage(random)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.chainmail_boots, 1, getRandomDamage(random)));
                }
                break;
            case GOLDEN:
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.golden_chestplate, 1, getRandomDamage(random, 50)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.golden_leggings, 1, getRandomDamage(random, 50)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.golden_helmet, 1, getRandomDamage(random, 30)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.golden_boots, 1, getRandomDamage(random, 40)));
                }
                break;
            case DIAMOND:
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.diamond_chestplate, 1, getRandomDamage(random)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.diamond_leggings, 1, getRandomDamage(random)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.diamond_helmet, 1, getRandomDamage(random)));
                }
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.diamond_boots, 1, getRandomDamage(random)));
                }
                break;
        }

        if (random.nextInt(3) == 0) {
            itemList.add(new ItemStack(Items.bow, 1, getRandomDamage(random)));
            itemList.add(new ItemStack(Items.arrow, 10 + random.nextInt(54), 0));
        }
//        int armorType = random.nextInt(10);
//        if (armorType > 5) { // Iron
//        } else if (armorType > 2) {
//        } else if (armorType > 0) {
//        } else {
//        }
    }

    /**
     * Fill grave with some random miner stuff
     */
    public static void fillMinerGrave(Random random, List<ItemStack> itemList, ContentMaterials materials) {
        materials = ContentMaterials.DIAMOND;//TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        switch (materials) {
            case IRON:
                itemList.add(new ItemStack(Items.iron_pickaxe, 1, getRandomDamage(random)));
//                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_IRON_GRAVES), random));//TODO
                break;
            case GOLDEN:
                itemList.add(new ItemStack(Items.golden_pickaxe, 1, getRandomDamage(random, 15)));
//                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_GOLDEN_GRAVES), random));//TODO
                break;
            case DIAMOND:
                itemList.add(new ItemStack(Items.diamond_pickaxe, 1, getRandomDamage(random)));
//                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_DIAMOND_GRAVES), random));//TODO
                break;
        }
//        if (random.nextInt(2) == 0) {
//            int pickAxeType = random.nextInt(10);
//
//            if (pickAxeType > 3) {
//            } else if (pickAxeType > 0) {
//            } else {
//            }
//        }

        switch (random.nextInt(10)) {
            case 0:
                itemList.add(new ItemStack(Items.diamond, 1 + random.nextInt(3), 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.emerald, 1 + random.nextInt(3), 0));
                break;
        }
        switch (random.nextInt(5)) {
            case 0:
                itemList.add(new ItemStack(Items.gold_ingot, 3 + random.nextInt(5), 0));
                break;
            case 1:
            case 2:
                itemList.add(new ItemStack(Items.iron_ingot, 3 + random.nextInt(5), 0));
                break;
        }
        switch (random.nextInt(5)) {
            case 0:
                itemList.add(new ItemStack(Items.redstone, 3 + random.nextInt(8), 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.dye, 3 + random.nextInt(8), 4));
                break;
        }
    }

    /**
     * Fill grave with some random wizard stuff
     */
    public static void fillWizardGrave(Random random, List<ItemStack> itemList) {
        switch (random.nextInt(10)) {
            case 0: // enchanted book
                EnchantmentData data = new EnchantmentData(Enchantment.enchantmentsBookList[random.nextInt(Enchantment.enchantmentsBookList.length)], 1 + random.nextInt(5));
                ItemStack items = Items.enchanted_book.getEnchantedItemStack(data);
                itemList.add(items);
//                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_REDSTONE_GRAVES), random));//TODO
                break;
            case 1:
                itemList.add(new ItemStack(Items.potionitem, 1 + random.nextInt(5), POTION_LIST[random.nextInt(POTION_LIST.length)]));
//                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_QUARTZ_GRAVES), random));//TODO
                break;
            case 2:
            case 3:
                itemList.add(new ItemStack(Items.book, 3 + random.nextInt(8), 0));
//                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_LAPIS_GRAVES), random));//TODO
                break;
        }
        switch (random.nextInt(15)) {
            case 0:
                itemList.add(new ItemStack(Items.ender_pearl, 1, 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.blaze_powder, 1, 0));
                break;
            case 2:
                itemList.add(new ItemStack(Items.glowstone_dust, 3 + random.nextInt(8), 0));
                break;
        }
        switch (random.nextInt(6)) {
            case 0:
                itemList.add(new ItemStack(Items.magma_cream, 1, 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.gunpowder, 1, 0));
                break;
        }
        switch (random.nextInt(10)) {
            case 0:
                itemList.add(new ItemStack(Items.ghast_tear, 1, 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.nether_wart, 1, 0));
                break;
        }
        switch (random.nextInt(5)) {
            case 0:
                itemList.add(new ItemStack(Items.spider_eye, 1, 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.fermented_spider_eye, 1, 0));
                break;
        }
        switch (random.nextInt(8)) {
            case 0:
                itemList.add(new ItemStack(Items.golden_carrot, 1, 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.speckled_melon, 1, 0));
                break;
        }
    }

    /**
     * Fill grave with some random worker stuff
     */
    public static void fillWorkerGrave(Random random, List<ItemStack> itemList, ContentMaterials materials) {//TODO
        materials = ContentMaterials.DIAMOND;//TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        switch (materials) {
            case IRON:
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.iron_axe, 1, getRandomDamage(random)));
                } else {
                    itemList.add(new ItemStack(Items.iron_shovel, 1, getRandomDamage(random)));
                }
//                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_IRON_GRAVES), random));//TODO
                break;
            case GOLDEN:
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.golden_axe, 1, getRandomDamage(random, 15)));
                } else {
                    itemList.add(new ItemStack(Items.golden_shovel, 1, getRandomDamage(random, 15)));
                }
//                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_GOLDEN_GRAVES), random));//TODO
                break;
            case DIAMOND:
                if (random.nextInt(2) == 0) {
                    itemList.add(new ItemStack(Items.diamond_axe, 1, getRandomDamage(random)));
                } else {
                    itemList.add(new ItemStack(Items.diamond_shovel, 1, getRandomDamage(random)));
                }
//                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_DIAMOND_GRAVES), random));//TODO
                break;
        }

//        int toolType = random.nextInt(10);
//        if (toolType > 3) {
//        } else if (toolType > 0) {
//        } else {
//        }

        if (random.nextInt(8) == 0) {
            itemList.add(new ItemStack(Items.saddle, 1, 0));
        }
    }
    //TODO
//    public static enum AdventurerContentMaterials {
//        COMPASS_MAP_OR_CLOCK,
//
//    }

    /**
     * Fill grave with some random adventurer stuff
     */
    public static void fillAdventureGrave(Random random, List<ItemStack> itemList) {
        switch (random.nextInt(8)) {
            case 0:
                itemList.add(new ItemStack(Items.compass, 1, 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.clock, 1, 0));
                break;
            case 2:
                itemList.add(new ItemStack(Items.map, 1, 0));
                break;
        }

        switch (random.nextInt(10)) {
            case 0:
                itemList.add(new ItemStack(Items.painting, 1 + random.nextInt(5), 0));
                break;
            case 1:
                itemList.add(getRandomRecord(random));
                break;
            case 2:
                itemList.add(new ItemStack(Items.writable_book, 1, 0));
                break;
        }

        if (random.nextInt(4) == 0) {
            itemList.add(new ItemStack(Items.stick, 3 + random.nextInt(9), 0));
        }

        if (random.nextInt(5) == 0) {
            itemList.add(new ItemStack(Items.cookie, 3 + random.nextInt(5), 0));
        }

        if (random.nextInt(15) == 0) {
            itemList.add(getRandomEgg(random));
//            tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.GENERATED_EMERALD_GRAVES), random));//TODO
        }
    }

    /**
     * Fill pet grave
     */
    public static void fillPetGrave(Random random, List<ItemStack> itemList) {
        if (random.nextInt(10) == 0) {
            itemList.add(new ItemStack(Items.lead, 1, 0));
            //            if (Arrays.asList(GraveStoneHelper.DOGS_GRAVES).contains(tileEntity.getGraveType())) {
            ////                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.DOG_GOLDEN_GRAVES), random));//TODO
            //            } else if (Arrays.asList(GraveStoneHelper.CATS_GRAVES).contains(tileEntity.getGraveType())) {
            ////                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.CAT_GOLDEN_GRAVES), random));//TODO
            //            }
        }

        if (random.nextInt(10) == 0) {
            itemList.add(new ItemStack(Items.name_tag, 1, 0));
            //            if (Arrays.asList(GraveStoneHelper.DOGS_GRAVES).contains(tileEntity.getGraveType())) {
            ////                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.DOG_DIAMOND_GRAVES), random));//TODO
            //            } else if (Arrays.asList(GraveStoneHelper.CATS_GRAVES).contains(tileEntity.getGraveType())) {
            ////                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.CAT_DIAMOND_GRAVES), random));//TODO
            //            }
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
    private static ItemStack getRandomRecord(Random random) {
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
    private static ItemStack getRandomEgg(Random random) {
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


    public static List<ItemStack> getRandomGraveContent(Random random, GraveGenerationHelper.EnumGraveTypeByEntity graveTypeByEntity, GraveContentType contentType,
                                                        GraveCorpseContentType corpseType, IContentMaterials contentMaterials) {
        List<ItemStack> itemList = new ArrayList<>();
        if (corpseType == GraveInventoryHelper.GraveCorpseContentType.RANDOM) {
            corpseType = GraveInventoryHelper.getRandomCorpseContentType(random);
        }
        switch (corpseType) {
            case CORPSE:
                GraveInventoryHelper.addCorpse(random, itemList);
                break;
            case BONES_AND_FLESH:
                GraveInventoryHelper.addBonesAndFlesh(random, itemList);
                break;
            case SKULL_BONES_AND_FLESH:
                GraveInventoryHelper.addSkull(random, itemList);
                GraveInventoryHelper.addBonesAndFlesh(random, itemList);
                break;
        }

        if (graveTypeByEntity == GraveGenerationHelper.EnumGraveTypeByEntity.DOGS_GRAVES ||
                graveTypeByEntity == GraveGenerationHelper.EnumGraveTypeByEntity.CATS_GRAVES ||
                graveTypeByEntity == GraveGenerationHelper.EnumGraveTypeByEntity.HORSE_GRAVES) {
            GraveInventoryHelper.fillPetGrave(random, itemList);
        } else {
            if (contentType == GraveInventoryHelper.GraveContentType.RANDOM) {
                contentType = GraveInventoryHelper.getRandomGraveContentType(random);
            }
            switch (contentType) {
                case WORKER:
                    GraveInventoryHelper.fillWorkerGrave(random, itemList, (ContentMaterials) contentMaterials);
                    break;
                case MINER:
                    GraveInventoryHelper.fillMinerGrave(random, itemList, (ContentMaterials) contentMaterials);
                    break;
                case WIZARD:
                    GraveInventoryHelper.fillWizardGrave(random, itemList);
                    break;
                case WARRIOR:
                    GraveInventoryHelper.fillWarriorGrave(random, itemList, (WarriorContentMaterials) contentMaterials);
                    break;
                case ADVENTURER:
                    GraveInventoryHelper.fillAdventureGrave(random, itemList);
                    break;
                case TREASURY:
                    //TODO
                    break;
                case JUNK:
                    //DO NOTHING, contentMaterials can be null !!!
                    break;
            }
//                if (tileEntity.isSwordGrave() && graveType > 5) {
//                    fillWarriorGrave(random, true);
//                } else if (graveType < 4) {
//                } else if (graveType < 7) {
//                } else if (graveType < 10) {
//                } else if (graveType < 12) {
//                } else if (graveType == 13) {
//                    fillWarriorGrave(random, false);
//                }
        }
        return itemList;
    }
}
