package nightkosh.gravestone;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.api.IGraveStoneHelper;
import nightkosh.gravestone.core.GSTileEntity;
import nightkosh.gravestone.core.proxy.CommonProxy;
import nightkosh.gravestone.helper.GraveGenerationHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
//@Mod(modid = "gravestone", name = "GraveStone", version = ModInfo.VERSION, updateJSON = "https://raw.githubusercontent.com/NightKosh/GraveStone-mod/master/update.json")
public class ModGraveStoneOld {
//TODO remove !!!!!!!!!!!!!!!!!!!!!!!

    //@Instance("GraveStone")
    public static ModGraveStoneOld instance;
    //@SidedProxy(clientSide = "nightkosh.gravestone.core.proxy.ClientProxy", serverSide = "nightkosh.gravestone.core.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static final IGraveStoneHelper gravestoneHelper = GraveGenerationHelper.INSTANCE;

    public ModGraveStoneOld() {
        instance = this;
    }

    //@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // API
        GraveStoneAPI.graveStone = gravestoneHelper;

        GSTileEntity.registration();
    }

    //@Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        proxy.registerTEISR();
    }
}