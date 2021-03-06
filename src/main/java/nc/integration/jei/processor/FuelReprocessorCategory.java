package nc.integration.jei.processor;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import nc.integration.jei.IJEIHandler;
import nc.integration.jei.JEICategoryProcessor;
import nc.integration.jei.JEIMethods.RecipeItemMapper;
import nc.integration.jei.JEIRecipeWrapper;
import nc.recipe.IngredientSorption;

public class FuelReprocessorCategory extends JEICategoryProcessor<JEIRecipeWrapper.FuelReprocessor> {
	
	public FuelReprocessorCategory(IGuiHelper guiHelper, IJEIHandler handler) {
		super(guiHelper, handler, "fuel_reprocessor_idle", 49, 30, 94, 38);
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, JEIRecipeWrapper.FuelReprocessor recipeWrapper, IIngredients ingredients) {
		super.setRecipe(recipeLayout, recipeWrapper, ingredients);
		
		RecipeItemMapper itemMapper = new RecipeItemMapper();
		itemMapper.map(IngredientSorption.INPUT, 0, 0, 50 - backPosX, 41 - backPosY);
		itemMapper.map(IngredientSorption.OUTPUT, 0, 1, 106 - backPosX, 31 - backPosY);
		itemMapper.map(IngredientSorption.OUTPUT, 1, 2, 126 - backPosX, 31 - backPosY);
		itemMapper.map(IngredientSorption.OUTPUT, 2, 3, 106 - backPosX, 51 - backPosY);
		itemMapper.map(IngredientSorption.OUTPUT, 3, 4, 126 - backPosX, 51 - backPosY);
		itemMapper.mapItemsTo(recipeLayout.getItemStacks(), ingredients);
	}
}
