package gravestone.block.enums;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumMemorials implements IBlockEnum {

    STONE_CROSS("block.memorial.cross"),
    OBELISK("block.memorial.obelisk"),
    STEVE_STATUE("block.memorial.steve_statue"),
    VILLAGER_STATUE("block.memorial.villager_statue"),
    ANGEL_STATUE("block.memorial.angel_statue"),
    DOG_STATUE("block.memorial.dog_statue"),
    CAT_STATUE("block.memorial.cat_statue"),
    CREEPER_STATUE("block.memorial.creeper_statue");
    private String name;

    private EnumMemorials(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    /**
     * Returns the grave type with the specified ID, or VERTICAL_PLATE if none
     * found.
     *
     * @param id Grave Id
     */
    public static EnumMemorials getByID(int id) {
        if (id < values().length) {
            return values()[id];
        }
        return STONE_CROSS;
    }
}
