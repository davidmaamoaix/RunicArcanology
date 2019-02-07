package cn.davidma.runicarcanology.render.rune.animation.core;

import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.util.MathHelper;
import net.minecraft.util.EnumFacing;

public abstract class RuneSymbol extends RuneAnimation {

	private static final double MAX_SHADOW_DIAMETER = 1.5;
	private static final double SHADOW_DURATION = 15;
	
	public RuneSymbol() {
		super();
		CircleStats glow = new CircleStats(this.getEnumCircle(), EnumFacing.UP, 0, 0.5);
		glow.setRotationOffset(0);
		glow.setyOffset(-0.5 + 0.0625 + AnimationHelper.DISTINCTION_OFFSET);
		glow.setColor(this.getColor());
		this.circles.add(glow);
		
		CircleStats shadow = new CircleStats(this.getEnumCircle(), EnumFacing.UP, 0, 0.5);
		shadow.setRotationOffset(0);
		shadow.setyOffset(-0.5 + 0.0625 + AnimationHelper.DISTINCTION_OFFSET);
		shadow.setColor(this.getColor());
		this.circles.add(shadow);
	}
	
	@Override
	public void draw(double x, double y, double z, double time) {
		float[] color = this.getColor();
		double alpha = MathHelper.oscillate(time * 4, 0.35, 1);
		this.circles.get(0).setAlpha((float) alpha);
		super.draw(x, y, z, time);
		
		CircleStats shadow = this.circles.get(1);
		double currTime = time % 180;
		if (currTime < 90 || currTime > 90 + SHADOW_DURATION) {
			shadow.setAlpha(0);
			shadow.setDiameter(0.5);
		} else {
			currTime -= 90;
			shadow.setAlpha((float) ((SHADOW_DURATION - currTime) / SHADOW_DURATION));
			shadow.setDiameter(currTime * MAX_SHADOW_DIAMETER / SHADOW_DURATION + 0.5);
		}
	}
	
	public abstract EnumCircle getEnumCircle();
	public abstract float[] getColor();
}
