
# [Gravestone mod - Graves](http://gravestone.nightkosh.com/)  [![Curseforge](http://cf.way2muchnoise.eu/full_gravestone-mod-graves_downloads.svg)](https://minecraft.curseforge.com/projects/gravestone-mod-graves) [![Curseforge](http://cf.way2muchnoise.eu/versions/For%20MC_gravestone-mod-graves_all.svg)](https://minecraft.curseforge.com/projects/gravestone-mod-graves)

Adds a gravestone to the game, which spawns after player's death.

For more information visit [official site](http://gravestone.nightkosh.com/) or [minecraft forum](http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1288082)

**Since minecraft 1.8 this mod splitted on 2 parts - "Graves" and "[Extended](https://github.com/NightKosh/Gravestone-mod-Extended)".**

## Minecraft versions
"Master branch" contains mod sources for the latest version of the Minecraft I'm working on. Any previous versions contains in an own branches.

# API
[Gravestone Mod API](https://github.com/NightKosh/GraveStone-mod-API)

## Requirements
1. [Forge](http://files.minecraftforge.net/) (check "build.gradle" file to know required forge version)
   * Latest versions of Forge requires [Gradle 2.0](https://gradle.org/) or higher
2. "Master branch" version of mod requires [jdk 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)(do not forget to enable java 8 support in your IDE)

## Dependencies.
**Be careful some of these API may not be updated yet, or may be bugged!**

1. [Gravestone mod - Graves API](https://github.com/NightKosh/Gravestone-mod-Graves-API) (will be loaded as git submodule)
2. ~~[Baubles API](https://github.com/Azanor/Baubles) (will be loaded as git submodule)~~
3. ~~[Galacticraft API](https://github.com/micdoodle8/Galacticraft-API)~~
4. ~~[Mariculture API](https://github.com/joshiejack/Mariculture)~~
5. ~~[Thaumcraft API](https://github.com/Azanor/thaumcraft-api) (will be loaded as git submodule)~~

## Get started
1. [Set up forge](http://www.minecraftforge.net/wiki/Installation/Source)
2. Clone mod repository into the Forge folder
3. Download mod's API
   * Download [submodules](https://git-scm.com/book/en/v2/Git-Tools-Submodules)
   * Download other API's manually and place them into "src/main/java" folder
