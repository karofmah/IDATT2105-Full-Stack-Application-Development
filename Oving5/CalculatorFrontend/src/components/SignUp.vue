<template>
    <div class="container">
        <h1>Sign up</h1>
        <div class="item">
            <BaseInput  label="username"
          type="text"
          required
          v-model="this.username" />
          <BaseInput  label="password"
          type="text"
          required
          v-model="this.password" />
    <button type="submit" @click="signUp" :disabled="this.isDisabled">
        Sign up
    </button>
    <button @click="logIn">
        Log in
    </button>
        </div>
       
    </div>
    
</template>

<script>

import BaseInput from '@/components/BaseInput.vue';
import router from '@/router';
import axios from 'axios';




export default {
    
    emits:['isDisabled'],
    components:{
        BaseInput
    },
    data() {
        return {
            username: 'name',
            password:'pw'
            
        }
    },
   
    methods: {
        async signUp() {
          
            const postObject={
                username: this.username,
                name:this.username,
                password:this.password
            }
            
        axios.post("http://localhost:8080/users",postObject).then(response=>{
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
     
       
    },
    logIn() {
        router.push("/")
    }
},
    computed:{
        isDisabled(){
            return this.username.length === 0 || this.password.length === 0
        }
    },
    
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
}
</style>