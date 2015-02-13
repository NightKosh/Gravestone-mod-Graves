/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gravestone.block.enums;

import net.minecraft.util.IStringSerializable;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumBoneBlock implements IBlockEnum, IStringSerializable {

    BONE_BLOCK("block.bone_block.default", "bone_block"),
    SKULL_BONE_BLOCK("block.bone_block.skull", "bone_block_with_skull"),
    CRAWLER_BONE_BLOCK("block.bone_block.crawler", "bone_block_with_crawler"),
    CRAWLER_SKULL_BONE_BLOCK("block.bone_block.crawler_skull", "bone_block_with_skull_and_crawler");
    private String name;
    private String blockModelName;

    private EnumBoneBlock(String name, String blockModelName) {
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

    public static EnumBoneBlock getById(byte id) {
        if (id < values().length) {
            return values()[id];
        }
        return BONE_BLOCK;
    }
}
