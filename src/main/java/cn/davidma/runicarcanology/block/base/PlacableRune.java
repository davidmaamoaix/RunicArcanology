package cn.davidma.runicarcanology.block.base;

import com.google.common.base.Predicate;

import cn.davidma.runicarcanology.tileentity.base.PlacableRuneTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PlacableRune extends TransparentTileEntityBlock<PlacableRuneTileEntity> {

	protected static final AxisAlignedBB DOWN = new AxisAlignedBB(0.25, 0, 0.25, 0.75, 0.125, 0.75);
	protected static final AxisAlignedBB UP = new AxisAlignedBB(0.25, 0.875, 0.25, 0.75, 1, 0.75);
	protected static final AxisAlignedBB NORTH = new AxisAlignedBB(0.25, 0.25, 0.875, 0.75, 0.75, 1);
    protected static final AxisAlignedBB SOUTH = new AxisAlignedBB(0.25, 0.25, 0.0D, 0.75, 0.75, 0.125);
    protected static final AxisAlignedBB WEST = new AxisAlignedBB(0.875, 0.25, 0.25, 1, 0.75, 0.75);
    protected static final AxisAlignedBB EAST = new AxisAlignedBB(0.0D, 0.25, 0.25, 0.125, 0.75, 0.75);
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>() {

		@Override
		public boolean apply(EnumFacing input) {
			return true;
		}
	});
	
	private Class<? extends PlacableRuneTileEntity> runeTEClass;
	
	public PlacableRune(String name, Class<? extends PlacableRuneTileEntity> runeTEClass) {
		super(name, Material.IRON);
		this.runeTEClass = runeTEClass;
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
	}
	
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) return true;
		TileEntity tileEntity = this.getTileEntity(world, pos);
		if (tileEntity == null || !(tileEntity instanceof PlacableRuneTileEntity)) return true;
		((PlacableRuneTileEntity) tileEntity).playerClick();
		
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
	
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase player) {
		EnumFacing newFacing = EnumFacing.NORTH;
		if (canPlaceBlock(world, pos, facing)) {
			newFacing = facing;
		} else {
			for (EnumFacing i: EnumFacing.values()) {
				if (canPlaceBlock(world, pos, i)) {
					newFacing = i;
				}
			}
		}
		
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity != null && tileEntity instanceof PlacableRuneTileEntity) {
			((PlacableRuneTileEntity) tileEntity).setRuneFacing(newFacing);
		}
		
		return this.getDefaultState().withProperty(FACING, newFacing);
	}
	
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
		return canPlaceBlock(world, pos, side);
	}
	
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		for (EnumFacing i: EnumFacing.values()) {
			if (canPlaceBlock(world, pos, i)) {
				return true;
			}
		}
		return false;
	}
	
	// Straight from the BlockButton class as I'm lazy.
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (this.checkForDrop(world, pos, state) && !canPlaceBlock(world, pos, (EnumFacing) state.getValue(FACING))) {
			this.dropBlockAsItem(world, pos, state, 0);
			world.setBlockToAir(pos);
		}
	}
	
	// Straight from the BlockButton class as I'm lazy.
	private boolean checkForDrop(World world, BlockPos pos, IBlockState state) {
		if (this.canPlaceBlockAt(world, pos)) {
			return true;
		} else {
			this.dropBlockAsItem(world, pos, state, 0);
			world.setBlockToAir(pos);
			return false;
		}
	}
	
	// Straight from the BlockButton class as I'm lazy.
	private static boolean canPlaceBlock(World world, BlockPos pos, EnumFacing facing) {
		BlockPos blockPos = pos.offset(facing.getOpposite());
		IBlockState state = world.getBlockState(blockPos);
		boolean flag = state.getBlockFaceShape(world, blockPos, facing) == BlockFaceShape.SOLID;
		Block block = state.getBlock();
		
		if (facing == EnumFacing.UP) {
			return state.isTopSolid() || !isExceptionBlockForAttaching(block) && flag;
		} else {
			return !isExceptBlockForAttachWithPiston(block) && flag;
		}
	}
	
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).ordinal();
	}
	
	public IBlockState getStateFromMeta(int meta) {
		try {
			return this.getDefaultState().withProperty(FACING, EnumFacing.values()[meta]);
		} catch (ArrayIndexOutOfBoundsException e) {
			return this.getDefaultState();
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}

	@Override
	public Class<PlacableRuneTileEntity> getTileEntityClass() {
		return (Class<PlacableRuneTileEntity>) this.runeTEClass;
	}

	@Override
	public PlacableRuneTileEntity createTileEntity(World world, IBlockState state) {
		try {
			return this.runeTEClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
