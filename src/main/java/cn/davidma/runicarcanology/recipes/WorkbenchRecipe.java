package cn.davidma.runicarcanology.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class WorkbenchRecipe {

	private ItemStack output;
	private List<ItemStack> ingredients;
	
	public WorkbenchRecipe(ItemStack output, ItemStack... ingredients) {
		this.output = output;
		this.ingredients = new ArrayList<ItemStack>();
		for (ItemStack i: ingredients) {
			this.ingredients.add(i);
		}
	}
	
	public boolean match(List<ItemStack> stacks) {
		List<ItemStack> newStacks = new ArrayList<ItemStack>(this.ingredients);
		for (ItemStack i: stacks) {
			for (int j = 0; j < newStacks.size(); j++) {
				if (itemStackMatch(i, newStacks.get(j))) {
					newStacks.remove(j);
					break;
				}
			}
		}
		
		return newStacks.isEmpty();
	}
	
	public ItemStack getOutput() {
		return this.output;
	}
	
	private static boolean itemStackMatch(ItemStack a, ItemStack b) {
		if (a.getItem() != b.getItem()) return false;
		if (a.getItemDamage() != b.getItemDamage()) return false;
		return true;
	}
}
