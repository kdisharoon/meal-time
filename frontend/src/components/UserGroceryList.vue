<template>
  <div>
      <h2>My Grocery List</h2>
  </div>
</template>

<script>
import groceryListService from '../services/GroceryListService';

export default {
  name: 'user-grocery-list',
  data() {
    return {
      groceryList: [
        { ingredientId: '0',
          ingredientName: '',
          measurementAmount: '',
          measurementUnit: ''
        }
      ]
    }
  },

  created() {
      groceryListService.getUserGroceryList(this.$store.state.user.id).then(response => {
        console.log(response.status);
        this.groceryList = response.data;
        if (response.status === 200) {
          alert("Successfully retrieved grocery list!");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

}
</script>

<style>

</style>