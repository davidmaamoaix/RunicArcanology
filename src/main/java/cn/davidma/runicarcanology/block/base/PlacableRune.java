package cn.davidma.runicarcanology.block.base;

import com.google.common.base.Predicate;

import cn.davidma.runicarcanology.tileentity.ActivatableRuneTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class PlacableRune<TE extends ActivatableRuneTileEntity> extends TransparentTileEntityBlock<TE> {

	protected static final AxisAlignedBB DOWN = new AxisAlignedBB(0.25, 0, 0.25, 0.75, 0.125, 0.75);
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>() {

		@Override
		public boolean apply(EnumFacing input) {
			return true;
		}
	});
	
	public PlacableRune(String name, Material material) {
		super(name, material);
	}
	
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) return true;
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity == null || !(tileEntity instanceof ActivatableRuneTileEntity)) return true;
		((ActivatableRuneTileEntity) tileEntity).playerClick();
		
		return true;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return DOWN;
	}
	
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}
	
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
