<template>
  <label>{{ label }}</label>
  <input
    v-bind="$attrs"
    :value="modelValue"
    :placeholder="label"
    @input="$emit('update:modelValue',($event.target.value))"
    class="field"
  >
</template>

<script>

export default {
  props: {
    label: {
      type: String,
      default: ''
    },
    modelValue: {
      type: [String, Number,Object,Function],
      default: ''
    }
  }
}
</script>
<style>
label{
  color: white;
}
input{
  display:flex;
  flex-direction: column;
  /*border-radius:10px;*/
  background-color: rgb(57, 62, 66);
  color:white;
  border:none;
  border-bottom:1px solid black;
  background-color: rgb(62, 62, 66);
  padding:10px;
 

}
input:focus{
  outline:none;
  border-left:1px solid black;

  
}
input:-webkit-autofill,
  input:-webkit-autofill:hover,
  input:-webkit-autofill:focus,
  input:-webkit-autofill:active {
    -webkit-box-shadow: 0 0 0px 1000px rgb(62, 62, 66)  inset !important; /* Override the default white background */
    -webkit-text-fill-color:white !important; /* Set the text color to white */
  }
button{
  border: solid black;
  color:black
}
</style>