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

    @Column
    private double answer;

    public Calculation() {
    }

    public Calculation(String username, double valueOne, String operator, double valueTwo, double answer) {
        this.username = username;
        this.valueOne = valueOne;
        this.operator = operator;
        this.valueTwo = valueTwo;
        this.answer=answer;
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
        answer=this.answer;
        switch (operator) {
            case "+":
                answer= valueOne + valueTwo;
                break;

            case "-":
                answer= valueOne - valueTwo;
                break;

            case "*":
                 answer= valueOne * valueTwo;
                 break;


            case "/":
                answer= valueOne / valueTwo;
                break;
            default:
                answer= -1;
        }
        return answer;
    }

    @Override
    public String toString() {
        return valueOne + " " + operator + " " + valueTwo;
    }
}
