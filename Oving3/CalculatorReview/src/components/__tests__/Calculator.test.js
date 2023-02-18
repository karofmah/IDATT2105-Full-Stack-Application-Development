import {test, expect} from 'vitest'
import { mount } from '@vue/test-utils'
import Calculator from '../Calculator.vue'


test("mount calculator and pass value",async()=>{
    expect(Calculator).toBeTruthy();
    const wrapper = mount(Calculator,{
        props:{
            header:'My Calculator',
            valueOne:'1',
            valueTwo:'2',
            operatorIsClicked:true,
            operatorSymbol:'+',
            calculateIsClicked:true,
            calculatorScreen:3,
        }
        

    });    


wrapper.vm.saveNumber();
wrapper.vm.operatorClicked();
wrapper.vm.calculateNumber();
wrapper.vm.clearAll();
wrapper.vm.answerClicked();
wrapper.vm.deleteNumber();
wrapper.vm.commaClicked();

expect(wrapper.emitted('save-number')).toBeTruthy();
expect(wrapper.emitted('operator-clicked')).toBeTruthy();
expect(wrapper.emitted('calculate-number')).toBeTruthy();
expect(wrapper.emitted('clear-all')).toBeTruthy();
expect(wrapper.emitted('answer-clicked')).toBeTruthy();
expect(wrapper.emitted('delete-number')).toBeTruthy();
expect(wrapper.emitted('comma-clicked')).toBeTruthy();

expect(wrapper.props('header')).toBe("My Calculator");
expect(wrapper.props('valueOne')).toBe('1');
expect(wrapper.props('valueTwo')).toBe('2');
expect(wrapper.props('operatorIsClicked')).toBe(true);
expect(wrapper.props('operatorSymbol')).toBe("+");
expect(wrapper.props('calculateIsClicked')).toBe(true);
expect(wrapper.props('calculatorScreen')).toBe(3);
  
 })