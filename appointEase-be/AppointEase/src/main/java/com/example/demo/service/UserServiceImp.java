package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.interfaces.UserService;

@Service
public class UserServiceImp implements UserService{
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImp(UserRepository userRepository) {
		this.userRepository =  userRepository;
	}

	@Override
	public ResponseEntity<User> createUser(String email) {
	    User user = new User(email);
	    user = userRepository.save(user);
	    return ResponseEntity.ok(user); 
	}
}
