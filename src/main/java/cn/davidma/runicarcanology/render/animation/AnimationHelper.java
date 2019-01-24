package cn.davidma.runicarcanology.render.animation;

import cn.davidma.runicarcanology.reference.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class AnimationHelper {

	public static void drawCircle(Circle circle, double x, double y, double z, double diameter, double time, EnumFacing facing) {
		
		// Texture setup.
		ResourceLocation circleTexture = circle.getTextureLocation();
		Minecraft.getMinecraft().renderEngine.bindTexture(circleTexture);
		
		// Tessellator and OpenGL configuration.
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, 0, 0);
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		
		// Hypotenuse.
		double radius = diameter / 2;
		double slant = Math.sqrt(radius * radius * 2);
		
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		
		// Four vertices.
		int[][] vertPos = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};		
		for (int i = 0; i < 4; i++) {
			double offset = Math.toRadians(i * 90 + time);
			double newX = slant * Math.cos(offset);
			double newY = 0;
			double newZ = slant * Math.sin(offset);
			
			// For swapping.
			double temp;
			
			// EnumFace offset.
			switch (facing) {
				case UP: break; // Do nothing.
				case DOWN: temp = newX; newX = newZ; newZ = temp; break; // newX, newY, newZ = z, y, x.
				case NORTH: temp = newX; newX = newY; newY = newZ; newZ = temp; break; // newX, newY, newZ = y, z, x.
				case SOUTH: temp = newY; newY = newZ; newZ = temp; break; // newX, newY, newZ = x, z, y.
				case EAST: temp = newZ; newZ = newY; newY = newX; newX = temp; break; // newX, newY, newZ = z, x, y.
				case WEST: temp = newX; newX = newY; newY = temp; // newX, newY, newZ = y, x, z
			}
			
			bufferBuilder.pos(x + newX, y + newY, z + newZ).tex(vertPos[i][0], vertPos[i][1]).endVertex();
		}
		
		tessellator.draw();
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}
}
