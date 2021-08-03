import axios from 'axios';

const http = axios.create( {
    baseURL: 'http://localhost:3000'
});

export default {

    getAllRecipes() {
        return http.get('/recipes');
    },

    getAllUserRecipes(userID) {
        return http.get(`/users/${userID}/recipes`);
    },

    getRecipeById(recipeID) {
        return http.get(`/recipes/${recipeID}`);
    }

}