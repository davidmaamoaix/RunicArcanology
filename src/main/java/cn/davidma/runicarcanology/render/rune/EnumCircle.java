package cn.davidma.runicarcanology.render.rune;

import cn.davidma.runicarcanology.reference.Info;
import net.minecraft.util.ResourceLocation;

public enum EnumCircle {
	
	CIRCLE("circle"),
	DASH_CIRCLE("dash_circle"),
	RUNE_CIRCLE("rune_circle"),
	SQUARE("square");
	
	private ResourceLocation texture;
	
	private EnumCircle(String baseName) {
		this.texture = new ResourceLocation(Info.MOD_ID, String.format("textures/blocks/%s.png", baseName));
	}
	
	public ResourceLocation getTextureLocation() {
		return this.texture;
	}
}
