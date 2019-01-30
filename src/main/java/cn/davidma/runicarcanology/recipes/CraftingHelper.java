package cn.davidma.runicarcanology.recipes;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.runicarcanology.registry.RAItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingHelper {

	private static List<WorkbenchRecipe> workbenchRecipes;
	
	public static void init() {
		workbenchRecipes = new ArrayList<WorkbenchRecipe>();
		addRecipe(new ItemStack(RAItems.getItem("runic_plate"), 2), new ItemStack(Items.DIAMOND), new ItemStack(Items.QUARTZ, 4));
	}
	
	public static ItemStack getCraftingResult(List<ItemStack> ingredients) {
		for (WorkbenchRecipe i: workbenchRecipes) {
			if (i.match(ingredients)) {
				return i.getOutput();
			}
		}
		return ItemStack.EMPTY;
	}
	
	private static void addRecipe(ItemStack output, ItemStack... ingredients) {
		WorkbenchRecipe recipe = new WorkbenchRecipe(output, ingredients);
		workbenchRecipes.add(recipe);
	}
}
