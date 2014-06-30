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
    CROSS("block.gravestone.cross"),
    HORISONTAL_PLATE("block.gravestone.plate"),
    DOG_STATUE("block.gravestone.dog_statue"),
    CAT_STATUE("block.gravestone.cat_statue"),
    WOODEN_SWORD("block.gravestone.wooden_sword"),
    STONE_SWORD("block.gravestone.stone_sword"),
    IRON_SWORD("block.gravestone.iron_sword"),
    GOLDEN_SWORD("block.gravestone.golden_sword"),
    DIAMOND_SWORD("block.gravestone.diamond_sword"),
    HORSE_STATUE("block.gravestone.horse_statue"),
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
    ICE_VERTICAL_PLATE("block.gravestone.gravestone");
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
