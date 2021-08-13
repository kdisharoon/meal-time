import axios from 'axios';


export default {

    addRecipesFromAPIToDatabase(newRecipes) {
        return axios.post('/recipes', newRecipes);
    },

    getAllRecipes() {
        return axios.get('/recipes');
    },

    getRandomRecipesFromSpoonacular() {
        return axios.get('https://api.spoonacular.com/recipes/random?number=100&apiKey=d391dd6b7d7c47358081f54cbadbfec0');
    },

    searchRecipesFromSpoonacular(searchTerm) {
        return axios.get(`https://api.spoonacular.com/recipes/complexSearch?query=${searchTerm}&number=6&sort=random&apiKey=941ba16793664284808b0c07b47ed70f`);
    },

    getSpecificRecipesFromSpoonacular(idString) {
        return axios.get(`https://api.spoonacular.com/recipes/informationBulk?ids=${idString}&apiKey=941ba16793664284808b0c07b47ed70f`)
    },

    getAllUserRecipes(userID) {
        return axios.get(`/users/${userID}/recipes`);
    },

    getRecipeById(recipeID) {
        return axios.get(`/recipes/${recipeID}`);
    },

    addRecipeToUserLibrary(userID, recipeID) {
        return axios.post(`/users/${userID}/recipes/${recipeID}`);
    },

    deleteRecipeFromUserLibrary(userID, recipeID){
        return axios.delete(`/users/${userID}/recipes/${recipeID}`);
    }

}