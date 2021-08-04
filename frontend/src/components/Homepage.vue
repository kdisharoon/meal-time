<template>
  <div id="popular">
    <div id="day" class="random-recipe">
      <h3>Random Recipe Generator</h3>
      <h4>{{chosenRecipe.recipeName}}</h4>
      <img :src="chosenRecipe.recipeImg" class="image">
    <h5>Ingredients</h5>
      <p>{{chosenRecipe.ingredients}}</p>
    <h5>Preparations</h5>
    <p>{{chosenRecipe.preparation}}</p>
    <button @click="randomizer">Get Random Recipe</button>
    </div>
    <div id="recipe">
      <h3>Popular Recipes</h3>
      <div class="card">
          <img src="">
            <div class="info">
                <a href= "#">Caprese Salad</a>
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
#day {
    padding-left: 10px;
    padding-right: 10px;
  grid-area: day;
  border-right: 1px solid rgb(34, 88, 133);
  text-align: center;
}
#recipe {
  grid-area: recipe;
  text-align: center;

}
h5{
    text-align: left;
}
li {
    text-align: left;
}
#recipe .card{
  border-radius: 3px;
  max-width: 300px;
  padding: 15px 35px 45px;
  margin: 0 auto;
  background-color: #fff;
  border: 1px solid rgba(0,0,0,0.1);  
}

.image{
  max-width: 180px;
  max-height: 180px;
}
</style>