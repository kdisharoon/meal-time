package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.MealPlan;
import com.techelevator.model.OrganizedRecipe;
import org.springframework.web.bind.annotation.PathVariable;

public interface MealPlanDao {

    long createMealPlan(MealPlan mealPlan);

    void addRecipeToUserMealPlan(long mealPlanId, long recipeId, OrganizedRecipe organizedRecipe);

    MealPlan[] getAllUserMealPlans(long userId);

    MealPlan getMealPlanByUser(long mealPlanId);

    void deleteRecipeFromMealPlan(long mealPlanId, long recipeId);

    Ingredient[] groceryList(long mealPlanId);

}
