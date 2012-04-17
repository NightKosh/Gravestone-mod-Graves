package net.minecraft.GraveStone.structures;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.world.World;

public class StructureGSCemeteryCatacombsLevel {

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

    public StructureGSCemeteryCatacombsLevel(LinkedList<ComponentGSCemeteryCatacombs> startComponents, int level, World world, Random random) {
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
    public void prepareLevel(LinkedList<ComponentGSCemeteryCatacombs> currentComponents) {
        LinkedList<ComponentGSCemeteryCatacombs> newComponents = new LinkedList();

        ComponentGSCemeteryCatacombs[] components = new ComponentGSCemeteryCatacombs[0];
        components = currentComponents.toArray(components);

        ComponentGSCemeteryCatacombs component;
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
    private int addComponent(LinkedList<ComponentGSCemeteryCatacombs> newComponents, ComponentGSCemeteryCatacombs component, int direction, COMPONENT_SIDE componentSide) {
        ComponentGSCemeteryCatacombs newComponent = tryCreateComponent(component, direction, componentSide);
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
    private void createEnd(LinkedList<ComponentGSCemeteryCatacombs> currentComponents) {
        LinkedList<ComponentGSCemeteryCatacombs> components = currentComponents;
        ComponentGSCemeteryCatacombs component, newComponent;
        Class componentClass;
        int ends = 1 + random.nextInt(components.size() - 1);
        int endsCount = 0;

        if (level == 4) {
            componentClass = ComponentGSCemeteryCatacombsWither.class;
            ends = 1;
        } else {
            componentClass = ComponentGSCemeteryCatacombsStairs.class;
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

    private ComponentGSCemeteryCatacombs tryCreateComponent(ComponentGSCemeteryCatacombs component, Class componentClass, int direction, COMPONENT_SIDE componentSide) {
        if (componentsCount < 30 && componentClass == ComponentGSCemeteryCatacombsTreasury.class) {
            componentClass = getCorridorType();
        }
        ComponentGSCemeteryCatacombs newComponent = createComponent(component, direction, componentClass, componentSide);

        if (canBePlaced(newComponent)) {
            levelComponents.add(newComponent);
            return newComponent;
        } else {
            return null;
        }
    }

    private ComponentGSCemeteryCatacombs tryCreateComponent(ComponentGSCemeteryCatacombs component, int direction, COMPONENT_SIDE componentSide) {
        return tryCreateComponent(component, getNextComponent(component.getClass(), componentSide), direction, componentSide);
    }

    private boolean canBePlaced(ComponentGSCemeteryCatacombs component) {
        Iterator<ComponentGSCemeteryCatacombs> it = levelComponents.iterator();
        ComponentGSCemeteryCatacombs xz;
        while (it.hasNext()) {
            xz = it.next();
            if (component.canBePlacedHere(xz.getBoundingBox())) {
                return false;
            }
        }

        return true;
    }

    private ComponentGSCemeteryCatacombs createComponent(ComponentGSCemeteryCatacombs component, int direction, Class buildComponent, COMPONENT_SIDE componentSide) {
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
                Constructor<ComponentGSCemeteryCatacombs> constructor = buildComponent.getConstructor(int.class, Random.class, int.class, int.class, int.class);
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
                return ComponentGSCemeteryCatacombsCorridor.class;
            } else {
                return ComponentGSCemeteryCatacombsTreasury.class;
            }
        }
    }

    private Class getNextComponentForLevel(Class componentClass) {
        int chance = random.nextInt(100);
        switch (level) {
            case 1:
                if (chance >= 25) {
                    return getCorridorType();
                } else if (chance >= 10) {
                    if (componentClass == ComponentGSCemeteryCatacombsCrossing.class) {
                        return getCorridorType();
                    } else {
                        return ComponentGSCemeteryCatacombsCrossing.class;
                    }
                } else if (chance >= 5) {
                    if (componentClass == ComponentGSCemeteryCatacombsSpidersCorridor.class) {
                        return getCorridorType();
                    } else {
                        return ComponentGSCemeteryCatacombsSpidersCorridor.class;
                    }
                } else {
                    if (componentClass == ComponentGSCemeteryCatacombsEnderHall.class) {
                        return getCorridorType();
                    } else {
                        return ComponentGSCemeteryCatacombsEnderHall.class;
                    }
                }
            default:
                if (chance >= 55) {
                    return getCorridorType();
                } else if (chance >= 40) {
                    if (componentClass == ComponentGSCemeteryCatacombsCrossing.class) {
                        return getCorridorType();
                    } else {
                        return ComponentGSCemeteryCatacombsCrossing.class;
                    }
                } else if (chance >= 30) {
                    if (componentClass == ComponentGSCemeteryCatacombsSpidersCorridor.class) {
                        return getCorridorType();
                    } else {
                        return ComponentGSCemeteryCatacombsSpidersCorridor.class;
                    }
                } else if (chance >= 20) {
                    if (componentClass == ComponentGSCemeteryCatacombsEnderHall.class) {
                        return getCorridorType();
                    } else {
                        return ComponentGSCemeteryCatacombsEnderHall.class;
                    }
                } else if (chance >= 10) {
                    return ComponentGSCemeteryCatacombsGraveHall.class;
                } else if (chance >= 5) {
                    if (componentClass == ComponentGSCemeteryCatacombsBridge.class) {
                        return getCorridorType();
                    } else {
                        return ComponentGSCemeteryCatacombsBridge.class;
                    }
                } else {
                    return ComponentGSCemeteryCatacombsTreasury.class;
                }
        }
    }

    private Class getCorridorType() {
        int corridorChance = random.nextInt(100);
        if (corridorChance >= 65) {
            return ComponentGSCemeteryCatacombsCorridor.class;
        } else if (corridorChance >= 5) {
            return ComponentGSCemeteryCatacombsGraveCorridor.class;
        } else {
            return ComponentGSCemeteryCatacombsTrapCorridor.class;
        }
    }

    public void generateLevel() {
        ComponentGSCemeteryCatacombs component;
        Iterator<ComponentGSCemeteryCatacombs> it = levelComponents.iterator();
        while (it.hasNext()) {
            component = it.next();
            component.addComponentParts(world, random);
        }
    }

    public LinkedList getEndParts() {
        return endComponents;
    }
}
