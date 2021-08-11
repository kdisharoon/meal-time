<template>
  <div>
      <div class="loading" v-if="isLoading">
        <img src="../assets/giphy.gif" />
      </div>
      <h2 style="text-align:center;">My Grocery List</h2>
<div id="paper">
  <div id="pattern">
    <div id="content">
      <ol>
        <li v-for="item in groceryList" 
            v-bind:key="item.ingredientId" 
            class="shopping-list-item" 
            :class="{ crossedOut: crossedOutItems.includes(item) }"
            @click="toggleCrossed(item)">
            {{ item.measurementAmount }} {{ item.measurementUnit }} {{ item.ingredientName }}
          </li> 
        </ol>
      </div>
  </div>
</div>
  </div>
</template>

<script href="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js">

import groceryListService from '../services/GroceryListService';

export default {
  name: 'user-grocery-list',
  data() {
    return {
      groceryList: [
        { ingredientId: '0',
          ingredientName: '',
          measurementAmount: '',
          measurementUnit: '',
        }
      ],
      crossedOutItems: [],
      isLoading: true,
    }
  },
  created() {
      groceryListService.getUserGroceryList(this.$store.state.user.id).then(response => {
        console.log(response.status);
        this.groceryList = response.data;
        this.isLoading = false;
      })
  },
  methods: {
    toggleCrossed(item) {
      if(this.crossedOutItems.includes(item)) {
        this.crossedOutItems = this.crossedOutItems.filter(
          (item) => item !== item
        );   
      } else {
        this.crossedOutItems.push(item);
      }
    }
  } 

}
</script>

<style>
.crossedOut {
  text-decoration: line-through;
  color: magenta;
}

body {
  background-color: rgba(0,0,0,0.1);
}

/* styling paper */
#paper {
  width: 600px;
  height: auto;
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

li {
  border: none!important;
  cursor: pointer;
}

.offList {
  text-decoration: line-through;
}

/* li:visited,
li:focus,
li:active {
  text-decoration: line-through;
} */

/* gotta keep adding commits */


/* Are you paying detailed attention to my commits? */

/*Commit commit commit*/

.loading {
  flex: 3;
}
</style>