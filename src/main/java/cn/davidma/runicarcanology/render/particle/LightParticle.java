package cn.davidma.runicarcanology.render.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LightParticle extends Particle {

	public LightParticle(World world, double posXIn, double posYIn, double posZIn) {
		super(world, posXIn, posYIn, posZIn);
	}
	
	public LightParticle(World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;
		this.setParticleTexture(TextureStitcher.lightParticle);
	}
	
	@Override
	public int getBrightnessForRender(float p_189214_1_) {
		return 0xf000f0;
	}
	
	@Override
	public boolean shouldDisableDepth() {
		return false;
	}
	
	@Override
	public int getFXLayer() {
		return 1;
	}

	
}
