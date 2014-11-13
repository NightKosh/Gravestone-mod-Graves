package gravestone.potion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.core.Resources;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CursePotion extends Potion {

    public CursePotion(int id, boolean isBadEffect, int liquidColor) {
        super(id, isBadEffect, liquidColor);
        setIconIndex(0, 0);
    }

    @Override
    public void performEffect(EntityLivingBase entity, int p_76394_2_) {

    }

    @Override
    public String getName() {
        return "Curse";
    }

    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(Resources.POTIONS);
        return super.getStatusIconIndex();
    }
}
