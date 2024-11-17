package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Slot;
import com.example.demo.entity.User;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.interfaces.AppointmentService;
import com.example.demo.service.interfaces.SlotService;
import com.example.demo.service.interfaces.UserService;

@Service
public class AppointmentServiceImp implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private SlotService slotService;
    private UserService userService;

    @Autowired
    public AppointmentServiceImp(AppointmentRepository appointmentRepository, SlotService slotService,UserService userService) {
        this.appointmentRepository = appointmentRepository;
        this.slotService = slotService;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Long> createAppointment() {
        Appointment appointment = new Appointment();
        appointmentRepository.save(appointment);
        Long appointmentId = appointment.getId();
        return ResponseEntity.ok(appointmentId);
    }

    @Override
    public ResponseEntity<List<Appointment>> getAppointment() {
    	System.out.println(2);
        return ResponseEntity.ok(appointmentRepository.findAll());
    }

    @Override
    public ResponseEntity<Void> deleteAppointment(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            appointmentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Appointment> getAppointment(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Appointment> updateAppointment(Long id, Appointment appointment) {
        Optional<Appointment> currentAppointment = appointmentRepository.findById(id);
        if (currentAppointment.isPresent()) {
            appointment.setId(id);
            appointmentRepository.save(appointment);
            return ResponseEntity.ok(appointment);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> updateAppointmentSlot(Long id, Long slotId) {
        ResponseEntity<Slot> slotResponse = slotService.getSlot(slotId);
        if (slotResponse.getStatusCode().is2xxSuccessful() && slotResponse.getBody() != null) {
            Slot slot = slotResponse.getBody();

            Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
            if (appointmentOpt.isPresent()) {
                Appointment appointment = appointmentOpt.get();
                
                if (appointment.getSlot() != null) {
                    slotService.vacantSlot(appointment.getSlot());
                }

                slotService.fillSlot(slotId);
                appointment.setSlot(slot);
                appointmentRepository.save(appointment);

                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(400).build();
    }

    @Override
    public ResponseEntity<Void> updateUserDetails(Long appointmentId, String email) {
        Optional<Appointment> appointmentOpt = this.appointmentRepository.findById(appointmentId);
        
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            
            ResponseEntity<User> userResponse = this.userService.createUser(email);
            if (userResponse.getStatusCode().is2xxSuccessful()) {
                User user = userResponse.getBody();
                appointment.setUser(user);
                this.appointmentRepository.save(appointment);
                
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(userResponse.getStatusCode()).build();
            }
        }
        return ResponseEntity.notFound().build();
    }

}
