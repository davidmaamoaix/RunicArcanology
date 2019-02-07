package cn.davidma.runicarcanology.tileentity.base;

import cn.davidma.runicarcanology.render.rune.animation.core.ActivatableRuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.util.NBTHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public abstract class ActivatableRuneTileEntity extends PlacableRuneTileEntity {

	private boolean isActive;
	
	public void playerClick() {
		this.isActive = !this.isActive;
		this.updateActivatableAnimation();
		this.save();
	}
	
	public boolean isActive() {
		return this.isActive;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.isActive = nbt.getBoolean(NBTHelper.IS_ACTIVE);
		this.updateActivatableAnimation();
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setBoolean(NBTHelper.IS_ACTIVE, this.isActive);
		return super.writeToNBT(nbt);
	}
	
	private void updateActivatableAnimation() {
		for (RuneAnimation animation: this.animations) {
			if (animation instanceof ActivatableRuneAnimation) {
				ActivatableRuneAnimation activatable = (ActivatableRuneAnimation) animation;
				activatable.setActive(this.isActive);
			}
		}
	}
}
