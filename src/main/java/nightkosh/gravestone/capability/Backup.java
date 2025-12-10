package nightkosh.gravestone.capability;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Backup {

    private int dimensionId;
    private BlockPos pos;
    private final List<ItemStack> items = new ArrayList<>();

    public Backup() {
    }

    public Backup(int dimensionId, BlockPos pos, List<ItemStack> items) {
        this.dimensionId = dimensionId;
        this.pos = pos;
        setItems(items);
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
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

}
