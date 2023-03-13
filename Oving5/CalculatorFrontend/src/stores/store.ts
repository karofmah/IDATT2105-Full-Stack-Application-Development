import { ref} from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', () => {
  const name = ref("")
  const email=ref("")
  

  function setName(newName:string) {
    useCounterStore().$patch({name:newName})
  }
  function setEmail(newEmail:string){
    useCounterStore().$patch({email:newEmail})
  }
  
  function getName() {
    return name.value
  }
  function getEmail(){
    return email.value
  }
  

  return { name, email, setName, getName,setEmail,getEmail }
})

