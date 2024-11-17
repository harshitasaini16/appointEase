package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Verification;
import com.example.demo.service.interfaces.VerificationService;

@RestController
@RequestMapping("/api/v1/verification")
public class VerificationController {
	
    private VerificationService verificationService;
	
    @Autowired
	public VerificationController(VerificationService verificationService) {
		this.verificationService = verificationService;
	}

    @PostMapping("/email")
    public ResponseEntity<Void> sendEmailVerification(@RequestParam("email") String email) {
        return verificationService.sendEmailVerificationCode(email);
    }
    
    @PostMapping("/code")
    public ResponseEntity<Void> verifyEmail(@RequestBody Verification verificationInfo) {
        return verificationService.verifyEmail(verificationInfo.getEmail(),verificationInfo.getCode());
    }
    
    @GetMapping("/confirmation")
    public ResponseEntity<Void> confirmation(@RequestParam("appointmentId") Long appointmentId, @RequestParam("email") String email) {
    	return verificationService.sendConfirmationMail(appointmentId,email);
    }
}
