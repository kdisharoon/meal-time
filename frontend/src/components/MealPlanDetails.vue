<template>
  <div class="meal-plan-details">
    <h1 class="meal-plan-name">{{ mealPlan.mealPlanName }}</h1>
    
    <div v-for="thisMeal in mealPlan.recipes" v-bind:key="thisMeal.aFakeNumber" class="meal-plan-meal">
        {{ thisMeal.day }} {{ thisMeal.meal }}: {{ thisMeal.recipeIds.length }} recipes
        <div v-for="recipeID in thisMeal.recipeIds" v-bind:key="recipeID" class="recipe"> 
          <p> {{ getRecipeName(recipeID) }} </p>
<!-- add a link to the recipe details page to each recipe's name -->         
        </div>
    </div>
  </div>
</template>

<script>
import mealPlanService from '../services/MealPlanService';
import recipeService from '../services/RecipeService';

export default {
  name: 'meal-plan-details',

  data() {
    return {
      mealPlan: {
          mealPlanId: 0,
          userId: 0,
          mealPlanName: "",
          organizedRecipes: [
              {
                day: "",
                meal: "",
                recipeIds: []
              }
          ]
      },
      
    }
  },

  methods: {
    getRecipeName(id) {
      recipeService.getRecipeById(id).then(response => {
        console.log(response.data.recipeName);
        return response.data.recipeName; 
      });  
    }

  },

  created() {
    mealPlanService.getUserMealPlanById(this.$route.params.mealPlanID).then(response => {
      console.log(response.data);
      this.mealPlan = response.data;
    })
  },
  


  
}
</script>

<style>

</style>