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
  border-radius:10px;
}
button{
  border: solid black;
  color:black
}
</style>