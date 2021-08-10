<template>
  <div>
      <h2>My Grocery List</h2>
<div id="paper">
  <div id="pattern">
    <div id="content">
      List Input Here X - Ryan No Smart Enuff two Make werk
      </div>
  </div>
</div>
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
body {
  background-color: rgba(0,0,0,0.1);
}

/* styling paper */
#paper {
  width: 600px;
  height: 700px;
  position: relative;
  margin: 20px auto;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: white;
  box-shadow: 0px 0px 5px 0px #888;
}

/* styling red vertical line */
#paper::before {
  content: '';
  width: 2px;
  height: 100%;
  position: absolute;
  top: 0;
  left: 40px;
  background-color: rgba(255,0,0,0.6);
}

/* styling blue horizontal lines */
#pattern {
  height: 100%;
  background-image: repeating-linear-gradient(white 0px, white 24px, teal 25px);
}

/* styling text content */
#content {
  padding-top: 6px;
  padding-left: 56px;
  padding-right: 16px;
  line-height: 25px;
  font-family: 'Dancing Script';
  font-size: 19px;
  letter-spacing: 1px;
  word-spacing: 5px;
}

/* gotta keep adding commits */


/* Are you paying detailed attention to my commits? */
</style>