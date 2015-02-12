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
    WOODEN_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_WOODEN_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE),
    SANDSTONE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_SANDSTONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE),
    STONE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_STONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE),
    IRON_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_IRON_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE),
    GOLDEN_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_GOLDEN_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE),
    DIAMOND_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_DIAMOND_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE),
    EMERALD_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_EMERALD_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE),
    LAPIS_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_LAPIS_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE),
    REDSTONE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_REDSTONE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE),
    OBSIDIAN_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_OBSIDIAN_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE),
    QUARTZ_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_QUARTZ_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE),
    ICE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_ICE_VERTICAL_PLATE, EnumGraveType.VERTICAL_PLATE),
    // CROSSES
    WOODEN_CROSS("block.gravestone.cross", Resources.GRAVE_WOODEN_CROSS, EnumGraveType.CROSS),
    SANDSTONE_CROSS("block.gravestone.cross", Resources.GRAVE_SANDSTONE_CROSS, EnumGraveType.CROSS),
    STONE_CROSS("block.gravestone.cross", Resources.GRAVE_STONE_CROSS, EnumGraveType.CROSS),
    IRON_CROSS("block.gravestone.cross", Resources.GRAVE_IRON_CROSS, EnumGraveType.CROSS),
    GOLDEN_CROSS("block.gravestone.cross", Resources.GRAVE_GOLDEN_CROSS, EnumGraveType.CROSS),
    DIAMOND_CROSS("block.gravestone.cross", Resources.GRAVE_DIAMOND_CROSS, EnumGraveType.CROSS),
    EMERALD_CROSS("block.gravestone.cross", Resources.GRAVE_EMERALD_CROSS, EnumGraveType.CROSS),
    LAPIS_CROSS("block.gravestone.cross", Resources.GRAVE_LAPIS_CROSS, EnumGraveType.CROSS),
    REDSTONE_CROSS("block.gravestone.cross", Resources.GRAVE_REDSTONE_CROSS, EnumGraveType.CROSS),
    OBSIDIAN_CROSS("block.gravestone.cross", Resources.GRAVE_OBSIDIAN_CROSS, EnumGraveType.CROSS),
    QUARTZ_CROSS("block.gravestone.cross", Resources.GRAVE_QUARTZ_CROSS, EnumGraveType.CROSS),
    ICE_CROSS("block.gravestone.cross", Resources.GRAVE_ICE_CROSS, EnumGraveType.CROSS),
    // HORISONTAL PLATES
    WOODEN_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_WOODEN_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE),
    SANDSTONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_SANDSTONE_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE),
    STONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_STONE_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE),
    IRON_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_IRON_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE),
    GOLDEN_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_GOLDEN_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE),
    DIAMOND_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_DIAMOND_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE),
    EMERALD_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_EMERALD_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE),
    LAPIS_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_LAPIS_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE),
    REDSTONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_REDSTONE_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE),
    OBSIDIAN_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_OBSIDIAN_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE),
    QUARTZ_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_QUARTZ_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE),
    ICE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_ICE_HORISONTAL_PLATE, EnumGraveType.HORISONTAL_PLATE),
    // DOGS GRAVES
    WOODEN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_WOODEN_DOG_STATUE, EnumGraveType.DOG_STATUE),
    SANDSTONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_SANDSTONE_DOG_STATUE, EnumGraveType.DOG_STATUE),
    STONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_STONE_DOG_STATUE, EnumGraveType.DOG_STATUE),
    IRON_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_IRON_DOG_STATUE, EnumGraveType.DOG_STATUE),
    GOLDEN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_GOLDEN_DOG_STATUE, EnumGraveType.DOG_STATUE),
    DIAMOND_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_DIAMOND_DOG_STATUE, EnumGraveType.DOG_STATUE),
    EMERALD_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_EMERALD_DOG_STATUE, EnumGraveType.DOG_STATUE),
    LAPIS_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_LAPIS_DOG_STATUE, EnumGraveType.DOG_STATUE),
    REDSTONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_REDSTONE_DOG_STATUE, EnumGraveType.DOG_STATUE),
    OBSIDIAN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_OBSIDIAN_DOG_STATUE, EnumGraveType.DOG_STATUE),
    QUARTZ_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_QUARTZ_DOG_STATUE, EnumGraveType.DOG_STATUE),
    ICE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_ICE_DOG_STATUE, EnumGraveType.DOG_STATUE),
    // CATS GRAVES
    WOODEN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_WOODEN_CAT_STATUE, EnumGraveType.CAT_STATUE),
    SANDSTONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_SANDSTONE_CAT_STATUE, EnumGraveType.CAT_STATUE),
    STONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_STONE_CAT_STATUE, EnumGraveType.CAT_STATUE),
    IRON_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_IRON_CAT_STATUE, EnumGraveType.CAT_STATUE),
    GOLDEN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_GOLDEN_CAT_STATUE, EnumGraveType.CAT_STATUE),
    DIAMOND_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_DIAMOND_CAT_STATUE, EnumGraveType.CAT_STATUE),
    EMERALD_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_EMERALD_CAT_STATUE, EnumGraveType.CAT_STATUE),
    LAPIS_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_LAPIS_CAT_STATUE, EnumGraveType.CAT_STATUE),
    REDSTONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_REDSTONE_CAT_STATUE, EnumGraveType.CAT_STATUE),
    OBSIDIAN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_OBSIDIAN_CAT_STATUE, EnumGraveType.CAT_STATUE),
    QUARTZ_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_QUARTZ_CAT_STATUE, EnumGraveType.CAT_STATUE),
    ICE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_ICE_CAT_STATUE, EnumGraveType.CAT_STATUE),
    // HORSES GRAVES
    WOODEN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_WOODEN_HORSE_STATUE, EnumGraveType.HORSE_STATUE),
    SANDSTONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_SANDSTONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE),
    STONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_STONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE),
    IRON_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_IRON_HORSE_STATUE, EnumGraveType.HORSE_STATUE),
    GOLDEN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_GOLDEN_HORSE_STATUE, EnumGraveType.HORSE_STATUE),
    DIAMOND_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_DIAMOND_HORSE_STATUE, EnumGraveType.HORSE_STATUE),
    EMERALD_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_EMERALD_HORSE_STATUE, EnumGraveType.HORSE_STATUE),
    LAPIS_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_LAPIS_HORSE_STATUE, EnumGraveType.HORSE_STATUE),
    REDSTONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_REDSTONE_HORSE_STATUE, EnumGraveType.HORSE_STATUE),
    OBSIDIAN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_OBSIDIAN_HORSE_STATUE, EnumGraveType.HORSE_STATUE),
    QUARTZ_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_QUARTZ_HORSE_STATUE, EnumGraveType.HORSE_STATUE),
    ICE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_ICE_HORSE_STATUE, EnumGraveType.HORSE_STATUE),
    // SWORD
    SWORD("block.gravestone.sword", null, EnumGraveType.SWORD);

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

    private EnumGraves(String name, ResourceLocation texture, EnumGraveType graveType) {
        this.name = name;
        this.texture = texture;
        this.graveType = graveType;
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
