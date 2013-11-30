
package gravestone.block;

import gravestone.ModGraveStone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSGhostlyLamp extends BlockContainer {


    public BlockGSGhostlyLamp(int id) {
        super(id, Material.rock);
        this.isBlockContainer = true;
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("Ghostly lamp");
        this.setHardness(1F);
        this.setResistance(1F);
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.setTextureName("stone");
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntity();//TileEntityGSGhostlyLamp();
    }

}
