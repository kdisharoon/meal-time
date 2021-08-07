<template>
  <div id="popular">
    <div id="day" class="random-recipe">
      <h3>Random Recipe Generator</h3>
      <h4>{{chosenRecipe.recipeName}}</h4>
      <img :src="chosenRecipe.recipeImg" class="image" id="randomImg">
    <h5>{{displayIngredients}}</h5>
    
      <p v-for="ingredient in chosenRecipe.ingredients" v-bind:key="ingredient.ingredientId">{{ingredient.measurementAmount}} {{ingredient.measurementUnit}} {{ingredient.ingredientName}}</p>
    <h5>{{displayPreparations}}</h5>
    <p>{{chosenRecipe.preparation}}</p>
    
    <div id="buttons">
    <button @click="randomizer(); requestText()">Get Random Recipe</button>
    <button @click.prevent="saveRecipe">Add to My Recipes </button>
    </div>
    </div>
    <div id="recipe">
      <h3>Popular Recipes</h3>
     <div v-for="recipe in recipes.slice(11, 17)" v-bind:key="recipe.recipeId" id="cards">
      
        <router-link v-bind:to="{ name: 'recipe', params: { recipeID: recipe.recipeId } }">
        
        <div class="card">
          <img :src="recipe.recipeImg">
            <h4>{{recipe.recipeName}}</h4>
      </div>
      </router-link>
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
      chosenRecipe: {},
      displayIngredients: '',
      displayPreparations: ''
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
    
   randomizer(){
      let randomRecipe = Math.floor(Math.random() * this.recipes.length);
    this.chosenRecipe = this.recipes[randomRecipe];
    },
    requestText(){
    this.displayIngredients = "Ingredients";
    this.displayPreparations = "Directions";
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
  
  grid-area: cards;
  
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
  background-size: cover;
}
#recipe {
  grid-area: recipe;
  display:flex;
 flex-grow: initial;
 flex-direction: row;
 align-content: center;
 align-items: center;
 flex-wrap: wrap;
}
h5,p{
    text-align: left;
}
h4{
  text-align: center;
}
a{
  margin: 10px;
  size: 100px;
}
#recipe .card{
  align-items: center;
   background:aliceblue;
    max-width: 300px;
    margin: 30px ;
    padding: 10px;
    border: 2px solid black;
    box-sizing: initial;
    border-radius: 5px  

}
.card img{
  align-self: start;
  
}
.card {
  grid-area: card;
  align-items: center;
  flex: 1;
}
#cards{
  display: flex;
}
#buttons{
  display: flex;
  justify-content: space-between;
  padding: 5px;
  gap: 6px;
}


.image{
  max-width: 180px;
  max-height: 180px;
  object-fit: contain;
 
}


</style>