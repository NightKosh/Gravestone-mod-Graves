package gravestone.particle;

import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityGreenFlameFX extends EntityFlameFX {

    public EntityGreenFlameFX(World world, double xPos, double yPos, double zPos, double p_i1209_8_, double p_i1209_10_, double p_i1209_12_) {
        super(world, xPos, yPos, zPos, p_i1209_8_, p_i1209_10_, p_i1209_12_);

        this.particleRed = 0.1F;
        this.particleBlue = 0.1F;
        this.particleGreen = 0.85F;
    }
}
