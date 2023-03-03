package com.example.calculatorbackend.service;

import com.example.calculatorbackend.model.Calculation;
import org.springframework.stereotype.Service;
@Service
public class CalculationsService {

    public double calculate(Calculation calculation){
        return calculation.calculateAnswer();
    }

}
