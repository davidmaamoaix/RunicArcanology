package cn.davidma.runicarcanology.render.rune.animation.core;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.runicarcanology.render.rune.Circle;

public class SingleUseRuneAnimation extends RuneAnimation {

	public int animationLength;
	public int currTime;
	
	private boolean expired;
	
	public SingleUseRuneAnimation(int animationLength) {
		super();
		this.animationLength = animationLength;
		currTime = 0;
		expired = true;
	}
	
	@Override
	public void tick(double x, double y, double z, double time) {
		if (expired) return;
		super.tick(x, y, z, time);
		currTime++;
		if (currTime >= animationLength) this.setExpired();
	}
	
	public void play() {
		this.expired = false;
	}
	
	public void setExpired() {
		this.expired = true;
	}
	
	public boolean isExpired() {
		return this.expired;
	}
}
