<template>
  <div class="recipe-details">
    <h1>{{ recipe.name }}</h1>
    <div v-for="recipe in recipe.recipes" v-bind:key="recipe.id" class="recipe">
      <h3 class="recipe-name">{{ recipe.name }}</h3>
      <p class="recipe-instructions">{{ recipe.preparation }}</p>
    </div>
  </div>
</template>

<script>
import recipeService from '../services/RecipeService'

export default {
  name: 'recipe-details',
  props: {
    'recipeId': Number
  },
  data() {
    return {
      recipe: {
        id: 0,
        name: '',
        preparation: '',
        prepTime: 0,
        cookTime: 0,
        imageURL: '',
        ingredients: {}
      },
    }
  },
  created() {
    recipeService.getRecipe(this.$route.params.id).then(response => {
      this.recipe = response.data;
    })
  }
}
</script>