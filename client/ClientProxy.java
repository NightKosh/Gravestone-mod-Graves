package net.minecraft.GraveStone.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.GraveStone.CommonProxy;
import net.minecraft.GraveStone.GraveStoneConfig;
import net.minecraft.GraveStone.ItemGSGraveStoneRenderer;
import net.minecraft.GraveStone.ItemGSMemorialRenderer;
import net.minecraft.GraveStone.tileentity.TileEntityGSGraveStone;
import net.minecraft.GraveStone.tileentity.TileEntityGSGraveStoneRenderer;
import net.minecraft.GraveStone.tileentity.TileEntityGSMemorial;
import net.minecraft.GraveStone.tileentity.TileEntityGSMemorialRenderer;
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
