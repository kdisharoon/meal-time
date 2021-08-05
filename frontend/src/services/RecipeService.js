import axios from 'axios';


export default {

    getAllRecipes() {
        return axios.get('/recipes');
    },


  getAllRandomRecipesFromSpoonacular() {
        return axios.get('https://api.spoonacular.com/recipes/complexSearch?query=chicken&addRecipeInformation=true&apiKey=941ba16793664284808b0c07b47ed70f');
    },

    getAllUserRecipes(userID) {
        return axios.get(`/users/${userID}/recipes`);
    },

    getRecipeById(recipeID) {
        return axios.get(`/recipes/${recipeID}`);
    },

    addRecipeToUserLibrary(userID, recipeID) {
        return axios.post(`/users/${userID}/recipes/${recipeID}`);
    }

}