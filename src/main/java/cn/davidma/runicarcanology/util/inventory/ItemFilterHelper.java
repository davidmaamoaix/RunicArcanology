package cn.davidma.runicarcanology.util.inventory;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class ItemFilterHelper {
	
	private List<ItemStack> contents;
	private boolean whitelist;
	private boolean ignoreMeta;
	
	public ItemFilterHelper(boolean whitelist, boolean ignoreMeta) {
		this.contents = new ArrayList<ItemStack>();
		this.whitelist = whitelist;
		this.ignoreMeta = ignoreMeta;
	}
	
	public boolean hasStack(ItemStack stack) {
		if (stack.isEmpty()) return false;
		
		for (ItemStack i: this.contents) {
			if (this.match(i, stack)) return true;
		}
		
		return false;
	}
	
	public void addStack(ItemStack stack) {
		if (stack.isEmpty()) return;
		
		ItemStack sample = InventoryHelper.copyStack(stack, 1);
		
		// Add if not exist.
		if (!this.hasStack(sample)) this.contents.add(sample);
	}
	
	public void setWhitelist(boolean whitelist) {
		this.whitelist = whitelist;
	}
	
	public boolean isWhitelist() {
		return this.whitelist;
	}
	
	public boolean isEmpty() {
		return this.contents.size() == 0;
	}
	
	private boolean match(ItemStack a, ItemStack b) {
		
		// No metadata nonsense for now.
		return a.getItem().equals(b.getItem()) && (this.ignoreMeta || a.getMetadata() == b.getMetadata());
	}
}
