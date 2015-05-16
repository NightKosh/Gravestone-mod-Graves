package gravestone.entity.monster;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityGSSkeleton extends EntitySkeleton {
    public EntityGSSkeleton(World worldIn) {
        super(worldIn);
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingData) {
        livingData = super.func_180482_a(difficulty, livingData);

        if (this.rand.nextBoolean()) {
            this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword, 1, 0));
        } else {
            this.setCurrentItemOrArmor(0, new ItemStack(Items.bow, 1, 0));
        }
        
        return livingData;
    }
}
