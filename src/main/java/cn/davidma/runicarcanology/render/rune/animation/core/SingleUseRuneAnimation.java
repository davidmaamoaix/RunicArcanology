package cn.davidma.runicarcanology.render.rune.animation.core;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.runicarcanology.render.rune.EnumCircle;

public class SingleUseRuneAnimation extends RuneAnimation {

	public int animationLength;
	public int currTime;
	
	private boolean expired;
	
	public SingleUseRuneAnimation(int animationLength) {
		super();
		this.animationLength = animationLength;
		this.currTime = 0;
		this.expired = true;
	}
	
	@Override
	public void draw(double x, double y, double z, double time) {
		if (this.expired) return;
		super.draw(x, y, z, time);
	}
	
	public void tick() {
		this.currTime++;
		if (this.currTime >= this.animationLength) this.setExpired();
	}
	
	public void play() {
		if (this.expired) {
			this.expired = false;
			this.currTime = 0;
		}
	}
	
	public void setExpired() {
		this.expired = true;
	}
	
	public boolean isExpired() {
		return this.expired;
	}
}
