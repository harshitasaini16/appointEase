package com.example.demo.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.User;

public interface UserService {
	
	public ResponseEntity<User> createUser(String email);
}
