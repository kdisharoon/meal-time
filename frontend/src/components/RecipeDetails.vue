<template>
  <div class="recipe-details">
    <h1 class="recipe-name">{{ recipe.name }}</h1>
    <img v-bind:src="recipe.imageURL" class="recipe-image" />
    <h3>{{ recipe.prepTime }} minutes prep time || {{ recipe.cookTime }} minutes cook time</h3>
    <div class="recipe-ingredients" v-for="ingredient in recipe.ingredients" v-bind:key="ingredient.ingredientId">
      <h4>{{ ingredient.measurementAmount }} {{ ingredient.measurementUnit }} {{ ingredient.ingredientName}}</h4>
    </div>
    <p class="recipe-preparation">{{ recipe.preparation }}</p>
  </div>
</template>

<script>
import recipeService from '../services/RecipeService'

export default {
  name: 'recipe-details',

  data() {
    return {
      recipe: {
        id: 0,
        name: '',
        preparation: [],
        prepTime: 0,
        cookTime: 0,
        imageURL: '',
        mealType: '',
        ingredients: []
      },
    }
  },
  created() {
    recipeService.getRecipeById(this.$route.params.recipeID).then(response => {
      this.recipe.id = response.data.recipeId;
      this.recipe.name = response.data.recipeName;
      this.recipe.preparation = response.data.preparation.split('. ');
      this.recipe.prepTime = response.data.prepTime;
      this.recipe.cookTime = response.data.cookTime;
      this.recipe.imageURL = response.data.recipeImg;
      this.recipe.mealType = response.data.type;
      this.recipe.ingredients = response.data.ingredients;
    })
  }
}
</script>