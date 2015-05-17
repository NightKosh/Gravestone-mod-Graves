package gravestone.block.enums;

import net.minecraft.util.IStringSerializable;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumPileOfBones implements IBlockEnum, IStringSerializable {

    PILE_OF_BONES("block.pile_of_bones", "pile_of_bones"),
    PILE_OF_BONES_WITH_SKULL("block.pile_of_bones_with_skull", "pile_of_bones_with_skull"),
    PILE_OF_BONES_WITH_SKULL_CRAWLER("block.pile_of_bones_with_skull_crawler", "pile_of_bones_with_skull_crawler");

    private String name;
    private String blockModelName;

    private EnumPileOfBones(String name, String blockModelName) {
        this.name = name;
        this.blockModelName = blockModelName;
    }

    @Override
    public String getUnLocalizedName() {
        return this.name;
    }

    @Override
    public String getName() {
        return blockModelName;
    }

    public static EnumPileOfBones getById(int id) {
        if (id < values().length) {
            return values()[id];
        }
        return PILE_OF_BONES;
    }
}
