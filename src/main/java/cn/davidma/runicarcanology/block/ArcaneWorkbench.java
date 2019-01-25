package cn.davidma.runicarcanology.block;

import java.util.List;

import cn.davidma.runicarcanology.block.template.TransparentTileEntityBlock;
import cn.davidma.runicarcanology.tileentity.ArcaneWorkbenchTileEntity;
import cn.davidma.runicarcanology.util.Msg;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ArcaneWorkbench extends TransparentTileEntityBlock<ArcaneWorkbenchTileEntity> {

	public ArcaneWorkbench(String name) {
		super(name, Material.IRON);
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
		AxisAlignedBB detectBox = new AxisAlignedBB(0, 0, 0, 0.875, 0.875, 0.875).offset(pos);
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
}
