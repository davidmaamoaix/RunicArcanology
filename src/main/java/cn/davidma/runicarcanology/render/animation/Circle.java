package cn.davidma.runicarcanology.render.animation;

import cn.davidma.runicarcanology.reference.Info;
import net.minecraft.util.ResourceLocation;

public enum Circle {
	DASH_CIRCLE("dash_circle");
	
	private ResourceLocation texture;
	
	private Circle(String baseName) {
		this.texture = new ResourceLocation(Info.MOD_ID, String.format("textures/blocks/%s.png", baseName));
	}
	
	public ResourceLocation getTextureLocation() {
		return this.texture;
	}
}
