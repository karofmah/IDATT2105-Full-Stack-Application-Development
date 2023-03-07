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
            router.push("/my-calculator")
            
        }
    },
    computed:{
        isDisabled(){
            return this.username.length === 0
        }
    }
}
    const username='admin'
    function logIn(){
        console.log(username)
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