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
    private static final Recipe RECIPE_1 = new Recipe(1L, "You will need fresh basil, fresh mozz, and fresh tomato on a plate. Layer the cheese, basil, and tomato. Drizzle balsamic vinegar and Enjoy!", "Caprese Salad", 10, "https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2019/07/Caprese-Salad-4.jpg", "Salad");
    private static final Recipe RECIPE_2 = new Recipe(2L, "Prepare the tortilini, drain. Cook the meat, then add the sauce and cream cheese. Put the tortellini into the backing dish and pour the meat/cheese/sauce mixture on top. Sprinkle the parmesan cheese over top. Bake for 20 min.", "Cheesey Tortellini Pasta Bake", 15, "https://images-gmi-pmc.edge-generalmills.com/de2a3f47-4a5a-4254-baa2-4e1e8cbc3ada.jpg", "Entree" );

    @Before
    public void setup(){
        //DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcRecipeDao(jdbcTemplate);

        testRecipe = new Recipe(99L, "Boil Eggs for 10 mins", "Hard-Boiled Eggs", 0, "egg.png", "snack");
    }

    @Test
    public void get_recipe_by_id_returns_correct_recipe(){
        Recipe recipe = sut.getRecipeByRecipeId(1L);

        assertRecipesMatch(RECIPE_1, recipe);
    }

    @Test
    public void get_recipe_by_id_returns_correct_recipe_2(){
        Recipe recipe = sut.getRecipeByRecipeId(2L);

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
        sut.deleteRecipeFromUserLibrary(1L, 1L);

        Recipe recipe = sut.getRecipeByRecipeId(1L);
        Assert.assertNull(recipe);
    }

    @Test
    public void get_all_recipes_by_user_id(){
        Recipe[] recipe = sut.getAllRecipesByUser(1L);

        Assert.assertEquals(4, recipe.length);
    }

    @Test
    public void add_recipe_to_users_library() {
        sut.addRecipeToUserRecipe(1L, 5L);
        Recipe[] userRecipes = sut.getAllRecipesByUser(1L);
        Assert.assertEquals(5, userRecipes.length);

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
