import axios from 'axios';


export default {

    getAllRecipes() {
        return axios.get('/recipes');
    },

    getAllUserRecipes(userID) {
        return axios.get(`/users/${userID}/recipes`);
    },

    getRecipeById(recipeID) {
        return axios.get(`/recipes/${recipeID}`);
    }

}