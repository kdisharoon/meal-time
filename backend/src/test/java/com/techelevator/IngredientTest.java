package com.techelevator;

import com.techelevator.model.Ingredient;
import org.junit.Assert;
import org.junit.Test;

public class IngredientTest {
    Ingredient ingredient = new Ingredient();


    @Test
    public void ingredient_id_is_correct_id(){
        ingredient.setIngredientId(22L);
        Assert.assertEquals(22L, ingredient.getIngredientId());
    }

    @Test
    public void ingredient_name_is_pepper(){
        ingredient.setIngredientName("pepper");
        Assert.assertEquals("pepper", ingredient.getIngredientName());
    }

    @Test
    public void ingredient_measurement_unit_is_cup(){
        ingredient.setMeasurementUnit("cup");
        Assert.assertEquals("cup", ingredient.getMeasurementUnit());
    }

    @Test
    public void ingredient_measurement_amount_is_2(){
        ingredient.setMeasurementAmount(2);
        Assert.assertEquals(2, ingredient.getMeasurementAmount());
    }
}
