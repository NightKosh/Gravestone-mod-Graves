package nightkosh.gravestone.helper;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import nightkosh.gravestone.core.GSEnchantments;
import nightkosh.gravestone.core.config.GSConfigs;

import java.util.Collection;

import static nightkosh.gravestone.ModGraveStone.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSEnchantmentHelper {

    public static Holder<Enchantment> getEnchantmentHolder(Level level, ResourceKey<Enchantment> key) {
        return level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(key);
    }

    public static void keepSoulBoundItems(LivingEntity entity, Collection<ItemEntity> drops) {
        if (entity instanceof Player player) {
            var ench = getEnchantmentHolder(player.level(), GSEnchantments.SOULBOUND);
            var inventory = player.getInventory();

            var it = drops.iterator();
            while (it.hasNext()) {
                var itemEntity = it.next();
                var itemStack = itemEntity.getItem();
                if (!itemStack.isEmpty() && EnchantmentHelper.getItemEnchantmentLevel(ench, itemStack) > 0) {
                    if (GSConfigs.DEBUG_MODE.get()) {
                        LOGGER.info("Going to keep soulbound item {} for player {}",
                                itemStack.getHoverName().getString(),
                                player.getScoreboardName());
                    }
                    inventory.add(itemStack.copy());
                    it.remove();
                }
            }
        }
    }

    public static void restoreSoulBoundItems(Player oldPlayer, Player newPlayer) {
        var ench = getEnchantmentHolder(oldPlayer.level(), GSEnchantments.SOULBOUND);
        var oldInventory = oldPlayer.getInventory();
        for (int i = 0; i < oldInventory.getContainerSize(); i++) {
            var item = oldInventory.getItem(i);
            if (!item.isEmpty() && EnchantmentHelper.getItemEnchantmentLevel(ench, item) > 0) {
                if (GSConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("Going to restore soulbound item {} for player {}",
                            item.getHoverName().getString(),
                            newPlayer.getScoreboardName());
                }
                oldInventory.setItem(i, ItemStack.EMPTY);
                newPlayer.getInventory().setItem(i, item.copy());
            }
        }
    }

}
