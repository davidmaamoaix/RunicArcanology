package cn.davidma.runicarcanology.render.animation;

import cn.davidma.runicarcanology.reference.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class AnimationHelper {

	public static void drawCircle(Circle circle, double x, double y, double z, double diameter, double spinSpeed) {
		
		// Texture setup.
		ResourceLocation circleTexture = circle.getTextureLocation();
		Minecraft.getMinecraft().renderEngine.bindTexture(circleTexture);
		
		// Tessellator.
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		GlStateManager.pushMatrix();
		GlStateManager.translate(x - 0.5D, y + 0.1D, z - 0.5D);
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		
		bufferBuilder.pos(-diameter/2, 0, -diameter/2).tex(0, 0).endVertex();
		bufferBuilder.pos(-diameter/2, 0, -diameter/2).tex(0, 1).endVertex();
		bufferBuilder.pos(+diameter/2, 0, -diameter/2).tex(1, 1).endVertex();
		bufferBuilder.pos(+diameter/2, 0, -diameter/2).tex(1, 0).endVertex();
		
		tessellator.draw();
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}
}
