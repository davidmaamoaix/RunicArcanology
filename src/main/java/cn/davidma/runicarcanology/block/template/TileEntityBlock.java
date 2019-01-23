package cn.davidma.runicarcanology.block.template;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class TileEntityBlock<TileEntityClass extends TileEntity> extends StandardBlockBase {

	public abstract Class<TileEntityClass> getTileEntityClass();
	
	public TileEntityBlock(String name, Material material) {
		super(name, material);
	}

	public TileEntityClass getTileEntity(IBlockAccess world, BlockPos pos) {
		return (TileEntityClass) world.getTileEntity(pos);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Nullable
	@Override
	public abstract TileEntityClass createTileEntity(World world, IBlockState state);
}
