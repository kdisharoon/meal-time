<template>
  <div class="meal-plan-details">

    <h2 style="text-align: center">{{mealPlan.mealPlanName}}</h2>



      
    <div class="user-meal-plans-list d-flex justify-content-around">

      <div class="clearfix" v-if="mealPlan.mealPlanId === 0">
        <button id="btnCreateMealPlan" type="button" class="btn" v-on:click="flipRevealButton">
          Create Your Meal Plan
        </button>
      </div>

      
     

      <div hidden id="isHiding" class="container mealPlanStyle">
        <form v-on:submit.prevent="checkAddMealPlan" id="planForm">
          <div class="card">
            <div class="col-25">
              <label for="fname">Meal Plan Name</label>
            </div>
            <div class="col-75">
              <input type="text" id="mname" name="mealplanname" v-model.lazy="mealPlan.mealPlanName" />
            </div>
          </div>
          <div class="row">
            <button id="plan-name-submit-button" type="submit" class="btn" v-on:click="flipRevealButton">
              {{mealPlan.mealPlanId > 0 ? "Rename" : "Create"}} Meal Plan
            </button>
          </div>
        </form>
      </div>


    </div>





    <div id="plan-cards-wrapper" v-if="mealPlan.mealPlanId > 0">
      <div v-for="thisMeal in mealPlan.recipes.slice(0,1)" v-bind:key="thisMeal.aFakeNumber" class="meal-plan-meal">
          <div id="daysOfWeek">
            <div class="day">Sunday
              <div class="mealtime">
              <div class="breakfast">Breakfast<div>recipes go here </div></div>
              <div class="lunch">Lunch<div>recipes go here</div></div>
              <div class="dinner">Dinner<div>recipes go here</div></div>
            </div>
            </div>
           <div class="day">Monday
             <div class="mealtime">
              <div class="breakfast">Breakfast<div>recipes go here</div></div>
              <div class="lunch">Lunch<div>recipes go here</div></div>
              <div class="dinner">Dinner<div>recipes go here</div></div>
            </div>
            </div>
            <div class="day">Tuesday
              <div class="mealtime">
              <div class="breakfast">Breakfast<div>recipes go here</div></div>
              <div class="lunch">Lunch<div>recipes go here</div></div>
              <div class="dinner">Dinner<div>recipes go here</div></div>
            </div>
            </div>
            <div class="day">Wednesday
              <div class="mealtime">
              <div class="breakfast">Breakfast<div>recipes go here</div></div>
              <div class="lunch">Lunch<div>recipes go here</div></div>
              <div class="dinner">Dinner<div>recipes go here</div></div>
            </div>
            </div>
            <div class="day">Thursday
              <div class="mealtime">
              <div class="breakfast">Breakfast<div>recipes go here</div></div>
              <div class="lunch">Lunch<div>recipes go here</div></div>
              <div class="dinner">Dinner<div>recipes go here</div></div>
            </div>
            </div>
            <div class="day">Friday
              <div class="mealtime">
              <div class="breakfast">Breakfast<div>recipes go here</div></div>
              <div class="lunch">Lunch<div>recipes go here</div></div>
              <div class="dinner">Dinner<div>recipes go here</div></div>
            </div>
            </div>
            <div class="day">Saturday
              <div class="mealtime">
              <div class="breakfast">Breakfast<div>recipes go here</div></div>
              <div class="lunch">Lunch<div>recipes go here</div></div>
              <div class="dinner">Dinner<div>recipes go here</div></div>
              </div>
            </div>
          </div>
       
          <div v-for="recipeID in thisMeal.recipeIds" v-bind:key="recipeID" class="recipe"> 
            <p> {{ getRecipeName(recipeID) }} </p>
            <!-- add a link to the recipe details page to each recipe's name -->         
          </div>
       
      </div>
    </div>
    <div id="mealPlanButts">
<div class="rename-meal-plan-wrapper" v-if="mealPlan.mealPlanId > 0">
        <button id="btnRenameMealPlan" type="button" class="btn" v-on:click="flipRevealButton">
          Rename Your Meal Plan
          </button>
      </div>
      
 <div class="rename-meal-plan-wrapper" v-if="mealPlan.mealPlanId > 0">
        <button id="btnRenameMealPlan" type="button" class="btn" v-on:click="flipRevealButton">
          Clear your meal plan
          </button>
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
      showForm: true,
      showPlanCards: false,
      mealPlan: {
          mealPlanId: 0,
          userId: this.$route.params.userID,
          mealPlanName: "",
          organizedRecipes: [
              {
                day: "",
                meal: "",
                recipeIds: []
              }
          ]
      },
    
    }
  },

  methods: {

    checkAddMealPlan() {
      if (this.mealPlan.mealPlanId === 0) {
        this.addMealPlan();
        mealPlanService.getUserMealPlanById(this.$store.state.user.id).then(response => {
          console.log(response.data);
          this.mealPlan = response.data;
        })
      }
      else {
        this.renameMealPlan(this.mealPlan.mealPlanName);
      }
    },

    getRecipeName(id) {
      recipeService.getRecipeById(id).then(response => {
        console.log(response.data.recipeName);
        return response.data.recipeName; 
      });  
    },

    flipRevealButton(){
      if (document.getElementById("isHiding").hasAttribute("hidden")) {
        document.getElementById("isHiding").removeAttribute("hidden");
      }
      else {
        document.getElementById("isHiding").setAttribute("hidden", "");
      }
    },

    addMealPlan() {
      mealPlanService.addUserMealPlan(this.mealPlan).then(response => {
        if (response.status === 201) {
          alert("Successfully named your meal plan!");
          this.$router.go();          
        }
      })
      .catch((error) => {
        console.log(error);
      });
      
      this.showForm = false;
      this.showPlanCards = true;

    },

    renameMealPlan(newName) {
      mealPlanService.renameMealPlan(this.$store.state.user.id, newName).then(response => {
        if (response.status === 204) {
          alert ("Successfully renamed your meal plan!");
        }
      })
      .catch((error) => {
        console.log(error);
      });
    }




  },

  created() {                                     
    mealPlanService.getUserMealPlanById(this.$store.state.user.id).then(response => {
      console.log(response.data);
      if (response.data.mealPlanId > 0) {
        this.mealPlan = response.data;
      }
    })
  },
  


  
}
</script>

<style>
#mealPlanButts{
  display: flex;
  justify-content: space-around;
}
.day{
  background-color: aliceblue;
  display: flex;
  font-size: 20px;
  flex-direction: column;
  border: 1px solid black;
  border-radius: 3px;
  text-align: center;
  height: 400px;
  width: 200px;
  text-decoration: underline;
}
.mealtime{
  display:grid;
  grid-template-rows: 125px 125px 125px;
  padding:5px;
  text-align: left;
  max-width: 180px;
}

#daysOfWeek{
  display: flex;
  justify-content: space-evenly;
  margin: 20px;
  margin-top: 75px;
  flex-wrap: wrap;
  text-align: left;
}

</style>