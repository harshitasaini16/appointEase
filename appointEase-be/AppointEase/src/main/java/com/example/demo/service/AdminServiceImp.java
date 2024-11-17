package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Appointment;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.interfaces.AdminService;
import com.example.demo.service.interfaces.AppointmentService;
@Service
public class AdminServiceImp implements AdminService {
	
	private AppointmentService appointmentService;
	private AdminRepository adminRepository;
	
	@Autowired
	public AdminServiceImp(AppointmentService appointmentService,AdminRepository adminRepository) {
		this.appointmentService = appointmentService;
		this.adminRepository = adminRepository;
	}
	
	@Override
	public ResponseEntity<List<Appointment>> getAppointment() {
		// TODO Auto-generated method stub
		return this.appointmentService.getAppointment();
	}

	@Override
	public ResponseEntity<Admin> getAdmin(String id) {
	    Optional<Admin> admin = this.adminRepository.findById(id);
	    if (admin.isPresent()) {
	        return ResponseEntity.ok(admin.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
