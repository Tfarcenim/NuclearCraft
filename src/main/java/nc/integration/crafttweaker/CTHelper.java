package nc.integration.crafttweaker;

import java.util.ArrayList;
import java.util.List;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IngredientOr;
import crafttweaker.api.item.IngredientStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.oredict.IOreDictEntry;
import nc.recipe.NCRecipes;
import nc.recipe.RecipeHelper;
import nc.recipe.ingredient.EmptyFluidIngredient;
import nc.recipe.ingredient.EmptyItemIngredient;
import nc.recipe.ingredient.FluidIngredient;
import nc.recipe.ingredient.IFluidIngredient;
import nc.recipe.ingredient.IItemIngredient;
import nc.recipe.ingredient.OreIngredient;
import nc.util.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class CTHelper {
	
	public static ItemStack getItemStack(IItemStack item) {
		if(item == null) return ItemStack.EMPTY;
		
		Object internal = item.getInternal();
		if (!(internal instanceof ItemStack)) {
			CraftTweakerAPI.logError("Not a valid item stack: " + item);
		}
		return ((ItemStack) internal).copy();
	}
	
	public static FluidStack getFluidStack(ILiquidStack stack) {
		if(stack == null) return null;
		return (FluidStack) stack.getInternal();
	}
	
	public static IItemIngredient buildAdditionItemIngredient(Object ingredient, NCRecipes.Type recipeType) {
		if (ingredient == null) {
			return new EmptyItemIngredient();
		} else if (ingredient instanceof IItemStack) {
			return RecipeHelper.buildItemIngredient(getItemStack((IItemStack) ingredient));
		} else if (ingredient instanceof IOreDictEntry) {
			return new OreIngredient(((IOreDictEntry) ingredient).getName(), ((IOreDictEntry) ingredient).getAmount());
		} else if (ingredient instanceof IngredientStack) {
			return buildOreIngredientArray((IngredientStack) ingredient, true);
		} else if (ingredient instanceof IngredientOr) {
			return buildItemIngredientArray((IngredientOr) ingredient);
		} else {
			CraftTweakerAPI.logError(String.format("%s: Invalid ingredient: %s, %s", recipeType.getRecipeName(), ingredient.getClass().getName(), ingredient));
			return null;
		}
	}
	
	public static IFluidIngredient buildAdditionFluidIngredient(Object ingredient, NCRecipes.Type recipeType) {
		if (ingredient == null) {
			return new EmptyFluidIngredient();
		} else if (ingredient instanceof ILiquidStack) {
			return RecipeHelper.buildFluidIngredient(getFluidStack((ILiquidStack) ingredient));
		} else if (ingredient instanceof IngredientOr) {
			return buildFluidIngredientArray((IngredientOr) ingredient);
		} else {
			CraftTweakerAPI.logError(String.format("%s: Invalid ingredient: %s, %s", recipeType.getRecipeName(), ingredient.getClass().getName(), ingredient));
			return null;
		}
	}
	
	public static IItemIngredient buildRemovalItemIngredient(IIngredient ingredient, NCRecipes.Type recipeType) {
		if (ingredient == null) {
			return new EmptyItemIngredient();
		} else if (ingredient instanceof IItemStack) {
			return RecipeHelper.buildItemIngredient(CTHelper.getItemStack((IItemStack) ingredient));
		} else if (ingredient instanceof IOreDictEntry) {
			return new OreIngredient(((IOreDictEntry) ingredient).getName(), ((IOreDictEntry) ingredient).getAmount());
		} else if (ingredient instanceof IngredientStack) {
			return buildOreIngredientArray((IngredientStack) ingredient, false);
		} else if (ingredient instanceof IngredientOr) {
			return buildItemIngredientArray(ingredient);
		} else {
			CraftTweakerAPI.logError(String.format("%s: Invalid ingredient: %s, %s", recipeType.getRecipeName(), ingredient.getClass().getName(), ingredient));
			return null;
		}
	}
	
	public static IFluidIngredient buildRemovalFluidIngredient(IIngredient ingredient, NCRecipes.Type recipeType) {
		if (ingredient == null) {
			return new EmptyFluidIngredient();
		} else if (ingredient instanceof ILiquidStack) {
			return new FluidIngredient(((ILiquidStack) ingredient).getName(), ((ILiquidStack) ingredient).getAmount());
		} else if (ingredient instanceof IngredientOr) {
			return buildFluidIngredientArray((IngredientOr) ingredient);
		} else {
			CraftTweakerAPI.logError(String.format("%s: Invalid ingredient: %s, %s", recipeType.getRecipeName(), ingredient.getClass().getName(), ingredient));
			return null;
		}
	}
	
	// Array Ingredients
	
	public static IItemIngredient buildItemIngredientArray(IIngredient ingredient) {
		List<ItemStack> stackList = new ArrayList<ItemStack>();
		ingredient.getItems().forEach(item -> stackList.add(getItemStack(item)));
		return RecipeHelper.buildItemIngredient(stackList);
	}
	
	public static IItemIngredient buildOreIngredientArray(IngredientStack stack, boolean addition) {
		List<ItemStack> stackList = new ArrayList<ItemStack>();
		stack.getItems().forEach(item -> stackList.add(ItemStackHelper.changeStackSize(getItemStack(item), stack.getAmount())));
		if (addition) {
			OreIngredient oreStack = RecipeHelper.getOreStackFromItems(stackList, stack.getAmount());
			if (oreStack != null) return oreStack;
		}
		return RecipeHelper.buildItemIngredient(stackList);
	}
	
	public static IFluidIngredient buildFluidIngredientArray(IngredientOr ingredient) {
		List<FluidStack> stackList = new ArrayList<FluidStack>();
		ingredient.getLiquids().forEach(fluid -> stackList.add(getFluidStack(fluid)));
		return RecipeHelper.buildFluidIngredient(stackList);
	}
}
