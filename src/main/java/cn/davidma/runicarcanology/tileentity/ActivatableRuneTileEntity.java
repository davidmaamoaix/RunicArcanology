package cn.davidma.runicarcanology.tileentity;

import com.google.common.base.Predicate;

import cn.davidma.runicarcanology.render.rune.animation.core.ActivatableRuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import net.minecraft.tileentity.TileEntity;

public abstract class ActivatableRuneTileEntity extends RuneHandlingTileEntity {
	
	private boolean active;
	
	public void playerClick() {
		for (RuneAnimation animation: this.animations) {
			if (animation instanceof ActivatableRuneAnimation) {
				ActivatableRuneAnimation activatable = (ActivatableRuneAnimation) animation;
				if (this.active) {
					activatable.setRuneState(2);
				} else {
					activatable.setRuneState(0);
				}
			}
		}
		this.active = !this.active;
	}
	
	public boolean isActive() {
		return this.active;
	}
}
