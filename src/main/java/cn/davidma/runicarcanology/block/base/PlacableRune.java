package cn.davidma.runicarcanology.block.base;

import com.google.common.base.Predicate;

import cn.davidma.runicarcanology.tileentity.ActivatableRuneTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
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
	
	public PlacableRune(String name) {
		super(name, Material.IRON);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
	}
	
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) return true;
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity == null || !(tileEntity instanceof ActivatableRuneTileEntity)) return true;
		((ActivatableRuneTileEntity) tileEntity).playerClick();
		
		return true;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return DOWN;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}
	
	private static boolean canPlaceAt(World worldIn, BlockPos pos, EnumFacing facing) {
		
		// Straight from the BlockButton class as I'm lazy.
		BlockPos blockPos = pos.offset(facing.getOpposite());
		IBlockState state = worldIn.getBlockState(blockPos);
		boolean flag = state.getBlockFaceShape(worldIn, blockPos, facing) == BlockFaceShape.SOLID;
		Block block = state.getBlock();
		
		if (facing == EnumFacing.UP) {
			return state.isTopSolid() || !isExceptionBlockForAttaching(block) && flag;
		} else {
			return !isExceptBlockForAttachWithPiston(block) && flag;
		}
	}
}
