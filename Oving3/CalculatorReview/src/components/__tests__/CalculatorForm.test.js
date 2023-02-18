import {test, expect} from 'vitest'
import { mount } from '@vue/test-utils'
import CalculatorForm from '../CalculatorForm.vue'
import {createPinia, defineStore, setActivePinia} from 'pinia'

test("mount calculator form",async()=>{
  setActivePinia(createPinia())
    expect(CalculatorForm).toBeTruthy();

    const store = defineStore({
        id: 'CalculatorForm',
        state: () => ({
          name: '',
          email: '',
        }),
        actions: {
          setName(name) {
            state.name = name;
          },
          setEmail(email) {
            state.email = email;
          },
        },
      });

   const wrapper = mount(CalculatorForm,{});
  await wrapper.find('#name').setValue('Karo')
  await wrapper.find('#email').setValue('karofm@ntnu.no')
  await wrapper.find('#message').setValue('My message')
  await wrapper.find('#submit').trigger('click')
  expect(wrapper.find('#name').element.value).toBe('Karo')
  expect(wrapper.find('#email').element.value).toBe('karofm@ntnu.no')
  expect(wrapper.find('#message').element.value).toBe('My message')
 })