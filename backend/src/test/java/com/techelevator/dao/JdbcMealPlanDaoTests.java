package com.techelevator.dao;

import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcMealPlanDaoTests extends FinalCapstoneDaoTests{
    private JdbcMealPlanDao sut;


    @Before
    public void setup(){
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcMealPlanDao(jdbcTemplate);
    }


    @Test
    public void get_meal_plan_by_user_id(){
        MealPlan[] mealPlan = sut.getAllUserMealPlans(1L);
        Assert.assertEquals(1, mealPlan.length);
    }
}
