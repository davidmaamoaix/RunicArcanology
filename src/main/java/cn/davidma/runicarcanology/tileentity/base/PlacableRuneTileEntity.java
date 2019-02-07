package cn.davidma.runicarcanology.tileentity.base;

import com.google.common.base.Predicate;

import cn.davidma.runicarcanology.render.rune.animation.core.ActivatableRuneAnimation;
import cn.davidma.runicarcanology.render.rune.animation.core.CircleStats;
import cn.davidma.runicarcanology.render.rune.animation.core.RuneAnimation;
import cn.davidma.runicarcanology.util.NBTHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public abstract class PlacableRuneTileEntity extends RuneHandlingTileEntity {
	
	private EnumFacing runeFacing;
	private boolean isActive;
	
	public void playerClick() {
		/*for (RuneAnimation animation: this.animations) {
			if (animation instanceof ActivatableRuneAnimation) {
				ActivatableRuneAnimation activatable = (ActivatableRuneAnimation) animation;
				if (this.isActive) {
					activatable.setRuneState(2);
				} else {
					activatable.setRuneState(0);
				}
			}
		}
		this.isActive = !this.isActive;*/
	}
	
	public EnumFacing getRuneFacing() {
		return this.runeFacing;
	}
	
	public void setRuneFacing(EnumFacing facing) {
		this.runeFacing = facing;
		double initialRotation = 0;
		switch(this.runeFacing) {
			case UP: initialRotation = -45;
			default: break;
		}
		for (RuneAnimation i: this.animations) {
			for (CircleStats circle: i.circles) {
				circle.setFacing(this.runeFacing);
			}
		}
	} 
	
	public boolean isActive() {
		return this.isActive;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.runeFacing = EnumFacing.values()[nbt.getInteger(NBTHelper.RUNE_FACING)];
		this.isActive = nbt.getBoolean(NBTHelper.IS_ACTIVE);
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger(NBTHelper.RUNE_FACING, this.runeFacing.ordinal());
		nbt.setBoolean(NBTHelper.IS_ACTIVE, this.isActive);
		return super.writeToNBT(nbt);
	}
}
