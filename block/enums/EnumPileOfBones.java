package gravestone.block.enums;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumPileOfBones implements IBlockEnum {

    PILE_OF_BONES("block.pile_of_bones.name"),
    PILE_OF_BONES_WITH_SKULL("block.pile_of_bones_with_skull.name");

    private String name;

    private EnumPileOfBones(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    public static EnumPileOfBones getByID(int id) {
        if (id < values().length) {
            return values()[id];
        }
        return PILE_OF_BONES;
    }
}
