package cn.davidma.runicarcanology.render.rune.animation.core;

public class ActivatableRuneAnimation extends RuneAnimation {

	private static final float ANIMATION_DURATION = 20.0F;
	
	private boolean active;
	
	public ActivatableRuneAnimation() {
		super();
	}
	
	@Override
	public void draw(double x, double y, double z, double time) {
		super.draw(x, y, z, time);
		for (CircleStats i: this.circles) {
			if (this.active) {
				i.setAlpha(Math.min(i.getAlpha() + 1.0F / ANIMATION_DURATION, 1));
			} else {
				i.setAlpha(Math.max(i.getAlpha() - 1.0F / ANIMATION_DURATION, 0));
			}
		}
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}
