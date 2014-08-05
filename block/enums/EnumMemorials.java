package gravestone.block.enums;

import gravestone.ModGraveStone;
import gravestone.core.Resources;
import gravestone.models.block.ModelMemorial;
import gravestone.renderer.tileentity.TileEntityGSMemorialRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumMemorials implements IBlockEnum {

    STONE_CROSS("block.memorial.cross", Resources.MEMORIAL_STONE_CROSS, TileEntityGSMemorialRenderer.cross, null),
    QUARTZ_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_QUARTZ_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    STONE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_STONE_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_STONE_BIG_PEDESTAL),
    STONE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_STONE_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_STONE_BIG_PEDESTAL),
    STONE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_STONE_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_STONE_BIG_PEDESTAL),
    STONE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_STONE_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_STONE_BIG_PEDESTAL),
    STONE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_STONE_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_STONE_BIG_PEDESTAL),
    STONE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_STONE_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_STONE_BIG_PEDESTAL),
    // crosses
    WOODEN_CROSS("block.memorial.cross", Resources.MEMORIAL_WOODEN_CROSS, TileEntityGSMemorialRenderer.cross, null),
    SANDSTONE_CROSS("block.memorial.cross", Resources.MEMORIAL_SANDSTONE_CROSS, TileEntityGSMemorialRenderer.cross, null),
    MOSSY_CROSS("block.memorial.cross", Resources.MEMORIAL_MOSSY_CROSS, TileEntityGSMemorialRenderer.cross, null),
    IRON_CROSS("block.memorial.cross", Resources.MEMORIAL_IRON_CROSS, TileEntityGSMemorialRenderer.cross, null),
    GOLDEN_CROSS("block.memorial.cross", Resources.MEMORIAL_GOLDEN_CROSS, TileEntityGSMemorialRenderer.cross, null),
    DIAMOND_CROSS("block.memorial.cross", Resources.MEMORIAL_DIAMOND_CROSS, TileEntityGSMemorialRenderer.cross, null),
    EMERALD_CROSS("block.memorial.cross", Resources.MEMORIAL_EMERALD_CROSS, TileEntityGSMemorialRenderer.cross, null),
    LAPIS_CROSS("block.memorial.cross", Resources.MEMORIAL_LAPIS_CROSS, TileEntityGSMemorialRenderer.cross, null),
    REDSTONE_CROSS("block.memorial.cross", Resources.MEMORIAL_REDSTONE_CROSS, TileEntityGSMemorialRenderer.cross, null),
    OBSIDIAN_CROSS("block.memorial.cross", Resources.MEMORIAL_OBSIDIAN_CROSS, TileEntityGSMemorialRenderer.cross, null),
    QUARTZ_CROSS("block.memorial.cross", Resources.MEMORIAL_QUARTZ_CROSS, TileEntityGSMemorialRenderer.cross, null),
    ICE_CROSS("block.memorial.cross", Resources.MEMORIAL_ICE_CROSS, TileEntityGSMemorialRenderer.cross, null),
    // obelisks
    WOODEN_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_WOODEN_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    SANDSTONE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_SANDSTONE_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    STONE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_STONE_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    MOSSY_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_MOSSY_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    IRON_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_IRON_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    GOLDEN_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_GOLDEN_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    DIAMOND_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_DIAMOND_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    EMERALD_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_EMERALD_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    LAPIS_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_LAPIS_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    REDSTONE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_REDSTONE_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    OBSIDIAN_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_OBSIDIAN_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    ICE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_ICE_OBELISK, TileEntityGSMemorialRenderer.obelisk, null),
    // steve memorials
    WOODEN_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_WOODEN_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL),
    SANDSTONE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_SANDSTONE_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL),
    MOSSY_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_MOSSY_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_MOSSY_BIG_PEDESTAL),
    IRON_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_IRON_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_IRON_BIG_PEDESTAL),
    GOLDEN_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_GOLDEN_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL),
    DIAMOND_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_DIAMOND_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL),
    EMERALD_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_EMERALD_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL),
    LAPIS_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_LAPIS_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL),
    REDSTONE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_REDSTONE_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL),
    OBSIDIAN_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_OBSIDIAN_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL),
    QUARTZ_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_QUARTZ_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL),
    ICE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_ICE_STEVE_STATUE, TileEntityGSMemorialRenderer.steveStatue, Resources.MEMORIAL_ICE_BIG_PEDESTAL),
    // villagers memorials
    WOODEN_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_WOODEN_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL),
    SANDSTONE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_SANDSTONE_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL),
    MOSSY_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_MOSSY_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_MOSSY_BIG_PEDESTAL),
    IRON_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_IRON_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_IRON_BIG_PEDESTAL),
    GOLDEN_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_GOLDEN_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL),
    DIAMOND_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_DIAMOND_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL),
    EMERALD_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_EMERALD_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL),
    LAPIS_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_LAPIS_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL),
    REDSTONE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_REDSTONE_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL),
    OBSIDIAN_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_OBSIDIAN_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL),
    QUARTZ_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_QUARTZ_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL),
    ICE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_ICE_VILLAGER_STATUE, TileEntityGSMemorialRenderer.villagerStatue, Resources.MEMORIAL_ICE_BIG_PEDESTAL),
    // angel memorials
    WOODEN_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_WOODEN_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL),
    SANDSTONE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_SANDSTONE_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL),
    MOSSY_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_MOSSY_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_MOSSY_BIG_PEDESTAL),
    IRON_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_IRON_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_IRON_BIG_PEDESTAL),
    GOLDEN_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_GOLDEN_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL),
    DIAMOND_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_DIAMOND_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL),
    EMERALD_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_EMERALD_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL),
    LAPIS_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_LAPIS_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL),
    REDSTONE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_REDSTONE_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL),
    OBSIDIAN_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_OBSIDIAN_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL),
    QUARTZ_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_QUARTZ_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL),
    ICE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_ICE_ANGEL_STATUE, TileEntityGSMemorialRenderer.angelStatue, Resources.MEMORIAL_ICE_BIG_PEDESTAL),
    // dogs memorial
    WOODEN_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_WOODEN_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL),
    SANDSTONE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_SANDSTONE_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL),
    MOSSY_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_MOSSY_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_MOSSY_BIG_PEDESTAL),
    IRON_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_IRON_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_IRON_BIG_PEDESTAL),
    GOLDEN_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_GOLDEN_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL),
    DIAMOND_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_DIAMOND_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL),
    EMERALD_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_EMERALD_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL),
    LAPIS_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_LAPIS_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL),
    REDSTONE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_REDSTONE_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL),
    OBSIDIAN_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_OBSIDIAN_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL),
    QUARTZ_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_QUARTZ_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL),
    ICE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_ICE_DOG_STATUE, TileEntityGSMemorialRenderer.dogStatue, Resources.MEMORIAL_ICE_BIG_PEDESTAL),
    // dogs memorial
    WOODEN_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_WOODEN_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL),
    SANDSTONE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_SANDSTONE_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL),
    MOSSY_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_MOSSY_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_MOSSY_BIG_PEDESTAL),
    IRON_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_IRON_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_IRON_BIG_PEDESTAL),
    GOLDEN_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_GOLDEN_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL),
    DIAMOND_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_DIAMOND_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL),
    EMERALD_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_EMERALD_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL),
    LAPIS_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_LAPIS_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL),
    REDSTONE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_REDSTONE_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL),
    OBSIDIAN_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_OBSIDIAN_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL),
    QUARTZ_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_QUARTZ_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL),
    ICE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_ICE_CAT_STATUE, TileEntityGSMemorialRenderer.catStatue, Resources.MEMORIAL_ICE_BIG_PEDESTAL),
    // creepers memorial
    WOODEN_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_WOODEN_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL),
    SANDSTONE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_SANDSTONE_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL),
    MOSSY_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_MOSSY_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_MOSSY_BIG_PEDESTAL),
    IRON_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_IRON_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_IRON_BIG_PEDESTAL),
    GOLDEN_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_GOLDEN_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL),
    DIAMOND_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_DIAMOND_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL),
    EMERALD_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_EMERALD_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL),
    LAPIS_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_LAPIS_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL),
    REDSTONE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_REDSTONE_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL),
    OBSIDIAN_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_OBSIDIAN_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL),
    QUARTZ_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_QUARTZ_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL),
    ICE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_ICE_CREEPER_STATUE, TileEntityGSMemorialRenderer.creeperStatue, Resources.MEMORIAL_ICE_BIG_PEDESTAL);
    //CELTIC_CROSS("block.memorial.celtic_cross");
    private String name;
    private ResourceLocation texture;
    private ModelMemorial model;
    private ResourceLocation pedestalTexture;

    private EnumMemorials(String name, ResourceLocation texture, ModelMemorial model, ResourceLocation pedestalTexture) {
        this.name = name;
        this.texture = texture;
        this.model = model;
        this.pedestalTexture = pedestalTexture;
    }

    @Override
    public String getName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public ResourceLocation getPedestalTexture() {
        return this.pedestalTexture;
    }

    public ModelMemorial getModel() {
        return this.model;
    }

    /**
     * Returns the grave type with the specified ID, or VERTICAL_PLATE if none
     * found.
     *
     * @param id Grave Id
     */
    public static EnumMemorials getByID(int id) {
        if (id < values().length) {
            return values()[id];
        }
        return STONE_CROSS;
    }
}
