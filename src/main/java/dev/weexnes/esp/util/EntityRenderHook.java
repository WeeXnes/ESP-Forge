package dev.weexnes.esp.util;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dev.weexnes.esp.KeyInputHandler; // Import the KeyInputHandler

public class EntityRenderHook {

    @SubscribeEvent
    public void onRenderLivingPre(RenderLivingEvent.Pre event) {
        if (KeyInputHandler.wallhackEnabled && !(event.entity instanceof net.minecraft.entity.player.EntityPlayer)) {
            GlStateManager.disableDepth();
        }
    }

    @SubscribeEvent
    public void onRenderLivingPost(RenderLivingEvent.Post event) {
        if (KeyInputHandler.wallhackEnabled && !(event.entity instanceof net.minecraft.entity.player.EntityPlayer)) {
            GlStateManager.enableDepth();
        }
    }
}
