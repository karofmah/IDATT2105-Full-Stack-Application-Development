import { ref} from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', () => {
  const name = ref("")
  const email=ref("")
  const username=ref("")

  function setName(newName:string) {
    useCounterStore().$patch({name:newName})
  }
  function setEmail(newEmail:string){
    useCounterStore().$patch({email:newEmail})
  }
  function setUsername(newUsername:string){
    useCounterStore().$patch({username:newUsername})
  }
  function getName() {
    return name.value
  }
  function getEmail(){
    return email.value
  }
  function getUsername(){
    return username.value
  }

  return { name, email, username,setName, getName,setEmail,getEmail,setUsername,getUsername }
})

