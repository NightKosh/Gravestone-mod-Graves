package gravestone.block.enums;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumChestTypes {

    BATS_CHEST((byte) 0, "block.ghostly_chest.bats_chest"),
    SKELETON_CHEST((byte) 1, "block.ghostly_chest.skeleton_chest");
    private byte id;
    private String name;

    private EnumChestTypes(byte id, String name) {
        this.id = id;
        this.name = name;
    }

    public byte getId() {
        return id;
    }

    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    public static EnumChestTypes getById(byte id) {
        EnumChestTypes[] chestTypes = values();
        for (int k = 0; k < chestTypes.length; ++k) {
            EnumChestTypes chestType = chestTypes[k];

            if (chestType.id == id) {
                return chestType;
            }
        }

        return BATS_CHEST;
    }
}
