<template>
    <div class="container">
        <h1>Log in</h1>
        <div class="item">
            <BaseInput  label="username"
          type="text"
          required
          v-model="username" />
          <BaseInput  label="password"
          type="text"
          required
          v-model="password" />
    <button type="submit" @click="logIn()" :disabled="isDisabled">
        Log in
    </button>
    <button @click="signUp()">Sign up</button>
        </div>
       
    </div>
    
</template>

<script setup>

import BaseInput from '@/components/BaseInput.vue';
import router from '@/router';
import { ref,computed } from 'vue';
import { useTokenStore } from "../stores/dist/mytoken";



    const username= ref('')
    const password=ref('')
    //const validData:false,
    const tokenStore=useTokenStore()
    const isDisabled=computed(()=>{
        return username.value.length === 0 || password.value.length === 0
    })
           
        

    async function logIn() {

           await tokenStore.getTokenAndSaveInStore(username.value, password.value);
           if((tokenStore.jwtToken || tokenStore.jwtToken !==null) && tokenStore.loggedInUser!==null){
               console.log(tokenStore.loggedInUser)
               console.log(tokenStore.jwtToken)
               router.push("/my-calculator")
           } else {
               alert("Invalid username or password")
           }
           
       }
       function signUp() {
           router.push("/sign-up")
       }
       
   
/*
export default {
    
    emits:['isDisabled'],
    components:{
        BaseInput
    },
    data() {
        return {
            username: 'name',
            password:'pw',
            validData:false,
            tokenStore:useTokenStore(),
           
        }
    },
    /*
    setup() {
    const tokenStore = useTokenStore();
    return { tokenStore };
  },

    methods: {
        async logIn() {
           
        

        await this.tokenStore.getTokenAndSaveInStore(this.username, this.password);
        if((this.tokenStore.jwtToken || this.tokenStore.jwtToken !==null) && this.tokenStore.loggedInUser!==null){
            console.log(this.tokenStore.loggedInUser)
           
            router.push("/my-calculator")
        } else {
            alert("Invalid username or password")
        }
        

    },
    signUp() {
        router.push("/sign-up")
    }
},
    computed:{
        isDisabled(){
            return this.username.length === 0 || this.password.length === 0
        }
    },
    
}*/
   
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