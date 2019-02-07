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
	
	public EnumFacing getRuneFacing() {
		return this.runeFacing;
	}
	
	public void setRuneFacing(EnumFacing facing) {
		this.runeFacing = facing;
		
		double initialRotation = 0;
		switch(this.runeFacing) {
			case UP: initialRotation = -135;break;
			case NORTH: initialRotation = 45;break;
			case EAST: initialRotation = 45; break;
			default: initialRotation = 135; break;
		}
		
		for (RuneAnimation i: this.animations) {
			for (CircleStats circle: i.circles) {
				circle.setFacing(this.runeFacing);
				circle.setRotationOffset(initialRotation);
			}
		}
	} 
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.runeFacing = EnumFacing.values()[nbt.getInteger(NBTHelper.RUNE_FACING)];
		this.setRuneFacing(this.runeFacing); // To update all the runes.
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger(NBTHelper.RUNE_FACING, this.runeFacing.ordinal());
		return super.writeToNBT(nbt);
	}
}
