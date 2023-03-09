<template>
    <div class="container">
        <h1>Log in</h1>
        <div class="item">
            <BaseInput  label="username"
          type="text"
          required
          v-model="this.username" />
    <button type="submit" @click="logIn" :disabled="this.isDisabled">
        Log in
    </button>
        </div>
       
    </div>
    
</template>

<script>

import BaseInput from '@/components/BaseInput.vue';
import router from '@/router';
import axios from 'axios';
import { useCounterStore } from '../stores/counter'
export default {
    
    emits:['isDisabled'],
    components:{
        BaseInput
    },
    data() {
        return {
            username: '',
           
        }
    },
    methods: {
        logIn() {
            useCounterStore().setUsername(this.username);
            const postObject={
                username: this.username,
                name:this.username
            }
            axios.post("http://localhost:8080/users",postObject).then(response=>{
        console.log(response.data)
      })
            router.push("/my-calculator")
            
        }
    },
    computed:{
        isDisabled(){
            return this.username.length === 0
        }
    }
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
    background-color: rgb(134, 149, 209);
    border: solid black;
    border-radius: 1%;
    margin-left: 200px;
    
}
.item{
    margin: 50px;
}
button{
    margin-top: 50px;
}
</style>