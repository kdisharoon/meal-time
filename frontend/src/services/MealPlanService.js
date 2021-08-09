import axios from 'axios';


export default {

    addUserMealPlan(newMealPlan) {
        return axios.post(`/users/mealplans`, newMealPlan)
    },

    //deleteUserMealPlan(userID, mealPlanID)

    getUserMealPlanById(userID) {
        return axios.get(`/users/${userID}/mealplans`);
    },
                                                //just the day and meal in planPlacementInfo
    addRecipeToUserMealPlan(userID, recipeID, planPlacementInfo) {
        return axios.post(`/users/${userID}/mealplans/recipes/${recipeID}`, planPlacementInfo);
    },

    deleteRecipeFromUserMealPlan(userID, recipeID){
        return axios.post(`/users/${userID}/mealplans/recipes/${recipeID}`);
    }

}