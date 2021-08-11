<template>
<div>
  <h2 id="myRecipe">My Recipes</h2>
  <div class="saved-recipes-list d-flex justify-content-around">
    <div v-for="recipe in recipes" v-bind:key="recipe.recipeId" class="recipe">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
      <div class="card">
        <div id="delButt">
        <button id="deleteButt" v-on:click.prevent="deleteRecipe(recipe.recipeId)"><i class="fas fa-times"></i></button>
        </div>
        <img v-bind:src="recipe.recipeImg" class="recipe-image" style="width:100%">
          <h4 id="rTwo"><b>{{recipe.recipeName}}</b></h4>
          <p class="card-text">{{ recipe.cookTime }} minutes cook time</p>
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

  methods: {
    deleteRecipe(recipeID) {
      recipeService.deleteRecipeFromUserLibrary(this.$route.params.userID, recipeID).then(response => {
        if (confirm("Are you sure you want to delete this recipe?") && response.status === 204) {
          alert("Recipe successfully removed from your recipe collection!");
          this.$router.go();
        }  
      }).catch((error) => {
        console.log(error);
      });
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
#delButt{
  display: flex;
  flex-direction: row-reverse;
  margin-bottom:4px;
}
#deleteButt{
  display: flex;
  height: 3px;
  width: 3px;

  align-content: center;
  justify-content: center;
  align-items: center;
}

</style>