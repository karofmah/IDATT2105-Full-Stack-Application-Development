package com.example.calculatorbackend.model;

public class Calculation {

    private double valueOne;
    private String operator;
    private double valueTwo;

    public Calculation() {
    }

    public double getValueOne() {
        return valueOne;
    }

    public String getOperator() {
        return operator;
    }

    public double getValueTwo() {
        return valueTwo;
    }

    public double calculateAnswer() {

        switch (operator) {
            case "+":
                    return valueOne + valueTwo;

            case "-":
                    return valueOne - valueTwo;

            case "*":
                 return valueOne * valueTwo;


            case "/":
                    return valueOne / valueTwo;

            default:
                return -1;
        }
    }
    @Override
    public String toString() {
        return valueOne + " " + operator + " " + valueTwo;
    }
}
