package cn.davidma.runicarcanology.render.rune.animation.activatable;

import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.render.rune.animation.core.ActivatableRuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.CircleStats;
import net.minecraft.util.EnumFacing;

public class ArcticWindActivatable extends ActivatableRuneAnimation {

	public ArcticWindActivatable() {
		super();
		
		float[] color = new float[] {86 / 255.0F, 158 / 255.0F, 214 / 255.0F};
		
		CircleStats base = new CircleStats(EnumCircle.DASH_CIRCLE, EnumFacing.UP, 2, 0.1);
		this.circles.add(base);
		
		CircleStats square_inner = new CircleStats(EnumCircle.SQUARE, EnumFacing.UP, -2, 0.6);
		this.circles.add(square_inner);
		
		CircleStats square_outer = new CircleStats(EnumCircle.SQUARE, EnumFacing.UP, -2, 0.65);
		this.circles.add(square_outer);
		
		for (CircleStats i: this.circles) {
			i.setColor(color);
			i.setAlpha(0);
			i.setyOffset(i.getyOffset() + 0.0625);
		}
	}
}
