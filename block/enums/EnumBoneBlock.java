/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gravestone.block.enums;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumBoneBlock implements IBlockEnum {

    BONE_BLOCK("tile.bone_block.name"),
    SKULL_BONE_BLOCK("tile.skull_bone_block.name");
    private String name;

    private EnumBoneBlock(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    public static EnumBoneBlock getById(byte id) {
        if (id < values().length) {
            return values()[id];
        }
        return BONE_BLOCK;
    }
}
