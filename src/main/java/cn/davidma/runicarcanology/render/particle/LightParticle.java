package cn.davidma.runicarcanology.render.particle;

import cn.davidma.runicarcanology.render.particle.base.MovingParticle;
import cn.davidma.runicarcanology.util.MathHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LightParticle extends MovingParticle {
	
	public LightParticle(World world, double fromX, double fromY, double fromZ, double toX, double toY, double toZ, double speed) {
		super(world, fromX, fromY, fromZ, toX, toY, toZ, speed);
		this.canCollide = false;
		this.multipleParticleScaleBy(0.6F);
		this.setAlphaF(MathHelper.randomFloat(0.5F, 1F));
	}

	@Override
	protected TextureAtlasSprite getTexture() {
		return TextureStitcher.LIGHT_PARTICLE;
	}

	@Override
	protected int getMaxAge() {
		return 20;
	}
}
