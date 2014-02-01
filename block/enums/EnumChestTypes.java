package gravestone.block.enums;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumChestTypes implements IBlockEnum {

    BATS_CHEST("block.ghostly_chest.bats_chest"),
    SKELETON_CHEST("block.ghostly_chest.skeleton_chest");
    private String name;

    private EnumChestTypes(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    public static EnumChestTypes getById(byte id) {
        if (id < values().length) {
            return values()[id];
        }
        return BATS_CHEST;
    }
}
