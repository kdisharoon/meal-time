<template>
  <div id="popular">
    <div id="day">
      <h3>Recipe of the Day</h3>
      <h4>Jambalaya</h4>
      <img src="https://cafedelites.com/wp-content/uploads/2018/05/Jambalaya-IMAGE-3.jpg">
    <h5>Ingredients</h5>
    <ul>
        <li>Chicken</li>
         <li>Shrimp</li>
          <li>Andouille Sausage</li>
           <li>Jasmine Rice</li>
    </ul>
    <h5>Preparations</h5>
    <p><i>Placeholder</i></p>
    </div>
    <div id="recipe">
      <h3>Popular Recipes</h3>
      <div class="card">
          <img src="https://www.tasteofhome.com/wp-content/uploads/2018/01/Caprese-Salad_EXPS_FT20_50347_F_0610_1_home.jpg?fit=700,1024">
            <div class="info">
                <a href= "#">Caprese Salad</a>
            </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
#popular {
  display: grid;
  grid-template-columns: 350px 1fr;
  grid-template-areas:
    "day recipe"
    "day recipe";
    
}
#day {
    padding-left: 10px;
    padding-right: 10px;
  grid-area: day;
  border-right: 1px solid rgb(34, 88, 133);
  text-align: center;
}
#recipe {
  grid-area: recipe;
  text-align: center;
}
h5{
    text-align: left;
}
li {
    text-align: left;
}
img {
    width:200px;
    height:200px;
    
}
.class {
    background-color: white;
}
</style>