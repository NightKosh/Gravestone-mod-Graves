#GraveStone mod

Adds a gravestone to the game, which spawns after player's death.

Read more on [official site](http://gravestone.nightkosh.com/) or on [minecraft forum](http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1288082-gravestone-mod-v2-9-7)

##Requirements
1. [Forge](http://files.minecraftforge.net/) (check "build.gradle" file to know required forge version)
   * Latest versions of Forge requires [Gradle 2.0](https://gradle.org/) or higher
2. "Master branch" version of mod requires [jdk 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)(do not forget to enable java 8 support in your IDE)

##Dependencies.
**Be careful some of these API may not be updated yet, or may be buggy!**

1. [Gravestone Mod API](https://github.com/NightKosh/GraveStone-mod-API) (will be loaded as git submodule)
2. [Sophisticated wolves API](https://github.com/NightKosh/Sophisticated-wolves-API) (will be loaded as git submodule)
3. [Baubles API](https://github.com/Azanor/Baubles) (will be loaded as git submodule)
4. [Forestry API](https://github.com/ForestryMC/ForestryAPI) (will be loaded as git submodule)
5. [Mariculture API](https://github.com/joshiejack/Mariculture)
6. [Galacticraft API](https://github.com/micdoodle8/Galacticraft-API)
7. [TinkersConstruct API](https://github.com/SlimeKnights/TinkersConstruct)
8. [Thaumcraft API](https://github.com/Azanor/thaumcraft-api)

##Get started
1. [Set up forge](http://www.minecraftforge.net/wiki/Installation/Source)
2. Clone mod repository into the Forge folder
3. Download mod's API
   * Download [submodules](https://git-scm.com/book/en/v2/Git-Tools-Submodules)
   * Download other API's manually and place them into "src/main/java" folder
