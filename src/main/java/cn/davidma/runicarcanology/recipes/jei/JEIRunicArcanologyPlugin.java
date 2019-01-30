package cn.davidma.runicarcanology.recipes.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@JEIPlugin
public class JEIRunicArcanologyPlugin implements IModPlugin {

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new WorkbenchRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}
	
	@Override
	public void register(IModRegistry registry) {
		System.out.println(123);
	}
}
