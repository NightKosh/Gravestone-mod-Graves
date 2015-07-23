package gravestone.structures.village.undertaker;

import gravestone.core.ModInfo;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UndertakerProfession extends VillagerRegistry.VillagerProfession {

    public UndertakerProfession() {
        //TODO Resources
        super(ModInfo.ID.toLowerCase() + ":undertaker", ModInfo.ID.toLowerCase() + ":textures/entities/undertaker.png");

//        new VillagerRegistry.VillagerCareer(this, "undertaker").init(VanillaTrades.trades[0][0]);
    }
}
