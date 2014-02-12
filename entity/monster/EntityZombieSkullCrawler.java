
package gravestone.entity.monster;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityZombieSkullCrawler extends EntitySkullCrawler {

    public EntityZombieSkullCrawler(World world) {
        super(world);
    }
    
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected int getDropItemId() {
        return Item.rottenFlesh.itemID;
    }
    
    @Override
    protected void dropRareDrop(int par1) {
        this.entityDropItem(new ItemStack(Item.skull.itemID, 1, 2), 0);
    }
    
    @Override
    protected PotionEffect getPotionEffect() {
        return new PotionEffect(Potion.hunger.id, 200);
    }
}
