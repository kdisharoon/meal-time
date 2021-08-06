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
      <div class="card">
        <a href= "/recipes/1">Caprese Salad</a>
          <img src="https://emilybites.com/wp-content/uploads/2020/08/Caprese-Salad-1b.jpg">
            
      </div>
      
      <div class="card">
        <a href= "/recipes/1">Caprese Salad</a>
          <img src="https://emilybites.com/wp-content/uploads/2020/08/Caprese-Salad-1b.jpg">
            
      </div>
      <div class="card">
        <a href= "/recipes/1">Caprese Salad</a>
          <img src="https://emilybites.com/wp-content/uploads/2020/08/Caprese-Salad-1b.jpg">
            
      </div>
      <div class="card">
        <a href= "/recipes/1">Caprese Salad</a>
          <img src="https://emilybites.com/wp-content/uploads/2020/08/Caprese-Salad-1b.jpg">
            
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
    saveRecipe() {
      recipeService.addRecipeToUserLibrary(this.$store.state.user.id, this.chosenRecipe.recipeId).then(response => {
        if (response.status === 201) {
          this.$router.push('/recipes');
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
  grid-template: 1fr 1fr;
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
  display: flex;
   background:aliceblue;
    max-width: 300px;
    margin: 30px ;
    padding: 10px;
    border: 2px solid black;
    border-radius: 5px  
  
}
.card{
  grid-area: card;
}

.card a{
  text-align: center !important;
}

.image{
  max-width: 180px;
  max-height: 180px;
  object-fit: contain;
 
}


</style>