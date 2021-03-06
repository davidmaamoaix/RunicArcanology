package cn.davidma.runicarcanology.block.base;

import javax.annotation.Nullable;

import cn.davidma.runicarcanology.registry.RATileEntities;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class TileEntityBlock<TE extends TileEntity> extends StandardBlockBase {

	public abstract Class<TE> getTileEntityClass();
	
	public TileEntityBlock(String name, Material material) {
		super(name, material);
		RATileEntities.addTileEntityClass(this.getRegistryName(), this.getTileEntityClass());
	}

	public TileEntity getTileEntity(IBlockAccess world, BlockPos pos) {
		return world.getTileEntity(pos);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Nullable
	@Override
	public abstract TE createTileEntity(World world, IBlockState state);
}
