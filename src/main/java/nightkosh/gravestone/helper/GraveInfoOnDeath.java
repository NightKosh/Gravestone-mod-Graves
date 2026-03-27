package nightkosh.gravestone.helper;

import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;

import javax.annotation.Nonnull;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public record GraveInfoOnDeath(
        @Nonnull EnumGraveType graveType,
        @Nonnull EnumGraveMaterial material) {
}
