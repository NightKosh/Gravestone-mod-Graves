package gravestone.structures.catacombs;

import gravestone.structures.catacombs.components.CatacombsBaseComponent;
import gravestone.structures.catacombs.components.CreeperRoom;
import gravestone.structures.catacombs.components.Crossing;
import gravestone.structures.catacombs.components.TrapCorridor;
import gravestone.structures.catacombs.components.Treasury;
import gravestone.structures.catacombs.components.Bridge;
import gravestone.structures.catacombs.components.Corridor;
import gravestone.structures.catacombs.components.EnderHall;
import gravestone.structures.catacombs.components.GraveCorridor;
import gravestone.structures.catacombs.components.GraveHall;
import gravestone.structures.catacombs.components.SpidersCorridor;
import gravestone.structures.catacombs.components.Stairs;
import gravestone.structures.catacombs.components.StatuesHall;
import gravestone.structures.catacombs.components.WitherHall;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsLevel {

    private final int level;
    private Random random;
    private World world;
    private int totalComponentsCount;
    private int componentsCount;
    private LinkedList levelComponents = new LinkedList();
    private LinkedList endComponents = new LinkedList();

    private static enum COMPONENT_SIDE {

        TOP,
        LEFT,
        RIGHT
    }

    public CatacombsLevel(LinkedList<CatacombsBaseComponent> startComponents, int level, World world, Random random) {
        levelComponents = startComponents;
        this.random = random;
        this.world = world;
        this.level = level;

        switch (level) {
            case 1:
                totalComponentsCount = 30 + random.nextInt(30);
                break;
            case 2:
                totalComponentsCount = 60 + random.nextInt(60);
                break;
            case 3:
                totalComponentsCount = 90 + random.nextInt(90);
                break;
            case 4:
                totalComponentsCount = 120 + random.nextInt(120);
                break;
            default:
                totalComponentsCount = 150 + random.nextInt(150);
                break;
        }

        componentsCount = 0;
        prepareLevel(levelComponents);
        generateLevel();
    }

    /*
     * Prepare Level pieces
     */
    public final void prepareLevel(LinkedList<CatacombsBaseComponent> currentComponents) {
        LinkedList<CatacombsBaseComponent> newComponents = new LinkedList();
        CatacombsBaseComponent[] components = new CatacombsBaseComponent[0];
        components = currentComponents.toArray(components);
        CatacombsBaseComponent component;
        int resultComponentsCount = 0;

        if (totalComponentsCount > componentsCount) {
            for (int i = 0; i < components.length; i++) {
                component = components[i];

                if (component.canGoOnlyTop()) {
                    if (component.goTop) {
                        resultComponentsCount += addComponent(newComponents, component, component.getDirection(), COMPONENT_SIDE.TOP);
                    }
                } else {
                    if (component.goTop) {
                        resultComponentsCount += addComponent(newComponents, component, component.getDirection(), COMPONENT_SIDE.TOP);
                        resultComponentsCount += addComponent(newComponents, component, component.getLeftDirection(component.getDirection()), COMPONENT_SIDE.LEFT);
                        resultComponentsCount += addComponent(newComponents, component, component.getRightDirection(component.getDirection()), COMPONENT_SIDE.RIGHT);
                    }
                }
            }
        } else if (endComponents.isEmpty()) {
            createEnd(currentComponents);
        }

        componentsCount += resultComponentsCount;

        if (resultComponentsCount != 0) {
            prepareLevel(newComponents);
        }
    }

    /*
     * Create and add new component if it available
     */
    private int addComponent(LinkedList<CatacombsBaseComponent> newComponents, CatacombsBaseComponent component, int direction, COMPONENT_SIDE componentSide) {
        CatacombsBaseComponent newComponent = tryCreateComponent(component, direction, componentSide);

        if (newComponent != null) {
            newComponents.add(newComponent);
            return 1;
        } else {
            return 0;
        }
    }

    /*
     * Create level end components
     */
    private void createEnd(LinkedList<CatacombsBaseComponent> currentComponents) {
        LinkedList<CatacombsBaseComponent> components = currentComponents;
        CatacombsBaseComponent component, newComponent;
        Class componentClass;
        int ends = 1 + random.nextInt(components.size() - 1);
        int endsCount = 0;

        if (level == 4) {
            componentClass = WitherHall.class;
            ends = 1;
        } else {
            componentClass = Stairs.class;
        }

        for (int i = 0; i < ends; i++) {
            if (endsCount < ends || components.size() > 0) {
                int j = random.nextInt(components.size());
                component = components.get(j);
                newComponent = tryCreateComponent(component, componentClass, component.getDirection(), COMPONENT_SIDE.TOP);

                if (newComponent == null) {
                    newComponent = tryCreateComponent(component, componentClass, component.getLeftDirection(component.getDirection()), COMPONENT_SIDE.LEFT);

                    if (newComponent == null) {
                        newComponent = tryCreateComponent(component, componentClass, component.getLeftDirection(component.getDirection()), COMPONENT_SIDE.LEFT);

                        if (newComponent != null) {
                            levelComponents.add(newComponent);
                            endComponents.add(newComponent);
                            endsCount++;
                            components.remove(j);
                        } else {
                            components.remove(j);
                        }
                    } else {
                        levelComponents.add(newComponent);
                        endComponents.add(newComponent);
                        endsCount++;
                        components.remove(j);
                    }
                } else {
                    levelComponents.add(newComponent);
                    endComponents.add(newComponent);
                    endsCount++;
                    components.remove(j);
                }
            } else {
                break;
            }
        }

        if (endsCount == 0) {
            component = currentComponents.get(random.nextInt(components.size()));
            newComponent = createComponent(component, component.getDirection(), componentClass, COMPONENT_SIDE.TOP);
            levelComponents.add(newComponent);
            endComponents.add(newComponent);
        }
    }

    private CatacombsBaseComponent tryCreateComponent(CatacombsBaseComponent component, Class componentClass, int direction, COMPONENT_SIDE componentSide) {
        if (componentsCount < 30 && componentClass == Treasury.class) {
            componentClass = getCorridorType();
        }

        CatacombsBaseComponent newComponent = createComponent(component, direction, componentClass, componentSide);

        if (canBePlaced(newComponent)) {
            levelComponents.add(newComponent);
            return newComponent;
        } else {
            return null;
        }
    }

    private CatacombsBaseComponent tryCreateComponent(CatacombsBaseComponent component, int direction, COMPONENT_SIDE componentSide) {
        return tryCreateComponent(component, getNextComponent(component.getClass(), componentSide), direction, componentSide);
    }

    /**
     * Check is this place availiable for this component
     */
    private boolean canBePlaced(CatacombsBaseComponent component) {
        Iterator<CatacombsBaseComponent> it = levelComponents.iterator();
        CatacombsBaseComponent xz;

        while (it.hasNext()) {
            xz = it.next();

            if (component.canBePlacedHere(xz.getBoundingBox())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Create component
     * @param component previouse component
     * @param direction component direction
     * @param buildComponent component class
     * @param componentSide 
     */
    private CatacombsBaseComponent createComponent(CatacombsBaseComponent component, int direction, Class buildComponent, COMPONENT_SIDE componentSide) {
        if (component != null) {
            int x, y, z;
            y = component.getYEnd();

            if (componentSide == COMPONENT_SIDE.TOP) {
                x = component.getTopXEnd();
                z = component.getTopZEnd();
            } else if (componentSide == COMPONENT_SIDE.LEFT) {
                x = component.getLeftXEnd();
                z = component.getLeftZEnd();
            } else {
                x = component.getRightXEnd();
                z = component.getRightZEnd();
            }

            try {
                Constructor<CatacombsBaseComponent> constructor = buildComponent.getConstructor(int.class, Random.class, int.class, int.class, int.class);
                component = constructor.newInstance(direction, random, x, y, z);
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

    private Class getNextComponent(Class componentClass, COMPONENT_SIDE componentSide) {
        if (componentSide == COMPONENT_SIDE.TOP) {
            return getNextComponentForLevel(componentClass);
        } else {
            if (level == 1 || random.nextInt(100) >= 5) {
                return Corridor.class;
            } else {
                return Treasury.class;
            }
        }
    }

    /**
     * Return next component for current level
     */
    private Class getNextComponentForLevel(Class componentClass) {
        int chance = random.nextInt(100);

        switch (level) {
            case 1:
                if (chance >= 25) {
                    return getCorridorType();
                } else if (chance >= 10) {
                    if (componentClass == Crossing.class) {
                        return getCorridorType();
                    } else {
                        return getCrossingType();
                    }
                } else if (chance >= 5) {
                    if (componentClass == SpidersCorridor.class) {
                        return getCorridorType();
                    } else {
                        return SpidersCorridor.class;
                    }
                } else {
                    if (componentClass == EnderHall.class) {
                        return getCorridorType();
                    } else {
                        return EnderHall.class;
                    }
                }
            default:
                if (chance >= 55) {
                    return getCorridorType();
                } else if (chance >= 40) {
                    if (componentClass == Crossing.class) {
                        return getCorridorType();
                    } else {
                        return getCrossingType();
                    }
                } else if (chance >= 30) {
                    if (componentClass == SpidersCorridor.class) {
                        return getCorridorType();
                    } else {
                        return SpidersCorridor.class;
                    }
                } else if (chance >= 20) {
                    if (componentClass == EnderHall.class) {
                        return getCorridorType();
                    } else {
                        return EnderHall.class;
                    }
                } else if (chance >= 10) {
                    return getHallType();
                } else if (chance >= 5) {
                    if (componentClass == Bridge.class) {
                        return getCorridorType();
                    } else {
                        return Bridge.class;
                    }
                } else {
                    return Treasury.class;
                }
        }
    }

    private Class getHallType() {
        int hallChance = random.nextInt(10);

        if (hallChance >= 2) {
            return GraveHall.class;
        } else {
            return StatuesHall.class;
        }
    }

    /**
     * Return ranfom class of corridor component
     */
    private Class getCorridorType() {
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
    private Class getCrossingType() {
        if (random.nextInt(100) >= 10) {
            return Crossing.class;
        } else {
            return CreeperRoom.class;
        }
    }

    /**
     * Generate level
     */
    public final void generateLevel() {
        CatacombsBaseComponent component;
        Iterator<CatacombsBaseComponent> it = levelComponents.iterator();

        while (it.hasNext()) {
            component = it.next();
            component.addComponentParts(world, random);
        }
    }

    /**
     * Return end parts of level
     */
    public LinkedList getEndParts() {
        return endComponents;
    }
}
