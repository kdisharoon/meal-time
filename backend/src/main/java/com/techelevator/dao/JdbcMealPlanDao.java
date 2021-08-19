package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class JdbcMealPlanDao implements MealPlanDao{

    private final JdbcTemplate jdbcTemplate;



    public JdbcMealPlanDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }




    public MealPlan createMealPlan(MealPlan mealPlan){
        // query to check if there already exists a meal plan associated with the user
        String sql2 = "select * from user_meal_plan " +
                      "where user_id =? and meal_plan_name=?";
        SqlRowSet mealPlanResult = jdbcTemplate.queryForRowSet(sql2,mealPlan.getUserId(),mealPlan.getMealPlanName());
        if (mealPlanResult.next()) {
            throw new IllegalArgumentException();
        }

        // creating the meal plan
        String sql = "insert into user_meal_plan (user_id, meal_plan_name) " +
                     "values (?,?)";
        jdbcTemplate.update(sql, mealPlan.getUserId(), mealPlan.getMealPlanName());

        // getting the meal plan id to return to frontend in the meal plan object
        String sql3 = "select meal_plan_id from user_meal_plan where user_id=?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql3, mealPlan.getUserId());
        if (result.next()){
            mealPlan.setMealPlanId(result.getLong("meal_plan_id"));
        }
        return mealPlan;
    }

    public void addRecipeToUserMealPlan(long userId, long recipeId, OrganizedRecipe organizedRecipe){

        // getting meal plan id for function
        String sql2 = "select meal_plan_id from user_meal_plan where user_id =?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql2, userId);
        if (result.next()) {
            long mealPlanId = result.getLong("meal_plan_id");

            String sql3 = "select meal_plan_id from meal_plan_user_recipes " +
                          "where meal_plan_id=? and recipe_id=? and day=? and meal =?";
            SqlRowSet result2 = jdbcTemplate.queryForRowSet(sql3, mealPlanId, recipeId, organizedRecipe.getDay(), organizedRecipe.getMeal());
            if (result2.next()){
                throw new IllegalArgumentException();
            }

            // logging recipe to a meal plan at a specific day and meal
            String sql = "insert into meal_plan_user_recipes (meal_plan_id, recipe_id, day, meal) " +
                    "values (?,?,?,?)";
            jdbcTemplate.update(sql, mealPlanId, recipeId, organizedRecipe.getDay().toLowerCase(), organizedRecipe.getMeal().toLowerCase());


        } else {
            // if there was no row, no meal plan has been created yet, so an exception is thrown
            throw new IllegalStateException("You need to create a meal plan first!");
        }

    }

    /*public MealPlan[] getAllUserMealPlans(long userId){
        List<MealPlan> mealPlans = new ArrayList<>();

        String sql = "select meal_plan_id, meal_plan_name from user_meal_plan " +
                     "where user_id =?";
        SqlRowSet mealPlanResults = jdbcTemplate.queryForRowSet(sql, userId);

        while (mealPlanResults.next()) {
            MealPlan mealPlan = new MealPlan();
            mealPlan.setUserId(userId);
            mealPlan.setMealPlanId(mealPlanResults.getLong("meal_plan_id"));
            mealPlan.setMealPlanName(mealPlanResults.getString("meal_plan_name"));
            mealPlans.add(mealPlan);
        }
        return mealPlans.toArray(new MealPlan[0]);

    }*/

    public MealPlan getMealPlanByUser(long userId){
        //creating recipeList that will hold the recipe ids associated with a specific day and meal
        MealPlan mealPlan = new MealPlan();
        RecipeList[] recipeLists = createRecipeListArray();

        // getting meal plan id for function to work
        String sql3 = "select meal_plan_id, meal_plan_name from user_meal_plan " +
                     "where user_id =?";
        SqlRowSet mealPlanResults = jdbcTemplate.queryForRowSet(sql3, userId);


        if (mealPlanResults.next()) {
            //setting all primitive fields in the mealPlan

            long mealPlanId = mealPlanResults.getLong("meal_plan_id");
            String mealPlanName = mealPlanResults.getString("meal_plan_name");
            mealPlan.setUserId(userId);
            mealPlan.setMealPlanId(mealPlanId);
            mealPlan.setMealPlanName(mealPlanName);

            // getting all recipes to populate the RecipeList[]
            String sql2 = "select recipe_id, recipes.recipe_name, day, meal " +
                    "from meal_plan_user_recipes " +
                    "join recipes using (recipe_id) " +
                    "where meal_plan_id=?";
            SqlRowSet recipeIdResults = jdbcTemplate.queryForRowSet(sql2, mealPlanId);
            while (recipeIdResults.next()) {
                // adding to recipeList[] at the correct index based off of the day and meal
                String day = recipeIdResults.getString("day");
                String meal = recipeIdResults.getString("meal");
                String recipeName = recipeIdResults.getString("recipe_name");
                int index = mealPlan.findIndex(day, meal);
                recipeLists[index].addToList(recipeIdResults.getLong("recipe_id"));
                recipeLists[index].addToList(recipeName);

            }
            for (int i = 0; i < 21; i++) {
                // adding the now complete list at each index of the RecipeList[] to OrganizedRecipe[]
                // each index in recipeList is mapped to the same index in the organizedRecipe
                mealPlan.setOrganizedRecipeAtIndex(i, recipeLists[i].getArray());
                mealPlan.setOrganizedRecipeAtIndex(i, recipeLists[i].getStringArray());
            }
        }
        return mealPlan;

    }

    public void deleteRecipeFromMealPlan(long userId, long recipeId){


        // getting mealPlan id for function to work
        String sql2 = "select meal_plan_id from user_meal_plan where user_id =?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql2,userId);

        if (result.next()) {
            long mealPlanId = result.getLong("meal_plan_id");

            //deleting recipe where the recipe id and the mealPlanId are in the same row
            String sql = "delete from meal_plan_user_recipes " +
                    "where meal_plan_id=? and recipe_id=?";
            jdbcTemplate.update(sql, mealPlanId, recipeId);
        }

    }

    public void deleteMealPlan(long userId){

        // getting mealPlan id for function to work
        String sql2 = "select meal_plan_id from user_meal_plan where user_id =?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql2,userId);

        if (result.next()) {
            long mealPlanId = result.getLong("meal_plan_id");
            String sql = "delete from meal_plan_user_recipes " +
                    "where meal_plan_id = ?";
            jdbcTemplate.update(sql,mealPlanId);

        }

    }



    public Ingredient[] groceryList(long userId){

        // creating lists, ingredients will have everything not ordered by ingredient
        // orderedIngredients will contain all ingredients of a shared name together
        List<Ingredient> ingredients = new ArrayList<>();
        List<Ingredient> orderedIngredients = new ArrayList<>();

        // getting mealPlanId for function to work
        String sql2 = "select meal_plan_id from user_meal_plan where user_id =?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql2,userId);

        if (result.next()) {
            long mealPlanId = result.getLong("meal_plan_id");

            // gets all recipes in the meal plan
            String sql = "select recipe_id from meal_plan_user_recipes " +
                    "where meal_plan_id=?";
            SqlRowSet recipeIdResults = jdbcTemplate.queryForRowSet(sql, mealPlanId);

            // for each recipe add all of the ingredients in the recipe to the List - ingredients
            while (recipeIdResults.next()) {
                ingredients = addToListOfIngredients((recipeIdResults.getLong("recipe_id")), ingredients);
            }
            // create a set to hold each string to know how many different names of ingredients there are
            Set<String> names = new HashSet<>();
            for (Ingredient ingredient : ingredients) {
                names.add(ingredient.getIngredientName());
            }

            // for each name, loop through all of the ingredients and add the same names together in the orderedIngredients
            for (String name : names) {

                for (Ingredient ingredient : ingredients) {
                    if (ingredient.getIngredientName().equals(name)) {
                        orderedIngredients.add(ingredient);
                    }
                }
            }
        }

        // return orderedIngredients as an array
        return orderedIngredients.toArray(new Ingredient[0]);

    }

    public void updateMealPlanName(long userId, String mealPlanName){
        String sql = "update user_meal_plan set meal_plan_name = ? " +
                     "where user_id = ?";

        jdbcTemplate.update(sql, mealPlanName, userId);
    }

    private List<Ingredient> addToListOfIngredients(long recipeId, List<Ingredient> ingredients) {



        String sql = "select recipe_id from recipes " +
                "where recipe_id=?";
        String sql2 = "select ingredient_id, measurement_amount, measurement_unit, ingredients.ingredient_name " +
                "from recipe_ingredients " +
                "join ingredients using (ingredient_id) " +
                "where recipe_id=?";
        // checking that the recipe_id exists
        SqlRowSet recipeResults = jdbcTemplate.queryForRowSet(sql, recipeId);
        if (recipeResults.next()){
            SqlRowSet ingredientResults = jdbcTemplate.queryForRowSet(sql2, recipeId);
            // add all of the ingredients in the recipe to the List<Ingredient>
            ingredients= mapRowsToIngredients(ingredientResults, ingredients);

        }
        return ingredients;
    }

    public RecipeList[] createRecipeListArray(){
        RecipeList[] recipeList = new RecipeList[21];
        for (int i = 0; i< 21; i++){
            recipeList[i] = new RecipeList();
        }
        return recipeList;
    }



    public List<Ingredient> mapRowsToIngredients(SqlRowSet results, List<Ingredient> ingredients){
        while (results.next()){
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientId(results.getLong("ingredient_id"));
            ingredient.setIngredientName(results.getString("ingredient_name"));
            ingredient.setMeasurementAmount(results.getDouble("measurement_amount"));
            ingredient.setMeasurementUnit(results.getString("measurement_unit"));
            ingredients.add(ingredient);
        }
        return ingredients;
    }


}



