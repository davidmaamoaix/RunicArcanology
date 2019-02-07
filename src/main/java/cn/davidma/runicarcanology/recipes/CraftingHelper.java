package cn.davidma.runicarcanology.recipes;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.runicarcanology.registry.RABlocks;
import cn.davidma.runicarcanology.registry.RAItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingHelper {

	private static List<WorkbenchRecipe> workbenchRecipes;
	
	public static void init() {
		workbenchRecipes = new ArrayList<WorkbenchRecipe>();
		addRecipe(new ItemStack(RAItems.getItem("runic_plate"), 2), new ItemStack(Items.DIAMOND), new ItemStack(Items.QUARTZ, 4));
		addRecipe(new ItemStack(RAItems.getItem("rune_nature_gift")), new ItemStack(Blocks.BONE_BLOCK), new ItemStack(Blocks.SEA_LANTERN, 2), new ItemStack(Blocks.GLASS, 2), new ItemStack(RAItems.getItem("runic_plate")), new ItemStack(Items.WHEAT_SEEDS), new ItemStack(Items.POTATO), new ItemStack(Items.CARROT), new ItemStack(Items.SUGAR));
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
	
	public static List<WorkbenchRecipe> getWorkbenchRecipes() {
		return new ArrayList<WorkbenchRecipe>(workbenchRecipes);
	}
}
