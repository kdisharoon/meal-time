package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.model.Recipe;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class RecipeController {

    private final RecipeDao recipeDao;


    public RecipeController(RecipeDao recipeDao){
        this.recipeDao = recipeDao;

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/users/{userId}/recipes" , method = RequestMethod.GET)
    public Recipe[] getAllRecipesByUserId(@PathVariable long userId){
        return recipeDao.getAllRecipesByUser(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/recipes", method = RequestMethod.GET)
    public Recipe[] getAllRecipes(){
        return recipeDao.getAllRecipes();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/recipes/{recipeId}", method = RequestMethod.GET)
    public Recipe getRecipeByRecipeId(@PathVariable long recipeId){
        return recipeDao.getRecipeByRecipeId(recipeId);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/users/{userId}/recipes/{recipeId}", method = RequestMethod.POST)
    public void AddRecipeToUserLibrary(@PathVariable long userId,@PathVariable long recipeId){
        try {
            recipeDao.addRecipeToUserRecipe(userId, recipeId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path= "/users/{userId}/recipes/{recipeId}", method = RequestMethod.DELETE)
    public void deleteRecipeFromLibrary(@PathVariable long userId,@PathVariable long recipeId){
        recipeDao.deleteRecipeFromUserLibrary(userId, recipeId);

        //when delete recipe from meal plan is added, call it here as well
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/recipes", method = RequestMethod.POST)
    public void addRecipeToRecipeLibrary(@RequestBody Recipe recipe){
        recipeDao.addRecipeToRecipeLibrary(recipe);
    }








    /*
    getRecipeByRecipeId()   /recipes/{recipeId}
    getAllRecipes()         /recipes

    add a Recipe            /users/{userId}/recipe/{recipeId}
    addIngredient
    addMealPlan         /users/{userId}/mealPlan
     */
}
