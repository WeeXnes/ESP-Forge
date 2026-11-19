# ESP-Forge

ESP mod for Minecraft Forge 1.8.9, built on a ready-to-use Forge 1.8.9 mod template.

[![Platform](https://img.shields.io/badge/platform-Forge%201.8.9-000000?style=flat-square&labelColor=1f1f1f)](#build-from-source)
[![Stack](https://img.shields.io/badge/stack-Java%20%C2%B7%20Gradle%204.9-000000?style=flat-square&labelColor=1f1f1f)](#build-from-source)

The repository works as a template for developing Forge 1.8.9 mods. It comes pre-configured with Gradle 4.9, so you can
jump straight into development.

## Build from source

1. **Set up the decompiled workspace.** Before you can start coding or running the client, set up the ForgeGradle
   development environment. This task downloads and decompiles Minecraft, making it ready for mod development.

   ```bash
   ./gradlew setupDecompWorkspace
   ```

2. **Run the client.** Once the decompiled workspace is set up, you can run a Minecraft client with your mod loaded.
   This is useful for testing your changes directly.

   ```bash
   ./gradlew runClient
   ```

3. **Build the mod JAR.** When you're ready to package your mod for distribution, use the `jar` task to build the mod's
   `.jar` file. It is placed in `build/libs`.

   ```bash
   ./gradlew jar
   ```

Happy modding!

### Troubleshooting

If you run into issues with the setup or running the client, try these steps:

1. **Clean build:** old build artifacts can cause problems. Cleaning the build directory can resolve these.

   ```bash
   ./gradlew clean
   ```

2. **Clean the ForgeGradle cache:** if issues persist, especially after updating ForgeGradle or dependencies, clearing
   its cache can help.

   ```bash
   ./gradlew cleanCache
   ```

## License

No license has been chosen for the mod yet. Forge Mod Loader's own terms are in [LICENSE-fml.txt](LICENSE-fml.txt).
