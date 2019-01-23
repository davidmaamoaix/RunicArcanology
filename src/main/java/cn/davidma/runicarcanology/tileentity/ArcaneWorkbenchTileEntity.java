package cn.davidma.runicarcanology.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class ArcaneWorkbenchTileEntity extends TileEntity implements ITickable {

	// To control the progress of crafting.
	private int craftingTick;
	
	// To handle the passive animations.
	// 7200 ticks per cycle.
	private int animationTick;
	
	@Override
	public void update() {
		this.animationTick++;
		this.animationTick %= 7200;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		return super.writeToNBT(nbt);
	}
	
	public int getAnimationTick() {
		return this.animationTick;
	}
}
