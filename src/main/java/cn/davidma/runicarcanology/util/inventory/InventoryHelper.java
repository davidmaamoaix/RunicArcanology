package cn.davidma.runicarcanology.util.inventory;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class InventoryHelper {

	public static boolean canInsertStack(IItemHandler inv, ItemStack stack) {
		ItemStack remain = ItemHandlerHelper.insertItemStacked(inv, stack, true);
		return remain.getCount() < stack.getCount();
	}
	
	public static ItemStack insertStack(IItemHandler inv, ItemStack stack) {
		return ItemHandlerHelper.insertItemStacked(inv, stack, false);
	}
	
	public static ItemStack copyStack(ItemStack stack, int count) {
		ItemStack copy = stack.copy();
		copy.setCount(count);
		return copy;
	}
}
