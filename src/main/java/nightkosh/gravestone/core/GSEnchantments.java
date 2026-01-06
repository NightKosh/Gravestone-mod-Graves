package nightkosh.gravestone.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import nightkosh.gravestone.api.ModInfo;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSEnchantments {

    public static final ResourceKey<Enchantment> SOULBOUND =
            ResourceKey.create(Registries.ENCHANTMENT, fromNamespaceAndPath(ModInfo.ID, "soulbound"));

}
