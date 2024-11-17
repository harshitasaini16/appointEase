package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Traveller;
import com.example.demo.service.interfaces.TravellerService;

@RestController
@RequestMapping("/api/v1/traveller")
public class TravellerController {
    private TravellerService travellerService;

    @Autowired
    public TravellerController(TravellerService travellerService) {
        this.travellerService = travellerService;
    }

    @PostMapping("/{appointmentId}")
    public ResponseEntity<Long> addTravellers(@RequestBody List<Traveller> travellers,@PathVariable Long appointmentId) {
        return travellerService.addTraveller(travellers,appointmentId);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Traveller>> getTraveller() {
        return travellerService.getTraveller();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Traveller> getTraveller(@PathVariable Long id) {
        return travellerService.getTraveller(id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraveller(@PathVariable Long id) {
        return travellerService.deleteTraveller(id);
    }
    
    @PutMapping("deleteAll")
    public ResponseEntity<Void> deleteTraveller(@RequestBody List<Long>deletedTravellers) {
        return travellerService.deleteAllTravellers(deletedTravellers);
    }
    
    @GetMapping("appointment/{appointmentId}")
    public ResponseEntity<List<Traveller>> getTravellerByAppointmentId(@PathVariable Long appointmentId) {
        return travellerService.getTravellerByAppointmentId(appointmentId);
    }
}
