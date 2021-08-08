package com.techelevator.model;

public class MealPlan {

    private long mealPlanId;
    private long userId;
    private String mealPlanName;
    private OrganizedRecipe[] organizedRecipes= buildOrganizedRecipeArray();;

    public MealPlan(){

    }

    public MealPlan(long mealPlanId, long userId, String mealPlanName){
        this.mealPlanId=mealPlanId;
        this.userId=userId;
        this.mealPlanName=mealPlanName;


    }

    public OrganizedRecipe[] getRecipes() {
        return organizedRecipes;
    }

    public void setOrganizedRecipes(OrganizedRecipe[] organizedRecipes) {
        this.organizedRecipes = organizedRecipes;
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

    public int findIndex(String day, String meal){
        for (int i = 0; i< organizedRecipes.length; i++){
            if (organizedRecipes[i].getDay().equals(day) && organizedRecipes[i].getMeal().equals(meal))
                return i;
        }
        //will never occur because the day and meal will match with one of the indexes
        return 0;
    }

    public void setOrganizedRecipeAtIndex(int index, long[] ids){
        organizedRecipes[index].setRecipeIds(ids);
    }

    public OrganizedRecipe[] buildOrganizedRecipeArray(){
        OrganizedRecipe[] organizedRecipes1 = new OrganizedRecipe[21];


        organizedRecipes1[0]= new OrganizedRecipe();
        organizedRecipes1[0].setDay("sunday");
        organizedRecipes1[0].setMeal("breakfast");

        organizedRecipes1[1]= new OrganizedRecipe();
        organizedRecipes1[1].setDay("sunday");
        organizedRecipes1[1].setMeal("lunch");

        organizedRecipes1[2]= new OrganizedRecipe();
        organizedRecipes1[2].setDay("sunday");
        organizedRecipes1[2].setMeal("dinner");

        organizedRecipes1[3]= new OrganizedRecipe();
        organizedRecipes1[3].setDay("monday");
        organizedRecipes1[3].setMeal("breakfast");

        organizedRecipes1[4]= new OrganizedRecipe();
        organizedRecipes1[4].setDay("monday");
        organizedRecipes1[4].setMeal("lunch");

        organizedRecipes1[5]= new OrganizedRecipe();
        organizedRecipes1[5].setDay("monday");
        organizedRecipes1[5].setMeal("dinner");

        organizedRecipes1[6]= new OrganizedRecipe();
        organizedRecipes1[6].setDay("tuesday");
        organizedRecipes1[6].setMeal("breakfast");

        organizedRecipes1[7]= new OrganizedRecipe();
        organizedRecipes1[7].setDay("tuesday");
        organizedRecipes1[7].setMeal("lunch");

        organizedRecipes1[8]= new OrganizedRecipe();
        organizedRecipes1[8].setDay("tuesday");
        organizedRecipes1[8].setMeal("dinner");

        organizedRecipes1[9]= new OrganizedRecipe();
        organizedRecipes1[9].setDay("wednesday");
        organizedRecipes1[9].setMeal("breakfast");

        organizedRecipes1[10]= new OrganizedRecipe();
        organizedRecipes1[10].setDay("wednesday");
        organizedRecipes1[10].setMeal("lunch");

        organizedRecipes1[11]= new OrganizedRecipe();
        organizedRecipes1[11].setDay("wednesday");
        organizedRecipes1[11].setMeal("dinner");

        organizedRecipes1[12]= new OrganizedRecipe();
        organizedRecipes1[12].setDay("thursday");
        organizedRecipes1[12].setMeal("breakfast");

        organizedRecipes1[13]= new OrganizedRecipe();
        organizedRecipes1[13].setDay("thursday");
        organizedRecipes1[13].setMeal("lunch");

        organizedRecipes1[14]= new OrganizedRecipe();
        organizedRecipes1[14].setDay("thursday");
        organizedRecipes1[14].setMeal("dinner");

        organizedRecipes1[15]= new OrganizedRecipe();
        organizedRecipes1[15].setDay("friday");
        organizedRecipes1[15].setMeal("breakfast");

        organizedRecipes1[16]= new OrganizedRecipe();
        organizedRecipes1[16].setDay("friday");
        organizedRecipes1[16].setMeal("lunch");

        organizedRecipes1[17]= new OrganizedRecipe();
        organizedRecipes1[17].setDay("friday");
        organizedRecipes1[17].setMeal("dinner");

        organizedRecipes1[18]= new OrganizedRecipe();
        organizedRecipes1[18].setDay("saturday");
        organizedRecipes1[18].setMeal("breakfast");

        organizedRecipes1[19]= new OrganizedRecipe();
        organizedRecipes1[19].setDay("saturday");
        organizedRecipes1[19].setMeal("lunch");

        organizedRecipes1[20]= new OrganizedRecipe();
        organizedRecipes1[20].setDay("saturday");
        organizedRecipes1[20].setMeal("dinner");

        return organizedRecipes1;


    }
}
