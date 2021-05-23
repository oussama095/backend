package com.doctor.backend.security.contoller;

import com.doctor.backend.model.Patient;
import com.doctor.backend.security.dto.RegisterRequest;
import com.doctor.backend.security.model.ERole;
import com.doctor.backend.security.model.Role;
import com.doctor.backend.security.model.User;
import com.doctor.backend.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @PostMapping("")
    @CrossOrigin(origins = "*", maxAge = 3600,
            allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
    public String login(@RequestBody RegisterRequest request) {
        return "login";
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
}
