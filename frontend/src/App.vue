<template>
  <div id="app">
    <h1>meal TimE</h1>
    <div id="nav">
      <router-link id="home" v-bind:to="{ name: 'home' }">HOME </router-link >
      <a href="/recipes" id="recipe"> RECIPES </a> 
      <a href="#" id="recipe"> MY RECIPES </a>
      <a href="#" id="recipe"> MY MEAL PLAN </a> 
      <a href="#" id="recipe"> GROCERY LIST </a> 
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"> LOGOUT</router-link>
    </div>
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
}
</style>