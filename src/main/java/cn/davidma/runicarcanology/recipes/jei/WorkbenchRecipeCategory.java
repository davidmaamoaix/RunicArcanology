package cn.davidma.runicarcanology.recipes.jei;

import cn.davidma.runicarcanology.reference.Info;
import cn.davidma.runicarcanology.registry.RABlocks;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class WorkbenchRecipeCategory implements IRecipeCategory {

	public static final String UID = Info.MOD_ID + ".arcane_workbench";
	
	private IDrawable background;
	private String name;
	
	public WorkbenchRecipeCategory(IGuiHelper guiHelper) {
		this.background = guiHelper.createBlankDrawable(150, 110);
		this.name = I18n.format("gui.arcane_workbench.key");
	}
	
	@Override
	public String getUid() {
		return UID;
	}

	@Override
	public String getTitle() {
		return this.name;
	}

	@Override
	public String getModName() {
		return Info.NAME;
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if (!(recipeWrapper instanceof WorkbenchRecipeWrapper)) return;
		
		WorkbenchRecipeWrapper wrapper = (WorkbenchRecipeWrapper) recipeWrapper;
		
		recipeLayout.getItemStacks().init(0, true, 64, 52);
		recipeLayout.getItemStacks().set(0, new ItemStack(RABlocks.getBlock("arcane_workbench")));
	}

}
