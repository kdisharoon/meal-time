import axios from 'axios';


export default {

    addUserMealPlan(userID, newMealPlan) {
        return axios.post(`/users/${userID}/mealplans`, newMealPlan)
    },

    getAllUserMealPlans(userID) {
        return axios.get(`/users/${userID}/mealplans`);
    },

    getUserMealPlanById(mealPlanID) {
        return axios.get(`/mealplans/${mealPlanID}`);
    },

    addRecipeToUserMealPlan() {
        return axios.post(`/mealplans/recipes`);
    }

}