<template>
  <h1> {{ header }} </h1>

  <div class="modal">
      
      <div class="calculator-screen" >
         <p>
          {{ calculatorScreen || 0 }}
         </p>
      </div>
     

      <button class="all-clear" value="all-clear" @click="clearAll">AC</button>
      <button class="previousAnswer" value="previousAnswer" @click="answerClicked">ANS</button>
      <button class="delete" @click="deleteNumber">DEL</button>
         
      <button class="operator" value="/" @click="operatorClicked">/</button>
      
      <button class="natural-number" value="1"  @click="saveNumber">1</button>
      <button class="natural-number" value="2"  @click="saveNumber">2</button>
      <button class="natural-number" value="3"  @click="saveNumber">3</button>

      <button class="operator" value="*" @click="operatorClicked">*</button>

      <button class="natural-number" value="4"  @click="saveNumber">4</button>
      <button class="natural-number" value="5"  @click="saveNumber">5</button>
      <button class="natural-number" value="6"  @click="saveNumber">6</button>

      <button class="operator" value="-" @click="operatorClicked">-</button>

      <button class="natural-number" value="7"  @click="saveNumber">7</button>
      <button class="natural-number" value="8"  @click="saveNumber">8</button>
      <button class="natural-number" value="9"  @click="saveNumber">9</button>

      <button class="operator" value="+" @click="operatorClicked">+</button>
      
      <button class="zero" value="0" @click="saveNumber" >0</button>
      <button class="decimal" value="." @click="commaClicked">.</button>
          
      <button class="equal-sign" value="=" @click="calculateNumber">=</button>
      
  </div>

</template>

<script>
export default {
 
  props:['header','valueOne','valueTwo','operatorIsClicked',
   'calculatorScreen', 'operatorSymbol', 'calculateIsClicked'],
   
   emits: ['save-number', 'operator-clicked', 'calculate-number',
   'clear-all','answer-clicked','delete-number','comma-clicked'],
  methods: {
  
      saveNumber(event){
         this.$emit('save-number',event)
          
      },
      operatorClicked(event){
          this.$emit('operator-clicked',event)
      },
      calculateNumber(){
          this.$emit('calculate-number')
      },
      clearAll(){
          this.$emit('clear-all')
      },
      answerClicked(){
          this.$emit('answer-clicked')
      },
      deleteNumber(){
          this.$emit('delete-number')
      },
      commaClicked(){
          this.$emit('comma-clicked')
      }
      
  }
}
</script>
<style scoped>
.modal{
display:grid;
grid-template-columns: repeat(4, 1fr);
grid-template-rows: repeat(6, 1fr);
margin-left:300px;
width: 500px;
height: 200px;
}
h1{
  color:white;
  margin-left: 500px;
 
}
.calculator-screen{
color: white;
background-color: gray;
height: 50px;
grid-area: 1 / 1 / 2 / 5;
text-align: center;
}
.calculator-screen p{
margin-top:10px;
}
button{
color: white;
padding: 10px;
}
.all-clear{
  grid-area:2 / 1 / 3 / 2;
}
button[value='0']{
  grid-area:6 / 1 / 7 / 3;
}
.equal-sign{
background-color:orange;
grid-area: 6 / 4 / 7 / 5;
}
.equal-sign:hover{
background-color: darkorange;
}
.operator{
background-color: rgb(128, 159, 185);
}
.operator:hover{
background-color: mediumturquoise;
}
.natural-number,.zero,.decimal,.previousAnswer,.delete,.all-clear{
background-color:#363946;
}
.natural-number:hover,.all-clear:hover,.previousAnswer:hover,.decimal:hover,.delete:hover,.zero:hover{
  background-color:#2d2e32;
}
</style>