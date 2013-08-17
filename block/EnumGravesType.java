
package GraveStone.block;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumGravesType {

    VERTICAL_PLATE((byte) 0, "Gravestone"),
    CROSS((byte) 1, "Cross"),
    HORISONTAL_PLATE((byte) 2, "Grave Plate"),
    DOG_STATUE((byte) 3, "Dog statue"),
    CAT_STATUE((byte) 4, "Cat statue"),
    WOODEN_SWORD((byte) 5, "Wooden sword gravestone"),
    STONE_SWORD((byte) 6, "Stone sword gravestone"),
    IRON_SWORD((byte) 7, "Iron sword gravestone"),
    GOLDEN_SWORD((byte) 8, "Golden sword gravestone"),
    DIAMOND_SWORD((byte) 9, "Diamond sword gravestone");
    private byte id;
    private String name;
    public static final byte GRAVES_COUNT = 10;

    private EnumGravesType(byte id, String name) {
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
    public static EnumGravesType getByID(int id) {
        EnumGravesType[] graveTypes = values();

        for (int k = 0; k < graveTypes.length; ++k) {
            EnumGravesType graveType = graveTypes[k];

            if (graveType.id == id) {
                return graveType;
            }
        }

        return VERTICAL_PLATE;
    }
}
