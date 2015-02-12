package gravestone.block.enums;

import gravestone.ModGraveStone;
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
    STONE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_STONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.STONE),
    IRON_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_IRON_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.IRON),
    GOLDEN_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_GOLDEN_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.GOLD),
    DIAMOND_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_DIAMOND_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.DIAMOND),
    EMERALD_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_EMERALD_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.EMERALD),
    LAPIS_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_LAPIS_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.LAPIS),
    REDSTONE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_REDSTONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_OBSIDIAN_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_QUARTZ_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.QUARTZ),
    ICE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_ICE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE, EnumGraveMaterial.ICE),
    // CROSSES
    WOODEN_CROSS("block.gravestone.cross", Resources.GRAVE_WOODEN_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.WOOD),
    SANDSTONE_CROSS("block.gravestone.cross", Resources.GRAVE_SANDSTONE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.SANDSTONE),
    STONE_CROSS("block.gravestone.cross", Resources.GRAVE_STONE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.STONE),
    IRON_CROSS("block.gravestone.cross", Resources.GRAVE_IRON_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.IRON),
    GOLDEN_CROSS("block.gravestone.cross", Resources.GRAVE_GOLDEN_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.GOLD),
    DIAMOND_CROSS("block.gravestone.cross", Resources.GRAVE_DIAMOND_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.DIAMOND),
    EMERALD_CROSS("block.gravestone.cross", Resources.GRAVE_EMERALD_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.EMERALD),
    LAPIS_CROSS("block.gravestone.cross", Resources.GRAVE_LAPIS_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.LAPIS),
    REDSTONE_CROSS("block.gravestone.cross", Resources.GRAVE_REDSTONE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_CROSS("block.gravestone.cross", Resources.GRAVE_OBSIDIAN_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_CROSS("block.gravestone.cross", Resources.GRAVE_QUARTZ_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.QUARTZ),
    ICE_CROSS("block.gravestone.cross", Resources.GRAVE_ICE_CROSS, EnumGraveType.CROSS, EnumGraveMaterial.ICE),
    // HORISONTAL PLATES
    WOODEN_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_WOODEN_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE, EnumGraveMaterial.WOOD),
    SANDSTONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_SANDSTONE_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE, EnumGraveMaterial.SANDSTONE),
    STONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_STONE_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE, EnumGraveMaterial.STONE),
    IRON_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_IRON_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE, EnumGraveMaterial.IRON),
    GOLDEN_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_GOLDEN_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE, EnumGraveMaterial.GOLD),
    DIAMOND_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_DIAMOND_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE, EnumGraveMaterial.DIAMOND),
    EMERALD_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_EMERALD_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE, EnumGraveMaterial.EMERALD),
    LAPIS_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_LAPIS_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE, EnumGraveMaterial.LAPIS),
    REDSTONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_REDSTONE_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_OBSIDIAN_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_QUARTZ_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE, EnumGraveMaterial.QUARTZ),
    ICE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_ICE_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE, EnumGraveMaterial.ICE),
    // DOGS GRAVES
    WOODEN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_WOODEN_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_SANDSTONE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.SANDSTONE),
    STONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_STONE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.STONE),
    IRON_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_IRON_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_GOLDEN_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_DIAMOND_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_EMERALD_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_LAPIS_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_REDSTONE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_OBSIDIAN_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_QUARTZ_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.QUARTZ),
    ICE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_ICE_DOG_STATUE, EnumGraveType.DOG_STATUE, EnumGraveMaterial.ICE),
    // CATS GRAVES
    WOODEN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_WOODEN_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_SANDSTONE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.SANDSTONE),
    STONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_STONE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.STONE),
    IRON_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_IRON_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_GOLDEN_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_DIAMOND_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_EMERALD_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_LAPIS_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_REDSTONE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_OBSIDIAN_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_QUARTZ_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.QUARTZ),
    ICE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_ICE_CAT_STATUE, EnumGraveType.CAT_STATUE, EnumGraveMaterial.ICE),
    // HORSES GRAVES
    WOODEN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_WOODEN_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.WOOD),
    SANDSTONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_SANDSTONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.SANDSTONE),
    STONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_STONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.STONE),
    IRON_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_IRON_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.IRON),
    GOLDEN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_GOLDEN_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.GOLD),
    DIAMOND_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_DIAMOND_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.DIAMOND),
    EMERALD_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_EMERALD_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.EMERALD),
    LAPIS_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_LAPIS_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.LAPIS),
    REDSTONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_REDSTONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.REDSTONE),
    OBSIDIAN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_OBSIDIAN_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.OBSIDIAN),
    QUARTZ_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_QUARTZ_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.QUARTZ),
    ICE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_ICE_HORSE_STATUE, EnumGraveType.HORSE_STATUE, EnumGraveMaterial.ICE),
    // SWORD
    SWORD("block.gravestone.sword", null, EnumGraveType.SWORD, EnumGraveMaterial.OTHER);

    public enum EnumGraveType {
        VERTICAL_PLATE,
        HORISONTAL_PLATE,
        CROSS,
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
    public String getLocalizedName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
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
}
