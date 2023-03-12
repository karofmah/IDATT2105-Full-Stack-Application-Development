<template>
  <div class="main">
    
    <div v-if="this.user" id="mainContainer">
          <h2>Hey, {{ this.user.name }} </h2>
          <label id="homepageTitle">Welcome to the home screen</label>
      </div>
      <div v-if="!this.user || this.user===null">
          <h2>Unauthorized!!!</h2>
      </div>

    <Calculator :header="header" :valueOne="valueOne" :valueTwo="valueTwo" 
    :operatorIsClicked="operatorIsClicked" 
    :operatorSymbol="operatorSymbol"
    :calculateIsClicked="calculateIsClicked"
    @answer-clicked="savePreviousAnswer"
    :calculatorScreen="calculatorScreen"
    @delete-number="deleteValue"
    @save-number="naturalNumberClicked"
    @clear-all="allClear"
    @operator-clicked="updateOperator"
    @calculate-number="calculateAnswer"
    @comma-clicked="decimalIsClicked"
   
    />
    <div class="list-container">
      <div class="list">
        <ul >
       <li class="calculation" v-for="(calculation,index) in calculations" :key="calculation.id" @click="getCalculation(calculation.id)">{{ calculationsString[index] }}</li>
      </ul>
    </div>
      <button @click="getCalculations">Get previous calculations</button>
    
    </div>
   
     <Log class="log" :expressions="expressions"
  :calculateIsClicked="calculateIsClicked"
  :logNewExpression="logNewExpression"
  />

  </div>
  </template>
  
  <script>
  import { useTokenStore } from '@/stores/dist/mytoken';
import axios from 'axios';
  
  import Calculator from '../components/Calculator.vue'
  
  import Log from '../components/Log.vue'
  
  import { useCounterStore } from '../stores/counter'

  import {getUserInfo} from "/httputils.js"





  export default {
 
    
    components: { Calculator, Log },
    
   

    data(){
      return{
       header: 'Calculator',
       valueOne:'',
       valueTwo: '',
       operatorSymbol:'',
       operatorIsClicked:false,
       calculateIsClicked:false,
       previousAnswer:'',
       value:'',
       expressions:[],
       logNewExpression:false,
       answerClicked:false,
       buttonIsClicked:"",
       result:null,
       username:'',
       password:'',
       calculations:[],
       calculationsString:[],
       user:null,
       tokenStore:useTokenStore()
      }
    },
    async mounted() {
      if(!this.tokenStore.jwtToken || this.tokenStore.jwtToken===null){
          console.log("Unauthenticated context");
      } else {
          console.log("Authenticated context");
          this.user = this.tokenStore.loggedInUser
          this.username = this.user.name
          this.password = this.user.password
          setInterval(()=>{
            this.refreshToken();

          }, 1000*10)
        
      }
    },

    methods:{ 
      async refreshToken(){
        console.log("refreshing token");
        console.log(this.tokenStore.jwtToken);
        return await this.tokenStore.getTokenAndSaveInStore(this.user.name, this.user.password)
        
      },
      saveValue(event){
        if(this.calculateIsClicked){
          this.valueOne=''
          this.valueTwo=''
        }
        this.calculateIsClicked=false
        
       
        if(!this.operatorIsClicked){
                  this.saveFirstValue(event)
              }
              else{
                  this.saveSecondValue(event)
                }
        
      },
      allClear(){
        this.value=''
        this.valueOne=''
        this.valueTwo=''
        this.operatorIsClicked=false
        this.calculateIsClicked=false
      },
    
      deleteValue(){
        if(this.value===this.valueOne){
          this.valueOne=this.valueOne.replace(this.valueOne.charAt(this.valueOne.length-1),'')
          this.value=this.valueOne
        }else if(this.value===this.valueTwo){
          this.valueTwo=this.valueTwo.replace(this.valueTwo.charAt(this.valueTwo.length-1),'')
          this.value=this.valueTwo
        }
      },
      naturalNumberClicked(event){
          this.saveValue(event)
        
      },
  
      saveFirstValue(event){
        
        if(this.answerClicked){
         this.valueOne=this.previousAnswer
        }else{
          this.valueOne+=event.target.value
        } 
        this.value=this.valueOne
        
      },
      saveSecondValue(event){ 
        if(this.answerClicked){
          this.valueTwo=this.previousAnswer
        }
        else{
          this.valueTwo+=event.target.value
        }         
          this.value=this.valueTwo

        console.log(this.valueOne)
        console.log(this.valueTwo)
      },
      updateOperator(event){
        
        this.operatorIsClicked=true
        this.operatorSymbol=event.target.value
      },
      savePreviousAnswer(){
        this.value=this.previousAnswer
        this.answerClicked=true
        this.saveValue();
        this.answerClicked=false
      },
      
       calculateAnswer(){
         
          this.calculateIsClicked=true

      },
      decimalIsClicked(){
        if(!this.operatorIsClicked){
          this.valueOne+='.'
          this.value=this.valueOne
          console.log(this.valueOne)
        }else{
          this.valueTwo+='.'
          this.value=this.valueTwo
          console.log(this.valueTwo)
        }
       
      },
      logOut(){
        router.push('/')
    },
    getCalculation(id){
      axios.get(`http://localhost:8080/calculations/${id}`).then(response=>{
        console.log(response.data)
        const selectedCalculation=response.data
        this.valueOne=selectedCalculation.valueOne
        this.operatorSymbol=selectedCalculation.operator
        this.valueTwo=selectedCalculation.valueTwo
        this.calculateIsClicked=true
        this.calculations=[]
        this.calculationsString=[]
      } ) 
    },
    getCalculations(){
      
      axios.get('http://localhost:8080/calculations?username='+this.username).then(response=>{
        console.log(response.data)
        this.calculations=response.data.reverse().slice(0,10)
        response.data.forEach(calculation=>{
          this.calculationsString.push(calculation.valueOne + ' ' + calculation.operator + ' ' + calculation.valueTwo)
        })
        console.log("list displayed")
        this.allClear()

      } )
     
    }
  },
    watch: {
  calculateIsClicked: function(newValue) {
    
    if (newValue===true) {
      const calculation = {
        username:this.user.name,
        valueOne: this.valueOne,
        operator: this.operatorSymbol,
        valueTwo: this.valueTwo,
        }
      
      
        
      axios.post("http://localhost:8080/calculate",calculation).then(response => {
        
        this.result=response.data
        this.expressions.push((this.valueOne + ' ' + this.operatorSymbol + ' ' 
        + this.valueTwo + ' = ' + this.result))
        console.log(this.expressions)
        this.logNewExpression=true
        this.previousAnswer=this.calculatorScreen
        this.operatorIsClicked=false
        console.log(this.username)

      
      });
      
      axios.post("http://localhost:8080/calculations",calculation).then(response=>{
        console.log(response.data)
       
      })
     
    }
  }
},
created(){
 //this.username= useCounterStore().getUsername()
 //this.password=useCounterStore().getPassword()
 console.log(this.username)
 

},
    computed:{
      calculatorScreen(){
        if(!this.calculateIsClicked){
          return this.value
        }else{
          return this.result
        }
      }
    }
  }
  
  </script>
  
  <style scoped>
  
 
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
    
  }
  
  html{
    background-color: #2c3e50;
  }

  .main{
    overflow-y: hidden;
    width:1200px;
    height: screen;
  
   
  }
  .list-container{
    float:right;
    position: relative;
    bottom:200px;
    width:300px;
    height: 300px;
  }
  .list{
    width: 300px;
    height: 300px;
    border:solid white;
   
  
  }
  .calculation{
    cursor:pointer;
    list-style-type:none;

  }
  .calculation:hover{
    background-color: #2c3e50;
  }
  button{
    border-radius:20px;
    cursor:pointer;
  }
  
  
  </style>
  