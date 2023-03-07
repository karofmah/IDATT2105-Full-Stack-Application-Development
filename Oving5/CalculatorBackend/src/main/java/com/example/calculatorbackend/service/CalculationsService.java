package com.example.calculatorbackend.service;

import com.example.calculatorbackend.controller.CalculationController;
import com.example.calculatorbackend.model.Calculation;
import com.example.calculatorbackend.repository.CalculationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculationsService {

    private static final Logger _logger =
            LoggerFactory.getLogger(CalculationController.class);
    CalculationRepository calculationRepository;
    @Autowired
    public void setCalculationRepository(CalculationRepository calculationRepository) {
        this.calculationRepository=calculationRepository;
    }
    public ResponseEntity<Calculation> createCalculation(Calculation calculation) {
        try {
            Calculation _calculation = calculationRepository
                    .save(new Calculation(calculation.getUsername()
                            ,calculation.getValueOne(),calculation.getOperator()
                            ,calculation.getValueTwo(),calculation.calculateAnswer()));
            return new ResponseEntity<>(_calculation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        public double calculate(Calculation calculation){
            try {
                System.out.println(calculation.getValueOne() + " " + calculation.getOperator()
                        + " " + calculation.getValueTwo() + " = " + calculation.calculateAnswer());
                _logger.info(String.valueOf(calculation.calculateAnswer()));
                return calculation.calculateAnswer();
            }catch (Exception e){
                _logger.warn(e.getMessage());
            }
            return 0;
        }


    public ResponseEntity<List<Calculation>> getAllCalculations(String username) {
        try {
            List<Calculation> calculations = new ArrayList<>();

            if (username == null)
                calculations.addAll(calculationRepository.findAll());
            else
                calculations.addAll(calculationRepository.findByUsername(username));

            if (calculations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(calculations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}


