package cn.davidma.runicarcanology.render.rune.animation.activatable;

import cn.davidma.runicarcanology.render.rune.Circle;
import cn.davidma.runicarcanology.render.rune.animation.core.SingleUseRuneAnimation;

public class CraftingStartAnimation extends SingleUseRuneAnimation {

	private static final int ANIMATION_LENGTH = 40;
	
	public CraftingStartAnimation() {
		super(ANIMATION_LENGTH);
		
	}
	
	@Override
	public void tick(double x, double y, double z, double time) {
		super.tick(x, y, z, time);
	}
}
