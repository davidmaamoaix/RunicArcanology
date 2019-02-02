package cn.davidma.runicarcanology.render.particle;

import cn.davidma.runicarcanology.reference.Info;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TextureStitcher {

	public static TextureAtlasSprite lightParticle;
	
	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Pre event) {
		lightParticle = event.getMap().registerSprite(new ResourceLocation(Info.MOD_ID, "particles/light_particle"));
	}
}
