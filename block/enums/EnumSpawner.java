
package gravestone.block.enums;

import gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumSpawner implements IBlockEnum {

    WITHER_SPAWNER("block.spawner.wither"),
    SKELETON_SPAWNER("block.spawner.skeleton"),
    ZOMBIE_SPAWNER("block.spawner.zombie");
    private String name;

    private EnumSpawner(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    public static EnumSpawner getById(byte id) {
        if (id < values().length) {
            return values()[id];
        }
        return ZOMBIE_SPAWNER;
    }
}
