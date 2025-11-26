package dev.weexnes.esp.util;

import dev.weexnes.esp.KeyInputHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class ESPRenderHook {

    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        if (!KeyInputHandler.espEnabled || mc.getRenderManager() == null || mc.theWorld == null) {
            return;
        }
        for (Entity entity : mc.theWorld.loadedEntityList) {
            if (entity != mc.thePlayer && entity.isEntityAlive()) {
                renderEntityBox(entity, event.partialTicks);
            }
        }
    }

    private void renderEntityBox(Entity entity, float partialTicks) {
        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - mc.getRenderManager().viewerPosX;
        double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - mc.getRenderManager().viewerPosY;
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - mc.getRenderManager().viewerPosZ;

        AxisAlignedBB entityBox = entity.getEntityBoundingBox();
        AxisAlignedBB renderBox = new AxisAlignedBB(
                entityBox.minX - entity.posX + x,
                entityBox.minY - entity.posY + y,
                entityBox.minZ - entity.posZ + z,
                entityBox.maxX - entity.posX + x,
                entityBox.maxY - entity.posY + y,
                entityBox.maxZ - entity.posZ + z
        );

        drawESPBox(renderBox, entity);
    }

    private void drawESPBox(AxisAlignedBB box, Entity entity) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();

        if (entity instanceof EntityPlayer && entity.isInvisible()) {
            GL11.glLineWidth(5.0F);
            GlStateManager.color(1.0f, 0.0f, 1.0f, 0.7f); // Pink
        } else if (entity instanceof EntityLivingBase) {
            GL11.glLineWidth(2.0F);
            EntityLivingBase livingBase = (EntityLivingBase) entity;
            float health = livingBase.getHealth();
            float maxHealth = livingBase.getMaxHealth();
            float healthPercent = health / maxHealth;

            float red = 1.0f - healthPercent;
            float green = healthPercent;

            GlStateManager.color(red, green, 0.0f, 0.5f);
        } else {
            GL11.glLineWidth(2.0F);
            GlStateManager.color(0.0f, 0.0f, 1.0f, 0.3f);   // Standard BLAU for non-living entities
        }

        // Draw the box
        worldRenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        worldRenderer.pos(box.minX, box.minY, box.minZ).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
        worldRenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
        worldRenderer.pos(box.minX, box.minY, box.minZ).endVertex();
        tessellator.draw();

        worldRenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        worldRenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
        tessellator.draw();

        worldRenderer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
        worldRenderer.pos(box.minX, box.minY, box.minZ).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
        worldRenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
        tessellator.draw();

        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
}
