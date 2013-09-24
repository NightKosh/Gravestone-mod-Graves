package gravestone.block.enums;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumMemorials implements IBlockEnum {

    STONE_CROSS((byte) 0, "block.memorial.cross"),
    OBELISK((byte) 1, "block.memorial.obelisk"),
    STEVE_STATUE((byte) 2, "block.memorial.steve_statue"),
    VILLAGER_STATUE((byte) 3, "block.memorial.villager_statue"),
    ANGEL_STATUE((byte) 4, "block.memorial.angel_statue"),
    DOG_STATUE((byte) 5, "block.memorial.dog_statue"),
    CAT_STATUE((byte) 6, "block.memorial.cat_statue"),
    CREEPER_STATUE((byte) 7, "block.memorial.creeper_statue");
    private byte id;
    private String name;

    private EnumMemorials(byte id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public byte getId() {
        return this.id;
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
