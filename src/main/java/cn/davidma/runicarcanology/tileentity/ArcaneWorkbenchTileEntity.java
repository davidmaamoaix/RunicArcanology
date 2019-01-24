package cn.davidma.runicarcanology.tileentity;

import cn.davidma.runicarcanology.util.Msg;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArcaneWorkbenchTileEntity extends TileEntity implements ITickable {

	// To control the progress of crafting.
	private int craftingTick;
	
	@Override
	public void update() {
		
	}
	
	public void playerClick(EntityPlayer player) {
		if (!this.enoughSpace()) {
			Msg.tellPlayer(player, I18n.format("error.workbench_space.key"));
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
	
	public boolean enoughSpace() {
		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				for (int k = 0; k < 2; k++) {
					// Do not check the position of this block.
					if (i == 0 && j == 0 && k == 0) continue;
					
					BlockPos check = new BlockPos(this.pos.getX() + i, this.pos.getY() + k, this.pos.getZ() + j);
					if (!this.world.isAirBlock(check)) return false;
				}
			}
		}
		return true;
	}
}
