package cn.davidma.runicarcanology.recipes.jei;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.runicarcanology.recipes.WorkbenchRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class WorkbenchRecipeWrapper implements IRecipeWrapper {

	private List<List<ItemStack>> ingredients;
	private ItemStack output;
	
	public WorkbenchRecipeWrapper(WorkbenchRecipe recipe) {
		this.ingredients = new ArrayList<List<ItemStack>>();
		for (ItemStack stack: recipe.getIngredients()) {
			List<ItemStack> temp = new ArrayList<ItemStack>();
			temp.add(stack);
			this.ingredients.add(temp);
		}
		this.output = recipe.getOutput();
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, this.ingredients);
		ingredients.setOutput(ItemStack.class, output);
	}
}
