<template>
<div class="container rpDetails">
  
 
<div class="row">
  <div class="recipe-details">
    <h1 class="recipe-name">{{ recipe.name }}</h1>
    <img v-bind:src="recipe.imageURL" class="recipe-detail-image" />
    <h3>{{ recipe.cookTime }} minutes total cook time</h3>
    
    <div class="grouped-ingredients">
      <h5>Ingredients</h5>
    <div class="recipe-ingredients" v-for="ingredient in recipe.ingredients" v-bind:key="ingredient.ingredientId">
      <h4 id="ingedInfo">{{ ingredient.measurementAmount }} {{ ingredient.measurementUnit }} {{ ingredient.ingredientName}}</h4>
    </div>
    </div>
    <ol>
      <h2>Instructions</h2>
      <li v-for="step in recipe.preparation" v-bind:key="step" class="recipe-step">
        {{ step }}
      </li>
    </ol>

 

   <div id="butt"> 
 <button class="btn btn-add-recipe-to-user-library" v-on:click.prevent="saveRecipe">Save Recipe To My Library</button>
<button class="btn btn-add-recipe-to-user-library"  v-on:click="revealForm()">Add Recipe to Meal Plan</button>
<button class="btn btn-add-recipe-to-user-library">Add Ingredients to Grocery List</button>


</div>
<div hidden id="hidden-form">
  <form @submit.prevent="submitForm">
    <div>
    
  
    </div>
    <div>
      <label for="dayofweek">Day of Week</label><br>
      <select id="day-of-week" name="daysWeek">
    <option value="monday">Monday</option>
    <option value="tuesday">Tuesday</option>
    <option value="wednesday">Wednesday</option>
    <option value="thursday">Thursday</option>
    <option value="friday">Friday</option>
    <option value="saturday">Saturday</option>
    <option value="sunday">Sunday</option>
  </select>
    </div>
    <div>
      <label for="mealtime">Meal Time</label><br>
      <select id="meal-time" name="meal-time">
    <option value="breakfast">Breakfast</option>
    <option value="lunch">Lunch</option>
    <option value="dinner">Dinner</option>
    
  </select>
    </div>
    <button  v-on:click.prevent="addToMealPlan" type="submit">Submit</button>
    
  </form>
</div>

</div>

  </div>
 </div>
</template>

<script>
import mealPlanService from '../services/MealPlanService'
import recipeService from '../services/RecipeService'

export default {
  name: 'recipe-details',

  data() {
    return {
      recipe: {
        id: 0,
        name: '',
        preparation: [],
        prepTime: 0,
        cookTime: 0,
        imageURL: '',
        mealType: '',
        ingredients: []
      },
        mealPlans: []
    }
  },
  computed:{
    options: () => mealPlanService,
    // options(){
    //   return Object.keys(this.mealPlan).map(k => {
    //     let o = this.mealPlan[k]
    //     return `${o.mealPlanName}`
    //   })
    // }
  },
  methods: {
    changeMealPlan(event){
      this.toMealPlan = event.target.options[event.target.options.selectedIndex].text
    },
    addToMealPlan() {

      // add a popup or menu to ask the user to choose which of their meal plans to add to!
      // then the popup or menu should ask which meal (date and breakfast/lunch/dinner) to add the recipe to.
      // the id and meal variables hard-coded below are just temporary.

      let planID = 2;
      let whichDateTime = {
        day: 'tuesday',
        meal: 'lunch',
        recipeIDs: [this.recipe.id]
      };
      

      mealPlanService.addRecipeToUserMealPlan(planID, this.recipe.id, whichDateTime).then(response => {
        if (response.status === 201) {
          this.$router.push({ name: 'meal-plan', params: { mealPlanID: planID } });
        }
      });
    },

    revealForm(){
      document.getElementById("hidden-form").innerHTML;
      document.getElementById("hidden-form").removeAttribute("hidden");
    },


    saveRecipe() {
      recipeService.addRecipeToUserLibrary(this.$store.state.user.id, this.recipe.id).then(response => {
        if (response.status === 201) {
          this.$router.push({name: 'saved-recipes', params: { userID: this.$store.state.user.id } });
          alert("Successfully Added!")
        }
      })
      .catch((error) => {
        alert("This recipe is already in your library");
        console.log(error);
        console.log("This recipe is already saved to your recipes!");
      });
    }
  },

  created() {
    recipeService.getRecipeById(this.$route.params.recipeID).then(response => {
      this.recipe.id = response.data.recipeId;
      this.recipe.name = response.data.recipeName;
      this.recipe.preparation = response.data.preparation.split('. ');
      this.recipe.prepTime = response.data.prepTime;
      this.recipe.cookTime = response.data.cookTime;
      this.recipe.imageURL = response.data.recipeImg;
      this.recipe.mealType = response.data.type;
      this.recipe.ingredients = response.data.ingredients;
    })
    
     mealPlanService.getAllUserMealPlans(this.$route.params.userID).then(response => {
      this.mealPlans = response.data;
    });
    
  }

}
</script>

<style>

#hidden-form{
  z-index: 10;
}
.rpDetails {
  border: solid black 5px;
  background: white;
}

.recipe-name {
  border: solid orange 10px;
  border-style: outset;
  padding: 5px;
  display: block;
  text-align: center;
}

.grouped-ingredients {
  padding-left: 25px;
  display: flex;
  flex-direction: column;
  
}

/* .recipe-ingredients {
  display:list-item;
} */

#ingredInfo {
  text-indent: 10px;
}

.recipe-details {
  padding: 50px;
}

h1 {
  display: inline;
  font-size: 50px;
  margin: 10px;
}
ol {
  display:flex;
  flex-flow: column;
}

li {
  font-size: 20px;
  border-style: outset;
}
#butt{
  display: flex;
  justify-content: space-around;
}
.recipe-detail-image {
  /* object-fit: contain; */
  /* display: block;
  padding: 5px;
  min-height: 500px;
  width: auto;
  max-width: 500px; */
  float: left;
  width:  400px;
  height: 300px;
  object-fit: cover;
}

h3{
  border: solid orange  5px;
  border-style: outset;
  display: inline-block;
  margin-left: 10px;
  margin-top: 25px;
}
h4{
  text-align: left;
}
h2 {
  font-size: 35px;
  text-decoration: underline;
}

h5 {
  font-size: 35px;
  text-decoration: underline;
  
}

.btn {
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
  
  .btn:hover {
    background-color:rgba(255, 166, 0, 0.626);
    border-radius: 10px;
  }
  
  .btn:active {
    background-color:orange;
    box-shadow: 0 5px #666;
    transform: translateY(4px);
  }
</style>