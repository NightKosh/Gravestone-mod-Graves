package gravestone.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import gravestone.core.GSBlock;
import gravestone.core.Resources;
import gravestone.entity.monster.*;
import gravestone.entity.monster.EntitySkullCrawler.SkullCrawlerType;
import gravestone.models.entity.ModelUndeadCat;
import gravestone.models.entity.ModelUndeadDog;
import gravestone.renderer.entity.RenderSkullCrawler;
import gravestone.renderer.entity.RenderUndeadCat;
import gravestone.renderer.entity.RenderUndeadDog;
import gravestone.renderer.item.*;
import gravestone.renderer.tileentity.*;
import gravestone.tileentity.*;
import net.minecraft.item.Item;
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
    }

    private void registerMobsRenderers() {
        // zombie dog
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieDog.class, new RenderUndeadDog(new ModelUndeadDog(), new ModelUndeadDog()));

        // zombie cat
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieCat.class, new RenderUndeadCat(new ModelUndeadCat(), 0));

        // skeleton dog
        RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonDog.class, new RenderUndeadDog(new ModelUndeadDog(), new ModelUndeadDog()));

        // zombie cat
        RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonCat.class, new RenderUndeadCat(new ModelUndeadCat(), 0));

        // skull crawler
        RenderingRegistry.registerEntityRenderingHandler(EntitySkullCrawler.class, new RenderSkullCrawler(SkullCrawlerType.skeleton));
        RenderingRegistry.registerEntityRenderingHandler(EntityWitherSkullCrawler.class, new RenderSkullCrawler(SkullCrawlerType.wither));
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieSkullCrawler.class, new RenderSkullCrawler(SkullCrawlerType.zombie));
    }

    @Override
    public void registerVillagers() {
        VillagerRegistry.instance().registerVillagerSkin(385, Resources.UNDARTAKER);
    }

    @Override
    public String getLocalizedString(String str) {
        String localizedString = LanguageRegistry.instance().getStringLocalization(str);
        if (localizedString.length() == 0) {
            return LanguageRegistry.instance().getStringLocalization(str, "en_US");
        } else {
            return localizedString;
        }
    }

    @Override
    public String getLocalizedEntityName(String name) {
        return StatCollector.translateToLocal(name);
    }
}
