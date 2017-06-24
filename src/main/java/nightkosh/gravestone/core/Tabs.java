package nightkosh.gravestone.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.block.enums.EnumGraves;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Tabs {
    public static CreativeTabs gravesTab;

    public static void registration() {
        gravesTab = new CreativeTabs("tabGSGraveStone") {
            @Override
            public ItemStack getIconItemStack() {
                return new ItemStack(GSBlock.GRAVE_STONE, 1, EnumGraves.STONE_VERTICAL_PLATE.ordinal());
            }

            @Override
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
                return new ItemStack(GSBlock.GRAVE_STONE, 1, EnumGraves.STONE_VERTICAL_PLATE.ordinal());
            }
        };

        GraveStoneAPI.gravesTab = gravesTab;
    }
}
