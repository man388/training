package com.cache.training.classwork.controller;

import com.cache.training.classwork.entities.User;
import com.cache.training.classwork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping("/{id}/")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
}