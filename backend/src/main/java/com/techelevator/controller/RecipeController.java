package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.model.Recipe;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class RecipeController {

    private RecipeDao recipeDao;

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



    /*
    getRecipeByRecipeId()   /recipes/{recipeId}
    getAllRecipes()         /recipes
     */
}
