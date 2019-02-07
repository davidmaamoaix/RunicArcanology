package cn.davidma.runicarcanology.render.rune.animation.activatable;

import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.render.rune.animation.core.ActivatableRuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.CircleStats;
import cn.davidma.runicarcanology.util.MathHelper;
import net.minecraft.util.EnumFacing;

public class NatureGiftActivatable extends ActivatableRuneAnimation {

	private static double DISTANCE = 0.35;
	
	public NatureGiftActivatable() {
		super();
		
		float[] color = new float[] {21 / 255.0F, 185 / 255.0F, 30 / 255.0F};
		
		CircleStats base = new CircleStats(EnumCircle.DASH_CIRCLE, EnumFacing.UP, 2, 0.75);
		base.setColor(color);
		base.setAlpha(0);
		base.setyOffset(base.getyOffset() + 0.0625);
		this.circles.add(base);
		
		for (int i = 0; i < 3; i++) {
			CircleStats small = new CircleStats(EnumCircle.RUNE_CIRCLE, EnumFacing.UP, -1, 0.3);
			small.setColor(color);
			small.setAlpha(0);
			small.setxOffset(Math.sin(Math.toRadians(i * 120)) * DISTANCE);
			small.setyOffset(small.getyOffset() + 0.0625);
			small.setzOffset(Math.cos(Math.toRadians(i * 120)) * DISTANCE);
			this.circles.add(small);
		}
	}
	
	@Override
	public void draw(double x, double y, double z, double time) {
		super.draw(x, y, z, time);
		for (int i = 1; i < 4; i++) {
			double offset = (i - 1) * 120;
			CircleStats small = this.circles.get(i);
			small.setxOffset(Math.sin(Math.toRadians(time + offset)) * DISTANCE);
			small.setzOffset(Math.cos(Math.toRadians(time + offset)) * DISTANCE);
		}
	}
}
