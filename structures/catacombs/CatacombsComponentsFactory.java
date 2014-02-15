
package gravestone.structures.catacombs;

import gravestone.structures.catacombs.components.Bridge;
import gravestone.structures.catacombs.components.CatacombsBaseComponent;
import gravestone.structures.catacombs.components.Corridor;
import gravestone.structures.catacombs.components.CreeperRoom;
import gravestone.structures.catacombs.components.Crossing;
import gravestone.structures.catacombs.components.EnderHall;
import gravestone.structures.catacombs.components.GraveCorridor;
import gravestone.structures.catacombs.components.GraveHall;
import gravestone.structures.catacombs.components.SpidersCorridor;
import gravestone.structures.catacombs.components.StatuesHall;
import gravestone.structures.catacombs.components.TrapCorridor;
import gravestone.structures.catacombs.components.Treasury;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsComponentsFactory {
    
    private CatacombsComponentsFactory() {
        
    }

    /**
     * Return component for level
     */
    public static Class getNextComponentForLevel(Class componentClass, Random random, int level) {
        int chance = random.nextInt(100);

        switch (level) {
            case 1:
                if (chance >= 25) {
                    return getCorridorType(random);
                } else if (chance >= 10) {
                    if (componentClass == Crossing.class) {
                        return getCorridorType(random);
                    } else {
                        return getCrossingType(random);
                    }
                } else if (chance >= 5) {
                    if (componentClass == SpidersCorridor.class) {
                        return getCorridorType(random);
                    } else {
                        return SpidersCorridor.class;
                    }
                } else {
                    if (componentClass == EnderHall.class) {
                        return getCorridorType(random);
                    } else {
                        return EnderHall.class;
                    }
                }
            default:
                if (chance >= 55) {
                    return getCorridorType(random);
                } else if (chance >= 40) {
                    if (componentClass == Crossing.class) {
                        return getCorridorType(random);
                    } else {
                        return getCrossingType(random);
                    }
                } else if (chance >= 30) {
                    if (componentClass == SpidersCorridor.class) {
                        return getCorridorType(random);
                    } else {
                        return SpidersCorridor.class;
                    }
                } else if (chance >= 20) {
                    if (componentClass == EnderHall.class) {
                        return getCorridorType(random);
                    } else {
                        return EnderHall.class;
                    }
                } else if (chance >= 10) {
                    return getHallType(random);
                } else if (chance >= 5) {
                    if (componentClass == Bridge.class) {
                        return getCorridorType(random);
                    } else {
                        return Bridge.class;
                    }
                } else {
                    return Treasury.class;
                }
        }
    }
    
    
    public static Class getNextComponent(Class componentClass, CatacombsLevel.COMPONENT_SIDE componentSide, Random random, int level) {
        if (componentSide == CatacombsLevel.COMPONENT_SIDE.TOP) {
            return CatacombsComponentsFactory.getNextComponentForLevel(componentClass, random, level);
        } else {
            if (level == 1 || random.nextInt(100) >= 5) {
                return Corridor.class;
            } else {
                return Treasury.class;
            }
        }
    }
    
    /**
     * Return ranfom class of corridor component
     */
    public static Class getCorridorType(Random random) {
        int corridorChance = random.nextInt(100);

        if (corridorChance >= 65) {
            return Corridor.class;
        } else if (corridorChance >= 10) {
            return GraveCorridor.class;
        } else {
            return TrapCorridor.class;
        }
    }
    
    /**
     * Return ranfom class of crossing component
     */
    private static Class getCrossingType(Random random) {
        if (random.nextInt(100) >= 10) {
            return Crossing.class;
        } else {
            return CreeperRoom.class;
        }
    }
    
    private static Class getHallType(Random random) {
        int hallChance = random.nextInt(10);

        if (hallChance >= 2) {
            return GraveHall.class;
        } else {
            return StatuesHall.class;
        }
    }
    
    /**
     * Create component
     * @param component previouse component
     * @param direction component direction
     * @param buildComponent component class
     * @param componentSide 
     */
    public static CatacombsBaseComponent createComponent(CatacombsBaseComponent component, Random random, int direction, int level, Class buildComponent, CatacombsLevel.COMPONENT_SIDE componentSide) {
        if (component != null) {
            int x, y, z;
            y = component.getYEnd();

            if (componentSide == CatacombsLevel.COMPONENT_SIDE.TOP) {
                x = component.getTopXEnd();
                z = component.getTopZEnd();
            } else if (componentSide == CatacombsLevel.COMPONENT_SIDE.LEFT) {
                x = component.getLeftXEnd();
                z = component.getLeftZEnd();
            } else {
                x = component.getRightXEnd();
                z = component.getRightZEnd();
            }

            try {
                Constructor<CatacombsBaseComponent> constructor = buildComponent.getConstructor(int.class, int.class, Random.class, int.class, int.class, int.class);
                component = constructor.newInstance(direction, level, random, x, y, z);
                return component;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
