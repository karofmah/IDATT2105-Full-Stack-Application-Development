package com.example.calculatorbackend.controller;

import com.example.calculatorbackend.model.Calculation;
import com.example.calculatorbackend.service.CalculationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173/")
public class CalculationController {
    private static final Logger _logger=
            LoggerFactory.getLogger(CalculationController.class);

    private CalculationsService calculationsService;

    @Autowired
    public void setService(CalculationsService calculationsService) {
        this.calculationsService=calculationsService;
    }

    @PostMapping("/post")
    public double posted(@RequestBody Calculation calculation) {
        try {
            System.out.println(calculation.getValueOne() + " " + calculation.getOperator()
                    + " " + calculation.getValueTwo() + " = " + calculationsService.calculate(calculation));
            _logger.info(String.valueOf(calculationsService.calculate(calculation)));
            return calculationsService.calculate(calculation);
        }catch (Exception e){
            _logger.warn(e.getMessage());
        }
       return 0;
    }

}
