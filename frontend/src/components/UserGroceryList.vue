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
      groceryList: []
    }
  },

  created() {
      groceryListService.getUserGroceryList(this.$route.params.userID).then(response => {
        this.groceryList = response.data;
      })
  }

}
</script>

<style>

</style>