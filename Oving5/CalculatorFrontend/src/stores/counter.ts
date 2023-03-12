import { ref} from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', () => {
  const name = ref("")
  const email=ref("")
  const username=ref("")
  const password=ref("")

  function setName(newName:string) {
    useCounterStore().$patch({name:newName})
  }
  function setEmail(newEmail:string){
    useCounterStore().$patch({email:newEmail})
  }
  function setUsername(newUsername:string){
    useCounterStore().$patch({username:newUsername})
  }
  function setPassword(newPassword:string){
    useCounterStore().$patch({password:newPassword})
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
  function getPassword(){
    return password.value
  }

  return { name, email, username,password,setName, getName,setEmail,getEmail,setUsername,getUsername,setPassword,getPassword }
})

