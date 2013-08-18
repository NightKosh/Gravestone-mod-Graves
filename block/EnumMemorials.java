
package GraveStone.block;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumMemorials {

    STONE_CROSS((byte) 0, "Cross Memorial"),
    OBELISK((byte) 1, "Obelisk"),
    STEVE_STATUE((byte) 2, "Steve statue"),
    VILLAGER_STATUE((byte) 3, "Villager statue"),
    ANGEL_STATUE((byte) 4, "Angel statue"),
    DOG_STATUE((byte) 5, "Dog statue"),
    CAT_STAUTE((byte) 6, "Cat statue"),
    CREEPER_STATUE((byte) 7, "Creeper Statue");
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
        return this.name;
    }

    /**
     * Returns the grave type with the specified ID, or VERTICAL_PLATE if none found.
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
