package com.example.calculatorbackend.repository;

import com.example.calculatorbackend.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByNameAndPassword(String name,String password);

    <S extends User> S save(S User);

    @Override
    <S extends User> List<S> findAll(Example<S> example);
}
