package GraveStone.client;

import GraveStone.GSCommonProxy;
import GraveStone.GraveStoneConfig;
import GraveStone.Resources;
import GraveStone.entity.EntitySkeletonCat;
import GraveStone.entity.EntitySkeletonDog;
import GraveStone.entity.EntityZombieCat;
import GraveStone.renderer.item.ItemGSGraveStoneRenderer;
import GraveStone.renderer.item.ItemGSMemorialRenderer;
import GraveStone.entity.EntityZombieDog;
import GraveStone.models.entity.ModelUndeadCat;
import GraveStone.models.entity.ModelUndeadDog;
import GraveStone.renderer.entity.RenderUndeadCat;
import GraveStone.renderer.entity.RenderUndeadDog;
import GraveStone.tileentity.TileEntityGSGraveStone;
import GraveStone.renderer.tileentity.TileEntityGSGraveStoneRenderer;
import GraveStone.tileentity.TileEntityGSMemorial;
import GraveStone.renderer.tileentity.TileEntityGSMemorialRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClientProxy extends GSCommonProxy {

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
    }

    public void registerVillagers() {
        VillagerRegistry.instance().registerVillagerSkin(385, Resources.UNDARTAKER);
    }
}
