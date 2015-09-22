package gravestone.core.proxy;

import gravestone.block.enums.EnumHauntedChest;
import gravestone.block.enums.EnumPileOfBones;
import gravestone.block.enums.EnumSkullCandle;
import gravestone.block.enums.EnumSpawner;
import gravestone.core.GSBlock;
import gravestone.core.GSItem;
import gravestone.core.Resources;
import gravestone.core.ResourcesModes;
import gravestone.core.event.GSRenderEventHandler;
import gravestone.entity.EntityRaven;
import gravestone.entity.helper.EntityGroupOfGravesMobSpawnerHelper;
import gravestone.entity.monster.*;
import gravestone.entity.monster.EntitySkullCrawler.SkullCrawlerType;
import gravestone.gui.GSGraveTextGui;
import gravestone.item.ItemGSMonsterPlacer;
import gravestone.models.entity.ModelUndeadCat;
import gravestone.models.entity.ModelUndeadDog;
import gravestone.renderer.entity.*;
import gravestone.renderer.item.*;
import gravestone.renderer.tileentity.*;
import gravestone.structures.village.undertaker.VillageHandlerGSUndertaker;
import gravestone.tileentity.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
//        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GSBlock.candle), new ItemGSCandleRenderer());

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

        // Skeleton
        RenderingRegistry.registerEntityRenderingHandler(EntityGSSkeleton.class, new RenderGSSkeleton(Minecraft.getMinecraft().getRenderManager()));
        // Horses
        RenderingRegistry.registerEntityRenderingHandler(EntityUndeadHorse.class, new RenderUndeadHorse(Minecraft.getMinecraft().getRenderManager(), new ModelHorse(), 0));

        // raven
        RenderingRegistry.registerEntityRenderingHandler(EntityRaven.class, new RenderRaven(Minecraft.getMinecraft().getRenderManager()));

        // Spawner Helper
        RenderingRegistry.registerEntityRenderingHandler(EntityGroupOfGravesMobSpawnerHelper.class, new RenderSpawnerHelper(Minecraft.getMinecraft().getRenderManager()));
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
    public void openGraveTextGui(TileEntityGSGrave tileEntity) {
        FMLClientHandler.instance().getClient().displayGuiScreen(new GSGraveTextGui(tileEntity));
    }

    @Override
    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new GSRenderEventHandler());
    }

    @Override
    public void registerBlocksModels() {
        //graves
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.graveStone), 0, ResourcesModes.graveStoneModel);
        ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(GSBlock.graveStone), 0, TileEntityGSGraveStone.class);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.graveStone), 0, ResourcesModes.graveStoneModel);
        //memorials
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.memorial), 0, ResourcesModes.memorialModel);
        ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(GSBlock.memorial), 0, TileEntityGSMemorial.class);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.memorial), 0, ResourcesModes.memorialModel);
        //spawners
        for (int num = 0; num < EnumSpawner.values().length; num++) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.spawner), num, ResourcesModes.spawnerModel);
            ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(GSBlock.spawner), num, TileEntityGSSpawner.class);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.spawner), num, ResourcesModes.spawnerModel);
        }
        //traps
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.trap), 0, ResourcesModes.trapModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.trap), 1, ResourcesModes.trapModel);
        ModelBakery.addVariantName(Item.getItemFromBlock(GSBlock.trap), new String[]{"gravestone:GSTrap_night_stone", "gravestone:GSTrap_thunder_stone"});
        //piles of bones
        for (int num = 0; num < EnumPileOfBones.values().length; num++) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.pileOfBones), num, ResourcesModes.pileOfBonesModel);
            ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(GSBlock.pileOfBones), num, TileEntityGSPileOfBones.class);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.pileOfBones), num, ResourcesModes.pileOfBonesModel);
        }
        //bone blocks
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.boneBlock), 0, ResourcesModes.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.boneBlock), 1, ResourcesModes.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.boneBlock), 2, ResourcesModes.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.boneBlock), 3, ResourcesModes.boneBlockModel);
        ModelBakery.addVariantName(Item.getItemFromBlock(GSBlock.boneBlock), new String[]{"gravestone:GSBoneBlock", "gravestone:GSBoneBlock_with_skull",
                "gravestone:GSBoneBlock_with_crawler", "gravestone:GSBoneBlock_with_skull_and_crawler"});

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.boneSlab), 0, ResourcesModes.boneSlabModel);

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.boneStairs), 0, ResourcesModes.boneStairsModel);
        //haunted chest
        for (int num = 0; num < EnumHauntedChest.values().length; num++) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.hauntedChest), num, ResourcesModes.hauntedChestModel);
            ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(GSBlock.hauntedChest), num, TileEntityGSHauntedChest.class);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.hauntedChest), num, ResourcesModes.hauntedChestModel);
        }
        //altar
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.altar), 0, ResourcesModes.altarModel);

        //candle
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.candle), 0, ResourcesModes.candleModel);
        ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(GSBlock.candle), 0, TileEntityGSCandle.class);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.candle), 0, ResourcesModes.candleModel);
        //skull candle
        for (int num = 0; num < EnumSkullCandle.values().length; num++) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.skullCandle), num, ResourcesModes.skullCandleModel);
            ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(GSBlock.skullCandle), num, TileEntityGSSkullCandle.class);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.skullCandle), num, ResourcesModes.skullCandleModel);
        }
    }

    @Override
    public void registerItemsModels() {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GSItem.chisel, 0, ResourcesModes.chiselModel);

        for (ItemGSMonsterPlacer.EnumEggs egg : ItemGSMonsterPlacer.EnumEggs.values()) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GSItem.spawnEgg, egg.ordinal(), ResourcesModes.spawnEggModel);
        }
    }
}
