# Forge 1.8.9 Mod Template

This repository serves as a ready-to-use template for developing mods for Minecraft Forge 1.8.9. It comes pre-configured with Gradle 4.9, allowing you to jump straight into development.

## Getting Started

Follow these steps to set up your development environment and run the mod:

1.  **Setup Decomp Workspace**:
    Before you can start coding or running the client, you need to set up the ForgeGradle development environment. This task downloads and decompiles Minecraft, making it ready for mod development.

    ```bash
    ./gradlew setupDecompWorkspace
    ```

2.  **Run Client**:
    Once the decompiled workspace is set up, you can run a Minecraft client with your mod loaded. This is useful for testing your changes directly.

    ```bash
    ./gradlew runClient
    ```

3.  **Build Mod JAR**:
    When you're ready to package your mod for distribution, use the `jar` task to build the mod's `.jar` file. This file will be located in the `build/libs` directory.

    ```bash
    ./gradlew jar
    ```

Happy modding!

## Troubleshooting

If you encounter issues with the setup or running the client, try these steps:

1.  **Clean Build**:
    Sometimes, old build artifacts can cause problems. Cleaning the build directory can resolve these.

    ```bash
    ./gradlew clean
    ```

2.  **Clean ForgeGradle Cache**:
    If issues persist, especially after updating ForgeGradle or dependencies, clearing its cache can help.

    ```bash
    ./gradlew cleanCache
    ```

