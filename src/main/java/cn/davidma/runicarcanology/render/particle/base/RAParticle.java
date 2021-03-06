package cn.davidma.runicarcanology.render.particle.base;

import cn.davidma.runicarcanology.render.particle.TextureStitcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public abstract class RAParticle extends Particle {

	protected RAParticle(World world, double posXIn, double posYIn, double posZIn) {
		super(world, posXIn, posYIn, posZIn);
	}

	public RAParticle(World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.motionX = xSpeedIn;
		this.motionY = ySpeedIn;
		this.motionZ = zSpeedIn;
		this.setParticleTexture(this.getTexture());
		this.setMaxAge(this.getMaxAge());
		Minecraft.getMinecraft().effectRenderer.addEffect(this);
	}
	
	public RAParticle(World world, double xCoordIn, double yCoordIn, double zCoordIn, double[] speed) {
		this(world, xCoordIn, yCoordIn, zCoordIn, speed[0], speed[1], speed[2]);
	}
	
	@Override
	public int getBrightnessForRender(float p_189214_1_) {
		return 0xf000f0;
	}
	
	@Override
	public int getFXLayer() {
		return 1;
	}
	
	@Override
	public void renderParticle(BufferBuilder buffer, Entity entity, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		super.renderParticle(buffer, entity, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}
	
	protected abstract TextureAtlasSprite getTexture();
	protected abstract int getMaxAge();
}
