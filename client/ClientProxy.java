package GraveStone.client;

import GraveStone.CommonProxy;
import GraveStone.GraveStoneConfig;
import GraveStone.ItemGSGraveStoneRenderer;
import GraveStone.ItemGSMemorialRenderer;
import GraveStone.tileentity.TileEntityGSGraveStone;
import GraveStone.tileentity.TileEntityGSGraveStoneRenderer;
import GraveStone.tileentity.TileEntityGSMemorial;
import GraveStone.tileentity.TileEntityGSMemorialRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        // register GraveStone as tile entity for spawer abilitys
        ClientRegistry.registerTileEntity(TileEntityGSGraveStone.class, "GSGraveStone", new TileEntityGSGraveStoneRenderer());
        MinecraftForgeClient.registerItemRenderer(GraveStoneConfig.graveStoneID, new ItemGSGraveStoneRenderer());
        
        
        // register GraveStone as tile entity for spawer abilitys
        ClientRegistry.registerTileEntity(TileEntityGSMemorial.class, "GSMemorial", new TileEntityGSMemorialRenderer());
        MinecraftForgeClient.registerItemRenderer(GraveStoneConfig.memorialID, new ItemGSMemorialRenderer());
    }
}
