<template>
  <div class="all-recipes-list">
    <div v-for="recipe in recipes" v-bind:key="recipe.id" class="recipe">
      <router-link v-bind:to="{ name: 'Recipe', params: { recipeID: recipe.id } }">
        {{ recipe.name }}
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