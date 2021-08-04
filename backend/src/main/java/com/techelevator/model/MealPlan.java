package com.techelevator.model;

public class MealPlan {

    private long mealPlanId;
    private long userId;
    private String mealPlanName;
    private Recipe[] recipes;

    public MealPlan(){}

    public MealPlan(long mealPlanId, long userId, String mealPlanName, Recipe[] recipes){
        this.mealPlanId=mealPlanId;
        this.userId=userId;
        this.mealPlanName=mealPlanName;
        this.recipes=recipes;

    }

    public Recipe[] getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipe[] recipes) {
        this.recipes = recipes;
    }

    public String getMealPlanName() {
        return mealPlanName;
    }

    public void setMealPlanName(String mealPlanName) {
        this.mealPlanName = mealPlanName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(long mealPlanId) {
        this.mealPlanId = mealPlanId;
    }
}
