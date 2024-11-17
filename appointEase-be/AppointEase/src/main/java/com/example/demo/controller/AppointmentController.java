package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Appointment;
import com.example.demo.service.interfaces.AppointmentService;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("")
    public ResponseEntity<Long> createAppointment() {
        return appointmentService.createAppointment();
    }
    
    @GetMapping("")
    public ResponseEntity<List<Appointment>> getAppointment() {
        return appointmentService.getAppointment();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
        return appointmentService.getAppointment(id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        return appointmentService.deleteAppointment(id);
    }
    
    @PutMapping("/setSlot/{id}/{slotId}")
    public ResponseEntity<Void> updateAppointmentSlot(@PathVariable Long id,@PathVariable Long slotId) {
        return appointmentService.updateAppointmentSlot(id,slotId);
    }
    
    @PutMapping("/user/{appointmentId}/{email}")
    public ResponseEntity<Void> updateUserDetails(@PathVariable Long appointmentId,@PathVariable String email) {
        return appointmentService.updateUserDetails(appointmentId,email);
    }
    
}
