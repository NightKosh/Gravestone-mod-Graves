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
public enum EnumMemorials implements IBlockEnum {

    // crosses
    WOODEN_CROSS("block.memorial.cross", Resources.MEMORIAL_WOODEN_CROSS, null, EnumMemorialType.CROSS),
    SANDSTONE_CROSS("block.memorial.cross", Resources.MEMORIAL_SANDSTONE_CROSS, null, EnumMemorialType.CROSS),
    STONE_CROSS("block.memorial.cross", Resources.MEMORIAL_STONE_CROSS, null, EnumMemorialType.CROSS),
    IRON_CROSS("block.memorial.cross", Resources.MEMORIAL_IRON_CROSS, null, EnumMemorialType.CROSS),
    GOLDEN_CROSS("block.memorial.cross", Resources.MEMORIAL_GOLDEN_CROSS, null, EnumMemorialType.CROSS),
    DIAMOND_CROSS("block.memorial.cross", Resources.MEMORIAL_DIAMOND_CROSS, null, EnumMemorialType.CROSS),
    EMERALD_CROSS("block.memorial.cross", Resources.MEMORIAL_EMERALD_CROSS, null, EnumMemorialType.CROSS),
    LAPIS_CROSS("block.memorial.cross", Resources.MEMORIAL_LAPIS_CROSS, null, EnumMemorialType.CROSS),
    REDSTONE_CROSS("block.memorial.cross", Resources.MEMORIAL_REDSTONE_CROSS, null, EnumMemorialType.CROSS),
    OBSIDIAN_CROSS("block.memorial.cross", Resources.MEMORIAL_OBSIDIAN_CROSS, null, EnumMemorialType.CROSS),
    QUARTZ_CROSS("block.memorial.cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CROSS),
    ICE_CROSS("block.memorial.cross", Resources.MEMORIAL_ICE_CROSS, null, EnumMemorialType.CROSS),
    // obelisks
    WOODEN_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_WOODEN_OBELISK, null, EnumMemorialType.OBELISK),
    SANDSTONE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_SANDSTONE_OBELISK, null, EnumMemorialType.OBELISK),
    STONE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_STONE_OBELISK, null, EnumMemorialType.OBELISK),
    IRON_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_IRON_OBELISK, null, EnumMemorialType.OBELISK),
    GOLDEN_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_GOLDEN_OBELISK, null, EnumMemorialType.OBELISK),
    DIAMOND_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_DIAMOND_OBELISK, null, EnumMemorialType.OBELISK),
    EMERALD_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_EMERALD_OBELISK, null, EnumMemorialType.OBELISK),
    LAPIS_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_LAPIS_OBELISK, null, EnumMemorialType.OBELISK),
    REDSTONE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_REDSTONE_OBELISK, null, EnumMemorialType.OBELISK),
    OBSIDIAN_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_OBSIDIAN_OBELISK, null, EnumMemorialType.OBELISK),
    QUARTZ_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_QUARTZ_OBELISK, null, EnumMemorialType.OBELISK),
    ICE_OBELISK("block.memorial.obelisk", Resources.MEMORIAL_ICE_OBELISK, null, EnumMemorialType.OBELISK),
    // steve memorials
    WOODEN_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_WOODEN_STEVE_STATUE, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE),
    SANDSTONE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_SANDSTONE_STEVE_STATUE, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE),
    STONE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_STONE_STEVE_STATUE, Resources.MEMORIAL_STONE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE),
    IRON_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_IRON_STEVE_STATUE, Resources.MEMORIAL_IRON_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE),
    GOLDEN_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_GOLDEN_STEVE_STATUE, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE),
    DIAMOND_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_DIAMOND_STEVE_STATUE, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE),
    EMERALD_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_EMERALD_STEVE_STATUE, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE),
    LAPIS_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_LAPIS_STEVE_STATUE, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE),
    REDSTONE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_REDSTONE_STEVE_STATUE, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE),
    OBSIDIAN_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_OBSIDIAN_STEVE_STATUE, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE),
    QUARTZ_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_QUARTZ_STEVE_STATUE, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE),
    ICE_STEVE_STATUE("block.memorial.steve_statue", Resources.MEMORIAL_ICE_STEVE_STATUE, Resources.MEMORIAL_ICE_BIG_PEDESTAL, EnumMemorialType.STEVE_STATUE),
    // villagers memorials
    WOODEN_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_WOODEN_VILLAGER_STATUE, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE),
    SANDSTONE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_SANDSTONE_VILLAGER_STATUE, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE),
    STONE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_STONE_VILLAGER_STATUE, Resources.MEMORIAL_STONE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE),
    IRON_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_IRON_VILLAGER_STATUE, Resources.MEMORIAL_IRON_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE),
    GOLDEN_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_GOLDEN_VILLAGER_STATUE, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE),
    DIAMOND_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_DIAMOND_VILLAGER_STATUE, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE),
    EMERALD_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_EMERALD_VILLAGER_STATUE, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE),
    LAPIS_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_LAPIS_VILLAGER_STATUE, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE),
    REDSTONE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_REDSTONE_VILLAGER_STATUE, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE),
    OBSIDIAN_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_OBSIDIAN_VILLAGER_STATUE, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE),
    QUARTZ_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_QUARTZ_VILLAGER_STATUE, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE),
    ICE_VILLAGER_STATUE("block.memorial.villager_statue", Resources.MEMORIAL_ICE_VILLAGER_STATUE, Resources.MEMORIAL_ICE_BIG_PEDESTAL, EnumMemorialType.VILLAGER_STATUE),
    // angel memorials
    WOODEN_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_WOODEN_ANGEL_STATUE, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE),
    SANDSTONE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_SANDSTONE_ANGEL_STATUE, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE),
    STONE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_STONE_ANGEL_STATUE, Resources.MEMORIAL_STONE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE),
    IRON_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_IRON_ANGEL_STATUE, Resources.MEMORIAL_IRON_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE),
    GOLDEN_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_GOLDEN_ANGEL_STATUE, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE),
    DIAMOND_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_DIAMOND_ANGEL_STATUE, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE),
    EMERALD_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_EMERALD_ANGEL_STATUE, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE),
    LAPIS_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_LAPIS_ANGEL_STATUE, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE),
    REDSTONE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_REDSTONE_ANGEL_STATUE, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE),
    OBSIDIAN_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_OBSIDIAN_ANGEL_STATUE, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE),
    QUARTZ_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_QUARTZ_ANGEL_STATUE, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE),
    ICE_ANGEL_STATUE("block.memorial.angel_statue", Resources.MEMORIAL_ICE_ANGEL_STATUE, Resources.MEMORIAL_ICE_BIG_PEDESTAL, EnumMemorialType.ANGEL_STATUE),
    // dogs memorial
    WOODEN_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_WOODEN_DOG_STATUE, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE),
    SANDSTONE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_SANDSTONE_DOG_STATUE, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE),
    STONE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_STONE_DOG_STATUE, Resources.MEMORIAL_STONE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE),
    IRON_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_IRON_DOG_STATUE, Resources.MEMORIAL_IRON_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE),
    GOLDEN_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_GOLDEN_DOG_STATUE, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE),
    DIAMOND_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_DIAMOND_DOG_STATUE, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE),
    EMERALD_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_EMERALD_DOG_STATUE, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE),
    LAPIS_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_LAPIS_DOG_STATUE, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE),
    REDSTONE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_REDSTONE_DOG_STATUE, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE),
    OBSIDIAN_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_OBSIDIAN_DOG_STATUE, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE),
    QUARTZ_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_QUARTZ_DOG_STATUE, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE),
    ICE_DOG_STATUE("block.memorial.dog_statue", Resources.MEMORIAL_ICE_DOG_STATUE, Resources.MEMORIAL_ICE_BIG_PEDESTAL, EnumMemorialType.DOG_STATUE),
    // dogs memorial
    WOODEN_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_WOODEN_CAT_STATUE, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE),
    SANDSTONE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_SANDSTONE_CAT_STATUE, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE),
    STONE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_STONE_CAT_STATUE, Resources.MEMORIAL_STONE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE),
    IRON_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_IRON_CAT_STATUE, Resources.MEMORIAL_IRON_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE),
    GOLDEN_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_GOLDEN_CAT_STATUE, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE),
    DIAMOND_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_DIAMOND_CAT_STATUE, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE),
    EMERALD_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_EMERALD_CAT_STATUE, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE),
    LAPIS_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_LAPIS_CAT_STATUE, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE),
    REDSTONE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_REDSTONE_CAT_STATUE, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE),
    OBSIDIAN_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_OBSIDIAN_CAT_STATUE, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE),
    QUARTZ_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_QUARTZ_CAT_STATUE, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE),
    ICE_CAT_STATUE("block.memorial.cat_statue", Resources.MEMORIAL_ICE_CAT_STATUE, Resources.MEMORIAL_ICE_BIG_PEDESTAL, EnumMemorialType.CAT_STATUE),
    // creepers memorial
    WOODEN_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_WOODEN_CREEPER_STATUE, Resources.MEMORIAL_WOODEN_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE),
    SANDSTONE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_SANDSTONE_CREEPER_STATUE, Resources.MEMORIAL_SANDSTONE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE),
    STONE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_STONE_CREEPER_STATUE, Resources.MEMORIAL_STONE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE),
    IRON_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_IRON_CREEPER_STATUE, Resources.MEMORIAL_IRON_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE),
    GOLDEN_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_GOLDEN_CREEPER_STATUE, Resources.MEMORIAL_GOLDEN_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE),
    DIAMOND_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_DIAMOND_CREEPER_STATUE, Resources.MEMORIAL_DIAMOND_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE),
    EMERALD_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_EMERALD_CREEPER_STATUE, Resources.MEMORIAL_EMERALD_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE),
    LAPIS_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_LAPIS_CREEPER_STATUE, Resources.MEMORIAL_LAPIS_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE),
    REDSTONE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_REDSTONE_CREEPER_STATUE, Resources.MEMORIAL_REDSTONE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE),
    OBSIDIAN_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_OBSIDIAN_CREEPER_STATUE, Resources.MEMORIAL_OBSIDIAN_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE),
    QUARTZ_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_QUARTZ_CREEPER_STATUE, Resources.MEMORIAL_QUARTZ_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE),
    ICE_CREEPER_STATUE("block.memorial.creeper_statue", Resources.MEMORIAL_ICE_CREEPER_STATUE, Resources.MEMORIAL_ICE_BIG_PEDESTAL, EnumMemorialType.CREEPER_STATUE),
    // celtic crosses
    WOODEN_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS),
    SANDSTONE_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS),
    IRON_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS),
    GOLDEN_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS),
    DIAMOND_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS),
    EMERALD_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS),
    LAPIS_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS),
    REDSTONE_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS),
    OBSIDIAN_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS),
    QUARTZ_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS),
    ICE_CELTIC_CROSS("block.memorial.celtic_cross", Resources.MEMORIAL_QUARTZ_CROSS, null, EnumMemorialType.CELTIC_CROSS),
    // gibbets
    GIBBET("block.memorial.gibbet", Resources.MEMORIAL_GIBBET, null, EnumMemorialType.GIBBET),
    // stocks
    STOCKS("block.memorial.stocks", Resources.MEMORIAL_STOCKS, null, EnumMemorialType.STOCKS),
    // burning stake
    BURNING_STAKE("block.memorial.burning_stake", Resources.BURNING_STAKE, null, EnumMemorialType.BURNING_STAKE);

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

    private EnumMemorials(String name, ResourceLocation texture, ResourceLocation pedestalTexture, EnumMemorialType memorialType) {
        this.name = name;
        this.texture = texture;
        this.pedestalTexture = pedestalTexture;
        this.memorialType = memorialType;
    }

    @Override
    public String getLocalizedName() {
        return ModGraveStone.proxy.getLocalizedString(this.name);
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
}
