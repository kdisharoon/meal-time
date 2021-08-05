package com.techelevator.dao;

import com.techelevator.model.MealPlan;
import com.techelevator.model.OrganizedRecipe;
import org.springframework.web.bind.annotation.PathVariable;

public interface MealPlanDao {

    void createMealPlan(MealPlan mealPlan);

    void addRecipeToUserMealPlan(long mealPlanId, long recipeId, OrganizedRecipe organizedRecipe);

    MealPlan[] getAllUserMealPlans(long userId);

    MealPlan getMealPlanByUser(long mealPlanId);

}
