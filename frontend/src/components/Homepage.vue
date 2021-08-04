<template>
  <div id="popular">
    <div id="day" class="random-recipe">
      <h3>Random Recipe Generator</h3>
      <h4>{{chosenRecipe.recipeName}}</h4>
      <img :src="chosenRecipe.recipeImg" class="image" id="randomImg">
    <h5 >Ingredients</h5>
      <p v-for="ingredient in chosenRecipe.ingredients" v-bind:key="ingredient.ingredientId">{{ingredient.measurementAmount}} {{ingredient.measurementUnit}} {{ingredient.ingredientName}}</p>
    <h5>Preparations</h5>
    <p>{{chosenRecipe.preparation}}</p>
    <button @click="randomizer">Get Random Recipe</button>
    </div>
    <div id="recipe">
      <h3>Popular Recipes</h3>
      <div class="card">
          <img src="https://emilybites.com/wp-content/uploads/2020/08/Caprese-Salad-1b.jpg">
            <div class="info">
                <a href= "/recipes/1">Caprese Salad</a>
            </div>
      </div>
    </div>
  </div>
</template>

<script>
import recipeService from '../services/RecipeService';

export default {
 
  name: "random-recipe",
  data() {
    return {
      recipes: [],
      chosenRecipe: {}
    }
  },
  created() {
      recipeService.getAllRecipes().then(response => {
      this.recipes = response.data;
    });
  },
  methods:{
   randomizer: function(){
      let randomRecipe = Math.floor(Math.random() * this.recipes.length);
    this.chosenRecipe = this.recipes[randomRecipe];
    }
  }
};
</script>

<style>
#popular {
  display: grid;
  grid-template-columns: 350px 1fr;
  grid-template-areas:
    "day recipe"
    "day recipe"
    "day recipe";
    
}
#randomImg {
  display: inline;
}
#day {
    padding-left: 10px;
    padding-right: 10px;
  grid-area: day;
  border-right: 1px solid rgb(34, 88, 133);
  text-align: center;
  background-color: antiquewhite;
  border-radius: 3px;
}
#recipe {
  grid-area: recipe;
  text-align: center;
}
h5,p{
    text-align: left;
}

#recipe .card{
   background:antiquewhite;
    max-width: 300px;
    margin: 30px auto;
    padding: 0px;
    border: 2px solid black;
    border-radius: 5px;  
}

.card a{
  text-align: center;
}

.image{
  max-width: 180px;
  max-height: 180px;
}
</style>