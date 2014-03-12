package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import gravestone.block.enums.EnumSpawner;
import gravestone.config.GraveStoneConfig;
import gravestone.core.Resources;
import gravestone.tileentity.TileEntityGSSpawner;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSSpawner extends BlockMobSpawner {
    
    private static final byte[] MobSpawners = {
        (byte) EnumSpawner.SKELETON_SPAWNER.ordinal(),
        (byte) EnumSpawner.ZOMBIE_SPAWNER.ordinal()
    };
    private static final byte[] BossSpawners = {
        (byte) EnumSpawner.WITHER_SPAWNER.ordinal()
    };

    public BlockGSSpawner(int id) {
        super(id);
        this.setUnlocalizedName("Spawner");
        this.setHardness(5.0F);
        this.setLightValue(0.45F);
        this.setStepSound(soundMetalFootstep);
        this.disableStats();
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.setTextureName(Resources.PENTAGRAM_ICO);
        this.setBlockBounds(-0.5F, 0, -0.5F, 1.5F, 0.1F, 1.5F);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing
     * the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityGSSpawner();
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return GraveStoneConfig.spawnerRenderID;
    }

    /**
     * A randomly called display update to be able to add particles or other
     * items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        double xPos = x + 0.5F;
        double yPos = y + 0.85;
        double zPos = z + 0.5F;
        double dRotation = Math.toRadians(72);
        double rotation = Math.toRadians(-36);
        double d = 1.07;
        double dx;
        double dz;

        for (int i = 0; i < 5; i++) {
            dx = -Math.sin(rotation) * d;
            dz = Math.cos(rotation) * d;
            world.spawnParticle("smoke", xPos + dx, yPos, zPos + dz, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", xPos + dx, yPos, zPos + dz, 0.0D, 0.0D, 0.0D);
            rotation += dRotation;
        }
    }

    /**
     * Returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List list) {
        for (byte meta : MobSpawners) {
            list.add(new ItemStack(id, 1, meta));
        }
        for (byte meta : BossSpawners) {
            list.add(new ItemStack(id, 1, meta));
        }
    }
}
