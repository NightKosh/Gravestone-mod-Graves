package gravestone.block.enums;

import gravestone.ModGraveStone;
import net.minecraft.util.IStringSerializable;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumHauntedChest implements IBlockEnum, IStringSerializable {

    BATS_CHEST("block.haunted_chest.bats_chest", "chest_with_bats"),
    SKELETON_CHEST("block.haunted_chest.skeleton_chest", "chest_with_skeleton");
    //RAVEN_CHEST("block.haunted_chest.raven_chest", "chest_with_ravens");
    private String name;
    private String blockModelName;

    private EnumHauntedChest(String name, String blockModelName) {
        this.name = name;
        this.blockModelName = blockModelName;
    }

    @Override
    public String getLocalizedName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    @Override
    public String getName() {
        return blockModelName;
    }

    public static EnumHauntedChest getById(int id) {
        if (id < values().length) {
            return values()[id];
        }
        return BATS_CHEST;
    }
}
