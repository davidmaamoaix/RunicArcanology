package cn.davidma.runicarcanology.render.rune.animation.core;

public class ActivatableRuneAnimation extends RuneAnimation {

	protected int runeState; // 0: Start; 1: Normal; 2: Stop
	protected int timer;
	
	public ActivatableRuneAnimation() {
		super();
		this.runeState = 0;
		this.timer = 0;
	}
	
	@Override
	public void draw(double x, double y, double z, double time) {
		super.draw(x, y, z, time);
		if (this.runeState == 1) {
			this.timer = 0;
		} else {
			this.timer++;
		}
	}
	
	public void setRuneState(int runeState) {
		this.runeState = runeState;
	}
}
