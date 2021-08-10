<template>
  <div class="meal-plan-details">

    <h2 style="text-align: center">{{mealPlan.mealPlanName}}</h2>



      
    <div class="user-meal-plans-list d-flex justify-content-around">

      <div class="clearfix" v-if="mealPlan.mealPlanId === 0">
        <button id="btnCreateMealPlan" type="button" class="btn" v-on:click="flipRevealButton">
          Create Your Meal Plan
        </button>
      </div>

      <div class="rename-meal-plan-wrapper" v-if="mealPlan.mealPlanId > 0">
        <button id="btnRenameMealPlan" type="button" class="btn" v-on:click="flipRevealButton">
          Rename Your Meal Plan
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
            <div id="sunday">Sunday
              <div id="breakfast">Breakfast<div>recipes go here</div></div>
              <div id="lunch">Lunch<div>recipes go here</div></div>
              <div id="dinner">Dinner<div>recipes go here</div></div>
            </div>
           <div id="monday">Monday
              <div id="breakfast">Breakfast<div>recipes go here</div></div>
              <div id="lunch">Lunch<div>recipes go here</div></div>
              <div id="dinner">Dinner<div>recipes go here</div></div>
            </div>
            <div id="tuesday">Tuesday
              <div id="breakfast">Breakfast<div>recipes go here</div></div>
              <div id="lunch">Lunch<div>recipes go here</div></div>
              <div id="dinner">Dinner<div>recipes go here</div></div>
            </div>
            <div id="wednesday">Wednesday
              <div id="breakfast">Breakfast<div>recipes go here</div></div>
              <div id="lunch">Lunch<div>recipes go here</div></div>
              <div id="dinner">Dinner<div>recipes go here</div></div>
            </div>
            <div id="thursday">Thursday
              <div id="breakfast">Breakfast<div>recipes go here</div></div>
              <div id="lunch">Lunch<div>recipes go here</div></div>
              <div id="dinner">Dinner<div>recipes go here</div></div>
            </div>
            <div id="friday">Friday
              <div id="breakfast">Breakfast<div>recipes go here</div></div>
              <div id="lunch">Lunch<div>recipes go here</div></div>
              <div id="dinner">Dinner<div>recipes go here</div></div>
            </div>
            <div id="saturday">Saturday
              <div id="breakfast">Breakfast<div>recipes go here</div></div>
              <div id="lunch">Lunch<div>recipes go here</div></div>
              <div id="dinner">Dinner<div>recipes go here</div></div>
            </div>
          </div>
       
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
#sunday{
  background-color: aliceblue;
  display: flex;
  flex-direction: column;
  border: 1px solid black;
  border-radius: 3px;
  text-align: center;
}
#monday{
  background-color: aliceblue;
  display: flex;
  flex-direction: column;
  border: 1px solid black;
  border-radius: 3px;
  text-align: center;
}
#tuesday{
  background-color: aliceblue;
  display: flex;
  flex-direction: column;
  border: 1px solid black;
  border-radius: 3px;
  text-align: center;
}
#wednesday{
  background-color: aliceblue;
  display: flex;
  flex-direction: column;
  border: 1px solid black;
  border-radius: 3px;
  text-align: center;
}
#thursday{
  background-color: aliceblue;
  display: flex;
  flex-direction: column;
  border: 1px solid black;
  border-radius: 3px;
  text-align: center;
}
#friday{
  background-color: aliceblue;
  display: flex;
  flex-direction: column;
  border: 1px solid black;
  border-radius: 3px;
  text-align: center;
}
#saturday{
  background-color: aliceblue;
  display: flex;
  flex-direction: column;
  border: 1px solid black;
  border-radius: 3px;
  text-align: center;
}
#breakfast{
text-align: left;
text-decoration: underline;
}
#lunch{
  text-align: left;
  text-decoration: underline;
}
#dinner{
  text-align: left;
  text-decoration: underline;
}

#daysOfWeek{
  display: flex;
  justify-content: space-evenly;
  margin: 10px;
}

</style>