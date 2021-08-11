<template>
<div id="full">
  <div class="loading" v-if="isLoading">
        <img src="../assets/giphy.gif" />
      </div>
  
  <h2 id="allRecipe">All Recipes</h2>

  <div class="lds-ripple" v-if="isLoading">
    <div></div><div></div>
  </div>
  
  <div  v-else>
    <button hidden v-on:click.prevent="getNewRecipesFromAPI" class="addRecipes">Add 10 Recipes to Database from External Source</button>
    
   <!-- Load an icon library -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 
  <form id="allSearch" action="">
     <div class="form-group">
       <input type="text" placeholder="Search By Recipe Name" v-model="userSearchTerm">
      <button class="searchButton" v-on:click.prevent="searchForRecipeName(userSearchTerm)" type="submit">
        <i class="fa fa-search"></i>
      </button>
      <button id="resetButt" class="reset-filter" v-if="showResetButton" v-on:click.prevent="resetFilter">
        Reset Search
      </button>
      
    </div>
  </form>
    
    
    <div class="container card-deck">
      <div class="row">
        <div class="all-recipes-list d-flex justify-content-around flex-wrap">
    

          <div v-for="recipe in recipes" v-bind:key="recipe.recipeId" class="recipe thumbnail">
            <div class="card" style="width: 18rem;">
              <img v-bind:src="recipe.recipeImg" class="recipe-image" style="width:100%">
                <h4 id="rTwo"><b>{{recipe.recipeName}}</b></h4>
                <p class="card-text">{{ recipe.cookTime }} minutes total time</p>
                <router-link v-bind:to="{ name: 'recipe', params: { recipeID: recipe.recipeId } }">
                  <button>Recipe Details</button>
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
      filteredRecipes: [],
      backupRecipes: [],
      showResetButton: false,
      userSearchTerm: '',
      isLoading: true,
      recipes: [],
    }
  },
  

  methods: {

    resetFilter() {
      this.recipes = this.backupRecipes;
      this.backupRecipes = [];
      this.filteredRecipes = [];
      this.showResetButton = false;
    },


    searchForRecipeName(userSearchTerm) {
      if(typeof userSearchTerm !== 'string' || userSearchTerm.length === 0) {
        return false;
      }
      let lowerCase = userSearchTerm.toLowerCase();
      console.log(userSearchTerm);
      console.log(lowerCase);
      console.log(this.recipes);
      this.filteredRecipes = this.recipes.filter(recipe => {
        if(recipe.recipeName.toLowerCase().includes(lowerCase)){
          return true;
        }
        return false;
      });
      this.backupRecipes = this.recipes;
      this.recipes = this.filteredRecipes;
      this.showResetButton = true;
    
    },

  

    saveToDatabase(recipesToAdd) {
      recipeService.addRecipesFromAPIToDatabase(recipesToAdd).then(response => {
        if (response.status === 201) {
          alert("Successfully added recipes to database from API!");
          this.$router.go();
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
    
  },
}
</script>

<style>
#resetButt{
  display: flex;
  align-items: center;
  height: 30px;
  
}
/* .all-recipes-list {
    background:white;
    max-width: 300px;
    margin: 30px auto;
    padding: 10px;
    border: 2px solid black;
    border-radius: 5px; 
  } */

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
 
}

  #allRecipe{
    text-align: center;
  }
  #rTwo{
text-align: center;
   text-overflow: ellipsis;
   white-space: nowrap;
   width: 250px;
   overflow: hidden;
   
  }
  #rTwo:hover{
text-align: center;
  white-space: normal;
  text-overflow: initial;
  }
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

  .card-text {
    text-align: center;
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
  #allSearch{
    display: flex;
    align-content: center;
    justify-content: center;
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


.addRecipes{
  margin:auto;
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

.loading {
  display: flex;
  justify-content: center;
  align-items: center;
}
  

</style>

