package com.example.calculatorbackend.controller;

import com.example.calculatorbackend.model.User;
import com.example.calculatorbackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:5173/")
@RestController
public class UserController {

    private UsersService usersService;

    @Autowired
    public void setUserRepository(UsersService usersService) {
        this.usersService=usersService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return usersService.createUser(user);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
       return usersService.getAllUsers(name);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        return usersService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
        return usersService.updateUser(id,user);
    }

}
