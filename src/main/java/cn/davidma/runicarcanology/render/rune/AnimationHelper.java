package cn.davidma.runicarcanology.render.rune;

import org.lwjgl.opengl.GL11;

import cn.davidma.runicarcanology.reference.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class AnimationHelper {
	
	public static double DISTINCTION_OFFSET = 1e-3;

	public static void drawSingleSidedCircle(EnumCircle circle, double x, double y, double z, double diameter, double time, EnumFacing facing, float[] color) {
		
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
		if (color.length == 3) GL11.glColor4f(color[0], color[1], color[2], 1);
		if (color.length == 4) GL11.glColor4f(color[0], color[1], color[2], color[3]);
		
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
				case WEST: temp = newX; newX = newY; newY = newZ; newZ = temp; break; // newX, newY, newZ = y, z, x.
				case SOUTH: temp = newY; newY = newZ; newZ = temp; break; // newX, newY, newZ = x, z, y.
				case NORTH: temp = newZ; newZ = newY; newY = newX; newX = temp; break; // newX, newY, newZ = z, x, y.
				case EAST: temp = newX; newX = newY; newY = temp; // newX, newY, newZ = y, x, z
			}
			
			bufferBuilder.pos(x + newX, y + newY, z + newZ).tex(vertPos[i][0], vertPos[i][1]).endVertex();
		}
		
		tessellator.draw();
		GL11.glColor4f(1, 1, 1, 1);
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}
	
	public static void drawCircle(EnumCircle circle, double x, double y, double z, double diameter, double time, EnumFacing facing, float[] color) {
		drawSingleSidedCircle(circle, x, y, z, diameter, time, facing, color);
		drawSingleSidedCircle(circle, x, y, z, diameter, time, facing.getOpposite(), color);
	}
	
	public static double oscillate(double time, double min, double max) {
		return min + (Math.sin(Math.toRadians(time)) + 1) / 2 * (max - min);
	}
}
