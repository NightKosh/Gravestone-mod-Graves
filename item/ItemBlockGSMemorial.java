
package GraveStone.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemBlockGSMemorial extends ItemBlock {

    private final static String[] subNames = {
        "Cross Memorial"
    };

    public ItemBlockGSMemorial(int id) {
        super(id);
        setHasSubtypes(true);
        setUnlocalizedName("multiBlock");
    }

    @Override
    public int getMetadata(int damageValue) {
        return 0;//damageValue;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        byte graveType = 0;
        if (itemStack.stackTagCompound != null) {
            graveType = itemStack.stackTagCompound.getByte("GraveType");
        }
        return getUnlocalizedName() + "." + subNames[graveType];
    }

    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        if (stack.stackTagCompound.hasKey("DeathText") && !stack.stackTagCompound.getString("DeathText").equals("")) {
            list.add(stack.stackTagCompound.getString("DeathText"));
        }
        if (stack.stackTagCompound.hasKey("GraveType")) {
            //list.add(" " + stack.stackTagCompound.getByte("GraveType"));
        }
    }
}
