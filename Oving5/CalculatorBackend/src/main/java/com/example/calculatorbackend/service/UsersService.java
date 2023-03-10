package com.example.calculatorbackend.service;

import com.example.calculatorbackend.model.User;
import com.example.calculatorbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public ResponseEntity<User> createUser(User user) {
        try {
            List<User> users = new ArrayList<>(userRepository.findAll());
            for (User u:users) {
                if(Objects.equals(u.getName(), user.getName())){
                    System.out.println(users);
                    return new ResponseEntity<>(user,HttpStatus.OK);
                }
            }
            User _user = userRepository
                    .save(new User(user.getName()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<User>> getAllUsers(String name) {
        try {
            List<User> users = new ArrayList<>();

            if (name == null)
                users.addAll(userRepository.findAll());
            else
                users.addAll(userRepository.findByName(name));

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> updateUser(long id, User user) {
        Optional<User> users = userRepository.findById(id);

        if (users.isPresent()) {
            User _user = users.get();
            _user.setName(user.getName());
            _user.setPassword(user.getPassword());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    public ResponseEntity<User> getUserById(long id) {
        Optional<User> users =userRepository.findById(id);
        return users.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
