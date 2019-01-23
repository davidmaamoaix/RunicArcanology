package cn.davidma.runicarcanology.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArcaneWorkbenchTileEntity extends TileEntity implements ITickable {

	// To control the progress of crafting.
	private int craftingTick;
	
	// To handle the passive animations.
	// 7200 ticks per cycle.
	private int animationTick;
	
	@Override
	public void update() {
		
		// Animations only update on client.
		if (this.world.isRemote) {
			this.animationTick++;
			this.animationTick %= 7200;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		return super.writeToNBT(nbt);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(this.getPos().add(-3, 0, -3), this.getPos().add(3, 3, 3));
	}
	
	public int getAnimationTick() {
		return this.animationTick;
	}
}
