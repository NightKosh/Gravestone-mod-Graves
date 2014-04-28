package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import gravestone.core.Resources;
import gravestone.item.CorpseHelper;
import gravestone.item.ItemGSCorpse;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSAltar extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon topTexture;
    @SideOnly(Side.CLIENT)
    private IIcon bottomTexture;

    public BlockGSAltar() {
        super(Material.rock);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        this.setLightOpacity(0);
        this.setBlockName("Altar");
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.setHarvestLevel("pickaxe", 2);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        ItemStack stack = player.getCurrentEquippedItem();
        if (stack != null && stack.getItem() instanceof ItemGSCorpse && CorpseHelper.canSpawnMob(player, stack.getItemDamage())) {
            CorpseHelper.spawnMob(stack.getItemDamage(), world, x, y, z, stack.stackTagCompound, player);
            CorpseHelper.getExperience(player, stack.getItemDamage());
            player.destroyCurrentEquippedItem();
            return true;
        }
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False
     * (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 0 ? this.bottomTexture : (side == 1 ? this.topTexture : this.blockIcon);
    }

    @SideOnly(Side.CLIENT)
    /**
     * When this method is called, your block should register all the icons it
     * needs with the given IconRegister. This is the only chance you get to
     * register icons.
     */
    @Override
    public void registerBlockIcons(IIconRegister register) {
        this.blockIcon = register.registerIcon(Resources.ALTAR_SIDE);
        this.topTexture = register.registerIcon(Resources.ALTAR_TOP);
        this.bottomTexture = register.registerIcon(Resources.ALTAR_BOTTOM);
    }
}
