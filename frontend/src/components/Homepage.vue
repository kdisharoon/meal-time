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
    
    <button @click="randomizer" class="buttons">Get Random Recipe</button>
    <button class="buttons" @click.prevent="saveRecipe">Add to My Recipes </button>
    
    </div>
    <div id="recipe">
      <h3>Popular Recipes</h3>
     <div id="cards">
      
        <a href= "/recipes/1">
        <h5>Caprese Salad</h5>
        <div class="card">
          <img :src="recipes.recipeImg">
            
      </div>
      </a>
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
    saveRecipe() {
      recipeService.addRecipeToUserLibrary(this.$store.state.user.id, this.recipe.id).then(response => {
        if (response.status === 201) {

          // add a popup "Success!" message here

          this.$router.push(`users/${this.$store.state.user.id}/recipes`);
        }
      })
      .catch((error) => {
        console.log(error);
      });
    },
    
   randomizer: function(){
      let randomRecipe = Math.floor(Math.random() * this.recipes.length);
    this.chosenRecipe = this.recipes[randomRecipe];
    }
  },

    
  }

</script>

<style>
#popular {
  display: grid;
  grid-template-columns: 350px 1fr;
  grid-template-areas:
    "day recipe"
    "day cards"
    "day cards";
    
}
#popular #cards{
  display:grid;
  grid-area: cards;
  gap: 30px;
  grid-template-columns: 1fr 1fr;
  justify-items: center;
  grid-template-areas: "card card"
                       "card card";
}
#day {
    padding-left: 10px;
    padding-right: 10px;
  grid-area: day;
  border-right: 2px solid black;
  border-bottom: 2px solid black;
  text-align: center;
  background-color:aliceblue;
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
  display: inline-flex;
  align-items: center;
   background:aliceblue;
    max-width: 300px;
    margin: 30px ;
    padding: 10px;
    border: 2px solid black;
    border-radius: 5px  

}
.card img{
  align-self: start;
}
.card{
  grid-area: card;
  align-items: center;
}

.image{
  max-width: 180px;
  max-height: 180px;
  object-fit: contain;
 
}


</style>