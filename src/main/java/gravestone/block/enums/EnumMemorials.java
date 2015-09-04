package gravestone.block.enums;

import gravestone.core.Resources;
import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumMemorials implements IBlockEnum {

    // crosses
    WOODEN_CROSS("block.memorial.cross", Resources.MEMORIAL_WOODEN_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.WOOD),
    SANDSTONE_CROSS("block.memorial.cross", Resources.MEMORIAL_SANDSTONE_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_CROSS("block.memorial.cross", Resources.MEMORIAL_RED_SANDSTONE_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.RED_SANDSTONE),
    STONE_CROSS("block.memorial.cross", Resources.MEMORIAL_STONE_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.STONE),
    DIORITE_CROSS("block.memorial.cross", Resources.MEMORIAL_DIORITE_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.DIORITE),
    ANDESITE_CROSS("block.memorial.cross", Resources.MEMORIAL_ANDESITE_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.ANDESITE),
    GRANITE_CROSS("block.memorial.cross", Resources.MEMORIAL_GRANITE_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.GRANITE),
    IRON_CROSS("block.memorial.cross", Resources.MEMORIAL_IRON_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.IRON),
    GOLDEN_CROSS("block.memorial.cross", Resources.MEMORIAL_GOLDEN_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.GOLD),
    DIAMOND_CROSS("block.memorial.cross", Resources.MEMORIAL_DIAMOND_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.DIAMOND),
    EMERALD_CROSS("block.memorial.cross", Resources.MEMORIAL_EMERALD_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.EMERALD),
    LAPIS_CROSS("block.memorial.cross", Resources.MEMORIAL_LAPIS_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.LAPIS),
    REDSTONE_CROSS("block.memorial.cross", Resources.MEMORIAL_REDSTONE_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_CROSS("block.memorial.cross", Resources.MEMORIAL_OBSIDIAN_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_CROSS("block.memorial.cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_CROSS("block.memorial.cross", Resources.MEMORIAL_PRIZMARINE_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.PRIZMARINE),
    ICE_CROSS("block.memorial.cross", Resources.MEMORIAL_ICE_CROSS, null, EnumMemorialType.CROSS, EnumGraveMaterial.ICE),
    // obelisks
    WOODEN_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_WOODEN_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.WOOD),
    SANDSTONE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_SANDSTONE_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_RED_SANDSTONE_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.RED_SANDSTONE),
    STONE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_STONE_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.STONE),
    DIORITE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_DIORITE_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.DIORITE),
    ANDESITE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_ANDESITE_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.ANDESITE),
    GRANITE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_GRANITE_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.GRANITE),
    IRON_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_IRON_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.IRON),
    GOLDEN_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_GOLDEN_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.GOLD),
    DIAMOND_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_DIAMOND_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.DIAMOND),
    EMERALD_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_EMERALD_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.EMERALD),
    LAPIS_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_LAPIS_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.LAPIS),
    REDSTONE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_REDSTONE_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_OBSIDIAN_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_QUARTZ_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_PRIZMARINE_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.PRIZMARINE),
    ICE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_ICE_OBELISK, null, EnumMemorialType.OBELISK, EnumGraveMaterial.ICE),
    // steve memorials
    WOODEN_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_WOODEN_STEVE_STATUE, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_SANDSTONE_STEVE_STATUE, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_RED_SANDSTONE_STEVE_STATUE, Resources.MEMORIAL_RED_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_STONE_STEVE_STATUE, Resources.MEMORIAL_STONE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.STONE),
    DIORITE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_DIORITE_STEVE_STATUE, Resources.MEMORIAL_DIORITE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_ANDESITE_STEVE_STATUE, Resources.MEMORIAL_ANDESITE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_GRANITE_STEVE_STATUE, Resources.MEMORIAL_GRANITE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.GRANITE),
    IRON_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_IRON_STEVE_STATUE, Resources.MEMORIAL_IRON_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_GOLDEN_STEVE_STATUE, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_DIAMOND_STEVE_STATUE, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_EMERALD_STEVE_STATUE, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_LAPIS_STEVE_STATUE, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_REDSTONE_STEVE_STATUE, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_OBSIDIAN_STEVE_STATUE, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_QUARTZ_STEVE_STATUE, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_PRIZMARINE_STEVE_STATUE, Resources.MEMORIAL_PRIZMARINE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_ICE_STEVE_STATUE, Resources.MEMORIAL_ICE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.ICE),
    // villagers memorials
    WOODEN_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_WOODEN_VILLAGER_STATUE, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_SANDSTONE_VILLAGER_STATUE, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_RED_SANDSTONE_VILLAGER_STATUE, Resources.MEMORIAL_RED_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_STONE_VILLAGER_STATUE, Resources.MEMORIAL_STONE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.STONE),
    DIORITE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_DIORITE_VILLAGER_STATUE, Resources.MEMORIAL_DIORITE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_ANDESITE_VILLAGER_STATUE, Resources.MEMORIAL_ANDESITE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_GRANITE_VILLAGER_STATUE, Resources.MEMORIAL_GRANITE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.GRANITE),
    IRON_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_IRON_VILLAGER_STATUE, Resources.MEMORIAL_IRON_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_GOLDEN_VILLAGER_STATUE, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_DIAMOND_VILLAGER_STATUE, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_EMERALD_VILLAGER_STATUE, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_LAPIS_VILLAGER_STATUE, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_REDSTONE_VILLAGER_STATUE, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_OBSIDIAN_VILLAGER_STATUE, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_QUARTZ_VILLAGER_STATUE, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_PRIZMARINE_VILLAGER_STATUE, Resources.MEMORIAL_PRIZMARINE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_ICE_VILLAGER_STATUE, Resources.MEMORIAL_ICE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE, EnumGraveMaterial.ICE),
    // angel memorials
    WOODEN_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_WOODEN_ANGEL_STATUE, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_SANDSTONE_ANGEL_STATUE, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_RED_SANDSTONE_ANGEL_STATUE, Resources.MEMORIAL_RED_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_STONE_ANGEL_STATUE, Resources.MEMORIAL_STONE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.STONE),
    DIORITE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_DIORITE_ANGEL_STATUE, Resources.MEMORIAL_DIORITE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_ANDESITE_ANGEL_STATUE, Resources.MEMORIAL_ANDESITE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_GRANITE_ANGEL_STATUE, Resources.MEMORIAL_GRANITE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.GRANITE),
    IRON_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_IRON_ANGEL_STATUE, Resources.MEMORIAL_IRON_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_GOLDEN_ANGEL_STATUE, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_DIAMOND_ANGEL_STATUE, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_EMERALD_ANGEL_STATUE, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_LAPIS_ANGEL_STATUE, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_REDSTONE_ANGEL_STATUE, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_OBSIDIAN_ANGEL_STATUE, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_QUARTZ_ANGEL_STATUE, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_PRIZMARINE_ANGEL_STATUE, Resources.MEMORIAL_PRIZMARINE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_ICE_ANGEL_STATUE, Resources.MEMORIAL_ICE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE, EnumGraveMaterial.ICE),
    // dogs memorial
    WOODEN_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_WOODEN_DOG_STATUE, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_SANDSTONE_DOG_STATUE, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_RED_SANDSTONE_DOG_STATUE, Resources.MEMORIAL_RED_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_STONE_DOG_STATUE, Resources.MEMORIAL_STONE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.STONE),
    DIORITE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_DIORITE_DOG_STATUE, Resources.MEMORIAL_DIORITE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_ANDESITE_DOG_STATUE, Resources.MEMORIAL_ANDESITE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_GRANITE_DOG_STATUE, Resources.MEMORIAL_GRANITE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.GRANITE),
    IRON_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_IRON_DOG_STATUE, Resources.MEMORIAL_IRON_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_GOLDEN_DOG_STATUE, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_DIAMOND_DOG_STATUE, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_EMERALD_DOG_STATUE, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_LAPIS_DOG_STATUE, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_REDSTONE_DOG_STATUE, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_OBSIDIAN_DOG_STATUE, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_QUARTZ_DOG_STATUE, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_PRIZMARINE_DOG_STATUE, Resources.MEMORIAL_PRIZMARINE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_ICE_DOG_STATUE, Resources.MEMORIAL_ICE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE, EnumGraveMaterial.ICE),
    // dogs memorial
    WOODEN_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_WOODEN_CAT_STATUE, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_SANDSTONE_CAT_STATUE, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_RED_SANDSTONE_CAT_STATUE, Resources.MEMORIAL_RED_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_STONE_CAT_STATUE, Resources.MEMORIAL_STONE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.STONE),
    DIORITE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_DIORITE_CAT_STATUE, Resources.MEMORIAL_DIORITE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_ANDESITE_CAT_STATUE, Resources.MEMORIAL_ANDESITE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_GRANITE_CAT_STATUE, Resources.MEMORIAL_GRANITE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.GRANITE),
    IRON_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_IRON_CAT_STATUE, Resources.MEMORIAL_IRON_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_GOLDEN_CAT_STATUE, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_DIAMOND_CAT_STATUE, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_EMERALD_CAT_STATUE, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_LAPIS_CAT_STATUE, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_REDSTONE_CAT_STATUE, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_OBSIDIAN_CAT_STATUE, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_QUARTZ_CAT_STATUE, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_PRIZMARINE_CAT_STATUE, Resources.MEMORIAL_PRIZMARINE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_ICE_CAT_STATUE, Resources.MEMORIAL_ICE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE, EnumGraveMaterial.ICE),
    // creepers memorial
    WOODEN_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_WOODEN_CREEPER_STATUE, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_SANDSTONE_CREEPER_STATUE, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_RED_SANDSTONE_CREEPER_STATUE, Resources.MEMORIAL_RED_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_STONE_CREEPER_STATUE, Resources.MEMORIAL_STONE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.STONE),
    DIORITE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_DIORITE_CREEPER_STATUE, Resources.MEMORIAL_DIORITE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_ANDESITE_CREEPER_STATUE, Resources.MEMORIAL_ANDESITE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_GRANITE_CREEPER_STATUE, Resources.MEMORIAL_GRANITE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.GRANITE),
    IRON_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_IRON_CREEPER_STATUE, Resources.MEMORIAL_IRON_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_GOLDEN_CREEPER_STATUE, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_DIAMOND_CREEPER_STATUE, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_EMERALD_CREEPER_STATUE, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_LAPIS_CREEPER_STATUE, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_REDSTONE_CREEPER_STATUE, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_OBSIDIAN_CREEPER_STATUE, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_QUARTZ_CREEPER_STATUE, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_PRIZMARINE_CREEPER_STATUE, Resources.MEMORIAL_PRIZMARINE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_ICE_CREEPER_STATUE, Resources.MEMORIAL_ICE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE, EnumGraveMaterial.ICE),
    // celtic crosses
    WOODEN_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.WOOD),
    SANDSTONE_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.RED_SANDSTONE),
    STONE_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.STONE),
    DIORITE_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.DIORITE),
    ANDESITE_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.ANDESITE),
    GRANITE_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.GRANITE),
    IRON_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.IRON),
    GOLDEN_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.GOLD),
    DIAMOND_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.DIAMOND),
    EMERALD_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.EMERALD),
    LAPIS_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.LAPIS),
    REDSTONE_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_PRIZMARINE_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.PRIZMARINE),
    ICE_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS, EnumGraveMaterial.ICE),
    // gibbets
    GIBBET("block.memorial.gibbet", Resources.MEMORIAL_GIBBET, null, EnumMemorialType.GIBBET, EnumGraveMaterial.OTHER),
    // stocks
    STOCKS("block.memorial.stocks", Resources.MEMORIAL_STOCKS, null, EnumMemorialType.STOCKS, EnumGraveMaterial.OTHER),
    // burning stake
    BURNING_STAKE("block.memorial.burning_stake", Resources.BURNING_STAKE, null, EnumMemorialType.BURNING_STAKE, EnumGraveMaterial.OTHER);

    public enum EnumMemorialType {
        CROSS,
        OBELISK,
        STEVE_STATUE,
        VILLAGER_STATUE,
        ANGEL_STATUE,
        DOG_STATUE,
        CAT_STATUE,
        CREEPER_STATUE,
        CELTIC_CROSS,
        GIBBET,
        STOCKS,
        BURNING_STAKE
    }

    private String name;
    private ResourceLocation texture;
    private ResourceLocation pedestalTexture;
    private EnumMemorialType memorialType;
    private EnumGraveMaterial material;

    private EnumMemorials(String name, ResourceLocation texture, ResourceLocation pedestalTexture, EnumMemorialType memorialType, EnumGraveMaterial material) {
        this.name = name;
        this.texture = texture;
        this.pedestalTexture = pedestalTexture;
        this.memorialType = memorialType;
        this.material = material;
    }

    @Override
    public String getUnLocalizedName() {
        return this.name;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public ResourceLocation getPedestalTexture() {
        return this.pedestalTexture;
    }

    public EnumMemorialType getMemorialType() {
        return memorialType;
    }

    public EnumGraveMaterial getMaterial() {
        return material;
    }

    /**
     * Returns the grave type with the specified ID, or VERTICAL_PLATE if none
     * found.
     *
     * @param id Grave Id
     */
    public static EnumMemorials getById(int id) {
        if (id < values().length) {
            return values()[id];
        }
        return STONE_CROSS;
    }

    public static EnumMemorials getByTypeAndMaterial(EnumMemorialType memorialType, EnumGraveMaterial material) {
        for (EnumMemorials memorial : EnumMemorials.values()) {
            if (memorial.getMemorialType().equals(memorialType) && memorial.getMaterial().equals(material)) {
                return memorial;
            }
        }
        return STONE_CROSS;
    }
}
