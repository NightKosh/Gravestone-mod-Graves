package nightkosh.gravestone.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.capability.BackupProvider;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CapabilityHandler {
    public static final ResourceLocation BACKUP_CAP = new ResourceLocation(ModInfo.ID, "backup");

    @SubscribeEvent
    public void attachPlayerCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(BACKUP_CAP, new BackupProvider());
        }
    }
}
