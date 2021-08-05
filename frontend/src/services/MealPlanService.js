import axios from 'axios';


export default {

    addUserMealPlan(userID) {
        return axios.post(`/users/${userID}/mealplans`)
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