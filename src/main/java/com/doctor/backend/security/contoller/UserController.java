package com.doctor.backend.security.contoller;

import com.doctor.backend.model.Patient;
import com.doctor.backend.security.dto.RegisterRequest;
import com.doctor.backend.security.model.ERole;
import com.doctor.backend.security.model.Role;
import com.doctor.backend.security.model.User;
import com.doctor.backend.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("register")
    public void register(@RequestBody RegisterRequest request) {
        var role = new ArrayList<Role>();
        role.add(new Role(ERole.PATIENT));
        var patient = new Patient();
        patient.setEmail(request.getEmail());
        var user = new User(request.getEmail(), request.getPassword(), role, true, patient);
        userService.registerUser(user);
    }

    @GetMapping("")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("el 3asba");
    }
}
