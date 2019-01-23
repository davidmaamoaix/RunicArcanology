package cn.davidma.runicarcanology.render.particle;

import cn.davidma.runicarcanology.reference.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LightParticle extends Particle {
	
	private final ResourceLocation lightLoc = new ResourceLocation(Info.MOD_ID, "textures/particles/lightParticle");

	protected LightParticle(World world, double x, double y, double z) {
		super(world, x, y, z);
		this.particleGravity = 0;
		this.particleMaxAge = 100;
		this.particleAlpha = 0.75F;
		
		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lightLoc.toString());
		this.setParticleTexture(sprite);
	}
	
	@Override
	public int getFXLayer() {
		return 3;
	}
	
	@Override
	public int getBrightnessForRender(float partialTick) {
		return 0xFFFFFF;
	}
	
	@Override
	public boolean shouldDisableDepth() {
		return false;
	}
	
	@Override
	public void onUpdate() {
		this.particleAge--;
		if (particleAge < 0) {
			this.setExpired();
		}
	}
	
	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}
}
