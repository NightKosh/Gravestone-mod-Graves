package nightkosh.gravestone.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BackupsHandler {

    public static final Map<String, Backup> BACKUPS = new HashMap<>();

    public static class Backup {
        private int dimensionId;

        private BlockPos pos;

        private List<ItemStack> items = new ArrayList<>();

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
            for (ItemStack stack : items) {
                if (stack != null && !stack.isEmpty()) {
                    this.items.add(stack.copy());
                }
            }
        }
    }
}
