import axios from 'axios';


export default {

    addUserMealPlan(newMealPlan) {
        return axios.post(`/users/mealplans`, newMealPlan)
    },

    getUserMealPlanById(userID) {
        return axios.get(`/users/${userID}/mealplans`);
    },

    addRecipeToUserMealPlan(userID, recipeID, dayAndMeal) {
        return axios.post(`/users/${userID}/mealplans/recipes/${recipeID}`, dayAndMeal);
    },

    deleteRecipeFromUserMealPlan(userID, recipeID){
        return axios.post(`/users/${userID}/mealplans/recipes/${recipeID}`);
    },

    clearUserMealPlan(userID) {
        return axios.delete(`/users/${userID}/mealplans`);
    },

    renameMealPlan(userID, newName) {
        return axios.put(`/users/${userID}/mealplans?mealPlanName=${newName}`);
    }

}