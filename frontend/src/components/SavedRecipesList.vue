<template>
<div>
  <h2 id="myRecipe">My Recipes</h2>
  <div class="saved-recipes-list d-flex justify-content-around">
    <div v-for="recipe in recipes" v-bind:key="recipe.recipeId" class="recipe">
      <div class="card">
        <img v-bind:src="recipe.recipeImg" class="recipe-image" style="width:100%">
          <h4 text-overflow:ellipsis;><b>{{recipe.recipeName}}</b></h4>
          <p class="card-text">{{ recipe.prepTime }} minutes prep time || {{ recipe.cookTime }} minutes cook time</p>
          <router-link v-bind:to="{ name: 'recipe', params: { recipeID: recipe.recipeId } }">
            <button> Recipe Details</button>
        </router-link>
      </div>
      </div>
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
<style>
#myRecipe{
  text-align: center;
}

.d-flex{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}

</style>