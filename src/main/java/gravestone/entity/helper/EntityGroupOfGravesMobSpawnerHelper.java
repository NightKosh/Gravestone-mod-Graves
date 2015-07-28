package gravestone.entity.helper;


import gravestone.tileentity.GSGraveStoneSpawn;
import gravestone.tileentity.ISpawnerEntity;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityGroupOfGravesMobSpawnerHelper extends Entity implements ISpawnerEntity {

    private List<TileEntityGSGraveStone> gravesTEList;
    private GSGraveStoneSpawn graveStoneSpawn;
    private boolean canMobsBeSpawned;

    public EntityGroupOfGravesMobSpawnerHelper(World worldIn) {
        super(worldIn);
        graveStoneSpawn = new GSGraveStoneSpawn(this);
    }

    public void addGraveTe(TileEntityGSGraveStone te) {
        gravesTEList.add(te);
    }

    public boolean canMobsBeSpawned() {
        return canMobsBeSpawned;
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

        if (!getWorld().isRemote) {
            if (gravesTEList != null && gravesTEList.isEmpty()) {
                this.setDead();
            } else {
                canMobsBeSpawned = graveStoneSpawn.isMobSpawnAllowed();
                graveStoneSpawn.updateDelay();
            }
        }
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {
        NBTTagList tagList = tagCompund.getTagList("gravesList", 10);
        if (tagList != null && !tagList.hasNoTags()) {
            gravesTEList = new ArrayList<TileEntityGSGraveStone>(tagList.tagCount());
            for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound posTag = (NBTTagCompound) tagList.get(i);
                if (posTag != null && posTag.hasKey("x") && posTag.hasKey("y") && posTag.hasKey("z")) {
                    BlockPos pos = new BlockPos(posTag.getInteger("x"), posTag.getInteger("y"), posTag.getInteger("z"));
                    TileEntity te = worldObj.getTileEntity(pos);
                    if (te != null && te instanceof TileEntityGSGraveStone) {
                        gravesTEList.add((TileEntityGSGraveStone) te);
                    }
                }
            }
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {
        if (gravesTEList != null && !gravesTEList.isEmpty()) {
            NBTTagList tagList = new NBTTagList();
            for (TileEntityGSGraveStone te : gravesTEList) {
                if (te != null) {
                    BlockPos pos = te.getPos();
                    NBTTagCompound posTag = new NBTTagCompound();
                    posTag.setInteger("x", pos.getX());
                    posTag.setInteger("y", pos.getY());
                    posTag.setInteger("z", pos.getZ());
                    tagList.appendTag(posTag);
                }
            }

            tagCompound.setTag("gravesList", tagList);
        }
    }

    @Override
    public BlockPos getPos() {
        return this.getPosition();
    }

    @Override
    public World getWorld() {
        return this.worldObj;
    }

    @Override
    public boolean haveSpawnerHelper() {
        return false;
    }

    @Override
    public EntityGroupOfGravesMobSpawnerHelper getSpawnerHelper() {
        return null;
    }
}
