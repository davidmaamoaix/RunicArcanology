package cn.davidma.runicarcanology.render.particle;

import cn.davidma.runicarcanology.render.particle.base.MovingParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.World;

public class LineParticle extends MovingParticle {

	public LineParticle(World world, double fromX, double fromY, double fromZ, double toX, double toY, double toZ, double speed) {
		super(world, fromX, fromY, fromZ, toX, toY, toZ, speed);
		this.canCollide = false;
	}

	@Override
	protected TextureAtlasSprite getTexture() {
		return TextureStitcher.LINE_PARTICLE;
	}

	@Override
	protected int getMaxAge() {
		return 100;
	}
}
