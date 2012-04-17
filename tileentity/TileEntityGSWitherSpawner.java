package net.minecraft.GraveStone.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGSWitherSpawner extends TileEntity {

    private static final String MOB_ID = "WitherBoss";
    protected String mobID = "WitherSkull";
    private static final int RANGE = 8;
    private int delay;

    public TileEntityGSWitherSpawner() {
        delay = 50;
    }
    
    public void updateEntity() {
        if (anyPlayerInRange()) {
            delay--;

            if (worldObj.isRemote) {
                double x = xCoord + worldObj.rand.nextFloat();
                double y = yCoord + worldObj.rand.nextFloat();
                double z = zCoord + worldObj.rand.nextFloat();
                worldObj.spawnParticle("smoke", x, y, z, 0.0D, 0.0D, 0.0D);
                worldObj.spawnParticle("flame", x, y, z, 0.0D, 0.0D, 0.0D);

            } else if (delay <= 0) {
                Entity entity = EntityList.createEntityByName(MOB_ID, worldObj);

                double x = xCoord + 0.5D;
                double y = yCoord + 0.5D;
                double z = zCoord + 0.5D;

                entity.setLocationAndAngles(x, y, z, worldObj.rand.nextFloat() * 360.0F, 0.0F);

                worldObj.spawnEntityInWorld(entity);
                worldObj.playAuxSFX(2004, xCoord, yCoord, zCoord, 0);

                worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
                worldObj.setBlock(xCoord, yCoord, zCoord, 0);
            }
        }
    }
    
    public boolean anyPlayerInRange() {
        return worldObj.getClosestPlayer(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, RANGE) != null;
    }
}
