package com.example.calculatorbackend.controller;

import com.example.calculatorbackend.model.Calculation;
import com.example.calculatorbackend.service.CalculationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173/")
public class CalculationController {


    private CalculationsService calculationsService;

    @Autowired
    public void setService(CalculationsService calculationsService) {
        this.calculationsService = calculationsService;
    }

    @PostMapping("/calculate")
    public double saveCalculation(@RequestBody Calculation calculation) {
        return calculationsService.calculate(calculation);
    }

 @PostMapping("/calculations")
    public ResponseEntity<Calculation> createCalculation(@RequestBody Calculation calculation) {
        return calculationsService.createCalculation(calculation);
    }
    @GetMapping("/calculations")
    public ResponseEntity<List<Calculation>> getAllUsers(@RequestParam(required = false) String username) {
        return calculationsService.getAllCalculations(username);
    }
}
