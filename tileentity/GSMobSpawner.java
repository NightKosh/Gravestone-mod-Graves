package gravestone.tileentity;

import gravestone.GraveStoneLogger;
import gravestone.block.BlockGSSpawner;
import gravestone.block.enums.EnumSpawner;
import gravestone.core.GSMobSpawn;
import net.minecraft.entity.Entity;
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
    private EnumSpawner spawnerType = null;

    public GSMobSpawner(TileEntity tileEntity) {
        super(tileEntity, BASE_DELAY);
    }

    @Override
    protected void clientUpdateLogic() {
        delay--;
        if (delay <= SPAWN_EFFECTS_DELAY) {
            double x = tileEntity.xCoord + tileEntity.worldObj.rand.nextFloat();
            double y = tileEntity.yCoord + tileEntity.worldObj.rand.nextFloat();
            double z = tileEntity.zCoord + tileEntity.worldObj.rand.nextFloat();
            tileEntity.worldObj.spawnParticle("largesmoke", x, y, z, 0.0D, 0.0D, 0.0D);
            tileEntity.worldObj.spawnParticle("portal", x, y, z, 0.0D, 0.0D, 0.0D);
            tileEntity.worldObj.spawnParticle("spell", x, y, z, 0.0D, 0.0D, 0.0D);
            tileEntity.worldObj.spawnParticle("witchMagic", x, y, z, 0.0D, 0.0D, 0.0D);
            tileEntity.worldObj.spawnParticle("lava", x, y, z, 0.0D, 0.0D, 0.0D);
            tileEntity.worldObj.spawnParticle("flame", x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void serverUpdateLogic() {
        delay--;
        if (delay <= 0) {
            Entity entity = getMob();
            if (entity == null) {
                GraveStoneLogger.logError("Spanwer mob get 'null' as mob!!!");
            } else {
                double x = tileEntity.xCoord + 0.5D;
                double y = tileEntity.yCoord + 0.5D;
                double z = tileEntity.zCoord + 0.5D;
                entity.setLocationAndAngles(x, y, z, tileEntity.worldObj.rand.nextFloat() * 360.0F, 0.0F);
                tileEntity.worldObj.spawnEntityInWorld(entity);
            }
            if (isBossSpawner()) {
                tileEntity.worldObj.removeBlockTileEntity(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
                tileEntity.worldObj.setBlock(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, 0);
            } else {
                this.updateDelay();
            }
        }
    }

    private boolean isBossSpawner() {
        return BlockGSSpawner.BOSS_SPAWNERS.contains((byte) getSpawnerType().ordinal());
    }

    private EnumSpawner getSpawnerType() {
        if (spawnerType == null) {
            if (tileEntity.worldObj == null) {
                GraveStoneLogger.logError("Spawner te worldobj is null !!!!!");
                return EnumSpawner.ZOMBIE_SPAWNER;
            } else {
                byte meta = (byte) tileEntity.worldObj.getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
                spawnerType = EnumSpawner.getById(meta);
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
        return GSMobSpawn.getMobEntityForSpawner(this.tileEntity.worldObj, getSpawnerType(), 
                this.tileEntity.xCoord, this.tileEntity.yCoord, this.tileEntity.zCoord);
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
