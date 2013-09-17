package gravestone.core;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import gravestone.block.BlockGSGhostlyChest;
import gravestone.config.GraveStoneConfig;
import gravestone.block.BlockGSGraveStone;
import gravestone.block.BlockGSMemorial;
import gravestone.block.BlockGSTrap;
import gravestone.block.BlockGSWitherSpawner;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumMemorials;
import gravestone.block.GraveStoneHelper;
import gravestone.block.enums.EnumChestTypes;
import gravestone.item.ItemBlockGSGraveStone;
import gravestone.item.ItemBlockGSMemorial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBlock {

    private GSBlock() {
    }
    
    // block GraveStone
    public static BlockGSGraveStone graveStone;
    // Block wither spawer
    public static BlockGSWitherSpawner witherSpawner;
    // Block Time Trap
    public static BlockGSTrap trap;
    // block memorial
    public static BlockGSMemorial memorial;
    // GhostlyChest
    public static BlockGSGhostlyChest ghostlyChest;

    public static void registration() {
        // gravestone
        graveStone = new BlockGSGraveStone(GraveStoneConfig.graveStoneID);
        GameRegistry.registerBlock(graveStone, ItemBlockGSGraveStone.class);

        for (byte i = 0; i < EnumGraves.values().length; i++) {
            ItemStack graveStoneStack = new ItemStack(graveStone, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", i);

            if (GraveStoneHelper.isSwordGrave(i)) {
                nbt.setByte("SwordType", GraveStoneHelper.graveTypeToSwordType(i));
            }

            graveStoneStack.setTagCompound(nbt);
            LanguageRegistry.addName(graveStoneStack, EnumGraves.getByID(i).getName());
        }

        MinecraftForge.setBlockHarvestLevel(graveStone, "pickaxe", 1);

        // wither spawner
        witherSpawner = new BlockGSWitherSpawner(GraveStoneConfig.witherSpawnerID);
        GameRegistry.registerBlock(witherSpawner, "GSWitherSpawner");
        LanguageRegistry.addName(witherSpawner, "Wither spawner");
        MinecraftForge.setBlockHarvestLevel(witherSpawner, "pickaxe", 1);

        // trap
        trap = new BlockGSTrap(GraveStoneConfig.timeTrapID);
        GameRegistry.registerBlock(trap, "GSTimeTrap");
        LanguageRegistry.addName(trap, "Night stone");
        MinecraftForge.setBlockHarvestLevel(trap, "pickaxe", 1);

        // memorials
        memorial = new BlockGSMemorial(GraveStoneConfig.memorialID);
        GameRegistry.registerBlock(memorial, "GSMemorial");
        LanguageRegistry.addName(memorial, "Memorial");
        GameRegistry.registerBlock(memorial, ItemBlockGSMemorial.class);

        for (byte i = 0; i < EnumMemorials.values().length; i++) {
            ItemStack memorialStack = new ItemStack(memorial, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", i);
            
            memorialStack.setTagCompound(nbt);
            LanguageRegistry.addName(memorialStack, EnumMemorials.getByID(i).getName());
        }
        MinecraftForge.setBlockHarvestLevel(memorial, "pickaxe", 2);
        
        // ghostChest
        ghostlyChest = new BlockGSGhostlyChest(1555);
        GameRegistry.registerBlock(ghostlyChest, "GSGhostlyChest");
        LanguageRegistry.addName(ghostlyChest, "GhostlyChest");
        
        for (byte i = 0; i < EnumChestTypes.values().length; i++) {
            ItemStack stack = new ItemStack(ghostlyChest, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("ChestType", i);

            stack.setTagCompound(nbt);
            LanguageRegistry.addName(stack, EnumChestTypes.getById(i).getName());
        }
        MinecraftForge.setBlockHarvestLevel(ghostlyChest, "axe", 1);
    }
}
