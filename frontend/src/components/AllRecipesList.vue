<template>
<div>
  <h2>All Recipes</h2>
  <div class="container card-deck">
    <div class="row">
  <div class="all-recipes-list d-flex justify-content-around flex-wrap">
    <div v-for="recipe in recipes" v-bind:key="recipe.recipeId" class="recipe thumbnail">
      <div class="card" style="width: 18rem">
        <img v-bind:src="recipe.recipeImg" class="recipe-image" style="width:100%">
          <h4><b>{{recipe.recipeName}}</b></h4>
          <p class="card-text">{{ recipe.prepTime }} minutes prep time || {{ recipe.cookTime }} minutes cook time</p>
          <router-link v-bind:to="{ name: 'recipe', params: { recipeID: recipe.recipeId } }">
            <button> Recipe Details</button>
        </router-link>
      </div>
      </div>
      </div>
    </div>
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

<style>

/* .all-recipes-list {
    background:white;
    max-width: 300px;
    margin: 30px auto;
    padding: 10px;
    border: 2px solid black;
    border-radius: 5px; 
  } */
 .card {
      padding: 5px;
      background:white;
      max-width: 300px;
      margin: 40px auto;
      padding: 10px;
      border: 2px solid black;
      border-radius: 5px;
      /* border-width: 2px;
      justify-content: center; */
  } 

  button {
    cursor: pointer;
    background-color: orange;
    border: none;
    color:black;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    border-radius: 10px;
  }
  button:hover {
    background-color:grey;
    border-radius: 10px;
  }
  
  button:active {
    background-color:orange;
    box-shadow: 0 5px #666;
    transform: translateY(4px);
  }

  img {
    float: left;
    width:  100px;
    height: 200px;
    object-fit: cover;

  }


  

</style>

