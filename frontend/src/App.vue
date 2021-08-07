<template>
  <div id="app">
    <header class="header">
    <h1>meal TimE</h1>
    <div id="nav" class="fake-btn">
      <router-link class="nav-btn-class" id="home" v-bind:to="{ name: 'home' }">HOME </router-link >
      <router-link class="nav-btn-class" id="all-recipes" v-bind:to="{ name: 'all-recipes' }"> ALL RECIPES </router-link>
      <router-link class="nav-btn-class" id="saved-recipes" v-bind:to="{ name: 'saved-recipes', params: { userID: this.$store.state.user.id } }"> MY RECIPES </router-link>
      <router-link class="nav-btn-class" id="user-meal-plans" v-bind:to="{ name: 'user-meal-plans', params: { userID: this.$store.state.user.id } }"> MY MEAL PLANS </router-link> 
      <a class="nav-btn-class" href="#" id="recipe"> GROCERY LIST </a> 
      <router-link class="nav-btn-class" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"> LOGOUT</router-link>
    </div>
     </header>
     
    <router-view />
   
  </div>
</template>
<script>
</script>

<style>
#app{
  background-image: linear-gradient(to bottom right, antiquewhite, #2fcee1, #A56EFA);
  min-height: 100vh;
  background-size: cover !important;
  font-family: Arial, Helvetica, sans-serif;
}

h1{
  font-size: 40px;
  padding-left: 10px;
  padding-top: 10px;
}
#nav {
  font-size: 18px;
  height: 100%;
  text-align: right;
  border-bottom: 2px solid black;
  padding-right: 10px;
  padding-bottom:10px;
  display:flex;
  justify-content: flex-end;
}
.nav-btn-class:hover{
  position:static;
  border:1px solid black;
  text-decoration: none;
  color: darkblue;
  text-align: right;
  padding: 5px 10px;
  background-color: aliceblue;
  border-radius: 10px;
  visibility:initial;
  display: inline-block;
}
.nav-btn-class:hover{
  background-color: orange;
}


</style>