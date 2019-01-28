package cn.davidma.runicarcanology.tileentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.davidma.runicarcanology.render.rune.EnumRune;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.SingleUseRuneAnimation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public abstract class RuneHandlingTileEntity extends TileEntity implements ITickable {

	protected List<RuneAnimation> animations;
	
	public RuneHandlingTileEntity() {
		super();
		this.init();
	}
	
	public void init() {
		animations = new ArrayList<RuneAnimation>();
		this.createAnimations();
	}
	
	public void playAnimation(EnumRune rune) {
		this.animations.add(rune.create());
	}
	
	@Override
	public void update() {
		List<RuneAnimation> expiredAnimations = new ArrayList<RuneAnimation>();
		for (RuneAnimation i: this.animations) {
			if (i instanceof SingleUseRuneAnimation) {
				SingleUseRuneAnimation animation = ((SingleUseRuneAnimation) i);
				animation.tick();
				if (animation.isExpired()) {
					expiredAnimations.add(i);
				}
			}
		}
		this.animations.removeAll(expiredAnimations);
	}
	
	public List<? extends RuneAnimation> getAnimations() {
		return new ArrayList<RuneAnimation>(this.animations);
	}
	
	public void addPassiveAnimation(EnumRune animation) {
		this.animations.add(animation.create());
	}
	
	protected abstract void createAnimations();
}
