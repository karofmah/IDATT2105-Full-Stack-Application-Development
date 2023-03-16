package com.example.calculatorbackend.controller;

import com.example.calculatorbackend.model.User;
import com.example.calculatorbackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

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
    public User getUser(@RequestParam String name,@RequestParam String password) throws NoSuchAlgorithmException {
        return usersService.getUser(name,password);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return usersService.createUser(user);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        return usersService.getUserById(id);
    }



}
