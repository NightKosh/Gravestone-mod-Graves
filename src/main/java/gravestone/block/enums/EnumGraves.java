package gravestone.block.enums;

import gravestone.core.Resources;
import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumGraves implements IBlockEnum {

    // VERTICAL PLATES
    WOODEN_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_WOODEN_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.WOOD),
    SANDSTONE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_SANDSTONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_RED_SANDSTONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_STONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.STONE),
    DIORITE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_DIORITE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.DIORITE),
    ANDESITE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_ANDESITE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.ANDESITE),
    GRANITE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_GRANITE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.GRANITE),
    IRON_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_IRON_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.IRON),
    GOLDEN_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_GOLDEN_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.GOLD),
    DIAMOND_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_DIAMOND_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.DIAMOND),
    EMERALD_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_EMERALD_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.EMERALD),
    LAPIS_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_LAPIS_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.LAPIS),
    REDSTONE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_REDSTONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_OBSIDIAN_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_QUARTZ_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_PRIZMARINE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.PRIZMARINE),
    ICE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_ICE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.ICE),
    // CROSSES
    WOODEN_CROSS("block.gravestone.cross", Resources.GRAVE_WOODEN_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.WOOD),
    SANDSTONE_CROSS("block.gravestone.cross", Resources.GRAVE_SANDSTONE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_CROSS("block.gravestone.cross", Resources.GRAVE_RED_SANDSTONE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.RED_SANDSTONE),
    STONE_CROSS("block.gravestone.cross", Resources.GRAVE_STONE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.STONE),
    DIORITE_CROSS("block.gravestone.cross", Resources.GRAVE_DIORITE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.DIORITE),
    ANDESITE_CROSS("block.gravestone.cross", Resources.GRAVE_ANDESITE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.ANDESITE),
    GRANITE_CROSS("block.gravestone.cross", Resources.GRAVE_GRANITE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.GRANITE),
    IRON_CROSS("block.gravestone.cross", Resources.GRAVE_IRON_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.IRON),
    GOLDEN_CROSS("block.gravestone.cross", Resources.GRAVE_GOLDEN_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.GOLD),
    DIAMOND_CROSS("block.gravestone.cross", Resources.GRAVE_DIAMOND_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.DIAMOND),
    EMERALD_CROSS("block.gravestone.cross", Resources.GRAVE_EMERALD_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.EMERALD),
    LAPIS_CROSS("block.gravestone.cross", Resources.GRAVE_LAPIS_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.LAPIS),
    REDSTONE_CROSS("block.gravestone.cross", Resources.GRAVE_REDSTONE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_CROSS("block.gravestone.cross", Resources.GRAVE_OBSIDIAN_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_CROSS("block.gravestone.cross", Resources.GRAVE_QUARTZ_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_CROSS("block.gravestone.cross", Resources.GRAVE_PRIZMARINE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.PRIZMARINE),
    ICE_CROSS("block.gravestone.cross", Resources.GRAVE_ICE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.ICE),
    // OBELISKS
    WOODEN_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_WOODEN_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.WOOD),
    SANDSTONE_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_SANDSTONE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_RED_SANDSTONE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.RED_SANDSTONE),
    STONE_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_STONE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.STONE),
    DIORITE_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_DIORITE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.DIORITE),
    ANDESITE_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_ANDESITE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.ANDESITE),
    GRANITE_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_GRANITE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.GRANITE),
    IRON_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_IRON_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.IRON),
    GOLDEN_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_GOLDEN_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.GOLD),
    DIAMOND_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_DIAMOND_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.DIAMOND),
    EMERALD_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_EMERALD_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.EMERALD),
    LAPIS_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_LAPIS_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.LAPIS),
    REDSTONE_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_REDSTONE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_OBSIDIAN_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_QUARTZ_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_PRIZMARINE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.PRIZMARINE),
    ICE_OBELISK("block.gravestone.obelisk", Resources.MEMORIAL_ICE_OBELISK, EnumGraveType.OBELISK, EnumGraveMaterial.ICE),
    // HORISONTAL PLATES
    WOODEN_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_WOODEN_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.WOOD),
    SANDSTONE_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_SANDSTONE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_RED_SANDSTONE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_STONE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.STONE),
    DIORITE_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_DIORITE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.DIORITE),
    ANDESITE_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_ANDESITE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.ANDESITE),
    GRANITE_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_GRANITE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.GRANITE),
    IRON_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_IRON_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.IRON),
    GOLDEN_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_GOLDEN_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.GOLD),
    DIAMOND_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_DIAMOND_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.DIAMOND),
    EMERALD_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_EMERALD_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.EMERALD),
    LAPIS_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_LAPIS_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.LAPIS),
    REDSTONE_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_REDSTONE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_OBSIDIAN_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_QUARTZ_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_PRIZMARINE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.PRIZMARINE),
    ICE_HORIZONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_ICE_HORISONTAL_PLATE, EnumGraveType.HORIZONTAL_PLATE, EnumGraveMaterial.ICE),
    // DOGS GRAVES
    WOODEN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_WOODEN_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_SANDSTONE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_RED_SANDSTONE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_STONE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.STONE),
    DIORITE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_DIORITE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_ANDESITE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_GRANITE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.GRANITE),
    IRON_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_IRON_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_GOLDEN_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_DIAMOND_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_EMERALD_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_LAPIS_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_REDSTONE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_OBSIDIAN_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_QUARTZ_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_PRIZMARINE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_ICE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.ICE),
    // CATS GRAVES
    WOODEN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_WOODEN_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_SANDSTONE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_RED_SANDSTONE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_STONE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.STONE),
    DIORITE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_DIORITE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_ANDESITE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_GRANITE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.GRANITE),
    IRON_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_IRON_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_GOLDEN_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_DIAMOND_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_EMERALD_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_LAPIS_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_REDSTONE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_OBSIDIAN_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_QUARTZ_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_PRIZMARINE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_ICE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.ICE),
    // HORSES GRAVES
    WOODEN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_WOODEN_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_SANDSTONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.SANDSTONE),
    RED_SANDSTONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_RED_SANDSTONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.RED_SANDSTONE),
    STONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_STONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.STONE),
    DIORITE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_DIORITE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.DIORITE),
    ANDESITE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_ANDESITE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.ANDESITE),
    GRANITE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_GRANITE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.GRANITE),
    IRON_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_IRON_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_GOLDEN_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_DIAMOND_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_EMERALD_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_LAPIS_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_REDSTONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_OBSIDIAN_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_QUARTZ_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.QUARTZ),
    PRIZMARINE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_PRIZMARINE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.PRIZMARINE),
    ICE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_ICE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.ICE),
    // SWORD
    SWORD("block.gravestone.sword", null, EnumGraveType.SWORD, EnumGraveMaterial.OTHER);

    public enum EnumGraveType implements IEnumGraveType {
        VERTICAL_PLATE,
        CROSS,
        OBELISK,
        HORIZONTAL_PLATE,
        DOG_STATUE,
        CAT_STATUE,
        HORSE_STATUE,
        SWORD
    }

    private String name;
    private ResourceLocation texture;
    private EnumGraveType graveType;
    private EnumGraveMaterial material;

    private EnumGraves(String name, ResourceLocation texture, EnumGraveType graveType, EnumGraveMaterial material) {
        this.name = name;
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
}
