<template>
  <div class="saved-recipes-list">
    <div v-for="recipe in recipes" v-bind:key="recipe.id" class="recipe">
      <img v-bind:src="recipe.imageURL" class="recipe-image" />
      <router-link v-bind:to="{ name: 'Recipe', params: { id: recipe.id } }">
        {{ recipe.name }}
      </router-link>
    </div>
  </div>
</template>

<script>
import recipeService from '../services/RecipeService';

export default {
  name: 'saved-recipes-list',
  data() {
    return {
      recipes: []
    }
  },
  created() {
    recipeService.getAllUserRecipes(this.$route.params.userID).then(response => {
      this.recipes = response.data;
    });
  }
}
</script>