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
    public double calculateAnswer(@RequestBody Calculation calculation) {
        return calculationsService.calculate(calculation);
    }

 @PostMapping("/calculations")
    public ResponseEntity<Calculation> saveCalculation(@RequestBody Calculation calculation) {
        return calculationsService.saveCalculation(calculation);
    }
    @GetMapping("/calculations")
    public ResponseEntity<List<Calculation>> getAllCalculationsByName(@RequestParam("username") String username) {
        return calculationsService.getAllCalculationsByName(username);
    }
    @GetMapping("/calculations/{id}")
    public ResponseEntity<Calculation> getCalculationById(@PathVariable("id") long id){
        return calculationsService.getCalculationById(id);
    }
}
