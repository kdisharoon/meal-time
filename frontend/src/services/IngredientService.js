import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:3000"
});

export default {

  getAllIngredients() {
    return http.get('/ingredients');
  },

  getIngredient(ingredientID) {
    return http.get(`/ingredient/${ingredientID}`)
  },
}