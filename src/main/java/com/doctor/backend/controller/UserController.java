package com.doctor.backend.controller;

import com.doctor.backend.dto.UserDto;
import com.doctor.backend.repository.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/Users")
    public List<UserDto> all() {
        List<UserDto> users = new ArrayList<>();


        repository.findAll().forEach(user -> users.add(UserDto.fromEntity(user)));
        return users;
    }

    @PostMapping("/Users")
    public UserDto newUser(@RequestBody UserDto newUser) {
        return UserDto.fromEntity(repository.save(UserDto.toEntity(newUser)));
    }

    // Single item

    @GetMapping("/Users/{id}")
    public UserDto one(@PathVariable Long id) {

        return UserDto.fromEntity(repository.findById(id).orElseGet(() -> UserDto.toEntity(new UserDto())));
    }

    @PutMapping("/Users/{id}")
    public UserDto replaceUser(@RequestBody UserDto newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setRole(newUser.getRole());
                    return UserDto.fromEntity(repository.save(user));
                })
                .orElseGet(() -> UserDto.fromEntity(repository.save(UserDto.toEntity(newUser))));
    }

    @DeleteMapping("/Users/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
