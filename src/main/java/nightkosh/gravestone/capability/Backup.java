package nightkosh.gravestone.capability;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Backup {

    private ResourceKey<Level> dimension;
    private BlockPos pos;
    private final List<ItemStack> items = new ArrayList<>();

    public Backup() {
    }

    public Backup(ResourceKey<Level> dimension, BlockPos pos, List<ItemStack> items) {
        this.dimension = dimension;
        this.pos = pos;
        setItems(items);
    }

    public ResourceKey<Level> getDimension() {
        return dimension;
    }

    public void setDimension(ResourceKey<Level> dimension) {
        this.dimension = dimension;
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public void setItems(List<ItemStack> items) {
        for (var stack : items) {
            if (stack != null && !stack.isEmpty()) {
                this.items.add(stack.copy());
            }
        }
    }

    public CompoundTag toNBT() {
        var tag = new CompoundTag();

        if (dimension != null) {
            tag.putString("Dimension", dimension.location().toString());
        }

        tag.putInt("PosX", pos.getX());
        tag.putInt("PosY", pos.getY());
        tag.putInt("PosZ", pos.getZ());

        var list = new ListTag();
        for (var stack : items) {
            if (stack != null && !stack.isEmpty()) {
                CompoundTag s = new CompoundTag();
                stack.save(s);
                list.add(s);
            }
        }
        tag.put("Items", list);

        return tag;
    }

    public static Backup fromNBT(CompoundTag tag) {
        var b = new Backup();

        if (tag.contains("Dimension")) {
            b.dimension = ResourceKey.create(
                    Registries.DIMENSION,
                    new ResourceLocation(tag.getString("Dimension")));
        }

        b.pos = new BlockPos(tag.getInt("PosX"), tag.getInt("PosY"), tag.getInt("PosZ"));

        var itemsTag = tag.getList("Items", Tag.TAG_COMPOUND);
        for (int i = 0; i < itemsTag.size(); i++) {
            b.items.add(ItemStack.of(itemsTag.getCompound(i)));
        }

        return b;
    }

}
