<template>
<!-- Tom see's all -->
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
      <button hidden id="hiding" @click.prevent="saveRecipe">Add to My Recipes</button>
    </div>
  
    </div>
    
  


    <div class="search">
      
      <input type="text" placeholder="Search..." results = "0" v-model="userSearchTerm">
      <input type="image" class="searchbutton" name="search" src="http://www.spheretekk.com/bc/images/search-icon.gif" alt="Search">
      <button v-on:click.prevent="searchRecipesFromAPI(userSearchTerm)" class="searchRecipes">Search For Recipes</button>
    </div>



    <div id="recipe">
       
    <div  id="recTry"><h3>Recipes to Try</h3>  
     <div class="search" id="searchbox">
      <input type="text" placeholder="Search..." v-model="userSearchTerm">
      <button id="searchbutt" v-on:click.passive="searchRecipesFromAPI(userSearchTerm)" class="searchRecipes">Search</button>
    </div>
    </div>
     <div v-for="recipe in recipes.slice(-6)" v-bind:key="recipe.recipeId" id="cards">
      
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
      userSearchTerm: '',
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

    searchRecipesFromAPI(userSearchTerm) {
      recipeService.searchRecipesFromSpoonacular(userSearchTerm).then(response => {
        console.log(response.data.results)
        let idString = '';
        for (let i = 0; i < response.data.results.length; i++) {
          idString += response.data.results[i].id + ',';
        }
        console.log(idString);
        this.getSpecificRecipesFromAPI(idString.slice(0, -1));
      });
    },

    getSpecificRecipesFromAPI(parsedRecipeIds) {
      recipeService.getSpecificRecipesFromSpoonacular(parsedRecipeIds).then(response => {
        let newRecipes = [];
        for (let i = 0; i < response.data.length; i++) {
          let tempArr = {
            ingredients: []
          };
          tempArr.recipeId = response.data[i].id;
          tempArr.recipeName = response.data[i].title;
          tempArr.recipeImg = response.data[i].image;
          tempArr.preparation = response.data[i].instructions;
          tempArr.cookTime = response.data[i].readyInMinutes;
          for (let j = 0; j < response.data[i].extendedIngredients.length; j++) {
            let tempIngredientsArr = {};
            tempIngredientsArr.ingredientId = response.data[i].extendedIngredients[j].id;
            tempIngredientsArr.ingredientName = response.data[i].extendedIngredients[j].name;
            tempIngredientsArr.measurementAmount = response.data[i].extendedIngredients[j].amount;
            tempIngredientsArr.measurementUnit = response.data[i].extendedIngredients[j].unit;
            tempArr.ingredients.push(tempIngredientsArr);
          }
          newRecipes.push(tempArr);
        }
        this.saveToDatabase(newRecipes);

      });
    },

    saveToDatabase(recipesToAdd) {
      recipeService.addRecipesFromAPIToDatabase(recipesToAdd).then(response => {
        if (response.status === 201) {
           alert("You found something");
           this.$router.go();    // make this go to a Search Results page instead!
          }
        })
      .catch((error) => {
        console.log(error);
      });
    },

    saveRecipe() {
      recipeService.addRecipeToUserLibrary(this.$store.state.user.id, this.chosenRecipe.recipeId).then(response => {
        if (response.status === 201) {
          this.$router.push({name: 'saved-recipes', params: { userID: this.$store.state.user.id } });
          alert("Successfully Added!")
        }
      })
      .catch((error) => {
        alert("There was an error adding the recipe");
        console.log(error);
        console.log("This recipe is already saved to your recipes.");
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
.search input[type=text]{
  padding: 15px 15px;
  border: none;
  width: 200px;
  height: 25px;
  text-decoration: none;
  font-size: 16px;
  border-radius: 10px;
  border: 1px solid #ccc;
}
@media screen and (max-width: 100px){
  .search input[type=text]{
    float: none;
    display: block;
    text-align: left;
    width: 10%;
    margin: 0;
    padding: 14px;

  }
  .search {
    float: right;
    margin: 7px;
  }
 
}
 .search.searchbutton {
position:absolute;
top:23%;
right:5px;
}

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
  height: 60rem;
  overflow: scroll;
}

#recipe {
  grid-area: recipe;
  display:grid;
  grid-auto-rows: 100px 1fr 1fr;
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

#searchbutt{
  height:30px;
  width:auto;
  padding:0;
  margin-left:3px;
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