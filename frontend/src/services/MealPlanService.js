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

    renameMealPlan(userID, newName) {
        return axios.put(`/users/${userID}/mealplans?mealPlanName=${newName}`);
    }

}