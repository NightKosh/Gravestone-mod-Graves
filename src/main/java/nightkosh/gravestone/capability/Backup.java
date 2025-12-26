package nightkosh.gravestone.capability;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.apache.commons.lang3.StringUtils;

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
        this.items.clear();
        if (items != null) {
            for (var stack : items) {
                if (stack != null && !stack.isEmpty()) {
                    this.items.add(stack.copy());
                }
            }
        }
    }

    public void write(ValueOutput out) {
        if (dimension != null) {
            String dim = dimension.identifier().toString();
            if (StringUtils.isNoneBlank(dim)) {
                out.putString("Dimension", dim);
            }

            out.putInt("PosX", pos.getX());
            out.putInt("PosY", pos.getY());
            out.putInt("PosZ", pos.getZ());

            var list = out.list("Items", ItemStack.CODEC);
            for (ItemStack s : items) {
                list.add(s);
            }
        }
    }

    public static Backup read(ValueInput in) {
        var b = new Backup();

        String dim = in.getStringOr("Dimension", null);
        if (StringUtils.isNoneBlank(dim)) {
            b.dimension = ResourceKey.create(
                    Registries.DIMENSION,
                    Identifier.parse(dim));
        }

        b.pos = new BlockPos(
                in.getIntOr("PosX", 0),
                in.getIntOr("PosY", 0),
                in.getIntOr("PosZ", 0));
        for (var s : in.listOrEmpty("Items", ItemStack.CODEC)) {
            b.items.add(s);
        }

        return b;
    }

}
