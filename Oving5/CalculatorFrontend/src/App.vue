<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
import router from "./router";
import { useTokenStore } from "./stores/dist/mytoken";

const tokenStore=useTokenStore()



function logOut(){
  tokenStore.loggedInUser=null;
  tokenStore.jwtToken=null;
  router.push("/")
}
</script>

<template>
  <div class="wrapper">
  <header> 
      <nav>
        
        <RouterLink  v-if="$route.path!=='/' && $route.path!=='/sign-up'  " to="/my-calculator" >Calculator</RouterLink>
        <RouterLink  v-if="$route.path!=='/' && $route.path!=='/sign-up'" to="/calculator-form">Contact form</RouterLink>
        
      </nav>
  </header>
  <div class="main">
    <RouterView/>  
    <button v-if="$route.path!=='/'  && $route.path!=='/sign-up'" @click="logOut">Log out</button>
  </div>
</div>
</template>

<style scoped>

.wrapper{
  display: grid;
  grid-template-columns: repeat(1,1fr);
  grid-template-rows: repeat(5,1fr);
  height: 500px;
}
a{
  width: 150px;
}
button{
    padding: 1px;
    border-radius: 20px;
    width: 70px;
    cursor: pointer;
}
button:hover{
  background-color: rgba(247, 244, 246, 0);
}
.main{
  grid-area: 2/1/6/2;
  height: 450px;
  
}
header {
  line-height: 1.5;
  max-height: 100vh;
  grid-area: 1/1/2/2;
  margin-left: 400px;
 
}

nav {
  display: flex;
  flex-direction: row;
  width: 200%;
  font-size: 12px;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

 
  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;
    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>
