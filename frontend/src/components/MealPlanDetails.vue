<template>
  <div class="meal-plan-details">

    <h2 style="text-align: center">{{mealPlan.mealPlanName}}</h2>

  <div id="mealPlanButts">
      <div class="rename-meal-plan-wrapper" v-if="mealPlan.mealPlanId > 0">
        <button id="btnRenameMealPlan" type="button" class="btn" v-on:click="flipRevealButton">
          Rename Your Meal Plan
        </button>
      </div>
      
 <div class="rename-meal-plan-wrapper" v-if="mealPlan.mealPlanId > 0">
        <button id="clearMealPlanButt" type="button" class="btn" >
          Clear your meal plan
          </button>
      </div>
    </div>

      
   

      <div class="clearfix" v-if="mealPlan.mealPlanId === 0">
        <button id="btnCreateMealPlan" type="button" class="btn" v-on:click="flipRevealButton">
          Create Your Meal Plan
        </button>
      </div>

      
     

      <div hidden id="isHiding" class="container mealPlanStyle">
        <form v-on:submit.prevent="checkAddMealPlan" id="planForm">
          <div class="card" id="mealCardName">
            <div class="col-25">
              <label for="fname">Meal Plan Name</label>
            </div>
            <div class="col-75">
              <input type="text" id="mname" name="mealplanname" v-model.lazy="mealPlan.mealPlanName" />
            </div>
          </div>
          
            <button id="plan-name-submit-button" type="submit" class="btn" v-on:click="flipRevealButton">
              {{mealPlan.mealPlanId > 0 ? "Rename" : "Create"}} Meal Plan
            </button>
          
        </form>
      </div>





<div class="dayTitles">
 <div> 
   <h2 class="dtitle">Sunday</h2> 
   </div>
   <div>
     <h2 class="dtitle">Monday</h2>
     </div>
     <div>
     <h2 class="dtitle">Tuesday</h2>
     </div>
     <div>
     <h2 class="dtitle">Wednesday</h2>
     </div>
     <div>
     <h2 class="dtitle">Thursday</h2>
     </div>
     <div>
     <h2 class="dtitle">Friday</h2>
     </div>
     <div>
     <h2 class="dtitle">Saturday</h2>
     </div>
</div>

    <div id="plan-cards-wrapper" v-if="mealPlan.mealPlanId > 0">                  <!-- if a meal plan has been created, mealPlanId will be greater than 0 -->
      <div v-for="dayMeal in mealPlan.recipes" v-bind:key="dayMeal" class="day-meal">    <!-- goes through all 21 day-meal combinations in the mealPlan object -->
          <div class="day">{{ dayMeal.meal.charAt(0).toUpperCase() +dayMeal.meal.slice(1) }}     <!-- prints the day of the week "Wednesday" and meal "breakfast" at top of each card -->
            <div class="recipe-name-display" v-for="rid in dayMeal.recipeIds" v-bind:key="rid">
              {{ rid }} {{ getRecipeName(id) }}
            
            </div>
              
            
          </div>
       

    
       
      </div>
    </div>


  

    <div hidden id="isHiding" class="container">
        <form v-on:submit.prevent="checkAddMealPlan" id="planForm">
          <div class="card">
            <div class="col-25">
              <label for="fname">Meal Plan Name</label>
            </div>
            <div class="col-75">
              <input type="text" id="mname" name="mealplanname" v-model.lazy="mealPlan.mealPlanName" />
            </div>
          </div>
          <div>
            <button id="plan-name-submit-button" type="submit" class="btn" v-on:click="flipRevealButton">
              {{mealPlan.mealPlanId > 0 ? "Rename" : "Create"}} Meal Plan
            </button>
          </div>
        </form>
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
      currentMealName: "OOO",

      mealPlan: {
          mealPlanId: 0,
          userId: this.$route.params.userID,
          mealPlanName: "",
          recipes: [
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
        this.currentMealName = response.data.recipeName; 
      })
      .catch((error) => {
        console.log(error);
      });
    },

    flipRevealButton(){
      document.getElementById("btnCreateMealPlan").setAttribute("hidden", "");

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
.dtitle{
  font-size: 28px;
  
}
.dayTitles{
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
  margin-left:13vh;
  margin-right:13vh;
  flex-wrap: nowrap;
  position:relative;
}
#mname{
  border-radius: 5px;
}
#planForm{
  display: flex;
  flex-direction: column;
  align-items: center;
}
#mealCardName{
  display: flex;
  align-items: center;
  width:300px;
}
#mealPlanButts{
  display: flex;
  justify-content: space-around;
}
.day{
  position: relative;
  background-color: aliceblue;
  display: flex;
  font-size: 20px;
  flex-direction: column;
  border: 1px solid black;
  border-radius: 3px;
  text-align: center;
  height: 300px;
  width: 200px;
  text-decoration: none;
  margin-left: 10px;
  transition: transform 2s ease-in-out;
}
.day:hover{
transform: scale(1.5);
z-index: 1;
}
.mealtime{
  display:grid;
  grid-template-rows: 125px 125px 125px;
  padding:5px;
  text-align: left;
  max-width: 180px;
}
.meal-plan-details{
  display: flex;
  flex-direction: column;
}
#plan-cards-wrapper{
  display: flex;
  text-decoration: underline;
  margin: 20px;
  margin-top: 40px;
  flex-wrap: wrap;
  text-align: left;
  height: 60rem;
  width: auto;
  flex-direction: column;
}
#plan-name-submit-button{
  width: 200px;
  
}

</style>