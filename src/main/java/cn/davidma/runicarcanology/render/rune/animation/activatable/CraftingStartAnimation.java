package cn.davidma.runicarcanology.render.rune.animation.activatable;

import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.render.rune.animation.core.CircleStats;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.SingleUseRuneAnimation;
import net.minecraft.util.EnumFacing;

public class CraftingStartAnimation extends SingleUseRuneAnimation {

	private static final int ANIMATION_LENGTH = 200;
	
	private static final int START_TICK = 15;
	private static final int END_TICK = 15;
	private static final float[] CIRCLE_DIAMETER = new float[] {2.75F, 2.75F, 2.75F, 2.75F, 5};
	
	public CraftingStartAnimation() {
		super(ANIMATION_LENGTH);
		this.circles.add(new CircleStats(EnumCircle.DASH_CIRCLE, EnumFacing.UP, 3, 0));
		for (int i = 0; i < 3; i++) {
			CircleStats square = new CircleStats(EnumCircle.SQUARE, EnumFacing.UP, -2, 0);
			square.setRotationOffset(i * 30);
			this.circles.add(square);
		}
		this.circles.add(new CircleStats(EnumCircle.RUNE_CIRCLE, EnumFacing.UP, 1, 0));
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
		
		this.circles.get(0).setColor(new float[] {1, 1, 1, (float) AnimationHelper.oscillate(time * 6, 0.25, 1)});
		for (int i = 1; i < 4; i++) {
			this.circles.get(i).setColor(new float[] {1, 1, 1, (float) AnimationHelper.oscillate(time * 8, 0.25, 1)});
		}
		this.circles.get(4).setColor(new float[] {1, 1, 1, (float) AnimationHelper.oscillate(time * 4, 0.5, 1)});
		
		if (this.currTime >= this.animationLength - END_TICK) {
			for (int i = 0; i < this.circles.size(); i++) {
				CircleStats circle = this.circles.get(i);
				circle.setDiameter(CIRCLE_DIAMETER[i] * (this.animationLength - this.currTime) / END_TICK);
			}
		}
		super.draw(x, y, z, time);
	}
}
