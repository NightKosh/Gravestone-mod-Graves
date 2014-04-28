package gravestone.core.event;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gravestone.block.BlockGSGraveStone.EnumGraveType;
import gravestone.block.GraveStoneHelper;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSBlock;
import gravestone.core.GSMobSpawn;
import gravestone.entity.monster.EntitySkullCrawler;
import gravestone.entity.monster.EntityWitherSkullCrawler;
import gravestone.entity.monster.EntityZombieSkullCrawler;
import gravestone.item.CorpseHelper;
import gravestone.item.enums.EnumCorpse;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSEventsHook {

    @SubscribeEvent
    public void onEntityLivingDeath(LivingDeathEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (!GraveStoneConfig.generateGravesInLava && event.source.damageType.equals("lava")) {
                return;
            }

            if (GraveStoneConfig.generatePlayerGraves && event.entityLiving instanceof EntityPlayer) {
                GraveStoneHelper.createPlayerGrave((EntityPlayer) event.entity, event);
            } else {
                if (GraveStoneConfig.generateVillagerGraves && event.entity instanceof EntityVillager) {
                    GraveStoneHelper.createGrave(event.entity, event, CorpseHelper.getCorpse(event.entity, EnumCorpse.VILLAGER), ((EntityVillager) event.entity).getAge(), EnumGraveType.PLAYER_GRAVES, true);
                    return;
                }

                if (GraveStoneConfig.generatePetGraves && event.entity instanceof EntityTameable) {
                    GraveStoneHelper.createPetGrave(event.entity, event);
                    return;
                }
            }

            if (event.entity instanceof EntitySkeleton) {
                EntitySkullCrawler crawler;
                if (GSMobSpawn.isWitherSkeleton((EntitySkeleton) event.entity)) {
                    crawler = new EntityWitherSkullCrawler(event.entity.worldObj);
                } else {
                    crawler = new EntitySkullCrawler(event.entity.worldObj);
                }
                GSMobSpawn.spawnCrawler(event.entity, crawler);
            } else if (event.entity instanceof EntityZombie) {
                GSMobSpawn.spawnCrawler(event.entity, new EntityZombieSkullCrawler(event.entity.worldObj));
            } else if (event.entity instanceof EntityHorse) {
                GraveStoneHelper.createHorseGrave((EntityHorse) event.entity, event);
                return;
            } else if (event.entity instanceof EntityCreeper && ((EntityCreeper) event.entity).getPowered()) {
                // drop creeper statue if entity is a charged creeper
                GSBlock.memorial.dropCreeperMemorial(event.entity.worldObj, (int) event.entity.posX,
                        (int) event.entity.posY, (int) event.entity.posZ);
            }
        }
    }
}
