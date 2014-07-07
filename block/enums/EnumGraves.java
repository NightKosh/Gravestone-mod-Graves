package gravestone.block.enums;

import gravestone.ModGraveStone;
import gravestone.core.Resources;
import gravestone.models.block.ModelGraveStone;
import gravestone.renderer.tileentity.TileEntityGSGraveStoneRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumGraves implements IBlockEnum {

    STONE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_STONE_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    STONE_CROSS("block.gravestone.cross", Resources.GRAVE_STONE_CROSS, TileEntityGSGraveStoneRenderer.cross),
    STONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_STONE_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    STONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_STONE_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    STONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_STONE_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    WOODEN_SWORD("block.gravestone.wooden_sword", Resources.GRAVE_WOODEN_SWORD, TileEntityGSGraveStoneRenderer.swordGrave),
    STONE_SWORD("block.gravestone.stone_sword", Resources.GRAVE_STONE_SWORD, TileEntityGSGraveStoneRenderer.swordGrave),
    IRON_SWORD("block.gravestone.iron_sword", Resources.GRAVE_IRON_SWORD, TileEntityGSGraveStoneRenderer.swordGrave),
    GOLDEN_SWORD("block.gravestone.golden_sword", Resources.GRAVE_GOLDEN_SWORD, TileEntityGSGraveStoneRenderer.swordGrave),
    DIAMOND_SWORD("block.gravestone.diamond_sword", Resources.GRAVE_DIAMOND_SWORD, TileEntityGSGraveStoneRenderer.swordGrave),
    STONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_STONE_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    // VERTICAL PLATES
    WOODEN_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_WOODEN_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    SANDSTONE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_SANDSTONE_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    IRON_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_IRON_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    GOLDEN_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_GOLDEN_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    DIAMOND_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_DIAMOND_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    EMERALD_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_EMERALD_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    LAPIS_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_LAPIS_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    REDSTONE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_REDSTONE_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    OBSIDIAN_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_OBSIDIAN_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    QUARTZ_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_QUARTZ_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    ICE_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_ICE_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    MOSSY_VERTICAL_PLATE("block.gravestone.gravestone", Resources.GRAVE_MOSSY_VERTICAL_PLATE, TileEntityGSGraveStoneRenderer.verticalPlate),
    // CROSSES
    WOODEN_CROSS("block.gravestone.cross", Resources.GRAVE_WOODEN_CROSS, TileEntityGSGraveStoneRenderer.cross),
    SANDSTONE_CROSS("block.gravestone.cross", Resources.GRAVE_SANDSTONE_CROSS, TileEntityGSGraveStoneRenderer.cross),
    IRON_CROSS("block.gravestone.cross", Resources.GRAVE_IRON_CROSS, TileEntityGSGraveStoneRenderer.cross),
    GOLDEN_CROSS("block.gravestone.cross", Resources.GRAVE_GOLDEN_CROSS, TileEntityGSGraveStoneRenderer.cross),
    DIAMOND_CROSS("block.gravestone.cross", Resources.GRAVE_DIAMOND_CROSS, TileEntityGSGraveStoneRenderer.cross),
    EMERALD_CROSS("block.gravestone.cross", Resources.GRAVE_EMERALD_CROSS, TileEntityGSGraveStoneRenderer.cross),
    LAPIS_CROSS("block.gravestone.cross", Resources.GRAVE_LAPIS_CROSS, TileEntityGSGraveStoneRenderer.cross),
    REDSTONE_CROSS("block.gravestone.cross", Resources.GRAVE_REDSTONE_CROSS, TileEntityGSGraveStoneRenderer.cross),
    OBSIDIAN_CROSS("block.gravestone.cross", Resources.GRAVE_OBSIDIAN_CROSS, TileEntityGSGraveStoneRenderer.cross),
    QUARTZ_CROSS("block.gravestone.cross", Resources.GRAVE_QUARTZ_CROSS, TileEntityGSGraveStoneRenderer.cross),
    ICE_CROSS("block.gravestone.cross", Resources.GRAVE_ICE_CROSS, TileEntityGSGraveStoneRenderer.cross),
    MOSSY_CROSS("block.gravestone.cross", Resources.GRAVE_MOSSY_CROSS, TileEntityGSGraveStoneRenderer.cross),
    // HORISONTAL PLATES
    WOODEN_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_WOODEN_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    SANDSTONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_SANDSTONE_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    IRON_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_IRON_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    GOLDEN_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_GOLDEN_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    DIAMOND_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_DIAMOND_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    EMERALD_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_EMERALD_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    LAPIS_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_LAPIS_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    REDSTONE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_REDSTONE_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    OBSIDIAN_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_OBSIDIAN_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    QUARTZ_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_QUARTZ_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    ICE_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_ICE_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    MOSSY_HORISONTAL_PLATE("block.gravestone.plate", Resources.GRAVE_MOSSY_HORISONTAL_PLATE, TileEntityGSGraveStoneRenderer.horisontalPlate),
    // DOGS GRAVES
    WOODEN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_WOODEN_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    SANDSTONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_SANDSTONE_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    IRON_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_IRON_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    GOLDEN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_GOLDEN_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    DIAMOND_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_DIAMOND_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    EMERALD_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_EMERALD_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    LAPIS_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_LAPIS_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    REDSTONE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_REDSTONE_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    OBSIDIAN_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_OBSIDIAN_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    QUARTZ_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_QUARTZ_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    ICE_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_ICE_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    MOSSY_DOG_STATUE("block.gravestone.dog_statue", Resources.GRAVE_MOSSY_DOG_STATUE, TileEntityGSGraveStoneRenderer.dogStatue),
    // CATS GRAVES
    WOODEN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_WOODEN_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    SANDSTONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_SANDSTONE_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    IRON_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_IRON_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    GOLDEN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_GOLDEN_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    DIAMOND_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_DIAMOND_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    EMERALD_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_EMERALD_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    LAPIS_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_LAPIS_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    REDSTONE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_REDSTONE_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    OBSIDIAN_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_OBSIDIAN_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    QUARTZ_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_QUARTZ_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    ICE_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_ICE_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    MOSSY_CAT_STATUE("block.gravestone.cat_statue", Resources.GRAVE_MOSSY_CAT_STATUE, TileEntityGSGraveStoneRenderer.catStatue),
    // HORSES GRAVES
    WOODEN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_WOODEN_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    SANDSTONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_SANDSTONE_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    IRON_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_IRON_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    GOLDEN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_GOLDEN_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    DIAMOND_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_DIAMOND_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    EMERALD_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_EMERALD_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    LAPIS_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_LAPIS_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    REDSTONE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_REDSTONE_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    OBSIDIAN_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_OBSIDIAN_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    QUARTZ_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_QUARTZ_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    ICE_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_ICE_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    MOSSY_HORSE_STATUE("block.gravestone.horse_statue", Resources.GRAVE_MOSSY_HORSE_STATUE, TileEntityGSGraveStoneRenderer.horseStatue),
    SWORD("block.gravestone.sword", null, null);
    private String name;
    private ResourceLocation texture;
    private ModelGraveStone model;

    private EnumGraves(String name, ResourceLocation texture, ModelGraveStone model) {
        this.name = name;
        this.texture = texture;
        this.model = model;
    }

    @Override
    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public ModelGraveStone getModel() {
        return this.model;
    }

    /**
     * Returns the grave type with the specified ID, or VERTICAL_PLATE if none found.
     *
     * @param id Grave Id
     */
    public static EnumGraves getByID(int id) {
        if (id < values().length) {
            return values()[id];
        }
        return STONE_VERTICAL_PLATE;
    }
}
