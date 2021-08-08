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




    public long createMealPlan(MealPlan mealPlan){
        String sql2 = "select * from user_meal_plan " +
                      "where user_id =? and meal_plan_name=?";
        SqlRowSet mealPlanResult = jdbcTemplate.queryForRowSet(sql2,mealPlan.getUserId(),mealPlan.getMealPlanName());
        if (mealPlanResult.next()) {
            throw new IllegalArgumentException();
        }


        String sql = "insert into user_meal_plan (user_id, meal_plan_name) " +
                     "values (?,?)";
        jdbcTemplate.update(sql, mealPlan.getUserId(), mealPlan.getMealPlanName());

        String sql3 = "select meal_plan_id from user_meal_plan where meal_plan_name=?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql3,mealPlan.getMealPlanName());
        long id= 0;
        if (result.next()){
            id = result.getLong("meal_plan_id");
        }
        return id;
 //       Long newId = jdbcTemplate.queryForObject(sql, Long.class, mealPlan.getUserId(), mealPlan.getMealPlanName());
    }

    public void addRecipeToUserMealPlan(long mealPlanId, long recipeId, OrganizedRecipe organizedRecipe){
        String sql = "insert into meal_plan_user_recipes (meal_plan_id, recipe_id, day, meal) " +
                     "values (?,?,?,?)";
        jdbcTemplate.update(sql, mealPlanId,recipeId,organizedRecipe.getDay().toLowerCase(),organizedRecipe.getMeal().toLowerCase());

        //can you add multiple of the same recipe to the mealplan?

    }

    public MealPlan[] getAllUserMealPlans(long userId){
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

    }

    public MealPlan getMealPlanByUser(long mealPlanId){
        MealPlan mealPlan = new MealPlan();
        RecipeList[] recipeLists = createRecipeListArray();

        String sql = "select meal_plan_name, user_id " +
                     "from user_meal_plan " +
                     "where meal_plan_id = ?";

        SqlRowSet mealPlanNameResult= jdbcTemplate.queryForRowSet(sql,mealPlanId);
        if (mealPlanNameResult.next()){
            mealPlan.setMealPlanName(mealPlanNameResult.getString("meal_plan_name"));
            mealPlan.setMealPlanId(mealPlanId);
            mealPlan.setUserId(mealPlanNameResult.getLong("user_id"));
        }

        String sql2 = "select recipe_id, day, meal " +
                      "from meal_plan_user_recipes " +
                      "where meal_plan_id=?";
        SqlRowSet recipeIdResults = jdbcTemplate.queryForRowSet(sql2, mealPlanId);
        while(recipeIdResults.next()){
            String day = recipeIdResults.getString("day");
            String meal = recipeIdResults.getString("meal");
            int index = mealPlan.findIndex(day, meal);
            recipeLists[index].addToList(recipeIdResults.getLong("recipe_id"));

        }
        for (int i = 0; i< 21; i++){
            mealPlan.setOrganizedRecipeAtIndex(i, recipeLists[i].getArray());
        }

        return mealPlan;
    }

    public void deleteRecipeFromMealPlan(long mealPlanId, long recipeId){

        String sql = "delete from ";

    }

    public RecipeList[] createRecipeListArray(){
        RecipeList[] recipeList = new RecipeList[21];
        for (int i = 0; i< 21; i++){
            recipeList[i] = new RecipeList();
        }
        return recipeList;
    }


}



