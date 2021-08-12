<template>            

  <div class="about_container">
    <header id="header" class="head">
      <h1>The Developers of meal TimE</h1>
    </header>
    <div class="container">
      <div class="row">
        <div class="col-6 bio card" id="shay">
          <img class="abtimg" src="../assets/shay.jpg" />
          <div class="about">
            <h6>Shay Griffith</h6>
            <p>Shay is a former Miss Pennsylvania conetestant, who was unfairly disqualified for crosschecking a fellow contestant during her talent showcase.  After her community service was up, Shay entered into a recruitment career where she filled many companies with talented, qualified and extremely tough candidates.  Covid hit Shay hard and she decided a career change was in order, but the NHL still won't allow females to compete, so she joined Tech Elevator.  For this application, Shay designed the database, worked with the API and contributed many designs to the layout and logo.  </p>
          </div>
        </div>
        <div class="col-6 bio card" id="ezra">
          <img class="abtimg" src="../assets/ezra.jpeg" />
          <div class="about">
            <h6>Ezra Saywitz</h6>
            <p>Ezra "Mad Dawg" Saywitz is a backend legend.  Single handidly owning the backend portion of meal TimE, Ezra was quickly able to solve many issues that popped up through the development.  When he isn't coding, you can find Ezra in the octogan destroying his flyweight competion.</p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-6 bio card" id="aj">
          <img class="abtimg" src="../assets/aj.jpeg" />
          <div class="about">
            <h6>AJ Jadot</h6>
            <p>After a short but stellar stint in the demolition derby circuit, AJ decided he needed to use his brain for more than just pin-point engine desctruction.  AJ served as equal parts design and front-end functionality for meal TimE.  His touch can be felt on every aspect for user interaction. If you ever pull up to AJ at a stop light, don't nod his way - he'll run you into a lightpost.</p>
          </div>
        </div>
        <div class="col-6 bio card" id="kris">
          <img class="abtimg" src="../assets/kris.jpeg" />
          <div class="about">
            <h6>Kris Disharoon</h6>
            <p>Originally from Bern, Switzerland, Kris spent over a decade in the Swiss Special Forces.  After retirement, with honors, he relocated to Pittsburgh, because he heard the pierogies were pretty good and enjoys breweries.  Kris is a natural leader - not only has he been involved on every part of the application, he has unknowlingly kept us all in line, organized and calm. His time in the deserts of Africa prepared him for 14 weeks at Tech Elevator.  Without Kris, Ryan might not have contributed anything at all to this application.</p>
          </div>
        </div>
      </div>
      <div class="row">
    <div class="bio col-12 card" id="ryan">
      <img class="abtimg" src="../assets/ryan.jpg" />
      <div class="about">
        <h6>Ryan Godman</h6>
        <p>After a failed attempt in the entertainment industry, Ryan spent time living in his parents basement...for a long time.  He eventually found someone who settled for him and they moved to the Pittsburgh area a few years ago.  After deciding a career change was in order, he found Tech Elevator, but his fiance decided to go through it first.  So he waited, and then got to go himself.  Ryan was around for every meeting and always has something to say...but no much to do. It's that undying dedication to words that has gotten Ryan this far in life. When you click on something and go, "why is this on here?", that's Ryan.</p>
      </div>
    </div>
      </div>
    </div>


    <footer id="footer">
      <img class="" src="" />
    </footer>
  </div>
</template>

<script>
export default {
  name: "about-all-us",
};
</script>


<style>

.card {
    min-width: 600px; 
}

img.abtimg {
    object-fit: contain;
    min-height: 400px;
    margin-top: 50px;
}

p {
    font-size: 20px;
    margin: 40px;
}

h6{
    text-align: center;
    font-size: 50px;
}
/* .person {
  -moz-border-radius: 2%;
  -webkit-border-radius: 2%;
  border-radius: 2%;
  box-shadow: 5px 5px 0 rgba(0, 0, 0, 0.08);
}

.profile .profile-body {
  padding: 20px;
  background: #f7f7f7;
}

.profile .profile-bio {
  background: #fff;
  position: relative;
  padding: 15px 10px 5px 15px;
}

.profile .profile-bio a {
  left: 50%;
  bottom: 20px;
  margin: -62px;
  text-align: center;
  position: absolute;
}

.profile .profile-bio h2 {
  margin-top: 0;
  font-weight: 200;
}

h1,
h2,
h3,
h4,
h5,
h6 {
  color: #585f69;
  margin-top: 5px;
  text-shadow: none;
  font-weight: normal;
  font-family: "Open Sans", sans-serif;
}
h2 {
  font-size: 24px;
  line-height: 33px;
}

p,
li,
li a {
  color: #555;
}

/*html {
    background-color: #7DDFA4;
}
.head {
    display: flex;
    justify-content: center;
    margin-bottom: 0px;
}
.logo {
    max-width: 500px;
  }
.ab{
  background-color: #7DDFA4;
  }
.about_container {
    margin: 10px;
    padding-left: 65px;
    padding-right: 65px;
    font-family: sans-serif;
    display: grid;
    grid-template-columns: 2fr 2fr;
    grid-gap: 30px;
    grid-template-areas:
        "header header"
        "shay ezra"
        "aj kris"
        "ryan";
  }
#header {
    grid-area: header; 
}
#shay {
    grid-area: shay;
}
#ezra {
    grid-area: ezra;
}
#aj {
    grid-area: aj;
}
#kris {
    grid-area: kris;
}
#ryan {
    grid-area: ryan;
}
#footer {
    grid-area: footer;
}
  .bio {
    background-color: #F0F0F0;
    padding: 10px;
    border: solid 2px rgb(3, 3, 3);
    display: flex;
    align-items: center;
    
  }
  .bio img {
    max-height: 200px;
    max-width: 200px;
    border-radius: 800px;
    margin: 10px;
  }
  .bio:nth-child(1) img {
    order: 2;
  }
 #footer {
     display: flex;
     justify-content: center;
 } 
.img {
    max-width: 200px;
}
h1 {
    line-height: 20px;
}
p {
    line-height: 24px;
}*/ 
</style>