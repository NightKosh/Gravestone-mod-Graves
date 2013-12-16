package gravestone.block;

import gravestone.GraveStoneLogger;
import gravestone.config.GraveStoneConfig;
import gravestone.ModGraveStone;
import gravestone.core.Resources;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSTrap extends Block {

    private static final int PRE_NIGHT = 12000;
    private static final int NIGHT = 14000;
    private static final int PRE_MORNING = 22500;
    public BlockGSTrap(int par1) {
        super(par1, Material.rock);
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("trap.night");
        this.setHardness(4.5F);
        this.setResistance(5F);
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.setTextureName(Resources.TIME_TRAP);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random random, int par3) {
        return Block.netherBrick.blockID;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly,
     * and not its normal drops.
     */
    @Override
    public boolean canSilkHarvest() {
        return true;
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        if (entity instanceof EntityPlayer) {
            if (GraveStoneConfig.enableNightStone) {
                long time = world.getWorldTime();
                long dayTime = time % 24000;
                if (dayTime < PRE_NIGHT || dayTime > PRE_MORNING) {
                    time = time - time % 24000 + PRE_NIGHT;
                    world.setWorldTime(time);
                    ((EntityPlayer) entity).sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(ModGraveStone.proxy.getLocalizedString("block.trap.curse")));
                } else if (dayTime > 20000 && dayTime < PRE_MORNING) {
                    time = time - time % 24000 + NIGHT;
                    world.setWorldTime(time);
                }
            }
        }
    }
}
