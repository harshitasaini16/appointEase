package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.AuthResponse;
import com.example.demo.service.interfaces.AdminService;
import com.example.demo.service.interfaces.AuthService;
import com.example.demo.util.JwtUtil;
@Service
public class AuthServiceImp implements AuthService{
	
	private AdminService adminService;
	private JwtUtil jwtUtil;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AuthServiceImp(AdminService adminService, JwtUtil jwtUtil,PasswordEncoder passwordEncoder) {
		this.adminService = adminService;
		this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
	}

	@Override
	public ResponseEntity<AuthResponse> validateRequest(AuthRequest authRequest) {
		ResponseEntity<Admin> admin = this.adminService.getAdmin(authRequest.getUserId());
		if(admin.getStatusCode() == HttpStatus.OK) {
			if(checkPassword(admin.getBody(), authRequest)) {
				String token = jwtUtil.generateToken(authRequest.getUserId());
	            return ResponseEntity.ok(new AuthResponse(token));
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	private boolean checkPassword(Admin admin, AuthRequest authRequest) {
		return passwordEncoder.matches(authRequest.getPassword(), admin.getPassword());
	}

}
