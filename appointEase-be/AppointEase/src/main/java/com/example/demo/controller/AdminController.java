package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Appointment;
import com.example.demo.service.interfaces.AdminService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	private AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAppointment() {
        return adminService.getAppointment();
    }
}
