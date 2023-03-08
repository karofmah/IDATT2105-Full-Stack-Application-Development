package com.example.calculatorbackend.model;

import jakarta.persistence.*;


@Entity
@Table
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String username;

    @Column
    private double valueOne;

    @Column
    private String operator;

    @Column
    private double valueTwo;


    public Calculation() {
    }

    public Calculation(String username, double valueOne, String operator, double valueTwo) {
        this.username = username;
        this.valueOne = valueOne;
        this.operator = operator;
        this.valueTwo = valueTwo;

    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
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
