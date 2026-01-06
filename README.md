
# [Gravestone mod - Graves](http://gravestone.nightkosh.com/)  [![Curseforge](http://cf.way2muchnoise.eu/full_gravestone-mod-graves_downloads.svg)](https://minecraft.curseforge.com/projects/gravestone-mod-graves) [![Curseforge](http://cf.way2muchnoise.eu/versions/For%20MC_gravestone-mod-graves_all.svg)](https://minecraft.curseforge.com/projects/gravestone-mod-graves)

Adds a gravestone to the game, which spawns after player's death.

For more information visit [official site](http://gravestone.nightkosh.com/) or [minecraft forum](http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1288082)

**Since minecraft 1.8 this mod split on 2 parts - "Graves" and "[Extended](https://github.com/NightKosh/Gravestone-mod-Extended)".**

## Minecraft versions
"Master branch" contains mod sources for the latest version of the Minecraft I'm working on. Any previous versions contains in an own branches.

# API
[Gravestone Mod API](https://github.com/NightKosh/GraveStone-mod-API)

## Requirements
1. [NeoForge](https://neoforged.net/) (check "build.gradle" file to know required NeoForge version)
2. [Jdk 21.0.2](https://jdk.java.net/archive/) (do not forget to enable its support in your IDE)
3. [Gradle 9.2.1](https://gradle.org/releases/)

## Dependencies.
**Be careful some of these API may not be updated yet, or may be bugged!**
1. [Gravestone mod - Graves API](https://github.com/NightKosh/Gravestone-mod-Graves-API) (will be loaded as git submodule)

## Get started
1. Clone mod repository
2. Download NeoForge and copy "gradlew.bat", "gradlew" files and "gradle" directory to mod folder(and any other files which may requires)
3. Download mod's API
    * Download [submodules](https://git-scm.com/book/en/v2/Git-Tools-Submodules)
        * Run next commands from mod folder:
       ```
         git submodule init
         git submodule update
       ```
    * ~~Download other API's manually and place them into "src/main/java" folder~~
4. Download MDK from NeoForge
5. Import mod to your ide as "new Gradle project"

## Gradle commands
1. Running client
    ```
        gradlew runClient
    ```
2. Running Server
    ```
        gradlew runServer
    ```
3. Build mod as .jar file
    ```
        gradlew build
    ```

For more information, look at "minecraft NeoForge" README.txt file (it's not included to this repository) or [this link](https://docs.neoforged.net/)

## Useful commands

## Apply an enchantment on item:
1. Soul bound
    ```
        /enchant @s gravestone:soulbound 1
    ``` 
