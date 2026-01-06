package nightkosh.gravestone.core;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.capability.Backups;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static nightkosh.gravestone.ModGraveStone.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public final class GSBackups {

    private GSBackups() {
    }

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ModInfo.ID);

    public static final Supplier<AttachmentType<Backups>> BACKUPS =
            ATTACHMENT_TYPES.register("backups", () ->
                    AttachmentType.builder(holder -> new Backups())
                            .serialize(new BackupsSerializer())
                            .copyOnDeath()
                            .build()
            );

    private static final class BackupsSerializer implements IAttachmentSerializer<Backups> {

        @Override
        public Backups read(@Nonnull IAttachmentHolder holder, @Nonnull ValueInput input) {
            var backups = new Backups();
            try {
                backups.read(input);
            } catch (Exception e) {
                LOGGER.error("Can't read backups!", e);
            }
            return backups;
        }

        @Override
        public boolean write(Backups backups, @Nonnull ValueOutput output) {
            try {
                if (backups == null) {
                    return false;
                } else {
                    backups.write(output);
                    return true;
                }
            } catch (Exception e) {
                LOGGER.error("Can't write backups!", e);
                return false;
            }
        }

    }

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }

}
