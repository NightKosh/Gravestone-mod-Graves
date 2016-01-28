package nightkosh.gravestone.helper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import nightkosh.gravestone.api.IGraveStone;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.GSBlock;
import nightkosh.gravestone.inventory.GraveInventory;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneHelper implements IGraveStone {
    public static ArrayList<Item> swordsList = new ArrayList<Item>(
            Arrays.asList(
                    Items.wooden_sword,
                    Items.stone_sword,
                    Items.iron_sword,
                    Items.golden_sword,
                    Items.diamond_sword
            )
    );

    public static final Item[] GENERATED_SWORD_GRAVES = {
            Items.wooden_sword,
            Items.stone_sword
    };


    public static final List<Block> FLOWERS_GROUND = Arrays.asList(
            Blocks.grass, Blocks.dirt);
    public static final List<BlockFlower> FLOWERS = Arrays.asList(
            Blocks.yellow_flower, Blocks.red_flower);

    public GraveStoneHelper() {
    }

    /**
     * Check is grave - sword grave
     *
     * @param graveType Grave type
     */
    public static boolean isSwordGrave(int graveType) {
        return graveType == EnumGraves.SWORD.ordinal();
    }

    /**
     * Return random grave type
     *
     * @param random
     * @param graveType
     */
    public static int getGraveType(World world, BlockPos pos, Random random, GraveGenerationHelper.EnumGraveTypeByEntity graveType) {
        ArrayList<EnumGraves> petsGravesList;
        switch (graveType) {
            case PLAYER_GRAVES:
                if (random.nextFloat() > 0.1) {
                    return 0;//TODO getRandomGrave(GraveGenerationHelper.getPlayerGraveTypesByBiomes(world, pos), random).ordinal();
                } else {
                    return EnumGraves.SWORD.ordinal();
                }
            case PETS_GRAVES:
                petsGravesList = new ArrayList<>();
                //TODO petsGravesList.addAll(GraveGenerationHelper.getDogGraveTypesByBiome(world, pos));
                //TODO petsGravesList.addAll(GraveGenerationHelper.getCatGraveTypesByBiome(world, pos));
                return getRandomGrave(petsGravesList, random).ordinal();
            case DOGS_GRAVES:
                return 0;//TODO getRandomGrave(GraveGenerationHelper.getDogGraveTypesByBiome(world, pos), random).ordinal();
            case CATS_GRAVES:
                return 0;//TODO getRandomGrave(GraveGenerationHelper.getCatGraveTypesByBiome(world, pos), random).ordinal();
            case ALL_GRAVES:
            default:
                if (random.nextFloat() > 0.2) {
                    if (random.nextFloat() > 0.1) {
                        return 0;//TODO getRandomGrave(GraveGenerationHelper.getPlayerGraveTypesByBiomes(world, pos), random).ordinal();
                    } else {
                        return EnumGraves.SWORD.ordinal();
                    }
                } else {
                    petsGravesList = new ArrayList<>();
                    //TODO petsGravesList.addAll(GraveGenerationHelper.getDogGraveTypesByBiome(world, pos));
                    //TODO petsGravesList.addAll(GraveGenerationHelper.getCatGraveTypesByBiome(world, pos));
                    return getRandomGrave(petsGravesList, random).ordinal();
                }
        }
    }

    public static int getTreasuryGraveType(World world, BlockPos pos, Random random) {
        ArrayList<EnumGraves> petsGravesList;
        if (random.nextFloat() > 0.1) {
            return 0;//TODO !!!!!!!!!!!!!getRandomGrave(GraveGenerationHelper.getPlayerGraveTypesByBiomes(world, pos), random).ordinal();
        } else {
            return EnumGraves.SWORD.ordinal();
        }
    }

    /**
     * Check is grave - pet grave
     *
     * @param graveType Grave type
     */
    public static boolean isPetGrave(int graveType) {
        return EnumGraves.getById(graveType).getGraveType() == EnumGraveType.DOG_STATUE ||
                EnumGraves.getById(graveType).getGraveType() == EnumGraveType.CAT_STATUE;
    }

    /**
     * Check ground type and replace it on dirt if it grass or mycelium
     */
    public static void replaceGround(World world, BlockPos pos) {
        Block botBlock = world.getBlockState(pos).getBlock();

        if (botBlock.equals(Blocks.grass) || botBlock.equals(Blocks.mycelium)) {
            world.setBlockState(pos, Blocks.dirt.getBlockState().getBaseState());
        }
    }

    /**
     * Spawn mob
     */
    public static void spawnMob(World world, BlockPos pos) {
        if (Config.spawnMobAtGraveDestruction && world.rand.nextInt(10) == 0) {
            TileEntityGraveStone tileEntity = (TileEntityGraveStone) world.getTileEntity(pos);

            if (tileEntity != null) {
                //TODO
//                Entity mob = GSMobSpawn.getMobEntity(world, tileEntity.getGraveType(), pos.getX(), pos.getY(), pos.getZ());
//
//                if (mob != null) {
//                    GSMobSpawn.spawnMob(world, mob, pos.getX(), pos.getY(), pos.getZ(), false);
//                }
            }
        }
    }

    /**
     * Check can be grave placed on this type of surface
     */
    public static boolean canPlaceBlockAt(World world, BlockPos pos) {
        return canPlaceBlockAt(world, world.getBlockState(pos).getBlock(), pos);
    }

    public static boolean canPlaceBlockAt(World world, Block block, BlockPos pos) {
        if (Config.canPlaceGravesEveryWhere) {
            return true;
        } else {
            String tool = block.getHarvestTool(world.getBlockState(pos));
            if (tool != null) {
                return tool.equals("shovel");
            } else {
                return false;
            }
        }
    }

    public static void addSwordInfo(NBTTagCompound nbt, ItemStack sword) {
        NBTTagCompound swordNBT = new NBTTagCompound();
        sword.writeToNBT(swordNBT);
        nbt.setTag("Sword", swordNBT);
    }

    public static ItemStack getSwordAsGrave(Item grave, ItemStack sword) {
        ItemStack graveStoneStack = new ItemStack(grave, 1, EnumGraves.SWORD.ordinal());
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("Purified", false);
        GraveStoneHelper.addSwordInfo(nbt, sword);

        graveStoneStack.setTagCompound(nbt);
        return graveStoneStack;
    }

    public static Item getRandomSwordForGeneration(int graveType, Random random) {
        if (graveType == EnumGraves.SWORD.ordinal()) {
            return GENERATED_SWORD_GRAVES[random.nextInt(GENERATED_SWORD_GRAVES.length)];
        } else {
            return null;
        }
    }

    @Override
    public void addSwordToSwordsList(Item sword) {
        if (sword != null) {
            swordsList.add(sword);
        }
    }

    public static EnumGraves getRandomGrave(List<EnumGraves> graveTypes, Random rand) {
        if (graveTypes.size() > 0) {
            return graveTypes.get(rand.nextInt(graveTypes.size()));
        } else {
            return EnumGraves.WOODEN_VERTICAL_PLATE;
        }
    }

    public static boolean canFlowerBePlaced(World world, BlockPos pos, ItemStack itemStack, TileEntityGraveStone te) {
        if (canFlowerBePlacedOnGrave(te)) {
            Item item = itemStack.getItem();
            if (Block.getBlockFromItem(item) instanceof BlockFlower) {
                return true;
            } else if (item instanceof IPlantable) {
                return Block.getBlockFromItem(item).canSustainPlant(world, pos.down(), EnumFacing.UP, (IPlantable) item);
            }
        }
        return false;
    }

    public static boolean canFlowerBePlacedOnGrave(TileEntityGraveStone te) {
        return !te.isSwordGrave() && (te.getGraveType().getGraveType() == EnumGraveType.VERTICAL_PLATE ||
                te.getGraveType().getGraveType() == EnumGraveType.CROSS);
        //TODO celtic cross ????
    }

    public static class RestrictedArea {
        private final BlockPos firstPoint;
        private final BlockPos lastPoint;

        public RestrictedArea(int startX, int startY, int startZ, int endX, int endY, int endZ) {
            firstPoint = new BlockPos(startX, startY, startZ);
            lastPoint = new BlockPos(endX, endY, endZ);
        }

        public boolean isInArea(BlockPos pos) {
            return pos.getX() >= firstPoint.getX() && pos.getX() <= lastPoint.getX() &&
                    pos.getY() >= firstPoint.getY() && pos.getY() <= lastPoint.getY() &&
                    pos.getZ() >= firstPoint.getZ() && pos.getZ() <= lastPoint.getZ();
        }

        public static RestrictedArea getFromString(String area) {
            String[] coordinates = area.split(",");
            if (coordinates.length == 6) {
                try {
                    return new GraveStoneHelper.RestrictedArea(
                            Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2]),
                            Integer.parseInt(coordinates[3]), Integer.parseInt(coordinates[4]), Integer.parseInt(coordinates[5]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    /**
     * Drop grave as item block
     */
    public static void dropBlock(World world, BlockPos pos, IBlockState state) {
        ItemStack itemStack = getBlockItemStack(world, pos, state);

        if (itemStack != null) {
            GraveInventory.dropItem(itemStack, world, pos);
        }
    }

    public static void dropBlockWithoutInfo(World world, BlockPos pos, IBlockState state) {
        ItemStack itemStack = GSBlock.graveStone.createStackedBlock(GSBlock.graveStone.getDefaultState());
        TileEntityGraveStone tileEntity = (TileEntityGraveStone) world.getTileEntity(pos);

        if (tileEntity != null) {
            if (tileEntity.isSwordGrave()) {
                tileEntity.dropSword();
            } else if (itemStack != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                itemStack.setItemDamage(tileEntity.getGraveTypeNum());
                nbt.setBoolean("Mossy", tileEntity.isMossy());
                nbt.setBoolean("Purified", true);

                itemStack.setTagCompound(nbt);
                GraveInventory.dropItem(itemStack, world, pos);
            }
        }
    }

    /**
     * Get grave block as item block
     */
    public static ItemStack getBlockItemStack(IBlockAccess access, BlockPos pos, IBlockState state) {
        ItemStack itemStack = GSBlock.graveStone.createStackedBlock(GSBlock.graveStone.getDefaultState());
        TileEntityGraveStone tileEntity = (TileEntityGraveStone) access.getTileEntity(pos);

        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            itemStack.setItemDamage(tileEntity.getGraveTypeNum());

            if (tileEntity.getDeathTextComponent().isLocalized()) {
                nbt.setBoolean("isLocalized", true);
                nbt.setString("name", tileEntity.getDeathTextComponent().getName());
                nbt.setString("KillerName", tileEntity.getDeathTextComponent().getKillerName());
            }

            nbt.setString("DeathText", tileEntity.getDeathTextComponent().getDeathText());
            nbt.setInteger("Age", tileEntity.getAge());

            if (tileEntity.isSwordGrave()) {
                GraveStoneHelper.addSwordInfo(nbt, tileEntity.getSword());
            }

            nbt.setBoolean("Enchanted", tileEntity.isEnchanted());
            nbt.setBoolean("Mossy", tileEntity.isMossy());

            nbt.setBoolean("Purified", true);

            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }
}