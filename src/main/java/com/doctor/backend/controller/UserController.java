package com.doctor.backend.controller;

import com.doctor.backend.model.user.User;
import com.doctor.backend.repository.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/Users")
    public List<User> all() {
        return (List<User>) repository.findAll();
    }

    @PostMapping("/Users")
    public User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single item

    @GetMapping("/Users/{id}")
    public Optional<User> one(@PathVariable Long id) {

        return repository.findById(id);
    }

    @PutMapping("/Users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setRole(newUser.getRole());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/Users/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
