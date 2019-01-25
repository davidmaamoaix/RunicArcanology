package cn.davidma.runicarcanology.tileentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.davidma.runicarcanology.render.rune.EnumRune;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.SingleUseRuneAnimation;
import net.minecraft.tileentity.TileEntity;

public abstract class RuneHandlingTileEntity extends TileEntity {

	protected Map<EnumRune, RuneAnimation> animations;
	
	public RuneHandlingTileEntity() {
		super();
		this.init();
	}
	
	public void init() {
		animations = new HashMap<EnumRune, RuneAnimation>();
		this.createAnimations();
	}
	
	public void playAnimation(EnumRune rune) {
		RuneAnimation target = this.animations.get(rune);
		if (target instanceof SingleUseRuneAnimation) {
			((SingleUseRuneAnimation) target).play();
		}
	}
	
	public List<? extends RuneAnimation> getAnimations() {
		return new ArrayList<RuneAnimation>(this.animations.values());
	}
	
	public void addAnimation(EnumRune animation) {
		this.animations.put(animation, animation.create());
	}
	
	protected abstract void createAnimations();
}
