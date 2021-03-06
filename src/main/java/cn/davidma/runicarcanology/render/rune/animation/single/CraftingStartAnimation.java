package cn.davidma.runicarcanology.render.rune.animation.single;

import cn.davidma.runicarcanology.reference.Settings;
import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.render.rune.EnumRuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.CircleStats;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.SingleUseRuneAnimation;
import cn.davidma.runicarcanology.util.MathHelper;
import net.minecraft.util.EnumFacing;

public class CraftingStartAnimation extends SingleUseRuneAnimation {

	private static final int ANIMATION_LENGTH = Settings.CRAFTING_DURATION;
	
	private static final int START_TICK = 15;
	private static final int END_TICK = 15;
	private static final float[] CIRCLE_DIAMETER = new float[] {2.75F, 2.75F, 2.75F, 2.75F, 5};
	
	public CraftingStartAnimation() {
		super();
		CircleStats base = new CircleStats(EnumCircle.DASH_CIRCLE, EnumFacing.UP, 3, 0);
		this.circles.add(base);
		for (int i = 0; i < 3; i++) {
			CircleStats square = new CircleStats(EnumCircle.SQUARE, EnumFacing.UP, -2, 0);
			square.setRotationOffset(i * 30);
			this.circles.add(square);
		}
		CircleStats top = new CircleStats(EnumCircle.RUNE_CIRCLE, EnumFacing.UP, 1, 0);
		this.circles.add(top);
	}
	
	@Override
	public void draw(double x, double y, double z, double time) {
		if (this.isExpired()) return;
		if (this.currTime < START_TICK) {
			for (int i = 0; i < this.circles.size(); i++) {
				CircleStats circle = this.circles.get(i);
				circle.setDiameter(CIRCLE_DIAMETER[i] * this.currTime / (START_TICK - 1));
			}
		}
		
		if (this.currTime >= START_TICK && this.currTime < this.animationLength - END_TICK) {
			for (int i = 0; i < this.circles.size(); i++) {
				CircleStats circle = this.circles.get(i);
				circle.setDiameter(CIRCLE_DIAMETER[i]);
			}
		}
		
		this.circles.get(0).setColor(new float[] {1, 1, 1, (float) MathHelper.oscillate(time * 6, 0.25, 1)});
		for (int i = 1; i < 4; i++) {
			this.circles.get(i).setColor(new float[] {1, 1, 1, (float) MathHelper.oscillate(time * 8, 0.25, 1)});
		}
		this.circles.get(4).setColor(new float[] {1, 1, 1, (float) MathHelper.oscillate(time * 4, 0.5, 1)});
		
		if (this.currTime >= this.animationLength - END_TICK) {
			for (int i = 0; i < this.circles.size(); i++) {
				CircleStats circle = this.circles.get(i);
				circle.setDiameter(CIRCLE_DIAMETER[i] * (this.animationLength - this.currTime) / END_TICK);
			}
		}
		super.draw(x, y, z, time);
	}

	@Override
	protected int getAnimationLength() {
		return ANIMATION_LENGTH;
	}
}
