package gravestone.core.proxy;

import gravestone.config.GraveStoneConfig;
import gravestone.core.Resources;
import gravestone.entity.EntitySkeletonCat;
import gravestone.entity.EntitySkeletonDog;
import gravestone.entity.EntityZombieCat;
import gravestone.renderer.item.ItemGSGraveStoneRenderer;
import gravestone.renderer.item.ItemGSMemorialRenderer;
import gravestone.entity.EntityZombieDog;
import gravestone.models.entity.ModelUndeadCat;
import gravestone.models.entity.ModelUndeadDog;
import gravestone.renderer.entity.RenderUndeadCat;
import gravestone.renderer.entity.RenderUndeadDog;
import gravestone.tileentity.TileEntityGSGraveStone;
import gravestone.renderer.tileentity.TileEntityGSGraveStoneRenderer;
import gravestone.tileentity.TileEntityGSMemorial;
import gravestone.renderer.tileentity.TileEntityGSMemorialRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import gravestone.renderer.tileentity.TileEntityGSGhostlyChestRenderer;
import gravestone.tileentity.TileEntityGSGhostlyChest;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        // register GraveStone renderer
        ClientRegistry.registerTileEntity(TileEntityGSGraveStone.class, "GSGraveStone", new TileEntityGSGraveStoneRenderer());
        MinecraftForgeClient.registerItemRenderer(GraveStoneConfig.graveStoneID, new ItemGSGraveStoneRenderer());

        // register GraveStone renderer
        ClientRegistry.registerTileEntity(TileEntityGSMemorial.class, "GSMemorial", new TileEntityGSMemorialRenderer());
        MinecraftForgeClient.registerItemRenderer(GraveStoneConfig.memorialID, new ItemGSMemorialRenderer());

        // zombie dog
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieDog.class, new RenderUndeadDog(new ModelUndeadDog(), new ModelUndeadDog()));

        // zombie cat
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieCat.class, new RenderUndeadCat(new ModelUndeadCat(), 0));

        // skeleton dog
        RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonDog.class, new RenderUndeadDog(new ModelUndeadDog(), new ModelUndeadDog()));

        // zombie cat
        RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonCat.class, new RenderUndeadCat(new ModelUndeadCat(), 0));
        
        // register GhostlyChest renderer
        ClientRegistry.registerTileEntity(TileEntityGSGhostlyChest.class, "GSGhostlyChest", new TileEntityGSGhostlyChestRenderer());
    }

    @Override
    public void registerVillagers() {
        VillagerRegistry.instance().registerVillagerSkin(385, Resources.UNDARTAKER);
    }

    @Override
    public String getLocalizedString(String str) {
        return LanguageRegistry.instance().getStringLocalization(str);
    }

    @Override
    public String getLocalizedEntityName(String name) {
        return StatCollector.translateToLocal(name);
    }
}
