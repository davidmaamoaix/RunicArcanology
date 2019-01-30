package cn.davidma.runicarcanology.recipes.jei;

import java.util.List;

import cn.davidma.runicarcanology.reference.Info;
import cn.davidma.runicarcanology.registry.RABlocks;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class WorkbenchRecipeCategory implements IRecipeCategory {

	public static final String UID = Info.MOD_ID + ".arcane_workbench";
	
	private static final ResourceLocation location = new ResourceLocation(Info.MOD_ID, "textures/gui/workbench_jei.png");
	
	private IDrawable background;
	private String name;
	
	public WorkbenchRecipeCategory(IGuiHelper guiHelper) {
		this.background = guiHelper.createDrawable(location, 0, 0, 150, 110);
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
		
		double degree = 0;
		List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
		int amount = inputs.size();
		int radius = 39;
		for (int i = 0; i < amount; i++) {
			int x = (int) (Math.sin(Math.toRadians(degree)) * radius + 66);
			int y = (int) (Math.cos(Math.toRadians(degree)) * radius + 45);
			recipeLayout.getItemStacks().init(i, true, x, y);
			recipeLayout.getItemStacks().set(i, inputs.get(i));
			degree += 360 / amount;
		}
		
		recipeLayout.getItemStacks().init(amount, true, 66, 46);
		recipeLayout.getItemStacks().set(amount, ingredients.getOutputs(ItemStack.class).get(0));
	}

}
