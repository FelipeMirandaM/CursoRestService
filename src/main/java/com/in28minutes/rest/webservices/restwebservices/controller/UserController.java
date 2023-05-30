package com.in28minutes.rest.webservices.restwebservices.controller;


import com.in28minutes.rest.webservices.restwebservices.Exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restwebservices.model.User;
import com.in28minutes.rest.webservices.restwebservices.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable Integer id){

        User user = userService.findById(id);

        if(user==null){
            throw new UserNotFoundException("id " + id);
        }

        return user;
    }

    @PostMapping(path= "/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
