<template>
 
</template>

<script>
import ingredientService from '../services/IngredientService';

export default {
    name: 'ingredient-detail',

data() {
    return {
        id: '',
        name: ''
       }
    },

created() {
    ingredientService
    .getIngredient(this.$route.params.ingredientID)
    .then(response => {
        this.ingredient = response.data;
      });
    }
}

</script>

<style>

</style>