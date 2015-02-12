package gravestone.core.proxy;

import gravestone.core.GSBlock;
import gravestone.core.GSItem;
import gravestone.core.Resources;
import gravestone.core.event.GSClientTickEventHandler;
import gravestone.core.event.GSRenderEventHandler;
import gravestone.entity.monster.*;
import gravestone.entity.monster.EntitySkullCrawler.SkullCrawlerType;
import gravestone.gui.GSGuiGrave;
import gravestone.models.entity.ModelUndeadCat;
import gravestone.models.entity.ModelUndeadDog;
import gravestone.renderer.entity.RenderAltar;
import gravestone.renderer.entity.RenderSkullCrawler;
import gravestone.renderer.entity.RenderUndeadCat;
import gravestone.renderer.entity.RenderUndeadDog;
import gravestone.renderer.item.*;
import gravestone.renderer.tileentity.*;
import gravestone.structures.village.undertaker.VillageHandlerGSUndertaker;
import gravestone.tileentity.*;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import org.apache.commons.lang3.StringUtils;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        // blocks renderers
        registerBlocksRenderers();

        // Mobs renderers
        registerMobsRenderers();
    }

    private void registerBlocksRenderers() {
        // register GraveStone renderer
        ClientRegistry.registerTileEntity(TileEntityGSGraveStone.class, "GSGraveStone", new TileEntityGSGraveStoneRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GSBlock.graveStone), new ItemGSGraveStoneRenderer());

        // register GraveStone renderer
        ClientRegistry.registerTileEntity(TileEntityGSMemorial.class, "GSMemorial", new TileEntityGSMemorialRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GSBlock.memorial), new ItemGSMemorialRenderer());

        // spawner renderer
        ClientRegistry.registerTileEntity(TileEntityGSSpawner.class, "GSSpawner", new TileEntityGSSpawnerRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GSBlock.spawner), new ItemGSSpawnerRenderer());

        // register HauntedChest renderer
        ClientRegistry.registerTileEntity(TileEntityGSHauntedChest.class, "GSHauntedChest", new TileEntityGSHauntedChestRenderer());

        // register SkullCandle renderer
        ClientRegistry.registerTileEntity(TileEntityGSSkullCandle.class, "GSSkullCandle", new TileEntityGSSkullCandleRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GSBlock.skullCandle), new ItemGSSkullCandleRenderer());

        // candle
        ClientRegistry.registerTileEntity(TileEntityGSCandle.class, "GSCandle", new TileEntityGSCandleRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GSBlock.candle), new ItemGSCandleRenderer());

        // pile of bones
        ClientRegistry.registerTileEntity(TileEntityGSPileOfBones.class, "GSPileOfBones", new TileEntityGSPileOfBonesRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GSBlock.pileOfBones), new ItemGSPileOfBonesRenderer());

        // corpses
        MinecraftForgeClient.registerItemRenderer(GSItem.corpse, new ItemGSCorpseRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGSAltar.class, new RenderAltar());
    }

    private void registerMobsRenderers() {
        // zombie dog
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieDog.class, new RenderUndeadDog(Minecraft.getMinecraft().getRenderManager(), new ModelUndeadDog(), new ModelUndeadDog()));

        // zombie cat
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieCat.class, new RenderUndeadCat(Minecraft.getMinecraft().getRenderManager(), new ModelUndeadCat(), 0));

        // skeleton dog
        RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonDog.class, new RenderUndeadDog(Minecraft.getMinecraft().getRenderManager(), new ModelUndeadDog(), new ModelUndeadDog()));

        // zombie cat
        RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonCat.class, new RenderUndeadCat(Minecraft.getMinecraft().getRenderManager(), new ModelUndeadCat(), 0));

        // skull crawler
        RenderingRegistry.registerEntityRenderingHandler(EntitySkullCrawler.class, new RenderSkullCrawler(SkullCrawlerType.skeleton, Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityWitherSkullCrawler.class, new RenderSkullCrawler(SkullCrawlerType.wither, Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieSkullCrawler.class, new RenderSkullCrawler(SkullCrawlerType.zombie, Minecraft.getMinecraft().getRenderManager()));
    }

    @Override
    public void registerVillagers() {
        VillagerRegistry.instance().registerVillagerSkin(VillageHandlerGSUndertaker.UNDERTAKER_ID, Resources.UNDERTAKER);
    }

    @Override
    public String getLocalizedString(String str) {
        String localizedString = null;
        try {
            localizedString = LanguageRegistry.instance().getStringLocalization(str);
        } catch (Exception e) {
        }
        if (StringUtils.isBlank(localizedString)) {
            return LanguageRegistry.instance().getStringLocalization(str, "en_US");
        } else {
            return localizedString;
        }
    }

    @Override
    public String getLocalizedEntityName(String name) {
        return StatCollector.translateToLocal(name);
    }

    @Override
    public void openGraveGui(TileEntityGSGrave tileEntity) {
        FMLClientHandler.instance().getClient().displayGuiScreen(new GSGuiGrave(tileEntity));
    }

    @Override
    public void registerHandlers() {
        FMLCommonHandler.instance().bus().register(new GSClientTickEventHandler());
        MinecraftForge.EVENT_BUS.register(new GSRenderEventHandler());
    }
}
