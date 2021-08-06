package com.techelevator.dao;

import com.techelevator.model.Recipe;

import java.util.List;

public interface RecipeDao {

    Recipe[] getAllRecipesByUser(long userId);

    Recipe[] getAllRecipes();

    Recipe getRecipeByRecipeId(long recipeId);

    void addRecipeToUserRecipe(long userId, long recipeId);

    void deleteRecipeFromUserLibrary(long userId, long recipeId);

    void addRecipeToRecipeLibrary(Recipe[] recipe);
}
