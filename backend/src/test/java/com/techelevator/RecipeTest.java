package com.techelevator;

import com.techelevator.model.Recipe;
import org.junit.Assert;
import org.junit.Test;

public class RecipeTest {

    Recipe recipe = new Recipe();

    @Test
    public void recipe_id_is_3(){
        recipe.setRecipeId(3L);
        Assert.assertEquals(3L, recipe.getRecipeId());
    }

    @Test
    public void preparation_is_a_string(){
        recipe.setPreparation("string");
        Assert.assertEquals("string", recipe.getPreparation());
    }

    @Test
    public void recipe_name_is_Jumbalaya(){
        recipe.setRecipeName("Jumbalaya");
        Assert.assertEquals("Jumbalaya", recipe.getRecipeName());
    }

//    @Test
//    public void prep_time_is_10_mins(){
//        recipe.setPrepTime(10);
//        Assert.assertEquals(10, recipe.getPrepTime());
//    }
    @Test
    public void cook_time_is_15_mins(){
        recipe.setCookTime(15);
        Assert.assertEquals(15, recipe.getCookTime());
    }

    @Test
    public void recipe_image_is_a_string(){
        recipe.setRecipeImg("string.png");
        Assert.assertEquals("string.png", recipe.getRecipeImg());
    }

    @Test
    public void recipe_type_returns_breakfast(){
        recipe.setRecipeType("breakfast");
        Assert.assertEquals("breakfast", recipe.getRecipeType());
    }

//    @Test
//    public void ingredients_returns_an_array_of_ingredients(){
//        recipe.setIngredients();
//    }
}
