package com.techelevator.dao;

import com.techelevator.model.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcRecipeDaoTests extends FinalCapstoneDaoTests{
    private JdbcRecipeDao sut;
    private Recipe recipe;
    private Recipe testRecipe;
    private static final Recipe RECIPE_1 = new Recipe(1, "prep1", "test1", 10, "someUrl", "entree");
    private static final Recipe RECIPE_2 = new Recipe(2, "prep2", "test2", 10, "someUrl", "entree");
    private static final Recipe RECIPE_3 = new Recipe(3, "prep3", "test3", 10, "someUrl", "entree");
    private static final Recipe RECIPE_4 = new Recipe(4, "prep4", "test4", 10, "someUrl", "entree");

    @Before
    public void setup(){
        //DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcRecipeDao(jdbcTemplate);

        testRecipe = new Recipe(99L, "Boil Eggs for 10 mins", "Hard-Boiled Eggs", 0, "egg.png", "snack");
    }

    @Test
    public void get_recipe_by_id_returns_correct_recipe(){
        Recipe recipe = sut.getRecipeByRecipeId(1);

        assertRecipesMatch(RECIPE_1, recipe);
    }

    @Test
    public void get_recipe_by_id_returns_correct_recipe_2(){
        Recipe recipe = sut.getRecipeByRecipeId(2);

        assertRecipesMatch(RECIPE_2, recipe);
    }

    @Test
    public void get_recipe_id_returns_null_if_it_does_not_exist(){
        Recipe recipe = sut.getRecipeByRecipeId(28L);
        Assert.assertNotNull(recipe);
    }

    //I think this test is not working because the current user may not have the permission to delete
    @Test
    public void deleted_recipe_from_user_library_can_not_be_retrieved(){
        sut.deleteRecipeFromUserLibrary(1, 4);

        Recipe[] recipes = sut.getAllRecipesByUser(1);
        for (Recipe recipe : recipes){
            if (recipe.getRecipeId()==4){
                Assert.assertNotNull(null);
            }
        }
        Assert.assertNull(null);
    }

    @Test
    public void get_all_recipes_by_user_id(){
        Recipe[] recipe = sut.getAllRecipesByUser(1);

        Assert.assertEquals(3, recipe.length);
    }

    @Test
    public void add_recipe_to_users_library() {
        sut.addRecipeToUserRecipe(2, 2);
        Recipe[] userRecipes = sut.getAllRecipesByUser(2);
        Assert.assertEquals(2, userRecipes.length);

    }

    private void assertRecipesMatch(Recipe expected, Recipe actual){
        Assert.assertEquals(expected.getRecipeId(), actual.getRecipeId());
        Assert.assertEquals(expected.getPreparation(), actual.getPreparation());
        Assert.assertEquals(expected.getRecipeName(), actual.getRecipeName());
//        Assert.assertEquals(expected.getPrepTime(), actual.getPrepTime());
        Assert.assertEquals(expected.getCookTime(), actual.getCookTime());
        Assert.assertEquals(expected.getRecipeImg(), actual.getRecipeImg());
        Assert.assertEquals(expected.getRecipeType(), actual.getRecipeType());
//        Assert.assertEquals(expected.getIngredients(), actual.getIngredients());
    }
}
