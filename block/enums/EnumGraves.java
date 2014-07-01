package gravestone.block.enums;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumGraves implements IBlockEnum {

    STONE_VERTICAL_PLATE("block.gravestone.gravestone"),
    STONE_CROSS("block.gravestone.cross"),
    HORISONTAL_PLATE("block.gravestone.plate"),
    STONE_DOG_STATUE("block.gravestone.dog_statue"),
    CAT_STATUE("block.gravestone.cat_statue"),
    WOODEN_SWORD("block.gravestone.wooden_sword"),
    STONE_SWORD("block.gravestone.stone_sword"),
    IRON_SWORD("block.gravestone.iron_sword"),
    GOLDEN_SWORD("block.gravestone.golden_sword"),
    DIAMOND_SWORD("block.gravestone.diamond_sword"),
    HORSE_STATUE("block.gravestone.horse_statue"),
    // VERTICAL PLATES
    WOODEN_VERTICAL_PLATE("block.gravestone.gravestone"),
    SANDSTONE_VERTICAL_PLATE("block.gravestone.gravestone"),
    IRON_VERTICAL_PLATE("block.gravestone.gravestone"),
    GOLDEN_VERTICAL_PLATE("block.gravestone.gravestone"),
    DIAMOND_VERTICAL_PLATE("block.gravestone.gravestone"),
    EMERALD_VERTICAL_PLATE("block.gravestone.gravestone"),
    LAPIS_VERTICAL_PLATE("block.gravestone.gravestone"),
    REDSTONE_VERTICAL_PLATE("block.gravestone.gravestone"),
    OBSIDIAN_VERTICAL_PLATE("block.gravestone.gravestone"),
    QUARTZ_VERTICAL_PLATE("block.gravestone.gravestone"),
    ICE_VERTICAL_PLATE("block.gravestone.gravestone"),
    MOSSY_VERTICAL_PLATE("block.gravestone.gravestone"),
    // CROSSES
    WOODEN_CROSS("block.gravestone.cross"),
    SANDSTONE_CROSS("block.gravestone.cross"),
    IRON_CROSS("block.gravestone.cross"),
    GOLDEN_CROSS("block.gravestone.cross"),
    DIAMOND_CROSS("block.gravestone.cross"),
    EMERALD_CROSS("block.gravestone.cross"),
    LAPIS_CROSS("block.gravestone.cross"),
    REDSTONE_CROSS("block.gravestone.cross"),
    OBSIDIAN_CROSS("block.gravestone.cross"),
    QUARTZ_CROSS("block.gravestone.cross"),
    ICE_CROSS("block.gravestone.cross"),
    MOSSY_CROSS("block.gravestone.cross"),
    // HORISONTAL PLATES
    // DOGS GRAVES
    WOODEN_DOG_STATUE("block.gravestone.dog_statue"),
    SANDSTONE_DOG_STATUE("block.gravestone.dog_statue"),
    IRON_DOG_STATUE("block.gravestone.dog_statue"),
    GOLDEN_DOG_STATUE("block.gravestone.dog_statue"),
    DIAMOND_DOG_STATUE("block.gravestone.dog_statue"),
    EMERALD_DOG_STATUE("block.gravestone.dog_statue"),
    LAPIS_DOG_STATUE("block.gravestone.dog_statue"),
    REDSTONE_DOG_STATUE("block.gravestone.dog_statue"),
    OBSIDIAN_DOG_STATUE("block.gravestone.dog_statue"),
    QUARTZ_DOG_STATUE("block.gravestone.dog_statue"),
    ICE_DOG_STATUE("block.gravestone.dog_statue"),
    MOSSY_DOG_STATUE("block.gravestone.dog_statue");
    // CATS GRAVES
    private String name;

    private EnumGraves(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    /**
     * Returns the grave type with the specified ID, or VERTICAL_PLATE if none found.
     *
     * @param id Grave Id
     */
    public static EnumGraves getByID(int id) {
        if (id < values().length) {
            return values()[id];
        }
        return STONE_VERTICAL_PLATE;
    }
}
