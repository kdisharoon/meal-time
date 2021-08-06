package com.techelevator;

import com.techelevator.model.OrganizedRecipe;
import org.junit.Assert;
import org.junit.Test;

public class OrganizedRecipeTest {

    OrganizedRecipe organizedRecipe = new OrganizedRecipe();

    @Test
    public void organized_recipe_day_is_thursday(){
        organizedRecipe.setDay("thursday");
        Assert.assertEquals("thursday", organizedRecipe.getDay());
    }

    @Test
    public void organized_recipe_meal_is_breakfast(){
        organizedRecipe.setMeal("breakfast");
        Assert.assertEquals("breakfast", organizedRecipe.getMeal());
    }

}
