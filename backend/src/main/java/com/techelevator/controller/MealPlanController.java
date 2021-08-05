package com.techelevator.controller;


import com.techelevator.dao.MealPlanDao;
import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class MealPlanController {

    private final MealPlanDao mealPlanDao;

    public MealPlanController(MealPlanDao mealplanDao){
        this.mealPlanDao=mealplanDao;
    }



}
