<template>
    <div class="main">
      <h1>Create a review</h1>
      <form @submit.prevent="onSubmit();">
  
        <BaseInput
          label="Name"
          type="name"
          required
          v-model="this.fields.name"
          :error="nameError"
          id="name"
        />
        <span>{{ this.nameError }}</span>
        <br>
        <BaseInput
          label="Email"
          type="email"
          required
          v-model="this.fields.email"
          :error="emailError"
          id="email"
          
        />
        <span>{{ this.emailError }}</span>
        <br>
      
        <BaseInput
          label="Message"
          type="message"
          required
          v-model="this.fields.message"
          :error="messageError"
          id="message"
        />
        <span>{{ this.messageError }}</span>
        <br>
        
        <button type="submit" 
        :disabled="this.isDisabled"
        data-cy="submit"
        id="submit"
        >
          Submit
        </button>
        
      </form>
  
    </div>
    
  </template>
  
  <script >
  import axios from "axios"
  import BaseInput from './BaseInput.vue' 
  import { useCounterStore } from '../stores/counter'
  
  export default {
    
    components: {
      BaseInput,
    },
    
    data(){
      return{
        fields:{
          name:'',
          email:'',
          message:''  
        },
        requiredMessage:'This field is required',
        
      }
    },
    methods:{
      
      onSubmit(){
        useCounterStore().setName(this.fields.name)
        useCounterStore().setEmail(this.fields.email)
        axios.post("http://localhost:3000/calculator-form",this.fields).
        then(function(response){
        console.log('Response', response)
        alert(response.status + ': ' + response.statusText)
      }
      
      )
     
      .catch(function(err){
        console.log('Error',err)
      })
      
      },
      
       
    },
    created(){
      this.fields.name=useCounterStore().getName()
      this.fields.email=useCounterStore().getEmail()
    },
    computed:{
      isDisabled(){
        if(this.nameError || this.emailError || this.messageError){
          return true
        }else{
          return false
        }
      },
      nameError(){
          if(this.fields.name===undefined || this.fields.name===null){
            
            return this.requiredMessage
          }
          if(!String(this.fields.name).length){
            
           return this.requiredMessage
          }
          return ''
        
      },
      emailError(){
  
          if(this.fields.email===undefined || this.fields.email===null){
            
            return this.requiredMessage
          }
          if(!String(this.fields.email).length){
            
           return this.requiredMessage
          }
          
        const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        if (!regex.test(String(this.fields.email).toLowerCase())) {
          return 'Please enter a valid email address'
        }
        return ''
      },
      messageError(){
          if(this.fields.message===undefined || this.fields.message===null){
            
            return this.requiredMessage
          }
          if(!String(this.fields.message).length){
            
           return this.requiredMessage
          }
          return ''
      },
      
    }
    
  }
  </script>
  <style scoped>
  
  .main{
    margin-left: 300px;
    border: solid #CCF381;
    width: 500px;
    background-color: #2c272c;
  }
  h1{
    color: white;
  }
  
  
  </style>