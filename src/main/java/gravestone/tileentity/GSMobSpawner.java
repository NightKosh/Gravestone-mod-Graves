package gravestone.tileentity;

import gravestone.block.BlockGSSpawner;
import gravestone.block.enums.EnumSpawner;
import gravestone.core.GSMobSpawn;
import gravestone.core.logger.GSLogger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSMobSpawner extends GSSpawner {

    private static final int BASE_DELAY = 60;
    private static final int MIN_DELAY = 600;
    private static final int MAX_DELAY = 800;
    private static final int BOSS_PLAYER_RANGE = 8;
    private static final int MOB_PLAYER_RANGE = 16;
    private static final int SPAWN_EFFECTS_DELAY = 20;
    private static final float MAX_LIGHT_VALUE = 0.46F;
    private EnumSpawner spawnerType = null;

    public GSMobSpawner(TileEntity tileEntity) {
        super(tileEntity, BASE_DELAY);
    }

    @Override
    protected void clientUpdateLogic() {
//        delay--;
//        if (delay <= SPAWN_EFFECTS_DELAY) {
//            double x = tileEntity.xCoord + tileEntity.worldObj.rand.nextFloat();
//            double y = tileEntity.yCoord + tileEntity.worldObj.rand.nextFloat();
//            double z = tileEntity.zCoord + tileEntity.worldObj.rand.nextFloat();
//            tileEntity.worldObj.spawnParticle("largesmoke", x, y, z, 0.0D, 0.0D, 0.0D);
//            tileEntity.worldObj.spawnParticle("portal", x, y, z, 0.0D, 0.0D, 0.0D);
//            tileEntity.worldObj.spawnParticle("spell", x, y, z, 0.0D, 0.0D, 0.0D);
//            tileEntity.worldObj.spawnParticle("witchMagic", x, y, z, 0.0D, 0.0D, 0.0D);
//            tileEntity.worldObj.spawnParticle("lava", x, y, z, 0.0D, 0.0D, 0.0D);
//            tileEntity.worldObj.spawnParticle("flame", x, y, z, 0.0D, 0.0D, 0.0D);
//        }
    }

    @Override
    protected void serverUpdateLogic() {
        delay--;
        if (delay <= 0) {
            EntityLiving entity = (EntityLiving) getMob();
            if (entity == null) {
                GSLogger.logError("Spanwer mob get 'null' as mob!!!");
            } else {
                double x = tileEntity.getPos().getX() + 0.5;
                double y = tileEntity.getPos().getY();
                double z = tileEntity.getPos().getZ() + 0.5;
                entity.setLocationAndAngles(x, y, z, tileEntity.getWorld().rand.nextFloat() * 360, 0);
                if (isBossSpawner()) {
                    tileEntity.getWorld().removeTileEntity(tileEntity.getPos());
                    tileEntity.getWorld().setBlockToAir(tileEntity.getPos());
                    tileEntity.getWorld().spawnEntityInWorld(entity);
                } else if (tileEntity.getWorld().getLightBrightness(tileEntity.getPos()) <= MAX_LIGHT_VALUE) {
                    tileEntity.getWorld().spawnEntityInWorld(entity);
                }
            }
            this.updateDelay();
        }
    }

    private boolean isBossSpawner() {
        return BlockGSSpawner.BOSS_SPAWNERS.contains(getSpawnerType());
    }

    private EnumSpawner getSpawnerType() {
        if (spawnerType == null) {
            if (tileEntity.getWorld() == null) {
                GSLogger.logError("Spawner tileentity's world obj is null !!!!!");
                return EnumSpawner.ZOMBIE_SPAWNER;
            } else {
                spawnerType = EnumSpawner.getById((byte) tileEntity.getBlockMetadata());
                return spawnerType;
            }
        }
        return spawnerType;
    }

    @Override
    protected boolean canSpawnMobs(World world) {
        return true;
    }

    @Override
    protected int getPlayerRange() {
        return isBossSpawner() ? BOSS_PLAYER_RANGE : MOB_PLAYER_RANGE;
    }

    @Override
    protected Entity getMob() {
        return GSMobSpawn.getMobEntityForSpawner(this.tileEntity.getWorld(), getSpawnerType(),
                this.tileEntity.getPos().getX(), this.tileEntity.getPos().getY(), this.tileEntity.getPos().getZ());
    }

    @Override
    protected int getSpawnRange() {
        return 0;
    }

    @Override
    protected int getMinDelay() {
        return MIN_DELAY;
    }

    @Override
    protected int getMaxDelay() {
        return MAX_DELAY;
    }
}
