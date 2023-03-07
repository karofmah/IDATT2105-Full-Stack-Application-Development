package com.example.calculatorbackend.repository;

import com.example.calculatorbackend.model.Calculation;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalculationRepository extends JpaRepository<Calculation,Long> {

    List<Calculation> findByUsername(String name);

    <S extends Calculation> S save(S Calculation);

    @Override
    <S extends Calculation> List<S> findAll(Example<S> example);
}
