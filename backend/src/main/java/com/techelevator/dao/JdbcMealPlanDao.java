package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcMealPlanDao implements MealPlanDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMealPlanDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }




    public void createMealPlan(MealPlan mealPlan){

        String sql = "";

    }

}
