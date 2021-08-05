<template>
  <div id="app">
    <header class="header">
    <h1>meal TimE</h1>
    <div id="nav" class="fake-btn">
      <router-link id="home" v-bind:to="{ name: 'home' }">HOME </router-link >
      <router-link id="all-recipes" v-bind:to="{ name: 'all-recipes' }"> RECIPES </router-link>
      <router-link id="saved-recipes" v-bind:to="{ name: 'saved-recipes', params: { userID: this.$store.state.user.id } }"> MY RECIPES </router-link>
      <a href="#" id="recipe"> MY MEAL PLAN </a> 
      <a href="#" id="recipe"> GROCERY LIST </a> 
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"> LOGOUT</router-link>
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
  border-bottom: 1px solid rgb(34, 88, 133);
  padding-right: 10px;
  padding-bottom:10px;
}
a{
  margin-left: 2.5em;
  text-decoration: none;
  color: darkblue;
  text-align: right;
  padding: 5px 10px;
  background-color: aliceblue;
  border-radius: 10px;
}
a:hover{
  background-color: orange;
}


</style>