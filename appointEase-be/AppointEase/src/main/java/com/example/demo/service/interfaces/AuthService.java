package com.example.demo.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.AuthResponse;

public interface AuthService {
	public ResponseEntity<AuthResponse> validateRequest(AuthRequest authRequest);
}
