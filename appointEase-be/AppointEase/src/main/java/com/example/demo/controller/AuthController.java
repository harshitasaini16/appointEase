package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.AuthResponse;
import com.example.demo.service.interfaces.AuthService;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;
    
    @Autowired
    public AuthController(AuthService authService) {
    	this.authService = authService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
    	return this.authService.validateRequest(authRequest);
    }
}
