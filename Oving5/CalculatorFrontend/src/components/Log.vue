<template>
    <div class="log">
      <h2>Log</h2> 
      <ul v-if="logNewExpression">
       <li v-for="expression in expressions">{{ expression }}</li>
      </ul>
   </div>
</template>

<script>
export default{

 props: ['calculateIsClicked','expressions','logNewExpression'],

 
}

</script>
<style scoped>



.log{
 border:solid black;
 color: white;
 width: 300px;
 height: 90px;
 margin-left: 300px;
 margin-top: 50px;
 overflow:hidden;
 overflow-y: scroll;
 
}

li{
 color:white;
}
</style>