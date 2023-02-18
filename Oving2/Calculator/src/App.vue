<template>
  <Modal :header="header" :valueOne="valueOne" :valueTwo="valueTwo" 
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
  
  <log :expressions="expressions"
:calculateIsClicked="calculateIsClicked"
:logNewExpression="logNewExpression"
/>
</template>



<script>
import Modal from './components/Modal.vue'

import log from './components/log.vue'

export default {

  components: { Modal, log },
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
     
    }
  },
  methods:{ 
    
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
      if(this.calculateIsClicked){
        this.operatorIsClicked=false
      }
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
      this.previousAnswer=this.calculatorScreen
      this.expressions.push((this.valueOne + ' ' + this.operatorSymbol + ' ' 
      + this.valueTwo + ' = ' + this.previousAnswer))
      this.logNewExpression=true
      this.operatorIsClicked=false
     
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
     
    }
   
    
  },
  computed:{
    calculatorScreen(){
      
      if(!this.calculateIsClicked){
        return this.value
      }else{
        if(String(this.valueOne).includes('.') || String(this.valueTwo).includes('.')){
          switch(this.operatorSymbol){
            case '+':
          return parseFloat(this.valueOne) + parseFloat(this.valueTwo)
          
          case '-':
          return parseFloat(this.valueOne) - parseFloat(this.valueTwo)

          case '*':
          return parseFloat(this.valueOne) * parseFloat(this.valueTwo)

          case '/':
          return parseFloat(this.valueOne) / parseFloat(this.valueTwo)
        }
      }
        else{
          
          switch(this.operatorSymbol){
            
            case '+':
          
          return parseInt(this.valueOne) + parseInt(this.valueTwo)
          
          case '-':
          return parseInt(this.valueOne) - parseInt(this.valueTwo)

          case '*':
          return parseInt(this.valueOne) * parseInt(this.valueTwo)

          case '/':
          return parseInt(this.valueOne) / parseInt(this.valueTwo)
          
        }
        }
       
      }
      
    }
  }
  
}

</script>

<style>
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
</style>
