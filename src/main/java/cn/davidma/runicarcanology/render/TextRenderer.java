package cn.davidma.runicarcanology.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TextRenderer {

	private double alpha;
	private String text;
	private int color;
	
	public void setText(String text, String color) {
		this.text = text;
		this.alpha = 100;
		this.color = Integer.parseInt(color, 16);
	}
	
	public void tick() {
		//if (text == null) return;
		System.out.println(123);
		Minecraft minecraft = Minecraft.getMinecraft();
		FontRenderer fontRenderer = minecraft.fontRenderer;
		
		ScaledResolution scaledResolution = new ScaledResolution(minecraft);
		int width = scaledResolution.getScaledWidth();
		int height = scaledResolution.getScaledHeight();
		
		GlStateManager.pushMatrix();
		fontRenderer.drawString("Hello World", width / 2, height / 2, this.color);
		GlStateManager.popMatrix();
		
		alpha--;
		if (alpha <= 0) {
			text = null;
		}
	}
}
