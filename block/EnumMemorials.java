package gravestone.block;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumMemorials {

    STONE_CROSS((byte) 0, "block.memorial.cross"),
    OBELISK((byte) 1, "block.memorial.obelisk"),
    STEVE_STATUE((byte) 2, "block.memorial.steve_statue"),
    VILLAGER_STATUE((byte) 3, "block.memorial.villager_statue"),
    ANGEL_STATUE((byte) 4, "block.memorial.angel_statue"),
    DOG_STATUE((byte) 5, "block.memorial.dog_statue"),
    CAT_STAUTE((byte) 6, "block.memorial.cat_statue"),
    CREEPER_STATUE((byte) 7, "block.memorial.creeper_statue");
    private byte id;
    private String name;
    public static final byte MEMORIALS_COUNT = 8;

    private EnumMemorials(byte id, String name) {
        this.id = id;
        this.name = name;
    }

    public byte getId() {
        return this.id;
    }

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
        EnumMemorials[] memorialTypes = values();

        for (int k = 0; k < memorialTypes.length; ++k) {
            EnumMemorials memorialType = memorialTypes[k];

            if (memorialType.id == id) {
                return memorialType;
            }
        }

        return STONE_CROSS;
    }
}
