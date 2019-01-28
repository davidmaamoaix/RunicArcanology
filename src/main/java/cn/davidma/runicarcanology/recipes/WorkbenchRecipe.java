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
			ItemStack newStack = i.copy();
			newStack.setCount(1);
			for (int j = 0; j < i.getCount(); j++) {
				this.ingredients.add(newStack);
			}
		}
	}
	
	public boolean match(List<ItemStack> stacks) {
		List<ItemStack> newIngredients = new ArrayList<ItemStack>(this.ingredients);
		List<ItemStack> newStacks = new ArrayList<ItemStack>(stacks);
		for (int i = 0; i < newStacks.size(); i++) {
			for (int j = 0; j < newIngredients.size(); j++) {
				if (itemStackMatch(newStacks.get(i), newIngredients.get(j))) {
					newIngredients.remove(j);
					newStacks.remove(i);
					i--;
					break;
				}
			}
		}
		
		return newIngredients.isEmpty() && newStacks.isEmpty();
	}
	
	public ItemStack getOutput() {
		return this.output;
	}
	
	private static boolean itemStackMatch(ItemStack a, ItemStack b) {
		if (a.getItem() != b.getItem()) return false;
		if (a.getCount() != b.getCount()) return false;
		if (a.getItemDamage() != b.getItemDamage()) return false;
		return true;
	}
}
