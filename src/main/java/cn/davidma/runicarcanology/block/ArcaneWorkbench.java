package cn.davidma.runicarcanology.block;

import java.util.List;
import java.util.Random;

import cn.davidma.runicarcanology.block.base.TransparentTileEntityBlock;
import cn.davidma.runicarcanology.render.particle.LightParticle;
import cn.davidma.runicarcanology.tileentity.ArcaneWorkbenchTileEntity;
import cn.davidma.runicarcanology.util.Msg;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ArcaneWorkbench extends TransparentTileEntityBlock<ArcaneWorkbenchTileEntity> {

	public ArcaneWorkbench(String name) {
		super(name, Material.IRON);
		this.setResistance(6000);
		this.setHardness(25);
		this.BOUNDING_BOX = new AxisAlignedBB(0, 0, 0, 1, 0.1875D, 1);
		this.COLLISION_BOX = new AxisAlignedBB(0, 0, 0, 1, 0.125D, 1);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if (world.isRemote) return true;
		
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity == null || !(tileEntity instanceof ArcaneWorkbenchTileEntity)) return true;
		ArcaneWorkbenchTileEntity workbenchTileEntity = (ArcaneWorkbenchTileEntity) tileEntity;
		
		// Get all colliding entities.
		AxisAlignedBB detectBox = new AxisAlignedBB(-0.5, 0, -0.5, 1.5, 1, 1.5).offset(pos);
		List <? extends Entity > collidingEntities;
		collidingEntities = world.getEntitiesWithinAABBExcludingEntity((Entity) null, detectBox);
		workbenchTileEntity.playerClick(player, collidingEntities);
		return true;
	}
	
	@Override
	public Class getTileEntityClass() {
		return ArcaneWorkbenchTileEntity.class;
	}

	@Override
	public ArcaneWorkbenchTileEntity createTileEntity(World world, IBlockState state) {
		return new ArcaneWorkbenchTileEntity();
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity != null) {
			IItemHandler itemHandler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			for (int i = 0; i < itemHandler.getSlots(); i++) {
				ItemStack stack = itemHandler.getStackInSlot(i);
				if (!stack.isEmpty()) {
					EntityItem item = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5, stack);
					world.spawnEntity(item);
				}
			}
		}
		super.breakBlock(world, pos, state);
	}
}
