package cn.davidma.runicarcanology.render.rune.animation.ambient;

import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.Circle;
import cn.davidma.runicarcanology.render.rune.animation.core.CircleStats;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import net.minecraft.util.EnumFacing;

public class WorkbenchAnimation extends RuneAnimation {

	public WorkbenchAnimation() {
		CircleStats base = new CircleStats(Circle.RUNE_CIRCLE, EnumFacing.UP, -2, 2);
		base.setxOffset(0.5);
		base.setyOffset(AnimationHelper.DISTINCTION_OFFSET);
		base.setzOffset(0.5);
		this.circles.add(base);
	}
}
