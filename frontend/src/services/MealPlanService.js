import axios from 'axios';


export default {

    addUserMealPlan(userID, newMealPlan) {
        return axios.post(`/users/${userID}/mealplans`, newMealPlan)
    },

    //deleteUserMealPlan(userID, mealPlanID)

//    getAllUserMealPlans(userID) {
//        return axios.get(`/users/${userID}/mealplans`);
//    },

    getUserMealPlanById(userID) {
        return axios.get(`/users/${userID}/mealplans`);
    },

    addRecipeToUserMealPlan(mealPlanID, recipeID, planPlacementInfo) {
        return axios.post(`/mealplans/${mealPlanID}/recipes/${recipeID}`, planPlacementInfo);
    }

    //deleteRecipeFromUserMealPlan(mealPlanID, recipeID)

}