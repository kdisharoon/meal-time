package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcRecipeDao implements RecipeDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Recipe[] getAllRecipesByUser(long userId){
        List<Recipe> recipes = new ArrayList<>();
        String sql = "select * from recipe " +
                     "join user_recipe using (recipe_id) " +
                     "join user using (user_id) " +
                     "where user_id=?";
        String sql2 = "select ingredient_id, measurement_amount, measurement_unit, ingredient_name " +
                      "from recipe_ingredient " +
                      "join ingredient using (ingredient_id) " +
                      "where recipe_id=?";
        SqlRowSet recipeResults = jdbcTemplate.queryForRowSet(sql, userId);
        while (recipeResults.next()){
            Recipe recipe = mapRowToRecipe(recipeResults);
            SqlRowSet ingredientResults = jdbcTemplate.queryForRowSet(sql2, recipe.getRecipeId());
            recipe.setIngredients(mapRowsToIngredients(ingredientResults));
            recipes.add(recipe);
        }
        return recipes.toArray(new Recipe[0]);

    }
    @Override
    public Recipe[] getAllRecipes(){
        List<Recipe> recipes = new ArrayList<>();

        String sql = "select * from recipe";
        String sql2 = "select ingredient_id, measurement_amount, measurement_unit, ingredient_name " +
                "from recipe_ingredient " +
                "join ingredient using (ingredient_id) " +
                "where recipe_id=?";
        SqlRowSet recipeResults = jdbcTemplate.queryForRowSet(sql);
        while (recipeResults.next()){
            Recipe recipe = mapRowToRecipe(recipeResults);
            SqlRowSet ingredientResults = jdbcTemplate.queryForRowSet(sql2, recipe.getRecipeId());
            recipe.setIngredients(mapRowsToIngredients(ingredientResults));
            recipes.add(recipe);
        }
        return recipes.toArray(new Recipe[0]);
    }
    @Override
    public Recipe getRecipeByRecipeId(long recipeId){
        Recipe recipe = new Recipe();

        String sql = "select * from recipe " +
                     "where recipe_id=?";
        String sql2 = "select ingredient_id, measurement_amount, measurement_unit, ingredient_name " +
                "from recipe_ingredient " +
                "join ingredient using (ingredient_id) " +
                "where recipe_id=?";
        SqlRowSet recipeResults = jdbcTemplate.queryForRowSet(sql, recipeId);
        if (recipeResults.next()){
            recipe = mapRowToRecipe(recipeResults);
            SqlRowSet ingredientResults = jdbcTemplate.queryForRowSet(sql2, recipeId);
            recipe.setIngredients(mapRowsToIngredients(ingredientResults));

        }
        return recipe;


    }

    public Recipe mapRowToRecipe(SqlRowSet result){
        Recipe recipe = new Recipe();
        recipe.setRecipeId(result.getLong("recipe_id"));
        recipe.setPreparation(result.getString("preparation"));
        recipe.setRecipeName(result.getString("recipe_name"));
        recipe.setPrepTime(result.getInt("prep_time"));
        recipe.setCookTime(result.getInt("cook_time"));
        recipe.setRecipeImg(result.getString("recipe_img"));
        recipe.setType(result.getString("type"));
        return recipe;

    }

    public Ingredient[] mapRowsToIngredients(SqlRowSet results){
        List<Ingredient> ingredients = new ArrayList<>();
        while (results.next()){
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientId(results.getLong("ingredient_id"));
            ingredient.setIngredientName(results.getString("ingredient_name"));
            ingredient.setMeasurementAmount(results.getLong("measurement_amount"));
            ingredient.setMeasurementUnit(results.getString("measurement_unit"));
            ingredients.add(ingredient);
        }
        return ingredients.toArray(new Ingredient[0]);
    }
}
