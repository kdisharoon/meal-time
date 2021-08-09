<template>
  <div class="meal-plan-details">
   
    
    <div v-for="thisMeal in mealPlan.recipes.slice(0,1)" v-bind:key="thisMeal.aFakeNumber" class="meal-plan-meal">
       <div id="dayTwo" v-for= "day in days" :key="day.name"> {{ day }} <br> 
       <div id="breakfast" ><h6>Breakfast</h6></div>
       <div id="placeholder">recipes go here</div>
       <div id="lunch" ><h6>Lunch</h6></div>
       <div id="placeholder">recipes go here</div>
       <div id="dinner"><h6>Dinner</h6></div>
       <div id="placeholder">recipes go here</div>
       
        <div v-for="recipeID in thisMeal.recipeIds" v-bind:key="recipeID" class="recipe"> 
          <p> {{ getRecipeName(recipeID) }} </p>
<!-- add a link to the recipe details page to each recipe's name -->         
        </div>
    </div>
  </div>
  </div>
</template>

<script>
import mealPlanService from '../services/MealPlanService';
import recipeService from '../services/RecipeService';

export default {
  name: 'meal-plan-details',

  data() {
    return {
      mealPlan: {
          mealPlanId: 0,
          userId: 0,
          mealPlanName: "",
          organizedRecipes: [
              {
                day: "",
                meal: "",
                recipeIds: []
              }
          ]
      },
      days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"]
    }
  },

  methods: {
    getRecipeName(id) {
      recipeService.getRecipeById(id).then(response => {
        console.log(response.data.recipeName);
        return response.data.recipeName; 
      });  
    },


  },

  created() {                                     //instead of mealPlanID
    mealPlanService.getUserMealPlanById(this.$route.params.userID).then(response => {
      console.log(response.data);
      this.mealPlan = response.data;
    })
  },
  


  
}
</script>

<style>
.meal-plan-meal{
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  margin-top: 10px;
}

#dayTwo{
  background-color: aliceblue;
  display: grid;
  grid-auto-columns: 200px;
  height:500px;
  width: 200px;
  justify-content: center;
  
  border:1px solid black;
  border-radius: 8px;
  text-decoration: underline;
  padding-left: 5px;
}

#breakfast{
  margin: auto;
 
}
#lunch{
  margin: auto;
}
#dinner{
  margin: auto;
}

</style>