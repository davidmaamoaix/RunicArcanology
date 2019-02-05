package cn.davidma.runicarcanology.block.base;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class TransparentTileEntityBlock<TE extends TileEntity> extends TileEntityBlock<TE> {

	public TransparentTileEntityBlock(String name, Material material) {
		super(name, material);
		this.setLightOpacity(1);
	}
	
	@Override
	public boolean isFullBlock(IBlockState bs) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState bs) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState bs) {
		return false;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public boolean causesSuffocation(IBlockState state) {
		return false;
	}
}
