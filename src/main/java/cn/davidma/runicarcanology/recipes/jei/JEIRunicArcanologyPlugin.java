package cn.davidma.runicarcanology.recipes.jei;

import cn.davidma.runicarcanology.recipes.CraftingHelper;
import cn.davidma.runicarcanology.recipes.WorkbenchRecipe;
import cn.davidma.runicarcanology.registry.RABlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIRunicArcanologyPlugin implements IModPlugin {

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new WorkbenchRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}
	
	@Override
	public void register(IModRegistry registry) {
		registry.handleRecipes(WorkbenchRecipe.class, WorkbenchRecipeWrapper::new, WorkbenchRecipeCategory.UID);
		
		registry.addRecipes(CraftingHelper.getWorkbenchRecipes(), WorkbenchRecipeCategory.UID);
		
		registry.addRecipeCatalyst(new ItemStack(RABlocks.getBlock("arcane_workbench")), WorkbenchRecipeCategory.UID);
	}
}
