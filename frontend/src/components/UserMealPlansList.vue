<template>
  <div>
    <h2>My Meal Plans</h2>
    <div class="user-meal-plans-list d-flex justify-content-around">
      <div v-for="mealPlan in mealPlans" v-bind:key="mealPlan.mealPlanId" class="meal-plan">
        <div class="card">
          <router-link v-bind:to="{ name: 'meal-plan', params: { userID: mealPlan.userId, mealPlanID: mealPlan.mealPlanId } }">
            <button class="add-a-class-here-to-style-this-button-please-and-thank-you">
              {{ mealPlan.mealPlanName }}
            </button>
          </router-link>
        </div>
      </div>
      <button class="add-a-class-here-to-style-this-button-please-and-thank-you">
          {{ addMealPlan }}
      </button>

    </div>
  </div>
</template>

<script>
import mealPlanService from '../services/MealPlanService';

export default {
  name: 'user-meal-plans-list',
  data() {
    return {
      mealPlans: []
    }
  },
  methods: {
    addMealPlan() {
      mealPlanService.addUserMealPlan(this.$route.params.userID).then(response => {
        if (response.status === 201) {
          this.$router.push(`/users/${this.$route.params.userID}/mealplans}`);
          }
        })
      .catch((error) => {
        console.log(error);
      });
    }
  },
  created() {
    mealPlanService.getAllUserMealPlans(this.$route.params.userID).then(response => {
      this.mealPlans = response.data;
    });
  }
}
</script>