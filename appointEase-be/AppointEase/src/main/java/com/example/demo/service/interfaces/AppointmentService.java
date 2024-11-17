package com.example.demo.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Traveller;

public interface AppointmentService {
	
	public ResponseEntity<Long> createAppointment();
	
	public ResponseEntity<List<Appointment>> getAppointment();
	
	public ResponseEntity<Appointment> getAppointment(Long id);
	
	public ResponseEntity<Void> deleteAppointment(Long id);
	
	public ResponseEntity<Appointment> updateAppointment(Long id, Appointment appointment); 
	
	public ResponseEntity<Void> updateAppointmentSlot(Long id, Long slotId);
	
	public ResponseEntity<Void> updateUserDetails(Long appointmentId, String email);
}
