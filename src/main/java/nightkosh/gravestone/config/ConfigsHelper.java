package nightkosh.gravestone.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import nightkosh.gravestone.core.logger.GSLogger;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ConfigsHelper {

    public static List<Integer> getDimensionList(Configuration config, String category, String ConfigID, String defaultValue, String comment) {
        Property dimensionIdProperty = config.get(category, ConfigID, defaultValue);
        dimensionIdProperty.setComment(comment);
        String ar = dimensionIdProperty.getString();
        String[] ids = ar.split(";");
        List<Integer> dimensionIds = new ArrayList<>(ids.length);
        for (String id : ids) {
            try {
                if (StringUtils.isNotBlank(id)) {
                    dimensionIds.add(Integer.parseInt(id));
                }
            } catch (NumberFormatException e) {
                GSLogger.logError("Can't parse Dimension Id list!!!");
                e.printStackTrace();
            }
        }
        if (dimensionIds.isEmpty() && StringUtils.isNotBlank(defaultValue)) {
            try {
                dimensionIds.add(Integer.parseInt(defaultValue));
            } catch (NumberFormatException e) {
                GSLogger.logError("Can't parse Dimension Id list!!!");
                e.printStackTrace();
            }
        }
        return dimensionIds;
    }

    public static List<Integer> getDimensionList(Configuration config, String category, String ConfigID, int defaultValue, String comment) {
        return getDimensionList(config, category, ConfigID, Integer.toString(defaultValue), comment);
    }
}
