<template>
  <div class="all-recipes-list">
    <h2>All Recipes</h2>
    <div v-for="recipe in recipes" v-bind:key="recipe.recipeId" class="recipe">
      <router-link v-bind:to="{ name: 'recipe', params: { recipeID: recipe.recipeId } }">
        {{ recipe.recipeName }}
      </router-link>
    </div>
  </div>
</template>

<script>
import recipeService from '../services/RecipeService';

export default {
  name: 'all-recipes-list',
  data() {
    return {
      recipes: []
    }
  },
  created() {
    recipeService.getAllRecipes().then(response => {
      this.recipes = response.data;
    });
  }
}
</script>