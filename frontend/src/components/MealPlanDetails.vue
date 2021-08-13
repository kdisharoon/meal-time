<template>
  <div class="meal-plan-details">

    <div class="loading" v-if="isLoading">
      <img src="../assets/giphy.gif" />
    </div>

    <h2 style="text-align: center">{{ mealPlan.mealPlanName }}</h2>

    <div id="mealPlanButts">
      <div class="rename-meal-plan-wrapper" v-if="mealPlan.mealPlanId > 0">
        <button id="btnRenameMealPlan" type="button" class="btn" v-on:click="flipRevealButton('btnRenameMealPlan'); flipRevealButton('isHiding')">
          Rename Your Meal Plan
        </button>
      </div>
      <div class="rename-meal-plan-wrapper" v-if="mealPlan.mealPlanId > 0">
        <button id="clearMealPlanButt" type="button" class="btn" v-on:click="clearMealPlan">
          Clear Your Meal Plan
        </button>
      </div>
    </div>

    <div class="clearfix" v-if="mealPlan.mealPlanId === 0">
      <button id="btnCreateMealPlan" type="button" class="btn" v-on:click="flipRevealButton('btnCreateMealPlan'); flipRevealButton('isHiding')">
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
            <input type="text" id="mname" name="mealplanname" v-model="mealPlan.mealPlanName" />
          </div>
        </div>
          
        <button id="btnSubmitName" type="submit" class="btn">
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
    <!-- if a meal plan has been created, mealPlanId will be greater than 0 -->
    <div id="plan-cards-wrapper" v-if="mealPlan.mealPlanId > 0">                  
      <div v-for="dayMeal in mealPlan.recipes" v-bind:key="dayMeal.day + dayMeal.meal" class="day-meal">
          <div class="day">{{ dayMeal.meal.charAt(0).toUpperCase() + dayMeal.meal.slice(1) }}
            <ul>
              <div class="recipe-name-display" v-for="rname in dayMeal.recipeNames" v-bind:key="rname">
                <li>
                  <router-link v-bind:to="{ name: 'recipe', params: { recipeID: dayMeal.recipeIds[dayMeal.recipeNames.indexOf(rname)] } }">
                    <br> {{ rname }} 
                  </router-link>
                </li>
              </div>
            </ul>
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
      isLoading: true,
      mealPlan: {
          mealPlanId: 0,
          userId: this.$route.params.userID,
          mealPlanName: "",
          recipes: [
              {
                day: "",
                meal: "",
                recipeNames: [],
                recipeIds: []

              }
          ]
      },
    
    }
  },

  methods: {

    clearMealPlan() {
      mealPlanService.clearUserMealPlan(this.$store.state.user.id).then(response => {
        if (response.status === 204) {
          console.log("Successfully cleared meal plan");
        }
        this.$router.go();
      })
    },

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
      })
      .catch((error) => {
        console.log(error);
      });
    },

    flipRevealButton(id){
      if (document.getElementById(id).hasAttribute("hidden")) {
        document.getElementById(id).removeAttribute("hidden");
      }
      else {
        document.getElementById(id).setAttribute("hidden", "");
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
          this.flipRevealButton('btnRenameMealPlan');
          this.flipRevealButton('isHiding');
        }
      })
      .catch((error) => {
        console.log(error);
      });
    },
    
  },

  created() {                                     
    mealPlanService.getUserMealPlanById(this.$store.state.user.id).then(response => {
      console.log(response.data);
      if (response.data.mealPlanId > 0) {
        this.mealPlan = response.data;
      }
      this.isLoading = false;
    });
  },
  
}

</script>

<style>
ul{
  list-style-type: none;
  padding-inline-start: 0px;

}
.clearfix{
  display: flex;
  justify-content: center;
}
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