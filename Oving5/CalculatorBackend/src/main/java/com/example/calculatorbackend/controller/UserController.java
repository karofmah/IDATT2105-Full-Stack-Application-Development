package com.example.calculatorbackend.controller;

import com.example.calculatorbackend.model.User;
import com.example.calculatorbackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@EnableAutoConfiguration
@CrossOrigin(origins="http://localhost:5173/")
@RestController
public class UserController {

    private UsersService usersService;

    @Autowired
    public void setUserService(UsersService usersService) {
        this.usersService=usersService;
    }
    @GetMapping("users")
    public User getUser(@RequestParam String name){
        return usersService.getUser(name);
    }


    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User user){
        System.out.println(user);
        return usersService.createUser(user);
    }/*
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
       return usersService.getAllUsers();
    }*/
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        return usersService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
        return usersService.updateUser(id,user);
    }

}
