package nightkosh.gravestone.block.enums;

import net.minecraft.resources.ResourceLocation;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.core.Resources;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumGraves implements IBlockEnum {

    // VERTICAL PLATES
    WOODEN_VERTICAL_PLATE(Resources.GRAVE_WOODEN_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.WOOD),
    SANDSTONE_VERTICAL_PLATE(Resources.GRAVE_SANDSTONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_VERTICAL_PLATE(Resources.GRAVE_RED_SANDSTONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_VERTICAL_PLATE(Resources.GRAVE_STONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.STONE),
    DIORITE_VERTICAL_PLATE(Resources.GRAVE_DIORITE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.DIORITE),
    ANDESITE_VERTICAL_PLATE(Resources.GRAVE_ANDESITE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.ANDESITE),
    GRANITE_VERTICAL_PLATE(Resources.GRAVE_GRANITE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.GRANITE),
    IRON_VERTICAL_PLATE(Resources.GRAVE_IRON_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.IRON),
    GOLDEN_VERTICAL_PLATE(Resources.GRAVE_GOLDEN_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.GOLD),
    DIAMOND_VERTICAL_PLATE(Resources.GRAVE_DIAMOND_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.DIAMOND),
    EMERALD_VERTICAL_PLATE(Resources.GRAVE_EMERALD_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.EMERALD),
    LAPIS_VERTICAL_PLATE(Resources.GRAVE_LAPIS_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.LAPIS),
    REDSTONE_VERTICAL_PLATE(Resources.GRAVE_REDSTONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_VERTICAL_PLATE(Resources.GRAVE_OBSIDIAN_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_VERTICAL_PLATE(Resources.GRAVE_QUARTZ_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_VERTICAL_PLATE(Resources.GRAVE_PRIZMARINE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.PRIZMARINE),
    ICE_VERTICAL_PLATE(Resources.GRAVE_ICE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.ICE),
    // CROSSES
    WOODEN_CROSS(Resources.GRAVE_WOODEN_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.WOOD),
    SANDSTONE_CROSS(Resources.GRAVE_SANDSTONE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_CROSS(Resources.GRAVE_RED_SANDSTONE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.RED_SANDSTONE),
    STONE_CROSS(Resources.GRAVE_STONE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.STONE),
    DIORITE_CROSS(Resources.GRAVE_DIORITE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.DIORITE),
    ANDESITE_CROSS(Resources.GRAVE_ANDESITE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.ANDESITE),
    GRANITE_CROSS(Resources.GRAVE_GRANITE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.GRANITE),
    IRON_CROSS(Resources.GRAVE_IRON_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.IRON),
    GOLDEN_CROSS(Resources.GRAVE_GOLDEN_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.GOLD),
    DIAMOND_CROSS(Resources.GRAVE_DIAMOND_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.DIAMOND),
    EMERALD_CROSS(Resources.GRAVE_EMERALD_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.EMERALD),
    LAPIS_CROSS(Resources.GRAVE_LAPIS_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.LAPIS),
    REDSTONE_CROSS(Resources.GRAVE_REDSTONE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_CROSS(Resources.GRAVE_OBSIDIAN_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_CROSS(Resources.GRAVE_QUARTZ_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_CROSS(Resources.GRAVE_PRIZMARINE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.PRIZMARINE),
    ICE_CROSS(Resources.GRAVE_ICE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.ICE),
    // OBELISKS
    WOODEN_OBELISK(Resources.WOODEN_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.WOOD),
    SANDSTONE_OBELISK(Resources.SANDSTONE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_OBELISK(Resources.RED_SANDSTONE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.RED_SANDSTONE),
    STONE_OBELISK(Resources.STONE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.STONE),
    DIORITE_OBELISK(Resources.DIORITE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.DIORITE),
    ANDESITE_OBELISK(Resources.ANDESITE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.ANDESITE),
    GRANITE_OBELISK(Resources.GRANITE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.GRANITE),
    IRON_OBELISK(Resources.IRON_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.IRON),
    GOLDEN_OBELISK(Resources.GOLDEN_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.GOLD),
    DIAMOND_OBELISK(Resources.DIAMOND_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.DIAMOND),
    EMERALD_OBELISK(Resources.EMERALD_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.EMERALD),
    LAPIS_OBELISK(Resources.LAPIS_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.LAPIS),
    REDSTONE_OBELISK(Resources.REDSTONE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_OBELISK(Resources.OBSIDIAN_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_OBELISK(Resources.QUARTZ_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_OBELISK(Resources.PRIZMARINE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.PRIZMARINE),
    ICE_OBELISK(Resources.ICE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.ICE),
    // CELTIC CROSSES
    WOODEN_CELTIC_CROSS(Resources.WOODEN_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.WOOD),
    SANDSTONE_CELTIC_CROSS(Resources.SANDSTONE_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_CELTIC_CROSS(Resources.RED_SANDSTONE_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.RED_SANDSTONE),
    STONE_CELTIC_CROSS(Resources.STONE_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.STONE),
    DIORITE_CELTIC_CROSS(Resources.DIORITE_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.DIORITE),
    ANDESITE_CELTIC_CROSS(Resources.ANDESITE_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.ANDESITE),
    GRANITE_CELTIC_CROSS(Resources.GRANITE_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.GRANITE),
    IRON_CELTIC_CROSS(Resources.IRON_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.IRON),
    GOLDEN_CELTIC_CROSS(Resources.GOLDEN_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.GOLD),
    DIAMOND_CELTIC_CROSS(Resources.DIAMOND_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.DIAMOND),
    EMERALD_CELTIC_CROSS(Resources.EMERALD_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.EMERALD),
    LAPIS_CELTIC_CROSS(Resources.LAPIS_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.LAPIS),
    REDSTONE_CELTIC_CROSS(Resources.REDSTONE_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_CELTIC_CROSS(Resources.OBSIDIAN_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_CELTIC_CROSS(Resources.QUARTZ_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_CELTIC_CROSS(Resources.PRIZMARINE_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.PRIZMARINE),
    ICE_CELTIC_CROSS(Resources.ICE_CELTIC_CROSS, EnumGraveType.CELTIC_CROSS, EnumGraveMaterial.ICE),
    // HORISONTAL PLATES
    WOODEN_HORIZONTAL_PLATE(Resources.GRAVE_WOODEN_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.WOOD),
    SANDSTONE_HORIZONTAL_PLATE(Resources.GRAVE_SANDSTONE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_HORIZONTAL_PLATE(Resources.GRAVE_RED_SANDSTONE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_HORIZONTAL_PLATE(Resources.GRAVE_STONE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.STONE),
    DIORITE_HORIZONTAL_PLATE(Resources.GRAVE_DIORITE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.DIORITE),
    ANDESITE_HORIZONTAL_PLATE(Resources.GRAVE_ANDESITE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.ANDESITE),
    GRANITE_HORIZONTAL_PLATE(Resources.GRAVE_GRANITE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.GRANITE),
    IRON_HORIZONTAL_PLATE(Resources.GRAVE_IRON_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.IRON),
    GOLDEN_HORIZONTAL_PLATE(Resources.GRAVE_GOLDEN_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.GOLD),
    DIAMOND_HORIZONTAL_PLATE(Resources.GRAVE_DIAMOND_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.DIAMOND),
    EMERALD_HORIZONTAL_PLATE(Resources.GRAVE_EMERALD_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.EMERALD),
    LAPIS_HORIZONTAL_PLATE(Resources.GRAVE_LAPIS_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.LAPIS),
    REDSTONE_HORIZONTAL_PLATE(Resources.GRAVE_REDSTONE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_HORIZONTAL_PLATE(Resources.GRAVE_OBSIDIAN_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_HORIZONTAL_PLATE(Resources.GRAVE_QUARTZ_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_HORIZONTAL_PLATE(Resources.GRAVE_PRIZMARINE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.PRIZMARINE),
    ICE_HORIZONTAL_PLATE(Resources.GRAVE_ICE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.ICE),
    // VILLAGERS STATUES
    WOODEN_VILLAGER_STATUE(Resources.WOODEN_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_VILLAGER_STATUE(Resources.SANDSTONE_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_VILLAGER_STATUE(Resources.RED_SANDSTONE_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_VILLAGER_STATUE(Resources.STONE_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.STONE),
    DIORITE_VILLAGER_STATUE(Resources.DIORITE_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_VILLAGER_STATUE(Resources.ANDESITE_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_VILLAGER_STATUE(Resources.GRANITE_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.GRANITE),
    IRON_VILLAGER_STATUE(Resources.IRON_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_VILLAGER_STATUE(Resources.GOLDEN_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_VILLAGER_STATUE(Resources.DIAMOND_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_VILLAGER_STATUE(Resources.EMERALD_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_VILLAGER_STATUE(Resources.LAPIS_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_VILLAGER_STATUE(Resources.REDSTONE_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_VILLAGER_STATUE(Resources.OBSIDIAN_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_VILLAGER_STATUE(Resources.QUARTZ_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_VILLAGER_STATUE(Resources.PRIZMARINE_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_VILLAGER_STATUE(Resources.ICE_VILLAGER_STATUE, EnumGraveType.VILLAGER_STATUE, EnumGraveMaterial.ICE),
    // DOGS GRAVES
    WOODEN_DOG_STATUE(Resources.WOODEN_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_DOG_STATUE(Resources.SANDSTONE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_DOG_STATUE(Resources.RED_SANDSTONE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_DOG_STATUE(Resources.STONE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.STONE),
    DIORITE_DOG_STATUE(Resources.DIORITE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_DOG_STATUE(Resources.ANDESITE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_DOG_STATUE(Resources.GRANITE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.GRANITE),
    IRON_DOG_STATUE(Resources.IRON_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_DOG_STATUE(Resources.GOLDEN_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_DOG_STATUE(Resources.DIAMOND_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_DOG_STATUE(Resources.EMERALD_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_DOG_STATUE(Resources.LAPIS_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_DOG_STATUE(Resources.REDSTONE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_DOG_STATUE(Resources.OBSIDIAN_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_DOG_STATUE(Resources.QUARTZ_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_DOG_STATUE(Resources.PRIZMARINE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_DOG_STATUE(Resources.ICE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.ICE),
    // CATS GRAVES
    WOODEN_CAT_STATUE(Resources.WOODEN_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_CAT_STATUE(Resources.SANDSTONE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_CAT_STATUE(Resources.RED_SANDSTONE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_CAT_STATUE(Resources.STONE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.STONE),
    DIORITE_CAT_STATUE(Resources.DIORITE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_CAT_STATUE(Resources.ANDESITE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_CAT_STATUE(Resources.GRANITE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.GRANITE),
    IRON_CAT_STATUE(Resources.IRON_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_CAT_STATUE(Resources.GOLDEN_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_CAT_STATUE(Resources.DIAMOND_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_CAT_STATUE(Resources.EMERALD_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_CAT_STATUE(Resources.LAPIS_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_CAT_STATUE(Resources.REDSTONE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_CAT_STATUE(Resources.OBSIDIAN_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_CAT_STATUE(Resources.QUARTZ_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_CAT_STATUE(Resources.PRIZMARINE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_CAT_STATUE(Resources.ICE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.ICE),
    // HORSES GRAVES
    WOODEN_HORSE_STATUE(Resources.GRAVE_WOODEN_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_HORSE_STATUE(Resources.GRAVE_SANDSTONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_HORSE_STATUE(Resources.GRAVE_RED_SANDSTONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_HORSE_STATUE(Resources.GRAVE_STONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.STONE),
    DIORITE_HORSE_STATUE(Resources.GRAVE_DIORITE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_HORSE_STATUE(Resources.GRAVE_ANDESITE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_HORSE_STATUE(Resources.GRAVE_GRANITE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.GRANITE),
    IRON_HORSE_STATUE(Resources.GRAVE_IRON_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_HORSE_STATUE(Resources.GRAVE_GOLDEN_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_HORSE_STATUE(Resources.GRAVE_DIAMOND_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_HORSE_STATUE(Resources.GRAVE_EMERALD_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_HORSE_STATUE(Resources.GRAVE_LAPIS_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_HORSE_STATUE(Resources.GRAVE_REDSTONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_HORSE_STATUE(Resources.GRAVE_OBSIDIAN_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_HORSE_STATUE(Resources.GRAVE_QUARTZ_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_HORSE_STATUE(Resources.GRAVE_PRIZMARINE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_HORSE_STATUE(Resources.GRAVE_ICE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.ICE),
    // CREEPER STATUES
    WOODEN_CREEPER_STATUE(Resources.WOODEN_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_CREEPER_STATUE(Resources.SANDSTONE_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_CREEPER_STATUE(Resources.RED_SANDSTONE_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_CREEPER_STATUE(Resources.STONE_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.STONE),
    DIORITE_CREEPER_STATUE(Resources.DIORITE_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_CREEPER_STATUE(Resources.ANDESITE_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_CREEPER_STATUE(Resources.GRANITE_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.GRANITE),
    IRON_CREEPER_STATUE(Resources.IRON_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_CREEPER_STATUE(Resources.GOLDEN_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_CREEPER_STATUE(Resources.DIAMOND_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_CREEPER_STATUE(Resources.EMERALD_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_CREEPER_STATUE(Resources.LAPIS_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_CREEPER_STATUE(Resources.REDSTONE_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_CREEPER_STATUE(Resources.OBSIDIAN_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_CREEPER_STATUE(Resources.QUARTZ_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_CREEPER_STATUE(Resources.PRIZMARINE_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_CREEPER_STATUE(Resources.ICE_CREEPER_STATUE, EnumGraveType.CREEPER_STATUE, EnumGraveMaterial.ICE),
    // CORPSES
    STARVED_CORPSE(Resources.SKELETON, EnumGraveType.STARVED_CORPSE, EnumGraveMaterial.OTHER),
    WITHERED_CORPSE(Resources.WITHER_SKELETON, EnumGraveType.WITHERED_CORPSE, EnumGraveMaterial.OTHER),
    // SWORD
    SWORD(null, EnumGraveType.SWORD, EnumGraveMaterial.OTHER);


    private String name;
    private ResourceLocation texture;
    private EnumGraveType graveType;
    private EnumGraveMaterial material;

    private EnumGraves(ResourceLocation texture, EnumGraveType graveType, EnumGraveMaterial material) {
        this.name = getName(graveType);
        this.texture = texture;
        this.graveType = graveType;
        this.material = material;
    }

    @Override
    public String getUnLocalizedName() {
        return this.name;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public EnumGraveType getGraveType() {
        return graveType;
    }

    public EnumGraveMaterial getMaterial() {
        return material;
    }

    /**
     * Returns the grave type with the specified ID, or VERTICAL_PLATE if none found.
     *
     * @param id Grave Id
     */
    public static EnumGraves getById(int id) {
        if (id < values().length) {
            return values()[id];
        }
        return STONE_VERTICAL_PLATE;
    }

    public static EnumGraves getByTypeAndMaterial(EnumGraveType graveType, EnumGraveMaterial material) {
        for (EnumGraves grave : EnumGraves.values()) {
            if (grave.getGraveType().equals(graveType) && grave.getMaterial().equals(material)) {
                return grave;
            }
        }
        return STONE_VERTICAL_PLATE;
    }

    private static String getName(EnumGraveType graveType) {
        switch (graveType) {
            case VERTICAL_PLATE:
            default:
                return "block.gravestone.gravestone";
            case CROSS:
                return "block.gravestone.cross";
            case OBELISK:
                return "block.gravestone.obelisk";
            case CELTIC_CROSS:
                return "block.gravestone.celtic_cross";
            case HORIZONTAL_PLATE:
                return "block.gravestone.plate";
            case VILLAGER_STATUE:
                return "block.gravestone.villager_statue";
            case DOG_STATUE:
                return "block.gravestone.dog_statue";
            case CAT_STATUE:
                return "block.gravestone.cat_statue";
            case HORSE_STATUE:
                return "block.gravestone.horse_statue";
            case CREEPER_STATUE:
                return "block.gravestone.creeper_statue";
            case STARVED_CORPSE:
                return "block.gravestone.starved_corpse";
            case WITHERED_CORPSE:
                return "block.gravestone.withered_corpse";
            case SWORD:
                return "block.gravestone.sword";
        }
    }

}
