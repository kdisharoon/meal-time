<template>


  <div id="popular">
 
    <!-- Loads an icon library -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <div class="loading" v-if="isLoading">
      <img src="../assets/giphy.gif" />
    </div>


    <!-- used to fill the Random Recipe Generator section with a random recipe from the recipes array -->
    <div id="day" class="random-recipe">
      <h3>Random Recipe Generator</h3>
      <h4 id="recName">{{chosenRecipe.recipeName}}</h4>
      <img :src="chosenRecipe.recipeImg" class="image" id="randomImg">
      <h5>{{displayIngredients}}</h5>
    
      <p class="rrg" v-for="ingredient in chosenRecipe.ingredients" v-bind:key="ingredient.ingredientId">
        {{ingredient.measurementAmount}} {{ingredient.measurementUnit}} {{ingredient.ingredientName}}
      </p>
      <h5>{{displayPreparations}}</h5>
      <p class="rrg">{{chosenRecipe.preparation}}</p>
    
      <div id="buttons">
        <button @click="randomizer(); requestText(); revealButton()">Get Random Recipe</button>
        <button hidden id="hiding" @click.prevent="saveRecipe">Add to My Recipes</button>
      </div>
    </div>
  

    <div id="recipe">
      <div id="recTry"><h3>Recipes to Try</h3>  
        <form action="">
          <div class="form-group">
            <!-- searches the recipe API (not the local database or local recipes array) for the term that the user inputs -->
            <input type="text" placeholder="Search..." v-model="userSearchTerm">
            <button class ="searchButton" v-on:click.prevent="searchRecipesFromAPI(userSearchTerm)" type="submit"><i class="fa fa-search"></i></button>
          </div>
        </form>
      </div>
       
      
      <!-- this shows the last 6 recipes in the database on the home page -->
      <div v-for="recipe in recipes.slice(-6)" v-bind:key="recipe.recipeId" id="cards">
        <router-link v-bind:to="{ name: 'recipe', params: { recipeID: recipe.recipeId } }">
          <div class="card">
            <img :src="recipe.recipeImg">
            <h4 id="rName">{{ recipe.recipeName }}</h4>
          </div>
        </router-link>
      </div>
    </div>


    <!-- this is the folding card Pittsburgh photo animation on the bottom of the page -->
    <div class="pghCard">
      <div class="fold"></div>
      <div class="fold"></div>
      <div class="fold"></div>
      <div class="fold"></div>
      <div class="fold"></div>
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
      displayPreparations: '',
      isLoading: true,
    }
  },

  created() {
    // this fills the recipes array in this component with the entire recipes table in the database
    recipeService.getAllRecipes().then(response => {
      this.recipes = response.data;
      this.isLoading = false;
    });
  },

  methods: {

    // this searches the user's input from the Spoonacular API and parses the resulting JSON objects to get the recipe IDs.
    // it makes a space-separated idString and does a second Spoonacular API call to get detailed recipe info for each recipe.
    // this is necessary to get the preparation instructions.
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

    // this parses the relevant information from the Spoonacular API's recipe search and fills the newRecipes array.
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

    // this sends the recipes we just pulled from the API to the backend to save them in our database
    saveToDatabase(recipesToAdd) {
      recipeService.addRecipesFromAPIToDatabase(recipesToAdd).then(response => {
        if (response.status === 201) {
           alert("You found some recipes!");
           this.$router.go();    // make this go to a Search Results page instead!
          }
        })
      .catch((error) => {
        console.log(error);
      });
    },

    // this takes a recipe and adds it to the user's "My Recipes" saved collection
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
    
    // selects a recipe from the recipes array randomly and puts that info into a chosenRecipe object to display it
    randomizer() {
      let randomRecipe = Math.floor(Math.random() * this.recipes.length);
      this.chosenRecipe = this.recipes[randomRecipe];
    },

    requestText() {
      this.displayIngredients = "Ingredients";
      this.displayPreparations = "Directions";
    },

    revealButton() {
      document.getElementById("hiding").innerHTML = "Add to My Recipes";
      document.getElementById("hiding").removeAttribute("hidden");
    }
  },
    
}

</script>




<style>

.rrg{
  margin: 5px;
  font-size: 16px;
}

.form-group {
  display: -webkit-flex;
  -webkit-flex-wrap: wrap;
  display: flex;
  flex-wrap: nowrap;
  margin-bottom: 1rem;
  display: inline-block;
}
 
input {
  flex: 1 1 auto;
  font-weight: 400;
  height: calc(1.5em + 1rem + 2px);
  padding: .5rem 1rem;
  font-size: 14px;
  line-height: 1.5;
  color: #495057;
  background-color: #fff;
  border: 1px solid #ced4da;
  border-radius: .3rem 0 0 .3rem;
  outline: 0;
}
 
.searchButton {
  font-weight: 400;
  color: #ffffff;
  cursor: pointer;
  text-align: center;
  user-select: none;
  border: 1px solid transparent; 
  padding: .5rem 1rem;
  font-size: 14px;
  line-height: 1.5;
  border-radius: 0 .3rem .3rem 0;
  background-color: #007bff;
  outline: 0;
  padding-bottom: 5px;
}

/*
.search input[type=text] {
  padding: 15px 15px;
  border: none;
  width: 200px;
  height: 25px;
  text-decoration: none;
  font-size: 16px;
  border-radius: 10px;
  border: 1px solid #ccc;
}

@media screen and (max-width: 100px) {
  .search input[type=text] {
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
*/

#popular {
  display:grid;
  grid-template-columns: 300px 1fr;
  grid-template-areas: "day recipe "
                       "day recipe ";
}

#recName {
  text-align: center;
}

#rName {
  text-align: center;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 280px;
  overflow: hidden;
  position: relative;
}

#rName:hover {
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

h5,p {
  text-align: left;
}

h4 {
  text-align: center;
}

#recTry {
  text-align: center;
  grid-area: try;
  border:10px;
  text-decoration: underline;
}

#searchbutt {
  height:30px;
  width:auto;
  padding:0;
  margin-left:3px;
}

a {
  margin: 10px;
  size: 100px;
  text-align: center;
}

/* 
recipe .card {
  align-items: center;
  background:aliceblue;
  max-width: 300px;
  margin: 30px ;
  padding: 10px;
  border: 2px solid black;
  box-sizing: initial;
  border-radius: 5px;
 
.card img {
  align-self: start;
}
*/

#cards {
  margin: auto;
}

#buttons {
  display: flex;
  justify-content: center;
  padding: 5px;
  gap:15px;
}

/* .card img{
  max-width: 300px;
  max-height: 300px;
  object-fit: cover;
 
*/

.loading {
  flex: 3;
}



/*
***The styles below are exclusively for the Pittsburgh folding photo animation at the bottom
*/

@-webkit-keyframes crunch {
  50% {
    transform: scaleX(0.6);
  }
}

@keyframes crunch {
  50% {
    transform: scaleX(0.6);
  }
}

.pghCard {
  display: flex;
  width: 95vw;
  height: 22.5625vw;
}

.fold {
  flex: 1;
  background: url(https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/Pittsburgh_Panorama_from_the_Duquesne_Incline.jpg/1280px-Pittsburgh_Panorama_from_the_Duquesne_Incline.jpg);
  background-size: cover;
  -webkit-animation: 4s 1s ease-in-out infinite;
          animation: 4s 1s ease-in-out infinite;
}
.fold:nth-child(1) {
  background-position: 0%;
}
.fold:nth-child(2) {
  background-position: 25%;
}
.fold:nth-child(3) {
  background-position: 50%;
}
.fold:nth-child(4) {
  background-position: 75%;
}
.fold:nth-child(5) {
  background-position: 100%;
}
.fold:first-child {
  border-radius: 2vw 0 0 2vw;
}
.fold:last-child {
  border-radius: 0 2vw 2vw 0;
}
.fold:nth-child(odd) {
  -webkit-animation-name: odd-fold;
          animation-name: odd-fold;
}
@-webkit-keyframes odd-fold {
  50% {
    transform: skewY(15deg);
    filter: brightness(1.25);
  }
}
@keyframes odd-fold {
  50% {
    transform: skewY(15deg);
    filter: brightness(1.25);
  }
}
.fold:nth-child(even) {
  -webkit-animation-name: even-fold;
          animation-name: even-fold;
}
@-webkit-keyframes even-fold {
  50% {
    transform: skewY(-15deg);
    filter: brightness(0.75);
  }
}
@keyframes even-fold {
  50% {
    transform: skewY(-15deg);
    filter: brightness(0.75);
  }
}
</style>