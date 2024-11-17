package com.example.demo.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface VerificationService {
	
	public ResponseEntity<Void> sendEmailVerificationCode(String mobileNumber);
	public ResponseEntity<Void> verifyEmail(String email, String code);
	public ResponseEntity<Void> sendConfirmationMail(Long appointmentId, String email);
}
