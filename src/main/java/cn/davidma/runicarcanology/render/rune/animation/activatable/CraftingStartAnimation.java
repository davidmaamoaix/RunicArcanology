package cn.davidma.runicarcanology.render.rune.animation.activatable;

import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.render.rune.animation.core.CircleStats;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.SingleUseRuneAnimation;
import net.minecraft.util.EnumFacing;

public class CraftingStartAnimation extends RuneAnimation {

	private static final int ANIMATION_LENGTH = 40;
	private static final double SMALL_RUNE_DISTANCE = 2;
	
	public CraftingStartAnimation() {
		this.circles.add(new CircleStats(EnumCircle.DASH_CIRCLE, EnumFacing.UP, 3, 2.75));
		for (int i = 0; i < 3; i++) {
			CircleStats square = new CircleStats(EnumCircle.SQUARE, EnumFacing.UP, -2, 2.75);
			square.setRotationOffset(i * 30);
			this.circles.add(square);
		}
		this.circles.add(new CircleStats(EnumCircle.RUNE_CIRCLE, EnumFacing.UP, 1, 5));
	}
	
	@Override
	public void tick(double x, double y, double z, double time) {
		this.circles.get(0).setColor(new float[] {1, 1, 1, (float) AnimationHelper.oscillate(time * 6, 0.25, 1)});
		for (int i = 1; i < 4; i++) {
			this.circles.get(i).setColor(new float[] {1, 1, 1, (float) AnimationHelper.oscillate(time * 8, 0.25, 1)});
		}
		this.circles.get(4).setColor(new float[] {1, 1, 1, (float) AnimationHelper.oscillate(time * 4, 0.5, 1)});
		super.tick(x, y, z, time);
	}
}
