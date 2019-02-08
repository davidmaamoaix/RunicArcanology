package cn.davidma.runicarcanology.item;

import cn.davidma.runicarcanology.item.base.StandardItemBase;
import cn.davidma.runicarcanology.tileentity.base.IItemFilter;
import cn.davidma.runicarcanology.util.inventory.ItemFilterHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ItemFilter extends StandardItemBase {

	private ItemFilterHelper itemFilter;
	private boolean whitelist;
	private boolean ignoreMeta;
	
	public ItemFilter(String name) {
		super(name);
		this.whitelist = false;
		this.ignoreMeta = false;
		this.itemFilter = new ItemFilterHelper(this.whitelist, this.ignoreMeta);
		
		this.setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float x, float y, float z) {
		if (world.isRemote) return EnumActionResult.SUCCESS;
		
		if (player.isSneaking()) {
			this.clearFilter();
			return EnumActionResult.SUCCESS;
		}
		
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity == null) return EnumActionResult.FAIL;
		
		if (tileEntity instanceof IItemFilter) {
			((IItemFilter) tileEntity).setFilter(this.itemFilter);
			return EnumActionResult.SUCCESS;
		}
		
		IItemHandler inv = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
		if (inv == null) return EnumActionResult.FAIL;
		
		for (int i = 0; i < inv.getSlots(); i++) {
			this.itemFilter.addStack(inv.getStackInSlot(i));
		}
		
		return EnumActionResult.SUCCESS;
	}
	
	private void clearFilter() {
		this.itemFilter = new ItemFilterHelper(this.whitelist, this.ignoreMeta);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return this.itemFilter.isEmpty();
	}
}
