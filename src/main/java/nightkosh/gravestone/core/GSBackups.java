package nightkosh.gravestone.core;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.capability.Backups;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

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

    private static final class BackupsSerializer implements IAttachmentSerializer<CompoundTag, Backups> {

        @Override
        public CompoundTag write(Backups attachment, @Nonnull HolderLookup.Provider provider) {
            return attachment.toNBT(provider);
        }

        @Nonnull
        @Override
        public Backups read(@Nonnull IAttachmentHolder holder, @Nonnull CompoundTag tag, @Nonnull HolderLookup.Provider provider) {
            Backups backups = new Backups();
            backups.fromNBT(tag, provider);
            return backups;
        }
    }

}
