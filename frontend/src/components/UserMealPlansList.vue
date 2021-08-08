<template>
  <div>
    <h2>My Meal Plans</h2>
    <div class="user-meal-plans-list d-flex justify-content-around">
      <div v-for="mealPlan in mealPlans" v-bind:key="mealPlan.mealPlanId" class="meal-plan">
        <div class="card">
          <router-link class="userMealPlanButon" v-bind:to="{ name: 'meal-plan', params: { userID: mealPlan.userId, mealPlanID: mealPlan.mealPlanId } }">
            <button>
              {{ mealPlan.mealPlanName }}
            </button>
          </router-link>
        </div>
      </div>
      <div class="clearfix">
      <button id="btnToggleForm" type="button" class="btn" v-on:click.prevent="addMealPlan">
          Add a Meal Plan
      </button>
      </div>
      

<div class="container mealPlanStyle">
  <form v-on:submit.prevent="addMealPlan" id="planForm">
  <div class="card">
    <div class="col-25">
      <label for="fname">Meal Plan Name</label>
    </div>
    <div class="col-75">
      <input type="text" id="mname" name="mealplanname" v-model="newPlan.mealPlanName" />
    </div>
  </div>
  <div class="row">
    <input class="btn" type="submit" value="Create Meal Plan">
  </div>
  </form>
</div>


    </div>
  </div>
</template>

<script>
import mealPlanService from '../services/MealPlanService';

export default {
  name: 'user-meal-plans-list',
  data() {
    return {
      mealPlans: [],
      newPlan: {
        mealPlanName: "",
        userId: this.$route.params.userID,
      }
    }
  },
  methods: {
    addMealPlan() {
      mealPlanService.addUserMealPlan(this.$route.params.userID, this.newPlan).then(response => {
        if (response.status === 201) {
          alert("Successfully added new meal plan to your meal plans!");
          this.$router.go();          
        }
      })
      .catch((error) => {
        console.log(error);
      });
      this.resetForm();
    },

    resetForm() {
  //    this.showForm = false;
      this.newPlan.mealPlanName = ""
    }

  },
  created() {
    mealPlanService.getAllUserMealPlans(this.$route.params.userID).then(response => {
      this.mealPlans = response.data;
    });
  },
}
</script>

<style>


input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

label {
  padding: 12px 12px 12px 0;
  text-align: center;
}

/* input[type=submit] {
  background-color: orange;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  float: right;
}

input[type=submit]:hover {
  background-color: orange;
} */

.mealPlanStyle {
  max-width: 300px;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
  border: solid black 2px;
}



.col-75 {
  float: left;
  width: 75%;
  margin-top: 6px;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}

@media screen and (max-width: 600px) {
  .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
  }
}
</style>