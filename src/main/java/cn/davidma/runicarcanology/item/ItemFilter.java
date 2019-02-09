package cn.davidma.runicarcanology.item;

import java.util.List;

import javax.annotation.Nullable;

import cn.davidma.runicarcanology.item.base.StandardItemBase;
import cn.davidma.runicarcanology.tileentity.base.IItemFilter;
import cn.davidma.runicarcanology.util.Msg;
import cn.davidma.runicarcanology.util.inventory.ItemFilterHelper;
import net.minecraft.client.util.ITooltipFlag;
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
	
	public ItemFilter(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float x, float y, float z) {
		if (world.isRemote) return EnumActionResult.SUCCESS;
		
		ItemStack stack = player.inventory.getCurrentItem();
		ItemFilterHelper itemFilter = ItemFilterHelper.filterFromStack(stack);
		
		if (!player.isSneaking()) return EnumActionResult.FAIL;
		
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity == null) {
			if (itemFilter.isEmpty()) {
				return EnumActionResult.FAIL;
			} else {
				clearFilter(stack);
				Msg.tellPlayer(player, "alert.content_cleared.key");
				return EnumActionResult.SUCCESS;
			}
		}
		
		if (tileEntity instanceof IItemFilter) {
			//((IItemFilter) tileEntity).setFilter(ItemFilterHelper.filterFromNBT());
			return EnumActionResult.SUCCESS;
		}
		
		IItemHandler inv = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
		if (inv == null) {
			if (itemFilter.isEmpty()) {
				return EnumActionResult.FAIL;
			} else {
				clearFilter(stack);
				Msg.tellPlayer(player, "alert.content_cleared.key");
				return EnumActionResult.SUCCESS;
			}
		}
		
		if (!itemFilter.isEmpty()) return EnumActionResult.FAIL;
		itemFilter.clearFilter();
		
		for (int i = 0; i < inv.getSlots(); i++) {
			itemFilter.addStack(inv.getStackInSlot(i));
		}
		
		if (itemFilter.isEmpty()) {
			Msg.tellPlayer(player, "error.empty_inventory.key");
		} else {
			Msg.tellPlayer(player, "alert.content_saved.key");
		}
		
		ItemFilterHelper.setFilter(stack, itemFilter);
		return EnumActionResult.SUCCESS;
	}
	
	private static void clearFilter(ItemStack stack) {
		ItemFilterHelper itemFilter = ItemFilterHelper.filterFromStack(stack);
		if (itemFilter == null) itemFilter = new ItemFilterHelper();
		itemFilter.clearFilter();
		ItemFilterHelper.setFilter(stack, itemFilter);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return !ItemFilterHelper.filterFromStack(stack).isEmpty();
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flagIn) {
		ItemFilterHelper itemFilter = ItemFilterHelper.filterFromStack(stack);
		tooltip.add(itemFilter.isWhitelist() ? "Whitelist" : "Blacklist");
		if (itemFilter.isEmpty()) tooltip.add("Empty");
		else tooltip.add(String.format("Contains %d Items", itemFilter.size()));
		if (itemFilter.ignoreMeta()) tooltip.add("Ignores Metadata");
	}
}
