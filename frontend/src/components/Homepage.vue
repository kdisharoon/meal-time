<template>
  <div id="popular">
    <div id="day" class="random-recipe">
      <h3>Random Recipe Generator</h3>
      <h4 id="recName">{{chosenRecipe.recipeName}}</h4>
      <img :src="chosenRecipe.recipeImg" class="image" id="randomImg">
    <h5>{{displayIngredients}}</h5>
    
      <p v-for="ingredient in chosenRecipe.ingredients" v-bind:key="ingredient.ingredientId">{{ingredient.measurementAmount}} {{ingredient.measurementUnit}} {{ingredient.ingredientName}}</p>
    <h5>{{displayPreparations}}</h5>
    <p>{{chosenRecipe.preparation}}</p>
    
    <div id="buttons">
    <button @click="randomizer(); requestText(); revealButton()">Get Random Recipe</button>
    <button hidden id="hiding" @click.prevent="saveRecipe" >Add to My Recipes </button>
    </div>
  
    </div>
    
    <div id="recipe">
    <h3 id="recTry">Recipes to Try</h3>  
     <div v-for="recipe in recipes.slice(11,17)" v-bind:key="recipe.recipeId" id="cards">
      
        <router-link v-bind:to="{ name: 'recipe', params: { recipeID: recipe.recipeId } }">
        
        <div class="card">
          <img :src="recipe.recipeImg">
            <h4 id="rName">{{recipe.recipeName}}</h4>
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

          alert("Recipe added to your library of recipes")

          this.$router.push(`users/${this.$store.state.user.id}/recipes`);
        }
      })
      .catch((error) => {
        console.log(error);
        alert("error")
      });
    },
    
   randomizer(){
      let randomRecipe = Math.floor(Math.random() * this.recipes.length);
    this.chosenRecipe = this.recipes[randomRecipe];
    },
    requestText(){
    this.displayIngredients = "Ingredients";
    this.displayPreparations = "Directions";
    },
    revealButton(){
      document.getElementById("hiding").innerHTML = "Add to My Recipes";
      document.getElementById("hiding").removeAttribute("hidden");
    }
  },
  

    
  }

</script>

<style>

#popular{
  display:grid;
  grid-template-columns: 300px 1fr;
  grid-template-areas: "day recipe "
                       "day recipe ";
}

#recName{
  text-align: center;
}
#rName{
  text-align: center;
   text-overflow: ellipsis;
   white-space: nowrap;
   width: 300px;
   overflow: hidden;
   position: relative;
}
#rName:hover{
  text-align: center;
  white-space: normal;
  text-overflow: initial;
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
  height: 49rem;
  overflow: scroll;
}

#recipe {
  grid-area: recipe;
  display:grid;
  grid-auto-rows: 75px 1fr 1fr;
 grid-auto-columns: 1fr 1fr 1fr;
 grid-template-areas: "try try try"
                      "cards cards cards"
                      "cards cards cards";
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
#recTry{
  text-align: center;
  grid-area: try;
  border:0px;
  text-decoration: underline;
}
a{
  margin: 10px;
  size: 100px;
  text-align: center;
}
#recipe .card{
  align-items: center;
   background:aliceblue;
    max-width: 300px;
    margin: 30px ;
    padding: 10px;
    border: 2px solid black;
    box-sizing: initial;
    border-radius: 5px;


}
.card img{
  align-self: start;
  
}
#cards{
  margin:auto;

}

#buttons{
  display: flex;
  justify-content: center;
  
  padding: 5px;
  gap:15px;
}


.image{
  max-width: 180px;
  max-height: 180px;
  object-fit: contain;
 
}


</style>