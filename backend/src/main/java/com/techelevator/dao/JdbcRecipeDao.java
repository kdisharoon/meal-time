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

    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Recipe[] getAllRecipesByUser(long userId){
        List<Recipe> recipes = new ArrayList<>();
        String sql = "select * from recipes " +
                     "join user_recipes using (recipe_id) " +
                     "join users using (user_id) " +
                     "where user_id=?";
        String sql2 = "select ingredient_id, measurement_amount, measurement_unit, ingredients.ingredient_name " +
                      "from recipe_ingredients " +
                      "join ingredients using (ingredient_id) " +
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

        String sql = "select * from recipes ";
        String sql2 = "select ingredient_id, measurement_amount, measurement_unit, ingredients.ingredient_name " +
                "from recipe_ingredients " +
                "join ingredients using (ingredient_id) " +
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

        String sql = "select * from recipes " +
                     "where recipe_id=?";
        String sql2 = "select ingredient_id, measurement_amount, measurement_unit, ingredients.ingredient_name " +
                "from recipe_ingredients " +
                "join ingredients using (ingredient_id) " +
                "where recipe_id=?";
        SqlRowSet recipeResults = jdbcTemplate.queryForRowSet(sql, recipeId);
        if (recipeResults.next()){
            recipe = mapRowToRecipe(recipeResults);
            SqlRowSet ingredientResults = jdbcTemplate.queryForRowSet(sql2, recipeId);
            recipe.setIngredients(mapRowsToIngredients(ingredientResults));

        }
        return recipe;


    }

    public void addRecipeToUserRecipe(long userId, long recipeId){

        String sql2 = "select user_id, recipe_id " +
                      "from user_recipes " +
                      "where user_id=? and recipe_id=?";
        SqlRowSet  containsRecipe = jdbcTemplate.queryForRowSet(sql2, userId, recipeId);
        if (containsRecipe.next()){
            throw new IllegalArgumentException();
        }
        String sql = "insert into user_recipes (user_id, recipe_id) " +
                     "values (?,?) ";

        jdbcTemplate.update(sql, userId, recipeId);
    }

    public void deleteRecipeFromUserLibrary(long userId, long recipeId){
        String sql = "delete from user_recipes " +
                     "where user_id=? and recipe_id=? ";
        jdbcTemplate.update(sql, userId, recipeId);
    }

    public Recipe mapRowToRecipe(SqlRowSet result){
        Recipe recipe = new Recipe();
        recipe.setRecipeId(result.getLong("recipe_id"));
        recipe.setPreparation(result.getString("preparation"));
        recipe.setRecipeName(result.getString("recipe_name"));
//        recipe.setPrepTime(result.getInt("prep_time"));
        recipe.setCookTime(result.getInt("cook_time"));
        recipe.setRecipeImg(result.getString("recipe_img"));
        recipe.setRecipeType(result.getString("recipe_type"));
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

    public void addRecipeToRecipeLibrary(Recipe recipe){
        String sql = "insert into recipes (recipe_id,preparation,recipe_name,prep_time,cook_time,recipe_type,recipe_img) " +
                     "values (?,?,?,?,?,?,?)";
        long recipeId = recipe.getRecipeId();
        String preparation= recipe.getPreparation();
        String recipeName=recipe.getRecipeName();
        int cookTime=recipe.getCookTime();
        String recipeImg=recipe.getRecipeImg();
        String recipeType=recipe.getRecipeType();
        Ingredient[] ingredients=recipe.getIngredients();
        jdbcTemplate.update(sql,recipeId,preparation,recipeName,0,cookTime,recipeImg,recipeType);

        long ingredientId;

        for (int i = 0; i<ingredients.length; i++){
            String sql2 = "select ingredient_name from ingredients where ingredient_name =?";
            SqlRowSet ingredientResult= jdbcTemplate.queryForRowSet(sql2,ingredients[i].getIngredientName());
            ingredientId = ingredients[i].getIngredientId();
            if (!ingredientResult.next()){
                String sql3 = "insert into ingredients (ingredient_id, ingredient_name) " +
                              "values (?,?) returning ingredient_id";
                jdbcTemplate.update(sql3,ingredientId,ingredients[i].getIngredientName());
            }
            String sql4="insert into recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) " +
                        "values (?,?,?,?)";
            jdbcTemplate.update(sql4,ingredientId,recipeId,ingredients[i].getMeasurementUnit(),ingredients[i].getMeasurementAmount());

        }
    }
}
