package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class AuthRequest {
	private String userId;
    private String password;
    
    public AuthRequest() {
    	
    }
    
	public AuthRequest(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}