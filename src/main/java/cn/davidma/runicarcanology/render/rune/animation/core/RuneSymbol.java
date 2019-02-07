package cn.davidma.runicarcanology.render.rune.animation.core;

import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.util.MathHelper;
import net.minecraft.util.EnumFacing;

public abstract class RuneSymbol extends RuneAnimation {

	public RuneSymbol() {
		super();
		CircleStats glow = new CircleStats(this.getEnumCircle(), EnumFacing.UP, 0, 0.5);
		glow.setRotationOffset(0);
		glow.setyOffset(-0.5 + 0.0625 + AnimationHelper.DISTINCTION_OFFSET);
		glow.setColor(this.getColor());
		this.circles.add(glow);
	}
	
	@Override
	public void draw(double x, double y, double z, double time) {
		float[] color = this.getColor();
		double alpha = MathHelper.oscillate(time * 4, 0.35, 1);
		this.circles.get(0).setColor(new float[] {color[0], color[1], color[2], (float) alpha});
		super.draw(x, y, z, time);
	}
	
	public abstract EnumCircle getEnumCircle();
	public abstract float[] getColor();
}
