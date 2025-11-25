package com.example.examplemod.util;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class EntityRenderHook {

    private static final KeyBinding toggleKey = new KeyBinding("Toggle Wallhack", Keyboard.KEY_H, "Example Mod");

    public EntityRenderHook() {
        ClientRegistry.registerKeyBinding(toggleKey);
    }

    @SubscribeEvent
    public void onRenderLivingPre(RenderLivingEvent.Pre event) {
        if (toggleKey.isKeyDown() && !(event.entity instanceof net.minecraft.entity.player.EntityPlayer)) {
            GlStateManager.disableDepth();
        }
    }

    @SubscribeEvent
    public void onRenderLivingPost(RenderLivingEvent.Post event) {
        if (toggleKey.isKeyDown() && !(event.entity instanceof net.minecraft.entity.player.EntityPlayer)) {
            GlStateManager.enableDepth();
        }
    }
}
