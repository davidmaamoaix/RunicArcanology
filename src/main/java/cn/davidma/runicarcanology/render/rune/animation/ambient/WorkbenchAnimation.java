package cn.davidma.runicarcanology.render.rune.animation.ambient;

import cn.davidma.runicarcanology.render.rune.AnimationHelper;
import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.render.rune.EnumRuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.CircleStats;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import net.minecraft.util.EnumFacing;

public class WorkbenchAnimation extends RuneAnimation {
	
	public WorkbenchAnimation() {
		super();
		
		CircleStats base = new CircleStats(EnumCircle.RUNE_CIRCLE, EnumFacing.UP, -2, 2);
		this.circles.add(base);
	}
}
