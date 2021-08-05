<template>
  <div class="recipe-details">
    <h1 class="recipe-name">{{ recipe.name }}</h1>
    <img v-bind:src="recipe.imageURL" class="recipe-image" />
    <h3>{{ recipe.prepTime }} minutes prep time || {{ recipe.cookTime }} minutes cook time</h3>
    <h5>Ingredients</h5>
    <div class="recipe-ingredients" v-for="ingredient in recipe.ingredients" v-bind:key="ingredient.ingredientId">
      <h4>{{ ingredient.measurementAmount }} {{ ingredient.measurementUnit }} {{ ingredient.ingredientName}}</h4>
    </div>
    <ol>
      <li v-for="step in recipe.preparation" v-bind:key="step" class="recipe-step">
        {{ step }}
      </li>
    </ol>
    <button class="btn btn-add-recipe-to-user-library" v-on:click.prevent="saveRecipe">Save Recipe To My Library</button>
  </div>
</template>

<script>
import recipeService from '../services/RecipeService'

export default {
  name: 'recipe-details',

  data() {
    return {
      recipe: {
        id: 0,
        name: '',
        preparation: [],
        prepTime: 0,
        cookTime: 0,
        imageURL: '',
        mealType: '',
        ingredients: []
      },
    }
  },
  methods: {
    saveRecipe() {
      recipeService.addRecipeToUserLibrary(this.$store.state.user.id, this.recipe.id).then(response => {
        if (response.status === 201) {
          this.$router.push('/recipes');
        }
      })
      .catch((error) => {
        console.log(error);
      });
    }
  },

  created() {
    recipeService.getRecipeById(this.$route.params.recipeID).then(response => {
      this.recipe.id = response.data.recipeId;
      this.recipe.name = response.data.recipeName;
      this.recipe.preparation = response.data.preparation.split('. ');
      this.recipe.prepTime = response.data.prepTime;
      this.recipe.cookTime = response.data.cookTime;
      this.recipe.imageURL = response.data.recipeImg;
      this.recipe.mealType = response.data.type;
      this.recipe.ingredients = response.data.ingredients;
    })
    
  }

}
</script>

<style>

.recipe-name {
  border: solid orange 5px;
  border-style: outset;
}

h4 {
  text-indent: 10px;
}

.recipe-details {
  padding: 50px;
}

h1 {
  display: inline;
}

li {
  font-size: 20px;
  border-style: outset;
}

img {
  object-fit: contain;
  display: block;
  padding: 5px;
}

h3{
  border: solid orange  5px;
  border-style: outset;
  display: inline-block;
}

h5 {
  font-size: 35px;
  text-decoration: underline;
}
</style>