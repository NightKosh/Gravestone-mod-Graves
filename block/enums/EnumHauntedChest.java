package gravestone.block.enums;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumHauntedChest implements IBlockEnum {

    BATS_CHEST("block.haunted_chest.bats_chest"),
    SKELETON_CHEST("block.haunted_chest.skeleton_chest");
    //RAVEN_CHEST("block.haunted_chest.raven_chest");
    private String name;

    private EnumHauntedChest(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    public static EnumHauntedChest getById(byte id) {
        if (id < values().length) {
            return values()[id];
        }
        return BATS_CHEST;
    }
}
