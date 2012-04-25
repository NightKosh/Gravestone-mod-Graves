package GraveStone.client;

import GraveStone.CommonProxy;
import GraveStone.GraveStoneConfig;
import GraveStone.entity.EntityZombieCat;
import GraveStone.renderer.item.ItemGSGraveStoneRenderer;
import GraveStone.renderer.item.ItemGSMemorialRenderer;
import GraveStone.entity.EntityZombieDog;
import GraveStone.models.entity.ModelZombieCat;
import GraveStone.models.entity.ModelZombieDog;
import GraveStone.renderer.entity.RenderZombieCat;
import GraveStone.renderer.entity.RenderZombieDog;
import GraveStone.tileentity.TileEntityGSGraveStone;
import GraveStone.renderer.tileentity.TileEntityGSGraveStoneRenderer;
import GraveStone.tileentity.TileEntityGSMemorial;
import GraveStone.renderer.tileentity.TileEntityGSMemorialRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

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
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieDog.class, new RenderZombieDog(new ModelZombieDog(), new ModelZombieDog()));
        
        // zombie cat
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieCat.class, new RenderZombieCat(new ModelZombieCat(), 0));
    }
}
