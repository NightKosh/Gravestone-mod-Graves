package gravestone.core.proxy;

import gravestone.block.enums.EnumHauntedChest;
import gravestone.block.enums.EnumPileOfBones;
import gravestone.block.enums.EnumSkullCandle;
import gravestone.block.enums.EnumSpawner;
import gravestone.core.GSBlock;
import gravestone.core.GSItem;
import gravestone.core.Resources;
import gravestone.core.ResourcesModels;
import gravestone.core.event.GSRenderEventHandler;
import gravestone.entity.EntityRaven;
import gravestone.entity.helper.EntityGroupOfGravesMobSpawnerHelper;
import gravestone.entity.monster.*;
import gravestone.entity.monster.EntitySkullCrawler.SkullCrawlerType;
import gravestone.gui.GSGraveTextGui;
import gravestone.item.ItemGSMonsterPlacer;
import gravestone.item.enums.EnumCorpse;
import gravestone.models.entity.ModelUndeadCat;
import gravestone.models.entity.ModelUndeadDog;
import gravestone.renderer.entity.*;
import gravestone.renderer.item.*;
import gravestone.renderer.tileentity.*;
import gravestone.structures.village.undertaker.VillageHandlerGSUndertaker;
import gravestone.tileentity.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
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

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGSAltar.class, new TileEntityGSRenderAltar());


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
        registerModelsForTEBlocks(1, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGSGraveStone.class);
        //memorials
        registerModelsForTEBlocks(1, GSBlock.memorial, ResourcesModels.memorialModel, TileEntityGSMemorial.class);
        //spawners
        registerModelsForTEBlocks(EnumSpawner.values().length, GSBlock.spawner, ResourcesModels.spawnerModel, TileEntityGSSpawner.class);
        //traps
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.trap), 0, ResourcesModels.nightStoneModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.trap), 1, ResourcesModels.thunderStoneModel);
        ModelBakery.addVariantName(Item.getItemFromBlock(GSBlock.trap), "gravestone:GSTrap_night_stone", "gravestone:GSTrap_thunder_stone");
        //piles of bones
        registerModelsForTEBlocks(EnumPileOfBones.values().length, GSBlock.pileOfBones, ResourcesModels.pileOfBonesModel, TileEntityGSPileOfBones.class);
        //bone blocks
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.boneBlock), 0, ResourcesModels.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.boneBlock), 1, ResourcesModels.boneBlockWithSkullModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.boneBlock), 2, ResourcesModels.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.boneBlock), 3, ResourcesModels.boneBlockWithSkullModel);
        ModelBakery.addVariantName(Item.getItemFromBlock(GSBlock.boneBlock), "gravestone:GSBoneBlock", "gravestone:GSBoneBlock_with_skull",
                "gravestone:GSBoneBlock", "gravestone:GSBoneBlock_with_skull");

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.boneSlab), 0, ResourcesModels.boneSlabModel);

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.boneStairs), 0, ResourcesModels.boneStairsModel);
        //haunted chest
        registerModelsForTEBlocks(EnumHauntedChest.values().length, GSBlock.hauntedChest, ResourcesModels.hauntedChestModel, TileEntityGSHauntedChest.class);
        //altar
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.altar), 0, ResourcesModels.altarModel);

        //candle
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.candle), 0, ResourcesModels.candleModel);
        ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(GSBlock.candle), 0, TileEntityGSCandle.class);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.candle), 0, ResourcesModels.candleModel);
        //skull candle
        registerModelsForTEBlocks(EnumSkullCandle.values().length, GSBlock.skullCandle, ResourcesModels.skullCandleModel, TileEntityGSSkullCandle.class);
    }

    private void registerModelsForTEBlocks(int length, Block block, ModelResourceLocation model, Class TEClass) {
        for (int num = 0; num < length; num++) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), num, model);
            ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(block), num, TEClass);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), num, model);
        }
    }

    @Override
    public void registerItemsModels() {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GSItem.chisel, 0, ResourcesModels.chiselModel);

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GSItem.corpse, EnumCorpse.VILLAGER.ordinal(), ResourcesModels.CORPSE_VILLAGER);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GSItem.corpse, EnumCorpse.DOG.ordinal(), ResourcesModels.CORPSE_DOG);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GSItem.corpse, EnumCorpse.CAT.ordinal(), ResourcesModels.CORPSE_CAT);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GSItem.corpse, EnumCorpse.HORSE.ordinal(), ResourcesModels.CORPSE_HORSE);
        ModelBakery.addVariantName(GSItem.corpse, "gravestone:GSCorpseVillager", "gravestone:GSCorpseDog", "gravestone:GSCorpseCat", "gravestone:GSCorpseHorse");

        for (ItemGSMonsterPlacer.EnumEggs egg : ItemGSMonsterPlacer.EnumEggs.values()) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GSItem.spawnEgg, egg.ordinal(), ResourcesModels.spawnEggModel);
        }
    }
}
