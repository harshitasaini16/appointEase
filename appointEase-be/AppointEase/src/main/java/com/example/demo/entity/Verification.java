package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Verification {
	
	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="code")
	private String code;
	
	private Verification() {
		
	}
	
	public Verification(String email,String code) {
		super();
		this.email = email;
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Verification [email=" + email + ", code=" + code + "]";
	}
	
}
