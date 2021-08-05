package com.techelevator.model;

public class OrganizedRecipe {

    private String day;
    private String meal;
    private long[] recipeIds;

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
