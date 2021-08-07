<template>
<div id="full">
  
  <h2>All Recipes</h2>

  <div class="lds-ripple" v-if="isLoading">
    <div></div><div></div>
  </div>
  
  <div v-else>
    <button v-on:click.prevent="getNewRecipesFromAPI" class="addRecipes">Add 10 Recipes to Database from API</button>
    <div class="container card-deck">
      <div class="row">
        <div class="all-recipes-list d-flex justify-content-around flex-wrap">
    
          <div v-for="recipe in recipes" v-bind:key="recipe.recipeId" class="recipe thumbnail">
            <div class="card" style="width: 18rem">
              <img v-bind:src="recipe.recipeImg" class="recipe-image" style="width:100%">
                <h4><b>{{recipe.recipeName}}</b></h4>
                <p class="card-text">{{ recipe.cookTime }} minutes total time</p>
                <router-link v-bind:to="{ name: 'recipe', params: { recipeID: recipe.recipeId } }">
                  <button> Recipe Details</button>
                </router-link>
            </div>
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
      isLoading: true,
      recipes: []
    }
  },

  methods: {
    saveToDatabase(recipesToAdd) {
      recipeService.addRecipesFromAPIToDatabase(recipesToAdd).then(response => {
        if (response.status === 201) {
          console.log("Successfully added 10 recipes to database from API!");
          }
        })
      .catch((error) => {
        console.log(error);
      });
    },

    getNewRecipesFromAPI() {
      recipeService.getRandomRecipesFromSpoonacular().then(response => {
        let newRecipes = [];
        for (let i = 0; i < response.data.recipes.length; i++) {
          let tempArr = {
            ingredients: []
          };
          tempArr.recipeId = response.data.recipes[i].id;
          tempArr.recipeName = response.data.recipes[i].title;
          tempArr.recipeImg = response.data.recipes[i].image;
          tempArr.preparation = response.data.recipes[i].instructions;
          tempArr.cookTime = response.data.recipes[i].readyInMinutes;
          for (let j = 0; j < response.data.recipes[i].extendedIngredients.length; j++) {
            let tempIngredientsArr = {};
            tempIngredientsArr.ingredientId = response.data.recipes[i].extendedIngredients[j].id;
            tempIngredientsArr.ingredientName = response.data.recipes[i].extendedIngredients[j].name;
            tempIngredientsArr.measurementAmount = response.data.recipes[i].extendedIngredients[j].amount;
            tempIngredientsArr.measurementUnit = response.data.recipes[i].extendedIngredients[j].unit;
            tempArr.ingredients.push(tempIngredientsArr);
          }
          newRecipes.push(tempArr);
        }
        this.saveToDatabase(newRecipes);
      });
    },

  },

  created() {
    recipeService.getAllRecipes().then(response => {
      this.recipes = response.data;
      this.isLoading = false;
    })
    
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
#top{
  display:flex;

}

  button:hover {
    background-color:rgba(255, 166, 0, 0.626);
    border-radius: 10px;
  }
  
  button:active {
    background-color:orange;
    box-shadow: 0 5px #666;
    transform: translateY(4px);
  }

  img {
 
    width:  auto;
    height: 200px;
    object-fit: cover;

  }






.lds-ripple {
  display: flex;
  justify-content: center;
  
  position: relative;
  
}
.lds-ripple div {
  position: absolute;
  border: 4px solid #fff;
  opacity: 1;
  border-radius: 50%;
  animation: lds-ripple 1s cubic-bezier(0, 0.2, 0.8, 1) infinite;
}
.lds-ripple div:nth-child(2) {
  animation-delay: -0.5s;
}
@keyframes lds-ripple {
  0% {
    top: 36px;
    left: 36px;
    width: 0;
    height: 0;
    opacity: 1;
  }
  100% {
    top: 0px;
    left: 0px;
    width: 72px;
    height: 72px;
    opacity: 0;
  }
}
  

</style>

