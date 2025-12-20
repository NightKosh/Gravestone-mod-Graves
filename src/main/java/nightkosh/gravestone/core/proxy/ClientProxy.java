package nightkosh.gravestone.core.proxy;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClientProxy extends CommonProxy {

    //TODO
//    @Override
//    public void registerTERenderers() {
//        ClientRegistry.registerTileEntity(GraveStoneBlockEntity.class, "GraveStoneTE", new TileEntityGraveStoneRenderer());
//    }

    @Override
    public String getLocalizedString(String str) {
        //TODO
        return "";//return I18n.translateToLocal(str);
    }

    @Override
    public String getLocalizedEntityName(String name) {
        //TODO
        return "";//return I18n.translateToLocal(name);
    }

}
