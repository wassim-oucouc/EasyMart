package org.example.easymart.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.easymart.dto.request.UserDTO;
import org.example.easymart.dto.response.UserDtoResponse;
import org.example.easymart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserDtoResponse register(@Valid @RequestBody UserDTO userDTO) {
        return authService.register(userDTO);
    }

    @PostMapping("/login")
    public UserDtoResponse login(@Valid @RequestParam String username,
                                 @RequestParam String password,
                                 HttpSession session) {
        UserDtoResponse userResponse = authService.login(username, password);
        session.setAttribute("CURRENT_USER", userResponse);
        return userResponse;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        return authService.logout(session);
    }
}
