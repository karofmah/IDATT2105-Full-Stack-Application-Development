<template>
    <div class="container">
        <h1>Sign up</h1>
        <div class="item">
            <BaseInput  label="username"
          type="text"
          required
          v-model="username" />
          <BaseInput  label="password"
          type="password"
          required
          v-model="password" />
    <button type="submit" @click="signUp()" :disabled="isDisabled">
        Sign up
    </button>
    <button @click="logIn()">
        Log in
    </button>
        </div>
       
    </div>
    
</template>

<script setup>

import BaseInput from '@/components/BaseInput.vue';
import router from '@/router';
import { useTokenStore } from '@/stores/dist/mytoken';
import axios from 'axios';
import { ref, computed } from 'vue';


const username=ref('')
const password=ref('');

const isDisabled=computed(()=>{
return username.value.length==0||password.value.length==0
})

function logIn(){
    router.push("/")
}
async function signUp() {
    const postObject={
                username: username.value,
                name:username.value,
                password:password.value,
            }
            
        axios.post("http://localhost:8080/signup",postObject).then(response=>{
        console.log(response.data)
        console.log(response.status)
        if(response.status==200){
            alert("You are already registered") 
        }else if(response.status==201){
            alert("You are registered successfully")
            
        }
           
        }).catch(function(err){
       console.log('Error',err)
       alert("An error occurred while registering");
     })
     
}
   
</script>

<style scoped>
h1{

    color: white;
}
.container{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    background-color: rgb(62, 62, 66);
    border: solid black;
    border-radius: 1%;
    margin-left: 200px;
    
    
}
.item{
    margin-top: 30px;
    border:solid black;
    padding:50px;
}
button{
    margin-top: 20px;
    padding: 5px;
    border-radius: 20px;
    width: 90px;
}
button:enabled:hover{
    cursor:pointer;
    background-color: rgba(247, 244, 246, 0);
}
</style>