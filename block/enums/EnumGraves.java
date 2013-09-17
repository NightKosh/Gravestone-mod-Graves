package gravestone.block.enums;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumGraves {

    VERTICAL_PLATE((byte) 0, "block.gravestone.gravestone"),
    CROSS((byte) 1, "block.gravestone.cross"),
    HORISONTAL_PLATE((byte) 2, "block.gravestone.plate"),
    DOG_STATUE((byte) 3, "block.gravestone.dog_statue"),
    CAT_STATUE((byte) 4, "block.gravestone.cat_statue"),
    WOODEN_SWORD((byte) 5, "block.gravestone.wooden_sword"),
    STONE_SWORD((byte) 6, "block.gravestone.stone_sword"),
    IRON_SWORD((byte) 7, "block.gravestone.iron_sword"),
    GOLDEN_SWORD((byte) 8, "block.gravestone.golden_sword"),
    DIAMOND_SWORD((byte) 9, "block.gravestone.diamond_sword");
    private byte id;
    private String name;

    private EnumGraves(byte id, String name) {
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
     * Returns the grave type with the specified ID, or VERTICAL_PLATE if none found.
     *
     * @param id Grave Id
     */
    public static EnumGraves getByID(int id) {
        EnumGraves[] graveTypes = values();

        for (int k = 0; k < graveTypes.length; ++k) {
            EnumGraves graveType = graveTypes[k];

            if (graveType.id == id) {
                return graveType;
            }
        }

        return VERTICAL_PLATE;
    }
}
