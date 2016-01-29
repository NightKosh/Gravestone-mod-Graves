package nightkosh.gravestone.helper;

import nightkosh.gravestone.tileentity.TileEntityGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface ISpawner {

    public static ISpawner spawner = new ISpawner() {
        @Override
        public ISpawner getSpawner(TileEntityGraveStone te) {
            return this;
        }
    };

    public default void update() {
    }

    public default void setMinDelay() {
    }

    public default ISpawner getSpawner(TileEntityGraveStone te) {
        return spawner;
    }
}
