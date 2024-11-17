package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Slot;
import com.example.demo.service.interfaces.SlotService;

@RestController
@RequestMapping("/api/v1/slot")
public class SlotController {

    private final SlotService slotService;

    @Autowired
    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping
    public ResponseEntity<List<Slot>> getAllSlots() {
        return slotService.getSlot();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Slot> getSlotById(@PathVariable Long id) {
        return slotService.getSlot(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Slot> updateSlot(@PathVariable Long id, @RequestBody Slot slot) {
        return slotService.updateSlot(id, slot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long id) {
        return slotService.deleteSlot(id);
    }
    
    @GetMapping("/slotByDate/{dateTime}")
    public ResponseEntity<List<Slot>> getSlotByDateTime(@PathVariable 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return slotService.getSlotByDateTime(dateTime);
    }
}
