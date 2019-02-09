package cn.davidma.runicarcanology.util.inventory;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.runicarcanology.util.NBTHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemFilterHelper {
	
	private List<ItemStack> contents;
	private boolean whitelist;
	private boolean ignoreMeta;
	
	public ItemFilterHelper() {
		this.contents = new ArrayList<ItemStack>();
		this.whitelist = false;
		this.ignoreMeta = false;
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
	
	public boolean ignoreMeta() {
		return this.ignoreMeta;
	}
	
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	public int size() {
		return this.contents.size();
	}
	
	private boolean match(ItemStack a, ItemStack b) {
		
		// No metadata nonsense for now.
		return a.getItem().equals(b.getItem()) && (this.ignoreMeta || a.getMetadata() == b.getMetadata());
	}
	
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean(NBTHelper.WHITELIST, this.whitelist);
		nbt.setBoolean(NBTHelper.IGNORE_META, this.ignoreMeta);
		
		NBTTagList stacks = new NBTTagList();
		for (ItemStack i: this.contents) {
			stacks.appendTag(i.serializeNBT());
		}
		nbt.setTag(NBTHelper.STACK_LIST, stacks);
		
		return nbt;
	}
	
	public void clearFilter() {
		this.contents = new ArrayList<ItemStack>();
	}
	
	public static ItemFilterHelper filterFromNBT(NBTTagCompound nbt) {
		ItemFilterHelper filter = new ItemFilterHelper();
		
		filter.whitelist = nbt.getBoolean(NBTHelper.WHITELIST);
		filter.ignoreMeta = nbt.getBoolean(NBTHelper.IGNORE_META);
		
		NBTBase base = nbt.getTag(NBTHelper.STACK_LIST);
		if (base == null || base.hasNoTags() || !(base instanceof NBTTagList)) return filter;
		NBTTagList list = (NBTTagList) base;
		for (NBTBase i: list) {
			if (!(i instanceof NBTTagCompound)) continue;
			ItemStack stack = ItemStack.EMPTY;
			stack.deserializeNBT((NBTTagCompound) i);
			filter.contents.add(stack);
		}
		
		return filter;
	}
	
	public static void setFilter(ItemStack stack, ItemFilterHelper itemFilter) {
		NBTTagCompound nbt = NBTHelper.getEssentialNBT(stack);
		nbt.setTag(NBTHelper.FILTER, itemFilter.serializeNBT());
		NBTHelper.setEssentialNBT(stack, nbt);
	}
	
	public static ItemFilterHelper filterFromStack(ItemStack stack) {
		NBTTagCompound nbt = NBTHelper.getEssentialNBT(stack).getCompoundTag(NBTHelper.FILTER);
		if (nbt == null) return new ItemFilterHelper();
		return filterFromNBT(nbt);
	}
}
