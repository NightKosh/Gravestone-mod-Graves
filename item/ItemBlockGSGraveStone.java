package net.minecraft.GraveStone.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemBlockGSGraveStone extends ItemBlock {

    private final static String[] subNames = {
        "Gravestone", "Cross Gravestone", "Gravestone Plate"
    };

    public ItemBlockGSGraveStone(int id) {
        super(id);
        setHasSubtypes(true);
        setUnlocalizedName("Gravestone");
    }

    @Override
    public int getMetadata(int damageValue) {
        return damageValue - (damageValue % 4);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName() + "." + subNames[(int) (itemStack.getItemDamage() / 4)];
    }

    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }

        //stack.stackTagCompound.setString("DeathText", "Qwerty");
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        if (stack.stackTagCompound.hasKey("DeathText")) {
            list.add(stack.stackTagCompound.getString("DeathText"));
        }
    }

    /**
     * Called to actually place the block, after the location is determined
     * and all permission checks have been made.
     *
     * @param stack The item stack that was used to place the block. This can be changed inside the method.
     * @param player The player who is placing the block. Can be null if the block is not being placed by a player.
     * @param side The side the player (or machine) right-clicked on.
     */
    /*
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        if (!world.setBlock(x, y, z, GraveStoneConfig.graveStoneID, metadata, 3)) {
            return false;
        }

        if (world.getBlockId(x, y, z) == GraveStoneConfig.graveStoneID) {
            Block.blocksList[GraveStoneConfig.graveStoneID].onBlockPlacedBy(world, x, y, z, player, stack);
            Block.blocksList[GraveStoneConfig.graveStoneID].onPostBlockPlaced(world, x, y, z, metadata);
        }
        
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null && stack.stackTagCompound != null && stack.stackTagCompound.hasKey("DeathText")) {
            tileEntity.setDeathText(stack.stackTagCompound.getString("DeathText"));
        }
        return true;
    }
     * 
     */
}
