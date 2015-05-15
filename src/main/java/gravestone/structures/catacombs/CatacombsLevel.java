package gravestone.structures.catacombs;

import gravestone.config.GSConfig;
import gravestone.core.GSBlock;
import gravestone.core.logger.GSLogger;
import gravestone.structures.catacombs.components.CatacombsBaseComponent;
import gravestone.structures.catacombs.components.Stairs;
import gravestone.structures.catacombs.components.Treasury;
import gravestone.structures.catacombs.components.WitherHall;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsLevel {

    private static final StructureComponent.BlockSelector catacombsStoneBlocks = new CatacombsStonesBlocks();
    private static final StructureComponent.BlockSelector catacombsBoneBlocks = new CatacombsBoneBlocks();
    private final int level;
    private Random random;
    private World world;
    private int totalComponentsCount;
    private int componentsCount;
    public static final int DEFAULT_MIN_ROOMS_COUNT_AT_1_LEVEL = 30;
    public static final int DEFAULT_MAX_ROOMS_COUNT_AT_1_LEVEL = 60;
    public static final int DEFAULT_MIN_ROOMS_COUNT_AT_2_LEVEL = 60;
    public static final int DEFAULT_MAX_ROOMS_COUNT_AT_2_LEVEL = 120;
    public static final int DEFAULT_MIN_ROOMS_COUNT_AT_3_LEVEL = 90;
    public static final int DEFAULT_MAX_ROOMS_COUNT_AT_3_LEVEL = 180;
    public static final int DEFAULT_MIN_ROOMS_COUNT_AT_4_LEVEL = 160;
    public static final int DEFAULT_MAX_ROOMS_COUNT_AT_4_LEVEL = 320;
    private LinkedList levelComponents = new LinkedList();
    private LinkedList endComponents = new LinkedList();

    public CatacombsLevel(LinkedList<CatacombsBaseComponent> startComponents, int level, World world, Random random) {
        levelComponents = startComponents;
        this.random = random;
        this.world = world;
        this.level = level;
        GSLogger.logInfo("Catacombs generation - start generation of " + this.level + " level");

        switch (this.level) {
            case 1:
                totalComponentsCount = GSConfig.catacombsMinRoomsCountAt1Level + random.nextInt(GSConfig.catacombsMaxRoomsCountAt1Level - GSConfig.catacombsMinRoomsCountAt1Level);
                break;
            case 2:
                totalComponentsCount = GSConfig.catacombsMinRoomsCountAt2Level + random.nextInt(GSConfig.catacombsMaxRoomsCountAt2Level - GSConfig.catacombsMinRoomsCountAt2Level);
                break;
            case 3:
                totalComponentsCount = GSConfig.catacombsMinRoomsCountAt3Level + random.nextInt(GSConfig.catacombsMaxRoomsCountAt3Level - GSConfig.catacombsMinRoomsCountAt3Level);
                break;
            case 4:
                totalComponentsCount = GSConfig.catacombsMinRoomsCountAt4Level + random.nextInt(GSConfig.catacombsMaxRoomsCountAt4Level - GSConfig.catacombsMinRoomsCountAt4Level);
                break;
        }

        componentsCount = 0;
        prepareLevel(levelComponents);
        generateLevel();

        GSLogger.logInfo("Catacombs generation - " + this.level + " level was successfully generated");
    }

    /**
     * Return StructureGSCemeteryCatacombsStones instance
     */
    public static StructureComponent.BlockSelector getCatacombsStones(int level) {
        return (level < 3) ? catacombsStoneBlocks : catacombsBoneBlocks;
    }

    public static Block getCatacombsStairsByLevelId(int level) {
        return (level < 3) ? Blocks.stone_brick_stairs : GSBlock.boneStairs;
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

                if (component.canGoOnlyForward()) {
                    if (component.goForward) {
                        resultComponentsCount += addComponent(newComponents, component, component.getDirection(), COMPONENT_SIDE.FRONT);
                    }
                } else {
                    if (component.goForward) {
                        resultComponentsCount += addComponent(newComponents, component, component.getDirection(), COMPONENT_SIDE.FRONT);
                        resultComponentsCount += addComponent(newComponents, component, component.getLeftDirection(component.getDirection()), COMPONENT_SIDE.LEFT);
                        resultComponentsCount += addComponent(newComponents, component, component.getRightDirection(component.getDirection()), COMPONENT_SIDE.RIGHT);
                    }
                }
            }
        } else if (endComponents.isEmpty()) {
            createEnd(currentComponents, level);
        }

        componentsCount += resultComponentsCount;

        if (resultComponentsCount != 0) {
            prepareLevel(newComponents);
        }
    }

    /*
     * Create and add new component if it available
     */
    private int addComponent(LinkedList<CatacombsBaseComponent> newComponents, CatacombsBaseComponent component, EnumFacing direction, COMPONENT_SIDE componentSide) {
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
    private void createEnd(LinkedList<CatacombsBaseComponent> currentComponents, int level) {
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
                newComponent = tryCreateComponent(component, componentClass, component.getDirection(), level, COMPONENT_SIDE.FRONT);

                if (newComponent == null) {
                    newComponent = tryCreateComponent(component, componentClass, component.getLeftDirection(component.getDirection()), level, COMPONENT_SIDE.LEFT);

                    if (newComponent == null) {
                        newComponent = tryCreateComponent(component, componentClass, component.getRightDirection(component.getDirection()), level, COMPONENT_SIDE.RIGHT);

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
            newComponent = CatacombsComponentsFactory.createComponent(component, random, component.getDirection(), level, componentClass, COMPONENT_SIDE.FRONT);
            levelComponents.add(newComponent);
            endComponents.add(newComponent);
        }
    }

    private CatacombsBaseComponent tryCreateComponent(CatacombsBaseComponent component, Class componentClass, EnumFacing direction, int level, COMPONENT_SIDE componentSide) {
        if (componentsCount < 30 && componentClass == Treasury.class) {
            componentClass = CatacombsComponentsFactory.getCorridorType(random);
        }

        CatacombsBaseComponent newComponent = CatacombsComponentsFactory.createComponent(component, random, direction, level, componentClass, componentSide);

        if (canBePlaced(newComponent)) {
            levelComponents.add(newComponent);
            return newComponent;
        } else {
            return null;
        }
    }

    private CatacombsBaseComponent tryCreateComponent(CatacombsBaseComponent component, EnumFacing direction, COMPONENT_SIDE componentSide) {
        return tryCreateComponent(component, CatacombsComponentsFactory.getNextComponent(component.getClass(), componentSide, random, level), direction, level, componentSide);
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

    protected static enum COMPONENT_SIDE {

        FRONT,
        LEFT,
        RIGHT
    }
}
