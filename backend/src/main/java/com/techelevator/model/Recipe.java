package com.techelevator.model;

import java.util.Map;

public class Recipe {

  /*  Recipe id(Serial) PK
    Preparation(String)
    Meal_plan_Name(string)
    Prep time(int)
    Cook time(int)
    Recipe image url (tentative)
    type*/

   private long recipeId;
   private String preparation;
   private String recipeName;
   private int prepTime;
   private int cookTime;
   private String recipeImg;
   private String recipeType;
   private Ingredient[] ingredients;

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public Recipe(){

   }

   public Recipe(long recipeId, String preparation, String recipeName, int prepTime, int cookTime, String recipeImg, String recipeType){
       this.recipeId=recipeId;
       this.preparation=preparation;
       this.recipeName=recipeName;
       this.prepTime=prepTime;
       this.cookTime=cookTime;
       this.recipeImg=recipeImg;
       this.recipeType=recipeType;
   }

    public String getRecipeImg() {
        return recipeImg;
    }

    public void setRecipeImg(String recipeImg) {
        this.recipeImg = recipeImg;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName= recipeName;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
}
