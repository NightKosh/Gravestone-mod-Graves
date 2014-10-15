package gravestone.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.core.GSTabs;
import gravestone.item.corpse.CorpseHelper;
import gravestone.item.enums.EnumCorpse;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemGSCorpse extends Item {

    private static IIcon[] icons;

    public ItemGSCorpse() {
        super();
        setCreativeTab(GSTabs.otherItemsTab);
        setUnlocalizedName("Corpse");
        this.setHasSubtypes(true);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        } else {
            CorpseHelper.addInfo(stack.getItemDamage(), list, stack.stackTagCompound);
        }
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye
     * returns 16 items)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int damage = 0; damage < EnumCorpse.values().length; ++damage) {
            list.add(CorpseHelper.getDefaultCorpse(item, damage));
        }
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        return EnumCorpse.getById((byte) itemStack.getItemDamage()).getName();
    }
}
