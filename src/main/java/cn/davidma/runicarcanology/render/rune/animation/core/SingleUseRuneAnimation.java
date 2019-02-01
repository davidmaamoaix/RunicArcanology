package cn.davidma.runicarcanology.render.rune.animation.core;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.runicarcanology.render.rune.EnumCircle;
import cn.davidma.runicarcanology.render.rune.EnumRuneAnimation;
import net.minecraft.nbt.NBTTagList;

public abstract class SingleUseRuneAnimation extends RuneAnimation {

	protected int animationLength;
	protected int currTime;
	
	private boolean expired;
	
	public SingleUseRuneAnimation() {
		super();
		this.currTime = 0;
		this.animationLength = this.getAnimationLength();
		this.expired = false;
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
	
	public void setExpired() {
		this.expired = true;
	}
	
	public boolean isExpired() {
		return this.expired;
	}
	
	public int getCurrTime() {
		return this.currTime;
	}
	
	public void setCurrTime(int currTime) {
		this.currTime = currTime;
	}
	
	protected abstract int getAnimationLength();
}
