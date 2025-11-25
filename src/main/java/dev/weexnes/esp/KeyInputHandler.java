package dev.weexnes.esp;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class KeyInputHandler {

    public static final KeyBinding wallhackToggleKey = new KeyBinding("Toggle Wallhack", Keyboard.KEY_H, "ESP");
    public static final KeyBinding espToggleKey = new KeyBinding("Toggle ESP", Keyboard.KEY_J, "ESP");

    public static boolean wallhackEnabled = false;
    public static boolean espEnabled = false; // Default to on

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (wallhackToggleKey.isPressed()) {
            wallhackEnabled = !wallhackEnabled;
        }
        if (espToggleKey.isPressed()) {
            espEnabled = !espEnabled;
        }
    }
}
