package com.example.demo.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Appointment;

public interface AdminService {
	public ResponseEntity<List<Appointment>> getAppointment();
	
	public ResponseEntity<Admin> getAdmin(String id);
}
