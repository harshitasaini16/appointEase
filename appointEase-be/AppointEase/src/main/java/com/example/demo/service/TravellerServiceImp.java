package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Traveller;
import com.example.demo.entity.Appointment;
import com.example.demo.repository.TravellerRepository;
import com.example.demo.service.interfaces.TravellerService;
import com.example.demo.service.interfaces.AppointmentService;

@Service
public class TravellerServiceImp implements TravellerService {

    private final TravellerRepository travellerRepository;
    private final AppointmentService appointmentService;

    @Autowired
    public TravellerServiceImp(TravellerRepository travellerRepository, AppointmentService appointmentService) {
        this.travellerRepository = travellerRepository;
        this.appointmentService = appointmentService;
    }

    @Override
    public ResponseEntity<Long> addTraveller(List<Traveller> travellers, Long appointmentId) {
        Appointment appointment = appointmentService.getAppointment(appointmentId).getBody();
        if (appointment == null) {
            return ResponseEntity.badRequest().body(null);
        }
        
        for (Traveller traveller : travellers) {
            traveller.setAppointment(appointment);
        }
        
        travellerRepository.saveAll(travellers);
        return ResponseEntity.ok(null); 
    }

    @Override
    public ResponseEntity<List<Traveller>> getTraveller() {
        List<Traveller> travellers = travellerRepository.findAll();
        return ResponseEntity.ok(travellers);
    }

    @Override
    public ResponseEntity<List<Traveller>> getTravellerByAppointmentId(Long id) {
        List<Traveller> travellers = travellerRepository.findByAppointmentId(id);
        if (travellers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(travellers);
    }

    @Override
    public ResponseEntity<Traveller> getTraveller(Long id) {
        Optional<Traveller> traveller = travellerRepository.findById(id);
        return traveller.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Void> deleteTraveller(Long id) {
        Optional<Traveller> traveller = travellerRepository.findById(id);
        if (traveller.isPresent()) {
            travellerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Traveller> updateTraveller(Long id, Traveller traveller) {
        Optional<Traveller> currentTraveller = travellerRepository.findById(id);
        if (currentTraveller.isPresent()) {
            traveller.setId(id);
            travellerRepository.save(traveller);
            return ResponseEntity.ok(traveller);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public ResponseEntity<Void> deleteAllTravellers(List<Long> deletedTravellers) {
        for (Long id : deletedTravellers) {
            Optional<Traveller> traveller = travellerRepository.findById(id);
            if (traveller.isPresent()) {
                travellerRepository.deleteById(id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        return ResponseEntity.ok().build();
    }
}
