package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import gravestone.block.enums.EnumBoneBlock;
import gravestone.core.Resources;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSBoneBlock extends Block {

    @SideOnly(Side.CLIENT)
    private Icon skullIcon;

    public BlockGSBoneBlock(int id) {
        super(id, Material.rock);
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("bone_block");
        this.setHardness(2F);
        this.setResistance(2F);
        this.setCreativeTab(ModGraveStone.creativeTab);
    }

    /**
     * When this method is called, your block should register all the icons it
     * needs with the given IconRegister. This is the only chance you get to
     * register icons.
     */
    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Resources.BONE_BLOCK);
        this.skullIcon = iconRegister.registerIcon(Resources.SKULL_BONE_BLOCK);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture.
     */
    @Override
    public Icon getIcon(int side, int metadata) {
        if (metadata == 1 || metadata == 3) {
            return skullIcon;
        } else {
            return blockIcon;
        }
    }

    /**
     * Returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List list) {
        for (byte meta = 0; meta < EnumBoneBlock.values().length; meta++) {
            list.add(new ItemStack(id, 1, meta));
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and
     * wood.
     */
    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }
}
