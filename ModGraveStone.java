package gravestone;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.config.GraveStoneConfig;
import gravestone.core.*;
import gravestone.core.commands.GSCommands;
import gravestone.core.compatibility.GSCompatibility;
import gravestone.core.event.GSEventsHook;
import gravestone.core.localization.GraveStoneLocalizationHandler;
import gravestone.core.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class ModGraveStone {

    @Instance("GraveStone")
    public static ModGraveStone instance;
    @SidedProxy(clientSide = "gravestone.core.proxy.ClientProxy", serverSide = "gravestone.core.proxy.CommonProxy")
    public static CommonProxy proxy;
    // creative tab
    public static CreativeTabs creativeTab;

    public ModGraveStone() {
        instance = this;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GraveStoneConfig.getInstance(event.getModConfigurationDirectory().getAbsolutePath() + "/GraveStoneMod/", "GraveStone.cfg");
        GraveStoneLocalizationHandler.init();
        GSStructures.preInit();
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        // register death event
        MinecraftForge.EVENT_BUS.register(new GSEventsHook());

        // creative tab
        creativeTab = new CreativeTabs("tabGraveStone") {
            @Override
            public ItemStack getIconItemStack() {
                ItemStack stack = new ItemStack(GSBlock.graveStone, 1, 0);
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte("GraveType", (byte) 0);
                stack.setTagCompound(nbt);
                return stack;
            }

            @Override
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return Item.getItemFromBlock(GSBlock.graveStone);
            }
        };

        // blocks registration
        GSBlock.registration();

        // items registration
        GSItem.registration();

        // reciepes registration
        GSReciepes.registration();

        // tileEntities registration
        GSTileEntity.registration();

        // Gui registration
        GSGui.registration();

        // register structures
        GSStructures.getInstance();

        // register entitys
        GSEntity.getInstance();
        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        GSCompatibility.getInstance().checkMods();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        GSCommands.getInstance(event);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
//        NetworkRegistry.INSTANCE.newChannel("GSDeathText", new GSPacketHandler());
    }
}