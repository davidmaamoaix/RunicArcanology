package cn.davidma.runicarcanology.tileentity;

import java.util.List;

import cn.davidma.runicarcanology.render.rune.EnumRune;
import cn.davidma.runicarcanology.util.Msg;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ArcaneWorkbenchTileEntity extends RuneHandlingTileEntity implements ITickable {

	// To control the progress of crafting.
	private boolean crafting;
	private int craftingTick;
	
	@Override
	protected void createAnimations() {
		this.addAnimation(EnumRune.WORKBENCH_PASSIVE);
		this.addAnimation(EnumRune.CRAFTING_START);
	}
	
	@Override
	public void update() {
		
	}
	
	public void playerClick(EntityPlayer player, List<? extends Entity> collidingEntity) {
		if (!this.enoughSpace()) {
			Msg.tellPlayer(player, I18n.format("error.workbench_space.key"));
		} else {
			IItemHandler itemHandler = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
			Msg.tellPlayer(player, Integer.toString(collidingEntity.size()));
			for (Entity i: collidingEntity) {
				if (i instanceof EntityItem) {
					EntityItem item = ((EntityItem) i);
					Msg.tellPlayer(player, ((EntityItem) i).getItem().getDisplayName());
				}
			}
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
	
	public boolean isCrafting() {
		return this.crafting;
	}
}
