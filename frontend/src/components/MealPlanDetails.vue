<template>
  <div class="meal-plan-details">
    <h1 class="meal-plan-name">{{ mealPlan.mealPlanName }}</h1>
    
    <div v-for="currentMeal in mealPlan.organizedRecipes" v-bind:key="currentMeal" class="meal-plan-meal">
        {{ currentMeal.day }} {{ currentMeal.meal }}: {{ currentMeal.recipeIds.length }} recipes
        <div v-for="recipeID in recipeIds" v-bind:key="recipeID" class="recipe">
            <p>{{ getRecipe(recipeID) }}</p>
        </div>
    </div>
    
    
    <h3>{{ recipe.cookTime }} minutes cook time</h3>
    <h5>Ingredients</h5>
    <div class="recipe-ingredients" v-for="ingredient in recipe.ingredients" v-bind:key="ingredient.ingredientId">
      <h4>{{ ingredient.measurementAmount }} {{ ingredient.measurementUnit }} {{ ingredient.ingredientName}}</h4>
    </div>
    <ol>
      <li v-for="step in recipe.preparation" v-bind:key="step" class="recipe-step">
        {{ step }}
      </li>
    </ol>
    <button class="btn btn-add-recipe-to-user-library" v-on:click.prevent="saveRecipe">Save Recipe To My Library</button>
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
      }
    }
  },
  methods: {
    getRecipe(id) {
      recipeService.getRecipeById(id);
    }
  
//    saveMealPlan() {
//      mealPlanService.addUserMealPlan(this.$store.state.user.id).then(response => {
//        if (response.status === 201) {
//          this.$router.push(`/users/${this.$store.state.user.id}/mealplans`);
//        }
//      })
//      .catch((error) => {
//        console.log(error);
//      });
//    }
  },

  created() {
    mealPlanService.getUserMealPlanById(this.$route.params.mealPlanID).then(response => {
      this.mealPlan = response.data;
    })
  

  }
}
</script>

<style>

</style>