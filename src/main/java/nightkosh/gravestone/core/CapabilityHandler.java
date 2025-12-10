package nightkosh.gravestone.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.capability.BackupProvider;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CapabilityHandler {

    public static final ResourceLocation BACKUP_CAP = fromNamespaceAndPath(ModInfo.ID, "backup");

    //TODO
//    @SubscribeEvent
//    public void attachPlayerCapability(AttachCapabilitiesEvent<Entity> event) {
//        if (event.getObject() instanceof Player) {
//            event.addCapability(BACKUP_CAP, new BackupProvider());
//        }
//    }

}
