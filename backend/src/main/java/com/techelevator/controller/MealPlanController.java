package com.techelevator.controller;


import com.techelevator.dao.MealPlanDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class MealPlanController {

    private final MealPlanDao mealPlanDao;

    public MealPlanController(MealPlanDao mealplanDao){
        this.mealPlanDao=mealplanDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/users/mealplans", method = RequestMethod.POST)
    public MealPlan addMealPlanToUser(@RequestBody MealPlan mealPlan){

        try{
            return mealPlanDao.createMealPlan(mealPlan);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/users/{userId}/mealplans/recipes/{recipeId}", method = RequestMethod.POST)
    public void addRecipeToUserMealPlan(@PathVariable long userId, @PathVariable long recipeId, @RequestBody OrganizedRecipe organizedRecipe){
        mealPlanDao.addRecipeToUserMealPlan(userId, recipeId, organizedRecipe);

    }

    /*@ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/users/{userId}/mealplans", method = RequestMethod.GET)
    public MealPlan[] getAllMealPlansByUser(@PathVariable long userId){
        return mealPlanDao.getAllUserMealPlans(userId);
    }*/

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/users/{userId}/mealplans", method = RequestMethod.GET)
    public MealPlan getMealPlanByUser(@PathVariable long userId){
        return mealPlanDao.getMealPlanByUser(userId);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path= "/users/{userId}/mealplans/recipes/{recipeId}", method = RequestMethod.DELETE)
    public void deleteRecipeFromMealPlan(@PathVariable long userId, @PathVariable long recipeId){
        mealPlanDao.deleteRecipeFromMealPlan(userId,recipeId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/users/{userId}/grocerylist", method = RequestMethod.GET)
    public Ingredient[] groceryList(@PathVariable long userId){
        return mealPlanDao.groceryList(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/users/{userId}/mealplans", method = RequestMethod.PUT)
    public void updateMealPlanName(@PathVariable long userId, @RequestParam String mealPlanName){
        mealPlanDao.updateMealPlanName(userId, mealPlanName);

    }






}
