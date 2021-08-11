package com.techelevator.model;

public class OrganizedRecipe {

    private String day;
    private String meal;
    private long[] recipeIds;
    private String[] recipeNames;

    public String[] getRecipeNames() {
        return recipeNames;
    }

    public void setRecipeNames(String[] recipeNames) {
        this.recipeNames = recipeNames;
    }

    public OrganizedRecipe(){}

    public OrganizedRecipe(String day, String meal){

        this.day=day;
        this.meal=meal;



    }

    public long[] getRecipeIds() {
        return recipeIds;
    }

    public void setRecipeIds(long[] recipeIds) {
        this.recipeIds = recipeIds;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
